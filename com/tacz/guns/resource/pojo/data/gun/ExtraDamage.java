/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.LinkedList;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ExtraDamage {
/*    */   @SerializedName("armor_ignore")
/* 10 */   private float armorIgnore = 0.0F;
/*    */   
/*    */   @SerializedName("head_shot_multiplier")
/* 13 */   private float headShotMultiplier = 1.0F;
/*    */ 
/*    */   
/*    */   @SerializedName("damage_adjust")
/* 17 */   private LinkedList<DistanceDamagePair> damageAdjust = Lists.newLinkedList();
/*    */   
/*    */   public float getArmorIgnore() {
/* 20 */     return this.armorIgnore;
/*    */   }
/*    */   
/*    */   public float getHeadShotMultiplier() {
/* 24 */     return this.headShotMultiplier;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LinkedList<DistanceDamagePair> getDamageAdjust() {
/* 29 */     return this.damageAdjust.isEmpty() ? null : this.damageAdjust;
/*    */   }
/*    */   
/*    */   public static class DistanceDamagePair
/*    */   {
/*    */     @SerializedName("distance")
/*    */     private float distance;
/*    */     @SerializedName("damage")
/*    */     private float damage;
/*    */     
/*    */     public DistanceDamagePair(float distance, float damage) {
/* 40 */       this.distance = distance;
/* 41 */       this.damage = damage;
/*    */     }
/*    */     
/*    */     public float getDistance() {
/* 45 */       return this.distance;
/*    */     }
/*    */     
/*    */     public float getDamage() {
/* 49 */       return this.damage;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\ExtraDamage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */