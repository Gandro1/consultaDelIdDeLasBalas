/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.resource.DedicatedServerReloadManager;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.DEDICATED_SERVER}, modid = "tacz", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class CommonLoadPack {
/*    */   @SubscribeEvent
/*    */   public static void loadGunPack(FMLCommonSetupEvent commonSetupEvent) {
/* 14 */     DedicatedServerReloadManager.loadGunPack();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\CommonLoadPack.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */