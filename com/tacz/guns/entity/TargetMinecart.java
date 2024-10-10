/*     */ package com.tacz.guns.entity;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.tacz.guns.api.entity.ITargetEntity;
/*     */ import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import com.tacz.guns.config.common.OtherConfig;
/*     */ import com.tacz.guns.init.ModBlocks;
/*     */ import com.tacz.guns.init.ModItems;
/*     */ import com.tacz.guns.init.ModSounds;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunHurt;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.DamageTypeTags;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.MobCategory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.vehicle.AbstractMinecart;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.GameRules;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.SkullBlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TargetMinecart extends AbstractMinecart implements ITargetEntity {
/*  38 */   public static EntityType<TargetMinecart> TYPE = EntityType.Builder.m_20704_(TargetMinecart::new, MobCategory.MISC)
/*  39 */     .m_20699_(0.75F, 2.4F)
/*  40 */     .m_20702_(8)
/*  41 */     .m_20712_("target_minecart");
/*     */   @Nullable
/*  43 */   private GameProfile gameProfile = null;
/*     */   
/*     */   public TargetMinecart(EntityType<TargetMinecart> type, Level world) {
/*  46 */     super(type, world);
/*     */   }
/*     */   
/*     */   public TargetMinecart(Level level, double x, double y, double z) {
/*  50 */     super(TYPE, level, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onProjectileHit(Entity entity, EntityHitResult result, DamageSource source, float damage) {
/*  55 */     if (m_9236_().m_5776_() || m_213877_()) {
/*     */       return;
/*     */     }
/*  58 */     if (!source.m_269014_()) {
/*     */       return;
/*     */     }
/*  61 */     Entity sourceEntity = source.m_7639_();
/*  62 */     if (sourceEntity instanceof Player) { Player player = (Player)sourceEntity;
/*  63 */       m_38160_(-1);
/*  64 */       m_38154_(10);
/*  65 */       m_5834_();
/*  66 */       m_38109_(10.0F);
/*  67 */       double dis = m_20182_().m_82554_(sourceEntity.m_20182_());
/*  68 */       player.m_5661_((Component)Component.m_237110_("message.tacz.target_minecart.hit", new Object[] { String.format("%.1f", new Object[] { Float.valueOf(damage) }), String.format("%.2f", new Object[] { Double.valueOf(dis) }) }), true);
/*     */ 
/*     */       
/*  71 */       float volume = ((Integer)OtherConfig.TARGET_SOUND_DISTANCE.get()).intValue() / 16.0F;
/*  72 */       volume = Math.max(volume, 0.0F);
/*  73 */       m_9236_().m_6269_(null, (Entity)this, (SoundEvent)ModSounds.TARGET_HIT.get(), SoundSource.BLOCKS, volume, (m_9236_()).f_46441_.m_188501_() * 0.1F + 0.9F);
/*     */       
/*  75 */       if (entity instanceof EntityKineticBullet) { EntityKineticBullet projectile = (EntityKineticBullet)entity;
/*  76 */         boolean isHeadshot = false;
/*  77 */         float headshotMultiplier = 1.0F;
/*  78 */         MinecraftForge.EVENT_BUS.post((Event)new EntityHurtByGunEvent.Post((Entity)projectile, (Entity)this, (LivingEntity)player, projectile.getGunId(), damage, Pair.of(source, source), isHeadshot, headshotMultiplier, LogicalSide.SERVER));
/*  79 */         NetworkHandler.sendToDimension(new ServerMessageGunHurt(projectile.m_19879_(), m_19879_(), player.m_19879_(), projectile.getGunId(), damage, isHeadshot, headshotMultiplier), (Entity)this); }
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6673_(DamageSource source) {
/*  86 */     return (source.m_269533_(DamageTypeTags.f_268415_) || super.m_6673_(source));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeRidden() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6783_(double distance) {
/*  96 */     double size = m_20191_().m_82309_();
/*  97 */     if (Double.isNaN(size)) {
/*  98 */       size = 1.0D;
/*     */     }
/* 100 */     size *= ((Integer)RenderConfig.TARGET_RENDER_DISTANCE.get()).intValue() * m_20150_();
/* 101 */     return (distance < size * size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7617_(DamageSource source) {
/* 106 */     m_142687_(Entity.RemovalReason.KILLED);
/* 107 */     if (m_9236_().m_46469_().m_46207_(GameRules.f_46137_)) {
/* 108 */       ItemStack itemStack = new ItemStack((ItemLike)ModItems.TARGET_MINECART.get());
/* 109 */       if (m_8077_()) {
/* 110 */         itemStack.m_41714_(m_7770_());
/*     */       }
/* 112 */       m_19983_(itemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item m_213728_() {
/* 118 */     return (Item)ModItems.TARGET_MINECART.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack m_142340_() {
/* 123 */     ItemStack itemStack = new ItemStack((ItemLike)ModItems.TARGET_MINECART.get());
/* 124 */     if (m_8077_()) {
/* 125 */       itemStack.m_41714_(m_7770_());
/*     */     }
/* 127 */     return itemStack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GameProfile getGameProfile() {
/* 132 */     if (this.gameProfile == null && m_7770_() != null) {
/* 133 */       this.gameProfile = new GameProfile(null, m_7770_().getString());
/* 134 */       SkullBlockEntity.m_155738_(this.gameProfile, gameProfile -> this.gameProfile = gameProfile);
/*     */     } 
/* 136 */     return this.gameProfile;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockState m_6390_() {
/* 142 */     return ((Block)ModBlocks.TARGET.get()).m_49966_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AbstractMinecart.Type m_6064_() {
/* 148 */     return AbstractMinecart.Type.RIDEABLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxCartSpeedOnRail() {
/* 153 */     return 0.2F;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\TargetMinecart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */