/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGuiOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class PreventsHotbarEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRenderHotbarEvent(RenderGuiOverlayEvent.Pre event) {
/* 17 */     Screen screen = (Minecraft.m_91087_()).f_91080_;
/*    */     
/* 19 */     if (screen instanceof com.tacz.guns.client.gui.GunSmithTableScreen) {
/* 20 */       event.setCanceled(true);
/*    */       
/*    */       return;
/*    */     } 
/* 24 */     if (screen instanceof com.tacz.guns.client.gui.GunRefitScreen)
/* 25 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\PreventsHotbarEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */