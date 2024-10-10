/*    */ package com.tacz.guns.client.renderer.block;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.block.GunSmithTableBlock;
/*    */ import com.tacz.guns.block.entity.GunSmithTableBlockEntity;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.BedPart;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ public class GunSmithTableRenderer implements BlockEntityRenderer<GunSmithTableBlockEntity> {
/*    */   public GunSmithTableRenderer(BlockEntityRendererProvider.Context context) {}
/*    */   
/*    */   public static Optional<BedrockModel> getModel() {
/* 26 */     return InternalAssetLoader.getBedrockModel(InternalAssetLoader.SMITH_TABLE_MODEL_LOCATION);
/*    */   }
/*    */   
/*    */   public static ResourceLocation getTextureLocation() {
/* 30 */     return InternalAssetLoader.SMITH_TABLE_TEXTURE_LOCATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(GunSmithTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
/* 35 */     getModel().ifPresent(model -> {
/*    */           BlockState blockState = blockEntity.m_58900_();
/*    */           if (((BedPart)blockState.m_61143_((Property)GunSmithTableBlock.PART)).equals(BedPart.HEAD)) {
/*    */             return;
/*    */           }
/*    */           Direction facing = (Direction)blockState.m_61143_((Property)GunSmithTableBlock.FACING);
/*    */           poseStack.m_85836_();
/*    */           poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*    */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*    */           poseStack.m_252781_(Axis.f_252392_.m_252977_((90 - facing.m_122416_() * 90)));
/*    */           RenderType renderType = RenderType.m_110473_(InternalAssetLoader.SMITH_TABLE_TEXTURE_LOCATION);
/*    */           model.render(poseStack, ItemDisplayContext.NONE, renderType, combinedLightIn, combinedOverlayIn);
/*    */           poseStack.m_85849_();
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderOffScreen(GunSmithTableBlockEntity blockEntity) {
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\block\GunSmithTableRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */