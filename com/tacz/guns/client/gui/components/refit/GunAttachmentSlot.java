/*     */ package com.tacz.guns.client.gui.components.refit;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.client.gui.GunRefitScreen;
/*     */ import java.util.Locale;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class GunAttachmentSlot
/*     */   extends Button implements IStackTooltip {
/*     */   private final AttachmentType type;
/*     */   private final Inventory inventory;
/*     */   private final int gunItemIndex;
/*     */   private final String nameKey;
/*     */   private boolean selected = false;
/*  26 */   private ItemStack attachmentItem = ItemStack.f_41583_;
/*     */   
/*     */   public GunAttachmentSlot(int pX, int pY, AttachmentType type, int gunItemIndex, Inventory inventory, Button.OnPress onPress) {
/*  29 */     super(pX, pY, 18, 18, (Component)Component.m_237119_(), onPress, Button.f_252438_);
/*  30 */     this.type = type;
/*  31 */     this.inventory = inventory;
/*  32 */     this.gunItemIndex = gunItemIndex;
/*  33 */     this.nameKey = String.format("tooltip.tacz.attachment.%s", new Object[] { type.name().toLowerCase(Locale.US) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTooltip(Consumer<ItemStack> consumer) {
/*  38 */     if (m_198029_() && !this.attachmentItem.m_41619_()) {
/*  39 */       consumer.accept(this.attachmentItem);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_87963_(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
/*  45 */     if (m_198029_()) {
/*  46 */       Font font = (Minecraft.m_91087_()).f_91062_;
/*  47 */       int yOffset = m_252907_() + 20;
/*  48 */       if (this.selected && !this.attachmentItem.m_41619_()) {
/*  49 */         yOffset = m_252907_() + 30;
/*     */       }
/*  51 */       graphics.m_280653_(font, (Component)Component.m_237115_(this.nameKey), m_252754_() + m_5711_() / 2, yOffset, ChatFormatting.WHITE.m_126665_().intValue());
/*     */     } 
/*  53 */     ItemStack gunItem = this.inventory.m_8020_(this.gunItemIndex);
/*  54 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  55 */     if (iGun == null) {
/*     */       return;
/*     */     }
/*     */     
/*  59 */     RenderSystem.disableDepthTest();
/*  60 */     RenderSystem.enableBlend();
/*     */     
/*  62 */     int x = m_252754_();
/*  63 */     int y = m_252907_();
/*  64 */     if (m_198029_() || this.selected) {
/*  65 */       graphics.m_280163_(GunRefitScreen.SLOT_TEXTURE, x, y, 0.0F, 0.0F, this.f_93618_, this.f_93619_, 18, 18);
/*     */     } else {
/*  67 */       graphics.m_280163_(GunRefitScreen.SLOT_TEXTURE, x + 1, y + 1, 1.0F, 1.0F, this.f_93618_ - 2, this.f_93619_ - 2, 18, 18);
/*     */     } 
/*     */     
/*  70 */     this.attachmentItem = iGun.getAttachment(gunItem, this.type);
/*  71 */     if (!this.attachmentItem.m_41619_()) {
/*  72 */       graphics.m_280480_(this.attachmentItem, x + 1, y + 1);
/*     */     } else {
/*  74 */       int xOffset = GunRefitScreen.getSlotTextureXOffset(gunItem, this.type);
/*  75 */       graphics.m_280411_(GunRefitScreen.ICONS_TEXTURE, x + 2, y + 2, this.f_93618_ - 4, this.f_93619_ - 4, xOffset, 0.0F, 32, 32, GunRefitScreen.getSlotsTextureWidth(), 32);
/*     */     } 
/*     */     
/*  78 */     RenderSystem.enableDepthTest();
/*  79 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   public void setSelected(boolean selected) {
/*  83 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public AttachmentType getType() {
/*  87 */     return this.type;
/*     */   }
/*     */   
/*     */   public ItemStack getAttachmentItem() {
/*  91 */     ItemStack gunItem = this.inventory.m_8020_(this.gunItemIndex);
/*  92 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  93 */     if (iGun == null) {
/*  94 */       return ItemStack.f_41583_;
/*     */     }
/*  96 */     return iGun.getAttachment(gunItem, this.type);
/*     */   }
/*     */   
/*     */   public boolean isAllow() {
/* 100 */     ItemStack gunItem = this.inventory.m_8020_(this.gunItemIndex);
/* 101 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 102 */     if (iGun == null) {
/* 103 */       return false;
/*     */     }
/* 105 */     return iGun.allowAttachmentType(gunItem, this.type);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\GunAttachmentSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */