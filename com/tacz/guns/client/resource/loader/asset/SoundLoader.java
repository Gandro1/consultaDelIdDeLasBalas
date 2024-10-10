/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ 
/*    */ import com.mojang.blaze3d.audio.OggAudioStream;
/*    */ import com.mojang.blaze3d.audio.SoundBuffer;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.file.FileVisitor;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public final class SoundLoader {
/* 25 */   private static final Marker MARKER = MarkerManager.getMarker("SoundLoader");
/* 26 */   private static final Pattern SOUND_PATTERN = Pattern.compile("^(\\w+)/sounds/([\\w/]+)\\.ogg$");
/*    */   
/*    */   @Nullable
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 30 */     Matcher matcher = SOUND_PATTERN.matcher(zipPath);
/* 31 */     if (matcher.find()) {
/* 32 */       String namespace = matcher.group(1);
/* 33 */       String path = matcher.group(2);
/* 34 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 35 */       if (entry == null) {
/* 36 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 37 */         return false;
/*    */       }  
/* 39 */       try { InputStream zipEntryStream = zipFile.getInputStream(entry); try { OggAudioStream audioStream = new OggAudioStream(zipEntryStream); 
/* 40 */           try { ByteBuffer bytebuffer = audioStream.m_83764_();
/* 41 */             ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 42 */             ClientAssetManager.INSTANCE.putSoundBuffer(registryName, new SoundBuffer(bytebuffer, audioStream.m_6206_()));
/* 43 */             boolean bool = true;
/* 44 */             audioStream.close(); if (zipEntryStream != null) zipEntryStream.close();  return bool; } catch (Throwable throwable) { try { audioStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Throwable throwable) { if (zipEntryStream != null) try { zipEntryStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException ioe)
/* 45 */       { GunMod.LOGGER.warn(MARKER, "Failed to load sound: {}", zipPath);
/* 46 */         ioe.printStackTrace(); }
/*    */     
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 53 */     Path filePath = root.toPath().resolve("sounds");
/* 54 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 55 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".ogg", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { OggAudioStream audioStream = new OggAudioStream(stream); try { ByteBuffer bytebuffer = audioStream.m_83764_(); ClientAssetManager.INSTANCE.putSoundBuffer(id, new SoundBuffer(bytebuffer, audioStream.m_6206_())); audioStream.close(); }
/* 56 */                 catch (Throwable throwable) { try { audioStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 59 */             catch (IOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read sound file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 65 */         Files.walkFileTree(filePath, (FileVisitor<? super Path>)visitor);
/* 66 */       } catch (IOException e) {
/* 67 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", filePath);
/* 68 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\SoundLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */