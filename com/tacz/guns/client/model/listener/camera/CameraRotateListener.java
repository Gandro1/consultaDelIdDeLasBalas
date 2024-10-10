/*    */ package com.tacz.guns.client.model.listener.camera;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.util.math.MathUtil;
/*    */ import org.joml.Quaternionf;
/*    */ 
/*    */ public class CameraRotateListener implements AnimationListener {
/*    */   private final CameraAnimationObject camera;
/*    */   
/*    */   public CameraRotateListener(CameraAnimationObject camera) {
/* 12 */     this.camera = camera;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 17 */     if (values.length == 4) {
/* 18 */       values = MathUtil.toEulerAngles(values);
/*    */     }
/* 20 */     float xRot = values[0];
/* 21 */     float yRot = values[1];
/* 22 */     float zRot = -values[2];
/*    */ 
/*    */     
/* 25 */     if (blend) {
/* 26 */       float[] q = MathUtil.toQuaternion(xRot, yRot, zRot);
/* 27 */       Quaternionf quaternion = MathUtil.toQuaternion(q);
/* 28 */       MathUtil.blendQuaternion(this.camera.rotationQuaternion, quaternion);
/*    */     } else {
/* 30 */       MathUtil.toQuaternion(xRot, yRot, zRot, this.camera.rotationQuaternion);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 36 */     return MathUtil.toQuaternion(this.camera.cameraRenderer.getRotateAngleX(), this.camera.cameraRenderer.getRotateAngleY(), this.camera.cameraRenderer.getRotateAngleZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 41 */     return ObjectAnimationChannel.ChannelType.ROTATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\camera\CameraRotateListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */