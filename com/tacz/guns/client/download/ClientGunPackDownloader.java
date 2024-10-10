/*     */ package com.tacz.guns.client.download;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.tacz.guns.GunMod;
/*     */ import com.tacz.guns.client.gui.GunPackProgressScreen;
/*     */ import com.tacz.guns.client.resource.ClientReloadManager;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionStage;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.SharedConstants;
/*     */ import net.minecraft.WorldVersion;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.User;
/*     */ import net.minecraft.client.gui.screens.ConfirmScreen;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.multiplayer.ClientPacketListener;
/*     */ import net.minecraft.network.chat.CommonComponents;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ import net.minecraft.util.ProgressListener;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class ClientGunPackDownloader {
/*     */   private static final int MAX_FILE_SIZE = 262144000;
/*  38 */   private final ReentrantLock downloadLock = new ReentrantLock(); private final Path serverGunPackPath;
/*     */   @Nullable
/*     */   private CompletableFuture<?> currentDownload;
/*     */   
/*     */   public ClientGunPackDownloader(Path serverGunPackPath) {
/*  43 */     this.serverGunPackPath = serverGunPackPath;
/*     */   }
/*     */   
/*     */   private static Map<String, String> getDownloadHeaders() {
/*  47 */     Map<String, String> map = Maps.newHashMap();
/*  48 */     User user = Minecraft.m_91087_().m_91094_();
/*  49 */     WorldVersion currentVersion = SharedConstants.m_183709_();
/*     */     
/*  51 */     map.put("X-Minecraft-Username", user.m_92546_());
/*  52 */     map.put("X-Minecraft-UUID", user.m_92545_());
/*  53 */     map.put("X-Minecraft-Version", currentVersion.m_132493_());
/*  54 */     map.put("X-Minecraft-Version-ID", currentVersion.m_132492_());
/*  55 */     map.put("X-TACZ-Version", ModList.get().getModFileById("tacz").versionString());
/*  56 */     map.put("User-Agent", "Minecraft Java/" + currentVersion.m_132493_());
/*     */     
/*  58 */     return map;
/*     */   }
/*     */   
/*     */   public CompletableFuture<?> downloadAndLoadGunPack(String plainUrl, String hash) {
/*     */     CompletableFuture<?> resultFuture;
/*  63 */     this.downloadLock.lock();
/*     */     
/*     */     try {
/*     */       CompletableFuture<?> downloadFuture;
/*     */       
/*  68 */       clearDownloadingGunPack();
/*     */       
/*  70 */       File gunPack = this.serverGunPackPath.resolve(hash).toFile();
/*     */       
/*  72 */       removeMismatchFile(hash, gunPack);
/*     */ 
/*     */ 
/*     */       
/*  76 */       if (gunPack.exists()) {
/*  77 */         downloadFuture = CompletableFuture.completedFuture("");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  82 */         GunPackProgressScreen progressScreen = new GunPackProgressScreen();
/*  83 */         Minecraft minecraft = Minecraft.m_91087_();
/*  84 */         minecraft.m_18709_(() -> minecraft.m_91152_((Screen)progressScreen));
/*  85 */         URL url = new URL(plainUrl);
/*  86 */         downloadFuture = HttpUtil.m_216225_(gunPack, url, getDownloadHeaders(), 262144000, (ProgressListener)progressScreen, minecraft.m_91096_());
/*     */       } 
/*     */ 
/*     */       
/*  90 */       this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         .currentDownload = downloadFuture.thenCompose(target -> notMatchHash(hash, gunPack) ? failedFuture(new RuntimeException("Hash check failure for file " + gunPack + ", see log")) : loadClientGunPack(gunPack)).whenComplete((target, throwable) -> afterFail(throwable, gunPack));
/*  99 */       resultFuture = this.currentDownload;
/* 100 */     } catch (MalformedURLException e) {
/* 101 */       throw new RuntimeException(e);
/*     */     } finally {
/* 103 */       this.downloadLock.unlock();
/*     */     } 
/* 105 */     return resultFuture;
/*     */   }
/*     */   
/*     */   public static <T> CompletableFuture<T> failedFuture(Throwable pTerminationException) {
/* 109 */     CompletableFuture<T> completablefuture = new CompletableFuture<>();
/* 110 */     completablefuture.completeExceptionally(pTerminationException);
/* 111 */     return completablefuture;
/*     */   }
/*     */   
/*     */   private void afterFail(Throwable throwable, File gunPack) {
/* 115 */     if (throwable == null) {
/*     */       return;
/*     */     }
/* 118 */     GunMod.LOGGER.warn("Pack application failed: {}, deleting file {}", throwable.getMessage(), gunPack);
/*     */     try {
/* 120 */       Files.delete(gunPack.toPath());
/* 121 */     } catch (IOException exception) {
/* 122 */       GunMod.LOGGER.warn("Failed to delete file {}: {}", gunPack, exception.getMessage());
/*     */     } 
/* 124 */     Minecraft.m_91087_().execute(() -> displayFailScreen(Minecraft.m_91087_()));
/*     */   }
/*     */   
/*     */   private void displayFailScreen(Minecraft mc) {
/* 128 */     MutableComponent mutableComponent1 = Component.m_237115_("gui.tacz.client_gun_pack_downloader.fail.title");
/* 129 */     MutableComponent mutableComponent2 = Component.m_237115_("gui.tacz.client_gun_pack_downloader.fail.subtitle");
/* 130 */     Component yesButton = CommonComponents.f_130659_;
/* 131 */     MutableComponent mutableComponent3 = Component.m_237115_("menu.disconnect");
/* 132 */     mc.m_91152_((Screen)new ConfirmScreen(button -> { if (button) { mc.m_91152_(null); } else { ClientPacketListener clientpacketlistener = mc.m_91403_(); if (clientpacketlistener != null) clientpacketlistener.m_104910_().m_129507_((Component)Component.m_237115_("connect.aborted"));  }  }(Component)mutableComponent1, (Component)mutableComponent2, yesButton, (Component)mutableComponent3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearDownloadingGunPack() {
/* 145 */     this.downloadLock.lock();
/*     */     try {
/* 147 */       if (this.currentDownload != null) {
/* 148 */         this.currentDownload.cancel(true);
/*     */       }
/* 150 */       this.currentDownload = null;
/*     */     } finally {
/* 152 */       this.downloadLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeMismatchFile(String expectedHash, File file) {
/* 157 */     if (file.exists() && notMatchHash(expectedHash, file))
/*     */       try {
/* 159 */         FileUtils.delete(file);
/* 160 */       } catch (IOException exception) {
/* 161 */         GunMod.LOGGER.warn("Failed to delete file {}: {}", file, exception.getMessage());
/*     */       }  
/*     */   }
/*     */   
/*     */   private boolean notMatchHash(String expectedHash, File file) {
/*     */     
/* 167 */     try { FileInputStream stream = new FileInputStream(file); 
/* 168 */       try { String fileHash = DigestUtils.sha1Hex(stream);
/* 169 */         if (fileHash.toLowerCase(Locale.US).equals(expectedHash.toLowerCase(Locale.US)))
/* 170 */         { GunMod.LOGGER.info("Found file {} matching requested fileHash {}", file, expectedHash);
/* 171 */           boolean bool = false;
/*     */ 
/*     */           
/* 174 */           stream.close(); return bool; }  GunMod.LOGGER.warn("File {} had wrong fileHash (expected {}, found {}).", file, expectedHash, fileHash); stream.close(); } catch (Throwable throwable) { try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ioexception)
/* 175 */     { GunMod.LOGGER.warn("File {} couldn't be hashed.", file, ioexception); }
/*     */     
/* 177 */     return true;
/*     */   }
/*     */   
/*     */   public CompletableFuture<?> loadClientGunPack(File file) {
/* 181 */     ClientReloadManager.loadClientDownloadGunPack(file);
/* 182 */     return CompletableFuture.completedFuture("");
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\download\ClientGunPackDownloader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */