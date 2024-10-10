/*    */ package com.tacz.guns.client.model.listener.model;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*    */ import com.tacz.guns.util.math.MathUtil;
/*    */ import org.joml.Quaternionf;
/*    */ 
/*    */ public class ModelRotateListener implements AnimationListener {
/*    */   private final ModelRendererWrapper rendererWrapper;
/*    */   
/*    */   public ModelRotateListener(ModelRendererWrapper rendererWrapper) {
/* 13 */     this.rendererWrapper = rendererWrapper;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 18 */     if (values.length == 4) {
/* 19 */       values = MathUtil.toEulerAngles(values);
/*    */     }
/* 21 */     if (blend) {
/* 22 */       float[] q = MathUtil.toQuaternion(values[0], values[1], values[2]);
/* 23 */       Quaternionf quaternion = MathUtil.toQuaternion(q);
/* 24 */       MathUtil.blendQuaternion(this.rendererWrapper.getAdditionalQuaternion(), quaternion);
/*    */     } else {
/* 26 */       MathUtil.toQuaternion(values[0], values[1], values[2], this.rendererWrapper.getAdditionalQuaternion());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 32 */     return MathUtil.toQuaternion(this.rendererWrapper.getRotateAngleX(), this.rendererWrapper.getRotateAngleY(), this.rendererWrapper.getRotateAngleZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 37 */     return ObjectAnimationChannel.ChannelType.ROTATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\model\ModelRotateListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */