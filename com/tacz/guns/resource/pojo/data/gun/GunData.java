/*     */ package com.tacz.guns.resource.pojo.data.gun;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GunData
/*     */ {
/*     */   @SerializedName("ammo")
/*  23 */   private ResourceLocation ammoId = null;
/*     */   
/*     */   @SerializedName("ammo_amount")
/*  26 */   private int ammoAmount = 30;
/*     */   
/*     */   @SerializedName("extended_mag_ammo_amount")
/*  29 */   private int[] extendedMagAmmoAmount = null;
/*     */   
/*     */   @SerializedName("bolt")
/*  32 */   private Bolt bolt = Bolt.OPEN_BOLT;
/*     */   
/*     */   @SerializedName("rpm")
/*  35 */   private int roundsPerMinute = 300;
/*     */   
/*     */   @SerializedName("bullet")
/*  38 */   private BulletData bulletData = new BulletData();
/*     */   
/*     */   @SerializedName("draw_time")
/*  41 */   private float drawTime = 0.4F;
/*     */   
/*     */   @SerializedName("put_away_time")
/*  44 */   private float putAwayTime = 0.4F;
/*     */   
/*     */   @SerializedName("sprint_time")
/*  47 */   private float sprintTime = 0.2F;
/*     */   
/*     */   @SerializedName("aim_time")
/*  50 */   private float aimTime = 0.2F;
/*     */   
/*     */   @SerializedName("bolt_action_time")
/*  53 */   private float boltActionTime = 0.0F;
/*     */   
/*     */   @SerializedName("reload")
/*  56 */   private GunReloadData reloadData = new GunReloadData();
/*     */ 
/*     */   
/*     */   @SerializedName("fire_mode")
/*  60 */   private List<FireMode> fireModeSet = Collections.singletonList(FireMode.UNKNOWN);
/*     */   
/*     */   @SerializedName("fire_mode_adjust")
/*  63 */   private EnumMap<FireMode, GunFireModeAdjustData> fireModeAdjust = Maps.newEnumMap(FireMode.class);
/*     */   @SerializedName("burst_data")
/*  65 */   private BurstData burstData = new BurstData();
/*     */   
/*     */   @SerializedName("crawl_recoil_multiplier")
/*  68 */   private float crawlRecoilMultiplier = 0.5F;
/*     */   
/*     */   @SerializedName("recoil")
/*  71 */   private GunRecoil recoil = new GunRecoil();
/*     */   
/*     */   @SerializedName("hurt_bob_tweak_multiplier")
/*  74 */   private float hurtBobTweakMultiplier = 0.05F;
/*     */   
/*     */   @SerializedName("inaccuracy")
/*  77 */   private Map<InaccuracyType, Float> inaccuracy = null;
/*     */   
/*     */   @SerializedName("movement_speed")
/*  80 */   private MoveSpeed moveSpeed = new MoveSpeed();
/*     */   
/*     */   @SerializedName("melee")
/*  83 */   private GunMeleeData gunMeleeData = new GunMeleeData();
/*     */ 
/*     */   
/*     */   @SerializedName("allow_attachment_types")
/*  87 */   private List<AttachmentType> allowAttachments = Lists.newArrayList();
/*     */   
/*     */   @SerializedName("exclusive_attachments")
/*  90 */   private Map<ResourceLocation, AttachmentData> exclusiveAttachments = Maps.newHashMap();
/*     */   @SerializedName("weight")
/*  92 */   private float weight = 0.0F;
/*     */ 
/*     */   
/*     */   @SerializedName("builtin_attachments")
/*  96 */   private Map<AttachmentType, ResourceLocation> builtInAttachments = Maps.newHashMap();
/*     */   
/*     */   public ResourceLocation getAmmoId() {
/*  99 */     return this.ammoId;
/*     */   }
/*     */   
/*     */   public int getAmmoAmount() {
/* 103 */     return this.ammoAmount;
/*     */   }
/*     */   
/*     */   public int[] getExtendedMagAmmoAmount() {
/* 107 */     return this.extendedMagAmmoAmount;
/*     */   }
/*     */   
/*     */   public Bolt getBolt() {
/* 111 */     return this.bolt;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getRoundsPerMinute() {
/* 116 */     return this.roundsPerMinute;
/*     */   }
/*     */   
/*     */   public int getRoundsPerMinute(FireMode fireMode) {
/* 120 */     int rpm = this.roundsPerMinute;
/* 121 */     GunFireModeAdjustData fireModeAdjustData = getFireModeAdjustData(fireMode);
/* 122 */     if (fireModeAdjustData != null) {
/* 123 */       rpm += fireModeAdjustData.getRoundsPerMinute();
/*     */     }
/*     */     
/* 126 */     if (rpm <= 0) {
/* 127 */       return 300;
/*     */     }
/* 129 */     return rpm;
/*     */   }
/*     */   
/*     */   public BulletData getBulletData() {
/* 133 */     return this.bulletData;
/*     */   }
/*     */   
/*     */   public float getDrawTime() {
/* 137 */     return this.drawTime;
/*     */   }
/*     */   
/*     */   public float getPutAwayTime() {
/* 141 */     return this.putAwayTime;
/*     */   }
/*     */   
/*     */   public float getAimTime() {
/* 145 */     return this.aimTime;
/*     */   }
/*     */   
/*     */   public float getSprintTime() {
/* 149 */     return this.sprintTime;
/*     */   }
/*     */   
/*     */   public float getBoltActionTime() {
/* 153 */     return this.boltActionTime;
/*     */   }
/*     */   
/*     */   public GunReloadData getReloadData() {
/* 157 */     return this.reloadData;
/*     */   }
/*     */   
/*     */   public List<FireMode> getFireModeSet() {
/* 161 */     return this.fireModeSet;
/*     */   }
/*     */   
/*     */   public BurstData getBurstData() {
/* 165 */     return this.burstData;
/*     */   }
/*     */   
/*     */   public float getWeight() {
/* 169 */     return this.weight;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GunFireModeAdjustData getFireModeAdjustData(FireMode fireMode) {
/* 174 */     if (this.fireModeAdjust != null && this.fireModeAdjust.containsKey(fireMode)) {
/* 175 */       return this.fireModeAdjust.get(fireMode);
/*     */     }
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   public float getCrawlRecoilMultiplier() {
/* 181 */     return this.crawlRecoilMultiplier;
/*     */   }
/*     */   
/*     */   public GunRecoil getRecoil() {
/* 185 */     return this.recoil;
/*     */   }
/*     */   
/*     */   public float getHurtBobTweakMultiplier() {
/* 189 */     return this.hurtBobTweakMultiplier;
/*     */   }
/*     */   
/*     */   public Map<InaccuracyType, Float> getInaccuracy() {
/* 193 */     return this.inaccuracy;
/*     */   }
/*     */   
/*     */   public void setInaccuracy(Map<InaccuracyType, Float> inaccuracy) {
/* 197 */     this.inaccuracy = inaccuracy;
/*     */   }
/*     */   
/*     */   public float getInaccuracy(InaccuracyType type) {
/* 201 */     return Math.max(((Float)this.inaccuracy.get(type)).floatValue(), 0.0F);
/*     */   }
/*     */   
/*     */   public float getInaccuracy(InaccuracyType type, float addend) {
/* 205 */     return Math.max(((Float)this.inaccuracy.get(type)).floatValue() + addend, 0.0F);
/*     */   }
/*     */   
/*     */   public MoveSpeed getMoveSpeed() {
/* 209 */     return this.moveSpeed;
/*     */   }
/*     */   
/*     */   public GunMeleeData getMeleeData() {
/* 213 */     return this.gunMeleeData;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<AttachmentType> getAllowAttachments() {
/* 218 */     return this.allowAttachments;
/*     */   }
/*     */   
/*     */   public Map<AttachmentType, ResourceLocation> getBuiltInAttachments() {
/* 222 */     return this.builtInAttachments;
/*     */   }
/*     */   
/*     */   public Map<ResourceLocation, AttachmentData> getExclusiveAttachments() {
/* 226 */     return this.exclusiveAttachments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getShootInterval(LivingEntity shooter, FireMode fireMode) {
/* 233 */     int rpm = getRoundsPerMinute(fireMode);
/* 234 */     AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity(shooter).getCacheProperty();
/* 235 */     if (cacheProperty != null) {
/* 236 */       rpm = Mth.m_14045_(((Integer)cacheProperty.getCache("rpm")).intValue(), 1, 1200);
/*     */     }
/* 238 */     return 60000L / rpm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getBurstShootInterval() {
/* 246 */     if (this.burstData == null || this.burstData.getBpm() <= 0) {
/* 247 */       return 300L;
/*     */     }
/* 249 */     return 60000L / this.burstData.getBpm();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */