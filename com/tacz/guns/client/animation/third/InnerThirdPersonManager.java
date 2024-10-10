/*    */ package com.tacz.guns.client.animation.third;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.client.other.ThirdPersonManager;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class InnerThirdPersonManager
/*    */ {
/*    */   public static void setRotationAnglesHead(LivingEntity entityIn, ModelPart rightArm, ModelPart leftArm, ModelPart body, ModelPart head, float limbSwingAmount) {
/* 18 */     if (Minecraft.m_91087_().m_91104_()) {
/*    */       return;
/*    */     }
/* 21 */     if (entityIn instanceof IGunOperator) { IGunOperator operator = (IGunOperator)entityIn;
/* 22 */       ItemStack mainHandItem = entityIn.m_21205_();
/* 23 */       IGun iGun = IGun.getIGunOrNull(mainHandItem);
/* 24 */       if (iGun == null) {
/* 25 */         PlayerAnimatorCompat.stopAllAnimation(entityIn);
/*    */         
/*    */         return;
/*    */       } 
/* 29 */       if (entityIn.m_20089_() == Pose.SLEEPING || entityIn.m_6147_() || entityIn.m_6069_() || entityIn.m_20089_() == Pose.FALL_FLYING) {
/* 30 */         PlayerAnimatorCompat.stopAllAnimation(entityIn);
/*    */         
/*    */         return;
/*    */       } 
/* 34 */       TimelessAPI.getClientGunIndex(iGun.getGunId(mainHandItem)).ifPresent(index -> {
/*    */             if (PlayerAnimatorCompat.hasPlayerAnimator3rd(entityIn, index)) {
/*    */               PlayerAnimatorCompat.playAnimation(entityIn, index, limbSwingAmount);
/*    */             } else {
/*    */               playVanillaAnimation(entityIn, rightArm, leftArm, body, head, operator, index);
/*    */             } 
/*    */           }); }
/*    */   
/*    */   }
/*    */   
/*    */   private static void playVanillaAnimation(LivingEntity entityIn, ModelPart rightArm, ModelPart leftArm, ModelPart body, ModelPart head, IGunOperator operator, ClientGunIndex index) {
/* 45 */     String animation = index.getThirdPersonAnimation();
/* 46 */     float aimingProgress = operator.getSynAimingProgress();
/* 47 */     if (aimingProgress <= 0.0F) {
/* 48 */       ThirdPersonManager.getAnimation(animation).animateGunHold(entityIn, rightArm, leftArm, body, head);
/*    */     } else {
/* 50 */       ThirdPersonManager.getAnimation(animation).animateGunAim(entityIn, rightArm, leftArm, body, head, aimingProgress);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\animation\third\InnerThirdPersonManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */