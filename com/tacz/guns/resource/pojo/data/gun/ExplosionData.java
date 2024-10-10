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
/*    */ public class ExplosionData
/*    */ {
/*    */   @SerializedName("explode")
/*    */   private boolean explode;
/*    */   @SerializedName("radius")
/*    */   private float radius;
/*    */   @SerializedName("damage")
/*    */   private float damage;
/*    */   @SerializedName("knockback")
/*    */   private boolean knockback;
/*    */   @SerializedName("destroy_block")
/*    */   private boolean destroyBlock;
/*    */   @SerializedName("delay")
/*    */   private int delay;
/*    */   
/*    */   public ExplosionData(boolean explode, float radius, float damage, boolean knockback, int delay, boolean destroyBlock) {
/* 28 */     this.explode = explode;
/* 29 */     this.radius = radius;
/* 30 */     this.damage = damage;
/* 31 */     this.knockback = knockback;
/* 32 */     this.delay = delay;
/* 33 */     this.destroyBlock = destroyBlock;
/*    */   }
/*    */   
/*    */   public boolean isExplode() {
/* 37 */     return this.explode;
/*    */   }
/*    */   
/*    */   public float getRadius() {
/* 41 */     return this.radius;
/*    */   }
/*    */   
/*    */   public float getDamage() {
/* 45 */     return this.damage;
/*    */   }
/*    */   
/*    */   public boolean isKnockback() {
/* 49 */     return this.knockback;
/*    */   }
/*    */   
/*    */   public boolean isDestroyBlock() {
/* 53 */     return this.destroyBlock;
/*    */   }
/*    */   
/*    */   public int getDelay() {
/* 57 */     return this.delay;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\ExplosionData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */