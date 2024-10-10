/*    */ package com.tacz.guns.api.client.animation.interpolator;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationChannelContent;
/*    */ 
/*    */ public class Linear
/*    */   implements Interpolator {
/*    */   private AnimationChannelContent content;
/*    */   
/*    */   public void compile(AnimationChannelContent content) {
/* 10 */     this.content = content;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] interpolate(int indexFrom, int indexTo, float alpha) {
/* 16 */     int offset = ((this.content.values[indexFrom]).length == 6) ? 3 : 0;
/* 17 */     float[] result = new float[3];
/* 18 */     for (int i = 0; i < 3; i++) {
/* 19 */       if (indexFrom == indexTo) {
/* 20 */         result[i] = this.content.values[indexFrom][i + offset];
/*    */       } else {
/* 22 */         result[i] = this.content.values[indexFrom][i + offset] * (1.0F - alpha) + this.content.values[indexTo][i] * alpha;
/*    */       } 
/*    */     } 
/* 25 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Linear clone() {
/*    */     try {
/* 31 */       Linear linear = (Linear)super.clone();
/* 32 */       linear.content = this.content;
/* 33 */       return linear;
/* 34 */     } catch (CloneNotSupportedException e) {
/* 35 */       e.printStackTrace();
/* 36 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\Linear.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */