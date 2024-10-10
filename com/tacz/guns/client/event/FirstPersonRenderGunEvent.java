/*     */ package com.tacz.guns.client.event;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.event.RenderItemInHandBobEvent;
/*     */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*     */ import com.tacz.guns.api.client.other.KeepingItemRenderer;
/*     */ import com.tacz.guns.api.event.common.GunFireEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.client.animation.screen.RefitTransform;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.model.functional.MuzzleFlashRender;
/*     */ import com.tacz.guns.client.model.functional.ShellRender;
/*     */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import com.tacz.guns.entity.EntityKineticBullet;
/*     */ import com.tacz.guns.util.math.Easing;
/*     */ import com.tacz.guns.util.math.MathUtil;
/*     */ import com.tacz.guns.util.math.PerlinNoise;
/*     */ import com.tacz.guns.util.math.SecondOrderDynamics;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.renderer.LightTexture;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Quaternionfc;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector3fc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*     */ public class FirstPersonRenderGunEvent
/*     */ {
/*  69 */   private static final SecondOrderDynamics AIMING_DYNAMICS = new SecondOrderDynamics(1.2F, 1.2F, 0.5F, 0.0F);
/*     */   
/*  71 */   private static final SecondOrderDynamics REFIT_OPENING_DYNAMICS = new SecondOrderDynamics(1.0F, 1.2F, 0.5F, 0.0F);
/*     */   
/*  73 */   private static final SecondOrderDynamics JUMPING_DYNAMICS = new SecondOrderDynamics(0.28F, 1.0F, 0.65F, 0.0F);
/*     */   
/*     */   private static final float JUMPING_Y_SWAY = -2.0F;
/*     */   private static final float JUMPING_SWAY_TIME = 0.3F;
/*     */   private static final float LANDING_SWAY_TIME = 0.15F;
/*  78 */   private static final PerlinNoise SHOOT_X_SWAY_NOISE = new PerlinNoise(-0.2F, 0.2F, 400L);
/*  79 */   private static final PerlinNoise SHOOT_Y_ROTATION_NOISE = new PerlinNoise(-0.0136F, 0.0136F, 100L);
/*     */   
/*     */   private static final float SHOOT_Y_SWAY = -0.1F;
/*     */   private static final float SHOOT_ANIMATION_TIME = 0.3F;
/*  83 */   private static float jumpingSwayProgress = 0.0F;
/*     */   private static boolean lastOnGround = false;
/*  85 */   private static long jumpingTimeStamp = -1L;
/*  86 */   private static long shootTimeStamp = -1L; @SubscribeEvent
/*     */   public static void onRenderHand(RenderHandEvent event) {
/*     */     IGun iGun;
/*     */     ItemDisplayContext transformType;
/*  90 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*  91 */     if (player == null) {
/*     */       return;
/*     */     }
/*  94 */     if (event.getHand() == InteractionHand.OFF_HAND) {
/*  95 */       ItemStack itemStack = KeepingItemRenderer.getRenderer().getCurrentItem();
/*  96 */       if (itemStack.m_41720_() instanceof IGun) {
/*  97 */         event.setCanceled(true);
/*     */       }
/*     */       return;
/*     */     } 
/* 101 */     ItemStack stack = event.getItemStack();
/* 102 */     Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (event.getHand() == InteractionHand.MAIN_HAND) {
/* 109 */       transformType = ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
/*     */     } else {
/* 111 */       transformType = ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
/*     */     } 
/*     */     
/* 114 */     ResourceLocation gunId = iGun.getGunId(stack);
/* 115 */     TimelessAPI.getClientGunIndex(gunId).ifPresentOrElse(gunIndex -> {
/*     */           BedrockGunModel gunModel = gunIndex.getGunModel();
/*     */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */           if (gunModel == null) {
/*     */             return;
/*     */           }
/*     */           if (animationStateMachine != null) {
/*     */             animationStateMachine.update(event.getPartialTick(), (Entity)player);
/*     */           }
/*     */           PoseStack poseStack = event.getPoseStack();
/*     */           poseStack.m_85836_();
/*     */           float xRotOffset = Mth.m_14179_(event.getPartialTick(), player.f_108588_, player.f_108586_);
/*     */           float yRotOffset = Mth.m_14179_(event.getPartialTick(), player.f_108587_, player.f_108585_);
/*     */           float xRot = player.m_5686_(event.getPartialTick()) - xRotOffset;
/*     */           float yRot = player.m_5675_(event.getPartialTick()) - yRotOffset;
/*     */           poseStack.m_252781_(Axis.f_252529_.m_252977_(xRot * -0.1F));
/*     */           poseStack.m_252781_(Axis.f_252436_.m_252977_(yRot * -0.1F));
/*     */           BedrockPart rootNode = gunModel.getRootNode();
/*     */           if (rootNode != null) {
/*     */             xRot = (float)Math.tanh((xRot / 25.0F)) * 25.0F;
/*     */             yRot = (float)Math.tanh((yRot / 25.0F)) * 25.0F;
/*     */             rootNode.offsetX += yRot * 0.1F / 16.0F / 3.0F;
/*     */             rootNode.offsetY += -xRot * 0.1F / 16.0F / 3.0F;
/*     */             rootNode.additionalQuaternion.mul((Quaternionfc)Axis.f_252529_.m_252977_(xRot * 0.05F));
/*     */             rootNode.additionalQuaternion.mul((Quaternionfc)Axis.f_252436_.m_252977_(yRot * 0.05F));
/*     */           } 
/*     */           poseStack.m_252880_(0.0F, 1.5F, 0.0F);
/*     */           poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
/*     */           applyFirstPersonGunTransform(player, stack, gunIndex, poseStack, gunModel, event.getPartialTick());
/*     */           MuzzleFlashRender.isSelf = true;
/*     */           ShellRender.isSelf = true;
/*     */           boolean renderHand = gunModel.getRenderHand();
/*     */           if (RefitTransform.getOpeningProgress() != 0.0F) {
/*     */             gunModel.setRenderHand(false);
/*     */           }
/*     */           RenderType renderType = RenderType.m_110452_(gunIndex.getModelTexture());
/*     */           gunModel.render(poseStack, stack, transformType, renderType, event.getPackedLight(), OverlayTexture.f_118083_);
/*     */           renderBulletTracer(player, poseStack, gunModel, event.getPartialTick());
/*     */           gunModel.setRenderHand(renderHand);
/*     */           poseStack.m_85849_();
/*     */           gunModel.cleanAnimationTransform();
/*     */           MuzzleFlashRender.isSelf = false;
/*     */           ShellRender.isSelf = false;
/*     */           event.setCanceled(true);
/*     */         }() -> renderBulletTracer(player, event.getPoseStack(), null, event.getPartialTick()));
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
/*     */   private static void renderBulletTracer(LocalPlayer player, PoseStack poseStack, BedrockGunModel gunModel, float partialTicks) {
/* 182 */     if (!((Boolean)RenderConfig.FIRST_PERSON_BULLET_TRACER_ENABLE.get()).booleanValue()) {
/*     */       return;
/*     */     }
/* 185 */     Optional<BedrockModel> modelOptional = InternalAssetLoader.getBedrockModel(InternalAssetLoader.DEFAULT_BULLET_MODEL);
/* 186 */     if (modelOptional.isEmpty()) {
/*     */       return;
/*     */     }
/* 189 */     BedrockModel model = modelOptional.get();
/* 190 */     Level level = player.m_9236_();
/* 191 */     AABB renderArea = player.m_20191_().m_82377_(256.0D, 256.0D, 256.0D);
/* 192 */     for (Entity entity : level.m_6249_((Entity)player, renderArea, FirstPersonRenderGunEvent::bulletFromPlayer)) {
/* 193 */       EntityKineticBullet entityBullet = (EntityKineticBullet)entity;
/* 194 */       if (!entityBullet.isTracerAmmo()) {
/*     */         continue;
/*     */       }
/* 197 */       Vec3 deltaMovement = entityBullet.m_20184_().m_82542_(partialTicks, partialTicks, partialTicks);
/* 198 */       Vec3 entityPosition = entityBullet.m_20318_(0.0F).m_82549_(deltaMovement);
/* 199 */       Camera camera = (Minecraft.m_91087_()).f_91063_.m_109153_();
/* 200 */       Vec3 cameraPosition = camera.m_90583_();
/* 201 */       Vec3 originCameraPosition = entityBullet.getOriginCameraPosition();
/* 202 */       if (originCameraPosition == null) {
/* 203 */         if (gunModel == null) {
/*     */           continue;
/*     */         }
/* 206 */         if (gunModel.getMuzzleFlashPosPath() != null) {
/* 207 */           poseStack.m_85836_();
/* 208 */           for (BedrockPart bedrockPart : gunModel.getMuzzleFlashPosPath()) {
/* 209 */             bedrockPart.translateAndRotateAndScale(poseStack);
/*     */           }
/* 211 */           Matrix4f pose = poseStack.m_85850_().m_252922_();
/* 212 */           originCameraPosition = new Vec3(cameraPosition.f_82479_, cameraPosition.f_82480_, cameraPosition.f_82481_);
/* 213 */           entityBullet.setOriginCameraPosition(originCameraPosition);
/* 214 */           entityBullet.setOriginRenderOffset(new Vec3(pose.m30(), pose.m31(), pose.m32()));
/* 215 */           poseStack.m_85849_();
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */       } 
/* 220 */       Vec3 originRenderOffset = entityBullet.getOriginRenderOffset();
/* 221 */       Vec3 alphaCameraTranslation = originCameraPosition.m_82546_(cameraPosition);
/* 222 */       double distance = entityPosition.m_82554_(originCameraPosition);
/* 223 */       Vec3 bulletDirection = entityPosition.m_82546_(originCameraPosition);
/* 224 */       double yRot = MathUtil.getTwoVecAngle(new Vec3(0.0D, 0.0D, -1.0D), new Vec3(bulletDirection.f_82479_, 0.0D, bulletDirection.f_82481_));
/* 225 */       double xRot = MathUtil.getTwoVecAngle(new Vec3(bulletDirection.f_82479_, 0.0D, bulletDirection.f_82481_), bulletDirection);
/* 226 */       if (yRot == -1.0D) {
/* 227 */         yRot = Math.toRadians((camera.m_90590_() + 180.0F));
/*     */       }
/* 229 */       if (xRot == -1.0D) {
/* 230 */         xRot = Math.toRadians(camera.m_90589_());
/*     */       }
/* 232 */       xRot *= (bulletDirection.f_82480_ > 0.0D) ? -1.0D : 1.0D;
/* 233 */       yRot *= (bulletDirection.f_82479_ > 0.0D) ? 1.0D : -1.0D;
/* 234 */       PoseStack poseStack1 = new PoseStack();
/*     */       
/* 236 */       poseStack1.m_252781_(Axis.f_252529_.m_252977_(camera.m_90589_()));
/* 237 */       poseStack1.m_252781_(Axis.f_252436_.m_252977_(camera.m_90590_() + 180.0F));
/* 238 */       poseStack1.m_85837_(alphaCameraTranslation.f_82479_, alphaCameraTranslation.f_82480_, alphaCameraTranslation.f_82481_);
/*     */       
/* 240 */       poseStack1.m_252781_(Axis.f_252392_.m_252961_((float)yRot));
/* 241 */       poseStack1.m_252781_(Axis.f_252495_.m_252961_((float)xRot));
/* 242 */       poseStack1.m_85837_(originRenderOffset.f_82479_, originRenderOffset.f_82480_, originRenderOffset.f_82481_ - distance);
/* 243 */       float trailLength = 0.5F * (float)entityBullet.m_20184_().m_82553_();
/* 244 */       float trailWidth = 0.03F * entityBullet.getTracerSizeOverride();
/* 245 */       poseStack1.m_252880_(0.0F, 0.0F, -trailLength / 2.0F);
/* 246 */       poseStack1.m_85841_(trailWidth, trailWidth, trailLength);
/*     */       
/* 248 */       ResourceLocation gunId = entityBullet.getGunId();
/* 249 */       TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */             Objects.requireNonNull(gunIndex);
/*     */             float[] entityTracerColor = entityBullet.getTracerColorOverride().orElseGet(gunIndex::getTracerColor);
/*     */             if (entityTracerColor == null) {
/*     */               ResourceLocation ammoId = entityBullet.getAmmoId();
/*     */               TimelessAPI.getClientAmmoIndex(ammoId).ifPresent(());
/*     */             } else {
/*     */               RenderType type = RenderType.m_110436_(InternalAssetLoader.DEFAULT_BULLET_TEXTURE, 15.0F, 15.0F);
/*     */               model.render(poseStack1, ItemDisplayContext.NONE, type, LightTexture.m_109885_(15, 15), OverlayTexture.f_118083_, entityTracerColor[0], entityTracerColor[1], entityTracerColor[2], 1.0F);
/*     */             } 
/*     */           });
/*     */     } 
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
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void cancelItemInHandViewBobbing(RenderItemInHandBobEvent.BobView event) {
/* 275 */     Minecraft mc = Minecraft.m_91087_();
/* 276 */     if (mc.f_91074_ == null) {
/*     */       return;
/*     */     }
/* 279 */     ItemStack itemStack = KeepingItemRenderer.getRenderer().getCurrentItem();
/* 280 */     if (IGun.getIGunOrNull(itemStack) != null) {
/* 281 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onGunFire(GunFireEvent event) {
/* 287 */     if (event.getLogicalSide().isClient()) {
/* 288 */       LivingEntity shooter = event.getShooter();
/* 289 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 290 */       if (!shooter.equals(player)) {
/*     */         return;
/*     */       }
/* 293 */       ItemStack mainhandItem = player.m_21205_();
/* 294 */       IGun iGun = IGun.getIGunOrNull(mainhandItem);
/* 295 */       if (iGun == null) {
/*     */         return;
/*     */       }
/* 298 */       TimelessAPI.getClientGunIndex(iGun.getGunId(mainhandItem)).ifPresent(gunIndex -> {
/*     */             shootTimeStamp = System.currentTimeMillis();
/*     */             MuzzleFlashRender.onShoot();
/*     */             if (gunIndex.getShellEjection() != null) {
/*     */               ShellRender.addShell(gunIndex.getShellEjection().getRandomVelocity());
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean bulletFromPlayer(Entity entity) {
/* 312 */     if (entity instanceof EntityKineticBullet) { EntityKineticBullet entityBullet = (EntityKineticBullet)entity;
/* 313 */       return entityBullet.m_19749_() instanceof LocalPlayer; }
/*     */     
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void applyFirstPersonGunTransform(LocalPlayer player, ItemStack gunItemStack, ClientGunIndex gunIndex, PoseStack poseStack, BedrockGunModel model, float partialTicks) {
/* 320 */     float refitScreenOpeningProgress = REFIT_OPENING_DYNAMICS.update(RefitTransform.getOpeningProgress());
/*     */     
/* 322 */     float aimingProgress = AIMING_DYNAMICS.update(IClientPlayerGunOperator.fromLocalPlayer(player).getClientAimingProgress(partialTicks));
/*     */     
/* 324 */     applyGunMovements(model, aimingProgress, partialTicks);
/*     */     
/* 326 */     applyFirstPersonPositioningTransform(poseStack, model, gunItemStack, aimingProgress, refitScreenOpeningProgress);
/*     */     
/* 328 */     applyAnimationConstraintTransform(poseStack, model, aimingProgress * (1.0F - refitScreenOpeningProgress));
/*     */   }
/*     */   
/*     */   private static void applyGunMovements(BedrockGunModel model, float aimingProgress, float partialTicks) {
/* 332 */     applyShootSwayAndRotation(model, aimingProgress);
/* 333 */     applyJumpingSway(model, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void applyFirstPersonPositioningTransform(PoseStack poseStack, BedrockGunModel model, ItemStack stack, float aimingProgress, float refitScreenOpeningProgress) {
/* 340 */     IGun iGun = IGun.getIGunOrNull(stack);
/* 341 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 344 */     Matrix4f transformMatrix = new Matrix4f();
/* 345 */     transformMatrix.identity();
/*     */     
/* 347 */     List<BedrockPart> idleNodePath = model.getIdleSightPath();
/* 348 */     List<BedrockPart> aimingNodePath = null;
/* 349 */     ResourceLocation scopeId = iGun.getAttachmentId(stack, AttachmentType.SCOPE);
/* 350 */     if (scopeId.equals(DefaultAssets.EMPTY_ATTACHMENT_ID)) {
/* 351 */       scopeId = iGun.getBuiltInAttachmentId(stack, AttachmentType.SCOPE);
/*     */     }
/* 353 */     if (DefaultAssets.isEmptyAttachmentId(scopeId)) {
/*     */       
/* 355 */       aimingNodePath = model.getIronSightPath();
/*     */     } else {
/*     */       
/* 358 */       List<BedrockPart> scopeNodePath = model.getScopePosPath();
/* 359 */       if (scopeNodePath != null) {
/* 360 */         aimingNodePath = new ArrayList<>(scopeNodePath);
/* 361 */         Optional<ClientAttachmentIndex> indexOptional = TimelessAPI.getClientAttachmentIndex(scopeId);
/* 362 */         if (indexOptional.isPresent()) {
/* 363 */           BedrockAttachmentModel attachmentModel = ((ClientAttachmentIndex)indexOptional.get()).getAttachmentModel();
/* 364 */           if (attachmentModel != null && attachmentModel.getScopeViewPath() != null) {
/* 365 */             aimingNodePath.addAll(attachmentModel.getScopeViewPath());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 370 */     MathUtil.applyMatrixLerp(transformMatrix, getPositioningNodeInverse(idleNodePath), transformMatrix, 1.0F - refitScreenOpeningProgress);
/* 371 */     MathUtil.applyMatrixLerp(transformMatrix, getPositioningNodeInverse(aimingNodePath), transformMatrix, (1.0F - refitScreenOpeningProgress) * aimingProgress);
/*     */     
/* 373 */     float refitTransformProgress = (float)Easing.easeOutCubic(RefitTransform.getTransformProgress());
/* 374 */     AttachmentType oldType = RefitTransform.getOldTransformType();
/* 375 */     AttachmentType currentType = RefitTransform.getCurrentTransformType();
/* 376 */     List<BedrockPart> fromNode = model.getRefitAttachmentViewPath(oldType);
/* 377 */     List<BedrockPart> toNode = model.getRefitAttachmentViewPath(currentType);
/* 378 */     MathUtil.applyMatrixLerp(transformMatrix, getPositioningNodeInverse(fromNode), transformMatrix, refitScreenOpeningProgress);
/* 379 */     MathUtil.applyMatrixLerp(transformMatrix, getPositioningNodeInverse(toNode), transformMatrix, refitScreenOpeningProgress * refitTransformProgress);
/*     */     
/* 381 */     poseStack.m_252880_(0.0F, 1.5F, 0.0F);
/* 382 */     poseStack.m_252931_(transformMatrix);
/* 383 */     poseStack.m_252880_(0.0F, -1.5F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   private static Matrix4f getPositioningNodeInverse(List<BedrockPart> nodePath) {
/* 391 */     Matrix4f matrix4f = new Matrix4f();
/* 392 */     matrix4f.identity();
/* 393 */     if (nodePath != null) {
/* 394 */       for (int i = nodePath.size() - 1; i >= 0; i--) {
/* 395 */         BedrockPart part = nodePath.get(i);
/*     */         
/* 397 */         matrix4f.rotate((Quaternionfc)Axis.f_252495_.m_252961_(part.xRot));
/* 398 */         matrix4f.rotate((Quaternionfc)Axis.f_252392_.m_252961_(part.yRot));
/* 399 */         matrix4f.rotate((Quaternionfc)Axis.f_252393_.m_252961_(part.zRot));
/*     */         
/* 401 */         if (part.getParent() != null) {
/* 402 */           matrix4f.translate(-part.x / 16.0F, -part.y / 16.0F, -part.z / 16.0F);
/*     */         } else {
/* 404 */           matrix4f.translate(-part.x / 16.0F, 1.5F - part.y / 16.0F, -part.z / 16.0F);
/*     */         } 
/*     */       } 
/*     */     }
/* 408 */     return matrix4f;
/*     */   }
/*     */   
/*     */   private static void applyShootSwayAndRotation(BedrockGunModel model, float aimingProgress) {
/* 412 */     BedrockPart rootNode = model.getRootNode();
/* 413 */     if (rootNode != null) {
/* 414 */       float progress = 1.0F - (float)(System.currentTimeMillis() - shootTimeStamp) / 300.0F;
/* 415 */       if (progress < 0.0F) {
/* 416 */         progress = 0.0F;
/*     */       }
/* 418 */       progress = (float)Easing.easeOutCubic(progress);
/* 419 */       rootNode.offsetX += SHOOT_X_SWAY_NOISE.getValue() / 16.0F * progress * (1.0F - aimingProgress);
/*     */       
/* 421 */       rootNode.offsetY += 0.00625F * progress * (1.0F - aimingProgress);
/* 422 */       rootNode.additionalQuaternion.mul((Quaternionfc)Axis.f_252436_.m_252961_(SHOOT_Y_ROTATION_NOISE.getValue() * progress));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void applyJumpingSway(BedrockGunModel model, float partialTicks) {
/* 427 */     if (jumpingTimeStamp == -1L) {
/* 428 */       jumpingTimeStamp = System.currentTimeMillis();
/*     */     }
/* 430 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 431 */     if (player != null) {
/* 432 */       double posY = Mth.m_14139_(partialTicks, (Minecraft.m_91087_()).f_91074_.f_19791_, (Minecraft.m_91087_()).f_91074_.m_20186_());
/* 433 */       float velocityY = (float)(posY - (Minecraft.m_91087_()).f_91074_.f_19791_) / partialTicks;
/* 434 */       if (player.m_20096_()) {
/* 435 */         if (!lastOnGround) {
/* 436 */           jumpingSwayProgress = velocityY / -0.1F;
/* 437 */           if (jumpingSwayProgress > 1.0F) {
/* 438 */             jumpingSwayProgress = 1.0F;
/*     */           }
/* 440 */           lastOnGround = true;
/*     */         } else {
/* 442 */           jumpingSwayProgress -= (float)(System.currentTimeMillis() - jumpingTimeStamp) / 150.0F;
/* 443 */           if (jumpingSwayProgress < 0.0F) {
/* 444 */             jumpingSwayProgress = 0.0F;
/*     */           }
/*     */         }
/*     */       
/* 448 */       } else if (lastOnGround) {
/*     */         
/* 450 */         jumpingSwayProgress = velocityY / 0.42F;
/* 451 */         if (jumpingSwayProgress > 1.0F) {
/* 452 */           jumpingSwayProgress = 1.0F;
/*     */         }
/* 454 */         lastOnGround = false;
/*     */       } else {
/* 456 */         jumpingSwayProgress -= (float)(System.currentTimeMillis() - jumpingTimeStamp) / 300.0F;
/* 457 */         if (jumpingSwayProgress < 0.0F) {
/* 458 */           jumpingSwayProgress = 0.0F;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 463 */     jumpingTimeStamp = System.currentTimeMillis();
/* 464 */     float ySway = JUMPING_DYNAMICS.update(-2.0F * jumpingSwayProgress);
/* 465 */     BedrockPart rootNode = model.getRootNode();
/* 466 */     if (rootNode != null)
/*     */     {
/* 468 */       rootNode.offsetY += -ySway / 16.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void getAnimationConstraintTransform(List<BedrockPart> nodePath, @Nonnull Vector3f originTranslation, @Nonnull Vector3f animatedTranslation, @Nonnull Vector3f rotation) {
/* 480 */     if (nodePath == null) {
/*     */       return;
/*     */     }
/*     */     
/* 484 */     Matrix4f animeMatrix = new Matrix4f();
/*     */     
/* 486 */     Matrix4f originMatrix = new Matrix4f();
/* 487 */     animeMatrix.identity();
/* 488 */     originMatrix.identity();
/* 489 */     BedrockPart constrainNode = nodePath.get(nodePath.size() - 1);
/* 490 */     for (BedrockPart part : nodePath) {
/*     */       
/* 492 */       if (part != constrainNode) {
/* 493 */         animeMatrix.translate(part.offsetX, part.offsetY, part.offsetZ);
/*     */       }
/*     */       
/* 496 */       if (part.getParent() != null) {
/* 497 */         animeMatrix.translate(part.x / 16.0F, part.y / 16.0F, part.z / 16.0F);
/*     */       } else {
/* 499 */         animeMatrix.translate(part.x / 16.0F, part.y / 16.0F - 1.5F, part.z / 16.0F);
/*     */       } 
/*     */       
/* 502 */       if (part != constrainNode) {
/* 503 */         animeMatrix.rotate((Quaternionfc)part.additionalQuaternion);
/*     */       }
/*     */       
/* 506 */       animeMatrix.rotate((Quaternionfc)Axis.f_252403_.m_252961_(part.zRot));
/* 507 */       animeMatrix.rotate((Quaternionfc)Axis.f_252436_.m_252961_(part.yRot));
/* 508 */       animeMatrix.rotate((Quaternionfc)Axis.f_252529_.m_252961_(part.xRot));
/*     */ 
/*     */       
/* 511 */       if (part.getParent() != null) {
/* 512 */         originMatrix.translate(part.x / 16.0F, part.y / 16.0F, part.z / 16.0F);
/*     */       } else {
/* 514 */         originMatrix.translate(part.x / 16.0F, part.y / 16.0F - 1.5F, part.z / 16.0F);
/*     */       } 
/*     */       
/* 517 */       originMatrix.rotate((Quaternionfc)Axis.f_252403_.m_252961_(part.zRot));
/* 518 */       originMatrix.rotate((Quaternionfc)Axis.f_252436_.m_252961_(part.yRot));
/* 519 */       originMatrix.rotate((Quaternionfc)Axis.f_252529_.m_252961_(part.xRot));
/*     */     } 
/*     */ 
/*     */     
/* 523 */     animeMatrix.getTranslation(animatedTranslation);
/* 524 */     originMatrix.getTranslation(originTranslation);
/* 525 */     Vector3f animatedRotation = MathUtil.getEulerAngles(animeMatrix);
/* 526 */     Vector3f originRotation = MathUtil.getEulerAngles(originMatrix);
/* 527 */     animatedRotation.sub((Vector3fc)originRotation);
/* 528 */     rotation.set(animatedRotation.x(), animatedRotation.y(), animatedRotation.z());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void applyAnimationConstraintTransform(PoseStack poseStack, BedrockGunModel gunModel, float weight) {
/* 537 */     List<BedrockPart> nodePath = gunModel.getConstraintPath();
/* 538 */     if (nodePath == null) {
/*     */       return;
/*     */     }
/* 541 */     if (gunModel.getConstraintObject() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 545 */     Vector3f originTranslation = new Vector3f();
/* 546 */     Vector3f animatedTranslation = new Vector3f();
/* 547 */     Vector3f rotation = new Vector3f();
/* 548 */     Vector3f translationICA = (gunModel.getConstraintObject()).translationConstraint;
/* 549 */     Vector3f rotationICA = (gunModel.getConstraintObject()).rotationConstraint;
/* 550 */     getAnimationConstraintTransform(nodePath, originTranslation, animatedTranslation, rotation);
/*     */     
/* 552 */     Vector3f inverseTranslation = new Vector3f((Vector3fc)originTranslation);
/* 553 */     inverseTranslation.sub((Vector3fc)animatedTranslation);
/* 554 */     inverseTranslation.mul(1.0F - translationICA.x(), 1.0F - translationICA.y(), 1.0F - translationICA.z());
/*     */     
/* 556 */     Vector3f inverseRotation = new Vector3f((Vector3fc)rotation);
/* 557 */     inverseRotation.mul(rotationICA.x() - 1.0F, rotationICA.y() - 1.0F, rotationICA.z() - 1.0F);
/*     */     
/* 559 */     poseStack.m_252880_(animatedTranslation.x(), animatedTranslation.y() + 1.5F, animatedTranslation.z());
/* 560 */     poseStack.m_252781_(Axis.f_252529_.m_252961_(inverseRotation.x() * weight));
/* 561 */     poseStack.m_252781_(Axis.f_252436_.m_252961_(inverseRotation.y() * weight));
/* 562 */     poseStack.m_252781_(Axis.f_252403_.m_252961_(inverseRotation.z() * weight));
/* 563 */     poseStack.m_252880_(-animatedTranslation.x(), -animatedTranslation.y() - 1.5F, -animatedTranslation.z());
/*     */     
/* 565 */     Matrix4f poseMatrix = poseStack.m_85850_().m_252922_();
/* 566 */     poseMatrix.m30(poseMatrix.m30() - inverseTranslation.x() * weight);
/* 567 */     poseMatrix.m31(poseMatrix.m31() - inverseTranslation.y() * weight);
/* 568 */     poseMatrix.m32(poseMatrix.m32() + inverseTranslation.z() * weight);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\FirstPersonRenderGunEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */