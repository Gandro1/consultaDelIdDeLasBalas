/*    */ package com.tacz.guns.resource.loader.index;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import com.tacz.guns.resource.pojo.GunIndexPOJO;
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
/*    */ 
/*    */ 
/*    */ public final class CommonGunIndexLoader
/*    */ {
/* 31 */   private static final Pattern GUNS_INDEX_PATTERN = Pattern.compile("^(\\w+)/guns/index/(\\w+)\\.json$");
/* 32 */   private static final Marker MARKER = MarkerManager.getMarker("CommonGunIndexLoader");
/*    */   
/*    */   public static void loadGunIndex(String path, ZipFile zipFile) throws IOException {
/* 35 */     Matcher matcher = GUNS_INDEX_PATTERN.matcher(path);
/* 36 */     if (matcher.find()) {
/* 37 */       String namespace = matcher.group(1);
/* 38 */       String id = matcher.group(2);
/* 39 */       ZipEntry entry = zipFile.getEntry(path);
/* 40 */       if (entry == null) {
/* 41 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", path); return;
/*    */       } 
/*    */       
/* 44 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 45 */         try { String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
/* 46 */           ResourceLocation registryName = new ResourceLocation(namespace, id);
/* 47 */           loadGunFromJsonString(registryName, json);
/* 48 */           CommonGunPackNetwork.addData(DataType.GUN_INDEX, registryName, json);
/* 49 */           if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IllegalArgumentException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 50 */       { GunMod.LOGGER.warn("{} index file read fail!", path);
/* 51 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadGunIndex(File root) throws IOException {
/* 57 */     Path filePath = root.toPath().resolve("guns/index");
/* 58 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 59 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { String json = IOUtils.toString(stream, StandardCharsets.UTF_8); loadGunFromJsonString(id, json); CommonGunPackNetwork.addData(DataType.GUN_INDEX, id, json); if (stream != null)
/* 60 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1)
/*    */                   { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 64 */             catch (IllegalArgumentException|IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn("{} index file read fail!", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/* 69 */       Files.walkFileTree(filePath, (FileVisitor<? super Path>)visitor);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadGunFromJsonString(ResourceLocation id, String json) {
/* 74 */     GunIndexPOJO indexPOJO = (GunIndexPOJO)CommonGunPackLoader.GSON.fromJson(json, GunIndexPOJO.class);
/* 75 */     CommonGunPackLoader.GUN_INDEX.put(id, CommonGunIndex.getInstance(indexPOJO));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\loader\index\CommonGunIndexLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */