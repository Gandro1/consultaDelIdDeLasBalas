/*    */ package com.tacz.guns.client.model.functional;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.client.model.BedrockGunModel;
/*    */ import com.tacz.guns.client.model.IFunctionalRenderer;
/*    */ import com.tacz.guns.client.model.papi.PapiManager;
/*    */ import com.tacz.guns.client.resource.pojo.display.gun.Align;
/*    */ import com.tacz.guns.client.resource.pojo.display.gun.TextShow;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.renderer.LightTexture;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix3fc;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ 
/*    */ public class TextShowRender implements IFunctionalRenderer {
/*    */   private final BedrockGunModel bedrockGunModel;
/*    */   
/*    */   public TextShowRender(BedrockGunModel bedrockGunModel, TextShow textShow, ItemStack gunStack) {
/* 26 */     this.bedrockGunModel = bedrockGunModel;
/* 27 */     this.textShow = textShow;
/* 28 */     this.gunStack = gunStack;
/*    */   }
/*    */   private final TextShow textShow; private final ItemStack gunStack;
/*    */   
/*    */   public void render(PoseStack poseStack, VertexConsumer vertexBuffer, ItemDisplayContext transformType, int light, int overlay) {
/* 33 */     if (!transformType.m_269069_()) {
/*    */       return;
/*    */     }
/* 36 */     String text = PapiManager.getTextShow(this.textShow.getTextKey(), this.gunStack);
/* 37 */     if (StringUtils.isBlank(text)) {
/*    */       return;
/*    */     }
/* 40 */     poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
/* 41 */     Matrix3f normal = new Matrix3f((Matrix3fc)poseStack.m_85850_().m_252943_());
/* 42 */     Matrix4f pose = new Matrix4f((Matrix4fc)poseStack.m_85850_().m_252922_());
/*    */ 
/*    */     
/* 45 */     this.bedrockGunModel.delegateRender((poseStack1, vertexBuffer1, transformType1, light1, overlay1) -> {
/*    */           int xOffset;
/*    */           Font font = (Minecraft.m_91087_()).f_91062_;
/*    */           boolean shadow = this.textShow.isShadow();
/*    */           int color = this.textShow.getColorInt();
/*    */           float scale = this.textShow.getScale();
/*    */           int packLight = LightTexture.m_109885_(this.textShow.getTextLight(), this.textShow.getTextLight());
/*    */           int width = font.m_92895_(text);
/*    */           switch (this.textShow.getAlign()) {
/*    */             case CENTER:
/*    */               xOffset = width / 2;
/*    */               break;
/*    */             case RIGHT:
/*    */               xOffset = width;
/*    */               break;
/*    */             default:
/*    */               xOffset = 0;
/*    */               break;
/*    */           } 
/*    */           PoseStack poseStack2 = new PoseStack();
/*    */           poseStack2.m_85850_().m_252943_().mul((Matrix3fc)normal);
/*    */           poseStack2.m_85850_().m_252922_().mul((Matrix4fc)pose);
/*    */           poseStack2.m_85841_(0.006666667F * scale, -0.006666667F * scale, -0.006666667F);
/*    */           MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/*    */           Objects.requireNonNull(font);
/*    */           font.m_271703_(text, -xOffset, -9 / 2.0F, color, shadow, poseStack2.m_85850_().m_252922_(), (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, packLight);
/*    */           bufferSource.m_109911_();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\functional\TextShowRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */