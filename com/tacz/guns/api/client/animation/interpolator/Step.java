/*    */ package com.tacz.guns.api.client.animation.interpolator;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationChannelContent;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class Step
/*    */   implements Interpolator
/*    */ {
/*    */   private AnimationChannelContent content;
/*    */   
/*    */   public void compile(AnimationChannelContent content) {
/* 12 */     this.content = content;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] interpolate(int indexFrom, int indexTo, float alpha) {
/*    */     float[] result;
/* 18 */     switch ((this.content.values[indexFrom]).length) { case 8: 
/*    */       case 6: 
/*    */       default:
/* 21 */         break; }  int offset = 0;
/*    */     
/* 23 */     if (alpha < 1.0F || indexFrom == indexTo) {
/* 24 */       result = Arrays.copyOfRange(this.content.values[indexFrom], offset, (this.content.values[indexFrom]).length);
/*    */     } else {
/* 26 */       int length = (this.content.values[indexTo]).length;
/* 27 */       switch (length) { case 8: 
/*    */         case 6: 
/*    */         default:
/* 30 */           break; }  length = length;
/*    */       
/* 32 */       result = Arrays.copyOfRange(this.content.values[indexTo], 0, length);
/*    */     } 
/* 34 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Step clone() {
/*    */     try {
/* 40 */       Step step = (Step)super.clone();
/* 41 */       step.content = this.content;
/* 42 */       return step;
/* 43 */     } catch (CloneNotSupportedException e) {
/* 44 */       e.printStackTrace();
/* 45 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\Step.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */