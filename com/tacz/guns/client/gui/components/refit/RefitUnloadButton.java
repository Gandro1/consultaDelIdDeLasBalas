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
/*    */ public class RefitUnloadButton
/*    */   extends Button implements IComponentTooltip {
/*    */   public RefitUnloadButton(int pX, int pY, Button.OnPress pOnPress) {
/* 16 */     super(pX, pY, 8, 8, (Component)Component.m_237119_(), pOnPress, f_252438_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_87963_(@Nonnull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
/* 21 */     RenderSystem.disableDepthTest();
/* 22 */     RenderSystem.enableBlend();
/*    */     
/* 24 */     int x = m_252754_(), y = m_252907_();
/* 25 */     if (m_198029_()) {
/* 26 */       graphics.m_280411_(GunRefitScreen.UNLOAD_TEXTURE, x, y, this.f_93618_, this.f_93619_, 0.0F, 0.0F, 80, 80, 160, 80);
/*    */     } else {
/* 28 */       graphics.m_280411_(GunRefitScreen.UNLOAD_TEXTURE, x, y, this.f_93618_, this.f_93619_, 80.0F, 0.0F, 80, 80, 160, 80);
/*    */     } 
/*    */     
/* 31 */     RenderSystem.enableDepthTest();
/* 32 */     RenderSystem.disableBlend();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTooltip(Consumer<List<Component>> consumer) {
/* 37 */     if (m_198029_())
/* 38 */       consumer.accept((List)Collections.singletonList(Component.m_237115_("tooltip.tacz.refit.unload"))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\RefitUnloadButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */