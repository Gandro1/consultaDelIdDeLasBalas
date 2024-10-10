/*    */ package com.tacz.guns.resource.loader.asset;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import com.tacz.guns.resource.pojo.data.recipe.TableRecipe;
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
/*    */ public final class RecipeLoader
/*    */ {
/* 30 */   private static final Marker MARKER = MarkerManager.getMarker("RecipeLoader");
/* 31 */   private static final Pattern RECIPES_PATTERN = Pattern.compile("^(\\w+)/recipes/([\\w/]+)\\.json$");
/*    */   
/*    */   public static boolean load(ZipFile zipFile, String zipPath) {
/* 34 */     Matcher matcher = RECIPES_PATTERN.matcher(zipPath);
/* 35 */     if (matcher.find()) {
/* 36 */       String namespace = matcher.group(1);
/* 37 */       String path = matcher.group(2);
/* 38 */       ZipEntry entry = zipFile.getEntry(zipPath);
/* 39 */       if (entry == null) {
/* 40 */         GunMod.LOGGER.warn(MARKER, "{} file don't exist", zipPath);
/* 41 */         return false;
/*    */       }  
/* 43 */       try { InputStream stream = zipFile.getInputStream(entry); 
/* 44 */         try { ResourceLocation registryName = new ResourceLocation(namespace, path);
/* 45 */           String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
/* 46 */           loadFromJsonString(registryName, json);
/* 47 */           CommonGunPackNetwork.addData(DataType.RECIPES, registryName, json);
/* 48 */           boolean bool = true;
/* 49 */           if (stream != null) stream.close();  return bool; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/* 50 */       { GunMod.LOGGER.warn(MARKER, "Failed to read recipe file: {}, entry: {}", zipFile, entry);
/* 51 */         exception.printStackTrace(); }
/*    */     
/*    */     } 
/* 54 */     return false;
/*    */   }
/*    */   
/*    */   public static void load(File root) {
/* 58 */     Path filePath = root.toPath().resolve("recipes");
/* 59 */     if (Files.isDirectory(filePath, new java.nio.file.LinkOption[0])) {
/* 60 */       TacPathVisitor visitor = new TacPathVisitor(filePath.toFile(), root.getName(), ".json", (id, file) -> { try { InputStream stream = Files.newInputStream(file, new java.nio.file.OpenOption[0]); try { String json = IOUtils.toString(stream, StandardCharsets.UTF_8); loadFromJsonString(id, json); CommonGunPackNetwork.addData(DataType.RECIPES, id, json); if (stream != null)
/* 61 */                   stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1)
/*    */                   { throwable.addSuppressed(throwable1); }
/*    */                     throw throwable; }
/*    */                }
/* 65 */             catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException exception)
/*    */             { GunMod.LOGGER.warn(MARKER, "Failed to read recipe file: {}", file);
/*    */               exception.printStackTrace(); }
/*    */           
/*    */           });
/*    */       try {
/* 71 */         Files.walkFileTree(filePath, (FileVisitor<? super Path>)visitor);
/* 72 */       } catch (IOException e) {
/* 73 */         GunMod.LOGGER.warn(MARKER, "Failed to walk file tree: {}", filePath);
/* 74 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadFromJsonString(ResourceLocation id, String json) {
/* 80 */     TableRecipe tableRecipe = (TableRecipe)CommonGunPackLoader.GSON.fromJson(json, TableRecipe.class);
/* 81 */     CommonAssetManager.INSTANCE.putRecipe(id, new GunSmithTableRecipe(id, tableRecipe));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\loader\asset\RecipeLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */