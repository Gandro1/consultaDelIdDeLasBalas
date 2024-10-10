/*    */ package com.tacz.guns.resource.loader.asset;
/*    */ 
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.FileVisitOption;
/*    */ import java.nio.file.FileVisitor;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
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
/*    */ public class AttachmentTagsLoader
/*    */ {
/* 32 */   private static final Marker MARKER = MarkerManager.getMarker("AttachmentTagsLoader");
/* 33 */   private static final Pattern TAGS_PATTERN = Pattern.compile("^(\\w+)/tags/attachments/(\\w+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 36 */     Matcher matcher = TAGS_PATTERN.matcher(zipPath);
/* 37 */     if (matcher.find()) {
/* 38 */       String namespace = matcher.group(1);
/* 39 */       String path = matcher.group(2);
/* 40 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 41 */       if (entry == null) {
/* 42 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 43 */         return false;
/*    */       }  
/* 45 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 46 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 47 */           String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
/* 48 */           loadFromJsonString(registryName, json);
/* 49 */           CommonGunPackNetwork.addData(DataType.ATTACHMENT_TAGS, registryName, json);
/* 50 */           boolean bool = true;
/* 51 */           if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 52 */       { GunMod.LOGGER.warn(MARKER, "Failed to read data file: {}, entry: {}", zipFile, entry);
/* 53 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 60 */     Path filePath = root.toPath().resolve("tags/attachments");
/* 61 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 62 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { String json = IOUtils.toString(stream, StandardCharsets.UTF_8); loadFromJsonString(id, json); CommonGunPackNetwork.addData(DataType.ATTACHMENT_TAGS, id, json); if (stream != null)
/* 63 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1)
/*    */                   { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 67 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read data file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 73 */         Files.walkFileTree(filePath, EnumSet.noneOf(FileVisitOption.class), 1, (FileVisitor<? super Path>)visitor);
/* 74 */       } catch (IOException e) {
/* 75 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", filePath);
/* 76 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadFromJsonString(ResourceLocation id, String json) {
/* 82 */     List<String> tags = (List<String>)CommonGunPackLoader.GSON.fromJson(json, new TypeToken<List<String>>() {  }
/*    */       );
/* 84 */     CommonAssetManager.INSTANCE.putAttachmentTags(id, tags);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\loader\asset\AttachmentTagsLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */