/*     */ package com.tacz.guns.client.event;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.platform.Window;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.compat.shouldersurfing.ShoulderSurfingCompat;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.Options;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.multiplayer.MultiPlayerGameMode;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.GameType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderGuiOverlayEvent;
/*     */ import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ 
/*     */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*     */ public class RenderCrosshairEvent {
/*  35 */   private static final ResourceLocation HIT_ICON = new ResourceLocation("tacz", "textures/crosshair/hit/hit_marker.png");
/*     */   private static final long KEEP_TIME = 300L;
/*     */   private static boolean isRefitScreen = false;
/*  38 */   private static long hitTimestamp = -1L;
/*  39 */   private static long killTimestamp = -1L;
/*  40 */   private static long headShotTimestamp = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(receiveCanceled = true)
/*     */   public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
/*  47 */     if (event.getOverlay().id().equals(VanillaGuiOverlay.CROSSHAIR.id())) {
/*  48 */       IGun iGun; LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*  49 */       if (player == null) {
/*     */         return;
/*     */       }
/*  52 */       if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/*     */         return;
/*     */       }
/*     */       
/*  56 */       event.setCanceled(true);
/*     */       
/*  58 */       renderHitMarker(event.getGuiGraphics(), event.getWindow());
/*     */       
/*  60 */       ReloadState reloadState = IGunOperator.fromLivingEntity((LivingEntity)player).getSynReloadState();
/*  61 */       if (reloadState.getStateType().isReloading()) {
/*     */         return;
/*     */       }
/*     */       
/*  65 */       if (isRefitScreen) {
/*     */         return;
/*     */       }
/*     */       
/*  69 */       ItemStack stack = player.m_21205_();
/*  70 */       Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */       else
/*     */       { return; }
/*  73 */        ResourceLocation gunId = iGun.getGunId(stack);
/*  74 */       IClientPlayerGunOperator playerGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
/*  75 */       TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */             if (playerGunOperator.getClientAimingProgress(event.getPartialTick()) > 0.9D) {
/*     */               boolean forceShow = gunIndex.isShowCrosshair();
/*     */               boolean shoulderSurfingForceShow = ShoulderSurfingCompat.showCrosshair();
/*     */               if (!forceShow && !shoulderSurfingForceShow) {
/*     */                 return;
/*     */               }
/*     */             } 
/*     */             GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */             if (!animationStateMachine.shouldHideCrossHair()) {
/*     */               renderCrosshair(event.getGuiGraphics(), event.getWindow());
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
/*     */   @SubscribeEvent
/*     */   public static void onRenderTick(TickEvent.RenderTickEvent event) {
/*  99 */     isRefitScreen = (Minecraft.m_91087_()).f_91080_ instanceof com.tacz.guns.client.gui.GunRefitScreen;
/*     */   }
/*     */   
/*     */   private static void renderCrosshair(GuiGraphics graphics, Window window) {
/* 103 */     Options options = (Minecraft.m_91087_()).f_91066_;
/*     */     
/* 105 */     boolean shoulderSurfingForceShow = ShoulderSurfingCompat.showCrosshair();
/* 106 */     if (!options.m_92176_().m_90612_() && !shoulderSurfingForceShow) {
/*     */       return;
/*     */     }
/* 109 */     if (options.f_92062_) {
/*     */       return;
/*     */     }
/* 112 */     MultiPlayerGameMode gameMode = (Minecraft.m_91087_()).f_91072_;
/* 113 */     if (gameMode == null) {
/*     */       return;
/*     */     }
/* 116 */     if (gameMode.m_105295_() == GameType.SPECTATOR) {
/*     */       return;
/*     */     }
/* 119 */     int width = window.m_85445_();
/* 120 */     int height = window.m_85446_();
/*     */     
/* 122 */     ResourceLocation location = CrosshairType.getTextureLocation((CrosshairType)RenderConfig.CROSSHAIR_TYPE.get());
/*     */     
/* 124 */     RenderSystem.enableBlend();
/* 125 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 126 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);
/* 127 */     float x = width / 2.0F - 8.0F;
/* 128 */     float y = height / 2.0F - 8.0F;
/* 129 */     graphics.m_280163_(location, (int)x, (int)y, 0.0F, 0.0F, 16, 16, 16, 16);
/*     */   }
/*     */   private static void renderHitMarker(GuiGraphics graphics, Window window) {
/*     */     float fadeTime;
/* 133 */     long remainHitTime = System.currentTimeMillis() - hitTimestamp;
/* 134 */     long remainKillTime = System.currentTimeMillis() - killTimestamp;
/* 135 */     long remainHeadShotTime = System.currentTimeMillis() - headShotTimestamp;
/* 136 */     float offset = ((Double)RenderConfig.HIT_MARKET_START_POSITION.get()).floatValue();
/*     */ 
/*     */     
/* 139 */     if (remainKillTime > 300L) {
/* 140 */       if (remainHitTime > 300L) {
/*     */         return;
/*     */       }
/* 143 */       fadeTime = (float)remainHitTime;
/*     */     }
/*     */     else {
/*     */       
/* 147 */       offset += (float)remainKillTime * 4.0F / 300.0F;
/* 148 */       fadeTime = (float)remainKillTime;
/*     */     } 
/*     */     
/* 151 */     int width = window.m_85445_();
/* 152 */     int height = window.m_85446_();
/* 153 */     float x = width / 2.0F - 8.0F;
/* 154 */     float y = height / 2.0F - 8.0F;
/*     */     
/* 156 */     RenderSystem.enableBlend();
/* 157 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 158 */     if (remainHeadShotTime > 300L) {
/* 159 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F - fadeTime / 300.0F);
/*     */     } else {
/* 161 */       RenderSystem.setShaderColor(1.0F, 0.0F, 0.0F, 1.0F - fadeTime / 300.0F);
/*     */     } 
/*     */     
/* 164 */     graphics.m_280163_(HIT_ICON, (int)(x - offset), (int)(y - offset), 0.0F, 0.0F, 8, 8, 16, 16);
/* 165 */     graphics.m_280163_(HIT_ICON, (int)(x + 8.0F + offset), (int)(y - offset), 8.0F, 0.0F, 8, 8, 16, 16);
/* 166 */     graphics.m_280163_(HIT_ICON, (int)(x - offset), (int)(y + 8.0F + offset), 0.0F, 8.0F, 8, 8, 16, 16);
/* 167 */     graphics.m_280163_(HIT_ICON, (int)(x + 8.0F + offset), (int)(y + 8.0F + offset), 8.0F, 8.0F, 8, 8, 16, 16);
/*     */   }
/*     */   
/*     */   public static void markHitTimestamp() {
/* 171 */     hitTimestamp = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public static void markKillTimestamp() {
/* 175 */     killTimestamp = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public static void markHeadShotTimestamp() {
/* 179 */     headShotTimestamp = System.currentTimeMillis();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\RenderCrosshairEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */