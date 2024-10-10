/*     */ package com.tacz.guns.api.event.common;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraftforge.eventbus.api.Cancelable;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.ApiStatus.Internal;
/*     */ import org.jetbrains.annotations.ApiStatus.Obsolete;
/*     */ 
/*     */ public class EntityHurtByGunEvent
/*     */   extends Event
/*     */ {
/*     */   protected final Entity bullet;
/*     */   @Nullable
/*     */   protected Entity hurtEntity;
/*     */   @Nullable
/*     */   protected LivingEntity attacker;
/*     */   protected ResourceLocation gunId;
/*     */   protected float baseAmount;
/*     */   protected DamageSource nonApPartDamageSource;
/*     */   protected DamageSource apPartDamageSource;
/*     */   protected boolean isHeadShot;
/*     */   protected float headshotMultiplier;
/*     */   protected final LogicalSide logicalSide;
/*     */   
/*     */   @Internal
/*     */   protected EntityHurtByGunEvent(Entity bullet, @Nullable Entity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float baseAmount, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/*  34 */     this.bullet = bullet;
/*  35 */     this.hurtEntity = hurtEntity;
/*  36 */     this.attacker = attacker;
/*  37 */     this.gunId = gunId;
/*  38 */     this.baseAmount = baseAmount;
/*  39 */     this.nonApPartDamageSource = Optional.<Pair<DamageSource, DamageSource>>ofNullable(sources).map(Pair::getLeft).orElse(null);
/*  40 */     this.apPartDamageSource = Optional.<Pair<DamageSource, DamageSource>>ofNullable(sources).map(Pair::getRight).orElse(null);
/*  41 */     this.isHeadShot = isHeadShot;
/*  42 */     this.headshotMultiplier = headshotMultiplier;
/*  43 */     this.logicalSide = logicalSide;
/*     */   }
/*     */ 
/*     */   
/*     */   @Cancelable
/*     */   public static class Pre
/*     */     extends EntityHurtByGunEvent
/*     */   {
/*     */     @Internal
/*     */     public Pre(Entity bullet, @Nullable Entity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float amount, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/*  53 */       super(bullet, hurtEntity, attacker, gunId, amount, sources, isHeadShot, headshotMultiplier, logicalSide);
/*  54 */       this.headshotMultiplier = headshotMultiplier;
/*     */     }
/*     */     
/*     */     public final void setHurtEntity(@Nullable Entity hurtEntity) {
/*  58 */       this.hurtEntity = hurtEntity;
/*     */     }
/*     */     
/*     */     public final void setAttacker(@Nullable LivingEntity attacker) {
/*  62 */       this.attacker = attacker;
/*     */     }
/*     */     
/*     */     public final void setGunId(ResourceLocation gunId) {
/*  66 */       this.gunId = gunId;
/*     */     }
/*     */     
/*     */     public final void setBaseAmount(float baseAmount) {
/*  70 */       this.baseAmount = baseAmount;
/*     */     }
/*     */     
/*     */     public final void setDamageSource(GunDamageSourcePart part, DamageSource value) {
/*  74 */       if (this.logicalSide.isClient()) {
/*  75 */         throw new UnsupportedOperationException("DamageSource about gun hit is not available on client side!");
/*     */       }
/*  77 */       if (part == GunDamageSourcePart.ARMOR_PIERCING) {
/*  78 */         this.apPartDamageSource = value;
/*     */       } else {
/*  80 */         this.nonApPartDamageSource = value;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void setHeadshot(boolean headshot) {
/*  85 */       this.isHeadShot = headshot;
/*     */     }
/*     */     
/*     */     public final void setHeadshotMultiplier(float headshotMultiplier) {
/*  89 */       this.headshotMultiplier = headshotMultiplier;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Post
/*     */     extends EntityHurtByGunEvent
/*     */   {
/*     */     @Internal
/*     */     public Post(Entity bullet, @Nullable Entity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float amount, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/* 100 */       super(bullet, hurtEntity, attacker, gunId, amount, sources, isHeadShot, headshotMultiplier, logicalSide);
/*     */     }
/*     */   }
/*     */   
/*     */   public Entity getBullet() {
/* 105 */     return this.bullet;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity getHurtEntity() {
/* 110 */     return this.hurtEntity;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getAttacker() {
/* 115 */     return this.attacker;
/*     */   }
/*     */   
/*     */   public ResourceLocation getGunId() {
/* 119 */     return this.gunId;
/*     */   }
/*     */   
/*     */   @Obsolete
/*     */   public float getAmount() {
/* 124 */     return this.baseAmount * this.headshotMultiplier;
/*     */   }
/*     */   
/*     */   public float getBaseAmount() {
/* 128 */     return this.baseAmount;
/*     */   }
/*     */   
/*     */   public DamageSource getDamageSource(GunDamageSourcePart part) {
/* 132 */     if (this.logicalSide.isClient()) {
/* 133 */       throw new UnsupportedOperationException("DamageSource about gun hit is not available on client side!");
/*     */     }
/* 135 */     return (part == GunDamageSourcePart.ARMOR_PIERCING) ? this.apPartDamageSource : this.nonApPartDamageSource;
/*     */   }
/*     */   
/*     */   public float getHeadshotMultiplier() {
/* 139 */     return this.headshotMultiplier;
/*     */   }
/*     */   
/*     */   public boolean isHeadShot() {
/* 143 */     return this.isHeadShot;
/*     */   }
/*     */   
/*     */   public LogicalSide getLogicalSide() {
/* 147 */     return this.logicalSide;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\EntityHurtByGunEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */