/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import net.minecraft.locale.Language;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ public final class LanguageLoader
/*    */ {
/* 24 */   private static final Marker MARKER = MarkerManager.getMarker("LanguageLoader");
/* 25 */   private static final Pattern LANG_PATTERN = Pattern.compile("^\\w+/lang/(\\w+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 28 */     Matcher matcher = LANG_PATTERN.matcher(zipPath);
/* 29 */     if (matcher.find()) {
/* 30 */       String languageCode = matcher.group(1);
/* 31 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 32 */       if (entry == null) {
/* 33 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 34 */         return false;
/*    */       }  
/* 36 */       try { InputStream zipEntryStream = zipFile.getInputStream(entry); 
/* 37 */         try { Map<String, String> languages = Maps.newHashMap();
/* 38 */           Objects.requireNonNull(languages); Language.m_128108_(zipEntryStream, languages::put);
/* 39 */           ClientAssetManager.INSTANCE.putLanguage(languageCode, languages);
/* 40 */           boolean bool = true;
/* 41 */           if (zipEntryStream != null) zipEntryStream.close();  return bool; } catch (Throwable throwable) { if (zipEntryStream != null) try { zipEntryStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 42 */       { GunMod.LOGGER.warn(MARKER, "Failed to read language file: {}, entry: {}", zipFile, entry);
/* 43 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 50 */     Path filePath = root.toPath().resolve("lang");
/* 51 */     if (!Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/*    */       return;
/*    */     }
/* 54 */     File[] subFiles = filePath.toFile().listFiles((dir, name) -> true);
/* 55 */     if (subFiles == null) {
/*    */       return;
/*    */     }
/* 58 */     for (File file : subFiles) {
/* 59 */       String name = file.getName();
/* 60 */       if (name.endsWith(".json")) {
/*    */ 
/*    */         
/* 63 */         String languageCode = name.substring(0, name.length() - 5); 
/* 64 */         try { InputStream inputStream = Files.newInputStream(file.toPath(), new java.nio.file.OpenOption[0]); 
/* 65 */           try { Map<String, String> languages = Maps.newHashMap();
/* 66 */             Objects.requireNonNull(languages); Language.m_128108_(inputStream, languages::put);
/* 67 */             ClientAssetManager.INSTANCE.putLanguage(languageCode, languages);
/* 68 */             if (inputStream != null) inputStream.close();  } catch (Throwable throwable) { if (inputStream != null) try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 69 */         { GunMod.LOGGER.warn(MARKER, "Failed to read language file: {}", file);
/* 70 */           exception.printStackTrace(); }
/*    */       
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\LanguageLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */