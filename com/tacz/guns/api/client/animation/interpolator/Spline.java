/*    */ package com.tacz.guns.api.client.animation.interpolator;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationChannelContent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Spline
/*    */   implements Interpolator
/*    */ {
/*    */   public void compile(AnimationChannelContent content) {}
/*    */   
/*    */   public float[] interpolate(int indexFrom, int indexTo, float alpha) {
/* 14 */     return new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Interpolator clone() {
/* 20 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\Spline.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */