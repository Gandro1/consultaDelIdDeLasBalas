/*    */ package com.tacz.guns.client.download;
/*    */ 
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import java.io.File;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.regex.Pattern;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public final class ClientGunPackDownloadManager
/*    */ {
/* 16 */   private static final Path DOWNLOAD_DIR_PATH = Paths.get("config", new String[] { "tacz", "server", "download" });
/* 17 */   private static final Pattern SHA1 = Pattern.compile("^[a-fA-F0-9]{40}$");
/* 18 */   private static final ClientGunPackDownloader DOWNLOADER = new ClientGunPackDownloader(DOWNLOAD_DIR_PATH);
/*    */   
/*    */   public static void init() {
/* 21 */     createFolder();
/*    */   }
/*    */   
/*    */   public static void downloadClientGunPack() {
/* 25 */     List<List<String>> download = (List<List<String>>)SyncConfig.CLIENT_GUN_PACK_DOWNLOAD_URLS.get();
/* 26 */     download.forEach(data -> {
/*    */           String url = data.get(0);
/*    */           String sha1 = data.get(1);
/*    */           if (StringUtils.isBlank(url)) {
/*    */             return;
/*    */           }
/*    */           if (!SHA1.matcher(sha1).matches()) {
/*    */             return;
/*    */           }
/*    */           download(url, sha1);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public static void download(String url, String hash) {
/* 41 */     String lowerCaseHash = hash.toLowerCase(Locale.US);
/* 42 */     DOWNLOADER.downloadAndLoadGunPack(url, lowerCaseHash).thenRun(() -> {
/*    */         
/* 44 */         }).exceptionally(throwable -> null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void createFolder() {
/* 51 */     File folder = DOWNLOAD_DIR_PATH.toFile();
/* 52 */     if (!folder.isDirectory())
/*    */       try {
/* 54 */         Files.createDirectories(folder.toPath(), (FileAttribute<?>[])new FileAttribute[0]);
/* 55 */       } catch (Exception e) {
/* 56 */         e.printStackTrace();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\download\ClientGunPackDownloadManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */