/*    */ package com.tacz.guns.client.gui;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.GameNarrator;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.util.ProgressListener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GunPackProgressScreen extends Screen implements ProgressListener {
/*    */   @Nullable
/*    */   private Component header;
/*    */   @Nullable
/*    */   private Component stage;
/*    */   
/*    */   public GunPackProgressScreen() {
/* 21 */     super(GameNarrator.f_93310_);
/*    */   }
/*    */   
/*    */   private int progress;
/*    */   private boolean stop;
/*    */   
/*    */   protected void m_7856_() {
/* 28 */     Button button = Button.m_253074_((Component)Component.m_237115_("gui.tacz.client_gun_pack_downloader.background_download"), b -> m_7730_()).m_252987_((this.f_96543_ - 200) / 2, 120, 200, 20).m_253136_();
/* 29 */     m_142416_((GuiEventListener)button);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_88315_(@NotNull GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
/* 34 */     if (this.stop) {
/* 35 */       getMinecraft().m_91152_(null);
/*    */     } else {
/* 37 */       m_280273_(gui);
/* 38 */       if (this.header != null) {
/* 39 */         gui.m_280653_(this.f_96547_, this.header, this.f_96543_ / 2, 70, 16777215);
/*    */       }
/* 41 */       if (this.stage != null && this.progress > 0) {
/* 42 */         MutableComponent text = this.stage.m_6881_().m_130946_(" " + this.progress + "%");
/* 43 */         gui.m_280653_(this.f_96547_, (Component)text, this.f_96543_ / 2, 90, 16777215);
/*    */       } 
/* 45 */       super.m_88315_(gui, mouseX, mouseY, partialTick);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_6309_(Component component) {
/* 52 */     m_6308_(component);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6308_(Component header) {
/* 57 */     this.header = (Component)Component.m_237115_("gui.tacz.client_gun_pack_downloader.downloading");
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6307_(Component component) {
/* 62 */     this.stage = component;
/* 63 */     m_6952_(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6952_(int progress) {
/* 68 */     this.progress = progress;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7730_() {
/* 73 */     this.stop = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\GunPackProgressScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */