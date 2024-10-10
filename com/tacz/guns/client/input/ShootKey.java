/*     */ package com.tacz.guns.client.input;
/*     */ import com.mojang.blaze3d.platform.InputConstants;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*     */ import com.tacz.guns.api.entity.ShootResult;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.util.InputExtraCheck;
/*     */ import net.minecraft.client.KeyMapping;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.client.settings.IKeyConflictContext;
/*     */ import net.minecraftforge.client.settings.KeyConflictContext;
/*     */ import net.minecraftforge.client.settings.KeyModifier;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ @EventBusSubscriber({Dist.CLIENT})
/*     */ public class ShootKey {
/*  30 */   public static final KeyMapping SHOOT_KEY = new KeyMapping("key.tacz.shoot.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.MOUSE, 0, "key.category.tacz");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void autoShoot(TickEvent.ClientTickEvent event) {
/*  39 */     if (event.phase != TickEvent.Phase.END && !InputExtraCheck.isInGame()) {
/*     */       return;
/*     */     }
/*  42 */     Minecraft mc = Minecraft.m_91087_();
/*  43 */     LocalPlayer player = mc.f_91074_;
/*  44 */     if (player == null || player.m_5833_()) {
/*     */       return;
/*     */     }
/*  47 */     ItemStack mainHandItem = player.m_21205_();
/*  48 */     Item item = mainHandItem.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/*  49 */       FireMode fireMode = iGun.getFireMode(mainHandItem);
/*     */ 
/*     */       
/*  52 */       boolean isBurstAuto = (fireMode == FireMode.BURST && ((Boolean)TimelessAPI.getCommonGunIndex(iGun.getGunId(mainHandItem)).map(index -> Boolean.valueOf(index.getGunData().getBurstData().isContinuousShoot())).orElse(Boolean.valueOf(false))).booleanValue());
/*  53 */       IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/*  54 */       if (SHOOT_KEY.m_90857_() && (fireMode == FireMode.AUTO || isBurstAuto)) {
/*  55 */         operator.shoot();
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static boolean autoShootController() {
/*  61 */     if (!InputExtraCheck.isInGame()) {
/*  62 */       return false;
/*     */     }
/*  64 */     Minecraft mc = Minecraft.m_91087_();
/*  65 */     LocalPlayer player = mc.f_91074_;
/*  66 */     if (player == null || player.m_5833_()) {
/*  67 */       return false;
/*     */     }
/*  69 */     ItemStack mainHandItem = player.m_21205_();
/*  70 */     Item item = mainHandItem.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/*  71 */       FireMode fireMode = iGun.getFireMode(mainHandItem);
/*     */ 
/*     */       
/*  74 */       boolean isBurstAuto = (fireMode == FireMode.BURST && ((Boolean)TimelessAPI.getCommonGunIndex(iGun.getGunId(mainHandItem)).map(index -> Boolean.valueOf(index.getGunData().getBurstData().isContinuousShoot())).orElse(Boolean.valueOf(false))).booleanValue());
/*  75 */       IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/*  76 */       if (fireMode == FireMode.AUTO || isBurstAuto) {
/*  77 */         return (operator.shoot() == ShootResult.SUCCESS);
/*     */       } }
/*     */     
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void semiShoot(InputEvent.MouseButton.Post event) {
/*  85 */     if (InputExtraCheck.isInGame() && SHOOT_KEY.m_90830_(event.getButton())) {
/*     */       
/*  87 */       if (event.getAction() == 0) {
/*  88 */         SoundPlayManager.resetDryFireSound();
/*     */         return;
/*     */       } 
/*  91 */       Minecraft mc = Minecraft.m_91087_();
/*  92 */       LocalPlayer player = mc.f_91074_;
/*  93 */       if (player == null || player.m_5833_()) {
/*     */         return;
/*     */       }
/*  96 */       ItemStack mainHandItem = player.m_21205_();
/*  97 */       Item item = mainHandItem.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/*  98 */         FireMode fireMode = iGun.getFireMode(mainHandItem);
/*     */ 
/*     */         
/* 101 */         boolean isBurstSemi = (fireMode == FireMode.BURST && ((Boolean)TimelessAPI.getCommonGunIndex(iGun.getGunId(mainHandItem)).map(index -> Boolean.valueOf(!index.getGunData().getBurstData().isContinuousShoot())).orElse(Boolean.valueOf(false))).booleanValue());
/* 102 */         if (fireMode == FireMode.UNKNOWN) {
/* 103 */           player.m_213846_((Component)Component.m_237115_("message.tacz.fire_select.fail"));
/*     */         }
/* 105 */         if (fireMode == FireMode.SEMI || isBurstSemi) {
/* 106 */           IClientPlayerGunOperator.fromLocalPlayer(player).shoot();
/*     */         } }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean semiShootController(boolean isPress) {
/* 113 */     if (!InputExtraCheck.isInGame()) {
/* 114 */       return false;
/*     */     }
/*     */     
/* 117 */     if (!isPress) {
/* 118 */       SoundPlayManager.resetDryFireSound();
/* 119 */       return false;
/*     */     } 
/* 121 */     Minecraft mc = Minecraft.m_91087_();
/* 122 */     LocalPlayer player = mc.f_91074_;
/* 123 */     if (player == null || player.m_5833_()) {
/* 124 */       return false;
/*     */     }
/* 126 */     ItemStack mainHandItem = player.m_21205_();
/* 127 */     Item item = mainHandItem.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 128 */       FireMode fireMode = iGun.getFireMode(mainHandItem);
/*     */ 
/*     */       
/* 131 */       boolean isBurstSemi = (fireMode == FireMode.BURST && ((Boolean)TimelessAPI.getCommonGunIndex(iGun.getGunId(mainHandItem)).map(index -> Boolean.valueOf(!index.getGunData().getBurstData().isContinuousShoot())).orElse(Boolean.valueOf(false))).booleanValue());
/* 132 */       if (fireMode == FireMode.UNKNOWN) {
/* 133 */         player.m_213846_((Component)Component.m_237115_("message.tacz.fire_select.fail"));
/* 134 */         return false;
/*     */       } 
/* 136 */       if (fireMode == FireMode.SEMI || isBurstSemi) {
/* 137 */         return (IClientPlayerGunOperator.fromLocalPlayer(player).shoot() == ShootResult.SUCCESS);
/*     */       } }
/*     */     
/* 140 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\ShootKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */