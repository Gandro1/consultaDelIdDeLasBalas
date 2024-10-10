/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class GunReloadTime {
/*    */   @SerializedName("empty")
/*  6 */   private float emptyTime = 2.5F;
/*    */   
/*    */   @SerializedName("tactical")
/*  9 */   private float tacticalTime = 2.0F;
/*    */ 
/*    */   
/*    */   public float getEmptyTime() {
/* 13 */     return this.emptyTime;
/*    */   }
/*    */   
/*    */   public float getTacticalTime() {
/* 17 */     return this.tacticalTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunReloadTime.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */