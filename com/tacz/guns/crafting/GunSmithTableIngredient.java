/*    */ package com.tacz.guns.crafting;
/*    */ 
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ 
/*    */ public class GunSmithTableIngredient {
/*    */   private final Ingredient ingredient;
/*    */   private final int count;
/*    */   
/*    */   public GunSmithTableIngredient(Ingredient ingredient, int count) {
/* 10 */     this.ingredient = ingredient;
/* 11 */     this.count = count;
/*    */   }
/*    */   
/*    */   public Ingredient getIngredient() {
/* 15 */     return this.ingredient;
/*    */   }
/*    */   
/*    */   public int getCount() {
/* 19 */     return this.count;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\crafting\GunSmithTableIngredient.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */