/*     */ package com.tacz.guns.client.resource;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.resource.ResourceManager;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.loader.asset.AmmoDisplayLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.AnimationLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.AttachmentDisplayLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.AttachmentSkinLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.BedrockModelLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.GunDisplayLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.LanguageLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.PackInfoLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.SoundLoader;
/*     */ import com.tacz.guns.client.resource.loader.asset.TextureLoader;
/*     */ import com.tacz.guns.client.resource.loader.index.ClientGunIndexLoader;
/*     */ import com.tacz.guns.client.resource.pojo.CommonTransformObject;
/*     */ import com.tacz.guns.client.resource.pojo.model.CubesItem;
/*     */ import com.tacz.guns.client.resource.serialize.AnimationKeyframesSerializer;
/*     */ import com.tacz.guns.client.resource.serialize.ItemStackSerializer;
/*     */ import com.tacz.guns.client.resource.serialize.SoundEffectKeyframesSerializer;
/*     */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*     */ import com.tacz.guns.config.common.OtherConfig;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.VersionChecker;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.util.GetJarResources;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ClientGunPackLoader {
/*  51 */   public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
/*  52 */     .registerTypeAdapter(CubesItem.class, new CubesItem.Deserializer())
/*  53 */     .registerTypeAdapter(Vector3f.class, new Vector3fSerializer())
/*  54 */     .registerTypeAdapter(CommonTransformObject.class, new CommonTransformObject.Serializer())
/*  55 */     .registerTypeAdapter(ItemStack.class, new ItemStackSerializer())
/*  56 */     .registerTypeAdapter(AnimationKeyframes.class, new AnimationKeyframesSerializer())
/*  57 */     .registerTypeAdapter(SoundEffectKeyframes.class, new SoundEffectKeyframesSerializer())
/*  58 */     .create();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final Map<ResourceLocation, ClientGunIndex> GUN_INDEX = Maps.newHashMap();
/*  64 */   public static final Map<ResourceLocation, ClientAmmoIndex> AMMO_INDEX = Maps.newHashMap();
/*  65 */   public static final Map<ResourceLocation, ClientAttachmentIndex> ATTACHMENT_INDEX = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*  71 */     createFolder();
/*  72 */     checkDefaultPack();
/*  73 */     CommonGunPackNetwork.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadAsset() {
/*  80 */     ClientAssetManager.INSTANCE.clearAll();
/*     */     
/*  82 */     File[] files = CommonGunPackLoader.FOLDER.toFile().listFiles((dir, name) -> true);
/*  83 */     if (files != null) {
/*  84 */       readAsset(files);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadIndex() {
/*  92 */     AMMO_INDEX.clear();
/*  93 */     GUN_INDEX.clear();
/*  94 */     ATTACHMENT_INDEX.clear();
/*     */     
/*  96 */     ClientAmmoIndexLoader.loadAmmoIndex();
/*  97 */     ClientGunIndexLoader.loadGunIndex();
/*  98 */     ClientAttachmentIndexLoader.loadAttachmentIndex();
/*     */ 
/*     */     
/* 101 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 102 */     if (player != null && IGun.mainhandHoldGun((LivingEntity)player)) {
/* 103 */       AttachmentPropertyManager.postChangeEvent((LivingEntity)player, player.m_21205_());
/*     */     }
/*     */   }
/*     */   
/*     */   private static void createFolder() {
/* 108 */     File folder = CommonGunPackLoader.FOLDER.toFile();
/* 109 */     if (!folder.isDirectory()) {
/*     */       try {
/* 111 */         Files.createDirectories(folder.toPath(), (FileAttribute<?>[])new FileAttribute[0]);
/* 112 */       } catch (Exception e) {
/* 113 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkDefaultPack() {
/* 119 */     if (!((Boolean)OtherConfig.DEFAULT_PACK_DEBUG.get()).booleanValue()) {
/* 120 */       for (ResourceManager.ExtraEntry entry : ResourceManager.EXTRA_ENTRIES) {
/* 121 */         GetJarResources.copyModDirectory(entry.modMainClass(), entry.srcPath(), CommonGunPackLoader.FOLDER, entry.extraDirName());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void readAsset(File[] files) {
/* 127 */     for (File file : files) {
/* 128 */       if (file.isFile() && file.getName().endsWith(".zip")) {
/* 129 */         readZipAsset(file);
/*     */       }
/* 131 */       if (file.isDirectory()) {
/* 132 */         File[] subFiles = file.listFiles((dir, name) -> true);
/* 133 */         if (subFiles == null) {
/*     */           return;
/*     */         }
/* 136 */         for (File namespaceFile : subFiles) {
/* 137 */           readDirAsset(namespaceFile);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void readDirAsset(File root) {
/* 144 */     if (VersionChecker.match(root)) {
/* 145 */       GunDisplayLoader.load(root);
/* 146 */       AmmoDisplayLoader.load(root);
/* 147 */       AttachmentDisplayLoader.load(root);
/* 148 */       AttachmentSkinLoader.load(root);
/* 149 */       AnimationLoader.load(root);
/* 150 */       PlayerAnimatorCompat.loadAnimationFromFile(root);
/* 151 */       BedrockModelLoader.load(root);
/* 152 */       TextureLoader.load(root);
/* 153 */       SoundLoader.load(root);
/* 154 */       LanguageLoader.load(root);
/* 155 */       PackInfoLoader.load(root);
/*     */     } 
/*     */   }
/*     */   public static void readZipAsset(File file) {
/*     */     
/* 160 */     try { ZipFile zipFile = new ZipFile(file);
/*     */       
/* 162 */       try { if (VersionChecker.noneMatch(zipFile, file.toPath()))
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 209 */           zipFile.close(); return; }  Enumeration<? extends ZipEntry> iteration = zipFile.entries(); while (iteration.hasMoreElements()) { String path = ((ZipEntry)iteration.nextElement()).getName(); if (GunDisplayLoader.load(zipFile, path)) continue;  if (AmmoDisplayLoader.load(zipFile, path)) continue;  if (AttachmentDisplayLoader.load(zipFile, path)) continue;  if (AttachmentSkinLoader.load(zipFile, path)) continue;  if (AnimationLoader.load(zipFile, path)) continue;  if (PlayerAnimatorCompat.loadAnimationFromZip(zipFile, path)) continue;  if (BedrockModelLoader.load(zipFile, path)) continue;  if (TextureLoader.load(zipFile, path)) continue;  if (SoundLoader.load(zipFile, path)) continue;  if (LanguageLoader.load(zipFile, path)) continue;  PackInfoLoader.load(zipFile, path); }  zipFile.close(); } catch (Throwable throwable) { try { zipFile.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ioException)
/* 210 */     { ioException.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, ClientGunIndex>> getAllGuns() {
/* 215 */     return GUN_INDEX.entrySet();
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, ClientAmmoIndex>> getAllAmmo() {
/* 219 */     return AMMO_INDEX.entrySet();
/*     */   }
/*     */   
/*     */   public static Set<Map.Entry<ResourceLocation, ClientAttachmentIndex>> getAllAttachments() {
/* 223 */     return ATTACHMENT_INDEX.entrySet();
/*     */   }
/*     */   
/*     */   public static Optional<ClientGunIndex> getGunIndex(ResourceLocation registryName) {
/* 227 */     return Optional.ofNullable(GUN_INDEX.get(registryName));
/*     */   }
/*     */   
/*     */   public static Optional<ClientAmmoIndex> getAmmoIndex(ResourceLocation registryName) {
/* 231 */     return Optional.ofNullable(AMMO_INDEX.get(registryName));
/*     */   }
/*     */   
/*     */   public static Optional<ClientAttachmentIndex> getAttachmentIndex(ResourceLocation registryName) {
/* 235 */     return Optional.ofNullable(ATTACHMENT_INDEX.get(registryName));
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\ClientGunPackLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */