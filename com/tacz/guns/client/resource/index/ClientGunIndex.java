/*     */ package com.tacz.guns.client.resource.index;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.tacz.guns.api.client.animation.AnimationController;
/*     */ import com.tacz.guns.api.client.animation.AnimationListenerSupplier;
/*     */ import com.tacz.guns.api.client.animation.Animations;
/*     */ import com.tacz.guns.api.client.animation.ObjectAnimation;
/*     */ import com.tacz.guns.api.client.animation.gltf.AnimationStructure;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimationFile;
/*     */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoParticle;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.AmmoCountStyle;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.ControllableData;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.DefaultAnimation;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.GunAmmo;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.GunDisplay;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.GunLod;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.GunTransform;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.LayerGunShow;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.MuzzleFlash;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.ShellEjection;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.TextShow;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.resource.pojo.GunIndexPOJO;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.sound.SoundManager;
/*     */ import com.tacz.guns.util.ColorHex;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ClientGunIndex {
/*  47 */   private String thirdPersonAnimation = "empty"; private String name; private BedrockGunModel gunModel; @Nullable
/*     */   private Pair<BedrockGunModel, ResourceLocation> lodModel;
/*     */   private GunAnimationStateMachine animationStateMachine;
/*     */   @Nullable
/*  51 */   private ResourceLocation playerAnimator3rd = new ResourceLocation("tacz", "rifle_default.player_animation"); private Map<String, ResourceLocation> sounds; private GunTransform transform; private GunData gunData; private ResourceLocation modelTexture; private ResourceLocation slotTexture;
/*     */   private ResourceLocation hudTexture;
/*     */   @Nullable
/*     */   private ResourceLocation hudEmptyTexture;
/*     */   private String type;
/*     */   private String itemType;
/*     */   @Nullable
/*     */   private ShellEjection shellEjection;
/*     */   @Nullable
/*     */   private MuzzleFlash muzzleFlash;
/*     */   private LayerGunShow offhandShow;
/*     */   @Nullable
/*     */   private Int2ObjectArrayMap<LayerGunShow> hotbarShow;
/*     */   private float ironZoom;
/*     */   private boolean showCrosshair = false;
/*     */   @Nullable
/*     */   private AmmoParticle particle;
/*  68 */   private float[] tracerColor = null;
/*     */   private EnumMap<FireMode, ControllableData> controllableData;
/*  70 */   private AmmoCountStyle ammoCountStyle = AmmoCountStyle.NORMAL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClientGunIndex getInstance(GunIndexPOJO gunIndexPOJO) throws IllegalArgumentException {
/*  76 */     ClientGunIndex index = new ClientGunIndex();
/*  77 */     checkIndex(gunIndexPOJO, index);
/*  78 */     GunDisplay display = checkDisplay(gunIndexPOJO);
/*  79 */     checkData(gunIndexPOJO, index);
/*  80 */     checkName(gunIndexPOJO, index);
/*  81 */     checkTextureAndModel(display, index);
/*  82 */     checkLod(display, index);
/*  83 */     checkSlotTexture(display, index);
/*  84 */     checkHUDTexture(display, index);
/*  85 */     checkAnimation(display, index);
/*  86 */     checkSounds(display, index);
/*  87 */     checkTransform(display, index);
/*  88 */     checkShellEjection(display, index);
/*  89 */     checkGunAmmo(display, index);
/*  90 */     checkMuzzleFlash(display, index);
/*  91 */     checkLayerGunShow(display, index);
/*  92 */     checkIronZoom(display, index);
/*  93 */     checkTextShow(display, index);
/*  94 */     index.showCrosshair = display.isShowCrosshair();
/*  95 */     index.controllableData = display.getControllableData();
/*  96 */     index.ammoCountStyle = display.getAmmoCountStyle();
/*  97 */     return index;
/*     */   }
/*     */   
/*     */   private static void checkIndex(GunIndexPOJO gunIndexPOJO, ClientGunIndex index) {
/* 101 */     Preconditions.checkArgument((gunIndexPOJO != null), "index object file is empty");
/* 102 */     Preconditions.checkArgument(StringUtils.isNoneBlank(new CharSequence[] { gunIndexPOJO.getType() }, ), "index object missing type field");
/* 103 */     index.type = gunIndexPOJO.getType();
/* 104 */     index.itemType = gunIndexPOJO.getItemType();
/*     */   }
/*     */   
/*     */   private static void checkName(GunIndexPOJO gunIndexPOJO, ClientGunIndex index) {
/* 108 */     index.name = gunIndexPOJO.getName();
/* 109 */     if (StringUtils.isBlank(index.name)) {
/* 110 */       index.name = "custom.tacz.error.no_name";
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkData(GunIndexPOJO gunIndexPOJO, ClientGunIndex index) {
/* 115 */     ResourceLocation pojoData = gunIndexPOJO.getData();
/* 116 */     Preconditions.checkArgument((pojoData != null), "index object missing pojoData field");
/* 117 */     GunData data = CommonAssetManager.INSTANCE.getGunData(pojoData);
/* 118 */     Preconditions.checkArgument((data != null), "there is no corresponding data file");
/*     */     
/* 120 */     index.gunData = data;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static GunDisplay checkDisplay(GunIndexPOJO gunIndexPOJO) {
/* 125 */     ResourceLocation pojoDisplay = gunIndexPOJO.getDisplay();
/* 126 */     Preconditions.checkArgument((pojoDisplay != null), "index object missing display field");
/* 127 */     GunDisplay display = ClientAssetManager.INSTANCE.getGunDisplay(pojoDisplay);
/* 128 */     Preconditions.checkArgument((display != null), "there is no corresponding display file");
/* 129 */     return display;
/*     */   }
/*     */   
/*     */   private static void checkIronZoom(GunDisplay display, ClientGunIndex index) {
/* 133 */     index.ironZoom = display.getIronZoom();
/* 134 */     if (index.ironZoom < 1.0F) {
/* 135 */       index.ironZoom = 1.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkTextShow(GunDisplay display, ClientGunIndex index) {
/* 140 */     Map<String, TextShow> textShowMap = Maps.newHashMap();
/* 141 */     display.getTextShows().forEach((key, value) -> {
/*     */           if (StringUtils.isNoneBlank(new CharSequence[] { key })) {
/*     */             int color = ColorHex.colorTextToRbgInt(value.getColorText());
/*     */             value.setColorInt(color);
/*     */             textShowMap.put(key, value);
/*     */           } 
/*     */         });
/* 148 */     index.gunModel.setTextShowList(textShowMap);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkTextureAndModel(GunDisplay display, ClientGunIndex index) {
/* 153 */     ResourceLocation modelLocation = display.getModelLocation();
/* 154 */     Preconditions.checkArgument((modelLocation != null), "display object missing model field");
/* 155 */     BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(modelLocation);
/* 156 */     Preconditions.checkArgument((modelPOJO != null), "there is no corresponding model file");
/*     */     
/* 158 */     ResourceLocation textureLocation = display.getModelTexture();
/* 159 */     Preconditions.checkArgument((textureLocation != null), "missing default texture");
/* 160 */     index.modelTexture = textureLocation;
/*     */     
/* 162 */     if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/* 163 */       index.gunModel = new BedrockGunModel(modelPOJO, BedrockVersion.LEGACY);
/*     */     }
/*     */     
/* 166 */     if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/* 167 */       index.gunModel = new BedrockGunModel(modelPOJO, BedrockVersion.NEW);
/*     */     }
/* 169 */     Preconditions.checkArgument((index.gunModel != null), "there is no model data in the model file");
/*     */   }
/*     */   
/*     */   private static void checkLod(GunDisplay display, ClientGunIndex index) {
/* 173 */     GunLod gunLod = display.getGunLod();
/* 174 */     if (gunLod != null) {
/* 175 */       ResourceLocation texture = gunLod.getModelTexture();
/* 176 */       if (gunLod.getModelLocation() == null) {
/*     */         return;
/*     */       }
/* 179 */       if (texture == null) {
/*     */         return;
/*     */       }
/* 182 */       BedrockModelPOJO modelPOJO = ClientAssetManager.INSTANCE.getModels(gunLod.getModelLocation());
/* 183 */       if (modelPOJO == null) {
/*     */         return;
/*     */       }
/*     */       
/* 187 */       if (BedrockVersion.isLegacyVersion(modelPOJO) && modelPOJO.getGeometryModelLegacy() != null) {
/* 188 */         BedrockGunModel model = new BedrockGunModel(modelPOJO, BedrockVersion.LEGACY);
/* 189 */         index.lodModel = Pair.of(model, texture);
/*     */       } 
/*     */       
/* 192 */       if (BedrockVersion.isNewVersion(modelPOJO) && modelPOJO.getGeometryModelNew() != null) {
/* 193 */         BedrockGunModel model = new BedrockGunModel(modelPOJO, BedrockVersion.NEW);
/* 194 */         index.lodModel = Pair.of(model, texture);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static void checkAnimation(GunDisplay display, ClientGunIndex index) {
/*     */     AnimationController controller;
/* 200 */     ResourceLocation location = display.getAnimationLocation();
/*     */     
/* 202 */     if (location == null) {
/* 203 */       controller = new AnimationController(Lists.newArrayList(), (AnimationListenerSupplier)index.gunModel);
/*     */     } else {
/* 205 */       AnimationStructure gltfAnimations = ClientAssetManager.INSTANCE.getGltfAnimations(location);
/* 206 */       BedrockAnimationFile bedrockAnimationFile = ClientAssetManager.INSTANCE.getBedrockAnimations(location);
/* 207 */       if (bedrockAnimationFile != null) {
/*     */         
/* 209 */         controller = Animations.createControllerFromBedrock(bedrockAnimationFile, (AnimationListenerSupplier)index.gunModel);
/* 210 */       } else if (gltfAnimations != null) {
/*     */         
/* 212 */         controller = Animations.createControllerFromGltf(gltfAnimations, (AnimationListenerSupplier)index.gunModel);
/*     */       } else {
/* 214 */         throw new IllegalArgumentException("animation not found: " + location);
/*     */       } 
/*     */       
/* 217 */       DefaultAnimation defaultAnimation = display.getDefaultAnimation();
/* 218 */       if (defaultAnimation != null) {
/* 219 */         switch (defaultAnimation) {
/*     */           case RIFLE:
/* 221 */             for (ObjectAnimation animation : InternalAssetLoader.getDefaultRifleAnimations()) {
/* 222 */               controller.providePrototypeIfAbsent(animation.name, () -> new ObjectAnimation(animation));
/*     */             }
/*     */             break;
/*     */           case PISTOL:
/* 226 */             for (ObjectAnimation animation : InternalAssetLoader.getDefaultPistolAnimations()) {
/* 227 */               controller.providePrototypeIfAbsent(animation.name, () -> new ObjectAnimation(animation));
/*     */             }
/*     */             break;
/*     */         } 
/*     */       
/*     */       }
/*     */     } 
/* 234 */     index.animationStateMachine = new GunAnimationStateMachine(controller);
/*     */     
/* 236 */     if (StringUtils.isNoneBlank(new CharSequence[] { display.getThirdPersonAnimation() })) {
/* 237 */       index.thirdPersonAnimation = display.getThirdPersonAnimation();
/*     */     }
/*     */     
/* 240 */     if (display.getPlayerAnimator3rd() != null) {
/* 241 */       index.playerAnimator3rd = display.getPlayerAnimator3rd();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkSounds(GunDisplay display, ClientGunIndex index) {
/* 246 */     index.sounds = Maps.newHashMap();
/* 247 */     Map<String, ResourceLocation> soundMaps = display.getSounds();
/* 248 */     if (soundMaps == null || soundMaps.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 252 */     soundMaps.putIfAbsent(SoundManager.DRY_FIRE_SOUND, new ResourceLocation("tacz", SoundManager.DRY_FIRE_SOUND));
/* 253 */     soundMaps.putIfAbsent(SoundManager.FIRE_SELECT, new ResourceLocation("tacz", SoundManager.FIRE_SELECT));
/* 254 */     soundMaps.putIfAbsent(SoundManager.HEAD_HIT_SOUND, new ResourceLocation("tacz", SoundManager.HEAD_HIT_SOUND));
/* 255 */     soundMaps.putIfAbsent(SoundManager.FLESH_HIT_SOUND, new ResourceLocation("tacz", SoundManager.FLESH_HIT_SOUND));
/* 256 */     soundMaps.putIfAbsent(SoundManager.KILL_SOUND, new ResourceLocation("tacz", SoundManager.KILL_SOUND));
/* 257 */     soundMaps.putIfAbsent(SoundManager.MELEE_BAYONET, new ResourceLocation("tacz", "melee_bayonet/melee_bayonet_01"));
/* 258 */     soundMaps.putIfAbsent(SoundManager.MELEE_STOCK, new ResourceLocation("tacz", "melee_stock/melee_stock_01"));
/* 259 */     soundMaps.putIfAbsent(SoundManager.MELEE_PUSH, new ResourceLocation("tacz", "melee_stock/melee_stock_02"));
/* 260 */     index.sounds.putAll(soundMaps);
/*     */   }
/*     */   
/*     */   private static void checkTransform(GunDisplay display, ClientGunIndex index) {
/* 264 */     GunTransform readTransform = display.getTransform();
/* 265 */     if (readTransform == null || readTransform.getScale() == null) {
/* 266 */       index.transform = GunTransform.getDefault();
/*     */     } else {
/* 268 */       index.transform = display.getTransform();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkSlotTexture(GunDisplay display, ClientGunIndex index) {
/* 274 */     index.slotTexture = Objects.<ResourceLocation>requireNonNullElseGet(display.getSlotTextureLocation(), MissingTextureAtlasSprite::m_118071_);
/*     */   }
/*     */   
/*     */   private static void checkHUDTexture(GunDisplay display, ClientGunIndex index) {
/* 278 */     index.hudTexture = Objects.<ResourceLocation>requireNonNullElseGet(display.getHudTextureLocation(), MissingTextureAtlasSprite::m_118071_);
/* 279 */     index.hudEmptyTexture = display.getHudEmptyTextureLocation();
/*     */   }
/*     */   
/*     */   private static void checkShellEjection(GunDisplay display, ClientGunIndex index) {
/* 283 */     index.shellEjection = display.getShellEjection();
/*     */   }
/*     */   
/*     */   private static void checkGunAmmo(GunDisplay display, ClientGunIndex index) {
/* 287 */     GunAmmo displayGunAmmo = display.getGunAmmo();
/* 288 */     if (displayGunAmmo == null) {
/*     */       return;
/*     */     }
/* 291 */     String tracerColorText = displayGunAmmo.getTracerColor();
/* 292 */     if (StringUtils.isNoneBlank(new CharSequence[] { tracerColorText })) {
/* 293 */       index.tracerColor = ColorHex.colorTextToRbgFloatArray(tracerColorText);
/*     */     }
/* 295 */     AmmoParticle particle = displayGunAmmo.getParticle();
/* 296 */     if (particle != null) {
/*     */       try {
/* 298 */         String name = particle.getName();
/* 299 */         if (StringUtils.isNoneBlank(new CharSequence[0])) {
/* 300 */           particle.setParticleOptions(ParticleArgument.m_247456_(new StringReader(name), (HolderLookup)BuiltInRegistries.f_257034_.m_255303_()));
/* 301 */           Preconditions.checkArgument((particle.getCount() > 0), "particle count must be greater than 0");
/* 302 */           Preconditions.checkArgument((particle.getLifeTime() > 0), "particle life time must be greater than 0");
/* 303 */           index.particle = particle;
/*     */         } 
/* 305 */       } catch (CommandSyntaxException e) {
/* 306 */         e.fillInStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkMuzzleFlash(GunDisplay display, ClientGunIndex index) {
/* 312 */     index.muzzleFlash = display.getMuzzleFlash();
/* 313 */     if (index.muzzleFlash != null && index.muzzleFlash.getTexture() == null) {
/* 314 */       index.muzzleFlash = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private static void checkLayerGunShow(GunDisplay display, ClientGunIndex index) {
/* 319 */     index.offhandShow = display.getOffhandShow();
/* 320 */     if (index.offhandShow == null) {
/* 321 */       index.offhandShow = new LayerGunShow();
/*     */     }
/* 323 */     Map<String, LayerGunShow> show = display.getHotbarShow();
/* 324 */     if (show == null || show.isEmpty()) {
/*     */       return;
/*     */     }
/* 327 */     index.hotbarShow = new Int2ObjectArrayMap();
/* 328 */     for (String key : show.keySet()) {
/*     */       try {
/* 330 */         index.hotbarShow.put(Integer.parseInt(key), show.get(key));
/* 331 */       } catch (NumberFormatException e) {
/* 332 */         throw new IllegalArgumentException("index number is error: " + key);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getType() {
/* 338 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getItemType() {
/* 342 */     return this.itemType;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 346 */     return this.name;
/*     */   }
/*     */   
/*     */   public BedrockGunModel getGunModel() {
/* 350 */     return this.gunModel;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Pair<BedrockGunModel, ResourceLocation> getLodModel() {
/* 355 */     return this.lodModel;
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine getAnimationStateMachine() {
/* 359 */     return this.animationStateMachine;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getSounds(String name) {
/* 364 */     return this.sounds.get(name);
/*     */   }
/*     */   
/*     */   public GunTransform getTransform() {
/* 368 */     return this.transform;
/*     */   }
/*     */   
/*     */   public ResourceLocation getSlotTexture() {
/* 372 */     return this.slotTexture;
/*     */   }
/*     */   
/*     */   public ResourceLocation getHUDTexture() {
/* 376 */     return this.hudTexture;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getHudEmptyTexture() {
/* 381 */     return this.hudEmptyTexture;
/*     */   }
/*     */   
/*     */   public ResourceLocation getModelTexture() {
/* 385 */     return this.modelTexture;
/*     */   }
/*     */   
/*     */   public GunData getGunData() {
/* 389 */     return this.gunData;
/*     */   }
/*     */   
/*     */   public String getThirdPersonAnimation() {
/* 393 */     return this.thirdPersonAnimation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ShellEjection getShellEjection() {
/* 398 */     return this.shellEjection;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public float[] getTracerColor() {
/* 403 */     return this.tracerColor;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AmmoParticle getParticle() {
/* 408 */     return this.particle;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MuzzleFlash getMuzzleFlash() {
/* 413 */     return this.muzzleFlash;
/*     */   }
/*     */   
/*     */   public LayerGunShow getOffhandShow() {
/* 417 */     return this.offhandShow;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Int2ObjectArrayMap<LayerGunShow> getHotbarShow() {
/* 422 */     return this.hotbarShow;
/*     */   }
/*     */   
/*     */   public float getIronZoom() {
/* 426 */     return this.ironZoom;
/*     */   }
/*     */   
/*     */   public boolean isShowCrosshair() {
/* 430 */     return this.showCrosshair;
/*     */   }
/*     */   @Nullable
/*     */   public ResourceLocation getPlayerAnimator3rd() {
/* 434 */     return this.playerAnimator3rd;
/*     */   }
/*     */   
/*     */   public EnumMap<FireMode, ControllableData> getControllableData() {
/* 438 */     return this.controllableData;
/*     */   }
/*     */   
/*     */   public AmmoCountStyle getAmmoCountStyle() {
/* 442 */     return this.ammoCountStyle;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\index\ClientGunIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */