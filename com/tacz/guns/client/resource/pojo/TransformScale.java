/*    */ package com.tacz.guns.client.resource.pojo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class TransformScale
/*    */ {
/*    */   @SerializedName("thirdperson")
/*    */   @Nullable
/*    */   private Vector3f thirdPerson;
/*    */   @SerializedName("ground")
/*    */   @Nullable
/*    */   private Vector3f ground;
/*    */   @SerializedName("fixed")
/*    */   @Nullable
/*    */   private Vector3f fixed;
/*    */   
/*    */   public static TransformScale getAmmoDefault() {
/* 20 */     TransformScale transformScale = new TransformScale();
/* 21 */     transformScale.thirdPerson = new Vector3f(0.75F, 0.75F, 0.75F);
/* 22 */     transformScale.ground = new Vector3f(0.75F, 0.75F, 0.75F);
/* 23 */     transformScale.fixed = new Vector3f(1.5F, 1.5F, 1.5F);
/* 24 */     return transformScale;
/*    */   }
/*    */   
/*    */   public static TransformScale getGunDefault() {
/* 28 */     TransformScale transformScale = new TransformScale();
/* 29 */     transformScale.thirdPerson = new Vector3f(0.6F, 0.6F, 0.6F);
/* 30 */     transformScale.ground = new Vector3f(0.6F, 0.6F, 0.6F);
/* 31 */     transformScale.fixed = new Vector3f(1.2F, 1.2F, 1.2F);
/* 32 */     return transformScale;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Vector3f getThirdPerson() {
/* 37 */     return this.thirdPerson;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Vector3f getGround() {
/* 42 */     return this.ground;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Vector3f getFixed() {
/* 47 */     return this.fixed;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\TransformScale.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */