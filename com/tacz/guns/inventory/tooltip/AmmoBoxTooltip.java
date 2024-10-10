/*    */ package com.tacz.guns.inventory.tooltip;
/*    */ 
/*    */ import net.minecraft.world.inventory.tooltip.TooltipComponent;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class AmmoBoxTooltip implements TooltipComponent {
/*    */   private final ItemStack ammoBox;
/*    */   private final ItemStack ammo;
/*    */   private final int count;
/*    */   
/*    */   public AmmoBoxTooltip(ItemStack ammoBox, ItemStack ammo, int count) {
/* 12 */     this.ammoBox = ammoBox;
/* 13 */     this.ammo = ammo;
/* 14 */     this.count = count;
/*    */   }
/*    */   
/*    */   public ItemStack getAmmoBox() {
/* 18 */     return this.ammoBox;
/*    */   }
/*    */   
/*    */   public ItemStack getAmmo() {
/* 22 */     return this.ammo;
/*    */   }
/*    */   
/*    */   public int getCount() {
/* 26 */     return this.count;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\inventory\tooltip\AmmoBoxTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */