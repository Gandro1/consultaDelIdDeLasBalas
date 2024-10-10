/*     */ package com.tacz.guns.client.resource.index;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.pojo.display.attachment.AttachmentDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.attachment.AttachmentLod;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.client.resource.pojo.skin.attachment.AttachmentSkin;
/*     */ import com.tacz.guns.resource.CommonAssetManager;
/*     */ import com.tacz.guns.resource.pojo.AttachmentIndexPOJO;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ClientAttachmentIndex
/*     */ {
/*  26 */   private final Map<ResourceLocation, ClientAttachmentSkinIndex> skinIndexMap = Maps.newHashMap();
/*     */   
/*     */   private String name;
/*     */   @Nullable
/*     */   private BedrockAttachmentModel attachmentModel;
/*     */   @Nullable
/*     */   private ResourceLocation modelTexture;
/*  33 */   private float fov = 70.0F; @Nullable
/*     */   private Pair<BedrockAttachmentModel, ResourceLocation> lodModel; private ResourceLocation slotTexture; private AttachmentData data;
/*     */   private float[] zoom;
/*     */   private boolean isScope;
/*     */   private boolean isSight;
/*     */   private boolean showMuzzle;
/*     */   @Nullable
/*     */   private String adapterNodeName;
/*     */   @Nullable
/*     */   private String tooltipKey;
/*     */   private Map<String, ResourceLocation> sounds;
/*     */   
/*     */   public static ClientAttachmentIndex getInstance(ResourceLocation registryName, AttachmentIndexPOJO indexPOJO) throws IllegalArgumentException {
/*  46 */     ClientAttachmentIndex index = new ClientAttachmentIndex();
/*  47 */     checkIndex(indexPOJO, index);
/*  48 */     AttachmentDisplay display = checkDisplay(indexPOJO, index);
/*  49 */     checkData(indexPOJO, index);
/*  50 */     checkName(indexPOJO, index);
/*  51 */     checkSlotTexture(display, index);
/*  52 */     checkTextureAndModel(display, index);
/*  53 */     checkLod(display, index);
/*  54 */     checkSkins(registryName, index);
/*  55 */     checkSounds(display, index);
/*  56 */     return index;
/*     */   }
/*     */   
/*     */   private static void checkIndex(AttachmentIndexPOJO attachmentIndexPOJO, ClientAttachmentIndex index) {
/*  60 */     Preconditions.checkArgument((attachmentIndexPOJO != null), "index object file is empty");
/*  61 */     index.tooltipKey = attachmentIndexPOJO.getTooltip();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static AttachmentDisplay checkDisplay(AttachmentIndexPOJO indexPOJO, ClientAttachmentIndex index) {
/*  66 */     ResourceLocation pojoDisplay = indexPOJO.getDisplay();
/*  67 */     Preconditions.checkArgument((pojoDisplay != null), "index object missing display field");
/*  68 */     AttachmentDisplay display = ClientAssetManager.INSTANCE.getAttachmentDisplay(pojoDisplay);
/*  69 */     Preconditions.checkArgument((display != null), "there is no corresponding display file");
/*  70 */     Preconditions.checkArgument((display.getFov() > 0.0F), "fov must > 0");
/*  71 */     index.fov = display.getFov();
/*  72 */     index.zoom = display.getZoom();
/*  73 */     if (index.zoom != null) {
/*  74 */       for (int i = 0; i < index.zoom.length; i++) {
/*  75 */         if (index.zoom[i] < 1.0F) {
/*  76 */           throw new IllegalArgumentException("zoom must >= 1");
/*     */         }
/*     */       } 
/*     */     }
/*  80 */     index.isScope = display.isScope();
/*  81 */     index.isSight = display.isSight();
/*  82 */     index.adapterNodeName = display.getAdapterNodeName();
/*  83 */     index.showMuzzle = display.isShowMuzzle();
/*  84 */     return display;
/*     */   }
/*     */   
/*     */   private static void checkData(AttachmentIndexPOJO indexPOJO, ClientAttachmentIndex index) {
/*  88 */     ResourceLocation dataId = indexPOJO.getData();
/*  89 */     Preconditions.checkArgument((dataId != null), "index object missing pojoData field");
/*  90 */     AttachmentData data = CommonAssetManager.INSTANCE.getAttachmentData(dataId);
/*  91 */     Preconditions.checkArgument((data != null), "there is no corresponding data file");
/*     */     
/*  93 */     index.data = data;
/*     */   }
/*     */   
/*     */   private static void checkName(AttachmentIndexPOJO indexPOJO, ClientAttachmentIndex index) {
/*  97 */     index.name = indexPOJO.getName();
/*  98 */     if (StringUtils.isBlank(index.name)) {
/*  99 */       index.name = "custom.tacz.error.no_name";
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkSlotTexture(AttachmentDisplay display, ClientAttachmentIndex index) {
/* 104 */     index.slotTexture = Objects.<ResourceLocation>requireNonNullElseGet(display.getSlotTextureLocation(), MissingTextureAtlasSprite::m_118071_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkTextureAndModel(AttachmentDisplay display, ClientAttachmentIndex index) {
/* 109 */     index.attachmentModel = ClientAssetManager.INSTANCE.getOrLoadAttachmentModel(display.getModel());
/* 110 */     if (index.attachmentModel != null) {
/* 111 */       index.attachmentModel.setIsScope(display.isScope());
/* 112 */       index.attachmentModel.setIsSight(display.isSight());
/*     */     } 
/* 114 */     index.modelTexture = display.getTexture();
/*     */   }
/*     */   
/*     */   private static void checkLod(AttachmentDisplay display, ClientAttachmentIndex index) {
/* 118 */     AttachmentLod gunLod = display.getAttachmentLod();
/* 119 */     if (gunLod != null) {
/* 120 */       ResourceLocation texture = gunLod.getModelTexture();
/* 121 */       if (gunLod.getModelLocation() == null) {
/*     */         return;
/*     */       }
/* 124 */       if (texture == null) {
/*     */         return;
/*     */       }
/* 127 */       BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(gunLod.getModelLocation());
/* 128 */       if (modelPOJO == null) {
/*     */         return;
/*     */       }
/*     */       
/* 132 */       if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/* 133 */         BedrockAttachmentModel model = new BedrockAttachmentModel(modelPOJO, BedrockVersion.LEGACY);
/* 134 */         index.lodModel = Pair.of(model, texture);
/*     */       } 
/*     */       
/* 137 */       if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/* 138 */         BedrockAttachmentModel model = new BedrockAttachmentModel(modelPOJO, BedrockVersion.NEW);
/* 139 */         index.lodModel = Pair.of(model, texture);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkSkins(ResourceLocation registryName, ClientAttachmentIndex index) {
/* 145 */     Map<ResourceLocation, AttachmentSkin> skins = ClientAssetManager.INSTANCE.getAttachmentSkins(registryName);
/* 146 */     if (skins != null) {
/* 147 */       for (Map.Entry<ResourceLocation, AttachmentSkin> entry : skins.entrySet()) {
/* 148 */         ClientAttachmentSkinIndex skinIndex = ClientAttachmentSkinIndex.getInstance(entry.getValue());
/* 149 */         index.skinIndexMap.put(entry.getKey(), skinIndex);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkSounds(AttachmentDisplay display, ClientAttachmentIndex index) {
/* 155 */     Map<String, ResourceLocation> displaySounds = display.getSounds();
/* 156 */     if (displaySounds == null) {
/* 157 */       index.sounds = Maps.newHashMap();
/*     */       return;
/*     */     } 
/* 160 */     index.sounds = displaySounds;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 164 */     return this.name;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getTooltipKey() {
/* 169 */     return this.tooltipKey;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockAttachmentModel getAttachmentModel() {
/* 174 */     return this.attachmentModel;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getModelTexture() {
/* 179 */     return this.modelTexture;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Pair<BedrockAttachmentModel, ResourceLocation> getLodModel() {
/* 184 */     return this.lodModel;
/*     */   }
/*     */   
/*     */   public ResourceLocation getSlotTexture() {
/* 188 */     return this.slotTexture;
/*     */   }
/*     */   
/*     */   public float getFov() {
/* 192 */     return this.fov;
/*     */   }
/*     */   
/*     */   public float[] getZoom() {
/* 196 */     return this.zoom;
/*     */   }
/*     */   
/*     */   public AttachmentData getData() {
/* 200 */     return this.data;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ClientAttachmentSkinIndex getSkinIndex(@Nullable ResourceLocation skinName) {
/* 205 */     if (skinName == null) {
/* 206 */       return null;
/*     */     }
/* 208 */     return this.skinIndexMap.get(skinName);
/*     */   }
/*     */   
/*     */   public boolean isScope() {
/* 212 */     return this.isScope;
/*     */   }
/*     */   
/*     */   public boolean isSight() {
/* 216 */     return this.isSight;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getAdapterNodeName() {
/* 221 */     return this.adapterNodeName;
/*     */   }
/*     */   
/*     */   public boolean isShowMuzzle() {
/* 225 */     return this.showMuzzle;
/*     */   }
/*     */   
/*     */   public Map<String, ResourceLocation> getSounds() {
/* 229 */     return this.sounds;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\index\ClientAttachmentIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */