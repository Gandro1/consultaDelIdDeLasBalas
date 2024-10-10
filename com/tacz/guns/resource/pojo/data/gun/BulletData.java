/*     */ package com.tacz.guns.resource.pojo.data.gun;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class BulletData {
/*     */   @SerializedName("life")
/*   7 */   private float lifeSecond = 10.0F;
/*     */   
/*     */   @SerializedName("bullet_amount")
/*  10 */   private int bulletAmount = 1;
/*     */   
/*     */   @SerializedName("damage")
/*  13 */   private float damageAmount = 5.0F;
/*     */   @SerializedName("extra_damage")
/*     */   @Nullable
/*  16 */   private ExtraDamage extraDamage = null;
/*     */   
/*     */   @SerializedName("speed")
/*  19 */   private float speed = 5.0F;
/*     */   
/*     */   @SerializedName("gravity")
/*  22 */   private float gravity = 0.0F;
/*     */   
/*     */   @SerializedName("knockback")
/*  25 */   private float knockback = 0.0F;
/*     */   
/*     */   @SerializedName("friction")
/*  28 */   private float friction = 0.01F;
/*     */   
/*     */   @SerializedName("pierce")
/*  31 */   private int pierce = 1;
/*     */   
/*     */   @SerializedName("ignite")
/*  34 */   private Ignite ignite = new Ignite(false);
/*     */   
/*     */   @SerializedName("ignite_entity_time")
/*  37 */   private int igniteEntityTime = 2;
/*     */   
/*     */   @SerializedName("tracer_count_interval")
/*  40 */   private int tracerCountInterval = -1;
/*     */   
/*     */   @SerializedName("explosion")
/*     */   @Nullable
/*     */   private ExplosionData explosionData;
/*     */   
/*     */   public float getLifeSecond() {
/*  47 */     return this.lifeSecond;
/*     */   }
/*     */   
/*     */   public int getBulletAmount() {
/*  51 */     return this.bulletAmount;
/*     */   }
/*     */   
/*     */   public float getDamageAmount() {
/*  55 */     return this.damageAmount;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ExtraDamage getExtraDamage() {
/*  60 */     return this.extraDamage;
/*     */   }
/*     */   
/*     */   public float getSpeed() {
/*  64 */     return this.speed;
/*     */   }
/*     */   
/*     */   public float getGravity() {
/*  68 */     return this.gravity;
/*     */   }
/*     */   
/*     */   public float getKnockback() {
/*  72 */     return this.knockback;
/*     */   }
/*     */   
/*     */   public float getFriction() {
/*  76 */     return this.friction;
/*     */   }
/*     */   
/*     */   public int getPierce() {
/*  80 */     return this.pierce;
/*     */   }
/*     */   
/*     */   public Ignite getIgnite() {
/*  84 */     return this.ignite;
/*     */   }
/*     */   
/*     */   public int getIgniteEntityTime() {
/*  88 */     return this.igniteEntityTime;
/*     */   }
/*     */   
/*     */   public boolean hasTracerAmmo() {
/*  92 */     return (this.tracerCountInterval >= 0);
/*     */   }
/*     */   
/*     */   public int getTracerCountInterval() {
/*  96 */     return this.tracerCountInterval;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ExplosionData getExplosionData() {
/* 101 */     return this.explosionData;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\BulletData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */