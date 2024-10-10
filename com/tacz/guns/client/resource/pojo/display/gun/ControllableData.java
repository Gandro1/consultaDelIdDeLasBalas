/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class ControllableData {
/*    */   @SerializedName("low_frequency")
/*  6 */   private float lowFrequency = 0.25F;
/*    */   
/*    */   @SerializedName("high_frequency")
/*  9 */   private float highFrequency = 0.5F;
/*    */   
/*    */   @SerializedName("time")
/* 12 */   private int timeInMs = 80;
/*    */ 
/*    */   
/*    */   public float getLowFrequency() {
/* 16 */     return this.lowFrequency;
/*    */   }
/*    */   
/*    */   public float getHighFrequency() {
/* 20 */     return this.highFrequency;
/*    */   }
/*    */   
/*    */   public int getTimeInMs() {
/* 24 */     return this.timeInMs;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\ControllableData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */