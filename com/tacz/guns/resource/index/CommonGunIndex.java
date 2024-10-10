/*     */ package com.tacz.guns.resource.index;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.resource.CommonAssetManager;
/*     */ import com.tacz.guns.resource.pojo.GunIndexPOJO;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunRecoil;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunRecoilKeyFrame;
/*     */ import com.tacz.guns.resource.pojo.data.gun.InaccuracyType;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class CommonGunIndex
/*     */ {
/*     */   private GunData gunData;
/*     */   private String type;
/*     */   
/*     */   public static CommonGunIndex getInstance(GunIndexPOJO gunIndexPOJO) throws IllegalArgumentException {
/*  25 */     CommonGunIndex index = new CommonGunIndex();
/*  26 */     index.pojo = gunIndexPOJO;
/*  27 */     checkIndex(gunIndexPOJO, index);
/*  28 */     checkData(gunIndexPOJO, index);
/*  29 */     return index;
/*     */   }
/*     */   private GunIndexPOJO pojo; private int sort;
/*     */   private static void checkIndex(GunIndexPOJO gunIndexPOJO, CommonGunIndex index) {
/*  33 */     Preconditions.checkArgument((gunIndexPOJO != null), "index object file is empty");
/*  34 */     Preconditions.checkArgument(StringUtils.isNoneBlank(new CharSequence[] { gunIndexPOJO.getType() }, ), "index object missing type field");
/*  35 */     index.type = gunIndexPOJO.getType();
/*  36 */     index.sort = Mth.m_14045_(gunIndexPOJO.getSort(), 0, 65536);
/*     */   }
/*     */   
/*     */   private static void checkData(GunIndexPOJO gunIndexPOJO, CommonGunIndex index) {
/*  40 */     ResourceLocation pojoData = gunIndexPOJO.getData();
/*  41 */     Preconditions.checkArgument((pojoData != null), "index object missing pojoData field");
/*  42 */     GunData data = CommonAssetManager.INSTANCE.getGunData(pojoData);
/*  43 */     Preconditions.checkArgument((data != null), "there is no corresponding data file");
/*  44 */     Preconditions.checkArgument((data.getAmmoId() != null), "ammo id is empty");
/*  45 */     Preconditions.checkArgument((data.getAmmoAmount() >= 1), "ammo count must >= 1");
/*  46 */     int[] extendedMagAmmoAmount = data.getExtendedMagAmmoAmount();
/*  47 */     Preconditions.checkArgument((extendedMagAmmoAmount == null || extendedMagAmmoAmount.length >= 3), "extended_mag_ammo_amount size must is 3");
/*  48 */     Preconditions.checkArgument((data.getRoundsPerMinute() >= 1), "rpm count must >= 1");
/*  49 */     Preconditions.checkArgument((data.getBolt() != null), "bolt type is error");
/*  50 */     Preconditions.checkArgument((data.getReloadData().getType() != null), "reload type is error");
/*  51 */     Preconditions.checkArgument(!data.getFireModeSet().isEmpty(), "fire mode is empty");
/*  52 */     Preconditions.checkArgument((!data.getFireModeSet().contains(null) && !data.getFireModeSet().contains(FireMode.UNKNOWN)), "fire mode is error");
/*  53 */     checkInaccuracy(data);
/*  54 */     checkRecoil(data);
/*  55 */     index.gunData = data;
/*     */   }
/*     */   
/*     */   private static void checkInaccuracy(GunData data) {
/*  59 */     Map<InaccuracyType, Float> defaultInaccuracy = InaccuracyType.getDefaultInaccuracy();
/*  60 */     Map<InaccuracyType, Float> readInaccuracy = data.getInaccuracy();
/*  61 */     if (readInaccuracy == null || readInaccuracy.isEmpty()) {
/*  62 */       data.setInaccuracy(defaultInaccuracy);
/*     */     } else {
/*  64 */       Objects.requireNonNull(readInaccuracy); defaultInaccuracy.forEach(readInaccuracy::putIfAbsent);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkRecoil(GunData data) {
/*  69 */     GunRecoil recoil = data.getRecoil();
/*  70 */     GunRecoilKeyFrame[] pitch = recoil.getPitch();
/*  71 */     GunRecoilKeyFrame[] yaw = recoil.getYaw();
/*  72 */     if (pitch != null) {
/*  73 */       for (GunRecoilKeyFrame keyFrame : pitch) {
/*  74 */         float[] value = keyFrame.getValue();
/*  75 */         Preconditions.checkArgument((value.length == 2), "Recoil value's length must be 2");
/*  76 */         Preconditions.checkArgument((value[0] <= value[1]), "Recoil value's left must be less than right");
/*  77 */         Preconditions.checkArgument((keyFrame.getTime() >= 0.0F), "Recoil time must be more than 0");
/*     */       } 
/*  79 */       Arrays.sort((Object[])pitch);
/*     */     } 
/*     */     
/*  82 */     if (yaw != null) {
/*  83 */       for (GunRecoilKeyFrame keyFrame : yaw) {
/*  84 */         float[] value = keyFrame.getValue();
/*  85 */         Preconditions.checkArgument((value.length == 2), "Recoil value's length must be 2");
/*  86 */         Preconditions.checkArgument((value[0] <= value[1]), "Recoil value's left must be less than right");
/*  87 */         Preconditions.checkArgument((keyFrame.getTime() >= 0.0F), "Recoil time must be more than 0");
/*     */       } 
/*  89 */       Arrays.sort((Object[])yaw);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GunData getGunData() {
/*  94 */     return this.gunData;
/*     */   }
/*     */   
/*     */   public BulletData getBulletData() {
/*  98 */     return this.gunData.getBulletData();
/*     */   }
/*     */   
/*     */   public String getType() {
/* 102 */     return this.type;
/*     */   }
/*     */   
/*     */   public GunIndexPOJO getPojo() {
/* 106 */     return this.pojo;
/*     */   }
/*     */   
/*     */   public int getSort() {
/* 110 */     return this.sort;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\index\CommonGunIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */