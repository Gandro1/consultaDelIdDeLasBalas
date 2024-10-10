/*    */ package com.tacz.guns.resource;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public enum CommonAssetManager {
/*    */   private final Map<ResourceLocation, Set<String>> allowAttachmentTags;
/* 16 */   INSTANCE; private final Map<ResourceLocation, Set<String>> attachmentTags; private final Map<ResourceLocation, GunSmithTableRecipe> gunSmithTableRecipes;
/*    */   private final Map<ResourceLocation, AttachmentData> attachmentData;
/*    */   private final Map<ResourceLocation, GunData> gunData;
/*    */   
/*    */   CommonAssetManager() {
/* 21 */     this.gunData = Maps.newHashMap();
/*    */     
/* 23 */     this.attachmentData = Maps.newHashMap();
/*    */     
/* 25 */     this.gunSmithTableRecipes = Maps.newHashMap();
/*    */     
/* 27 */     this.attachmentTags = Maps.newHashMap();
/*    */     
/* 29 */     this.allowAttachmentTags = Maps.newHashMap();
/*    */   }
/*    */   public void putGunData(ResourceLocation registryName, GunData data) {
/* 32 */     this.gunData.put(registryName, data);
/*    */   }
/*    */   
/*    */   public GunData getGunData(ResourceLocation registryName) {
/* 36 */     return this.gunData.get(registryName);
/*    */   }
/*    */   
/*    */   public void putAttachmentData(ResourceLocation registryName, AttachmentData data) {
/* 40 */     this.attachmentData.put(registryName, data);
/*    */   }
/*    */   
/*    */   public AttachmentData getAttachmentData(ResourceLocation registryName) {
/* 44 */     return this.attachmentData.get(registryName);
/*    */   }
/*    */   
/*    */   public void putRecipe(ResourceLocation registryName, GunSmithTableRecipe recipe) {
/* 48 */     this.gunSmithTableRecipes.put(registryName, recipe);
/*    */   }
/*    */   
/*    */   public Optional<GunSmithTableRecipe> getRecipe(ResourceLocation recipeId) {
/* 52 */     return Optional.ofNullable(this.gunSmithTableRecipes.get(recipeId));
/*    */   }
/*    */   
/*    */   public Map<ResourceLocation, GunSmithTableRecipe> getAllRecipes() {
/* 56 */     return this.gunSmithTableRecipes;
/*    */   }
/*    */   
/*    */   public void putAttachmentTags(ResourceLocation registryName, List<String> tags) {
/* 60 */     ((Set<String>)this.attachmentTags.computeIfAbsent(registryName, id -> Sets.newHashSet())).addAll(tags);
/*    */   }
/*    */   
/*    */   public Set<String> getAttachmentTags(ResourceLocation registryName) {
/* 64 */     return this.attachmentTags.get(registryName);
/*    */   }
/*    */   
/*    */   public void putAllowAttachmentTags(ResourceLocation registryName, List<String> tags) {
/* 68 */     ((Set<String>)this.allowAttachmentTags.computeIfAbsent(registryName, id -> Sets.newHashSet())).addAll(tags);
/*    */   }
/*    */   
/*    */   public Set<String> getAllowAttachmentTags(ResourceLocation registryName) {
/* 72 */     return this.allowAttachmentTags.get(registryName);
/*    */   }
/*    */   
/*    */   public void clearAll() {
/* 76 */     this.gunData.clear();
/* 77 */     this.attachmentData.clear();
/* 78 */     this.attachmentTags.clear();
/* 79 */     this.allowAttachmentTags.clear();
/*    */   }
/*    */   
/*    */   public void clearRecipes() {
/* 83 */     this.gunSmithTableRecipes.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\CommonAssetManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */