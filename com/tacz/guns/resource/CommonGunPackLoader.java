/*     */ package com.tacz.guns.resource;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.resource.ResourceManager;
/*     */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.loader.asset.AllowAttachmentTagsLoader;
/*     */ import com.tacz.guns.resource.loader.asset.AttachmentDataLoader;
/*     */ import com.tacz.guns.resource.loader.asset.AttachmentTagsLoader;
/*     */ import com.tacz.guns.resource.loader.asset.GunDataLoader;
/*     */ import com.tacz.guns.resource.loader.asset.RecipeLoader;
/*     */ import com.tacz.guns.resource.loader.index.CommonAmmoIndexLoader;
/*     */ import com.tacz.guns.resource.loader.index.CommonAttachmentIndexLoader;
/*     */ import com.tacz.guns.resource.loader.index.CommonGunIndexLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Ignite;
/*     */ import com.tacz.guns.resource.serialize.GunSmithTableIngredientSerializer;
/*     */ import com.tacz.guns.resource.serialize.IgniteSerializer;
/*     */ import com.tacz.guns.resource.serialize.PairSerializer;
/*     */ import com.tacz.guns.resource.serialize.Vec3Serializer;
/*     */ import com.tacz.guns.util.GetJarResources;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ public class CommonGunPackLoader {
/*  42 */   public static final Gson GSON = (new GsonBuilder())
/*  43 */     .registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
/*  44 */     .registerTypeAdapter(Pair.class, new PairSerializer())
/*  45 */     .registerTypeAdapter(GunSmithTableIngredient.class, new GunSmithTableIngredientSerializer())
/*  46 */     .registerTypeAdapter(GunSmithTableResult.class, new GunSmithTableResultSerializer())
/*  47 */     .registerTypeAdapter(ExtraDamage.DistanceDamagePair.class, new DistanceDamagePairSerializer())
/*  48 */     .registerTypeAdapter(Vec3.class, new Vec3Serializer())
/*  49 */     .registerTypeAdapter(Ignite.class, new IgniteSerializer())
/*  50 */     .create();
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final Path FOLDER = Paths.get("config", new String[] { "tacz", "custom" });
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final Map<ResourceLocation, CommonGunIndex> GUN_INDEX = Maps.newHashMap();
/*  59 */   public static final Map<ResourceLocation, CommonAmmoIndex> AMMO_INDEX = Maps.newHashMap();
/*  60 */   public static final Map<ResourceLocation, CommonAttachmentIndex> ATTACHMENT_INDEX = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*  66 */     createFolder();
/*  67 */     checkDefaultPack();
/*  68 */     CommonGunPackNetwork.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadAsset() {
/*  75 */     CommonAssetManager.INSTANCE.clearAll();
/*     */     
/*  77 */     File[] files = FOLDER.toFile().listFiles((dir, name) -> true);
/*  78 */     if (files != null) {
/*  79 */       readAsset(files);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadIndex() {
/*  87 */     GUN_INDEX.clear();
/*  88 */     AMMO_INDEX.clear();
/*     */     
/*  90 */     File[] files = FOLDER.toFile().listFiles((dir, name) -> true);
/*  91 */     if (files != null) {
/*  92 */       readIndex(files);
/*     */     }
/*     */ 
/*     */     
/*  96 */     MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
/*  97 */     if (server != null) {
/*  98 */       List<ServerPlayer> players = server.m_6846_().m_11314_();
/*  99 */       players.forEach(player -> {
/*     */             if (player != null && IGun.mainhandHoldGun((LivingEntity)player)) {
/*     */               AttachmentPropertyManager.postChangeEvent((LivingEntity)player, player.m_21205_());
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadRecipes() {
/* 111 */     CommonAssetManager.INSTANCE.clearRecipes();
/* 112 */     File[] files = FOLDER.toFile().listFiles((dir, name) -> true);
/* 113 */     if (files != null) {
/* 114 */       readRecipes(files);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkDefaultPack() {
/* 119 */     if (!((Boolean)OtherConfig.DEFAULT_PACK_DEBUG.get()).booleanValue()) {
/* 120 */       for (ResourceManager.ExtraEntry entry : ResourceManager.EXTRA_ENTRIES) {
/* 121 */         GetJarResources.copyModDirectory(entry.modMainClass(), entry.srcPath(), FOLDER, entry.extraDirName());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void createFolder() {
/* 127 */     File folder = FOLDER.toFile();
/* 128 */     if (!folder.isDirectory()) {
/*     */       try {
/* 130 */         Files.createDirectories(folder.toPath(), (FileAttribute<?>[])new FileAttribute[0]);
/* 131 */       } catch (Exception e) {
/* 132 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void readAsset(File[] files) {
/* 138 */     for (File file : files) {
/* 139 */       if (file.isFile() && file.getName().endsWith(".zip")) {
/* 140 */         readZipAsset(file);
/*     */       }
/* 142 */       if (file.isDirectory()) {
/* 143 */         File[] subFiles = file.listFiles((dir, name) -> true);
/* 144 */         if (subFiles == null) {
/*     */           return;
/*     */         }
/* 147 */         for (File namespaceFile : subFiles)
/* 148 */           readDirAsset(namespaceFile); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readZipAsset(File file) {
/*     */     
/* 155 */     try { ZipFile zipFile = new ZipFile(file);
/*     */       
/* 157 */       try { if (VersionChecker.noneMatch(zipFile, file.toPath()))
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 172 */           zipFile.close(); return; }  Enumeration<? extends ZipEntry> iteration = zipFile.entries(); while (iteration.hasMoreElements()) { String path = ((ZipEntry)iteration.nextElement()).getName(); GunDataLoader.load(zipFile, path); AttachmentDataLoader.load(zipFile, path); AttachmentTagsLoader.load(zipFile, path); AllowAttachmentTagsLoader.load(zipFile, path); }  zipFile.close(); } catch (Throwable throwable) { try { zipFile.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ioException)
/* 173 */     { ioException.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void readDirAsset(File root) {
/* 178 */     if (VersionChecker.match(root)) {
/* 179 */       GunDataLoader.load(root);
/* 180 */       AttachmentDataLoader.load(root);
/* 181 */       AttachmentTagsLoader.load(root);
/* 182 */       AllowAttachmentTagsLoader.load(root);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readIndex(File[] files) {
/* 187 */     for (File file : files) {
/* 188 */       if (file.isFile() && file.getName().endsWith(".zip")) {
/* 189 */         readZipIndex(file);
/*     */       }
/* 191 */       if (file.isDirectory()) {
/* 192 */         File[] subFiles = file.listFiles((dir, name) -> true);
/* 193 */         if (subFiles == null) {
/*     */           return;
/*     */         }
/* 196 */         for (File namespaceFile : subFiles)
/* 197 */           readDirIndex(namespaceFile); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readZipIndex(File file) {
/*     */     
/* 204 */     try { ZipFile zipFile = new ZipFile(file);
/*     */       
/* 206 */       try { if (VersionChecker.noneMatch(zipFile, file.toPath()))
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 216 */           zipFile.close(); return; }  Enumeration<? extends ZipEntry> iteration = zipFile.entries(); while (iteration.hasMoreElements()) { String path = ((ZipEntry)iteration.nextElement()).getName(); CommonAmmoIndexLoader.loadAmmoIndex(path, zipFile); CommonGunIndexLoader.loadGunIndex(path, zipFile); CommonAttachmentIndexLoader.loadAttachmentIndex(path, zipFile); }  zipFile.close(); } catch (Throwable throwable) { try { zipFile.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ioException)
/* 217 */     { ioException.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void readDirIndex(File root) {
/* 222 */     if (VersionChecker.match(root)) {
/*     */       try {
/* 224 */         CommonAmmoIndexLoader.loadAmmoIndex(root);
/* 225 */         CommonGunIndexLoader.loadGunIndex(root);
/* 226 */         CommonAttachmentIndexLoader.loadAttachmentIndex(root);
/* 227 */       } catch (IOException e) {
/* 228 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void readRecipes(File[] files) {
/* 234 */     for (File file : files) {
/* 235 */       if (file.isFile() && file.getName().endsWith(".zip")) {
/* 236 */         readZipRecipes(file);
/*     */       }
/* 238 */       if (file.isDirectory()) {
/* 239 */         File[] subFiles = file.listFiles((dir, name) -> true);
/* 240 */         if (subFiles == null) {
/*     */           return;
/*     */         }
/* 243 */         for (File namespaceFile : subFiles)
/* 244 */           readDirRecipes(namespaceFile); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readZipRecipes(File file) {
/*     */     
/* 251 */     try { ZipFile zipFile = new ZipFile(file);
/*     */       
/* 253 */       try { if (VersionChecker.noneMatch(zipFile, file.toPath()))
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 261 */           zipFile.close(); return; }  Enumeration<? extends ZipEntry> iteration = zipFile.entries(); while (iteration.hasMoreElements()) { String path = ((ZipEntry)iteration.nextElement()).getName(); RecipeLoader.load(zipFile, path); }  zipFile.close(); } catch (Throwable throwable) { try { zipFile.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ioException)
/* 262 */     { ioException.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void readDirRecipes(File root) {
/* 267 */     if (VersionChecker.match(root)) {
/* 268 */       RecipeLoader.load(root);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Optional<CommonGunIndex> getGunIndex(ResourceLocation registryName) {
/* 273 */     return Optional.ofNullable(GUN_INDEX.get(registryName));
/*     */   }
/*     */   
/*     */   public static Optional<CommonAmmoIndex> getAmmoIndex(ResourceLocation registryName) {
/* 277 */     return Optional.ofNullable(AMMO_INDEX.get(registryName));
/*     */   }
/*     */   
/*     */   public static Optional<CommonAttachmentIndex> getAttachmentIndex(ResourceLocation registryName) {
/* 281 */     return Optional.ofNullable(ATTACHMENT_INDEX.get(registryName));
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, CommonGunIndex>> getAllGuns() {
/* 285 */     return GUN_INDEX.entrySet();
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, CommonAmmoIndex>> getAllAmmo() {
/* 289 */     return AMMO_INDEX.entrySet();
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, CommonAttachmentIndex>> getAllAttachments() {
/* 293 */     return ATTACHMENT_INDEX.entrySet();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\CommonGunPackLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */