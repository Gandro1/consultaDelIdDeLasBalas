/*    */ package com.tacz.guns.resource.loader.asset;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.FileVisitor;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ public final class GunDataLoader
/*    */ {
/* 29 */   private static final Marker MARKER = MarkerManager.getMarker("GunDataLoader");
/* 30 */   private static final Pattern DATA_PATTERN = Pattern.compile("^(\\w+)/guns/data/([\\w/]+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 33 */     Matcher matcher = DATA_PATTERN.matcher(zipPath);
/* 34 */     if (matcher.find()) {
/* 35 */       String namespace = matcher.group(1);
/* 36 */       String path = matcher.group(2);
/* 37 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 38 */       if (entry == null) {
/* 39 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 40 */         return false;
/*    */       }  
/* 42 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 43 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 44 */           String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
/* 45 */           loadFromJsonString(registryName, json);
/* 46 */           CommonGunPackNetwork.addData(DataType.GUN_DATA, registryName, json);
/* 47 */           boolean bool = true;
/* 48 */           if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 49 */       { GunMod.LOGGER.warn(MARKER, "Failed to read data file: {}, entry: {}", zipFile, entry);
/* 50 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 57 */     Path filePath = root.toPath().resolve("guns/data");
/* 58 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 59 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { String json = IOUtils.toString(stream, StandardCharsets.UTF_8); loadFromJsonString(id, json); CommonGunPackNetwork.addData(DataType.GUN_DATA, id, json); if (stream != null)
/* 60 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1)
/*    */                   { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 64 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read data file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 70 */         Files.walkFileTree(filePath, (FileVisitor<? super Path>)visitor);
/* 71 */       } catch (IOException e) {
/* 72 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", filePath);
/* 73 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadFromJsonString(ResourceLocation id, String json) {
/* 79 */     GunData data = (GunData)CommonGunPackLoader.GSON.fromJson(json, GunData.class);
/* 80 */     CommonAssetManager.INSTANCE.putGunData(id, data);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\loader\asset\GunDataLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */