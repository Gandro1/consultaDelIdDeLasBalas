/*     */ package com.tacz.guns.client.renderer.item;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*     */ import com.tacz.guns.client.model.SlotModel;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentSkinIndex;
/*     */ import com.tacz.guns.util.RenderDistance;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.model.geom.EntityModelSet;
/*     */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class AttachmentItemRenderer extends BlockEntityWithoutLevelRenderer {
/*  28 */   public static final SlotModel SLOT_ATTACHMENT_MODEL = new SlotModel();
/*     */   
/*     */   public AttachmentItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
/*  31 */     super(pBlockEntityRenderDispatcher, pEntityModelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_108829_(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
/*  36 */     Item item = stack.m_41720_(); if (item instanceof IAttachment) { IAttachment iAttachment = (IAttachment)item;
/*  37 */       ResourceLocation attachmentId = iAttachment.getAttachmentId(stack);
/*  38 */       poseStack.m_85836_();
/*  39 */       TimelessAPI.getClientAttachmentIndex(attachmentId).ifPresentOrElse(attachmentIndex -> {
/*     */             if (transformType == ItemDisplayContext.GUI) {
/*     */               poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */               
/*     */               poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */               
/*     */               VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(attachmentIndex.getSlotTexture()));
/*     */               
/*     */               SLOT_ATTACHMENT_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/*     */             poseStack.m_85837_(0.5D, 2.0D, 0.5D);
/*     */             poseStack.m_85841_(-1.0F, -1.0F, 1.0F);
/*     */             if (transformType == ItemDisplayContext.FIXED) {
/*     */               poseStack.m_252781_(Axis.f_252392_.m_252977_(90.0F));
/*     */             }
/*     */             ResourceLocation skinId = iAttachment.getSkinId(stack);
/*     */             ClientAttachmentSkinIndex skinIndex = attachmentIndex.getSkinIndex(skinId);
/*     */             if (skinIndex != null) {
/*     */               BedrockAttachmentModel model = skinIndex.getModel();
/*     */               ResourceLocation texture = skinIndex.getTexture();
/*     */               RenderType renderType = RenderType.m_110452_(texture);
/*     */               model.render(poseStack, transformType, renderType, pPackedLight, pPackedOverlay);
/*     */             } else {
/*     */               renderDefaultAttachment(transformType, poseStack, pBuffer, pPackedLight, pPackedOverlay, attachmentIndex);
/*     */             } 
/*     */           }() -> {
/*     */             poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */             poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */             VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
/*     */             SLOT_ATTACHMENT_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */           });
/*  73 */       poseStack.m_85849_(); }
/*     */   
/*     */   }
/*     */   
/*     */   private void renderDefaultAttachment(@NotNull ItemDisplayContext transformType, @NotNull PoseStack poseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, ClientAttachmentIndex attachmentIndex) {
/*  78 */     BedrockAttachmentModel model = attachmentIndex.getAttachmentModel();
/*  79 */     ResourceLocation texture = attachmentIndex.getModelTexture();
/*     */     
/*  81 */     if (model != null && texture != null) {
/*     */       
/*  83 */       Pair<BedrockAttachmentModel, ResourceLocation> lodModel = attachmentIndex.getLodModel();
/*     */       
/*  85 */       if (lodModel != null && !RenderDistance.inRenderHighPolyModelDistance(poseStack) && !transformType.m_269069_()) {
/*  86 */         model = (BedrockAttachmentModel)lodModel.getLeft();
/*  87 */         texture = (ResourceLocation)lodModel.getRight();
/*     */       } 
/*  89 */       RenderType renderType = RenderType.m_110452_(texture);
/*  90 */       model.render(poseStack, transformType, renderType, pPackedLight, pPackedOverlay);
/*     */     }
/*     */     else {
/*     */       
/*  94 */       poseStack.m_85837_(0.0D, 0.5D, 0.0D);
/*     */       
/*  96 */       if (transformType == ItemDisplayContext.FIXED) {
/*  97 */         poseStack.m_252781_(Axis.f_252436_.m_252977_(90.0F));
/*     */       }
/*  99 */       VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(attachmentIndex.getSlotTexture()));
/* 100 */       SLOT_ATTACHMENT_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\item\AttachmentItemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */