/*    */ package com.tacz.guns.api.event.common;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ 
/*    */ public class EntityKillByGunEvent
/*    */   extends Event
/*    */ {
/*    */   private final Entity bullet;
/*    */   @Nullable
/*    */   private final LivingEntity killedEntity;
/*    */   @Nullable
/*    */   private final LivingEntity attacker;
/*    */   private final ResourceLocation gunId;
/*    */   private final float baseDamage;
/*    */   private final DamageSource nonApPartDamageSource;
/*    */   private final DamageSource apPartDamageSource;
/*    */   private final boolean isHeadShot;
/*    */   private final float headshotMultiplier;
/*    */   private final LogicalSide logicalSide;
/*    */   
/*    */   public EntityKillByGunEvent(Entity bullet, @Nullable LivingEntity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float baseDamage, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/* 30 */     this.bullet = bullet;
/* 31 */     this.killedEntity = hurtEntity;
/* 32 */     this.attacker = attacker;
/* 33 */     this.gunId = gunId;
/* 34 */     this.baseDamage = baseDamage;
/* 35 */     this.nonApPartDamageSource = Optional.<Pair<DamageSource, DamageSource>>ofNullable(sources).map(Pair::getLeft).orElse(null);
/* 36 */     this.apPartDamageSource = Optional.<Pair<DamageSource, DamageSource>>ofNullable(sources).map(Pair::getRight).orElse(null);
/* 37 */     this.isHeadShot = isHeadShot;
/* 38 */     this.headshotMultiplier = headshotMultiplier;
/* 39 */     this.logicalSide = logicalSide;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity getBullet() {
/* 46 */     return this.bullet;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getKilledEntity() {
/* 51 */     return this.killedEntity;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getAttacker() {
/* 56 */     return this.attacker;
/*    */   }
/*    */   
/*    */   public ResourceLocation getGunId() {
/* 60 */     return this.gunId;
/*    */   }
/*    */   
/*    */   public float getBaseDamage() {
/* 64 */     return this.baseDamage;
/*    */   }
/*    */   
/*    */   public DamageSource getDamageSource(GunDamageSourcePart part) {
/* 68 */     if (this.logicalSide.isClient()) {
/* 69 */       throw new UnsupportedOperationException("DamageSource about gun hit is not available on client side!");
/*    */     }
/* 71 */     return (part == GunDamageSourcePart.ARMOR_PIERCING) ? this.apPartDamageSource : this.nonApPartDamageSource;
/*    */   }
/*    */   
/*    */   public boolean isHeadShot() {
/* 75 */     return this.isHeadShot;
/*    */   }
/*    */   
/*    */   public float getHeadshotMultiplier() {
/* 79 */     return this.headshotMultiplier;
/*    */   }
/*    */   
/*    */   public LogicalSide getLogicalSide() {
/* 83 */     return this.logicalSide;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\EntityKillByGunEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */