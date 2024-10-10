/*     */ package com.tacz.guns.client.resource.pojo.display.gun;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class GunDisplay {
/*     */   @SerializedName("model")
/*     */   private ResourceLocation modelLocation;
/*     */   @SerializedName("texture")
/*     */   private ResourceLocation modelTexture;
/*     */   @SerializedName("iron_zoom")
/*  18 */   private float ironZoom = 1.2F;
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   @SerializedName("lod")
/*     */   private GunLod gunLod;
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   @SerializedName("hud")
/*     */   private ResourceLocation hudTextureLocation;
/*     */   
/*     */   @SerializedName("ammo_count_style")
/*     */   @NotNull
/*  32 */   private AmmoCountStyle ammoCountStyle = AmmoCountStyle.NORMAL; @Nullable
/*     */   @SerializedName("hud_empty")
/*     */   private ResourceLocation hudEmptyTextureLocation; @Nullable
/*     */   @SerializedName("slot")
/*     */   private ResourceLocation slotTextureLocation; @Nullable
/*     */   @SerializedName("third_person_animation")
/*     */   private String thirdPersonAnimation; @Nullable
/*     */   @SerializedName("animation")
/*     */   private ResourceLocation animationLocation; @Nullable
/*     */   @SerializedName("use_default_animation")
/*     */   private DefaultAnimation defaultAnimation;
/*     */   @Nullable
/*     */   @SerializedName("player_animator_3rd")
/*     */   private ResourceLocation playerAnimator3rd;
/*     */   @Nullable
/*     */   @SerializedName("sounds")
/*     */   private Map<String, ResourceLocation> sounds;
/*     */   @Nullable
/*     */   @SerializedName("transform")
/*     */   private GunTransform transform;
/*     */   @Nullable
/*     */   @SerializedName("shell")
/*     */   private ShellEjection shellEjection;
/*     */   @Nullable
/*     */   @SerializedName("ammo")
/*     */   private GunAmmo gunAmmo;
/*     */   @Nullable
/*     */   @SerializedName("muzzle_flash")
/*     */   private MuzzleFlash muzzleFlash;
/*     */   @SerializedName("offhand_show")
/*  62 */   private LayerGunShow offhandShow = new LayerGunShow(); @Nullable
/*     */   @SerializedName("hotbar_show")
/*  64 */   private Map<String, LayerGunShow> hotbarShow = null;
/*     */ 
/*     */   
/*     */   @SerializedName("text_show")
/*  68 */   private Map<String, TextShow> textShows = Maps.newHashMap();
/*     */   @SerializedName("show_crosshair")
/*     */   private boolean showCrosshair = false;
/*     */   @SerializedName("controllable")
/*  72 */   private EnumMap<FireMode, ControllableData> controllableData = Maps.newEnumMap(FireMode.class);
/*     */   
/*     */   public ResourceLocation getModelLocation() {
/*  75 */     return this.modelLocation;
/*     */   }
/*     */   
/*     */   public ResourceLocation getModelTexture() {
/*  79 */     return this.modelTexture;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GunLod getGunLod() {
/*  84 */     return this.gunLod;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getHudTextureLocation() {
/*  89 */     return this.hudTextureLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getHudEmptyTextureLocation() {
/*  94 */     return this.hudEmptyTextureLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getSlotTextureLocation() {
/*  99 */     return this.slotTextureLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getAnimationLocation() {
/* 104 */     return this.animationLocation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DefaultAnimation getDefaultAnimation() {
/* 109 */     return this.defaultAnimation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getPlayerAnimator3rd() {
/* 114 */     return this.playerAnimator3rd;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getThirdPersonAnimation() {
/* 119 */     return this.thirdPersonAnimation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Map<String, ResourceLocation> getSounds() {
/* 124 */     return this.sounds;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GunTransform getTransform() {
/* 129 */     return this.transform;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ShellEjection getShellEjection() {
/* 134 */     return this.shellEjection;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GunAmmo getGunAmmo() {
/* 139 */     return this.gunAmmo;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MuzzleFlash getMuzzleFlash() {
/* 144 */     return this.muzzleFlash;
/*     */   }
/*     */   
/*     */   public LayerGunShow getOffhandShow() {
/* 148 */     return this.offhandShow;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Map<String, LayerGunShow> getHotbarShow() {
/* 153 */     return this.hotbarShow;
/*     */   }
/*     */   
/*     */   public float getIronZoom() {
/* 157 */     return this.ironZoom;
/*     */   }
/*     */   
/*     */   public Map<String, TextShow> getTextShows() {
/* 161 */     return this.textShows;
/*     */   }
/*     */   
/*     */   public boolean isShowCrosshair() {
/* 165 */     return this.showCrosshair;
/*     */   }
/*     */   
/*     */   public EnumMap<FireMode, ControllableData> getControllableData() {
/* 169 */     return this.controllableData;
/*     */   }
/*     */   @NotNull
/*     */   public AmmoCountStyle getAmmoCountStyle() {
/* 173 */     return this.ammoCountStyle;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\GunDisplay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */