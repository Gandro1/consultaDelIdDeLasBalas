/*    */ package com.tacz.guns.api.event.common;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import org.jetbrains.annotations.ApiStatus.Internal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class Pre
/*    */   extends EntityHurtByGunEvent
/*    */ {
/*    */   @Internal
/*    */   public Pre(Entity bullet, @Nullable Entity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float amount, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/* 53 */     super(bullet, hurtEntity, attacker, gunId, amount, sources, isHeadShot, headshotMultiplier, logicalSide);
/* 54 */     this.headshotMultiplier = headshotMultiplier;
/*    */   }
/*    */   
/*    */   public final void setHurtEntity(@Nullable Entity hurtEntity) {
/* 58 */     this.hurtEntity = hurtEntity;
/*    */   }
/*    */   
/*    */   public final void setAttacker(@Nullable LivingEntity attacker) {
/* 62 */     this.attacker = attacker;
/*    */   }
/*    */   
/*    */   public final void setGunId(ResourceLocation gunId) {
/* 66 */     this.gunId = gunId;
/*    */   }
/*    */   
/*    */   public final void setBaseAmount(float baseAmount) {
/* 70 */     this.baseAmount = baseAmount;
/*    */   }
/*    */   
/*    */   public final void setDamageSource(GunDamageSourcePart part, DamageSource value) {
/* 74 */     if (this.logicalSide.isClient()) {
/* 75 */       throw new UnsupportedOperationException("DamageSource about gun hit is not available on client side!");
/*    */     }
/* 77 */     if (part == GunDamageSourcePart.ARMOR_PIERCING) {
/* 78 */       this.apPartDamageSource = value;
/*    */     } else {
/* 80 */       this.nonApPartDamageSource = value;
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void setHeadshot(boolean headshot) {
/* 85 */     this.isHeadShot = headshot;
/*    */   }
/*    */   
/*    */   public final void setHeadshotMultiplier(float headshotMultiplier) {
/* 89 */     this.headshotMultiplier = headshotMultiplier;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\EntityHurtByGunEvent$Pre.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */