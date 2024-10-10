/*     */ package com.tacz.guns.client.event;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.event.BeforeRenderHandEvent;
/*     */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*     */ import com.tacz.guns.api.client.other.KeepingItemRenderer;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunFireEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.modifier.ParameterizedCachePair;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.util.math.MathUtil;
/*     */ import com.tacz.guns.util.math.SecondOrderDynamics;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.ComputeFovModifierEvent;
/*     */ import net.minecraftforge.client.event.ViewportEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*     */ public class CameraSetupEvent
/*     */ {
/*  49 */   private static final SecondOrderDynamics WORLD_FOV_DYNAMICS = new SecondOrderDynamics(0.5F, 1.2F, 0.5F, 0.0F);
/*  50 */   private static final SecondOrderDynamics ITEM_MODEL_FOV_DYNAMICS = new SecondOrderDynamics(0.5F, 1.2F, 0.5F, 0.0F);
/*     */   private static PolynomialSplineFunction pitchSplineFunction;
/*     */   private static PolynomialSplineFunction yawSplineFunction;
/*  53 */   private static long shootTimeStamp = -1L;
/*  54 */   private static double xRotO = 0.0D;
/*  55 */   private static double yRotO = 0.0D;
/*  56 */   private static BedrockGunModel lastModel = null;
/*     */   @SubscribeEvent
/*     */   public static void applyLevelCameraAnimation(ViewportEvent.ComputeCameraAngles event) {
/*     */     IGun iGun;
/*  60 */     if (!((Boolean)(Minecraft.m_91087_()).f_91066_.m_231830_().m_231551_()).booleanValue()) {
/*     */       return;
/*     */     }
/*  63 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*  64 */     if (player == null) {
/*     */       return;
/*     */     }
/*  67 */     ItemStack stack = KeepingItemRenderer.getRenderer().getCurrentItem();
/*  68 */     Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  71 */      TimelessAPI.getClientGunIndex(iGun.getGunId(stack)).ifPresent(gunIndex -> {
/*     */           BedrockGunModel gunModel = gunIndex.getGunModel();
/*     */           if (lastModel != gunModel) {
/*     */             gunModel.cleanCameraAnimationTransform();
/*     */             lastModel = gunModel;
/*     */           } 
/*     */           IClientPlayerGunOperator clientPlayerGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
/*     */           float partialTicks = Minecraft.m_91087_().m_91296_();
/*     */           float aimingProgress = clientPlayerGunOperator.getClientAimingProgress(partialTicks);
/*     */           float zoom = iGun.getAimingZoom(stack);
/*     */           float multiplier = 1.0F - aimingProgress + aimingProgress / (float)Math.sqrt(zoom);
/*     */           Quaternionf q = MathUtil.multiplyQuaternion((gunModel.getCameraAnimationObject()).rotationQuaternion, multiplier);
/*     */           double yaw = Math.asin((2.0F * (q.w() * q.y() - q.x() * q.z())));
/*     */           double pitch = Math.atan2((2.0F * (q.w() * q.x() + q.y() * q.z())), (1.0F - 2.0F * (q.x() * q.x() + q.y() * q.y())));
/*     */           double roll = Math.atan2((2.0F * (q.w() * q.z() + q.x() * q.y())), (1.0F - 2.0F * (q.y() * q.y() + q.z() * q.z())));
/*     */           yaw = Math.toDegrees(yaw);
/*     */           pitch = Math.toDegrees(pitch);
/*     */           roll = Math.toDegrees(roll);
/*     */           event.setYaw((float)yaw + event.getYaw());
/*     */           event.setPitch((float)pitch + event.getPitch());
/*     */           event.setRoll((float)roll + event.getRoll());
/*     */         });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void applyItemInHandCameraAnimation(BeforeRenderHandEvent event) {
/*     */     IGun iGun;
/*  98 */     if (!((Boolean)(Minecraft.m_91087_()).f_91066_.m_231830_().m_231551_()).booleanValue()) {
/*     */       return;
/*     */     }
/* 101 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 102 */     if (player == null) {
/*     */       return;
/*     */     }
/* 105 */     ItemStack stack = KeepingItemRenderer.getRenderer().getCurrentItem();
/* 106 */     Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/* 109 */      TimelessAPI.getClientGunIndex(iGun.getGunId(stack)).ifPresent(gunIndex -> {
/*     */           BedrockGunModel gunModel = gunIndex.getGunModel();
/*     */           PoseStack poseStack = event.getPoseStack();
/*     */           IClientPlayerGunOperator clientPlayerGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
/*     */           float partialTicks = Minecraft.m_91087_().m_91296_();
/*     */           float aimingProgress = clientPlayerGunOperator.getClientAimingProgress(partialTicks);
/*     */           float zoom = iGun.getAimingZoom(stack);
/*     */           float multiplier = 1.0F - aimingProgress + aimingProgress / (float)Math.sqrt(zoom);
/*     */           Quaternionf quaternion = MathUtil.multiplyQuaternion((gunModel.getCameraAnimationObject()).rotationQuaternion, multiplier);
/*     */           poseStack.m_252781_(quaternion);
/*     */           gunModel.cleanCameraAnimationTransform();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void applyScopeMagnification(ViewportEvent.ComputeFov event) {
/* 127 */     if (!event.usedConfiguredFov()) {
/*     */       return;
/*     */     }
/* 130 */     Entity entity = event.getCamera().m_90592_();
/* 131 */     if (entity instanceof LivingEntity) { IGun iGun; LivingEntity livingEntity = (LivingEntity)entity;
/* 132 */       ItemStack stack = KeepingItemRenderer.getRenderer().getCurrentItem();
/* 133 */       Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 134 */       else { float fov = WORLD_FOV_DYNAMICS.update((float)event.getFOV());
/* 135 */         event.setFOV(fov);
/*     */         return; }
/*     */       
/* 138 */       float zoom = iGun.getAimingZoom(stack);
/* 139 */       if (livingEntity instanceof LocalPlayer) { LocalPlayer localPlayer = (LocalPlayer)livingEntity;
/* 140 */         IClientPlayerGunOperator gunOperator = IClientPlayerGunOperator.fromLocalPlayer(localPlayer);
/* 141 */         float aimingProgress = gunOperator.getClientAimingProgress((float)event.getPartialTick());
/* 142 */         float fov = WORLD_FOV_DYNAMICS.update((float)MathUtil.magnificationToFov((1.0F + (zoom - 1.0F) * aimingProgress), event.getFOV()));
/* 143 */         event.setFOV(fov); }
/*     */       else
/* 145 */       { IGunOperator gunOperator = IGunOperator.fromLivingEntity(livingEntity);
/* 146 */         float aimingProgress = gunOperator.getSynAimingProgress();
/* 147 */         float fov = WORLD_FOV_DYNAMICS.update((float)MathUtil.magnificationToFov((1.0F + (zoom - 1.0F) * aimingProgress), event.getFOV()));
/* 148 */         event.setFOV(fov); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void applyGunModelFovModifying(ViewportEvent.ComputeFov event) {
/* 155 */     if (event.usedConfiguredFov()) {
/*     */       return;
/*     */     }
/* 158 */     Entity entity = event.getCamera().m_90592_();
/* 159 */     if (entity instanceof LivingEntity) { IGun iGun; LivingEntity livingEntity = (LivingEntity)entity;
/* 160 */       ItemStack stack = KeepingItemRenderer.getRenderer().getCurrentItem();
/* 161 */       Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 162 */       else { float fov = ITEM_MODEL_FOV_DYNAMICS.update((float)event.getFOV());
/* 163 */         event.setFOV(fov);
/*     */         return; }
/*     */       
/* 166 */       ResourceLocation scopeItemId = iGun.getAttachmentId(stack, AttachmentType.SCOPE);
/* 167 */       if (scopeItemId.equals(DefaultAssets.EMPTY_ATTACHMENT_ID)) {
/* 168 */         scopeItemId = iGun.getBuiltInAttachmentId(stack, AttachmentType.SCOPE);
/*     */       }
/* 170 */       if (DefaultAssets.isEmptyAttachmentId(scopeItemId)) {
/* 171 */         float fov = ITEM_MODEL_FOV_DYNAMICS.update((float)event.getFOV());
/* 172 */         event.setFOV(fov);
/*     */         return;
/*     */       } 
/* 175 */       float modifiedFov = ((Float)TimelessAPI.getClientAttachmentIndex(scopeItemId).map(ClientAttachmentIndex::getFov).orElse(Float.valueOf((float)event.getFOV()))).floatValue();
/* 176 */       if (livingEntity instanceof LocalPlayer) { LocalPlayer localPlayer = (LocalPlayer)livingEntity;
/* 177 */         IClientPlayerGunOperator gunOperator = IClientPlayerGunOperator.fromLocalPlayer(localPlayer);
/* 178 */         float aimingProgress = gunOperator.getClientAimingProgress((float)event.getPartialTick());
/* 179 */         float fov = ITEM_MODEL_FOV_DYNAMICS.update(Mth.m_14179_(aimingProgress, (float)event.getFOV(), modifiedFov));
/* 180 */         event.setFOV(fov); }
/*     */       else
/* 182 */       { IGunOperator gunOperator = IGunOperator.fromLivingEntity(livingEntity);
/* 183 */         float aimingProgress = gunOperator.getSynAimingProgress();
/* 184 */         float fov = ITEM_MODEL_FOV_DYNAMICS.update(Mth.m_14179_(aimingProgress, (float)event.getFOV(), modifiedFov));
/* 185 */         event.setFOV(fov); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void initialCameraRecoil(GunFireEvent event) {
/* 192 */     if (event.getLogicalSide().isClient()) {
/* 193 */       IGun iGun; LivingEntity shooter = event.getShooter();
/* 194 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 195 */       if (!shooter.equals(player)) {
/*     */         return;
/*     */       }
/* 198 */       ItemStack mainHandItem = player.m_21205_();
/* 199 */       Item item = mainHandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */       else
/*     */       { return; }
/* 202 */        AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity((LivingEntity)player).getCacheProperty();
/* 203 */       if (cacheProperty == null) {
/*     */         return;
/*     */       }
/* 206 */       ResourceLocation gunId = iGun.getGunId(mainHandItem);
/* 207 */       Optional<ClientGunIndex> gunIndexOptional = TimelessAPI.getClientGunIndex(gunId);
/* 208 */       if (gunIndexOptional.isEmpty()) {
/*     */         return;
/*     */       }
/* 211 */       ClientGunIndex gunIndex = gunIndexOptional.get();
/* 212 */       GunData gunData = gunIndex.getGunData();
/*     */       
/* 214 */       ParameterizedCachePair<Float, Float> attachmentRecoilModifier = (ParameterizedCachePair<Float, Float>)cacheProperty.getCache("recoil");
/* 215 */       IClientPlayerGunOperator clientPlayerGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 216 */       float partialTicks = Minecraft.m_91087_().m_91296_();
/* 217 */       float aimingProgress = clientPlayerGunOperator.getClientAimingProgress(partialTicks);
/* 218 */       float zoom = iGun.getAimingZoom(mainHandItem);
/* 219 */       float aimingRecoilModifier = 1.0F - aimingProgress + aimingProgress / (float)Math.sqrt(zoom);
/*     */       
/* 221 */       if (!player.m_6069_() && player.m_20089_() == Pose.SWIMMING) {
/* 222 */         aimingRecoilModifier *= gunData.getCrawlRecoilMultiplier();
/*     */       }
/* 224 */       pitchSplineFunction = gunData.getRecoil().genPitchSplineFunction((float)attachmentRecoilModifier.left().eval(aimingRecoilModifier));
/* 225 */       yawSplineFunction = gunData.getRecoil().genYawSplineFunction((float)attachmentRecoilModifier.right().eval(aimingRecoilModifier));
/* 226 */       shootTimeStamp = System.currentTimeMillis();
/* 227 */       xRotO = 0.0D;
/* 228 */       yRotO = 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void applyCameraRecoil(ViewportEvent.ComputeCameraAngles event) {
/* 234 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 235 */     if (player == null) {
/*     */       return;
/*     */     }
/* 238 */     long timeTotal = System.currentTimeMillis() - shootTimeStamp;
/* 239 */     if (pitchSplineFunction != null && pitchSplineFunction.isValidPoint(timeTotal)) {
/* 240 */       double value = pitchSplineFunction.value(timeTotal);
/* 241 */       player.m_146926_(player.m_146909_() - (float)(value - xRotO));
/* 242 */       xRotO = value;
/*     */     } 
/* 244 */     if (yawSplineFunction != null && yawSplineFunction.isValidPoint(timeTotal)) {
/* 245 */       double value = yawSplineFunction.value(timeTotal);
/* 246 */       player.m_146922_(player.m_146908_() - (float)(value - yRotO));
/* 247 */       yRotO = value;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOW)
/*     */   public static void onComputeMovementFov(ComputeFovModifierEvent event) {
/* 253 */     if (!((Boolean)RenderConfig.DISABLE_MOVEMENT_ATTRIBUTE_FOV.get()).booleanValue())
/* 254 */       return;  LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 255 */     if (player == null) {
/*     */       return;
/*     */     }
/* 258 */     float f = 1.0F;
/* 259 */     if (player.m_21205_().m_41720_() instanceof com.tacz.guns.api.item.gun.AbstractGunItem) {
/* 260 */       if ((player.m_150110_()).f_35935_) {
/* 261 */         f *= 1.1F;
/*     */       }
/* 263 */       event.setNewFovModifier(player.m_20142_() ? (1.15F * f) : f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\CameraSetupEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */