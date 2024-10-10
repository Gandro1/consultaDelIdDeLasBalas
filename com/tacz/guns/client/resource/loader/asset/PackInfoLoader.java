/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PackInfoLoader
/*    */ {
/* 26 */   private static final Marker MARKER = MarkerManager.getMarker("CreativeTabLoader");
/* 27 */   private static final Pattern PACK_INFO_PATTERN = Pattern.compile("^(\\w+)/pack\\.json$");
/*    */ 
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 31 */     Matcher matcher = PACK_INFO_PATTERN.matcher(zipPath);
/* 32 */     if (matcher.find()) {
/* 33 */       String namespace = matcher.group(1);
/* 34 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 35 */       if (entry == null) {
/* 36 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 37 */         return false;
/*    */       }  
/* 39 */       try { InputStream inputStream = zipFile.getInputStream(entry); 
/* 40 */         try { PackInfo packInfo = (PackInfo)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), PackInfo.class);
/* 41 */           ClientAssetManager.INSTANCE.putPackInfo(namespace, packInfo);
/* 42 */           boolean bool = true;
/* 43 */           if (inputStream != null) inputStream.close();  return bool; } catch (Throwable throwable) { if (inputStream != null) try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 44 */       { GunMod.LOGGER.warn(MARKER, "Failed to read info json: {}, entry: {}", zipFile, entry);
/* 45 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void load(File root) {
/* 53 */     Path packInfoFilePath = root.toPath().resolve("pack.json");
/* 54 */     if (Files.isRegularFile(packInfoFilePath, new java.nio.file.LinkOption[0]))
/* 55 */       try { InputStream stream = Files.newInputStream(packInfoFilePath, new java.nio.file.OpenOption[0]); 
/* 56 */         try { PackInfo packInfo = (PackInfo)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), PackInfo.class);
/* 57 */           ClientAssetManager.INSTANCE.putPackInfo(root.getName(), packInfo);
/* 58 */           if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 59 */       { GunMod.LOGGER.warn(MARKER, "Failed to read info json: {}", packInfoFilePath);
/* 60 */         exception.printStackTrace(); }
/*    */        
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\PackInfoLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */