/*    */ package com.tacz.guns.resource.pojo.data.recipe;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.crafting.GunSmithTableIngredient;
/*    */ import com.tacz.guns.crafting.GunSmithTableResult;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class TableRecipe
/*    */ {
/*    */   @SerializedName("materials")
/*    */   private List<GunSmithTableIngredient> materials;
/*    */   @SerializedName("result")
/*    */   private GunSmithTableResult result;
/*    */   
/*    */   public List<GunSmithTableIngredient> getMaterials() {
/* 17 */     return this.materials;
/*    */   }
/*    */   
/*    */   public GunSmithTableResult getResult() {
/* 21 */     return this.result;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\recipe\TableRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */