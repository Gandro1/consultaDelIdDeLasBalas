/*    */ package com.tacz.guns.client.gui.components;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class FlatColorButton
/*    */   extends Button {
/*    */   private boolean isSelect = false;
/*    */   private List<Component> tooltips;
/*    */   
/*    */   public FlatColorButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, Button.OnPress pOnPress) {
/* 18 */     super(pX, pY, pWidth, pHeight, pMessage, pOnPress, f_252438_);
/*    */   }
/*    */   
/*    */   public FlatColorButton setTooltips(String key) {
/* 22 */     this.tooltips = Collections.singletonList(Component.m_237115_(key));
/* 23 */     return this;
/*    */   }
/*    */   
/*    */   public FlatColorButton setTooltips(List<Component> tooltips) {
/* 27 */     this.tooltips = tooltips;
/* 28 */     return this;
/*    */   }
/*    */   
/*    */   public FlatColorButton setTooltips(Component... tooltips) {
/* 32 */     this.tooltips = List.of(tooltips);
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   public void renderToolTip(GuiGraphics graphics, Screen screen, int pMouseX, int pMouseY) {
/* 37 */     if (this.f_93622_ && this.tooltips != null) {
/* 38 */       graphics.m_280666_((screen.getMinecraft()).f_91062_, this.tooltips, pMouseX, pMouseY);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_87963_(GuiGraphics graphics, int mouseX, int mouseY, float pPartialTick) {
/* 44 */     Minecraft minecraft = Minecraft.m_91087_();
/* 45 */     Font font = minecraft.f_91062_;
/* 46 */     if (this.isSelect) {
/* 47 */       graphics.m_280024_(m_252754_(), m_252907_(), m_252754_() + this.f_93618_, m_252907_() + this.f_93619_, -1356717534, -1356717534);
/*    */     } else {
/* 49 */       graphics.m_280024_(m_252754_(), m_252907_(), m_252754_() + this.f_93618_, m_252907_() + this.f_93619_, -1356717534, -1356717534);
/*    */     } 
/* 51 */     if (m_198029_()) {
/* 52 */       graphics.m_280024_(m_252754_(), m_252907_() + 1, m_252754_() + 1, m_252907_() + this.f_93619_ - 1, -790560, -790560);
/* 53 */       graphics.m_280024_(m_252754_(), m_252907_(), m_252754_() + this.f_93618_, m_252907_() + 1, -790560, -790560);
/* 54 */       graphics.m_280024_(m_252754_() + this.f_93618_ - 1, m_252907_() + 1, m_252754_() + this.f_93618_, m_252907_() + this.f_93619_ - 1, -790560, -790560);
/* 55 */       graphics.m_280024_(m_252754_(), m_252907_() + this.f_93619_ - 1, m_252754_() + this.f_93618_, m_252907_() + this.f_93619_, -790560, -790560);
/*    */     } 
/* 57 */     m_280372_(graphics, font, 2, 15986656);
/* 58 */     renderToolTip(graphics, minecraft.f_91080_, mouseX, mouseY);
/*    */   }
/*    */   
/*    */   public void setSelect(boolean select) {
/* 62 */     this.isSelect = select;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\FlatColorButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */