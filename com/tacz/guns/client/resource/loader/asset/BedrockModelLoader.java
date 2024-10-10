/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.FileVisitor;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BedrockModelLoader
/*    */ {
/* 28 */   private static final Marker MARKER = MarkerManager.getMarker("BedrockModelLoader");
/* 29 */   private static final Pattern MODEL_PATTERN = Pattern.compile("^(\\w+)/models/([\\w/]+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 32 */     Matcher matcher = MODEL_PATTERN.matcher(zipPath);
/* 33 */     if (matcher.find()) {
/* 34 */       String namespace = matcher.group(1);
/* 35 */       String path = matcher.group(2);
/* 36 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 37 */       if (entry == null) {
/* 38 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 39 */         return false;
/*    */       }  
/* 41 */       try { InputStream modelFileStream = zipFile.getInputStream(entry); 
/* 42 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 43 */           BedrockModelPOJO modelPOJO = (BedrockModelPOJO)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(modelFileStream, StandardCharsets.UTF_8), BedrockModelPOJO.class);
/* 44 */           ClientAssetManager.INSTANCE.putModel(registryName, modelPOJO);
/* 45 */           boolean bool = true;
/* 46 */           if (modelFileStream != null) modelFileStream.close();  return bool; } catch (Throwable throwable) { if (modelFileStream != null) try { modelFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 47 */       { GunMod.LOGGER.warn(MARKER, "Failed to read model file: {}, entry: {}", zipFile, entry);
/* 48 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 55 */     Path modelPath = root.toPath().resolve("models");
/* 56 */     if (Files.isDirectory(modelPath, new java.nio.file.LinkOption[0])) {
/* 57 */       TacPathVisitor visitor = new TacPathVisitor(modelPath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream modelFileStream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { BedrockModelPOJO modelPOJO = (BedrockModelPOJO)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(modelFileStream, StandardCharsets.UTF_8), BedrockModelPOJO.class); ClientAssetManager.INSTANCE.putModel(id, modelPOJO); if (modelFileStream != null)
/* 58 */                   modelFileStream.close();  } catch (Throwable throwable) { if (modelFileStream != null) try { modelFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 61 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read model file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 67 */         Files.walkFileTree(modelPath, (FileVisitor<? super Path>)visitor);
/* 68 */       } catch (IOException e) {
/* 69 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", modelPath);
/* 70 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\BedrockModelLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */