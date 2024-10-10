/*     */ package com.tacz.guns.client.resource.index;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.tacz.guns.client.model.BedrockAmmoModel;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoEntityDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoParticle;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoTransform;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.ShellDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.resource.pojo.AmmoIndexPOJO;
/*     */ import com.tacz.guns.util.ColorHex;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.commands.arguments.ParticleArgument;
/*     */ import net.minecraft.core.HolderLookup;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ClientAmmoIndex {
/*     */   private String name;
/*     */   @Nullable
/*     */   private BedrockAmmoModel ammoModel;
/*     */   @Nullable
/*     */   private ResourceLocation modelTextureLocation;
/*     */   private ResourceLocation slotTextureLocation;
/*     */   @Nullable
/*     */   private BedrockAmmoModel ammoEntityModel;
/*  34 */   private float[] tracerColor = new float[] { 1.0F, 1.0F, 1.0F }; @Nullable
/*     */   private ResourceLocation ammoEntityTextureLocation;
/*     */   @Nullable
/*     */   private BedrockAmmoModel shellModel;
/*     */   @Nullable
/*     */   private ResourceLocation shellTextureLocation;
/*     */   
/*     */   public static ClientAmmoIndex getInstance(AmmoIndexPOJO clientPojo) throws IllegalArgumentException {
/*  42 */     ClientAmmoIndex index = new ClientAmmoIndex();
/*  43 */     checkIndex(clientPojo, index);
/*  44 */     AmmoDisplay display = checkDisplay(clientPojo);
/*  45 */     checkName(clientPojo, index);
/*  46 */     checkTextureAndModel(display, index);
/*  47 */     checkSlotTexture(display, index);
/*  48 */     checkStackSize(clientPojo, index);
/*  49 */     checkAmmoEntity(display, index);
/*  50 */     checkShell(display, index);
/*  51 */     checkParticle(display, index);
/*  52 */     checkTracerColor(display, index);
/*  53 */     checkTransform(display, index);
/*  54 */     return index;
/*     */   } private int stackSize; @Nullable
/*     */   private AmmoParticle particle; private AmmoTransform transform; @Nullable
/*     */   private String tooltipKey; private static void checkIndex(AmmoIndexPOJO ammoIndexPOJO, ClientAmmoIndex index) {
/*  58 */     Preconditions.checkArgument((ammoIndexPOJO != null), "index object file is empty");
/*  59 */     index.tooltipKey = ammoIndexPOJO.getTooltip();
/*     */   }
/*     */   
/*     */   private static void checkName(AmmoIndexPOJO ammoIndexPOJO, ClientAmmoIndex index) {
/*  63 */     index.name = ammoIndexPOJO.getName();
/*  64 */     if (StringUtils.isBlank(index.name)) {
/*  65 */       index.name = "custom.tacz.error.no_name";
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static AmmoDisplay checkDisplay(AmmoIndexPOJO ammoIndexPOJO) {
/*  71 */     ResourceLocation pojoDisplay = ammoIndexPOJO.getDisplay();
/*  72 */     Preconditions.checkArgument((pojoDisplay != null), "index object missing display field");
/*     */     
/*  74 */     AmmoDisplay display = ClientAssetManager.INSTANCE.getAmmoDisplay(pojoDisplay);
/*  75 */     Preconditions.checkArgument((display != null), "there is no corresponding display file");
/*  76 */     return display;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkTextureAndModel(AmmoDisplay display, ClientAmmoIndex index) {
/*  81 */     ResourceLocation modelLocation = display.getModelLocation();
/*  82 */     if (modelLocation == null) {
/*     */       return;
/*     */     }
/*  85 */     BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(modelLocation);
/*  86 */     Preconditions.checkArgument((modelPOJO != null), "there is no corresponding model file");
/*     */     
/*  88 */     index.modelTextureLocation = display.getModelTexture();
/*     */     
/*  90 */     if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/*  91 */       index.ammoModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.LEGACY);
/*     */     }
/*     */     
/*  94 */     if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/*  95 */       index.ammoModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.NEW);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkSlotTexture(AmmoDisplay display, ClientAmmoIndex index) {
/* 101 */     index.slotTextureLocation = Objects.<ResourceLocation>requireNonNullElseGet(display.getSlotTextureLocation(), MissingTextureAtlasSprite::m_118071_);
/*     */   }
/*     */   
/*     */   private static void checkAmmoEntity(AmmoDisplay display, ClientAmmoIndex index) {
/* 105 */     AmmoEntityDisplay ammoEntity = display.getAmmoEntity();
/* 106 */     if (ammoEntity != null && ammoEntity.getModelLocation() != null && ammoEntity.getModelTexture() != null) {
/* 107 */       index.ammoEntityTextureLocation = ammoEntity.getModelTexture();
/* 108 */       ResourceLocation modelLocation = ammoEntity.getModelLocation();
/* 109 */       BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(modelLocation);
/* 110 */       if (modelPOJO == null) {
/*     */         return;
/*     */       }
/*     */       
/* 114 */       if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/* 115 */         index.ammoEntityModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.LEGACY);
/*     */       }
/*     */       
/* 118 */       if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/* 119 */         index.ammoEntityModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.NEW);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkShell(AmmoDisplay display, ClientAmmoIndex index) {
/* 125 */     ShellDisplay shellDisplay = display.getShellDisplay();
/* 126 */     if (shellDisplay != null && shellDisplay.getModelLocation() != null && shellDisplay.getModelTexture() != null) {
/* 127 */       index.shellTextureLocation = shellDisplay.getModelTexture();
/* 128 */       ResourceLocation modelLocation = shellDisplay.getModelLocation();
/* 129 */       BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(modelLocation);
/* 130 */       if (modelPOJO == null) {
/*     */         return;
/*     */       }
/*     */       
/* 134 */       if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/* 135 */         index.shellModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.LEGACY);
/*     */       }
/*     */       
/* 138 */       if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/* 139 */         index.shellModel = new BedrockAmmoModel(modelPOJO, BedrockVersion.NEW);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkParticle(AmmoDisplay display, ClientAmmoIndex index) {
/* 145 */     if (display.getParticle() != null) {
/*     */       try {
/* 147 */         AmmoParticle particle = display.getParticle();
/* 148 */         String name = particle.getName();
/* 149 */         if (StringUtils.isNoneBlank(new CharSequence[0])) {
/* 150 */           particle.setParticleOptions(ParticleArgument.m_247456_(new StringReader(name), (HolderLookup)BuiltInRegistries.f_257034_.m_255303_()));
/* 151 */           Preconditions.checkArgument((particle.getCount() > 0), "particle count must be greater than 0");
/* 152 */           Preconditions.checkArgument((particle.getLifeTime() > 0), "particle life time must be greater than 0");
/* 153 */           index.particle = particle;
/*     */         } 
/* 155 */       } catch (CommandSyntaxException e) {
/* 156 */         e.fillInStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkTracerColor(AmmoDisplay display, ClientAmmoIndex index) {
/* 162 */     String tracerColorText = display.getTracerColor();
/* 163 */     if (StringUtils.isNoneBlank(new CharSequence[] { tracerColorText })) {
/* 164 */       index.tracerColor = ColorHex.colorTextToRbgFloatArray(tracerColorText);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkTransform(AmmoDisplay display, ClientAmmoIndex index) {
/* 169 */     AmmoTransform readTransform = display.getTransform();
/* 170 */     if (readTransform == null || readTransform.getScale() == null) {
/* 171 */       index.transform = AmmoTransform.getDefault();
/*     */     } else {
/* 173 */       index.transform = display.getTransform();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkStackSize(AmmoIndexPOJO clientPojo, ClientAmmoIndex index) {
/* 178 */     index.stackSize = Math.max(clientPojo.getStackSize(), 1);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 182 */     return this.name;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getTooltipKey() {
/* 187 */     return this.tooltipKey;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockAmmoModel getAmmoModel() {
/* 192 */     return this.ammoModel;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getModelTextureLocation() {
/* 197 */     return this.modelTextureLocation;
/*     */   }
/*     */   
/*     */   public ResourceLocation getSlotTextureLocation() {
/* 201 */     return this.slotTextureLocation;
/*     */   }
/*     */   
/*     */   public int getStackSize() {
/* 205 */     return this.stackSize;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockAmmoModel getAmmoEntityModel() {
/* 210 */     return this.ammoEntityModel;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getAmmoEntityTextureLocation() {
/* 215 */     return this.ammoEntityTextureLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockAmmoModel getShellModel() {
/* 220 */     return this.shellModel;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getShellTextureLocation() {
/* 225 */     return this.shellTextureLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AmmoParticle getParticle() {
/* 230 */     return this.particle;
/*     */   }
/*     */   
/*     */   public float[] getTracerColor() {
/* 234 */     return this.tracerColor;
/*     */   }
/*     */   
/*     */   public AmmoTransform getTransform() {
/* 238 */     return this.transform;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\index\ClientAmmoIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */