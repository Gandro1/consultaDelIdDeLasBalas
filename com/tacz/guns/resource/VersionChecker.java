/*     */ package com.tacz.guns.resource;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.GunMod;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import net.minecraftforge.fml.ModContainer;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import org.apache.maven.artifact.versioning.ArtifactVersion;
/*     */ import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
/*     */ import org.apache.maven.artifact.versioning.VersionRange;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class VersionChecker
/*     */ {
/*  33 */   private static final Marker MARKER = MarkerManager.getMarker("VersionChecker");
/*  34 */   private static final Pattern PACK_INFO_PATTERN = Pattern.compile("^\\w+/pack\\.json$");
/*  35 */   private static final Map<Path, Boolean> VERSION_CHECK_CACHE = Maps.newHashMap();
/*     */   
/*     */   public static boolean match(File dir) {
/*  38 */     return ((Boolean)VERSION_CHECK_CACHE.computeIfAbsent(dir.toPath(), path -> Boolean.valueOf(checkDirVersion(dir)))).booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean noneMatch(ZipFile zipFile, Path zipFilePath) {
/*  42 */     return !((Boolean)VERSION_CHECK_CACHE.computeIfAbsent(zipFilePath, path -> Boolean.valueOf(checkZipVersion(zipFile)))).booleanValue();
/*     */   }
/*     */   
/*     */   public static void clearCache() {
/*  46 */     VERSION_CHECK_CACHE.clear();
/*     */   }
/*     */   
/*     */   private static boolean checkDirVersion(File root) {
/*  50 */     if (!root.isDirectory()) {
/*  51 */       return false;
/*     */     }
/*  53 */     Path packInfoFilePath = root.toPath().resolve("pack.json");
/*     */     
/*  55 */     if (Files.notExists(packInfoFilePath, new java.nio.file.LinkOption[0]))
/*  56 */       return true; 
/*     */     
/*  58 */     try { InputStream stream = Files.newInputStream(packInfoFilePath, new java.nio.file.OpenOption[0]); 
/*  59 */       try { Info info = (Info)CommonGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), Info.class);
/*  60 */         boolean bool = modVersionAllMatch(info);
/*  61 */         if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException|InvalidVersionSpecificationException exception)
/*  62 */     { GunMod.LOGGER.warn(MARKER, "Failed to read info json: {}", packInfoFilePath);
/*  63 */       GunMod.LOGGER.warn(exception.getMessage());
/*     */       
/*  65 */       return true; }
/*     */   
/*     */   }
/*     */   private static boolean checkZipVersion(ZipFile zipFile) {
/*  69 */     Enumeration<? extends ZipEntry> iteration = zipFile.entries();
/*  70 */     while (iteration.hasMoreElements()) {
/*  71 */       String path = ((ZipEntry)iteration.nextElement()).getName();
/*  72 */       Matcher matcher = PACK_INFO_PATTERN.matcher(path);
/*  73 */       if (!matcher.matches()) {
/*     */         continue;
/*     */       }
/*  76 */       ZipEntry entry = zipFile.getEntry(path);
/*     */       
/*  78 */       if (entry == null)
/*  79 */         return true; 
/*     */       
/*  81 */       try { InputStream stream = zipFile.getInputStream(entry); 
/*  82 */         try { Info info = (Info)CommonGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), Info.class);
/*     */           
/*  84 */           if (!modVersionAllMatch(info))
/*  85 */           { boolean bool = false;
/*     */             
/*  87 */             if (stream != null) stream.close();  return bool; }  if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException|InvalidVersionSpecificationException exception)
/*     */       
/*  89 */       { GunMod.LOGGER.warn(MARKER, "Failed to read info json: {}", path);
/*  90 */         GunMod.LOGGER.warn(exception.getMessage()); }
/*     */     
/*     */     } 
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean modVersionAllMatch(Info info) throws InvalidVersionSpecificationException {
/*  97 */     HashMap<String, String> dependencies = info.getDependencies();
/*  98 */     for (String modId : dependencies.keySet()) {
/*  99 */       if (!modVersionMatch(modId, dependencies.get(modId))) {
/* 100 */         return false;
/*     */       }
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean modVersionMatch(String modId, String version) throws InvalidVersionSpecificationException {
/* 107 */     VersionRange versionRange = VersionRange.createFromVersionSpec(version);
/* 108 */     return ((Boolean)ModList.get().getModContainerById(modId).map(mod -> {
/*     */           ArtifactVersion modVersion = mod.getModInfo().getVersion();
/*     */           return Boolean.valueOf(versionRange.containsVersion(modVersion));
/* 111 */         }).orElse(Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */   
/*     */   private static class Info {
/*     */     @SerializedName("dependencies")
/* 116 */     private HashMap<String, String> dependencies = Maps.newHashMap();
/*     */     
/*     */     public HashMap<String, String> getDependencies() {
/* 119 */       return this.dependencies;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\VersionChecker.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */