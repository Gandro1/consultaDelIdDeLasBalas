/*    */ package com.tacz.guns.api.item.builder;
/*    */ import com.tacz.guns.api.DefaultAssets;
/*    */ import com.tacz.guns.api.item.IAmmo;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public final class AmmoItemBuilder {
/* 10 */   private int count = 1;
/* 11 */   private ResourceLocation ammoId = DefaultAssets.DEFAULT_AMMO_ID;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AmmoItemBuilder create() {
/* 17 */     return new AmmoItemBuilder();
/*    */   }
/*    */   
/*    */   public AmmoItemBuilder setCount(int count) {
/* 21 */     this.count = Math.max(count, 1);
/* 22 */     return this;
/*    */   }
/*    */   
/*    */   public AmmoItemBuilder setId(ResourceLocation id) {
/* 26 */     this.ammoId = id;
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public ItemStack build() {
/* 31 */     ItemStack ammo = new ItemStack((ItemLike)ModItems.AMMO.get(), this.count);
/* 32 */     Item item = ammo.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/* 33 */       iAmmo.setAmmoId(ammo, this.ammoId); }
/*    */     
/* 35 */     return ammo;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\builder\AmmoItemBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */