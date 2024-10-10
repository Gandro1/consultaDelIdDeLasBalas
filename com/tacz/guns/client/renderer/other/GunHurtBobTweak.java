/*    */ package com.tacz.guns.client.renderer.other;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.util.Mth;
/*    */ 
/*    */ public class GunHurtBobTweak {
/*  9 */   private static long hurtByGunTimeStamp = -1L;
/* 10 */   private static float lastTweakMultiplier = 0.05F;
/*    */ 
/*    */   
/*    */   public static boolean onHurtBobTweak(LocalPlayer player, PoseStack matrixStack, float partialTicks) {
/* 14 */     if (System.currentTimeMillis() - hurtByGunTimeStamp > 500L)
/*    */     {
/* 16 */       return false;
/*    */     }
/* 18 */     float zRot = player.f_20916_ - partialTicks;
/* 19 */     if (zRot < 0.0F) {
/* 20 */       return true;
/*    */     }
/* 22 */     zRot /= player.f_20917_;
/* 23 */     zRot = Mth.m_14031_(zRot * zRot * zRot * zRot * 3.1415927F);
/* 24 */     float yRot = player.m_264297_();
/*    */     
/* 26 */     yRot *= lastTweakMultiplier;
/* 27 */     zRot *= lastTweakMultiplier;
/*    */     
/* 29 */     matrixStack.m_252781_(Axis.f_252436_.m_252977_(-yRot));
/* 30 */     matrixStack.m_252781_(Axis.f_252529_.m_252977_(-zRot * 14.0F));
/* 31 */     matrixStack.m_252781_(Axis.f_252436_.m_252977_(yRot));
/* 32 */     return true;
/*    */   }
/*    */   
/*    */   public static void markTimestamp(float tweakMultiplier) {
/* 36 */     hurtByGunTimeStamp = System.currentTimeMillis();
/* 37 */     lastTweakMultiplier = tweakMultiplier;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\other\GunHurtBobTweak.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */