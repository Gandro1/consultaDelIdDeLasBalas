/*    */ package com.tacz.guns.client.model.listener.camera;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.AnimationListenerSupplier;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*    */ import org.joml.Quaternionf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CameraAnimationObject
/*    */   implements AnimationListenerSupplier
/*    */ {
/* 15 */   public Quaternionf rotationQuaternion = new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F);
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelRendererWrapper cameraRenderer;
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimationListener supplyListeners(String nodeName, ObjectAnimationChannel.ChannelType type) {
/* 24 */     if (!nodeName.equals("camera")) {
/* 25 */       return null;
/*    */     }
/* 27 */     if (type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 28 */       return new CameraRotateListener(this);
/*    */     }
/* 30 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\camera\CameraAnimationObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */