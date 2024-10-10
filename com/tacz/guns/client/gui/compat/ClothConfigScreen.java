/*    */ package com.tacz.guns.client.gui.compat;
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.components.MultiLineLabel;
/*    */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*    */ import net.minecraft.client.gui.screens.ConfirmLinkScreen;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.CommonComponents;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.FormattedText;
/*    */ import net.minecraftforge.client.ConfigScreenHandler;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ClothConfigScreen extends Screen {
/*    */   public static final String CLOTH_CONFIG_URL = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";
/*    */   private final Screen lastScreen;
/* 22 */   private MultiLineLabel message = MultiLineLabel.f_94331_;
/*    */   
/*    */   protected ClothConfigScreen(Screen lastScreen) {
/* 25 */     super((Component)Component.m_237113_("Cloth Config API"));
/* 26 */     this.lastScreen = lastScreen;
/*    */   }
/*    */   
/*    */   public static void registerNoClothConfigPage() {
/* 30 */     if (!ModList.get().isLoaded("cloth_config")) {
/* 31 */       ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_7856_() {
/* 38 */     int posX = (this.f_96543_ - 200) / 2;
/* 39 */     int posY = this.f_96544_ / 2;
/* 40 */     this.message = MultiLineLabel.m_94341_(this.f_96547_, (FormattedText)Component.m_237115_("gui.tacz.cloth_config_warning.tips"), 300);
/* 41 */     m_142416_(
/* 42 */         (GuiEventListener)Button.m_253074_((Component)Component.m_237115_("gui.tacz.cloth_config_warning.download"), b -> openUrl("https://www.curseforge.com/minecraft/mc-mods/cloth-config"))
/* 43 */         .m_252987_(posX, posY - 15, 200, 20).m_253136_());
/*    */     
/* 45 */     m_142416_(
/* 46 */         (GuiEventListener)Button.m_253074_(CommonComponents.f_130660_, b -> Minecraft.m_91087_().m_91152_(this.lastScreen))
/* 47 */         .m_252987_(posX, posY + 50, 200, 20).m_253136_());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_88315_(@NotNull GuiGraphics gui, int pMouseX, int pMouseY, float pPartialTick) {
/* 53 */     m_280273_(gui);
/* 54 */     this.message.m_6276_(gui, this.f_96543_ / 2, 80);
/* 55 */     super.m_88315_(gui, pMouseX, pMouseY, pPartialTick);
/*    */   }
/*    */   
/*    */   private void openUrl(String url) {
/* 59 */     if (StringUtils.isNotBlank(url) && this.f_96541_ != null)
/* 60 */       this.f_96541_.m_91152_((Screen)new ConfirmLinkScreen(yes -> { if (yes) Util.m_137581_().m_137646_(url);  this.f_96541_.m_91152_(this); }url, true)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\compat\ClothConfigScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */