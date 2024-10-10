/*    */ package com.tacz.guns.api.item.nbt;
/*    */ 
/*    */ import com.tacz.guns.api.DefaultAssets;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IAmmo;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public interface AmmoItemDataAccessor
/*    */   extends IAmmo {
/*    */   public static final String AMMO_ID_TAG = "AmmoId";
/*    */   
/*    */   @Nonnull
/*    */   default ResourceLocation getAmmoId(ItemStack ammo) {
/* 22 */     CompoundTag nbt = ammo.m_41784_();
/* 23 */     if (nbt.m_128425_("AmmoId", 8)) {
/* 24 */       ResourceLocation gunId = ResourceLocation.m_135820_(nbt.m_128461_("AmmoId"));
/* 25 */       return Objects.<ResourceLocation>requireNonNullElse(gunId, DefaultAssets.EMPTY_AMMO_ID);
/*    */     } 
/* 27 */     return DefaultAssets.EMPTY_AMMO_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   default void setAmmoId(ItemStack ammo, @Nullable ResourceLocation ammoId) {
/* 32 */     CompoundTag nbt = ammo.m_41784_();
/* 33 */     if (ammoId != null) {
/* 34 */       nbt.m_128359_("AmmoId", ammoId.toString());
/*    */       return;
/*    */     } 
/* 37 */     nbt.m_128359_("AmmoId", DefaultAssets.DEFAULT_AMMO_ID.toString());
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean isAmmoOfGun(ItemStack gun, ItemStack ammo) {
/* 42 */     Item item = gun.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item; item = ammo.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/* 43 */         ResourceLocation gunId = iGun.getGunId(gun);
/* 44 */         ResourceLocation ammoId = iAmmo.getAmmoId(ammo);
/* 45 */         return ((Boolean)TimelessAPI.getCommonGunIndex(gunId).map(gunIndex -> Boolean.valueOf(gunIndex.getGunData().getAmmoId().equals(ammoId))).orElse(Boolean.valueOf(false))).booleanValue(); }
/*    */        }
/* 47 */      return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\nbt\AmmoItemDataAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */