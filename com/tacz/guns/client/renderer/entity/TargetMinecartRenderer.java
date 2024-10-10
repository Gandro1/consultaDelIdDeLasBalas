/*    */ package com.tacz.guns.client.renderer.entity;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*    */ import com.tacz.guns.entity.TargetMinecart;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.geom.ModelLayers;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MinecartRenderer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.core.UUIDUtil;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.vehicle.AbstractMinecart;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class TargetMinecartRenderer extends MinecartRenderer<TargetMinecart> {
/*    */   private static final String HEAD_NAME = "head";
/*    */   
/*    */   public TargetMinecartRenderer(EntityRendererProvider.Context ctx) {
/* 34 */     super(ctx, ModelLayers.f_171253_);
/* 35 */     this.f_114477_ = 0.25F;
/*    */   }
/*    */   private static final String HEAD_2_NAME = "head2";
/*    */   public static Optional<BedrockModel> getModel() {
/* 39 */     return InternalAssetLoader.getBedrockModel(InternalAssetLoader.TARGET_MINECART_MODEL_LOCATION);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(TargetMinecart minecart) {
/* 44 */     return InternalAssetLoader.ENTITY_EMPTY_TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderMinecartContents(TargetMinecart targetMinecart, float pPartialTicks, BlockState pState, PoseStack stack, MultiBufferSource buffer, int pPackedLight) {
/* 49 */     getModel().ifPresent(model -> {
/*    */           BedrockPart headModel = model.getNode("head");
/*    */           BedrockPart head2Model = model.getNode("head2");
/*    */           headModel.visible = false;
/*    */           head2Model.visible = false;
/*    */           stack.m_85836_();
/*    */           stack.m_85837_(0.5D, 1.875D, 0.5D);
/*    */           stack.m_85841_(1.5F, 1.5F, 1.5F);
/*    */           stack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*    */           stack.m_252781_(Axis.f_252392_.m_252977_(90.0F));
/*    */           RenderType renderType = RenderType.m_110473_(InternalAssetLoader.TARGET_MINECART_TEXTURE_LOCATION);
/*    */           model.render(stack, ItemDisplayContext.NONE, renderType, pPackedLight, OverlayTexture.f_118083_);
/*    */           if (targetMinecart.getGameProfile() != null) {
/*    */             ResourceLocation skin;
/*    */             stack.m_85837_(0.0D, 1.0D, -0.28125D);
/*    */             Minecraft minecraft = Minecraft.m_91087_();
/*    */             GameProfile gameProfile = targetMinecart.getGameProfile();
/*    */             Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.m_91109_().m_118815_(gameProfile);
/*    */             if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/*    */               skin = minecraft.m_91109_().m_118825_(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*    */             } else {
/*    */               skin = DefaultPlayerSkin.m_118627_(UUIDUtil.m_235875_(gameProfile));
/*    */             } 
/*    */             headModel.visible = true;
/*    */             RenderType skullRenderType = RenderType.m_110470_(skin);
/*    */             headModel.render(stack, ItemDisplayContext.NONE, buffer.m_6299_(skullRenderType), pPackedLight, OverlayTexture.f_118083_);
/*    */             head2Model.visible = true;
/*    */             stack.m_85837_(0.0D, 0.0D, 0.01D);
/*    */             head2Model.render(stack, ItemDisplayContext.NONE, buffer.m_6299_(skullRenderType), pPackedLight, OverlayTexture.f_118083_);
/*    */           } 
/*    */           stack.m_85849_();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\entity\TargetMinecartRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */