/*     */ package com.tacz.guns.util;
/*     */ 
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.config.sync.SyncConfig;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.modifier.custom.ExplosionModifier;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunFireModeAdjustData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AttachmentDataUtils
/*     */ {
/*     */   public static void getAllAttachmentData(ItemStack gunItem, GunData gunData, Consumer<AttachmentData> dataConsumer) {
/*  33 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  34 */     if (iGun == null) {
/*     */       return;
/*     */     }
/*  37 */     for (AttachmentType type : AttachmentType.values()) {
/*  38 */       if (type != AttachmentType.NONE) {
/*     */ 
/*     */         
/*  41 */         ResourceLocation attachmentId = iGun.getAttachmentId(gunItem, type);
/*  42 */         if (!DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/*     */ 
/*     */           
/*  45 */           AttachmentData attachmentData = (AttachmentData)gunData.getExclusiveAttachments().get(attachmentId);
/*  46 */           if (attachmentData != null) {
/*  47 */             dataConsumer.accept(attachmentData);
/*     */           } else {
/*  49 */             TimelessAPI.getCommonAttachmentIndex(attachmentId).ifPresent(index -> dataConsumer.accept(index.getData()));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public static int getAmmoCountWithAttachment(ItemStack gunItem, GunData gunData) {
/*  55 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  56 */     if (iGun == null) {
/*  57 */       return gunData.getAmmoAmount();
/*     */     }
/*  59 */     int[] extendedMagAmmoAmount = gunData.getExtendedMagAmmoAmount();
/*  60 */     if (extendedMagAmmoAmount == null) {
/*  61 */       return gunData.getAmmoAmount();
/*     */     }
/*  63 */     ResourceLocation attachmentId = iGun.getAttachmentId(gunItem, AttachmentType.EXTENDED_MAG);
/*  64 */     if (DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/*  65 */       return gunData.getAmmoAmount();
/*     */     }
/*  67 */     AttachmentData attachmentData = (AttachmentData)gunData.getExclusiveAttachments().get(attachmentId);
/*  68 */     if (attachmentData != null) {
/*  69 */       int level = attachmentData.getExtendedMagLevel();
/*  70 */       if (level <= 0 || level > 3) {
/*  71 */         return gunData.getAmmoAmount();
/*     */       }
/*  73 */       return extendedMagAmmoAmount[level];
/*     */     } 
/*  75 */     return ((Integer)TimelessAPI.getCommonAttachmentIndex(attachmentId).map(index -> {
/*     */           int level = index.getData().getExtendedMagLevel();
/*  77 */           return (level <= 0 || level > 3) ? Integer.valueOf(gunData.getAmmoAmount()) : Integer.valueOf(extendedMagAmmoAmount[level - 1]);
/*     */ 
/*     */ 
/*     */         
/*  81 */         }).orElse(Integer.valueOf(gunData.getAmmoAmount()))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getWightWithAttachment(ItemStack gunItem, GunData gunData) {
/*  86 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/*  87 */     if (iGun == null) {
/*  88 */       return gunData.getWeight();
/*     */     }
/*     */     
/*  91 */     List<Modifier> modifiers = new ArrayList<>();
/*  92 */     for (AttachmentType type : AttachmentType.values()) {
/*  93 */       ResourceLocation id = iGun.getAttachmentId(gunItem, type);
/*  94 */       AttachmentData attachmentData = (AttachmentData)gunData.getExclusiveAttachments().get(id);
/*  95 */       if (attachmentData != null) {
/*  96 */         JsonProperty<?> m = (JsonProperty)attachmentData.getModifier().get("weight_modifier");
/*  97 */         if (m != null) { Object object = m.getValue(); if (object instanceof Modifier) { Modifier modifier1 = (Modifier)object;
/*  98 */             modifiers.add(modifier1); continue; }
/*     */            }
/* 100 */          Modifier modifier = new Modifier();
/* 101 */         modifier.setAddend(attachmentData.getWeight());
/* 102 */         modifiers.add(modifier);
/*     */         continue;
/*     */       } 
/* 105 */       TimelessAPI.getCommonAttachmentIndex(id).ifPresent(index -> {
/*     */             JsonProperty<?> m = (JsonProperty)index.getData().getModifier().get("weight_modifier"); if (m != null) {
/*     */               Object patt4722$temp = m.getValue(); if (patt4722$temp instanceof Modifier) {
/*     */                 Modifier modifier1 = (Modifier)patt4722$temp; modifiers.add(modifier1); return;
/*     */               } 
/*     */             } 
/*     */             Modifier modifier = new Modifier();
/*     */             modifier.setAddend(index.getData().getWeight());
/*     */             modifiers.add(modifier);
/*     */           });
/*     */       continue;
/*     */     } 
/* 117 */     return AttachmentPropertyManager.eval(modifiers, gunData.getWeight());
/*     */   }
/*     */   
/*     */   public static boolean isExplodeEnabled(ItemStack gunItem, GunData gunData) {
/* 121 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 122 */     if (iGun == null) {
/* 123 */       if (gunData.getBulletData().getExplosionData() != null) {
/* 124 */         return gunData.getBulletData().getExplosionData().isExplode();
/*     */       }
/* 126 */       return false;
/*     */     } 
/*     */     
/* 129 */     return calcBooleanValue(gunItem, gunData, "explosion", ExplosionModifier.ExplosionModifierValue.class, ExplosionModifier.ExplosionModifierValue::isExplode);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getArmorIgnoreWithAttachment(ItemStack gunItem, GunData gunData) {
/* 134 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 135 */     if (iGun == null) {
/* 136 */       return 0.0D;
/*     */     }
/* 138 */     FireMode fireMode = iGun.getFireMode(gunItem);
/* 139 */     BulletData bulletData = gunData.getBulletData();
/* 140 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */     
/* 142 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */ 
/*     */     
/* 145 */     float finalBase = (extraDamage != null) ? extraDamage.getArmorIgnore() : 0.0F;
/* 146 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getArmorIgnore()) : finalBase;
/* 147 */     finalBase = (float)(finalBase * ((Double)SyncConfig.ARMOR_IGNORE_BASE_MULTIPLIER.get()).doubleValue());
/*     */     
/* 149 */     List<Modifier> modifiers = getModifiers(gunItem, gunData, "armor_ignore");
/* 150 */     return AttachmentPropertyManager.eval(modifiers, finalBase);
/*     */   }
/*     */   
/*     */   public static double getHeadshotMultiplier(ItemStack gunItem, GunData gunData) {
/* 154 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 155 */     if (iGun == null) {
/* 156 */       return 0.0D;
/*     */     }
/* 158 */     FireMode fireMode = iGun.getFireMode(gunItem);
/* 159 */     BulletData bulletData = gunData.getBulletData();
/* 160 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */     
/* 162 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */ 
/*     */     
/* 165 */     float finalBase = (extraDamage != null) ? extraDamage.getHeadShotMultiplier() : 0.0F;
/* 166 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getHeadShotMultiplier()) : finalBase;
/* 167 */     finalBase = (float)(finalBase * ((Double)SyncConfig.HEAD_SHOT_BASE_MULTIPLIER.get()).doubleValue());
/*     */     
/* 169 */     List<Modifier> modifiers = getModifiers(gunItem, gunData, "head_shot");
/* 170 */     return AttachmentPropertyManager.eval(modifiers, finalBase);
/*     */   }
/*     */   
/*     */   public static double getDamageWithAttachment(ItemStack gunItem, GunData gunData) {
/* 174 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 175 */     if (iGun == null) {
/* 176 */       return 0.0D;
/*     */     }
/* 178 */     FireMode fireMode = iGun.getFireMode(gunItem);
/* 179 */     BulletData bulletData = gunData.getBulletData();
/* 180 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */     
/* 182 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/* 183 */     float rawDamage = bulletData.getDamageAmount();
/*     */ 
/*     */     
/* 186 */     float finalBase = (fireModeAdjustData != null) ? fireModeAdjustData.getDamageAmount() : 0.0F;
/* 187 */     if (extraDamage != null && extraDamage.getDamageAdjust() != null) {
/* 188 */       finalBase += ((ExtraDamage.DistanceDamagePair)extraDamage.getDamageAdjust().get(0)).getDamage();
/*     */     } else {
/* 190 */       finalBase += rawDamage;
/*     */     } 
/* 192 */     finalBase = (float)(finalBase * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue());
/*     */     
/* 194 */     List<Modifier> modifiers = getModifiers(gunItem, gunData, "damage");
/* 195 */     return AttachmentPropertyManager.eval(modifiers, finalBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<Modifier> getModifiers(ItemStack gunItem, GunData gunData, String id) {
/* 206 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 207 */     if (iGun == null) {
/* 208 */       return new ArrayList<>();
/*     */     }
/* 210 */     List<Modifier> modifiers = new ArrayList<>();
/* 211 */     for (AttachmentType type : AttachmentType.values()) {
/* 212 */       ResourceLocation attachmentId = iGun.getAttachmentId(gunItem, type);
/* 213 */       if (!DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/*     */ 
/*     */         
/* 216 */         AttachmentData attachmentData = (AttachmentData)gunData.getExclusiveAttachments().get(attachmentId);
/* 217 */         if (attachmentData != null)
/* 218 */         { JsonProperty<?> m = (JsonProperty)attachmentData.getModifier().get(id);
/* 219 */           if (m != null) { Object object = m.getValue(); if (object instanceof Modifier) { Modifier modifier = (Modifier)object;
/* 220 */               modifiers.add(modifier); }
/*     */              }
/*     */            }
/* 223 */         else { CommonAttachmentIndex index = TimelessAPI.getCommonAttachmentIndex(attachmentId).orElse(null);
/* 224 */           if (index != null) {
/* 225 */             JsonProperty<?> m = (JsonProperty)index.getData().getModifier().get(id);
/* 226 */             if (m != null) { Object object = m.getValue(); if (object instanceof Modifier) { Modifier modifier = (Modifier)object;
/* 227 */                 modifiers.add(modifier); }  }
/*     */           
/*     */           }  }
/*     */       
/*     */       } 
/* 232 */     }  return modifiers;
/*     */   }
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
/*     */   private static <T> boolean calcBooleanValue(ItemStack gunItem, GunData gunData, String id, Class<T> clazz, BooleanResolver<T> resolver) {
/* 246 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 247 */     if (iGun == null) {
/* 248 */       return false;
/*     */     }
/* 250 */     for (AttachmentType type : AttachmentType.values()) {
/* 251 */       ResourceLocation attachmentId = iGun.getAttachmentId(gunItem, type);
/* 252 */       if (!DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/*     */ 
/*     */         
/* 255 */         AttachmentData attachmentData = (AttachmentData)gunData.getExclusiveAttachments().get(attachmentId);
/* 256 */         if (attachmentData != null) {
/* 257 */           JsonProperty<?> m = (JsonProperty)attachmentData.getModifier().get(id);
/* 258 */           boolean value = resolve(m, resolver, clazz);
/* 259 */           if (value) {
/* 260 */             return true;
/*     */           }
/*     */         } else {
/* 263 */           CommonAttachmentIndex index = TimelessAPI.getCommonAttachmentIndex(attachmentId).orElse(null);
/* 264 */           if (index != null) {
/* 265 */             JsonProperty<?> m = (JsonProperty)index.getData().getModifier().get(id);
/* 266 */             boolean value = resolve(m, resolver, clazz);
/* 267 */             if (value)
/* 268 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 273 */     return false;
/*     */   }
/*     */   
/*     */   private static <T> boolean resolve(JsonProperty<?> raw, BooleanResolver<T> data, Class<T> type) {
/* 277 */     if (raw != null && raw.getValue() != null && raw.getValue().getClass().equals(type)) {
/* 278 */       return data.apply((T)raw.getValue());
/*     */     }
/* 280 */     return false;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface BooleanResolver<T> {
/*     */     boolean apply(T param1T);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\AttachmentDataUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */