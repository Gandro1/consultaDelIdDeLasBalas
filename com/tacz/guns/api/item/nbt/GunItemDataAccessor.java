/*     */ package com.tacz.guns.api.item.nbt;
/*     */ 
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public interface GunItemDataAccessor
/*     */   extends IGun {
/*     */   public static final String GUN_ID_TAG = "GunId";
/*     */   public static final String GUN_FIRE_MODE_TAG = "GunFireMode";
/*     */   public static final String GUN_HAS_BULLET_IN_BARREL = "HasBulletInBarrel";
/*     */   public static final String GUN_CURRENT_AMMO_COUNT_TAG = "GunCurrentAmmoCount";
/*     */   public static final String GUN_ATTACHMENT_BASE = "Attachment";
/*     */   public static final String GUN_EXP_TAG = "GunLevelExp";
/*     */   public static final String GUN_DUMMY_AMMO = "DummyAmmo";
/*     */   public static final String GUN_MAX_DUMMY_AMMO = "MaxDummyAmmo";
/*     */   public static final String GUN_ATTACHMENT_LOCK = "AttachmentLock";
/*     */   
/*     */   default boolean useDummyAmmo(ItemStack gun) {
/*  36 */     CompoundTag nbt = gun.m_41784_();
/*  37 */     return nbt.m_128425_("DummyAmmo", 3);
/*     */   }
/*     */ 
/*     */   
/*     */   default int getDummyAmmoAmount(ItemStack gun) {
/*  42 */     CompoundTag nbt = gun.m_41784_();
/*  43 */     return Math.max(0, nbt.m_128451_("DummyAmmo"));
/*     */   }
/*     */ 
/*     */   
/*     */   default void setDummyAmmoAmount(ItemStack gun, int amount) {
/*  48 */     CompoundTag nbt = gun.m_41784_();
/*  49 */     nbt.m_128405_("DummyAmmo", Math.max(amount, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   default void addDummyAmmoAmount(ItemStack gun, int amount) {
/*  54 */     if (!useDummyAmmo(gun)) {
/*     */       return;
/*     */     }
/*  57 */     int maxDummyAmmo = Integer.MAX_VALUE;
/*  58 */     if (hasMaxDummyAmmo(gun)) {
/*  59 */       maxDummyAmmo = getMaxDummyAmmoAmount(gun);
/*     */     }
/*  61 */     CompoundTag nbt = gun.m_41784_();
/*  62 */     amount = Math.min(getDummyAmmoAmount(gun) + amount, maxDummyAmmo);
/*  63 */     nbt.m_128405_("DummyAmmo", Math.max(amount, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean hasMaxDummyAmmo(ItemStack gun) {
/*  68 */     CompoundTag nbt = gun.m_41784_();
/*  69 */     return nbt.m_128425_("MaxDummyAmmo", 3);
/*     */   }
/*     */ 
/*     */   
/*     */   default int getMaxDummyAmmoAmount(ItemStack gun) {
/*  74 */     CompoundTag nbt = gun.m_41784_();
/*  75 */     return Math.max(0, nbt.m_128451_("MaxDummyAmmo"));
/*     */   }
/*     */ 
/*     */   
/*     */   default void setMaxDummyAmmoAmount(ItemStack gun, int amount) {
/*  80 */     CompoundTag nbt = gun.m_41784_();
/*  81 */     nbt.m_128405_("MaxDummyAmmo", Math.max(amount, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean hasAttachmentLock(ItemStack gun) {
/*  86 */     CompoundTag nbt = gun.m_41784_();
/*  87 */     if (nbt.m_128425_("AttachmentLock", 1)) {
/*  88 */       return nbt.m_128471_("AttachmentLock");
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setAttachmentLock(ItemStack gun, boolean lock) {
/*  95 */     CompoundTag nbt = gun.m_41784_();
/*  96 */     nbt.m_128379_("AttachmentLock", lock);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default ResourceLocation getGunId(ItemStack gun) {
/* 102 */     CompoundTag nbt = gun.m_41784_();
/* 103 */     if (nbt.m_128425_("GunId", 8)) {
/* 104 */       ResourceLocation gunId = ResourceLocation.m_135820_(nbt.m_128461_("GunId"));
/* 105 */       return Objects.<ResourceLocation>requireNonNullElse(gunId, DefaultAssets.EMPTY_GUN_ID);
/*     */     } 
/* 107 */     return DefaultAssets.EMPTY_GUN_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setGunId(ItemStack gun, @Nullable ResourceLocation gunId) {
/* 112 */     CompoundTag nbt = gun.m_41784_();
/* 113 */     if (gunId != null) {
/* 114 */       nbt.m_128359_("GunId", gunId.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   default int getLevel(ItemStack gun) {
/* 120 */     CompoundTag nbt = gun.m_41784_();
/* 121 */     if (nbt.m_128425_("GunLevelExp", 3)) {
/* 122 */       return getLevel(nbt.m_128451_("GunLevelExp"));
/*     */     }
/* 124 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   default int getExp(ItemStack gun) {
/* 129 */     CompoundTag nbt = gun.m_41784_();
/* 130 */     if (nbt.m_128425_("GunLevelExp", 3)) {
/* 131 */       return nbt.m_128451_("GunLevelExp");
/*     */     }
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   default int getExpToNextLevel(ItemStack gun) {
/* 138 */     int exp = getExp(gun);
/* 139 */     int level = getLevel(exp);
/* 140 */     if (level >= getMaxLevel()) {
/* 141 */       return 0;
/*     */     }
/* 143 */     int nextLevelExp = getExp(level + 1);
/* 144 */     return nextLevelExp - exp;
/*     */   }
/*     */ 
/*     */   
/*     */   default int getExpCurrentLevel(ItemStack gun) {
/* 149 */     int exp = getExp(gun);
/* 150 */     int level = getLevel(exp);
/* 151 */     if (level <= 0) {
/* 152 */       return exp;
/*     */     }
/* 154 */     return exp - getExp(level - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default FireMode getFireMode(ItemStack gun) {
/* 160 */     CompoundTag nbt = gun.m_41784_();
/* 161 */     if (nbt.m_128425_("GunFireMode", 8)) {
/* 162 */       return FireMode.valueOf(nbt.m_128461_("GunFireMode"));
/*     */     }
/* 164 */     return FireMode.UNKNOWN;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setFireMode(ItemStack gun, @Nullable FireMode fireMode) {
/* 169 */     CompoundTag nbt = gun.m_41784_();
/* 170 */     if (fireMode != null) {
/* 171 */       nbt.m_128359_("GunFireMode", fireMode.name());
/*     */       return;
/*     */     } 
/* 174 */     nbt.m_128359_("GunFireMode", FireMode.UNKNOWN.name());
/*     */   }
/*     */ 
/*     */   
/*     */   default int getCurrentAmmoCount(ItemStack gun) {
/* 179 */     CompoundTag nbt = gun.m_41784_();
/* 180 */     if (nbt.m_128425_("GunCurrentAmmoCount", 3)) {
/* 181 */       return nbt.m_128451_("GunCurrentAmmoCount");
/*     */     }
/* 183 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setCurrentAmmoCount(ItemStack gun, int ammoCount) {
/* 188 */     CompoundTag nbt = gun.m_41784_();
/* 189 */     nbt.m_128405_("GunCurrentAmmoCount", Math.max(ammoCount, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   default void reduceCurrentAmmoCount(ItemStack gun) {
/* 194 */     setCurrentAmmoCount(gun, getCurrentAmmoCount(gun) - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default CompoundTag getAttachmentTag(ItemStack gun, AttachmentType type) {
/* 200 */     if (!allowAttachmentType(gun, type)) {
/* 201 */       return null;
/*     */     }
/* 203 */     CompoundTag nbt = gun.m_41784_();
/* 204 */     String key = "Attachment" + type.name();
/* 205 */     if (nbt.m_128425_(key, 10)) {
/* 206 */       CompoundTag allItemStackTag = nbt.m_128469_(key);
/* 207 */       if (allItemStackTag.m_128425_("tag", 10)) {
/* 208 */         return allItemStackTag.m_128469_("tag");
/*     */       }
/*     */     } 
/* 211 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default ItemStack getBuiltinAttachment(ItemStack gun, AttachmentType type) {
/* 217 */     IGun iGun = IGun.getIGunOrNull(gun);
/* 218 */     if (iGun == null) {
/* 219 */       return ItemStack.f_41583_;
/*     */     }
/* 221 */     CommonGunIndex index = TimelessAPI.getCommonGunIndex(iGun.getGunId(gun)).orElse(null);
/* 222 */     if (index != null) {
/* 223 */       Map<AttachmentType, ResourceLocation> builtin = index.getGunData().getBuiltInAttachments();
/* 224 */       if (builtin.containsKey(type)) {
/* 225 */         return AttachmentItemBuilder.create().setId(builtin.get(type)).build();
/*     */       }
/*     */     } 
/* 228 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default ItemStack getAttachment(ItemStack gun, AttachmentType type) {
/* 234 */     if (!allowAttachmentType(gun, type)) {
/* 235 */       return ItemStack.f_41583_;
/*     */     }
/* 237 */     CompoundTag nbt = gun.m_41784_();
/* 238 */     String key = "Attachment" + type.name();
/* 239 */     if (nbt.m_128425_(key, 10)) {
/* 240 */       return ItemStack.m_41712_(nbt.m_128469_(key));
/*     */     }
/* 242 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default ResourceLocation getBuiltInAttachmentId(ItemStack gun, AttachmentType type) {
/* 248 */     IGun iGun = IGun.getIGunOrNull(gun);
/* 249 */     if (iGun == null) {
/* 250 */       return DefaultAssets.EMPTY_ATTACHMENT_ID;
/*     */     }
/* 252 */     CommonGunIndex index = TimelessAPI.getCommonGunIndex(iGun.getGunId(gun)).orElse(null);
/* 253 */     if (index != null) {
/* 254 */       Map<AttachmentType, ResourceLocation> builtin = index.getGunData().getBuiltInAttachments();
/* 255 */       if (builtin.containsKey(type)) {
/* 256 */         return builtin.get(type);
/*     */       }
/*     */     } 
/* 259 */     return DefaultAssets.EMPTY_ATTACHMENT_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   default ResourceLocation getAttachmentId(ItemStack gun, AttachmentType type) {
/* 265 */     CompoundTag attachmentTag = getAttachmentTag(gun, type);
/* 266 */     if (attachmentTag != null) {
/* 267 */       return AttachmentItemDataAccessor.getAttachmentIdFromTag(attachmentTag);
/*     */     }
/* 269 */     return DefaultAssets.EMPTY_ATTACHMENT_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   default void installAttachment(@Nonnull ItemStack gun, @Nonnull ItemStack attachment) {
/* 274 */     if (!allowAttachment(gun, attachment)) {
/*     */       return;
/*     */     }
/* 277 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachment);
/* 278 */     if (iAttachment == null) {
/*     */       return;
/*     */     }
/* 281 */     CompoundTag nbt = gun.m_41784_();
/* 282 */     String key = "Attachment" + iAttachment.getType(attachment).name();
/* 283 */     CompoundTag attachmentTag = new CompoundTag();
/* 284 */     attachment.m_41739_(attachmentTag);
/* 285 */     nbt.m_128365_(key, (Tag)attachmentTag);
/*     */   }
/*     */ 
/*     */   
/*     */   default void unloadAttachment(@Nonnull ItemStack gun, AttachmentType type) {
/* 290 */     if (!allowAttachmentType(gun, type)) {
/*     */       return;
/*     */     }
/* 293 */     CompoundTag nbt = gun.m_41784_();
/* 294 */     String key = "Attachment" + type.name();
/* 295 */     CompoundTag attachmentTag = new CompoundTag();
/* 296 */     ItemStack.f_41583_.m_41739_(attachmentTag);
/* 297 */     nbt.m_128365_(key, (Tag)attachmentTag);
/*     */   }
/*     */ 
/*     */   
/*     */   default float getAimingZoom(ItemStack gunItem) {
/* 302 */     float zoom = 1.0F;
/* 303 */     ResourceLocation scopeId = getAttachmentId(gunItem, AttachmentType.SCOPE);
/* 304 */     boolean builtin = false;
/* 305 */     if (scopeId.equals(DefaultAssets.EMPTY_ATTACHMENT_ID)) {
/* 306 */       scopeId = getBuiltInAttachmentId(gunItem, AttachmentType.SCOPE);
/* 307 */       builtin = true;
/*     */     } 
/* 309 */     if (!DefaultAssets.isEmptyAttachmentId(scopeId)) {
/* 310 */       CompoundTag attachmentTag = getAttachmentTag(gunItem, AttachmentType.SCOPE);
/* 311 */       int zoomNumber = builtin ? 0 : AttachmentItemDataAccessor.getZoomNumberFromTag(attachmentTag);
/* 312 */       float[] zooms = TimelessAPI.getClientAttachmentIndex(scopeId).map(ClientAttachmentIndex::getZoom).orElse(null);
/* 313 */       if (zooms != null) {
/* 314 */         zoom = zooms[zoomNumber % zooms.length];
/*     */       }
/*     */     } else {
/* 317 */       ResourceLocation gunId = getGunId(gunItem);
/* 318 */       zoom = ((Float)TimelessAPI.getClientGunIndex(gunId).map(ClientGunIndex::getIronZoom).orElse(Float.valueOf(1.0F))).floatValue();
/*     */     } 
/* 320 */     return zoom;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean hasBulletInBarrel(ItemStack gun) {
/* 325 */     CompoundTag nbt = gun.m_41784_();
/* 326 */     if (nbt.m_128425_("HasBulletInBarrel", 1)) {
/* 327 */       return nbt.m_128471_("HasBulletInBarrel");
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setBulletInBarrel(ItemStack gun, boolean bulletInBarrel) {
/* 334 */     CompoundTag nbt = gun.m_41784_();
/* 335 */     nbt.m_128379_("HasBulletInBarrel", bulletInBarrel);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\nbt\GunItemDataAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */