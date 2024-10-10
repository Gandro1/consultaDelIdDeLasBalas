/*    */ package com.tacz.guns.client.resource.pojo.animation.gltf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimationChannel
/*    */ {
/*    */   private Integer sampler;
/*    */   private AnimationChannelTarget target;
/*    */   
/*    */   public Integer getSampler() {
/* 21 */     return this.sampler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSampler(Integer sampler) {
/* 32 */     if (sampler == null) {
/* 33 */       throw new NullPointerException("Invalid value for sampler: " + sampler + ", may not be null");
/*    */     }
/* 35 */     this.sampler = sampler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimationChannelTarget getTarget() {
/* 44 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTarget(AnimationChannelTarget target) {
/* 54 */     if (target == null) {
/* 55 */       throw new NullPointerException("Invalid value for target: " + target + ", may not be null");
/*    */     }
/* 57 */     this.target = target;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AnimationChannel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */