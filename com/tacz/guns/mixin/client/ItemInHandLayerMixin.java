/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.model.functional.MuzzleFlashRender;
/*    */ import com.tacz.guns.client.model.functional.ShellRender;
/*    */ import com.tacz.guns.client.renderer.other.HumanoidOffhandRender;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
/*    */ import net.minecraft.world.entity.HumanoidArm;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({ItemInHandLayer.class})
/*    */ public class ItemInHandLayerMixin {
/*    */   @Inject(method = {"render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V"}, at = {@At("TAIL")})
/*    */   private void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
/* 25 */     MuzzleFlashRender.isSelf = false;
/* 26 */     ShellRender.isSelf = false;
/* 27 */     HumanoidOffhandRender.renderGun(livingEntity, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderArmWithItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderArmWithItemHead(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext pDisplayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
/* 32 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 33 */     if (livingEntity.equals(player)) {
/* 34 */       MuzzleFlashRender.isSelf = true;
/* 35 */       ShellRender.isSelf = true;
/*    */     } 
/* 37 */     if (IGun.mainhandHoldGun(livingEntity) && arm == HumanoidArm.LEFT) {
/* 38 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderArmWithItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At("TAIL")})
/*    */   private void renderArmWithItemTail(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext pDisplayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
/* 44 */     MuzzleFlashRender.isSelf = false;
/* 45 */     ShellRender.isSelf = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\ItemInHandLayerMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */