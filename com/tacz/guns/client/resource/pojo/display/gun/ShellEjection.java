/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class ShellEjection {
/*    */   @SerializedName("initial_velocity")
/*  7 */   private Vector3f initialVelocity = new Vector3f(8.0F, 5.0F, -0.5F);
/*    */   
/*    */   @SerializedName("random_velocity")
/* 10 */   private Vector3f randomVelocity = new Vector3f(2.5F, 1.5F, 0.25F);
/*    */   
/*    */   @SerializedName("acceleration")
/* 13 */   private Vector3f acceleration = new Vector3f(0.0F, -20.0F, 0.0F);
/*    */   
/*    */   @SerializedName("angular_velocity")
/* 16 */   private Vector3f angularVelocity = new Vector3f(-720.0F, -720.0F, 90.0F);
/*    */   
/*    */   @SerializedName("living_time")
/* 19 */   private float livingTime = 1.0F;
/*    */ 
/*    */   
/*    */   public Vector3f getInitialVelocity() {
/* 23 */     return this.initialVelocity;
/*    */   }
/*    */   
/*    */   public Vector3f getRandomVelocity() {
/* 27 */     return this.randomVelocity;
/*    */   }
/*    */   
/*    */   public Vector3f getAcceleration() {
/* 31 */     return this.acceleration;
/*    */   }
/*    */   
/*    */   public Vector3f getAngularVelocity() {
/* 35 */     return this.angularVelocity;
/*    */   }
/*    */   
/*    */   public float getLivingTime() {
/* 39 */     return this.livingTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\ShellEjection.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */