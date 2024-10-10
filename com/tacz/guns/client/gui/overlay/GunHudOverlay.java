/*     */ package com.tacz.guns.client.gui.overlay;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.api.item.IAmmoBox;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.AmmoCountStyle;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.util.AttachmentDataUtils;
/*     */ import java.text.DecimalFormat;
/*     */ import net.minecraft.SharedConstants;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*     */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ 
/*     */ public class GunHudOverlay
/*     */   implements IGuiOverlay
/*     */ {
/*  35 */   private static final ResourceLocation SEMI = new ResourceLocation("tacz", "textures/hud/fire_mode_semi.png");
/*  36 */   private static final ResourceLocation AUTO = new ResourceLocation("tacz", "textures/hud/fire_mode_auto.png");
/*  37 */   private static final ResourceLocation BURST = new ResourceLocation("tacz", "textures/hud/fire_mode_burst.png");
/*  38 */   private static final DecimalFormat CURRENT_AMMO_FORMAT = new DecimalFormat("000");
/*  39 */   private static final DecimalFormat CURRENT_AMMO_FORMAT_PERCENT = new DecimalFormat("000%");
/*  40 */   private static final DecimalFormat INVENTORY_AMMO_FORMAT = new DecimalFormat("0000");
/*  41 */   private static long checkAmmoTimestamp = -1L;
/*  42 */   private static int cacheMaxAmmoCount = 0;
/*  43 */   private static int cacheInventoryAmmoCount = 0; public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) { IGun iGun;
/*     */     int ammoCountColor;
/*     */     String currentAmmoCountText;
/*     */     int inventoryAmmoCountColor;
/*  47 */     if (!((Boolean)RenderConfig.GUN_HUD_ENABLE.get()).booleanValue()) {
/*     */       return;
/*     */     }
/*  50 */     Minecraft mc = Minecraft.m_91087_();
/*  51 */     LocalPlayer player = mc.f_91074_;
/*  52 */     if (!(player instanceof com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator)) {
/*     */       return;
/*     */     }
/*  55 */     ItemStack stack = player.m_21205_();
/*  56 */     Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  59 */      ResourceLocation gunId = iGun.getGunId(stack);
/*  60 */     ClientGunIndex gunIndex = TimelessAPI.getClientGunIndex(gunId).orElse(null);
/*  61 */     if (gunIndex == null) {
/*     */       return;
/*     */     }
/*     */     
/*  65 */     int ammoCount = iGun.getCurrentAmmoCount(stack) + ((iGun.hasBulletInBarrel(stack) && gunIndex.getGunData().getBolt() != Bolt.OPEN_BOLT) ? 1 : 0);
/*     */     
/*  67 */     if (ammoCount < cacheMaxAmmoCount * 0.25D) {
/*     */       
/*  69 */       ammoCountColor = 16733525;
/*     */     } else {
/*     */       
/*  72 */       ammoCountColor = 16777215;
/*     */     } 
/*     */ 
/*     */     
/*  76 */     if (gunIndex.getAmmoCountStyle() == AmmoCountStyle.PERCENT) {
/*     */       
/*  78 */       currentAmmoCountText = CURRENT_AMMO_FORMAT_PERCENT.format((ammoCount / ((cacheMaxAmmoCount == 0) ? 1.0F : cacheMaxAmmoCount)));
/*     */     } else {
/*     */       
/*  81 */       currentAmmoCountText = CURRENT_AMMO_FORMAT.format(ammoCount);
/*     */     } 
/*     */ 
/*     */     
/*  85 */     handleCacheCount(player, stack, gunIndex, iGun);
/*     */ 
/*     */     
/*  88 */     graphics.m_280509_(width - 75, height - 43, width - 74, height - 25, -1);
/*     */     
/*  90 */     PoseStack poseStack = graphics.m_280168_();
/*     */     
/*  92 */     Font font = mc.f_91062_;
/*     */ 
/*     */     
/*  95 */     poseStack.m_85836_();
/*  96 */     poseStack.m_85841_(1.5F, 1.5F, 1.0F);
/*  97 */     graphics.drawString(font, currentAmmoCountText, (width - 70) / 1.5F, (height - 43) / 1.5F, ammoCountColor, false);
/*  98 */     poseStack.m_85849_();
/*     */     
/* 100 */     poseStack.m_85836_();
/* 101 */     poseStack.m_85841_(0.8F, 0.8F, 1.0F);
/* 102 */     String inventoryAmmoCountText = INVENTORY_AMMO_FORMAT.format(cacheInventoryAmmoCount);
/*     */     
/* 104 */     if (iGun.useDummyAmmo(stack)) {
/* 105 */       inventoryAmmoCountColor = 5636095;
/*     */     } else {
/* 107 */       inventoryAmmoCountColor = 11184810;
/*     */     } 
/* 109 */     graphics.drawString(font, inventoryAmmoCountText, ((width - 68) + mc.f_91062_.m_92895_(currentAmmoCountText) * 1.5F) / 0.8F, (height - 43) / 0.8F, inventoryAmmoCountColor, false);
/* 110 */     poseStack.m_85849_();
/*     */ 
/*     */     
/* 113 */     String minecraftVersion = SharedConstants.m_183709_().m_132493_();
/* 114 */     String modVersion = ModList.get().getModFileById("tacz").versionString();
/* 115 */     String debugInfo = String.format("%s-%s", new Object[] { minecraftVersion, modVersion });
/*     */     
/* 117 */     poseStack.m_85836_();
/* 118 */     poseStack.m_85841_(0.5F, 0.5F, 1.0F);
/* 119 */     graphics.m_280488_(font, debugInfo, (int)((width - 70) / 0.5F), (int)((height - 29.0F) / 0.5F), -5592406);
/* 120 */     poseStack.m_85849_();
/*     */ 
/*     */     
/* 123 */     RenderSystem.enableDepthTest();
/* 124 */     RenderSystem.setShader(GameRenderer::m_172817_);
/* 125 */     RenderSystem.enableBlend();
/* 126 */     RenderSystem.defaultBlendFunc();
/*     */ 
/*     */     
/* 129 */     ResourceLocation hudTexture = gunIndex.getHUDTexture();
/* 130 */     ResourceLocation hudEmptyTexture = gunIndex.getHudEmptyTexture();
/*     */     
/* 132 */     if (ammoCount <= 0) {
/* 133 */       if (hudEmptyTexture == null) {
/* 134 */         RenderSystem.setShaderColor(1.0F, 0.3F, 0.3F, 1.0F);
/*     */       } else {
/* 136 */         hudTexture = hudEmptyTexture;
/*     */       } 
/*     */     }
/*     */     
/* 140 */     graphics.m_280163_(hudTexture, width - 117, height - 44, 0.0F, 0.0F, 39, 13, 39, 13);
/*     */ 
/*     */     
/* 143 */     FireMode fireMode = IGun.getMainhandFireMode((LivingEntity)player);
/* 144 */     switch (fireMode) { case AUTO: 
/*     */       case BURST: 
/*     */       default:
/* 147 */         break; }  ResourceLocation fireModeTexture = SEMI;
/*     */     
/* 149 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 150 */     graphics.m_280163_(fireModeTexture, (int)(width - 68.5D + mc.f_91062_.m_92895_(currentAmmoCountText) * 1.5D), height - 38, 0.0F, 0.0F, 10, 10, 10, 10); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void handleCacheCount(LocalPlayer player, ItemStack stack, ClientGunIndex gunIndex, IGun iGun) {
/* 155 */     if (System.currentTimeMillis() - checkAmmoTimestamp > 200L) {
/* 156 */       checkAmmoTimestamp = System.currentTimeMillis();
/*     */       
/* 158 */       cacheMaxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(stack, gunIndex.getGunData());
/*     */       
/* 160 */       if (IGunOperator.fromLivingEntity((LivingEntity)player).needCheckAmmo()) {
/* 161 */         if (iGun.useDummyAmmo(stack)) {
/*     */           
/* 163 */           cacheInventoryAmmoCount = iGun.getDummyAmmoAmount(stack);
/*     */         } else {
/*     */           
/* 166 */           handleInventoryAmmo(stack, player.m_150109_());
/*     */         } 
/*     */       } else {
/* 169 */         cacheInventoryAmmoCount = 9999;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void handleInventoryAmmo(ItemStack stack, Inventory inventory) {
/* 175 */     cacheInventoryAmmoCount = 0;
/* 176 */     for (int i = 0; i < inventory.m_6643_(); i++) {
/* 177 */       ItemStack inventoryItem = inventory.m_8020_(i);
/* 178 */       Item item = inventoryItem.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item; if (iAmmo.isAmmoOfGun(stack, inventoryItem))
/* 179 */           cacheInventoryAmmoCount += inventoryItem.m_41613_();  }
/*     */       
/* 181 */       item = inventoryItem.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item; if (iAmmoBox.isAmmoBoxOfGun(stack, inventoryItem)) {
/*     */           
/* 183 */           if (iAmmoBox.isAllTypeCreative(inventoryItem) || iAmmoBox.isCreative(inventoryItem)) {
/* 184 */             cacheInventoryAmmoCount = 9999;
/*     */             return;
/*     */           } 
/* 187 */           cacheInventoryAmmoCount += iAmmoBox.getAmmoCount(inventoryItem);
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\overlay\GunHudOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */