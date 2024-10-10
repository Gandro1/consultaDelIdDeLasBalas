/*    */ package com.tacz.guns.compat.oculus.newly;
/*    */ 
/*    */ import com.tacz.guns.compat.oculus.newly.pbr.PBRRegister;
/*    */ import net.irisshaders.batchedentityrendering.impl.FullyBufferedMultiBufferSource;
/*    */ import net.irisshaders.iris.shadows.ShadowRenderingState;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ 
/*    */ public final class OculusCompatNewly {
/*    */   public static boolean isRenderShadow() {
/* 10 */     return ShadowRenderingState.areShadowsCurrentlyBeingRendered();
/*    */   }
/*    */   
/*    */   public static boolean endBatch(MultiBufferSource.BufferSource bufferSource) {
/* 14 */     if (bufferSource instanceof FullyBufferedMultiBufferSource) { FullyBufferedMultiBufferSource fullyBufferedMultiBufferSource = (FullyBufferedMultiBufferSource)bufferSource;
/* 15 */       fullyBufferedMultiBufferSource.m_109911_();
/* 16 */       return true; }
/*    */     
/* 18 */     return false;
/*    */   }
/*    */   
/*    */   public static void registerPBRLoader() {
/* 22 */     PBRRegister.registerPBRLoader();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\oculus\newly\OculusCompatNewly.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */