/*     */ package com.tacz.guns.client.resource;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.audio.SoundBuffer;
/*     */ import com.tacz.guns.api.client.animation.gltf.AnimationStructure;
/*     */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*     */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimationFile;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.attachment.AttachmentDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.GunDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.client.resource.pojo.skin.attachment.AttachmentSkin;
/*     */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public enum ClientAssetManager {
/*     */   private final Map<ResourceLocation, BedrockGunModel> tempGunModelMap;
/*     */   private final Map<ResourceLocation, BedrockAttachmentModel> tempAttachmentModelMap;
/*     */   private final Map<String, Map<String, String>> languages;
/*     */   private final Map<ResourceLocation, SoundBuffer> soundBuffers;
/*     */   private final Map<ResourceLocation, BedrockModelPOJO> models;
/*     */   private final Map<ResourceLocation, BedrockAnimationFile> bedrockAnimations;
/*  29 */   INSTANCE; private final Map<ResourceLocation, AnimationStructure> gltfAnimations; private final Map<ResourceLocation, Map<ResourceLocation, AttachmentSkin>> attachmentSkins;
/*     */   private final Map<ResourceLocation, AttachmentDisplay> attachmentDisplays;
/*     */   
/*     */   ClientAssetManager() {
/*  33 */     this.customInfos = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  37 */     this.gunDisplays = Maps.newHashMap();
/*  38 */     this.ammoDisplays = Maps.newHashMap();
/*  39 */     this.attachmentDisplays = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.attachmentSkins = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.gltfAnimations = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  51 */     this.bedrockAnimations = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  55 */     this.models = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.soundBuffers = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.languages = Maps.newHashMap();
/*     */     
/*  65 */     this.tempAttachmentModelMap = Maps.newHashMap();
/*     */     
/*  67 */     this.tempGunModelMap = Maps.newHashMap();
/*     */   } private final Map<ResourceLocation, AmmoDisplay> ammoDisplays; private final Map<ResourceLocation, GunDisplay> gunDisplays; private final Map<String, PackInfo> customInfos;
/*     */   @Nullable
/*     */   private static BedrockAttachmentModel getAttachmentModel(BedrockModelPOJO modelPOJO) {
/*  71 */     BedrockAttachmentModel attachmentModel = null;
/*     */     
/*  73 */     if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/*  74 */       attachmentModel = new BedrockAttachmentModel(modelPOJO, BedrockVersion.LEGACY);
/*     */     }
/*     */     
/*  77 */     if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/*  78 */       attachmentModel = new BedrockAttachmentModel(modelPOJO, BedrockVersion.NEW);
/*     */     }
/*  80 */     return attachmentModel;
/*     */   }
/*     */   
/*     */   public void putPackInfo(String namespace, PackInfo info) {
/*  84 */     this.customInfos.put(namespace, info);
/*     */   }
/*     */   
/*     */   public void putGunDisplay(ResourceLocation registryName, GunDisplay display) {
/*  88 */     this.gunDisplays.put(registryName, display);
/*     */   }
/*     */   
/*     */   public void putAmmoDisplay(ResourceLocation registryName, AmmoDisplay display) {
/*  92 */     this.ammoDisplays.put(registryName, display);
/*     */   }
/*     */   
/*     */   public void putAttachmentDisplay(ResourceLocation registryName, AttachmentDisplay display) {
/*  96 */     this.attachmentDisplays.put(registryName, display);
/*     */   }
/*     */   
/*     */   public void putAttachmentSkin(ResourceLocation registryName, AttachmentSkin skin) {
/* 100 */     this.attachmentSkins.compute(skin.getParent(), (name, map) -> {
/*     */           if (map == null) {
/*     */             map = Maps.newHashMap();
/*     */           }
/*     */           map.put(registryName, skin);
/*     */           return map;
/*     */         });
/*     */   }
/*     */   
/*     */   public void putGltfAnimation(ResourceLocation registryName, AnimationStructure animation) {
/* 110 */     this.gltfAnimations.put(registryName, animation);
/*     */   }
/*     */   
/*     */   public void putBedrockAnimation(ResourceLocation registryName, BedrockAnimationFile bedrockAnimationFile) {
/* 114 */     this.bedrockAnimations.put(registryName, bedrockAnimationFile);
/*     */   }
/*     */   
/*     */   public void putModel(ResourceLocation registryName, BedrockModelPOJO model) {
/* 118 */     this.models.put(registryName, model);
/*     */   }
/*     */   
/*     */   public void putSoundBuffer(ResourceLocation registryName, SoundBuffer soundBuffer) {
/* 122 */     this.soundBuffers.put(registryName, soundBuffer);
/*     */   }
/*     */   
/*     */   public void putLanguage(String region, Map<String, String> lang) {
/* 126 */     Map<String, String> languageMaps = this.languages.getOrDefault(region, Maps.newHashMap());
/* 127 */     languageMaps.putAll(lang);
/* 128 */     this.languages.put(region, languageMaps);
/*     */   }
/*     */   
/*     */   public GunDisplay getGunDisplay(ResourceLocation registryName) {
/* 132 */     return this.gunDisplays.get(registryName);
/*     */   }
/*     */   
/*     */   public AmmoDisplay getAmmoDisplay(ResourceLocation registryName) {
/* 136 */     return this.ammoDisplays.get(registryName);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AttachmentDisplay getAttachmentDisplay(ResourceLocation registryName) {
/* 141 */     return this.attachmentDisplays.get(registryName);
/*     */   }
/*     */   
/*     */   public Map<ResourceLocation, AttachmentSkin> getAttachmentSkins(ResourceLocation registryName) {
/* 145 */     return this.attachmentSkins.get(registryName);
/*     */   }
/*     */   
/*     */   public AnimationStructure getGltfAnimations(ResourceLocation registryName) {
/* 149 */     return this.gltfAnimations.get(registryName);
/*     */   }
/*     */   
/*     */   public BedrockAnimationFile getBedrockAnimations(ResourceLocation registryName) {
/* 153 */     return this.bedrockAnimations.get(registryName);
/*     */   }
/*     */   
/*     */   public BedrockModelPOJO getModels(ResourceLocation registryName) {
/* 157 */     return this.models.get(registryName);
/*     */   }
/*     */   
/*     */   public SoundBuffer getSoundBuffers(ResourceLocation registryName) {
/* 161 */     return this.soundBuffers.get(registryName);
/*     */   }
/*     */   
/*     */   public Map<String, String> getLanguages(String region) {
/* 165 */     return this.languages.get(region);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PackInfo getPackInfo(ResourceLocation id) {
/* 170 */     return this.customInfos.get(id.m_135827_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BedrockAttachmentModel getOrLoadAttachmentModel(@Nullable ResourceLocation modelLocation) {
/* 178 */     if (modelLocation == null) {
/* 179 */       return null;
/*     */     }
/* 181 */     BedrockAttachmentModel model = this.tempAttachmentModelMap.get(modelLocation);
/* 182 */     if (model != null) {
/* 183 */       return model;
/*     */     }
/* 185 */     BedrockModelPOJO modelPOJO = getModels(modelLocation);
/* 186 */     if (modelPOJO == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     BedrockAttachmentModel attachmentModel = getAttachmentModel(modelPOJO);
/* 190 */     if (attachmentModel == null) {
/* 191 */       return null;
/*     */     }
/* 193 */     this.tempAttachmentModelMap.put(modelLocation, attachmentModel);
/* 194 */     return attachmentModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAll() {
/* 201 */     this.customInfos.clear();
/* 202 */     this.gunDisplays.clear();
/* 203 */     this.ammoDisplays.clear();
/* 204 */     this.attachmentDisplays.clear();
/* 205 */     this.attachmentSkins.clear();
/* 206 */     this.gltfAnimations.clear();
/* 207 */     this.bedrockAnimations.clear();
/* 208 */     this.models.clear();
/* 209 */     this.soundBuffers.clear();
/* 210 */     this.languages.clear();
/* 211 */     this.tempGunModelMap.clear();
/* 212 */     this.tempAttachmentModelMap.clear();
/* 213 */     PlayerAnimatorCompat.clearAllAnimationCache();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\ClientAssetManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */