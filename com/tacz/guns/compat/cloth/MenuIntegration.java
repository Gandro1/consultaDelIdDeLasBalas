/*    */ package com.tacz.guns.compat.cloth;
/*    */ 
/*    */ import com.tacz.guns.compat.cloth.client.KeyClothConfig;
/*    */ import com.tacz.guns.compat.cloth.client.RenderClothConfig;
/*    */ import com.tacz.guns.compat.cloth.client.ZoomClothConfig;
/*    */ import com.tacz.guns.compat.cloth.common.AmmoClothConfig;
/*    */ import com.tacz.guns.compat.cloth.common.GunClothConfig;
/*    */ import com.tacz.guns.compat.cloth.common.OtherClothConfig;
/*    */ import javax.annotation.Nullable;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraftforge.client.ConfigScreenHandler;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ 
/*    */ public class MenuIntegration {
/*    */   public static ConfigBuilder getConfigBuilder() {
/* 20 */     ConfigBuilder root = ConfigBuilder.create().setTitle((Component)Component.m_237113_("Timeless and Classics Guns"));
/* 21 */     root.setGlobalized(true);
/* 22 */     root.setGlobalizedExpanded(false);
/* 23 */     ConfigEntryBuilder entryBuilder = root.entryBuilder();
/*    */     
/* 25 */     KeyClothConfig.init(root, entryBuilder);
/* 26 */     RenderClothConfig.init(root, entryBuilder);
/* 27 */     ZoomClothConfig.init(root, entryBuilder);
/*    */     
/* 29 */     GunClothConfig.init(root, entryBuilder);
/* 30 */     AmmoClothConfig.init(root, entryBuilder);
/* 31 */     OtherClothConfig.init(root, entryBuilder);
/*    */     
/* 33 */     return root;
/*    */   }
/*    */   
/*    */   public static void registerModsPage() {
/* 37 */     ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(()));
/*    */   }
/*    */ 
/*    */   
/*    */   public static Screen getConfigScreen(@Nullable Screen parent) {
/* 42 */     return getConfigBuilder().setParentScreen(parent).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\MenuIntegration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */