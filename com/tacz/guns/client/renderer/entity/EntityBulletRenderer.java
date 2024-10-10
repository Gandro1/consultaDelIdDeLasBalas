/*     */ package com.tacz.guns.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.client.model.BedrockAmmoModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*     */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.entity.EntityKineticBullet;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityBulletRenderer
/*     */   extends EntityRenderer<EntityKineticBullet>
/*     */ {
/*     */   public EntityBulletRenderer(EntityRendererProvider.Context pContext) {
/*  33 */     super(pContext);
/*     */   }
/*     */   
/*     */   public static Optional<BedrockModel> getModel() {
/*  37 */     return InternalAssetLoader.getBedrockModel(InternalAssetLoader.DEFAULT_BULLET_MODEL);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(EntityKineticBullet bullet, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
/*  42 */     ResourceLocation gunId = bullet.getGunId();
/*  43 */     Optional<ClientGunIndex> optionalClientGunIndex = TimelessAPI.getClientGunIndex(gunId);
/*  44 */     if (optionalClientGunIndex.isEmpty()) {
/*     */       return;
/*     */     }
/*  47 */     Objects.requireNonNull(optionalClientGunIndex.get()); float[] tracerColor = bullet.getTracerColorOverride().orElseGet((ClientGunIndex)optionalClientGunIndex.get()::getTracerColor);
/*  48 */     ResourceLocation ammoId = bullet.getAmmoId();
/*  49 */     TimelessAPI.getClientAmmoIndex(ammoId).ifPresent(ammoIndex -> {
/*     */           BedrockAmmoModel ammoEntityModel = ammoIndex.getAmmoEntityModel();
/*     */           ResourceLocation textureLocation = ammoIndex.getAmmoEntityTextureLocation();
/*     */           if (ammoEntityModel != null && textureLocation != null) {
/*     */             poseStack.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_(partialTicks, bullet.f_19859_, bullet.m_146908_()) - 180.0F));
/*     */             poseStack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_(partialTicks, bullet.f_19860_, bullet.m_146909_())));
/*     */             poseStack.m_85836_();
/*     */             poseStack.m_85837_(0.0D, 1.5D, 0.0D);
/*     */             poseStack.m_85841_(-1.0F, -1.0F, 1.0F);
/*     */             ammoEntityModel.render(poseStack, ItemDisplayContext.GROUND, RenderType.m_110470_(textureLocation), packedLight, OverlayTexture.f_118083_);
/*     */             poseStack.m_85849_();
/*     */           } 
/*     */           if (bullet.isTracerAmmo()) {
/*     */             float[] actualTracerColor = Objects.<float[]>requireNonNullElse(tracerColor, ammoIndex.getTracerColor());
/*     */             renderTracerAmmo(bullet, actualTracerColor, partialTicks, poseStack, packedLight);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTracerAmmo(EntityKineticBullet bullet, float[] tracerColor, float partialTicks, PoseStack poseStack, int packedLight) {
/*  71 */     getModel().ifPresent(model -> {
/*     */           Entity shooter = bullet.m_19749_();
/*     */           if (shooter == null) {
/*     */             return;
/*     */           }
/*     */           poseStack.m_85836_();
/*     */           float width = 0.005F;
/*     */           Vec3 bulletPosition = bullet.m_20318_(partialTicks);
/*     */           double trailLength = 0.85D * bullet.m_20184_().m_82553_();
/*     */           double disToEye = bulletPosition.m_82554_(shooter.m_20299_(partialTicks));
/*     */           trailLength = Math.min(trailLength, disToEye * 0.8D);
/*     */           if (this.f_114476_.f_114360_.m_92176_().m_90612_() && bullet.m_19749_() instanceof net.minecraft.client.player.LocalPlayer) {
/*     */             poseStack.m_85849_();
/*     */             return;
/*     */           } 
/*     */           width *= bullet.getTracerSizeOverride();
/*     */           width *= (float)Math.max(1.0D, disToEye / 3.5D);
/*     */           poseStack.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_(partialTicks, bullet.f_19859_, bullet.m_146908_()) - 180.0F));
/*     */           poseStack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_(partialTicks, bullet.f_19860_, bullet.m_146909_())));
/*     */           poseStack.m_85837_(0.0D, -0.2D, trailLength / 2.0D);
/*     */           poseStack.m_85841_(width, width, (float)trailLength);
/*     */           double bulletDistance = bulletPosition.m_82554_(shooter.m_146892_());
/*     */           if (bullet.f_19797_ >= 5 || bulletDistance > 2.0D) {
/*     */             RenderType type = RenderType.m_110436_(InternalAssetLoader.DEFAULT_BULLET_TEXTURE, 15.0F, 15.0F);
/*     */             model.render(poseStack, ItemDisplayContext.NONE, type, packedLight, OverlayTexture.f_118083_, tracerColor[0], tracerColor[1], tracerColor[2], 1.0F);
/*     */           } 
/*     */           poseStack.m_85849_();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBlockLightLevel(@NotNull EntityKineticBullet entityBullet, @NotNull BlockPos blockPos) {
/* 111 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender(EntityKineticBullet bullet, Frustum camera, double pCamX, double pCamY, double pCamZ) {
/* 116 */     AABB aabb = bullet.m_6921_().m_82400_(0.5D);
/* 117 */     if (aabb.m_82392_() || aabb.m_82309_() == 0.0D) {
/* 118 */       aabb = new AABB(bullet.m_20185_() - 2.0D, bullet.m_20186_() - 2.0D, bullet.m_20189_() - 2.0D, bullet.m_20185_() + 2.0D, bullet.m_20186_() + 2.0D, bullet.m_20189_() + 2.0D);
/*     */     }
/* 120 */     return camera.m_113029_(aabb);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(@NotNull EntityKineticBullet entity) {
/* 125 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\entity\EntityBulletRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */