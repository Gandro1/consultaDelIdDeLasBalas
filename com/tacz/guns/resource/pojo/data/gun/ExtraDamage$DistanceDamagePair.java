/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DistanceDamagePair
/*    */ {
/*    */   @SerializedName("distance")
/*    */   private float distance;
/*    */   @SerializedName("damage")
/*    */   private float damage;
/*    */   
/*    */   public DistanceDamagePair(float distance, float damage) {
/* 40 */     this.distance = distance;
/* 41 */     this.damage = damage;
/*    */   }
/*    */   
/*    */   public float getDistance() {
/* 45 */     return this.distance;
/*    */   }
/*    */   
/*    */   public float getDamage() {
/* 49 */     return this.damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\ExtraDamage$DistanceDamagePair.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */