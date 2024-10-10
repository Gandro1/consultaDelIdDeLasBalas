package com.tacz.guns.api.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IAmmoBox {
  ResourceLocation getAmmoId(ItemStack paramItemStack);
  
  int getAmmoCount(ItemStack paramItemStack);
  
  void setAmmoId(ItemStack paramItemStack, ResourceLocation paramResourceLocation);
  
  void setAmmoCount(ItemStack paramItemStack, int paramInt);
  
  boolean isAmmoBoxOfGun(ItemStack paramItemStack1, ItemStack paramItemStack2);
  
  ItemStack setAmmoLevel(ItemStack paramItemStack, int paramInt);
  
  int getAmmoLevel(ItemStack paramItemStack);
  
  boolean isCreative(ItemStack paramItemStack);
  
  boolean isAllTypeCreative(ItemStack paramItemStack);
  
  ItemStack setCreative(ItemStack paramItemStack, boolean paramBoolean);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\IAmmoBox.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */