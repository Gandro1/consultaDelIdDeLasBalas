/*     */ package com.tacz.guns.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ITargetEntity;
/*     */ import com.tacz.guns.api.entity.KnockBackModifier;
/*     */ import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
/*     */ import com.tacz.guns.api.event.common.EntityKillByGunEvent;
/*     */ import com.tacz.guns.api.event.common.GunDamageSourcePart;
/*     */ import com.tacz.guns.api.event.server.AmmoHitBlockEvent;
/*     */ import com.tacz.guns.client.particle.AmmoParticleSpawner;
/*     */ import com.tacz.guns.config.common.AmmoConfig;
/*     */ import com.tacz.guns.config.sync.SyncConfig;
/*     */ import com.tacz.guns.init.ModDamageTypes;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunHurt;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunKill;
/*     */ import com.tacz.guns.particles.BulletHoleOption;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExplosionData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Ignite;
/*     */ import com.tacz.guns.util.EntityUtil;
/*     */ import com.tacz.guns.util.ExplodeUtil;
/*     */ import com.tacz.guns.util.TacHitResult;
/*     */ import com.tacz.guns.util.block.BlockRayTrace;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.MobCategory;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.BaseFireBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.entity.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.entity.PartEntity;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityKineticBullet extends Projectile implements IEntityAdditionalSpawnData {
/*  76 */   public static final EntityType<EntityKineticBullet> TYPE = EntityType.Builder.m_20704_(EntityKineticBullet::new, MobCategory.MISC).m_20698_().m_20716_().m_20719_().m_20699_(0.0625F, 0.0625F).m_20702_(5).m_20717_(5).setShouldReceiveVelocityUpdates(false).m_20712_("bullet");
/*  77 */   public static final TagKey<EntityType<?>> USE_MAGIC_DAMAGE_ON = TagKey.m_203882_(Registries.f_256939_, new ResourceLocation("tacz:use_magic_damage_on"));
/*  78 */   public static final TagKey<EntityType<?>> USE_VOID_DAMAGE_ON = TagKey.m_203882_(Registries.f_256939_, new ResourceLocation("tacz:use_void_damage_on"));
/*  79 */   public static final TagKey<EntityType<?>> PRETEND_MELEE_DAMAGE_ON = TagKey.m_203882_(Registries.f_256939_, new ResourceLocation("tacz:pretend_melee_damage_on"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TRACER_COLOR_OVERRIDER_KEY = "tacz:tracer_override";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TRACER_SIZE_OVERRIDER_KEY = "tacz:tracer_size";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private ResourceLocation ammoId = DefaultAssets.EMPTY_AMMO_ID;
/* 101 */   private int life = 200;
/* 102 */   private float speed = 1.0F;
/* 103 */   private float gravity = 0.0F;
/* 104 */   private float friction = 0.01F;
/* 105 */   private LinkedList<ExtraDamage.DistanceDamagePair> damageAmount = Lists.newLinkedList();
/* 106 */   private float distanceAmount = 0.0F;
/* 107 */   private float knockback = 0.0F;
/*     */   private boolean explosion = false;
/*     */   private boolean igniteEntity = false;
/*     */   private boolean igniteBlock = false;
/* 111 */   private int igniteEntityTime = 2;
/* 112 */   private float explosionDamage = 3.0F;
/* 113 */   private float explosionRadius = 3.0F;
/* 114 */   private int explosionDelayCount = Integer.MAX_VALUE;
/*     */   private boolean explosionKnockback = false;
/*     */   private boolean explosionDestroyBlock = false;
/* 117 */   private float damageModifier = 1.0F;
/*     */   
/* 119 */   private int pierce = 1;
/*     */   
/*     */   private Vec3 startPos;
/*     */   
/*     */   private boolean isTracerAmmo;
/*     */   
/*     */   private Vec3 originCameraPosition;
/*     */   
/*     */   private Vec3 originRenderOffset;
/*     */   private ResourceLocation gunId;
/*     */   private float armorIgnore;
/*     */   private float headShot;
/*     */   
/*     */   public EntityKineticBullet(EntityType<? extends Projectile> type, Level worldIn) {
/* 133 */     super(type, worldIn);
/*     */   }
/*     */   
/*     */   public EntityKineticBullet(EntityType<? extends Projectile> type, double x, double y, double z, Level worldIn) {
/* 137 */     this(type, worldIn);
/* 138 */     m_6034_(x, y, z);
/*     */   }
/*     */   
/*     */   public EntityKineticBullet(Level worldIn, LivingEntity throwerIn, ItemStack gunItem, ResourceLocation ammoId, ResourceLocation gunId, boolean isTracerAmmo, GunData gunData, BulletData bulletData) {
/* 142 */     this((EntityType)TYPE, worldIn, throwerIn, gunItem, ammoId, gunId, isTracerAmmo, gunData, bulletData);
/*     */   }
/*     */   
/*     */   protected EntityKineticBullet(EntityType<? extends Projectile> type, Level worldIn, LivingEntity throwerIn, ItemStack gunItem, ResourceLocation ammoId, ResourceLocation gunId, boolean isTracerAmmo, GunData gunData, BulletData bulletData) {
/* 146 */     this(type, throwerIn.m_20185_(), throwerIn.m_20188_() - 0.10000000149011612D, throwerIn.m_20189_(), worldIn);
/* 147 */     m_5602_((Entity)throwerIn);
/* 148 */     AttachmentCacheProperty cacheProperty = Objects.<AttachmentCacheProperty>requireNonNull(IGunOperator.fromLivingEntity(throwerIn).getCacheProperty());
/* 149 */     this.armorIgnore = Mth.m_14036_(((Float)cacheProperty.getCache("armor_ignore")).floatValue(), 0.0F, 1.0F);
/* 150 */     this.headShot = Math.max(((Float)cacheProperty.getCache("head_shot")).floatValue(), 0.0F);
/* 151 */     this.knockback = Math.max(((Float)cacheProperty.getCache("knockback")).floatValue(), 0.0F);
/* 152 */     this.ammoId = ammoId;
/* 153 */     this.life = Mth.m_14045_((int)(bulletData.getLifeSecond() * 20.0F), 1, 2147483647);
/*     */     
/* 155 */     this.speed = Mth.m_14036_(((Float)cacheProperty.getCache("ammo_speed")).floatValue() / 20.0F, 0.0F, 30.0F);
/* 156 */     this.gravity = Mth.m_14036_(bulletData.getGravity(), 0.0F, Float.MAX_VALUE);
/* 157 */     this.friction = Mth.m_14036_(bulletData.getFriction(), 0.0F, Float.MAX_VALUE);
/* 158 */     Ignite ignite = (Ignite)cacheProperty.getCache("ignite");
/* 159 */     this.igniteEntity = (bulletData.getIgnite().isIgniteEntity() || ignite.isIgniteEntity());
/* 160 */     this.igniteEntityTime = Math.max(bulletData.getIgniteEntityTime(), 0);
/* 161 */     this.igniteBlock = (bulletData.getIgnite().isIgniteBlock() || ignite.isIgniteBlock());
/* 162 */     this.damageAmount = (LinkedList<ExtraDamage.DistanceDamagePair>)cacheProperty.getCache("damage");
/* 163 */     this.distanceAmount = ((Float)cacheProperty.getCache("effective_range")).floatValue();
/*     */     
/* 165 */     if (bulletData.getBulletAmount() > 1) {
/* 166 */       this.damageModifier = 1.0F / bulletData.getBulletAmount();
/*     */     }
/* 168 */     this.pierce = Mth.m_14045_(((Integer)cacheProperty.getCache("pierce")).intValue(), 1, 2147483647);
/* 169 */     ExplosionData explosionData = (ExplosionData)cacheProperty.getCache("explosion");
/* 170 */     if (explosionData != null) {
/* 171 */       this.explosion = explosionData.isExplode();
/* 172 */       this.explosionDamage = (float)Mth.m_14008_(explosionData.getDamage() * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue(), 0.0D, 3.4028234663852886E38D);
/* 173 */       this.explosionRadius = Mth.m_14036_(explosionData.getRadius(), 0.0F, Float.MAX_VALUE);
/* 174 */       this.explosionKnockback = explosionData.isKnockback();
/*     */       
/* 176 */       int delayTickCount = explosionData.getDelay() * 20;
/* 177 */       if (delayTickCount < 0) {
/* 178 */         delayTickCount = Integer.MAX_VALUE;
/*     */       }
/* 180 */       this.explosionDestroyBlock = (explosionData.isDestroyBlock() && ((Boolean)AmmoConfig.EXPLOSIVE_AMMO_DESTROYS_BLOCK.get()).booleanValue());
/* 181 */       this.explosionDelayCount = Math.max(delayTickCount, 1);
/*     */     } 
/*     */     
/* 184 */     double posX = throwerIn.f_19790_ + (throwerIn.m_20185_() - throwerIn.f_19790_) / 2.0D;
/* 185 */     double posY = throwerIn.f_19791_ + (throwerIn.m_20186_() - throwerIn.f_19791_) / 2.0D + throwerIn.m_20192_();
/* 186 */     double posZ = throwerIn.f_19792_ + (throwerIn.m_20189_() - throwerIn.f_19792_) / 2.0D;
/* 187 */     m_6034_(posX, posY, posZ);
/* 188 */     this.startPos = m_20182_();
/* 189 */     this.isTracerAmmo = isTracerAmmo;
/* 190 */     this.gunId = gunId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 199 */     super.m_8119_();
/*     */     
/* 201 */     onBulletTick();
/*     */     
/* 203 */     if ((m_9236_()).f_46443_) {
/* 204 */       DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ());
/*     */     }
/*     */     
/* 207 */     Vec3 movement = m_20184_();
/* 208 */     double x = movement.f_82479_;
/* 209 */     double y = movement.f_82480_;
/* 210 */     double z = movement.f_82481_;
/* 211 */     double distance = movement.m_165924_();
/* 212 */     m_146922_((float)Math.toDegrees(Mth.m_14136_(x, z)));
/* 213 */     m_146926_((float)Math.toDegrees(Mth.m_14136_(y, distance)));
/*     */     
/* 215 */     if (this.f_19860_ == 0.0F && this.f_19859_ == 0.0F) {
/* 216 */       this.f_19859_ = m_146908_();
/* 217 */       this.f_19860_ = m_146909_();
/*     */     } 
/*     */     
/* 220 */     m_146926_(m_37273_(this.f_19860_, m_146909_()));
/* 221 */     m_146922_(m_37273_(this.f_19859_, m_146908_()));
/*     */     
/* 223 */     double nextPosX = m_20185_() + x;
/* 224 */     double nextPosY = m_20186_() + y;
/* 225 */     double nextPosZ = m_20189_() + z;
/* 226 */     m_6034_(nextPosX, nextPosY, nextPosZ);
/* 227 */     float friction = this.friction;
/* 228 */     float gravity = this.gravity;
/*     */     
/* 230 */     if (m_20069_()) {
/* 231 */       for (int i = 0; i < 4; i++) {
/* 232 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123795_, nextPosX - x * 0.25D, nextPosY - y * 0.25D, nextPosZ - z * 0.25D, x, y, z);
/*     */       }
/*     */       
/* 235 */       friction = 0.4F;
/* 236 */       gravity *= 0.6F;
/*     */     } 
/*     */     
/* 239 */     m_20256_(m_20184_().m_82490_((1.0F - friction)));
/* 240 */     m_20256_(m_20184_().m_82520_(0.0D, -gravity, 0.0D));
/*     */     
/* 242 */     if (this.f_19797_ >= this.life - 1) {
/* 243 */       m_146870_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onBulletTick() {
/* 250 */     if (!m_9236_().m_5776_()) {
/*     */       
/* 252 */       if (this.explosion) {
/* 253 */         if (this.explosionDelayCount > 0) {
/* 254 */           this.explosionDelayCount--;
/*     */         } else {
/* 256 */           ExplodeUtil.createExplosion(m_19749_(), (Entity)this, this.explosionDamage, this.explosionRadius, this.explosionKnockback, this.explosionDestroyBlock, m_20182_());
/*     */           
/* 258 */           m_146870_();
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/* 263 */       Vec3 startVec = m_20182_();
/*     */       
/* 265 */       Vec3 endVec = startVec.m_82549_(m_20184_());
/*     */       
/* 267 */       BlockHitResult blockHitResult1 = BlockRayTrace.rayTraceBlocks(m_9236_(), new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
/* 268 */       BlockHitResult resultB = blockHitResult1;
/* 269 */       if (resultB.m_6662_() != HitResult.Type.MISS)
/*     */       {
/* 271 */         endVec = resultB.m_82450_();
/*     */       }
/*     */       
/* 274 */       List<EntityResult> hitEntities = null;
/*     */       
/* 276 */       if (this.pierce <= 1 || this.explosion) {
/* 277 */         EntityResult entityResult = EntityUtil.findEntityOnPath(this, startVec, endVec);
/*     */         
/* 279 */         if (entityResult != null) {
/* 280 */           hitEntities = Collections.singletonList(entityResult);
/*     */         }
/*     */       } else {
/* 283 */         hitEntities = EntityUtil.findEntitiesOnPath(this, startVec, endVec);
/*     */       } 
/*     */       
/* 286 */       if (hitEntities != null && !hitEntities.isEmpty()) {
/* 287 */         EntityResult[] hitEntityResult = hitEntities.<EntityResult>toArray(new EntityResult[0]);
/*     */         
/* 289 */         for (int i = 0; (i < this.pierce || i < 1) && i < hitEntityResult.length - 1; i++) {
/* 290 */           int k = i;
/* 291 */           for (int j = i + 1; j < hitEntityResult.length; j++) {
/* 292 */             if ((hitEntityResult[j]).hitVec.m_82554_(startVec) < (hitEntityResult[k]).hitVec.m_82554_(startVec)) {
/* 293 */               k = j;
/*     */             }
/*     */           } 
/* 296 */           EntityResult t = hitEntityResult[i];
/* 297 */           hitEntityResult[i] = hitEntityResult[k];
/* 298 */           hitEntityResult[k] = t;
/*     */         } 
/* 300 */         for (EntityResult entityResult : hitEntityResult) {
/* 301 */           TacHitResult tacHitResult = new TacHitResult(entityResult);
/* 302 */           onHitEntity(tacHitResult, startVec, endVec);
/* 303 */           this.pierce--;
/* 304 */           if (this.pierce < 1 || this.explosion) {
/*     */             
/* 306 */             m_146870_();
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 311 */       onHitBlock(resultB, startVec, endVec);
/*     */     } 
/*     */   }
/*     */   public static final class MaybeMultipartEntity extends Record { private final Entity hitPart; private final Entity core;
/* 315 */     public MaybeMultipartEntity(Entity hitPart, Entity core) { this.hitPart = hitPart; this.core = core; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #315	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 315 */       //   0	7	0	this	Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity; } public Entity hitPart() { return this.hitPart; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #315	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #315	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/tacz/guns/entity/EntityKineticBullet$MaybeMultipartEntity;
/* 315 */       //   0	8	1	o	Ljava/lang/Object; } public Entity core() { return this.core; }
/*     */ 
/*     */ 
/*     */     
/*     */     public static MaybeMultipartEntity of(Entity hitPart) {
/* 320 */       PartEntity<?> part = (PartEntity)hitPart;
/*     */       
/* 322 */       Entity core = (hitPart instanceof PartEntity) ? part.getParent() : hitPart;
/* 323 */       return new MaybeMultipartEntity(hitPart, core);
/*     */     } }
/*     */ 
/*     */   
/*     */   protected void onHitEntity(TacHitResult result, Vec3 startVec, Vec3 endVec) {
/* 328 */     Entity entity1 = result.m_82443_(); if (entity1 instanceof ITargetEntity) { ITargetEntity targetEntity = (ITargetEntity)entity1;
/* 329 */       DamageSource source = m_269291_().m_269390_((Entity)this, m_19749_());
/* 330 */       targetEntity.onProjectileHit((Entity)this, (EntityHitResult)result, source, getDamage(result.m_82450_()));
/*     */       
/*     */       return; }
/*     */ 
/*     */     
/* 335 */     Entity entity = result.m_82443_();
/* 336 */     Entity owner = m_19749_();
/*     */     
/* 338 */     LivingEntity attacker = (owner instanceof LivingEntity) ? (LivingEntity)owner : null;
/* 339 */     Pair<DamageSource, DamageSource> sources = createDamageSources(MaybeMultipartEntity.of(entity));
/* 340 */     boolean headshot = result.isHeadshot();
/* 341 */     float damage = getDamage(result.m_82450_());
/* 342 */     float headShotMultiplier = Math.max(this.headShot, 0.0F);
/*     */     
/* 344 */     EntityHurtByGunEvent.Pre preEvent = new EntityHurtByGunEvent.Pre((Entity)this, entity, attacker, this.gunId, damage, sources, headshot, headShotMultiplier, LogicalSide.SERVER);
/* 345 */     boolean cancelled = MinecraftForge.EVENT_BUS.post((Event)preEvent);
/* 346 */     if (cancelled) {
/*     */       return;
/*     */     }
/*     */     
/* 350 */     entity = preEvent.getHurtEntity();
/*     */     
/* 352 */     MaybeMultipartEntity parts = MaybeMultipartEntity.of(entity);
/* 353 */     attacker = preEvent.getAttacker();
/* 354 */     ResourceLocation newGunId = preEvent.getGunId();
/* 355 */     damage = preEvent.getBaseAmount();
/* 356 */     sources = Pair.of(preEvent.getDamageSource(GunDamageSourcePart.NON_ARMOR_PIERCING), preEvent.getDamageSource(GunDamageSourcePart.ARMOR_PIERCING));
/* 357 */     headshot = preEvent.isHeadShot();
/* 358 */     headShotMultiplier = preEvent.getHeadshotMultiplier();
/* 359 */     if (entity == null) {
/*     */       return;
/*     */     }
/*     */     
/* 363 */     if (this.igniteEntity && ((Boolean)AmmoConfig.IGNITE_ENTITY.get()).booleanValue()) {
/* 364 */       entity.m_20254_(this.igniteEntityTime);
/*     */       
/* 366 */       Level level = m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/* 367 */         serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123756_, entity.m_20185_(), entity.m_20186_() + entity.m_20192_(), entity.m_20189_(), 1, 0.0D, 0.0D, 0.0D, 0.0D); }
/*     */     
/*     */     } 
/*     */     
/* 371 */     if (headshot)
/*     */     {
/* 373 */       damage *= headShotMultiplier;
/*     */     }
/*     */     
/* 376 */     Entity entity2 = parts.core(); if (entity2 instanceof LivingEntity) { LivingEntity livingCore = (LivingEntity)entity2;
/*     */       
/* 378 */       KnockBackModifier modifier = KnockBackModifier.fromLivingEntity(livingCore);
/* 379 */       modifier.setKnockBackStrength(this.knockback);
/*     */       
/* 381 */       tacAttackEntity(parts, damage, sources);
/*     */       
/* 383 */       modifier.resetKnockBackStrength(); }
/*     */     else
/*     */     
/* 386 */     { tacAttackEntity(parts, damage, sources); }
/*     */ 
/*     */     
/* 389 */     if (this.explosion) {
/*     */       
/* 391 */       (parts.core()).f_19802_ = 0;
/* 392 */       ExplodeUtil.createExplosion(m_19749_(), (Entity)this, this.explosionDamage, this.explosionRadius, this.explosionKnockback, this.explosionDestroyBlock, result.m_82450_());
/*     */     } 
/*     */     
/* 395 */     entity2 = parts.core(); if (entity2 instanceof LivingEntity) { LivingEntity livingCore = (LivingEntity)entity2;
/*     */       
/* 397 */       if (!(m_9236_()).f_46443_) {
/* 398 */         int attackerId = (attacker == null) ? 0 : attacker.m_19879_();
/*     */         
/* 400 */         if (livingCore.m_21224_()) {
/* 401 */           MinecraftForge.EVENT_BUS.post((Event)new EntityKillByGunEvent((Entity)this, livingCore, attacker, newGunId, damage, sources, headshot, headShotMultiplier, LogicalSide.SERVER));
/* 402 */           NetworkHandler.sendToDimension(new ServerMessageGunKill(m_19879_(), livingCore.m_19879_(), attackerId, newGunId, damage, headshot, headShotMultiplier), (Entity)livingCore);
/*     */         } else {
/* 404 */           MinecraftForge.EVENT_BUS.post((Event)new EntityHurtByGunEvent.Post((Entity)this, (Entity)livingCore, attacker, newGunId, damage, sources, headshot, headShotMultiplier, LogicalSide.SERVER));
/* 405 */           NetworkHandler.sendToDimension(new ServerMessageGunHurt(m_19879_(), livingCore.m_19879_(), attackerId, newGunId, damage, headshot, headShotMultiplier), (Entity)livingCore);
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   protected void onHitBlock(BlockHitResult result, Vec3 startVec, Vec3 endVec) {
/* 412 */     m_8060_(result);
/* 413 */     if (result.m_6662_() == HitResult.Type.MISS) {
/*     */       return;
/*     */     }
/* 416 */     Vec3 hitVec = result.m_82450_();
/* 417 */     BlockPos pos = result.m_82425_();
/*     */     
/* 419 */     if (MinecraftForge.EVENT_BUS.post((Event)new AmmoHitBlockEvent(m_9236_(), result, m_9236_().m_8055_(pos), this))) {
/*     */       return;
/*     */     }
/*     */     
/* 423 */     if (this.explosion) {
/* 424 */       ExplodeUtil.createExplosion(m_19749_(), (Entity)this, this.explosionDamage, this.explosionRadius, this.explosionKnockback, this.explosionDestroyBlock, hitVec);
/*     */       
/* 426 */       m_146870_();
/*     */       
/*     */       return;
/*     */     } 
/* 430 */     Level level = m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/* 431 */       BulletHoleOption bulletHoleOption = new BulletHoleOption(result.m_82434_(), result.m_82425_(), this.ammoId.toString(), this.gunId.toString());
/* 432 */       serverLevel.m_8767_((ParticleOptions)bulletHoleOption, hitVec.f_82479_, hitVec.f_82480_, hitVec.f_82481_, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/* 433 */       if (this.igniteBlock) {
/* 434 */         serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123756_, hitVec.f_82479_, hitVec.f_82480_, hitVec.f_82481_, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } }
/*     */     
/* 437 */     if (this.igniteBlock && ((Boolean)AmmoConfig.IGNITE_BLOCK.get()).booleanValue()) {
/* 438 */       BlockPos offsetPos = pos.m_121945_(result.m_82434_());
/* 439 */       if (BaseFireBlock.m_49255_(m_9236_(), offsetPos, result.m_82434_())) {
/* 440 */         BlockState fireState = BaseFireBlock.m_49245_((BlockGetter)m_9236_(), offsetPos);
/* 441 */         m_9236_().m_7731_(offsetPos, fireState, 11);
/* 442 */         ((ServerLevel)m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123756_, hitVec.f_82479_ - 1.0D + this.f_19796_.m_188500_() * 2.0D, hitVec.f_82480_, hitVec.f_82481_ - 1.0D + this.f_19796_.m_188500_() * 2.0D, 4, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/* 445 */     m_146870_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDamage(Vec3 hitVec) {
/* 451 */     double playerDistance = hitVec.m_82554_(this.startPos);
/* 452 */     for (ExtraDamage.DistanceDamagePair pair : this.damageAmount) {
/* 453 */       float effectiveDistance = (((ExtraDamage.DistanceDamagePair)this.damageAmount.get(0)).getDistance() == pair.getDistance()) ? this.distanceAmount : pair.getDistance();
/* 454 */       if (playerDistance < effectiveDistance) {
/* 455 */         float damage = pair.getDamage();
/* 456 */         return Math.max(damage * this.damageModifier, 0.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 460 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Pair<DamageSource, DamageSource> createDamageSources(MaybeMultipartEntity parts) {
/*     */     DamageSource source1, source2;
/* 468 */     EntityType<?> hitPartType = parts.hitPart().m_6095_();
/* 469 */     Entity directCause = hitPartType.m_204039_(PRETEND_MELEE_DAMAGE_ON) ? m_19749_() : (Entity)this;
/*     */     
/* 471 */     if (hitPartType.m_204039_(USE_MAGIC_DAMAGE_ON)) {
/* 472 */       source1 = source2 = m_269291_().m_269104_((Entity)this, m_19749_());
/* 473 */     } else if (hitPartType.m_204039_(USE_VOID_DAMAGE_ON)) {
/* 474 */       source1 = ModDamageTypes.Sources.bulletVoid(m_9236_().m_9598_(), directCause, m_19749_(), false);
/* 475 */       source2 = ModDamageTypes.Sources.bulletVoid(m_9236_().m_9598_(), directCause, m_19749_(), true);
/*     */     } else {
/* 477 */       source1 = ModDamageTypes.Sources.bullet(m_9236_().m_9598_(), directCause, m_19749_(), false);
/* 478 */       source2 = ModDamageTypes.Sources.bullet(m_9236_().m_9598_(), directCause, m_19749_(), true);
/*     */     } 
/* 480 */     return Pair.of(source1, source2);
/*     */   }
/*     */   
/*     */   private void tacAttackEntity(MaybeMultipartEntity parts, float damage, Pair<DamageSource, DamageSource> sources) {
/* 484 */     DamageSource source1 = (DamageSource)sources.getLeft();
/* 485 */     DamageSource source2 = (DamageSource)sources.getRight();
/*     */     
/* 487 */     float armorDamagePercent = Mth.m_14036_(this.armorIgnore, 0.0F, 1.0F);
/* 488 */     float normalDamagePercent = 1.0F - armorDamagePercent;
/*     */     
/* 490 */     (parts.core()).f_19802_ = 0;
/*     */     
/* 492 */     parts.hitPart().m_6469_(source1, damage * normalDamagePercent);
/*     */     
/* 494 */     (parts.core()).f_19802_ = 0;
/*     */     
/* 496 */     parts.hitPart().m_6469_(source2, damage * armorDamagePercent);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 501 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(FriendlyByteBuf buffer) {
/* 506 */     buffer.writeFloat(m_146909_());
/* 507 */     buffer.writeFloat(m_146908_());
/* 508 */     buffer.writeDouble((m_20184_()).f_82479_);
/* 509 */     buffer.writeDouble((m_20184_()).f_82480_);
/* 510 */     buffer.writeDouble((m_20184_()).f_82481_);
/* 511 */     Entity entity = m_19749_();
/* 512 */     buffer.writeInt((entity != null) ? entity.m_19879_() : 0);
/* 513 */     buffer.m_130085_(this.ammoId);
/* 514 */     buffer.writeFloat(this.gravity);
/* 515 */     buffer.writeBoolean(this.explosion);
/* 516 */     buffer.writeBoolean(this.igniteEntity);
/* 517 */     buffer.writeBoolean(this.igniteBlock);
/* 518 */     buffer.writeFloat(this.explosionRadius);
/* 519 */     buffer.writeFloat(this.explosionDamage);
/* 520 */     buffer.writeInt(this.life);
/* 521 */     buffer.writeFloat(this.speed);
/* 522 */     buffer.writeFloat(this.friction);
/* 523 */     buffer.writeInt(this.pierce);
/* 524 */     buffer.writeBoolean(this.isTracerAmmo);
/* 525 */     buffer.m_130085_(this.gunId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(FriendlyByteBuf additionalData) {
/* 530 */     m_146926_(additionalData.readFloat());
/* 531 */     m_146922_(additionalData.readFloat());
/* 532 */     m_20334_(additionalData.readDouble(), additionalData.readDouble(), additionalData.readDouble());
/* 533 */     Entity entity = m_9236_().m_6815_(additionalData.readInt());
/* 534 */     if (entity != null) {
/* 535 */       m_5602_(entity);
/*     */     }
/* 537 */     this.ammoId = additionalData.m_130281_();
/* 538 */     this.gravity = additionalData.readFloat();
/* 539 */     this.explosion = additionalData.readBoolean();
/* 540 */     this.igniteEntity = additionalData.readBoolean();
/* 541 */     this.igniteBlock = additionalData.readBoolean();
/* 542 */     this.explosionRadius = additionalData.readFloat();
/* 543 */     this.explosionDamage = additionalData.readFloat();
/* 544 */     this.life = additionalData.readInt();
/* 545 */     this.speed = additionalData.readFloat();
/* 546 */     this.friction = additionalData.readFloat();
/* 547 */     this.pierce = additionalData.readInt();
/* 548 */     this.isTracerAmmo = additionalData.readBoolean();
/* 549 */     this.gunId = additionalData.m_130281_();
/*     */   }
/*     */   
/*     */   public ResourceLocation getAmmoId() {
/* 553 */     return this.ammoId;
/*     */   }
/*     */   
/*     */   public ResourceLocation getGunId() {
/* 557 */     return this.gunId;
/*     */   }
/*     */   
/*     */   public boolean isTracerAmmo() {
/* 561 */     return this.isTracerAmmo;
/*     */   }
/*     */   
/*     */   public RandomSource getRandom() {
/* 565 */     return this.f_19796_;
/*     */   }
/*     */   
/*     */   public Vec3 getOriginCameraPosition() {
/* 569 */     return this.originCameraPosition;
/*     */   }
/*     */   
/*     */   public void setOriginCameraPosition(Vec3 originCameraPosition) {
/* 573 */     this.originCameraPosition = originCameraPosition;
/*     */   }
/*     */   
/*     */   public Vec3 getOriginRenderOffset() {
/* 577 */     return this.originRenderOffset;
/*     */   }
/*     */   
/*     */   public void setOriginRenderOffset(Vec3 originRenderOffset) {
/* 581 */     this.originRenderOffset = originRenderOffset;
/*     */   }
/*     */   public Optional<float[]> getTracerColorOverride() {
/*     */     float albedo, alpha;
/* 585 */     CompoundTag pd = getPersistentData();
/* 586 */     if (!pd.m_128425_("tacz:tracer_override", 11)) {
/* 587 */       return (Optional)Optional.empty();
/*     */     }
/* 589 */     int[] ints = pd.m_128465_("tacz:tracer_override");
/*     */ 
/*     */     
/* 592 */     switch (ints.length) {
/*     */       case 0:
/* 594 */         return (Optional)Optional.empty();
/*     */       case 1:
/* 596 */         albedo = ints[0] / 255.0F;
/* 597 */         return (Optional)Optional.of(new float[] { albedo, albedo, albedo, 1.0F });
/*     */       
/*     */       case 2:
/* 600 */         albedo = ints[0] / 255.0F;
/* 601 */         alpha = ints[1] / 255.0F;
/* 602 */         return (Optional)Optional.of(new float[] { albedo, albedo, albedo, alpha });
/*     */       
/*     */       case 3:
/* 605 */         r = ints[0] / 255.0F;
/* 606 */         g = ints[1] / 255.0F;
/* 607 */         b = ints[2] / 255.0F;
/* 608 */         return (Optional)Optional.of(new float[] { r, g, b, 1.0F });
/*     */     } 
/*     */     
/* 611 */     float r = ints[0] / 255.0F;
/* 612 */     float g = ints[1] / 255.0F;
/* 613 */     float b = ints[2] / 255.0F;
/* 614 */     float a = ints[3] / 255.0F;
/* 615 */     return (Optional)Optional.of(new float[] { r, g, b, a });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTracerSizeOverride() {
/* 622 */     CompoundTag pd = getPersistentData();
/* 623 */     return pd.m_128425_("tacz:tracer_size", 99) ? pd.m_128457_("tacz:tracer_size") : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_150171_(@Nullable Entity entity) {
/* 628 */     if (entity == null) {
/* 629 */       return false;
/*     */     }
/* 631 */     return super.m_150171_(entity);
/*     */   }
/*     */   
/*     */   public static class EntityResult {
/*     */     private final Entity entity;
/*     */     private final Vec3 hitVec;
/*     */     private final boolean headshot;
/*     */     
/*     */     public EntityResult(Entity entity, Vec3 hitVec, boolean headshot) {
/* 640 */       this.entity = entity;
/* 641 */       this.hitVec = hitVec;
/* 642 */       this.headshot = headshot;
/*     */     }
/*     */ 
/*     */     
/*     */     public Entity getEntity() {
/* 647 */       return this.entity;
/*     */     }
/*     */ 
/*     */     
/*     */     public Vec3 getHitPos() {
/* 652 */       return this.hitVec;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isHeadshot() {
/* 657 */       return this.headshot;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\EntityKineticBullet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */