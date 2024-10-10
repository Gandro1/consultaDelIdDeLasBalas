/*    */ package com.tacz.guns.client.model.functional;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.client.model.BedrockGunModel;
/*    */ import com.tacz.guns.client.model.IFunctionalRenderer;
/*    */ import com.tacz.guns.util.RenderHelper;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.world.entity.HumanoidArm;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix3fc;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ 
/*    */ public class RightHandRender implements IFunctionalRenderer {
/*    */   public RightHandRender(BedrockGunModel bedrockGunModel) {
/* 19 */     this.bedrockGunModel = bedrockGunModel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(PoseStack poseStack, VertexConsumer vertexBuffer, ItemDisplayContext transformType, int light, int overlay) {
/* 24 */     if (transformType.m_269069_()) {
/* 25 */       if (!this.bedrockGunModel.getRenderHand()) {
/*    */         return;
/*    */       }
/* 28 */       poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
/* 29 */       Matrix3f normal = new Matrix3f((Matrix3fc)poseStack.m_85850_().m_252943_());
/* 30 */       Matrix4f pose = new Matrix4f((Matrix4fc)poseStack.m_85850_().m_252922_());
/*    */       
/* 32 */       this.bedrockGunModel.delegateRender((poseStack1, vertexBuffer1, transformType1, light1, overlay1) -> {
/*    */             PoseStack poseStack2 = new PoseStack();
/*    */             poseStack2.m_85850_().m_252943_().mul((Matrix3fc)normal);
/*    */             poseStack2.m_85850_().m_252922_().mul((Matrix4fc)pose);
/*    */             RenderHelper.renderFirstPersonArm((Minecraft.m_91087_()).f_91074_, HumanoidArm.RIGHT, poseStack2, light1);
/*    */             Minecraft.m_91087_().m_91269_().m_110104_().m_109911_();
/*    */           });
/*    */     } 
/*    */   }
/*    */   
/*    */   private final BedrockGunModel bedrockGunModel;
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\functional\RightHandRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */