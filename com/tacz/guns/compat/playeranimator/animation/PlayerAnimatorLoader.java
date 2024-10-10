/*    */ package com.tacz.guns.compat.playeranimator.animation;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public class PlayerAnimatorLoader
/*    */ {
/* 22 */   private static final Marker MARKER = MarkerManager.getMarker("PlayerAnimatorLoader");
/* 23 */   private static final Pattern ANIMATOR_PATTERN = Pattern.compile("^(\\w+)/player_animator/([\\w/]+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 26 */     Matcher matcher = ANIMATOR_PATTERN.matcher(zipPath);
/* 27 */     if (matcher.find()) {
/* 28 */       String namespace = matcher.group(1);
/* 29 */       String path = matcher.group(2);
/* 30 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 31 */       if (entry == null) {
/* 32 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 33 */         return false;
/*    */       }  
/* 35 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 36 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 37 */           PlayerAnimatorAssetManager.INSTANCE.putAnimation(registryName, stream);
/* 38 */           boolean bool = true;
/* 39 */           if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 40 */       { GunMod.LOGGER.warn(MARKER, "Failed to read player animator file: {}, entry: {}", zipFile, entry);
/* 41 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 48 */     Path playerAnimatorPath = root.toPath().resolve("player_animator");
/* 49 */     if (Files.isDirectory(playerAnimatorPath, new java.nio.file.LinkOption[0])) {
/* 50 */       TacPathVisitor visitor = new TacPathVisitor(playerAnimatorPath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { PlayerAnimatorAssetManager.INSTANCE.putAnimation(id, stream); if (stream != null)
/* 51 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*    */                }
/* 53 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read player animator file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 59 */         Files.walkFileTree(playerAnimatorPath, (FileVisitor<? super Path>)visitor);
/* 60 */       } catch (IOException e) {
/* 61 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", playerAnimatorPath);
/* 62 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\animation\PlayerAnimatorLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */