/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.client.download.ClientGunPackDownloadManager;
/*    */ import com.tacz.guns.config.util.HeadShotAABBConfigRead;
/*    */ import com.tacz.guns.config.util.InteractKeyConfigRead;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.config.ModConfigEvent;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class LoadingConfigEvent
/*    */ {
/*    */   private static final String CONFIG_NAME = "tacz-server.toml";
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onLoadingConfig(ModConfigEvent.Loading event) {
/* 21 */     String fileName = event.getConfig().getFileName();
/* 22 */     if ("tacz-server.toml".equals(fileName)) {
/* 23 */       HeadShotAABBConfigRead.init();
/* 24 */       InteractKeyConfigRead.init();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onReloadingConfig(ModConfigEvent.Reloading event) {
/* 33 */     String fileName = event.getConfig().getFileName();
/* 34 */     if ("tacz-server.toml".equals(fileName)) {
/* 35 */       HeadShotAABBConfigRead.init();
/* 36 */       InteractKeyConfigRead.init();
/* 37 */       DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientGunPackDownloadManager::downloadClientGunPack);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\LoadingConfigEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */