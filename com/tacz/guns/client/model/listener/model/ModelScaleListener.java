/*    */ package com.tacz.guns.client.model.listener.model;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*    */ 
/*    */ public class ModelScaleListener implements AnimationListener {
/*    */   private final ModelRendererWrapper rendererWrapper;
/*    */   
/*    */   public ModelScaleListener(ModelRendererWrapper rendererWrapper) {
/* 11 */     this.rendererWrapper = rendererWrapper;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 16 */     if (blend) {
/* 17 */       this.rendererWrapper.setScaleX(this.rendererWrapper.getScaleX() * values[0]);
/* 18 */       this.rendererWrapper.setScaleY(this.rendererWrapper.getScaleY() * values[1]);
/* 19 */       this.rendererWrapper.setScaleZ(this.rendererWrapper.getScaleZ() * values[2]);
/*    */     } else {
/* 21 */       this.rendererWrapper.setScaleX(values[0]);
/* 22 */       this.rendererWrapper.setScaleY(values[1]);
/* 23 */       this.rendererWrapper.setScaleZ(values[2]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 29 */     return new float[] { 1.0F, 1.0F, 1.0F };
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 34 */     return ObjectAnimationChannel.ChannelType.SCALE;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\model\ModelScaleListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */