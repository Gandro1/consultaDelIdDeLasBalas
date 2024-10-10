/*    */ package com.tacz.guns.client.model.functional;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.api.item.IAttachment;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*    */ import com.tacz.guns.client.model.BedrockGunModel;
/*    */ import com.tacz.guns.client.model.IFunctionalRenderer;
/*    */ import com.tacz.guns.client.renderer.item.AttachmentItemRenderer;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentSkinIndex;
/*    */ import com.tacz.guns.util.RenderDistance;
/*    */ import java.util.EnumMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix3fc;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ 
/*    */ public class AttachmentRender implements IFunctionalRenderer {
/*    */   private final BedrockGunModel bedrockGunModel;
/*    */   
/*    */   public AttachmentRender(BedrockGunModel bedrockGunModel, AttachmentType type) {
/* 32 */     this.bedrockGunModel = bedrockGunModel;
/* 33 */     this.type = type;
/*    */   }
/*    */   private final AttachmentType type;
/*    */   public static void renderAttachment(ItemStack attachmentItem, PoseStack poseStack, ItemDisplayContext transformType, int light, int overlay) {
/* 37 */     poseStack.m_85837_(0.0D, -1.5D, 0.0D);
/* 38 */     Item item = attachmentItem.m_41720_(); if (item instanceof IAttachment) { IAttachment iAttachment = (IAttachment)item;
/* 39 */       ResourceLocation attachmentId = iAttachment.getAttachmentId(attachmentItem);
/* 40 */       TimelessAPI.getClientAttachmentIndex(attachmentId).ifPresentOrElse(attachmentIndex -> {
/*    */             ResourceLocation skinId = iAttachment.getSkinId(attachmentItem);
/*    */             ClientAttachmentSkinIndex skinIndex = attachmentIndex.getSkinIndex(skinId);
/*    */             if (skinIndex != null) {
/*    */               BedrockAttachmentModel model = skinIndex.getModel();
/*    */               ResourceLocation texture = skinIndex.getTexture();
/*    */               RenderType renderType = RenderType.m_110452_(texture);
/*    */               model.render(poseStack, transformType, renderType, light, overlay);
/*    */             } else {
/*    */               BedrockAttachmentModel model = attachmentIndex.getAttachmentModel();
/*    */               ResourceLocation texture = attachmentIndex.getModelTexture();
/*    */               if (model != null && texture != null) {
/*    */                 Pair<BedrockAttachmentModel, ResourceLocation> lodModel = attachmentIndex.getLodModel();
/*    */                 if (lodModel != null && !RenderDistance.inRenderHighPolyModelDistance(poseStack) && !transformType.m_269069_()) {
/*    */                   model = (BedrockAttachmentModel)lodModel.getLeft();
/*    */                   texture = (ResourceLocation)lodModel.getRight();
/*    */                 } 
/*    */                 RenderType renderType = RenderType.m_110452_(texture);
/*    */                 model.render(poseStack, transformType, renderType, light, overlay);
/*    */               } 
/*    */             } 
/*    */           }() -> {
/*    */             MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/*    */             VertexConsumer buffer = bufferSource.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
/*    */             AttachmentItemRenderer.SLOT_ATTACHMENT_MODEL.m_7695_(poseStack, buffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */           }); }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack poseStack, VertexConsumer vertexBuffer, ItemDisplayContext transformType, int light, int overlay) {
/* 77 */     EnumMap<AttachmentType, ItemStack> currentAttachmentItem = this.bedrockGunModel.getCurrentAttachmentItem();
/* 78 */     ItemStack attachmentItem = currentAttachmentItem.get(this.type);
/* 79 */     if (attachmentItem != null && !attachmentItem.m_41619_()) {
/* 80 */       Matrix3f normal = new Matrix3f((Matrix3fc)poseStack.m_85850_().m_252943_());
/* 81 */       Matrix4f pose = new Matrix4f((Matrix4fc)poseStack.m_85850_().m_252922_());
/*    */       
/* 83 */       this.bedrockGunModel.delegateRender((poseStack1, vertexBuffer1, transformType1, light1, overlay1) -> {
/*    */             PoseStack poseStack2 = new PoseStack();
/*    */             poseStack2.m_85850_().m_252943_().mul((Matrix3fc)normal);
/*    */             poseStack2.m_85850_().m_252922_().mul((Matrix4fc)pose);
/*    */             renderAttachment(attachmentItem, poseStack2, transformType, light, overlay);
/*    */           });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\functional\AttachmentRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */