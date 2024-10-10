/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class BurstData {
/*    */   @SerializedName("continuous_shoot")
/*    */   private boolean continuousShoot = false;
/*    */   @SerializedName("count")
/*  9 */   private int count = 3;
/*    */   
/*    */   @SerializedName("bpm")
/* 12 */   private int bpm = 200;
/*    */   
/*    */   @SerializedName("min_interval")
/* 15 */   private double minInterval = 1.0D;
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 19 */     return this.count;
/*    */   }
/*    */   
/*    */   public int getBpm() {
/* 23 */     return this.bpm;
/*    */   }
/*    */   
/*    */   public double getMinInterval() {
/* 27 */     return this.minInterval;
/*    */   }
/*    */   
/*    */   public boolean isContinuousShoot() {
/* 31 */     return this.continuousShoot;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\BurstData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */