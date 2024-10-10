/*     */ package com.tacz.guns.client.resource.loader.asset;
/*     */ 
/*     */ import com.tacz.guns.GunMod;
/*     */ import com.tacz.guns.api.client.animation.gltf.AnimationStructure;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimationFile;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.RawAnimationStructure;
/*     */ import com.tacz.guns.util.TacPathVisitor;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.FileVisitor;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AnimationLoader
/*     */ {
/*  30 */   private static final Marker MARKER = MarkerManager.getMarker("AnimationLoader");
/*  31 */   private static final Pattern GLTF_ANIMATION_PATTERN = Pattern.compile("^(\\w+)/animations/([\\w/]+)\\.gltf$");
/*  32 */   private static final Pattern BEDROCK_ANIMATION_PATTERN = Pattern.compile("^(\\w+)/animations/([\\w/]+)\\.animation\\.json$");
/*     */   
/*     */   public static boolean load(ZipFile zipFile, String zipPath) {
/*  35 */     Matcher gltfMatcher = GLTF_ANIMATION_PATTERN.matcher(zipPath);
/*  36 */     if (gltfMatcher.find()) {
/*  37 */       String namespace = gltfMatcher.group(1);
/*  38 */       String path = gltfMatcher.group(2);
/*  39 */       ZipEntry entry = zipFile.getEntry(zipPath);
/*  40 */       if (entry == null) {
/*  41 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/*  42 */         return false;
/*     */       }  
/*  44 */       try { InputStream animationFileStream = zipFile.getInputStream(entry); 
/*  45 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/*  46 */           RawAnimationStructure rawStructure = (RawAnimationStructure)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(animationFileStream, StandardCharsets.UTF_8), RawAnimationStructure.class);
/*  47 */           ClientAssetManager.INSTANCE.putGltfAnimation(registryName, new AnimationStructure(rawStructure));
/*  48 */           if (animationFileStream != null) animationFileStream.close();  } catch (Throwable throwable) { if (animationFileStream != null) try { animationFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*  49 */       { GunMod.LOGGER.warn(MARKER, "Failed to read animation file: {}, entry: {}", zipFile, entry);
/*  50 */         exception.printStackTrace(); }
/*     */     
/*     */     } 
/*  53 */     Matcher bedrockMatcher = BEDROCK_ANIMATION_PATTERN.matcher(zipPath);
/*  54 */     if (bedrockMatcher.find()) {
/*  55 */       String namespace = bedrockMatcher.group(1);
/*  56 */       String path = bedrockMatcher.group(2);
/*  57 */       ZipEntry entry = zipFile.getEntry(zipPath);
/*  58 */       if (entry == null) {
/*  59 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/*  60 */         return false;
/*     */       }  
/*  62 */       try { InputStream animationFileStream = zipFile.getInputStream(entry); 
/*  63 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/*  64 */           BedrockAnimationFile bedrockAnimationFile = (BedrockAnimationFile)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(animationFileStream, StandardCharsets.UTF_8), BedrockAnimationFile.class);
/*  65 */           ClientAssetManager.INSTANCE.putBedrockAnimation(registryName, bedrockAnimationFile);
/*  66 */           if (animationFileStream != null) animationFileStream.close();  } catch (Throwable throwable) { if (animationFileStream != null) try { animationFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*  67 */       { GunMod.LOGGER.warn(MARKER, "Failed to read animation file: {}, entry: {}", zipFile, entry);
/*  68 */         exception.printStackTrace(); }
/*     */     
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public static void load(File root) {
/*  75 */     Path animationPath = root.toPath().resolve("animations");
/*  76 */     if (Files.isDirectory(animationPath, new java.nio.file.LinkOption[0])) {
/*  77 */       TacPathVisitor gltfVisitor = new TacPathVisitor(animationPath.toFile(), root.getName(), ".gltf", (id, file) -> { try { InputStream animationFileStream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { RawAnimationStructure rawStructure = (RawAnimationStructure)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(animationFileStream, StandardCharsets.UTF_8), RawAnimationStructure.class); ClientAssetManager.INSTANCE.putGltfAnimation(id, new AnimationStructure(rawStructure)); if (animationFileStream != null)
/*  78 */                   animationFileStream.close();  } catch (Throwable throwable) { if (animationFileStream != null) try { animationFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                     throw throwable; }
/*     */                }
/*  81 */             catch (IOException exception)
/*     */             { GunMod.LOGGER.warn(MARKER, "Failed to read animation file: {}", file);
/*     */               exception.printStackTrace(); }
/*     */           
/*     */           });
/*     */       try {
/*  87 */         Files.walkFileTree(animationPath, (FileVisitor<? super Path>)gltfVisitor);
/*  88 */       } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception) {
/*  89 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", animationPath);
/*  90 */         exception.printStackTrace();
/*     */       } 
/*     */       
/*  93 */       TacPathVisitor bedrockVisitor = new TacPathVisitor(animationPath.toFile(), root.getName(), ".animation.json", (id, file) -> { try { InputStream animationFileStream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { BedrockAnimationFile bedrockAnimationFile = (BedrockAnimationFile)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(animationFileStream, StandardCharsets.UTF_8), BedrockAnimationFile.class); ClientAssetManager.INSTANCE.putBedrockAnimation(id, bedrockAnimationFile); if (animationFileStream != null)
/*  94 */                   animationFileStream.close();  } catch (Throwable throwable) { if (animationFileStream != null) try { animationFileStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                     throw throwable; }
/*     */                }
/*  97 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*     */             { GunMod.LOGGER.warn(MARKER, "Failed to read animation file: {}", file);
/*     */               exception.printStackTrace(); }
/*     */           
/*     */           });
/*     */       try {
/* 103 */         Files.walkFileTree(animationPath, (FileVisitor<? super Path>)bedrockVisitor);
/* 104 */       } catch (IOException e) {
/* 105 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", animationPath);
/* 106 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\asset\AnimationLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */