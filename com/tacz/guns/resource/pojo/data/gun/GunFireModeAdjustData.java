/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class GunFireModeAdjustData {
/*    */   @SerializedName("damage")
/*  6 */   private float damageAmount = 0.0F;
/*    */   
/*    */   @SerializedName("rpm")
/*  9 */   private int roundsPerMinute = 0;
/*    */   
/*    */   @SerializedName("speed")
/* 12 */   private float speed = 0.0F;
/*    */   
/*    */   @SerializedName("knockback")
/* 15 */   private float knockback = 0.0F;
/*    */   
/*    */   @SerializedName("armor_ignore")
/* 18 */   private float armorIgnore = 0.0F;
/*    */   
/*    */   @SerializedName("head_shot_multiplier")
/* 21 */   private float headShotMultiplier = 0.0F;
/*    */   
/*    */   @SerializedName("aim_inaccuracy")
/* 24 */   private float aimInaccuracy = 0.0F;
/*    */   
/*    */   @SerializedName("other_inaccuracy")
/* 27 */   private float otherInaccuracy = 0.0F;
/*    */ 
/*    */   
/*    */   public float getDamageAmount() {
/* 31 */     return this.damageAmount;
/*    */   }
/*    */   
/*    */   public int getRoundsPerMinute() {
/* 35 */     return this.roundsPerMinute;
/*    */   }
/*    */   
/*    */   public float getSpeed() {
/* 39 */     return this.speed;
/*    */   }
/*    */   
/*    */   public float getKnockback() {
/* 43 */     return this.knockback;
/*    */   }
/*    */   
/*    */   public float getArmorIgnore() {
/* 47 */     return this.armorIgnore;
/*    */   }
/*    */   
/*    */   public float getHeadShotMultiplier() {
/* 51 */     return this.headShotMultiplier;
/*    */   }
/*    */   
/*    */   public float getAimInaccuracy() {
/* 55 */     return this.aimInaccuracy;
/*    */   }
/*    */   
/*    */   public float getOtherInaccuracy() {
/* 59 */     return this.otherInaccuracy;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunFireModeAdjustData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */