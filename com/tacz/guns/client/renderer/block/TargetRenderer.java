/*    */ package com.tacz.guns.client.renderer.block;
/*    */ 
/*    */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.block.TargetBlock;
/*    */ import com.tacz.guns.block.entity.TargetBlockEntity;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.UUIDUtil;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ public class TargetRenderer
/*    */   implements BlockEntityRenderer<TargetBlockEntity>
/*    */ {
/*    */   private static final String UPPER_NAME = "target_upper";
/*    */   
/*    */   public static Optional<BedrockModel> getModel() {
/* 36 */     return InternalAssetLoader.getBedrockModel(InternalAssetLoader.TARGET_MODEL_LOCATION);
/*    */   }
/*    */   private static final String HEAD_NAME = "head";
/*    */   public TargetRenderer(BlockEntityRendererProvider.Context context) {}
/*    */   public void render(TargetBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
/* 41 */     getModel().ifPresent(model -> {
/*    */           BlockState blockState = blockEntity.m_58900_();
/*    */           Direction facing = (Direction)blockState.m_61143_((Property)TargetBlock.FACING);
/*    */           BedrockPart headModel = model.getNode("head");
/*    */           BedrockPart upperModel = model.getNode("target_upper");
/*    */           float deg = -Mth.m_14179_(partialTick, blockEntity.oRot, blockEntity.rot);
/*    */           upperModel.xRot = (float)Math.toRadians(deg);
/*    */           headModel.visible = false;
/*    */           poseStack.m_85836_();
/*    */           poseStack.m_85837_(0.5D, 0.225D, 0.5D);
/*    */           poseStack.m_252781_(Axis.f_252392_.m_252977_((facing.m_122416_() * 90)));
/*    */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*    */           poseStack.m_85837_(0.0D, -1.275D, 0.0125D);
/*    */           RenderType renderType = RenderType.m_110473_(InternalAssetLoader.TARGET_TEXTURE_LOCATION);
/*    */           model.render(poseStack, ItemDisplayContext.NONE, renderType, combinedLightIn, combinedOverlayIn);
/*    */           if (blockEntity.getOwner() != null) {
/*    */             ResourceLocation skin;
/*    */             poseStack.m_85837_(0.0D, 1.25D, 0.0D);
/*    */             poseStack.m_252781_(Axis.f_252529_.m_252977_(deg));
/*    */             Minecraft minecraft = Minecraft.m_91087_();
/*    */             Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.m_91109_().m_118815_(blockEntity.getOwner());
/*    */             if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/*    */               skin = minecraft.m_91109_().m_118825_(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*    */             } else {
/*    */               skin = DefaultPlayerSkin.m_118627_(UUIDUtil.m_235875_(blockEntity.getOwner()));
/*    */             } 
/*    */             headModel.visible = true;
/*    */             RenderType skullRenderType = RenderType.m_110470_(skin);
/*    */             headModel.render(poseStack, ItemDisplayContext.NONE, bufferIn.m_6299_(skullRenderType), combinedLightIn, OverlayTexture.f_118083_);
/*    */           } 
/*    */           poseStack.m_85849_();
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_142163_() {
/* 78 */     return ((Integer)RenderConfig.TARGET_RENDER_DISTANCE.get()).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderOffScreen(TargetBlockEntity blockEntity) {
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\block\TargetRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */