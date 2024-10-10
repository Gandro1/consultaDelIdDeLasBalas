/*     */ package com.tacz.guns.item;
/*     */ import com.google.common.base.Supplier;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunFireEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.api.item.nbt.GunItemDataAccessor;
/*     */ import com.tacz.guns.command.sub.DebugCommand;
/*     */ import com.tacz.guns.debug.GunMeleeDebug;
/*     */ import com.tacz.guns.entity.EntityKineticBullet;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunFire;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.EffectData;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.MeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunDefaultMeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunMeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.InaccuracyType;
/*     */ import com.tacz.guns.sound.SoundManager;
/*     */ import it.unimi.dsi.fastutil.Pair;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ public class ModernKineticGunItem extends AbstractGunItem implements GunItemDataAccessor {
/*     */   public static final String TYPE_NAME = "modern_kinetic";
/*     */   
/*     */   public ModernKineticGunItem() {
/*  59 */     super((new Item.Properties()).m_41487_(1));
/*     */   }
/*     */   private static final DoubleFunction<AttributeModifier> AM_FACTORY;
/*     */   
/*     */   public void bolt(ItemStack gunItem) {
/*  64 */     if (getCurrentAmmoCount(gunItem) > 0) {
/*  65 */       reduceCurrentAmmoCount(gunItem);
/*  66 */       setBulletInBarrel(gunItem, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shoot(ItemStack gunItem, Supplier<Float> pitch, Supplier<Float> yaw, boolean tracer, LivingEntity shooter) {
/*  72 */     ResourceLocation gunId = getGunId(gunItem);
/*  73 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  74 */     if (iGun == null) {
/*     */       return;
/*     */     }
/*  77 */     Optional<CommonGunIndex> gunIndexOptional = TimelessAPI.getCommonGunIndex(gunId);
/*  78 */     if (gunIndexOptional.isEmpty()) {
/*     */       return;
/*     */     }
/*  81 */     CommonGunIndex gunIndex = gunIndexOptional.get();
/*  82 */     BulletData bulletData = gunIndex.getBulletData();
/*  83 */     GunData gunData = gunIndex.getGunData();
/*  84 */     ResourceLocation ammoId = gunData.getAmmoId();
/*  85 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  86 */     AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity(shooter).getCacheProperty();
/*  87 */     if (cacheProperty == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  92 */     InaccuracyType inaccuracyType = InaccuracyType.getInaccuracyType(shooter);
/*  93 */     float inaccuracy = Math.max(0.0F, ((Float)((Map)cacheProperty.getCache("inaccuracy")).get(inaccuracyType)).floatValue());
/*  94 */     if (inaccuracyType == InaccuracyType.AIM) {
/*  95 */       inaccuracy = Math.max(0.0F, ((Float)((Map)cacheProperty.getCache("aim_inaccuracy")).get(inaccuracyType)).floatValue());
/*     */     }
/*  97 */     float finalInaccuracy = inaccuracy;
/*     */ 
/*     */     
/* 100 */     Pair<Integer, Boolean> silence = (Pair<Integer, Boolean>)cacheProperty.getCache("silence");
/* 101 */     int soundDistance = ((Integer)silence.first()).intValue();
/* 102 */     boolean useSilenceSound = ((Boolean)silence.right()).booleanValue();
/*     */ 
/*     */     
/* 105 */     float speed = ((Float)cacheProperty.getCache("ammo_speed")).floatValue();
/* 106 */     float finalSpeed = Mth.m_14036_(speed / 20.0F, 0.0F, Float.MAX_VALUE);
/*     */     
/* 108 */     int bulletAmount = Math.max(bulletData.getBulletAmount(), 1);
/*     */     
/* 110 */     int cycles = (fireMode == FireMode.BURST) ? gunData.getBurstData().getCount() : 1;
/*     */     
/* 112 */     long period = (fireMode == FireMode.BURST) ? gunData.getBurstShootInterval() : 1L;
/*     */     
/* 114 */     boolean consumeAmmo = IGunOperator.fromLivingEntity(shooter).consumesAmmoOrNot();
/*     */     
/* 116 */     CycleTaskHelper.addCycleTask(() -> {
/*     */           if (shooter.m_21224_()) {
/*     */             return false;
/*     */           }
/*     */           
/*     */           if (consumeAmmo) {
/*     */             Bolt boltType = gunData.getBolt();
/*     */             
/* 124 */             boolean hasAmmoInBarrel = (hasBulletInBarrel(gunItem) && boltType != Bolt.OPEN_BOLT);
/*     */             int ammoCount = getCurrentAmmoCount(gunItem) + (hasAmmoInBarrel ? 1 : 0);
/*     */             if (ammoCount <= 0) {
/*     */               return false;
/*     */             }
/*     */           } 
/*     */           boolean fire = !MinecraftForge.EVENT_BUS.post((Event)new GunFireEvent(shooter, gunItem, LogicalSide.SERVER));
/*     */           if (fire) {
/*     */             NetworkHandler.sendToTrackingEntity(new ServerMessageGunFire(shooter.m_19879_(), gunItem), (Entity)shooter);
/*     */             if (consumeAmmo) {
/*     */               reduceAmmo(gunItem);
/*     */             }
/*     */             Level world = shooter.m_9236_();
/*     */             for (int i = 0; i < bulletAmount; i++) {
/*     */               doSpawnBulletEntity(world, shooter, gunItem, ((Float)pitch.get()).floatValue(), ((Float)yaw.get()).floatValue(), finalSpeed, finalInaccuracy, ammoId, gunId, tracer, gunData, bulletData);
/*     */             }
/*     */             if (soundDistance > 0) {
/*     */               String soundId = useSilenceSound ? SoundManager.SILENCE_3P_SOUND : SoundManager.SHOOT_3P_SOUND;
/*     */               SoundManager.sendSoundToNearby(shooter, soundDistance, gunId, soundId, 0.8F, 0.9F + shooter.m_217043_().m_188501_() * 0.125F);
/*     */             } 
/*     */           } 
/*     */           return true;
/*     */         }period, cycles);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void melee(LivingEntity user, ItemStack gunItem) {
/* 155 */     ResourceLocation gunId = getGunId(gunItem);
/* 156 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(gunIndex -> {
/*     */           GunMeleeData meleeData = gunIndex.getGunData().getMeleeData();
/*     */           float distance = meleeData.getDistance();
/*     */           ResourceLocation muzzleId = getAttachmentId(gunItem, AttachmentType.MUZZLE);
/*     */           MeleeData muzzleData = getMeleeData(muzzleId);
/*     */           if (muzzleData != null) {
/*     */             doMelee(user, distance, muzzleData.getDistance(), muzzleData.getRangeAngle(), muzzleData.getKnockback(), muzzleData.getDamage(), muzzleData.getEffects());
/*     */             return;
/*     */           } 
/*     */           ResourceLocation stockId = getAttachmentId(gunItem, AttachmentType.STOCK);
/*     */           MeleeData stockData = getMeleeData(stockId);
/*     */           if (stockData != null) {
/*     */             doMelee(user, distance, stockData.getDistance(), stockData.getRangeAngle(), stockData.getKnockback(), stockData.getDamage(), stockData.getEffects());
/*     */             return;
/*     */           } 
/*     */           GunDefaultMeleeData defaultData = meleeData.getDefaultMeleeData();
/*     */           if (defaultData == null) {
/*     */             return;
/*     */           }
/*     */           doMelee(user, distance, defaultData.getDistance(), defaultData.getRangeAngle(), defaultData.getKnockback(), defaultData.getDamage(), Collections.emptyList());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 182 */     AM_FACTORY = (amount -> new AttributeModifier(UUID.randomUUID(), "TACZ Melee Damage", amount, AttributeModifier.Operation.ADDITION));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doMelee(LivingEntity user, float gunDistance, float meleeDistance, float rangeAngle, float knockback, float damage, List<EffectData> effects) {
/* 189 */     double distance = (gunDistance + meleeDistance);
/* 190 */     float xRot = (float)Math.toRadians(-user.m_146909_());
/* 191 */     float yRot = (float)Math.toRadians(-user.m_146908_());
/*     */     
/* 193 */     Vec3 eyeVec = (new Vec3(0.0D, 0.0D, 1.0D)).m_82496_(xRot).m_82524_(yRot).m_82541_().m_82490_(distance);
/*     */     
/* 195 */     Vec3 centrePos = user.m_146892_().m_82546_(eyeVec);
/*     */     
/* 197 */     List<LivingEntity> entityList = user.m_9236_().m_45976_(LivingEntity.class, user.m_20191_().m_82400_(distance));
/* 198 */     Supplier<Float> supplier = Suppliers.memoize(() -> {
/*     */           AttributeInstance instance = user.m_21051_(Attributes.f_22281_);
/*     */           
/*     */           if (instance == null) {
/*     */             return Float.valueOf(damage);
/*     */           }
/*     */           double oldBase = instance.m_22115_();
/*     */           AttributeModifier modifier = AM_FACTORY.apply(damage);
/*     */           try {
/*     */             instance.m_22100_(0.0D);
/*     */             instance.m_22118_(modifier);
/*     */             return Float.valueOf((float)instance.m_22135_());
/*     */           } finally {
/*     */             instance.m_22100_(oldBase);
/*     */             instance.m_22130_(modifier);
/*     */           } 
/*     */         });
/* 215 */     for (LivingEntity living : entityList) {
/*     */       
/* 217 */       Vec3 targetVec = living.m_146892_().m_82546_(centrePos);
/*     */       
/* 219 */       double targetLength = targetVec.m_82553_();
/*     */       
/* 221 */       if (targetLength < distance) {
/*     */         continue;
/*     */       }
/*     */       
/* 225 */       double degree = Math.toDegrees(Math.acos(targetVec.m_82526_(eyeVec) / targetLength * distance));
/*     */       
/* 227 */       if (degree < (rangeAngle / 2.0F))
/*     */       {
/* 229 */         if (user.m_142582_((Entity)living)) {
/* 230 */           doPerLivingHurt(user, living, knockback, ((Float)supplier.get()).floatValue(), effects);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 236 */     if (user instanceof Player) { Player player = (Player)user;
/* 237 */       player.m_36399_(0.1F); }
/*     */ 
/*     */ 
/*     */     
/* 241 */     if (DebugCommand.DEBUG) {
/* 242 */       GunMeleeDebug.showRange(user, (int)Math.round(distance), centrePos, eyeVec, rangeAngle);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void doPerLivingHurt(LivingEntity user, LivingEntity target, float knockback, float damage, List<EffectData> effects) {
/* 247 */     if (target.equals(user)) {
/*     */       return;
/*     */     }
/* 250 */     target.m_147240_(knockback, (float)Math.sin(Math.toRadians(user.m_146908_())), (float)-Math.cos(Math.toRadians(user.m_146908_())));
/* 251 */     if (user instanceof Player) { Player player = (Player)user;
/* 252 */       target.m_6469_(user.m_269291_().m_269075_(player), damage); }
/*     */     else
/* 254 */     { target.m_6469_(user.m_269291_().m_269333_(user), damage); }
/*     */ 
/*     */     
/* 257 */     user.m_19970_(user, (Entity)target);
/*     */     
/* 259 */     if (!target.m_6084_()) {
/*     */       return;
/*     */     }
/* 262 */     for (EffectData data : effects) {
/* 263 */       MobEffect mobEffect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(data.getEffectId());
/* 264 */       if (mobEffect == null) {
/*     */         continue;
/*     */       }
/* 267 */       int time = Math.max(0, data.getTime() * 20);
/* 268 */       int amplifier = Math.max(0, data.getAmplifier());
/* 269 */       MobEffectInstance effectInstance = new MobEffectInstance(mobEffect, time, amplifier, false, data.isHideParticles());
/* 270 */       target.m_7292_(effectInstance);
/*     */     } 
/* 272 */     Level level = user.m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/* 273 */       int count = (int)(damage * 0.5D);
/* 274 */       serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123798_, target.m_20185_(), target.m_20227_(0.5D), target.m_20189_(), count, 0.1D, 0.0D, 0.1D, 0.2D); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void fireSelect(ItemStack gunItem) {
/* 280 */     ResourceLocation gunId = getGunId(gunItem);
/* 281 */     TimelessAPI.getCommonGunIndex(gunId).map(gunIndex -> {
/*     */           FireMode fireMode = getFireMode(gunItem);
/*     */           List<FireMode> fireModeSet = gunIndex.getGunData().getFireModeSet();
/*     */           int nextIndex = (fireModeSet.indexOf(fireMode) + 1) % fireModeSet.size();
/*     */           FireMode nextFireMode = fireModeSet.get(nextIndex);
/*     */           setFireMode(gunItem, nextFireMode);
/*     */           return nextFireMode;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doSpawnBulletEntity(Level world, LivingEntity shooter, ItemStack gunItem, float pitch, float yaw, float speed, float inaccuracy, ResourceLocation ammoId, ResourceLocation gunId, boolean tracer, GunData gunData, BulletData bulletData) {
/* 297 */     EntityKineticBullet bullet = new EntityKineticBullet(world, shooter, gunItem, ammoId, gunId, tracer, gunData, bulletData);
/* 298 */     bullet.m_37251_((Entity)bullet, pitch, yaw, 0.0F, speed, inaccuracy);
/* 299 */     world.m_7967_((Entity)bullet);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel(int exp) {
/* 304 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExp(int level) {
/* 309 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/* 314 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reduceAmmo(ItemStack currentGunItem) {
/* 323 */     Bolt boltType = TimelessAPI.getCommonGunIndex(getGunId(currentGunItem)).map(index -> index.getGunData().getBolt()).orElse(null);
/* 324 */     if (boltType == null) {
/*     */       return;
/*     */     }
/* 327 */     if (boltType == Bolt.MANUAL_ACTION) {
/* 328 */       setBulletInBarrel(currentGunItem, false);
/* 329 */     } else if (boltType == Bolt.CLOSED_BOLT) {
/* 330 */       if (getCurrentAmmoCount(currentGunItem) > 0) {
/* 331 */         reduceCurrentAmmoCount(currentGunItem);
/*     */       } else {
/* 333 */         setBulletInBarrel(currentGunItem, false);
/*     */       } 
/*     */     } else {
/* 336 */       reduceCurrentAmmoCount(currentGunItem);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private MeleeData getMeleeData(ResourceLocation attachmentId) {
/* 342 */     if (DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/* 343 */       return null;
/*     */     }
/* 345 */     return TimelessAPI.getCommonAttachmentIndex(attachmentId).map(index -> index.getData().getMeleeData()).orElse(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\ModernKineticGunItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */