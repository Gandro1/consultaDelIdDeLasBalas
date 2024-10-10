/*    */ package com.tacz.guns.entity.shooter;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*    */ import com.tacz.guns.resource.pojo.data.gun.MoveSpeed;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LivingEntitySpeedModifier
/*    */ {
/* 18 */   private static final UUID EXTRA_SPEED_MODIFIER_UUID = UUID.fromString("4D5696AE-A7C5-C59C-80E9-2A2DC8373C46");
/* 19 */   private static final UUID WEIGHT_SPEED_MODIFIER_UUID = UUID.fromString("2CB6F5AD-C6D2-9D29-4E84-0856ACD47CDB");
/*    */   private final LivingEntity shooter;
/*    */   
/*    */   public LivingEntitySpeedModifier(LivingEntity shooter, ShooterDataHolder dataHolder) {
/* 23 */     this.shooter = shooter;
/* 24 */     this.dataHolder = dataHolder;
/*    */   }
/*    */   private final ShooterDataHolder dataHolder;
/*    */   public void updateSpeedModifier() {
/* 28 */     if (!this.shooter.m_6084_())
/*    */       return; 
/* 30 */     ItemStack stack = this.shooter.m_21205_();
/* 31 */     AttributeInstance speedModifier = this.shooter.m_21204_().m_22146_(Attributes.f_22279_);
/* 32 */     if (speedModifier == null)
/*    */       return; 
/* 34 */     if (stack.m_41720_() instanceof com.tacz.guns.api.item.gun.AbstractGunItem) {
/*    */       
/* 36 */       AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity(this.shooter).getCacheProperty();
/* 37 */       if (cacheProperty != null) {
/* 38 */         double weightFactor = ((Double)SyncConfig.WEIGHT_SPEED_MULTIPLIER.get()).doubleValue();
/* 39 */         if (weightFactor > 0.0D) {
/* 40 */           float targetSpeed = ((Float)cacheProperty.getCache("weight_modifier")).floatValue();
/* 41 */           targetSpeed *= (float)-weightFactor;
/* 42 */           AttributeModifier currentModifier = speedModifier.m_22111_(WEIGHT_SPEED_MODIFIER_UUID);
/* 43 */           if (currentModifier == null || currentModifier.m_22218_() != targetSpeed) {
/* 44 */             speedModifier.m_22120_(WEIGHT_SPEED_MODIFIER_UUID);
/* 45 */             speedModifier.m_22118_(new AttributeModifier(WEIGHT_SPEED_MODIFIER_UUID, "Gun Speed Modifier", targetSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
/*    */           } 
/*    */         } 
/*    */ 
/*    */         
/* 50 */         MoveSpeed speed = (MoveSpeed)cacheProperty.getCache("movement_speed");
/* 51 */         if (speed != null) {
/* 52 */           double targetSpeed = getTargetSpeed(speed);
/* 53 */           AttributeModifier currentModifier = speedModifier.m_22111_(EXTRA_SPEED_MODIFIER_UUID);
/* 54 */           if (currentModifier == null || currentModifier.m_22218_() != targetSpeed) {
/* 55 */             speedModifier.m_22120_(EXTRA_SPEED_MODIFIER_UUID);
/* 56 */             speedModifier.m_22118_(new AttributeModifier(EXTRA_SPEED_MODIFIER_UUID, "Extra Gun Speed Modifier", targetSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } else {
/*    */       
/* 62 */       speedModifier.m_22120_(WEIGHT_SPEED_MODIFIER_UUID);
/* 63 */       speedModifier.m_22120_(EXTRA_SPEED_MODIFIER_UUID);
/*    */     } 
/*    */   }
/*    */   
/*    */   private double getTargetSpeed(MoveSpeed moveSpeed) {
/* 68 */     if (this.dataHolder.reloadStateType.isReloading()) {
/* 69 */       return moveSpeed.getReloadMultiplier();
/*    */     }
/* 71 */     if (this.dataHolder.isAiming) {
/* 72 */       return moveSpeed.getAimMultiplier();
/*    */     }
/* 74 */     return moveSpeed.getBaseMultiplier();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntitySpeedModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */