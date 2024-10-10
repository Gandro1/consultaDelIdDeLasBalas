/*    */ package com.tacz.guns.resource.pojo.data.attachment;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MeleeData {
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
/* 21 */   private float knockback = 0.0F;
/*    */   
/*    */   @SerializedName("prep")
/* 24 */   private float prepTime = 0.1F;
/*    */ 
/*    */   
/*    */   @SerializedName("effects")
/* 28 */   private List<EffectData> effects = Lists.newArrayList();
/*    */   
/*    */   public float getDistance() {
/* 31 */     return this.distance;
/*    */   }
/*    */   
/*    */   public float getRangeAngle() {
/* 35 */     return this.rangeAngle;
/*    */   }
/*    */   
/*    */   public float getCooldown() {
/* 39 */     return this.cooldown;
/*    */   }
/*    */   
/*    */   public float getDamage() {
/* 43 */     return this.damage;
/*    */   }
/*    */   
/*    */   public float getKnockback() {
/* 47 */     return this.knockback;
/*    */   }
/*    */   
/*    */   public float getPrepTime() {
/* 51 */     return this.prepTime;
/*    */   }
/*    */   
/*    */   public List<EffectData> getEffects() {
/* 55 */     return this.effects;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\attachment\MeleeData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */