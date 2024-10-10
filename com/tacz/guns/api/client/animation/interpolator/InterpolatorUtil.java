/*    */ package com.tacz.guns.api.client.animation.interpolator;
/*    */ 
/*    */ public class InterpolatorUtil {
/*    */   public static Interpolator fromInterpolation(InterpolatorType interpolation) {
/*  5 */     switch (interpolation) {
/*    */       case SPLINE:
/*  7 */         return new Spline();
/*    */       
/*    */       case STEP:
/* 10 */         return new Step();
/*    */       
/*    */       case SLERP:
/* 13 */         return new SLerp();
/*    */     } 
/*    */     
/* 16 */     return new Linear();
/*    */   }
/*    */ 
/*    */   
/*    */   public enum InterpolatorType
/*    */   {
/* 22 */     LINEAR,
/* 23 */     SLERP,
/* 24 */     SPLINE,
/* 25 */     STEP;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\InterpolatorUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */