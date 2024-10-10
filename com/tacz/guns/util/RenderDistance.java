/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.joml.Matrix4f;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public final class RenderDistance {
/* 11 */   private static long GUI_RENDER_TIMESTAMP = -1L;
/*    */   
/*    */   public static boolean inRenderHighPolyModelDistance(PoseStack poseStack) {
/* 14 */     if (isGuiRender()) {
/* 15 */       return true;
/*    */     }
/* 17 */     int distance = ((Integer)RenderConfig.GUN_LOD_RENDER_DISTANCE.get()).intValue();
/* 18 */     if (distance <= 0) {
/* 19 */       return false;
/*    */     }
/* 21 */     Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
/* 22 */     float viewDistance = matrix4f.m30() * matrix4f.m30() + matrix4f.m31() * matrix4f.m31() + matrix4f.m32() * matrix4f.m32();
/* 23 */     return (viewDistance < (distance * distance));
/*    */   }
/*    */   
/*    */   public static void markGuiRenderTimestamp() {
/* 27 */     GUI_RENDER_TIMESTAMP = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   private static boolean isGuiRender() {
/* 31 */     return (System.currentTimeMillis() - GUI_RENDER_TIMESTAMP < 100L);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\RenderDistance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */