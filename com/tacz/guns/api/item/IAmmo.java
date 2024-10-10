/*    */ package com.tacz.guns.api.item;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IAmmo
/*    */ {
/*    */   @Nullable
/*    */   static IAmmo getIAmmoOrNull(@Nullable ItemStack stack) {
/* 14 */     if (stack == null) {
/* 15 */       return null;
/*    */     }
/* 17 */     Item item = stack.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/* 18 */       return iAmmo; }
/*    */     
/* 20 */     return null;
/*    */   }
/*    */   
/*    */   ResourceLocation getAmmoId(ItemStack paramItemStack);
/*    */   
/*    */   void setAmmoId(ItemStack paramItemStack, @Nullable ResourceLocation paramResourceLocation);
/*    */   
/*    */   boolean isAmmoOfGun(ItemStack paramItemStack1, ItemStack paramItemStack2);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\IAmmo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */