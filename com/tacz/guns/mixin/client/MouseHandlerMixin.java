/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.tacz.guns.api.DefaultAssets;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.api.item.nbt.AttachmentItemDataAccessor;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.config.client.ZoomConfig;
/*    */ import com.tacz.guns.util.math.MathUtil;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.MouseHandler;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ 
/*    */ @Mixin({MouseHandler.class})
/*    */ public class MouseHandlerMixin {
/*    */   @WrapOperation(method = {"turnPlayer"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V")})
/*    */   public void reduceSensitivity(LocalPlayer player, double yaw, double pitch, Operation<Void> original) {
/* 32 */     ItemStack mainHandItem = player.m_21205_();
/* 33 */     IGun iGun = IGun.getIGunOrNull(mainHandItem);
/* 34 */     if (iGun == null) {
/* 35 */       original.call(new Object[] { player, Double.valueOf(yaw), Double.valueOf(pitch) });
/*    */       return;
/*    */     } 
/* 38 */     ResourceLocation scopeId = iGun.getAttachmentId(mainHandItem, AttachmentType.SCOPE);
/* 39 */     if (scopeId.equals(DefaultAssets.EMPTY_ATTACHMENT_ID)) {
/* 40 */       scopeId = iGun.getBuiltInAttachmentId(mainHandItem, AttachmentType.SCOPE);
/*    */     }
/* 42 */     float zoomLevel = 1.0F;
/* 43 */     if (DefaultAssets.isEmptyAttachmentId(scopeId)) {
/*    */       
/* 45 */       ResourceLocation gunId = iGun.getGunId(mainHandItem);
/* 46 */       zoomLevel = ((Float)TimelessAPI.getClientGunIndex(gunId).map(ClientGunIndex::getIronZoom).orElse(Float.valueOf(1.0F))).floatValue();
/*    */     } else {
/* 48 */       Optional<ClientAttachmentIndex> optional = TimelessAPI.getClientAttachmentIndex(scopeId);
/* 49 */       if (optional.isPresent()) {
/* 50 */         float[] zoom = ((ClientAttachmentIndex)optional.get()).getZoom();
/* 51 */         if (zoom != null && zoom.length > 0) {
/* 52 */           CompoundTag attachmentTag = iGun.getAttachmentTag(mainHandItem, AttachmentType.SCOPE);
/* 53 */           zoomLevel = zoom[AttachmentItemDataAccessor.getZoomNumberFromTag(attachmentTag) % zoom.length];
/*    */         } 
/*    */       } 
/*    */     } 
/* 57 */     Minecraft minecraft = Minecraft.m_91087_();
/* 58 */     float progress = IGunOperator.fromLivingEntity((LivingEntity)player).getSynAimingProgress();
/*    */     
/* 60 */     double sensitivityMultiplier = ((Double)ZoomConfig.ZOOM_SENSITIVITY_BASE_MULTIPLIER.get()).doubleValue();
/* 61 */     sensitivityMultiplier = 1.0D + (sensitivityMultiplier - 1.0D) * progress;
/*    */     
/* 63 */     double originalFov = ((Integer)minecraft.f_91066_.m_231837_().m_231551_()).intValue();
/* 64 */     double currentFov = MathUtil.magnificationToFov((1.0F + (zoomLevel - 1.0F) * progress), originalFov);
/*    */     
/* 66 */     double coefficient = ((Double)ZoomConfig.SCREEN_DISTANCE_COEFFICIENT.get()).doubleValue();
/* 67 */     double denominator = MathUtil.zoomSensitivityRatio(currentFov, originalFov, coefficient) * sensitivityMultiplier;
/*    */     
/* 69 */     double finalYaw = yaw * denominator;
/* 70 */     double finalPitch = getCrawlPitch(player, pitch, denominator);
/* 71 */     original.call(new Object[] { player, Double.valueOf(finalYaw), Double.valueOf(finalPitch) });
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private static double getCrawlPitch(LocalPlayer player, double pitch, double denominator) {
/* 76 */     double finalPitch = pitch * denominator;
/*    */     
/* 78 */     if (!player.m_6069_() && player.m_20089_() == Pose.SWIMMING) {
/*    */       
/* 80 */       float playerPitch = -player.m_146909_();
/*    */       
/* 82 */       if (playerPitch > 25.0F) {
/* 83 */         finalPitch = Math.max(finalPitch, 0.0D);
/*    */       }
/*    */       
/* 86 */       if (playerPitch < -10.0F) {
/* 87 */         finalPitch = Math.min(finalPitch, 0.0D);
/*    */       }
/*    */     } 
/* 90 */     return finalPitch;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\MouseHandlerMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */