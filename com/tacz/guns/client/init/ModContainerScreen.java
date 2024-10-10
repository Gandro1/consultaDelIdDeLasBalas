/*    */ package com.tacz.guns.client.init;
/*    */ 
/*    */ import com.tacz.guns.inventory.GunSmithTableMenu;
/*    */ import net.minecraft.client.gui.screens.MenuScreens;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModContainerScreen {
/*    */   @SubscribeEvent
/*    */   public static void clientSetup(FMLClientSetupEvent evt) {
/* 15 */     evt.enqueueWork(() -> MenuScreens.m_96206_(GunSmithTableMenu.TYPE, com.tacz.guns.client.gui.GunSmithTableScreen::new));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\init\ModContainerScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */