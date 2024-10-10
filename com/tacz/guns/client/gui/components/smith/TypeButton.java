/*    */ package com.tacz.guns.client.gui.components.smith;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TypeButton
/*    */   extends Button {
/* 13 */   private static final ResourceLocation TEXTURE = new ResourceLocation("tacz", "textures/gui/gun_smith_table.png");
/*    */   private final ItemStack stack;
/*    */   private boolean isSelected = false;
/*    */   
/*    */   public TypeButton(int pX, int pY, ItemStack stack, Button.OnPress onPress) {
/* 18 */     super(pX, pY, 24, 25, (Component)Component.m_237119_(), onPress, f_252438_);
/* 19 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_87963_(@NotNull GuiGraphics gui, int pMouseX, int pMouseY, float pPartialTick) {
/* 24 */     RenderSystem.enableDepthTest();
/*    */     
/* 26 */     int vOffset = m_198029_() ? (204 + this.f_93619_) : 204;
/* 27 */     if (this.isSelected) {
/* 28 */       gui.m_280163_(TEXTURE, m_252754_(), m_252907_(), 0.0F, vOffset, this.f_93618_, this.f_93619_, 256, 256);
/*    */     } else {
/* 30 */       gui.m_280163_(TEXTURE, m_252754_(), m_252907_(), 26.0F, vOffset, this.f_93618_, this.f_93619_, 256, 256);
/*    */     } 
/*    */     
/* 33 */     gui.m_280480_(this.stack, m_252754_() + 4, m_252907_() + 5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5691_() {
/* 38 */     this.isSelected = true;
/* 39 */     this.f_93717_.m_93750_(this);
/*    */   }
/*    */   
/*    */   public void setSelected(boolean selected) {
/* 43 */     this.isSelected = selected;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\smith\TypeButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */