/*    */ package com.tacz.guns.client.resource.pojo.animation.bedrock;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ public class AnimationBone
/*    */ {
/*    */   @SerializedName("position")
/*    */   private AnimationKeyframes position;
/*    */   @SerializedName("rotation")
/*    */   private AnimationKeyframes rotation;
/*    */   @SerializedName("scale")
/*    */   private AnimationKeyframes scale;
/*    */   
/*    */   public AnimationKeyframes getPosition() {
/* 16 */     return this.position;
/*    */   }
/*    */   
/*    */   public AnimationKeyframes getRotation() {
/* 20 */     return this.rotation;
/*    */   }
/*    */   
/*    */   public AnimationKeyframes getScale() {
/* 24 */     return this.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\bedrock\AnimationBone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */