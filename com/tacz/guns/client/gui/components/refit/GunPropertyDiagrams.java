/*     */ package com.tacz.guns.client.gui.components.refit;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.util.AttachmentDataUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public final class GunPropertyDiagrams {
/*     */   public static int getHidePropertyButtonYOffset() {
/*  24 */     int[] startYOffset = { 49 };
/*  25 */     AttachmentPropertyManager.getModifiers().forEach((key, value) -> startYOffset[0] = startYOffset[0] + value.getDiagramsDataSize() * 10);
/*     */ 
/*     */     
/*  28 */     return startYOffset[0];
/*     */   }
/*     */   
/*     */   public static void draw(GuiGraphics graphics, Font font, int x, int y) {
/*  32 */     graphics.m_280509_(x, y, x + 288, y + getHidePropertyButtonYOffset() - 11, -1356717534);
/*     */     
/*  34 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*  35 */     if (player == null) {
/*     */       return;
/*     */     }
/*  38 */     ItemStack gunItem = player.m_21205_();
/*  39 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  40 */     if (iGun == null) {
/*     */       return;
/*     */     }
/*  43 */     AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity((LivingEntity)player).getCacheProperty();
/*  44 */     if (cacheProperty == null) {
/*     */       return;
/*     */     }
/*  47 */     ResourceLocation gunId = iGun.getGunId(gunItem);
/*  48 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(index -> {
/*     */           GunData gunData = index.getGunData();
/*     */           
/*     */           FireMode fireMode = iGun.getFireMode(gunItem);
/*     */           
/*     */           int barStartX = x + 83;
/*     */           
/*     */           int barMaxWidth = 120;
/*     */           
/*     */           int barEndX = barStartX + barMaxWidth;
/*     */           
/*     */           int barBackgroundColor = -16777216;
/*     */           
/*     */           int barBaseColor = -1;
/*     */           
/*     */           int barPositivelyColor = -11141291;
/*     */           
/*     */           int barNegativeColor = -43691;
/*     */           
/*     */           int fontColor = 13421772;
/*     */           
/*     */           int nameTextStartX = x + 5;
/*     */           
/*     */           int valueTextStartX = x + 210;
/*     */           int[] yOffset = { y + 5 };
/*     */           MutableComponent fireModeText = Component.m_237115_("gui.tacz.gun_refit.property_diagrams.fire_mode");
/*     */           if (fireMode == FireMode.AUTO) {
/*     */             fireModeText.m_7220_((Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.auto"));
/*     */           } else if (fireMode == FireMode.SEMI) {
/*     */             fireModeText.m_7220_((Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.semi"));
/*     */           } else if (fireMode == FireMode.BURST) {
/*     */             fireModeText.m_7220_((Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.burst"));
/*     */           } else {
/*     */             fireModeText.m_7220_((Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.unknown"));
/*     */           } 
/*     */           graphics.m_280614_(font, (Component)fireModeText, nameTextStartX + 12, yOffset[0], fontColor, false);
/*     */           yOffset[0] = yOffset[0] + 10;
/*  85 */           int barrelBulletAmount = (iGun.hasBulletInBarrel(gunItem) && index.getGunData().getBolt() != Bolt.OPEN_BOLT) ? 1 : 0;
/*     */           int ammoAmount = gunData.getAmmoAmount() + barrelBulletAmount;
/*     */           double ammoAmountPercent = Math.min(ammoAmount / 100.0D, 1.0D);
/*     */           int ammoLength = (int)(barStartX + barMaxWidth * ammoAmountPercent);
/*     */           int maxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(gunItem, index.getGunData()) + barrelBulletAmount;
/*     */           int addAmmoCount = Math.max(maxAmmoCount - ammoAmount, 0);
/*     */           int addAmmoCountLength = (int)((barMaxWidth * addAmmoCount) / 100.0D);
/*     */           graphics.m_280614_(font, (Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.ammo_capacity"), nameTextStartX, yOffset[0], fontColor, false);
/*     */           graphics.m_280509_(barStartX, yOffset[0] + 2, barEndX, yOffset[0] + 6, barBackgroundColor);
/*     */           graphics.m_280509_(barStartX, yOffset[0] + 2, ammoLength, yOffset[0] + 6, barBaseColor);
/*     */           if (addAmmoCount > 0) {
/*     */             int barRight = Math.min(ammoLength + addAmmoCountLength, barEndX);
/*     */             graphics.m_280509_(ammoLength, yOffset[0] + 2, barRight, yOffset[0] + 6, barPositivelyColor);
/*     */             graphics.m_280056_(font, String.format("%d §a(+%d)", new Object[] { Integer.valueOf(ammoAmount), Integer.valueOf(addAmmoCount) }), valueTextStartX, yOffset[0], fontColor, false);
/*     */           } else {
/*     */             graphics.m_280056_(font, String.format("%d", new Object[] { Integer.valueOf(ammoAmount) }), valueTextStartX, yOffset[0], fontColor, false);
/*     */           } 
/*     */           yOffset[0] = yOffset[0] + 10;
/*     */           float aimTime = gunData.getAimTime();
/*     */           float sprintTime = gunData.getSprintTime();
/*     */           double sprintTimePercent = Mth.m_14036_(aimTime, 0.0F, 1.0F);
/*     */           int sprintLength = (int)(barStartX + barMaxWidth * sprintTimePercent);
/*     */           String sprintValueText = String.format("%.2fs", new Object[] { Float.valueOf(sprintTime) });
/*     */           graphics.m_280614_(font, (Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.sprint_time"), nameTextStartX, yOffset[0], fontColor, false);
/*     */           graphics.m_280509_(barStartX, yOffset[0] + 2, barEndX, yOffset[0] + 6, barBackgroundColor);
/*     */           graphics.m_280509_(barStartX, yOffset[0] + 2, sprintLength, yOffset[0] + 6, barBaseColor);
/*     */           graphics.m_280056_(font, sprintValueText, valueTextStartX, yOffset[0], fontColor, false);
/*     */           yOffset[0] = yOffset[0] + 10;
/*     */           AttachmentPropertyManager.getModifiers().forEach(());
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\GunPropertyDiagrams.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */