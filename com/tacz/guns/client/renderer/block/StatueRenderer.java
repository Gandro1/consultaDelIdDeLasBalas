/*    */ package com.tacz.guns.client.renderer.block;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.block.TargetBlock;
/*    */ import com.tacz.guns.block.entity.StatueBlockEntity;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.LightTexture;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.Position;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ public class StatueRenderer implements BlockEntityRenderer<StatueBlockEntity> {
/*    */   public static Optional<BedrockModel> getModel() {
/* 33 */     return InternalAssetLoader.getBedrockModel(InternalAssetLoader.STATUE_MODEL_LOCATION);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(StatueBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
/* 38 */     getModel().ifPresent(model -> {
/*    */           Level level = blockEntity.m_58904_();
/*    */           if (level == null) {
/*    */             return;
/*    */           }
/*    */           poseStack.m_85836_();
/*    */           BlockState blockState = blockEntity.m_58900_();
/*    */           Direction facing = (Direction)blockState.m_61143_((Property)TargetBlock.FACING);
/*    */           poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*    */           poseStack.m_252781_(Axis.f_252392_.m_252977_(((facing.m_122416_() + 2) % 4 * 90)));
/*    */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*    */           RenderType renderType = RenderType.m_110473_(getTextureLocation());
/*    */           model.render(poseStack, ItemDisplayContext.NONE, renderType, combinedLightIn, combinedOverlayIn);
/*    */           poseStack.m_85841_(0.5F, 0.5F, 0.5F);
/*    */           poseStack.m_85837_(0.0D, -0.875D, -1.2D);
/*    */           poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
/*    */           double offset = Math.sin(Util.m_137550_() / 500.0D) * 0.1D;
/*    */           poseStack.m_85837_(0.0D, offset, 0.0D);
/*    */           ItemStack stack = blockEntity.getGunItem();
/*    */           Minecraft.m_91087_().m_91291_().m_269128_(stack, ItemDisplayContext.FIXED, LightTexture.m_109885_(15, 15), OverlayTexture.f_118083_, poseStack, bufferIn, level, 0);
/*    */           poseStack.m_85849_();
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StatueRenderer(BlockEntityRendererProvider.Context context) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResourceLocation getTextureLocation() {
/* 82 */     return InternalAssetLoader.STATUE_TEXTURE_LOCATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142163_() {
/* 87 */     return ((Integer)RenderConfig.TARGET_RENDER_DISTANCE.get()).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderOffScreen(StatueBlockEntity blockEntity) {
/* 92 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(StatueBlockEntity pBlockEntity, Vec3 pCameraPos) {
/* 97 */     return Vec3.m_82512_((Vec3i)pBlockEntity.m_58899_().m_7494_()).m_82509_((Position)pCameraPos, m_142163_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\block\StatueRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */