/*    */ package com.tacz.guns.client.model.listener.model;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationListener;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*    */ import com.tacz.guns.client.model.BedrockAnimatedModel;
/*    */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*    */ import com.tacz.guns.client.resource.pojo.model.BonesItem;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ModelTranslateListener implements AnimationListener {
/*    */   private final ModelRendererWrapper rendererWrapper;
/*    */   @Nullable
/*    */   private final BonesItem bonesItem;
/*    */   
/*    */   public ModelTranslateListener(BedrockAnimatedModel model, ModelRendererWrapper rendererWrapper, String nodeName) {
/* 16 */     this.rendererWrapper = rendererWrapper;
/*    */     
/* 18 */     if (model.getShouldRender().contains(rendererWrapper.getModelRenderer())) {
/* 19 */       this.bonesItem = (BonesItem)model.getIndexBones().get(nodeName);
/*    */     } else {
/* 21 */       this.bonesItem = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(float[] values, boolean blend) {
/* 27 */     if (blend) {
/*    */       
/* 29 */       this.rendererWrapper.addOffsetX(values[0]);
/* 30 */       this.rendererWrapper.addOffsetY(-values[1]);
/* 31 */       this.rendererWrapper.addOffsetZ(values[2]);
/*    */     } else {
/* 33 */       this.rendererWrapper.setOffsetX(values[0]);
/* 34 */       this.rendererWrapper.setOffsetY(-values[1]);
/* 35 */       this.rendererWrapper.setOffsetZ(values[2]);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] initialValue() {
/* 42 */     float[] recover = new float[3];
/* 43 */     if (this.bonesItem != null) {
/* 44 */       recover[0] = ((Float)this.bonesItem.getPivot().get(0)).floatValue() / 16.0F;
/* 45 */       recover[1] = -((Float)this.bonesItem.getPivot().get(1)).floatValue() / 16.0F;
/* 46 */       recover[2] = ((Float)this.bonesItem.getPivot().get(2)).floatValue() / 16.0F;
/*    */     } else {
/* 48 */       recover[0] = this.rendererWrapper.getRotationPointX() / 16.0F;
/* 49 */       recover[1] = this.rendererWrapper.getRotationPointY() / 16.0F;
/* 50 */       recover[2] = this.rendererWrapper.getRotationPointZ() / 16.0F;
/*    */     } 
/* 52 */     return recover;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectAnimationChannel.ChannelType getType() {
/* 57 */     return ObjectAnimationChannel.ChannelType.TRANSLATION;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\listener\model\ModelTranslateListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */