/*    */ package com.tacz.guns.client.resource.loader.asset;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.texture.FilePackTexture;
/*    */ import com.tacz.guns.client.resource.texture.ZipPackTexture;
/*    */ import com.tacz.guns.util.TacPathVisitor;
/*    */ import java.io.File;
/*    */ import java.nio.file.FileVisitor;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.AbstractTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public final class TextureLoader {
/* 21 */   private static final Marker MARKER = MarkerManager.getMarker("TextureLoader");
/* 22 */   private static final Pattern TEXTURE_PATTERN = Pattern.compile("^(\\w+)/textures/([\\w/]+)\\.png$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 25 */     Matcher matcher = TEXTURE_PATTERN.matcher(zipPath);
/* 26 */     if (matcher.find()) {
/* 27 */       String namespace = matcher.group(1);
/* 28 */       String path = matcher.group(2);
/* 29 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 30 */       if (entry == null) {
/* 31 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 32 */         return false;
/*    */       } 
/* 34 */       ResourceLocation id = new ResourceLocation(namespace, path);
/* 35 */       ZipPackTexture zipPackTexture = new ZipPackTexture(id, zipFile.getName());
/* 36 */       (Minecraft.m_91087_()).f_90987_.m_118495_(id, (AbstractTexture)zipPackTexture);
/* 37 */       return true;
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 43 */     Path filePath = root.toPath().resolve("textures");
/* 44 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 45 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".png", (id, file) -> {
/*    */             FilePackTexture filePackTexture = new FilePackTexture(id, file);
/*    */             (Minecraft.m_91087_()).f_90987_.m_118495_(id, (AbstractTexture)filePackTexture);
/*    */           });
/*    */       try {
/* 50 */         Files.walkFileTree(filePath, (FileVisitor<? super Path>)visitor);
/* 51 */       } catch (Exception e) {
/* 52 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", filePath);
/* 53 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\TextureLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */