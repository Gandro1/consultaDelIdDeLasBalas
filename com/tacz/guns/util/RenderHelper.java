/*    */ package com.tacz.guns.util;
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.vertex.BufferBuilder;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.Tesselator;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*    */ import net.minecraft.client.renderer.entity.player.PlayerRenderer;
/*    */ import net.minecraft.world.entity.HumanoidArm;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import org.joml.Matrix4f;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.opengl.GL30;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public final class RenderHelper {
/*    */   public static void blit(PoseStack poseStack, float x, float y, float uOffset, float vOffset, float pWidth, float height, float textureWidth, float textureHeight) {
/* 23 */     blit(poseStack, x, y, pWidth, height, uOffset, vOffset, pWidth, height, textureWidth, textureHeight);
/*    */   }
/*    */   
/*    */   private static void blit(PoseStack poseStack, float x, float y, float pWidth, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
/* 27 */     innerBlit(poseStack, x, x + pWidth, y, y + height, 0.0F, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
/*    */   }
/*    */   
/*    */   private static void innerBlit(PoseStack poseStack, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
/* 31 */     innerBlit(poseStack.m_85850_().m_252922_(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
/*    */   }
/*    */   
/*    */   private static void innerBlit(Matrix4f matrix, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV) {
/* 35 */     RenderSystem.setShader(GameRenderer::m_172817_);
/* 36 */     BufferBuilder bufferbuilder = Tesselator.m_85913_().m_85915_();
/* 37 */     bufferbuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85817_);
/* 38 */     bufferbuilder.m_252986_(matrix, x1, y2, blitOffset).m_7421_(minU, maxV).m_5752_();
/* 39 */     bufferbuilder.m_252986_(matrix, x2, y2, blitOffset).m_7421_(maxU, maxV).m_5752_();
/* 40 */     bufferbuilder.m_252986_(matrix, x2, y1, blitOffset).m_7421_(maxU, minV).m_5752_();
/* 41 */     bufferbuilder.m_252986_(matrix, x1, y1, blitOffset).m_7421_(minU, minV).m_5752_();
/* 42 */     BufferUploader.m_231209_(bufferbuilder.m_231175_());
/*    */   }
/*    */   
/*    */   public static void enableItemEntityStencilTest() {
/* 46 */     RenderSystem.assertOnRenderThread();
/* 47 */     if (OptifineCompat.isOptifineInstalled()) {
/*    */       
/* 49 */       int depthTextureId = GL30.glGetFramebufferAttachmentParameteri(36160, 36096, 36049);
/* 50 */       int stencilTextureId = GL30.glGetFramebufferAttachmentParameteri(36160, 36128, 36048);
/* 51 */       if (depthTextureId != 0 && stencilTextureId == 0) {
/* 52 */         GL30.glBindTexture(3553, depthTextureId);
/* 53 */         int dataType = GL30.glGetTexLevelParameteri(3553, 0, 35862);
/* 54 */         if (dataType == 35863) {
/* 55 */           int width = GL30.glGetTexLevelParameteri(3553, 0, 4096);
/* 56 */           int height = GL30.glGetTexLevelParameteri(3553, 0, 4097);
/* 57 */           GlStateManager._texImage2D(3553, 0, 35056, width, height, 0, 34041, 34042, null);
/* 58 */           GlStateManager._glFramebufferTexture2D(36160, 33306, 3553, depthTextureId, 0);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 62 */       Minecraft.m_91087_().m_91385_().enableStencil();
/*    */     } 
/* 64 */     GL11.glEnable(2960);
/*    */   }
/*    */   
/*    */   public static void disableItemEntityStencilTest() {
/* 68 */     RenderSystem.assertOnRenderThread();
/* 69 */     GL11.glDisable(2960);
/*    */   }
/*    */   
/*    */   public static void renderFirstPersonArm(LocalPlayer player, HumanoidArm hand, PoseStack matrixStack, int combinedLight) {
/* 73 */     Minecraft mc = Minecraft.m_91087_();
/* 74 */     EntityRenderDispatcher renderManager = mc.m_91290_();
/* 75 */     PlayerRenderer renderer = (PlayerRenderer)renderManager.m_114382_((Entity)player);
/* 76 */     MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/* 77 */     int oldId = RenderSystem.getShaderTexture(0);
/* 78 */     RenderSystem.setShaderTexture(0, player.m_108560_());
/*    */     
/* 80 */     if (hand == HumanoidArm.RIGHT) {
/* 81 */       renderer.m_117770_(matrixStack, (MultiBufferSource)bufferSource, combinedLight, (AbstractClientPlayer)player);
/*    */     } else {
/* 83 */       renderer.m_117813_(matrixStack, (MultiBufferSource)bufferSource, combinedLight, (AbstractClientPlayer)player);
/*    */     } 
/* 85 */     RenderSystem.setShaderTexture(0, oldId);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\RenderHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */