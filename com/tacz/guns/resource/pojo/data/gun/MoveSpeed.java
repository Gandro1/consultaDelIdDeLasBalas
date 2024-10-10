/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MoveSpeed {
/*    */   @SerializedName("base")
/*  8 */   private float baseMultiplier = 0.0F;
/*    */   
/*    */   @SerializedName("aim")
/* 11 */   private float aimMultiplier = 0.0F;
/*    */   
/*    */   @SerializedName("reload")
/* 14 */   private float reloadMultiplier = 0.0F;
/*    */ 
/*    */   
/*    */   public float getBaseMultiplier() {
/* 18 */     return this.baseMultiplier;
/*    */   }
/*    */   
/*    */   public float getAimMultiplier() {
/* 22 */     return this.aimMultiplier;
/*    */   }
/*    */   
/*    */   public float getReloadMultiplier() {
/* 26 */     return this.reloadMultiplier;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MoveSpeed of(MoveSpeed moveSpeed, List<MoveSpeed> modifiers) {
/* 31 */     MoveSpeed result = new MoveSpeed();
/* 32 */     result.baseMultiplier = moveSpeed.baseMultiplier;
/* 33 */     result.aimMultiplier = moveSpeed.aimMultiplier;
/* 34 */     result.reloadMultiplier = moveSpeed.reloadMultiplier;
/* 35 */     for (MoveSpeed modifier : modifiers) {
/* 36 */       result.baseMultiplier += modifier.baseMultiplier;
/* 37 */       result.aimMultiplier += modifier.aimMultiplier;
/* 38 */       result.reloadMultiplier += modifier.reloadMultiplier;
/*    */     } 
/* 40 */     result.baseMultiplier = Math.max(0.0F, result.baseMultiplier);
/* 41 */     result.aimMultiplier = Math.max(0.0F, result.aimMultiplier);
/* 42 */     result.reloadMultiplier = Math.max(0.0F, result.reloadMultiplier);
/* 43 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\MoveSpeed.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */