/*     */ package com.tacz.guns.client.gui;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.client.animation.screen.RefitTransform;
/*     */ import com.tacz.guns.client.gui.components.FlatColorButton;
/*     */ import com.tacz.guns.client.gui.components.refit.GunAttachmentSlot;
/*     */ import com.tacz.guns.client.gui.components.refit.GunPropertyDiagrams;
/*     */ import com.tacz.guns.client.gui.components.refit.InventoryAttachmentSlot;
/*     */ import com.tacz.guns.client.gui.components.refit.RefitTurnPageButton;
/*     */ import com.tacz.guns.client.gui.components.refit.RefitUnloadButton;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessageRefitGun;
/*     */ import com.tacz.guns.network.message.ClientMessageUnloadAttachment;
/*     */ import com.tacz.guns.sound.SoundManager;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.Renderable;
/*     */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*     */ public class GunRefitScreen extends Screen {
/*  30 */   public static final ResourceLocation SLOT_TEXTURE = new ResourceLocation("tacz", "textures/gui/refit_slot.png");
/*  31 */   public static final ResourceLocation TURN_PAGE_TEXTURE = new ResourceLocation("tacz", "textures/gui/refit_turn_page.png");
/*  32 */   public static final ResourceLocation UNLOAD_TEXTURE = new ResourceLocation("tacz", "textures/gui/refit_unload.png");
/*  33 */   public static final ResourceLocation ICONS_TEXTURE = new ResourceLocation("tacz", "textures/gui/refit_slot_icons.png");
/*     */   
/*     */   public static final int ICON_UV_SIZE = 32;
/*     */   
/*     */   public static final int SLOT_SIZE = 18;
/*     */   private static final int INVENTORY_ATTACHMENT_SLOT_COUNT = 8;
/*     */   private static boolean HIDE_GUN_PROPERTY_DIAGRAMS = true;
/*  40 */   private int currentPage = 0;
/*     */   
/*     */   public GunRefitScreen() {
/*  43 */     super((Component)Component.m_237113_("Gun Refit Screen"));
/*  44 */     RefitTransform.init();
/*     */   }
/*     */   
/*     */   public static int getSlotTextureXOffset(ItemStack gunItem, AttachmentType attachmentType) {
/*  48 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  49 */     if (iGun == null) {
/*  50 */       return -1;
/*     */     }
/*  52 */     if (!iGun.allowAttachmentType(gunItem, attachmentType)) {
/*  53 */       return 192;
/*     */     }
/*  55 */     switch (attachmentType) {
/*     */       case GRIP:
/*  57 */         return 0;
/*     */       
/*     */       case LASER:
/*  60 */         return 32;
/*     */       
/*     */       case MUZZLE:
/*  63 */         return 64;
/*     */       
/*     */       case SCOPE:
/*  66 */         return 96;
/*     */       
/*     */       case STOCK:
/*  69 */         return 128;
/*     */       
/*     */       case EXTENDED_MAG:
/*  72 */         return 160;
/*     */     } 
/*     */     
/*  75 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getSlotsTextureWidth() {
/*  79 */     return 224;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7856_() {
/*  84 */     m_169413_();
/*     */     
/*  86 */     addAttachmentTypeButtons();
/*     */     
/*  88 */     addInventoryAttachmentButtons();
/*     */     
/*  90 */     if (HIDE_GUN_PROPERTY_DIAGRAMS) {
/*  91 */       m_142416_((GuiEventListener)new FlatColorButton(11, 11, 288, 16, 
/*  92 */             (Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.show"), b -> switchHideButton()));
/*     */     } else {
/*  94 */       m_142416_((GuiEventListener)(new FlatColorButton(14, 14, 12, 12, (Component)Component.m_237113_("S"), b -> {
/*     */               LocalPlayer player = (Minecraft.m_91087_()).f_91074_; if (player == null || player.m_5833_())
/*     */                 return; 
/*     */               if (IGun.mainhandHoldGun((LivingEntity)player)) {
/*     */                 IClientPlayerGunOperator.fromLocalPlayer(player).fireSelect();
/*     */                 m_7856_();
/*     */               } 
/* 101 */             })).setTooltips(new Component[] { (Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.fire_mode.switch") }));
/* 102 */       int buttonYOffset = GunPropertyDiagrams.getHidePropertyButtonYOffset();
/* 103 */       m_142416_((GuiEventListener)new FlatColorButton(11, buttonYOffset, 288, 12, 
/* 104 */             (Component)Component.m_237115_("gui.tacz.gun_refit.property_diagrams.hide"), b -> switchHideButton()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_88315_(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float pPartialTick) {
/* 110 */     super.m_88315_(graphics, mouseX, mouseY, pPartialTick);
/*     */     
/* 112 */     if (!HIDE_GUN_PROPERTY_DIAGRAMS) {
/* 113 */       GunPropertyDiagrams.draw(graphics, this.f_96547_, 11, 11);
/*     */     }
/*     */     
/* 116 */     this.f_169369_.stream().filter(w -> w instanceof IComponentTooltip).forEach(w -> ((IComponentTooltip)w).renderTooltip(()));
/*     */     
/* 118 */     this.f_169369_.stream().filter(w -> w instanceof IStackTooltip).forEach(w -> ((IStackTooltip)w).renderTooltip(()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7043_() {
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   private void addInventoryAttachmentButtons() {
/* 128 */     LocalPlayer player = (getMinecraft()).f_91074_;
/* 129 */     if (RefitTransform.getCurrentTransformType() == AttachmentType.NONE || player == null) {
/*     */       return;
/*     */     }
/* 132 */     int startX = this.f_96543_ - 30;
/* 133 */     int startY = 50;
/* 134 */     int pageStart = this.currentPage * 8;
/* 135 */     int count = 0;
/* 136 */     int currentY = startY;
/* 137 */     Inventory inventory = player.m_150109_();
/* 138 */     for (int i = 0; i < inventory.m_6643_(); i++) {
/* 139 */       ItemStack inventoryItem = inventory.m_8020_(i);
/* 140 */       IAttachment attachment = IAttachment.getIAttachmentOrNull(inventoryItem);
/* 141 */       IGun iGun = IGun.getIGunOrNull(player.m_21205_());
/* 142 */       if (attachment != null && iGun != null && attachment.getType(inventoryItem) == RefitTransform.getCurrentTransformType() && 
/* 143 */         iGun.allowAttachment(player.m_21205_(), inventoryItem)) {
/*     */ 
/*     */         
/* 146 */         count++;
/* 147 */         if (count > pageStart)
/*     */         {
/*     */           
/* 150 */           if (count <= pageStart + 8) {
/*     */ 
/*     */             
/* 153 */             InventoryAttachmentSlot button = new InventoryAttachmentSlot(startX, currentY, i, inventory, b -> {
/*     */                   int slotIndex = ((InventoryAttachmentSlot)b).getSlotIndex();
/*     */                   SoundPlayManager.playerRefitSound(inventory.m_8020_(slotIndex), player, SoundManager.INSTALL_SOUND);
/*     */                   ClientMessageRefitGun message = new ClientMessageRefitGun(slotIndex, inventory.f_35977_, RefitTransform.getCurrentTransformType());
/*     */                   NetworkHandler.CHANNEL.sendToServer(message);
/*     */                 });
/* 159 */             m_142416_((GuiEventListener)button);
/* 160 */             currentY += 18;
/*     */           }  } 
/*     */       } 
/* 163 */     }  int totalPage = (count - 1) / 8;
/* 164 */     RefitTurnPageButton turnPageButtonUp = new RefitTurnPageButton(startX, startY - 10, true, b -> {
/*     */           if (this.currentPage > 0) {
/*     */             this.currentPage--;
/*     */             m_7856_();
/*     */           } 
/*     */         });
/* 170 */     RefitTurnPageButton turnPageButtonDown = new RefitTurnPageButton(startX, startY + 144 + 2, false, b -> {
/*     */           if (this.currentPage < totalPage) {
/*     */             this.currentPage++;
/*     */             m_7856_();
/*     */           } 
/*     */         });
/* 176 */     if (this.currentPage < totalPage) {
/* 177 */       m_142416_((GuiEventListener)turnPageButtonDown);
/*     */     }
/* 179 */     if (this.currentPage > 0) {
/* 180 */       m_142416_((GuiEventListener)turnPageButtonUp);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addAttachmentTypeButtons() {
/* 185 */     LocalPlayer player = (getMinecraft()).f_91074_;
/* 186 */     if (player == null) {
/*     */       return;
/*     */     }
/* 189 */     IGun iGun = IGun.getIGunOrNull(player.m_21205_());
/* 190 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 193 */     int startX = this.f_96543_ - 30;
/* 194 */     int startY = 10;
/* 195 */     for (AttachmentType type : AttachmentType.values()) {
/* 196 */       if (type != AttachmentType.NONE) {
/*     */ 
/*     */         
/* 199 */         Inventory inventory = player.m_150109_();
/* 200 */         GunAttachmentSlot button = new GunAttachmentSlot(startX, startY, type, inventory.f_35977_, inventory, b -> {
/*     */               AttachmentType buttonType = ((GunAttachmentSlot)b).getType();
/*     */               
/*     */               if (!((GunAttachmentSlot)b).isAllow()) {
/*     */                 if (RefitTransform.changeRefitScreenView(AttachmentType.NONE)) {
/*     */                   m_7856_();
/*     */                 }
/*     */                 
/*     */                 return;
/*     */               } 
/*     */               
/*     */               if (RefitTransform.getCurrentTransformType() == buttonType && buttonType != AttachmentType.NONE) {
/*     */                 if (RefitTransform.changeRefitScreenView(AttachmentType.NONE)) {
/*     */                   m_7856_();
/*     */                 }
/*     */                 return;
/*     */               } 
/*     */               if (RefitTransform.changeRefitScreenView(buttonType)) {
/*     */                 m_7856_();
/*     */               }
/*     */             });
/* 221 */         if (RefitTransform.getCurrentTransformType() == type) {
/* 222 */           button.setSelected(true);
/*     */           
/* 224 */           RefitUnloadButton unloadButton = new RefitUnloadButton(startX + 5, startY + 18 + 2, b -> {
/*     */                 ItemStack attachmentItem = button.getAttachmentItem();
/*     */                 if (!attachmentItem.m_41619_()) {
/*     */                   int freeSlot = inventory.m_36062_();
/*     */                   if (freeSlot != -1) {
/*     */                     SoundPlayManager.playerRefitSound(attachmentItem, player, SoundManager.UNINSTALL_SOUND);
/*     */                     ClientMessageUnloadAttachment message = new ClientMessageUnloadAttachment(inventory.f_35977_, RefitTransform.getCurrentTransformType());
/*     */                     NetworkHandler.CHANNEL.sendToServer(message);
/*     */                   } else {
/*     */                     player.m_213846_((Component)Component.m_237115_("gui.tacz.gun_refit.unload.no_space"));
/*     */                   } 
/*     */                 } 
/*     */               });
/* 237 */           if (!button.getAttachmentItem().m_41619_()) {
/* 238 */             m_142416_((GuiEventListener)unloadButton);
/*     */           }
/*     */         } 
/* 241 */         m_142416_((GuiEventListener)button);
/* 242 */         startX -= 18;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void switchHideButton() {
/* 247 */     HIDE_GUN_PROPERTY_DIAGRAMS = !HIDE_GUN_PROPERTY_DIAGRAMS;
/* 248 */     m_7856_();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\GunRefitScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */