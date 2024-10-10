/*    */ package com.tacz.guns.client.model.listener.constraint;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.util.math.MathUtil;
/*    */ 
/*    */ public class ConstraintRotateListener implements AnimationListener {
/*    */   private final ConstraintObject constraint;
/*    */   
/*    */   public ConstraintRotateListener(ConstraintObject constraint) {
/* 11 */     this.constraint = constraint;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 16 */     if (values.length == 4) {
/* 17 */       values = MathUtil.toEulerAngles(values);
/*    */     }
/* 19 */     if (blend) {
/* 20 */       this.constraint.rotationConstraint.set(
/* 21 */           (float)Math.max(this.constraint.rotationConstraint.x(), MathUtil.toDegreePositive(values[0])), 
/* 22 */           (float)Math.max(this.constraint.rotationConstraint.y(), MathUtil.toDegreePositive(values[1])), 
/* 23 */           (float)Math.max(this.constraint.rotationConstraint.z(), MathUtil.toDegreePositive(values[2])));
/*    */     } else {
/*    */       
/* 26 */       this.constraint.rotationConstraint.set(
/* 27 */           (float)MathUtil.toDegreePositive(values[0]), 
/* 28 */           (float)MathUtil.toDegreePositive(values[1]), 
/* 29 */           (float)MathUtil.toDegreePositive(values[2]));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 35 */     return MathUtil.toQuaternion(this.constraint.node.xRot, this.constraint.node.yRot, this.constraint.node.zRot);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 40 */     return ObjectAnimationChannel.ChannelType.ROTATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\constraint\ConstraintRotateListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */