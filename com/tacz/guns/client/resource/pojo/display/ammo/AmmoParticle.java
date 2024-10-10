/*    */ package com.tacz.guns.client.resource.pojo.display.ammo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class AmmoParticle
/*    */ {
/* 10 */   private static final Vector3f ZERO = new Vector3f(0.0F, 0.0F, 0.0F);
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   @SerializedName("delta")
/* 14 */   private Vector3f delta = ZERO;
/*    */   
/*    */   @SerializedName("speed")
/* 17 */   private float speed = 0.0F;
/*    */   
/*    */   @SerializedName("life_time")
/* 20 */   private int lifeTime = 20;
/*    */   
/*    */   @SerializedName("count")
/* 23 */   private int count = 1;
/*    */ 
/*    */   
/*    */   private transient ParticleOptions particleOptions;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return this.name;
/*    */   }
/*    */   
/*    */   public Vector3f getDelta() {
/* 34 */     return this.delta;
/*    */   }
/*    */   
/*    */   public float getSpeed() {
/* 38 */     return this.speed;
/*    */   }
/*    */   
/*    */   public int getCount() {
/* 42 */     return this.count;
/*    */   }
/*    */   
/*    */   public int getLifeTime() {
/* 46 */     return this.lifeTime;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ParticleOptions getParticleOptions() {
/* 51 */     return this.particleOptions;
/*    */   }
/*    */   
/*    */   public void setParticleOptions(ParticleOptions particleOptions) {
/* 55 */     this.particleOptions = particleOptions;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\ammo\AmmoParticle.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */