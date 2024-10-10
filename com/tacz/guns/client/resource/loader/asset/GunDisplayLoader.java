/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.pojo.display.gun.GunDisplay;
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
/*    */ public final class GunDisplayLoader
/*    */ {
/* 27 */   private static final Marker MARKER = MarkerManager.getMarker("GunDisplayLoader");
/* 28 */   private static final Pattern DISPLAY_PATTERN = Pattern.compile("^(\\w+)/guns/display/([\\w/]+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 31 */     Matcher matcher = DISPLAY_PATTERN.matcher(zipPath);
/* 32 */     if (matcher.find()) {
/* 33 */       String namespace = matcher.group(1);
/* 34 */       String path = matcher.group(2);
/* 35 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 36 */       if (entry == null) {
/* 37 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 38 */         return false;
/*    */       }  
/* 40 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 41 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 42 */           GunDisplay display = (GunDisplay)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), GunDisplay.class);
/* 43 */           ClientAssetManager.INSTANCE.putGunDisplay(registryName, display);
/* 44 */           boolean bool = true;
/* 45 */           if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 46 */       { GunMod.LOGGER.warn(MARKER, "Failed to read display file: {}, entry: {}", zipFile, entry);
/* 47 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 54 */     Path displayPath = root.toPath().resolve("guns/display");
/* 55 */     if (Files.isDirectory(displayPath, new java.nio.file.LinkOption[0])) {
/* 56 */       TacPathVisitor visitor = new TacPathVisitor(displayPath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { GunDisplay display = (GunDisplay)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), GunDisplay.class); ClientAssetManager.INSTANCE.putGunDisplay(id, display); if (stream != null)
/* 57 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 60 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read display file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 66 */         Files.walkFileTree(displayPath, (FileVisitor<? super Path>)visitor);
/* 67 */       } catch (IOException e) {
/* 68 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", displayPath);
/* 69 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\GunDisplayLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */