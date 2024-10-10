/*    */ package com.tacz.guns.client.model.listener.constraint;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ 
/*    */ public class ConstraintTranslateListener implements AnimationListener {
/*    */   private final ConstraintObject constraint;
/*    */   
/*    */   public ConstraintTranslateListener(ConstraintObject constraint) {
/* 10 */     this.constraint = constraint;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 15 */     if (blend) {
/* 16 */       this.constraint.translationConstraint.set(
/* 17 */           Math.max(this.constraint.translationConstraint.x(), values[0] * 16.0F), 
/* 18 */           Math.max(this.constraint.translationConstraint.y(), values[1] * 16.0F), 
/* 19 */           Math.max(this.constraint.translationConstraint.z(), values[2] * 16.0F));
/*    */     } else {
/*    */       
/* 22 */       this.constraint.translationConstraint.set(values[0] * 16.0F, values[1] * 16.0F, values[2] * 16.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 32 */     float[] recover = new float[3];
/* 33 */     if (this.constraint.bonesItem != null) {
/* 34 */       recover[0] = ((Float)this.constraint.bonesItem.getPivot().get(0)).floatValue() / 16.0F;
/* 35 */       recover[1] = -((Float)this.constraint.bonesItem.getPivot().get(1)).floatValue() / 16.0F;
/* 36 */       recover[2] = ((Float)this.constraint.bonesItem.getPivot().get(2)).floatValue() / 16.0F;
/*    */     } else {
/* 38 */       recover[0] = this.constraint.node.x / 16.0F;
/* 39 */       recover[1] = this.constraint.node.y / 16.0F;
/* 40 */       recover[2] = this.constraint.node.z / 16.0F;
/*    */     } 
/* 42 */     return recover;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 47 */     return ObjectAnimationChannel.ChannelType.TRANSLATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\constraint\ConstraintTranslateListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */