/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class GunDefaultMeleeData {
/*    */   @SerializedName("animation_type")
/*  6 */   private String animationType = "melee_push";
/*    */   
/*    */   @SerializedName("distance")
/*  9 */   private float distance = 1.0F;
/*    */   
/*    */   @SerializedName("range_angle")
/* 12 */   private float rangeAngle = 30.0F;
/*    */   
/*    */   @SerializedName("cooldown")
/* 15 */   private float cooldown = 0.0F;
/*    */   
/*    */   @SerializedName("damage")
/* 18 */   private float damage = 0.0F;
/*    */   
/*    */   @SerializedName("knockback")
/* 21 */   private float knockback = 0.2F;
/*    */   
/*    */   @SerializedName("prep")
/* 24 */   private float prepTime = 0.1F;
/*    */ 
/*    */   
/*    */   public String getAnimationType() {
/* 28 */     return this.animationType;
/*    */   }
/*    */   
/*    */   public float getDistance() {
/* 32 */     return this.distance;
/*    */   }
/*    */   
/*    */   public float getRangeAngle() {
/* 36 */     return this.rangeAngle;
/*    */   }
/*    */   
/*    */   public float getCooldown() {
/* 40 */     return this.cooldown;
/*    */   }
/*    */   
/*    */   public float getDamage() {
/* 44 */     return this.damage;
/*    */   }
/*    */   
/*    */   public float getKnockback() {
/* 48 */     return this.knockback;
/*    */   }
/*    */   
/*    */   public float getPrepTime() {
/* 52 */     return this.prepTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunDefaultMeleeData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */