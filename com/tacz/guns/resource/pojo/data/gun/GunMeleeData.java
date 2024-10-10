/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class GunMeleeData {
/*    */   @SerializedName("distance")
/*  8 */   private float distance = 1.0F;
/*    */   
/*    */   @SerializedName("cooldown")
/* 11 */   private float cooldown = 1.0F;
/*    */   @SerializedName("default")
/*    */   @Nullable
/* 14 */   private GunDefaultMeleeData defaultMeleeData = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public float getDistance() {
/* 19 */     return this.distance;
/*    */   }
/*    */   
/*    */   public float getCooldown() {
/* 23 */     return this.cooldown;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public GunDefaultMeleeData getDefaultMeleeData() {
/* 28 */     return this.defaultMeleeData;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunMeleeData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */