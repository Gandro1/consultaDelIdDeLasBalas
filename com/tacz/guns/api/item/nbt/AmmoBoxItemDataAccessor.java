/*     */ package com.tacz.guns.api.item.nbt;
/*     */ 
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAmmoBox;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public interface AmmoBoxItemDataAccessor extends IAmmoBox {
/*     */   public static final String AMMO_ID_TAG = "AmmoId";
/*     */   public static final String AMMO_COUNT_TAG = "AmmoCount";
/*     */   public static final String CREATIVE_TAG = "Creative";
/*     */   public static final String ALL_TYPE_CREATIVE_TAG = "AllTypeCreative";
/*     */   public static final String LEVEL_TAG = "Level";
/*     */   
/*     */   default ResourceLocation getAmmoId(ItemStack ammoBox) {
/*  21 */     CompoundTag tag = ammoBox.m_41784_();
/*  22 */     if (tag.m_128425_("AmmoId", 8)) {
/*  23 */       return new ResourceLocation(tag.m_128461_("AmmoId"));
/*     */     }
/*  25 */     return DefaultAssets.EMPTY_AMMO_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setAmmoId(ItemStack ammoBox, ResourceLocation ammoId) {
/*  30 */     CompoundTag tag = ammoBox.m_41784_();
/*  31 */     tag.m_128359_("AmmoId", ammoId.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   default int getAmmoCount(ItemStack ammoBox) {
/*  36 */     CompoundTag tag = ammoBox.m_41784_();
/*  37 */     if (isAllTypeCreative(ammoBox) || isCreative(ammoBox)) {
/*  38 */       return Integer.MAX_VALUE;
/*     */     }
/*  40 */     if (tag.m_128425_("AmmoCount", 3)) {
/*  41 */       return tag.m_128451_("AmmoCount");
/*     */     }
/*  43 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setAmmoCount(ItemStack ammoBox, int count) {
/*  48 */     CompoundTag tag = ammoBox.m_41784_();
/*  49 */     if (isCreative(ammoBox)) {
/*  50 */       tag.m_128405_("AmmoCount", 2147483647);
/*     */       return;
/*     */     } 
/*  53 */     tag.m_128405_("AmmoCount", count);
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isAmmoBoxOfGun(ItemStack gun, ItemStack ammoBox) {
/*  58 */     Item item = gun.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item; item = ammoBox.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item;
/*  59 */         if (isAllTypeCreative(ammoBox)) {
/*  60 */           return true;
/*     */         }
/*  62 */         ResourceLocation ammoId = iAmmoBox.getAmmoId(ammoBox);
/*  63 */         if (ammoId.equals(DefaultAssets.EMPTY_AMMO_ID)) {
/*  64 */           return false;
/*     */         }
/*  66 */         ResourceLocation gunId = iGun.getGunId(gun);
/*  67 */         return ((Boolean)TimelessAPI.getCommonGunIndex(gunId).map(gunIndex -> Boolean.valueOf(gunIndex.getGunData().getAmmoId().equals(ammoId))).orElse(Boolean.valueOf(false))).booleanValue(); }
/*     */        }
/*  69 */      return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default ItemStack setAmmoLevel(ItemStack ammoBox, int level) {
/*  74 */     CompoundTag tag = ammoBox.m_41784_();
/*  75 */     tag.m_128405_("Level", Math.max(level, 0));
/*  76 */     return ammoBox;
/*     */   }
/*     */ 
/*     */   
/*     */   default int getAmmoLevel(ItemStack ammoBox) {
/*  81 */     CompoundTag tag = ammoBox.m_41784_();
/*  82 */     if (tag.m_128425_("Level", 3)) {
/*  83 */       return tag.m_128451_("Level");
/*     */     }
/*  85 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isCreative(ItemStack ammoBox) {
/*  90 */     CompoundTag tag = ammoBox.m_41783_();
/*  91 */     if (tag != null && tag.m_128425_("Creative", 1)) {
/*  92 */       return tag.m_128471_("Creative");
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isAllTypeCreative(ItemStack ammoBox) {
/*  99 */     CompoundTag tag = ammoBox.m_41783_();
/* 100 */     if (tag != null && tag.m_128425_("AllTypeCreative", 1)) {
/* 101 */       return tag.m_128471_("AllTypeCreative");
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default ItemStack setCreative(ItemStack ammoBox, boolean isAllType) {
/* 108 */     CompoundTag tag = ammoBox.m_41784_();
/* 109 */     if (isAllType) {
/*     */       
/* 111 */       if (tag.m_128425_("Creative", 1)) {
/* 112 */         tag.m_128473_("Creative");
/*     */       }
/* 114 */       tag.m_128379_("AllTypeCreative", true);
/* 115 */       return ammoBox;
/*     */     } 
/*     */     
/* 118 */     if (tag.m_128425_("AllTypeCreative", 1)) {
/* 119 */       tag.m_128473_("AllTypeCreative");
/*     */     }
/* 121 */     tag.m_128379_("Creative", true);
/* 122 */     return ammoBox;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\nbt\AmmoBoxItemDataAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */