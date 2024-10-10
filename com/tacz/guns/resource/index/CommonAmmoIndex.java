/*    */ package com.tacz.guns.resource.index;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.tacz.guns.resource.pojo.AmmoIndexPOJO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonAmmoIndex
/*    */ {
/*    */   private int stackSize;
/*    */   private AmmoIndexPOJO pojo;
/*    */   
/*    */   public static CommonAmmoIndex getInstance(AmmoIndexPOJO ammoIndexPOJO) throws IllegalArgumentException {
/* 14 */     CommonAmmoIndex index = new CommonAmmoIndex();
/* 15 */     index.pojo = ammoIndexPOJO;
/* 16 */     checkIndex(ammoIndexPOJO, index);
/* 17 */     return index;
/*    */   }
/*    */   
/*    */   private static void checkIndex(AmmoIndexPOJO ammoIndexPOJO, CommonAmmoIndex index) {
/* 21 */     Preconditions.checkArgument((ammoIndexPOJO != null), "index object file is empty");
/* 22 */     index.stackSize = Math.max(ammoIndexPOJO.getStackSize(), 1);
/*    */   }
/*    */   
/*    */   public int getStackSize() {
/* 26 */     return this.stackSize;
/*    */   }
/*    */   
/*    */   public AmmoIndexPOJO getPojo() {
/* 30 */     return this.pojo;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\index\CommonAmmoIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */