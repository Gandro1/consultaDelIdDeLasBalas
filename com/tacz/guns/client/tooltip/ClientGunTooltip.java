/*     */ package com.tacz.guns.client.tooltip;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.builder.AmmoItemBuilder;
/*     */ import com.tacz.guns.client.input.RefitKey;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.AmmoCountStyle;
/*     */ import com.tacz.guns.config.sync.SyncConfig;
/*     */ import com.tacz.guns.inventory.tooltip.GunTooltip;
/*     */ import com.tacz.guns.item.GunTooltipPart;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.util.AttachmentDataUtils;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.FormattedText;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.FormattedCharSequence;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public class ClientGunTooltip implements ClientTooltipComponent {
/*  37 */   private static final DecimalFormat FORMAT = new DecimalFormat("#.##%");
/*  38 */   private static final DecimalFormat FORMAT_P_D1 = new DecimalFormat("#.#%");
/*  39 */   private static final DecimalFormat DAMAGE_FORMAT = new DecimalFormat("#.##");
/*  40 */   private static final DecimalFormat CURRENT_AMMO_FORMAT_PERCENT = new DecimalFormat("0%"); private final ItemStack gun; private final IGun iGun;
/*     */   private final CommonGunIndex gunIndex;
/*     */   @Nullable
/*     */   private final ClientGunIndex clientGunIndex;
/*     */   private final ItemStack ammo;
/*     */   @Nullable
/*     */   private List<FormattedCharSequence> desc;
/*     */   private Component ammoName;
/*     */   private MutableComponent ammoCountText;
/*     */   @Nullable
/*     */   private MutableComponent gunType;
/*     */   private MutableComponent damage;
/*     */   private MutableComponent armorIgnore;
/*     */   private MutableComponent headShotMultiplier;
/*     */   private MutableComponent weight;
/*     */   private MutableComponent tips;
/*     */   private MutableComponent levelInfo;
/*     */   @Nullable
/*     */   private MutableComponent packInfo;
/*     */   private int maxWidth;
/*     */   
/*     */   public ClientGunTooltip(GunTooltip tooltip) {
/*  62 */     this.gun = tooltip.getGun();
/*  63 */     this.iGun = tooltip.getIGun();
/*  64 */     ResourceLocation ammoId = tooltip.getAmmoId();
/*  65 */     this.gunIndex = tooltip.getGunIndex();
/*  66 */     this.clientGunIndex = TimelessAPI.getClientGunIndex(this.iGun.getGunId(this.gun)).orElse(null);
/*  67 */     this.ammo = AmmoItemBuilder.create().setId(ammoId).build();
/*  68 */     this.maxWidth = 0;
/*  69 */     getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142103_() {
/*  74 */     int height = 0;
/*  75 */     if (shouldShow(GunTooltipPart.DESCRIPTION) && this.desc != null) {
/*  76 */       height += 10 * this.desc.size() + 2;
/*     */     }
/*  78 */     if (shouldShow(GunTooltipPart.AMMO_INFO)) {
/*  79 */       height += 24;
/*     */     }
/*  81 */     if (shouldShow(GunTooltipPart.BASE_INFO)) {
/*  82 */       height += 34;
/*     */     }
/*  84 */     if (shouldShow(GunTooltipPart.EXTRA_DAMAGE_INFO)) {
/*  85 */       height += 34;
/*     */     }
/*  87 */     if (shouldShow(GunTooltipPart.UPGRADES_TIP)) {
/*  88 */       height += 14;
/*     */     }
/*  90 */     if (shouldShow(GunTooltipPart.PACK_INFO)) {
/*  91 */       height += 14;
/*     */     }
/*  93 */     return height;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142069_(Font font) {
/*  98 */     return this.maxWidth;
/*     */   }
/*     */   
/*     */   private void getText() {
/* 102 */     Font font = (Minecraft.m_91087_()).f_91062_;
/* 103 */     BulletData bulletData = this.gunIndex.getBulletData();
/* 104 */     GunData gunData = this.gunIndex.getGunData();
/*     */     
/* 106 */     if (shouldShow(GunTooltipPart.DESCRIPTION)) {
/* 107 */       String tooltip = this.gunIndex.getPojo().getTooltip();
/* 108 */       if (tooltip != null) {
/* 109 */         List<FormattedCharSequence> split = font.m_92923_((FormattedText)Component.m_237115_(tooltip), 300);
/* 110 */         if (split.size() > 3) {
/* 111 */           this.desc = split.subList(0, 3);
/*     */         } else {
/* 113 */           this.desc = split;
/*     */         } 
/* 115 */         for (FormattedCharSequence sequence : this.desc) {
/* 116 */           this.maxWidth = Math.max(font.m_92724_(sequence), this.maxWidth);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 122 */     if (shouldShow(GunTooltipPart.AMMO_INFO)) {
/* 123 */       this.ammoName = this.ammo.m_41786_();
/* 124 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.ammoName) + 22, this.maxWidth);
/*     */       
/* 126 */       int barrelBulletAmount = (this.iGun.hasBulletInBarrel(this.gun) && this.gunIndex.getGunData().getBolt() != Bolt.OPEN_BOLT) ? 1 : 0;
/* 127 */       int maxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(this.gun, this.gunIndex.getGunData()) + barrelBulletAmount;
/* 128 */       int currentAmmoCount = this.iGun.getCurrentAmmoCount(this.gun) + barrelBulletAmount;
/*     */       
/* 130 */       if (!this.iGun.useDummyAmmo(this.gun)) {
/* 131 */         if (this.clientGunIndex != null && this.clientGunIndex.getAmmoCountStyle() == AmmoCountStyle.PERCENT) {
/* 132 */           this.ammoCountText = Component.m_237113_(CURRENT_AMMO_FORMAT_PERCENT.format((currentAmmoCount / ((maxAmmoCount == 0) ? 1.0F : maxAmmoCount))));
/*     */         } else {
/* 134 */           this.ammoCountText = Component.m_237113_("%d/%d".formatted(new Object[] { Integer.valueOf(currentAmmoCount), Integer.valueOf(maxAmmoCount) }));
/*     */         } 
/*     */       } else {
/* 137 */         int dummyAmmoAmount = this.iGun.getDummyAmmoAmount(this.gun);
/* 138 */         if (this.clientGunIndex != null && this.clientGunIndex.getAmmoCountStyle() == AmmoCountStyle.PERCENT) {
/* 139 */           String p = CURRENT_AMMO_FORMAT_PERCENT.format((currentAmmoCount / ((maxAmmoCount == 0) ? 1.0F : maxAmmoCount)));
/* 140 */           this.ammoCountText = Component.m_237113_("%s (%d)".formatted(new Object[] { p, Integer.valueOf(dummyAmmoAmount) }));
/*     */         } else {
/* 142 */           this.ammoCountText = Component.m_237113_("%d/%d (%d)".formatted(new Object[] { Integer.valueOf(currentAmmoCount), Integer.valueOf(maxAmmoCount), Integer.valueOf(dummyAmmoAmount) }));
/*     */         } 
/*     */       } 
/*     */       
/* 146 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.ammoCountText) + 22, this.maxWidth);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     if (shouldShow(GunTooltipPart.BASE_INFO)) {
/* 151 */       int expToNextLevel = this.iGun.getExpToNextLevel(this.gun);
/* 152 */       int expCurrentLevel = this.iGun.getExpCurrentLevel(this.gun);
/* 153 */       int level = this.iGun.getLevel(this.gun);
/* 154 */       if (level >= this.iGun.getMaxLevel()) {
/* 155 */         String levelText = String.format("%d (MAX)", new Object[] { Integer.valueOf(level) });
/* 156 */         this.levelInfo = Component.m_237115_("tooltip.tacz.gun.level").m_7220_((Component)Component.m_237113_(levelText).m_130940_(ChatFormatting.DARK_PURPLE));
/*     */       } else {
/* 158 */         String levelText = String.format("%d (%.1f%%)", new Object[] { Integer.valueOf(level), Float.valueOf((expCurrentLevel / (expToNextLevel + expCurrentLevel)) * 100.0F) });
/* 159 */         this.levelInfo = Component.m_237115_("tooltip.tacz.gun.level").m_7220_((Component)Component.m_237113_(levelText).m_130940_(ChatFormatting.YELLOW));
/*     */       } 
/* 161 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.levelInfo), this.maxWidth);
/*     */       
/* 163 */       String tabKey = "tacz.type." + this.gunIndex.getType() + ".name";
/* 164 */       this.gunType = Component.m_237115_("tooltip.tacz.gun.type").m_7220_((Component)Component.m_237115_(tabKey).m_130940_(ChatFormatting.AQUA));
/* 165 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.gunType), this.maxWidth);
/*     */       
/* 167 */       double damage = AttachmentDataUtils.getDamageWithAttachment(this.gun, gunData);
/* 168 */       MutableComponent value = Component.m_237113_(DAMAGE_FORMAT.format(damage)).m_130940_(ChatFormatting.AQUA);
/* 169 */       if (bulletData.getExplosionData() != null && (AttachmentDataUtils.isExplodeEnabled(this.gun, gunData) || bulletData.getExplosionData().isExplode())) {
/* 170 */         value.m_130946_(" + ").m_130946_(DAMAGE_FORMAT.format(bulletData.getExplosionData().getDamage() * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue())).m_7220_((Component)Component.m_237115_("tooltip.tacz.gun.explosion"));
/*     */       }
/* 172 */       this.damage = Component.m_237115_("tooltip.tacz.gun.damage").m_7220_((Component)value);
/* 173 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.damage), this.maxWidth);
/*     */     } 
/*     */ 
/*     */     
/* 177 */     if (shouldShow(GunTooltipPart.EXTRA_DAMAGE_INFO)) {
/* 178 */       ExtraDamage extraDamage = bulletData.getExtraDamage();
/* 179 */       if (extraDamage != null) {
/* 180 */         double armorDamagePercent = AttachmentDataUtils.getArmorIgnoreWithAttachment(this.gun, gunData);
/* 181 */         double headShotMultiplierPercent = AttachmentDataUtils.getHeadshotMultiplier(this.gun, gunData);
/*     */         
/* 183 */         armorDamagePercent = Mth.m_14008_(armorDamagePercent, 0.0D, 1.0D);
/*     */         
/* 185 */         this.armorIgnore = Component.m_237110_("tooltip.tacz.gun.armor_ignore", new Object[] { FORMAT.format(armorDamagePercent) });
/* 186 */         this.headShotMultiplier = Component.m_237110_("tooltip.tacz.gun.head_shot_multiplier", new Object[] { FORMAT.format(headShotMultiplierPercent) });
/*     */       } else {
/* 188 */         this.armorIgnore = Component.m_237110_("tooltip.tacz.gun.armor_ignore", new Object[] { FORMAT.format(0L) });
/* 189 */         this.headShotMultiplier = Component.m_237110_("tooltip.tacz.gun.head_shot_multiplier", new Object[] { FORMAT.format(1L) });
/*     */       } 
/*     */       
/* 192 */       double weightFactor = ((Double)SyncConfig.WEIGHT_SPEED_MULTIPLIER.get()).doubleValue();
/* 193 */       double weight = AttachmentDataUtils.getWightWithAttachment(this.gun, gunData);
/* 194 */       this.weight = Component.m_237110_("tooltip.tacz.gun.movement_speed", new Object[] { FORMAT_P_D1.format(-weightFactor * weight) }).m_130940_(ChatFormatting.RED);
/*     */       
/* 196 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.armorIgnore), this.maxWidth);
/* 197 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.headShotMultiplier), this.maxWidth);
/* 198 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.weight), this.maxWidth);
/*     */     } 
/*     */ 
/*     */     
/* 202 */     if (shouldShow(GunTooltipPart.UPGRADES_TIP)) {
/* 203 */       String keyName = Component.m_237117_(RefitKey.REFIT_KEY.m_90860_()).getString().toUpperCase(Locale.ENGLISH);
/* 204 */       this.tips = Component.m_237110_("tooltip.tacz.gun.tips", new Object[] { keyName }).m_130940_(ChatFormatting.YELLOW).m_130940_(ChatFormatting.ITALIC);
/* 205 */       this.maxWidth = Math.max(font.m_92852_((FormattedText)this.tips), this.maxWidth);
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (shouldShow(GunTooltipPart.PACK_INFO)) {
/* 210 */       ResourceLocation gunId = this.iGun.getGunId(this.gun);
/* 211 */       PackInfo packInfoObject = ClientAssetManager.INSTANCE.getPackInfo(gunId);
/* 212 */       if (packInfoObject != null) {
/* 213 */         this.packInfo = Component.m_237115_(packInfoObject.getName()).m_130940_(ChatFormatting.BLUE).m_130940_(ChatFormatting.ITALIC);
/* 214 */         this.maxWidth = Math.max(font.m_92852_((FormattedText)this.packInfo), this.maxWidth);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142440_(Font font, int pX, int pY, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
/* 221 */     int yOffset = pY;
/*     */     
/* 223 */     if (shouldShow(GunTooltipPart.DESCRIPTION) && this.desc != null) {
/* 224 */       yOffset += 2;
/* 225 */       for (FormattedCharSequence sequence : this.desc) {
/* 226 */         font.m_272191_(sequence, pX, yOffset, 11184810, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 227 */         yOffset += 10;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 232 */     if (shouldShow(GunTooltipPart.AMMO_INFO)) {
/* 233 */       yOffset += 4;
/*     */ 
/*     */       
/* 236 */       font.m_272077_(this.ammoName, (pX + 20), yOffset, 16755200, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/*     */ 
/*     */       
/* 239 */       font.m_272077_((Component)this.ammoCountText, (pX + 20), (yOffset + 10), 7829367, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/*     */       
/* 241 */       yOffset += 20;
/*     */     } 
/*     */ 
/*     */     
/* 245 */     if (shouldShow(GunTooltipPart.BASE_INFO)) {
/* 246 */       yOffset += 4;
/*     */ 
/*     */       
/* 249 */       font.m_272077_((Component)this.levelInfo, pX, yOffset, 7829367, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 250 */       yOffset += 10;
/*     */ 
/*     */       
/* 253 */       if (this.gunType != null) {
/* 254 */         font.m_272077_((Component)this.gunType, pX, yOffset, 7829367, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 255 */         yOffset += 10;
/*     */       } 
/*     */ 
/*     */       
/* 259 */       font.m_272077_((Component)this.damage, pX, yOffset, 7829367, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 260 */       yOffset += 10;
/*     */     } 
/*     */ 
/*     */     
/* 264 */     if (shouldShow(GunTooltipPart.EXTRA_DAMAGE_INFO)) {
/* 265 */       yOffset += 4;
/*     */ 
/*     */       
/* 268 */       font.m_272077_((Component)this.armorIgnore, pX, yOffset, 16755200, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 269 */       yOffset += 10;
/*     */ 
/*     */       
/* 272 */       font.m_272077_((Component)this.headShotMultiplier, pX, yOffset, 16755200, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 273 */       yOffset += 10;
/*     */       
/* 275 */       font.m_272077_((Component)this.weight, pX, yOffset, 16777215, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 276 */       yOffset += 10;
/*     */     } 
/*     */ 
/*     */     
/* 280 */     if (shouldShow(GunTooltipPart.UPGRADES_TIP)) {
/* 281 */       yOffset += 4;
/*     */ 
/*     */       
/* 284 */       font.m_272077_((Component)this.tips, pX, yOffset, 16777215, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 285 */       yOffset += 10;
/*     */     } 
/*     */ 
/*     */     
/* 289 */     if (shouldShow(GunTooltipPart.PACK_INFO))
/*     */     {
/* 291 */       if (this.packInfo != null) {
/* 292 */         yOffset += 4;
/* 293 */         font.m_272077_((Component)this.packInfo, pX, yOffset, 16777215, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_183452_(Font pFont, int pX, int pY, GuiGraphics guiGraphics) {
/* 300 */     IGun iGun = IGun.getIGunOrNull(this.gun);
/* 301 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 304 */     if (shouldShow(GunTooltipPart.AMMO_INFO)) {
/* 305 */       int yOffset = pY;
/* 306 */       if (shouldShow(GunTooltipPart.DESCRIPTION) && this.desc != null) {
/* 307 */         yOffset += this.desc.size() * 10 + 2;
/*     */       }
/* 309 */       guiGraphics.m_280480_(this.ammo, pX, yOffset + 4);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean shouldShow(GunTooltipPart part) {
/* 314 */     return ((GunTooltipPart.getHideFlags(this.gun) & part.getMask()) == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\tooltip\ClientGunTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */