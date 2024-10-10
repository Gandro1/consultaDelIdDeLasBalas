/*    */ package com.tacz.guns.client.model.listener.model;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.BedrockGunModel;
/*    */ 
/*    */ public class ModelAdditionalMagazineListener implements AnimationListener {
/*    */   private final AnimationListener listener;
/*    */   private final BedrockGunModel model;
/*    */   
/*    */   public ModelAdditionalMagazineListener(AnimationListener listener, BedrockGunModel model) {
/* 12 */     this.listener = listener;
/* 13 */     this.model = model;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 18 */     this.listener.update(values, blend);
/* 19 */     if (this.model.getAdditionalMagazineNode() != null) {
/* 20 */       (this.model.getAdditionalMagazineNode()).visible = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 26 */     return this.listener.initialValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 31 */     return this.listener.getType();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\model\ModelAdditionalMagazineListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */