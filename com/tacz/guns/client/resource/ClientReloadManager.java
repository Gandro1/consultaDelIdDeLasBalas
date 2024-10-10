/*    */ package com.tacz.guns.client.resource;
/*    */ import com.tacz.guns.client.download.ClientGunPackDownloadManager;
/*    */ import com.tacz.guns.config.ServerConfig;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import com.tacz.guns.network.message.ServerMessageSyncGunPack;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.VersionChecker;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import java.io.File;
/*    */ import java.util.EnumMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public class ClientReloadManager {
/* 20 */   private static final EnumMap<DataType, Map<ResourceLocation, String>> LOCALE_CACHE = new EnumMap<>(DataType.class);
/*    */ 
/*    */   
/*    */   public static void reloadAllPack() {
/* 24 */     VersionChecker.clearCache();
/* 25 */     ClientGunPackLoader.init();
/*    */     
/* 27 */     CommonGunPackLoader.reloadAsset();
/* 28 */     ClientGunPackLoader.reloadAsset();
/*    */ 
/*    */     
/* 31 */     CommonGunPackLoader.reloadIndex();
/* 32 */     ClientGunPackLoader.reloadIndex();
/*    */     
/* 34 */     CommonGunPackLoader.reloadRecipes();
/*    */     
/* 36 */     Minecraft mc = Minecraft.m_91087_();
/*    */ 
/*    */     
/* 39 */     if (mc.m_91091_() && mc.m_91092_() != null && mc.m_91092_().m_6992_()) {
/* 40 */       CommonGunPackNetwork.syncClientExceptSelf((MinecraftServer)mc.m_91092_(), (Player)(Minecraft.m_91087_()).f_91074_);
/*    */       
/*    */       return;
/*    */     } 
/* 44 */     if (!mc.m_91090_()) {
/* 45 */       if (ServerConfig.SERVER_CONFIG_SPEC != null && ServerConfig.SERVER_CONFIG_SPEC.isLoaded() && !((List)SyncConfig.CLIENT_GUN_PACK_DOWNLOAD_URLS.get()).isEmpty()) {
/* 46 */         ClientGunPackDownloadManager.downloadClientGunPack();
/*    */       }
/* 48 */       if (!LOCALE_CACHE.isEmpty()) {
/* 49 */         CommonGunPackNetwork.loadFromCache(LOCALE_CACHE);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadClientDownloadGunPack(File file) {
/* 55 */     ClientGunPackLoader.readZipAsset(file);
/* 56 */     if (!LOCALE_CACHE.isEmpty()) {
/* 57 */       CommonGunPackNetwork.loadFromCache(LOCALE_CACHE);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void cacheAll(ServerMessageSyncGunPack message) {
/* 62 */     LOCALE_CACHE.clear();
/* 63 */     LOCALE_CACHE.putAll(message.getCache());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\ClientReloadManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */