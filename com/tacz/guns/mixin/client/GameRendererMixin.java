/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.api.client.event.RenderItemInHandBobEvent;
/*    */ import com.tacz.guns.api.client.event.RenderLevelBobEvent;
/*    */ import com.tacz.guns.client.renderer.other.GunHurtBobTweak;
/*    */ import net.minecraft.client.Camera;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({GameRenderer.class})
/*    */ public abstract class GameRendererMixin
/*    */ {
/*    */   @Unique
/*    */   private boolean tacz$useFovSetting;
/*    */   
/*    */   @Inject(method = {"bobHurt"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onBobHurt(PoseStack pMatrixStack, float pPartialTicks, CallbackInfo ci) {
/*    */     boolean cancel;
/* 34 */     Entity entity = m_172797_().m_91288_(); if (entity instanceof LocalPlayer) { LocalPlayer player = (LocalPlayer)entity; if (!player.m_21224_() && 
/* 35 */         GunHurtBobTweak.onHurtBobTweak(player, pMatrixStack, pPartialTicks)) {
/* 36 */         ci.cancel();
/*    */         
/*    */         return;
/*    */       }  }
/*    */ 
/*    */     
/* 42 */     if (!this.tacz$useFovSetting) {
/* 43 */       cancel = MinecraftForge.EVENT_BUS.post((Event)new RenderItemInHandBobEvent.BobHurt());
/*    */     } else {
/* 45 */       cancel = MinecraftForge.EVENT_BUS.post((Event)new RenderLevelBobEvent.BobHurt());
/*    */     } 
/* 47 */     if (cancel)
/* 48 */       ci.cancel(); 
/*    */   } @Shadow
/*    */   public abstract Minecraft m_172797_(); @Shadow
/*    */   public abstract void m_109093_(float paramFloat, long paramLong, boolean paramBoolean);
/*    */   @Inject(method = {"bobView"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onBobView(PoseStack pMatrixStack, float pPartialTicks, CallbackInfo ci) {
/*    */     boolean cancel;
/* 55 */     if (!this.tacz$useFovSetting) {
/* 56 */       cancel = MinecraftForge.EVENT_BUS.post((Event)new RenderItemInHandBobEvent.BobView());
/*    */     } else {
/* 58 */       cancel = MinecraftForge.EVENT_BUS.post((Event)new RenderLevelBobEvent.BobView());
/*    */     } 
/* 60 */     if (cancel) {
/* 61 */       ci.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"getFov"}, at = {@At("HEAD")})
/*    */   public void switchRenderType(Camera pActiveRenderInfo, float pPartialTicks, boolean pUseFOVSetting, CallbackInfoReturnable<Double> cir) {
/* 72 */     this.tacz$useFovSetting = pUseFOVSetting;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\GameRendererMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */