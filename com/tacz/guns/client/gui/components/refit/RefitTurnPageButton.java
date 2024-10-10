/*    */ package com.tacz.guns.client.gui.components.refit;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.tacz.guns.client.gui.GunRefitScreen;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class RefitTurnPageButton
/*    */   extends Button implements IComponentTooltip {
/*    */   private final boolean isUpPage;
/*    */   
/*    */   public RefitTurnPageButton(int pX, int pY, boolean isUpPage, Button.OnPress pOnPress) {
/* 18 */     super(pX, pY, 18, 8, (Component)Component.m_237119_(), pOnPress, f_252438_);
/* 19 */     this.isUpPage = isUpPage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_87963_(@Nonnull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
/* 24 */     RenderSystem.disableDepthTest();
/* 25 */     RenderSystem.enableBlend();
/*    */     
/* 27 */     int x = m_252754_(), y = m_252907_();
/* 28 */     int yOffset = this.isUpPage ? 0 : 80;
/* 29 */     if (m_198029_()) {
/* 30 */       graphics.m_280411_(GunRefitScreen.TURN_PAGE_TEXTURE, x, y, this.f_93618_, this.f_93619_, 0.0F, yOffset, 180, 80, 180, 160);
/*    */     } else {
/* 32 */       graphics.m_280411_(GunRefitScreen.TURN_PAGE_TEXTURE, x + 1, y + 1, this.f_93618_ - 2, this.f_93619_ - 2, 10.0F, (yOffset + 10), 160, 60, 180, 160);
/*    */     } 
/*    */     
/* 35 */     RenderSystem.enableDepthTest();
/* 36 */     RenderSystem.disableBlend();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTooltip(Consumer<List<Component>> consumer) {
/* 41 */     if (m_198029_()) {
/* 42 */       String key = this.isUpPage ? "tooltip.tacz.page.previous" : "tooltip.tacz.page.next";
/* 43 */       consumer.accept((List)Collections.singletonList(Component.m_237115_(key)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\RefitTurnPageButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */