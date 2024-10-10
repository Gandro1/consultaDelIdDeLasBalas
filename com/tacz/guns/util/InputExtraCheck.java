/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public final class InputExtraCheck {
/*    */   public static boolean isInGame() {
/* 10 */     Minecraft mc = Minecraft.m_91087_();
/*    */     
/* 12 */     if (mc.m_91265_() != null) {
/* 13 */       return false;
/*    */     }
/*    */     
/* 16 */     if (mc.f_91080_ != null) {
/* 17 */       return false;
/*    */     }
/*    */     
/* 20 */     if (!mc.f_91067_.m_91600_()) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     return mc.m_91302_();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\InputExtraCheck.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */