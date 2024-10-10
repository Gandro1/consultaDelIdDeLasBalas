/*    */ package com.tacz.guns.client.model.listener.constraint;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.AnimationListenerSupplier;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import com.tacz.guns.client.resource.pojo.model.BonesItem;
/*    */ import javax.annotation.Nullable;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ 
/*    */ public class ConstraintObject
/*    */   implements AnimationListenerSupplier
/*    */ {
/* 15 */   public Vector3f translationConstraint = new Vector3f(0.0F, 0.0F, 0.0F);
/* 16 */   public Vector3f rotationConstraint = new Vector3f(0.0F, 0.0F, 0.0F);
/*    */ 
/*    */ 
/*    */   
/*    */   public BedrockPart node;
/*    */ 
/*    */   
/*    */   public BonesItem bonesItem;
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AnimationListener supplyListeners(String nodeName, ObjectAnimationChannel.ChannelType type) {
/* 29 */     if (!nodeName.equals("constraint")) {
/* 30 */       return null;
/*    */     }
/* 32 */     if (type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 33 */       return new ConstraintRotateListener(this);
/*    */     }
/* 35 */     if (type.equals(ObjectAnimationChannel.ChannelType.TRANSLATION)) {
/* 36 */       return new ConstraintTranslateListener(this);
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\constraint\ConstraintObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */