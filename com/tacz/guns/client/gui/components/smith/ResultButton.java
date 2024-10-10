/*    */ package com.tacz.guns.client.gui.components.smith;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ResultButton
/*    */   extends Button
/*    */ {
/* 16 */   private static final ResourceLocation TEXTURE = new ResourceLocation("tacz", "textures/gui/gun_smith_table.png");
/*    */   private final ItemStack stack;
/*    */   private boolean isSelected = false;
/*    */   
/*    */   public ResultButton(int pX, int pY, ItemStack stack, Button.OnPress onPress) {
/* 21 */     super(pX, pY, 94, 16, (Component)Component.m_237119_(), onPress, f_252438_);
/* 22 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_87963_(@NotNull GuiGraphics gui, int pMouseX, int pMouseY, float pPartialTick) {
/* 27 */     RenderSystem.enableDepthTest();
/*    */     
/* 29 */     if (this.isSelected) {
/* 30 */       if (m_198029_()) {
/* 31 */         gui.m_280163_(TEXTURE, m_252754_() - 1, m_252907_() - 1, 52.0F, 229.0F, this.f_93618_ + 2, this.f_93619_ + 2, 256, 256);
/*    */       } else {
/* 33 */         gui.m_280163_(TEXTURE, m_252754_(), m_252907_(), 53.0F, 230.0F, this.f_93618_, this.f_93619_, 256, 256);
/*    */       }
/*    */     
/* 36 */     } else if (m_198029_()) {
/* 37 */       gui.m_280163_(TEXTURE, m_252754_() - 1, m_252907_() - 1, 52.0F, 211.0F, this.f_93618_ + 2, this.f_93619_ + 2, 256, 256);
/*    */     } else {
/* 39 */       gui.m_280163_(TEXTURE, m_252754_(), m_252907_(), 53.0F, 212.0F, this.f_93618_, this.f_93619_, 256, 256);
/*    */     } 
/*    */     
/* 42 */     Minecraft mc = Minecraft.m_91087_();
/* 43 */     gui.m_280480_(this.stack, m_252754_() + 1, m_252907_());
/*    */     
/* 45 */     Component hoverName = this.stack.m_41786_();
/* 46 */     m_280138_(gui, mc.f_91062_, hoverName, m_252754_() + 20, m_252907_() + 4, m_252754_() + 92, m_252907_() + 13, 16777215);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5691_() {
/* 51 */     this.isSelected = true;
/* 52 */     this.f_93717_.m_93750_(this);
/*    */   }
/*    */   
/*    */   public void setSelected(boolean selected) {
/* 56 */     this.isSelected = selected;
/*    */   }
/*    */   
/*    */   public void renderTooltips(Consumer<ItemStack> consumer) {
/* 60 */     if (m_198029_() && !this.stack.m_41619_())
/* 61 */       consumer.accept(this.stack); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\smith\ResultButton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */