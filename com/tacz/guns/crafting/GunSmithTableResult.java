/*    */ package com.tacz.guns.crafting;
/*    */ 
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class GunSmithTableResult
/*    */ {
/*    */   public static final String GUN = "gun";
/*    */   public static final String AMMO = "ammo";
/*    */   public static final String ATTACHMENT = "attachment";
/*    */   private final ItemStack result;
/*    */   private final String group;
/*    */   
/*    */   public GunSmithTableResult(ItemStack result, String group) {
/* 14 */     this.result = result;
/* 15 */     this.group = group;
/*    */   }
/*    */   
/*    */   public ItemStack getResult() {
/* 19 */     return this.result;
/*    */   }
/*    */   
/*    */   public String getGroup() {
/* 23 */     return this.group;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\crafting\GunSmithTableResult.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */