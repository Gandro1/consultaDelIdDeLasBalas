/*    */ package com.tacz.guns.client.renderer.item;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.renderer.block.GunSmithTableRenderer;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.model.geom.EntityModelSet;
/*    */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class GunSmithTableItemRenderer extends BlockEntityWithoutLevelRenderer {
/*    */   public GunSmithTableItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
/* 19 */     super(dispatcher, modelSet);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_108829_(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
/* 24 */     GunSmithTableRenderer.getModel().ifPresent(model -> {
/*    */           ResourceLocation texture = GunSmithTableRenderer.getTextureLocation();
/*    */           poseStack.m_85836_();
/*    */           poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*    */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*    */           RenderType renderType = RenderType.m_110473_(texture);
/*    */           model.render(poseStack, transformType, renderType, pPackedLight, pPackedOverlay);
/*    */           poseStack.m_85849_();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\item\GunSmithTableItemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */