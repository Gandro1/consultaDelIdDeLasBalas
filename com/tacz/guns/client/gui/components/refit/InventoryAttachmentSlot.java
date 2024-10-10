/*    */ package com.tacz.guns.client.gui.components.refit;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.tacz.guns.client.gui.GunRefitScreen;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class InventoryAttachmentSlot
/*    */   extends Button implements IStackTooltip {
/*    */   private final int slotIndex;
/*    */   private final Inventory inventory;
/*    */   
/*    */   public InventoryAttachmentSlot(int pX, int pY, int slotIndex, Inventory inventory, Button.OnPress onPress) {
/* 19 */     super(pX, pY, 18, 18, (Component)Component.m_237119_(), onPress, f_252438_);
/* 20 */     this.slotIndex = slotIndex;
/* 21 */     this.inventory = inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTooltip(Consumer<ItemStack> consumer) {
/* 26 */     if (m_198029_() && 0 <= this.slotIndex && this.slotIndex < this.inventory.m_6643_()) {
/* 27 */       ItemStack item = this.inventory.m_8020_(this.slotIndex);
/* 28 */       consumer.accept(item);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_87963_(@Nonnull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
/* 34 */     RenderSystem.disableDepthTest();
/* 35 */     RenderSystem.enableBlend();
/*    */     
/* 37 */     int x = m_252754_(), y = m_252907_();
/* 38 */     if (m_198029_()) {
/* 39 */       graphics.m_280163_(GunRefitScreen.SLOT_TEXTURE, x, y, 0.0F, 0.0F, this.f_93618_, this.f_93619_, 18, 18);
/*    */     } else {
/* 41 */       graphics.m_280163_(GunRefitScreen.SLOT_TEXTURE, x + 1, y + 1, 1.0F, 1.0F, this.f_93618_ - 2, this.f_93619_ - 2, 18, 18);
/*    */     } 
/* 43 */     graphics.m_280480_(this.inventory.m_8020_(this.slotIndex), x + 1, y + 1);
/*    */     
/* 45 */     RenderSystem.enableDepthTest();
/* 46 */     RenderSystem.disableBlend();
/*    */   }
/*    */   
/*    */   public int getSlotIndex() {
/* 50 */     return this.slotIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\InventoryAttachmentSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */