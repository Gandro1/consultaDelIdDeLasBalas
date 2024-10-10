/*    */ package com.tacz.guns.util.math;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class PerlinNoise {
/*  7 */   private final Random random = new Random();
/*    */   private final float rangeDown;
/*    */   private final float rangeUp;
/*    */   private final long periodMs;
/*    */   private float prevNum;
/*    */   private float num;
/* 13 */   private long prevTime = (new Date()).getTime();
/*    */   private boolean reverse = false;
/*    */   
/*    */   public PerlinNoise(float rangeDown, float rangeUp, long periodMs) {
/* 17 */     this.rangeDown = rangeDown;
/* 18 */     this.rangeUp = rangeUp;
/* 19 */     this.periodMs = periodMs;
/* 20 */     this.prevNum = this.random.nextFloat() * (rangeUp - rangeDown) + rangeDown;
/* 21 */     this.num = this.random.nextFloat() * (rangeUp - rangeDown) + rangeDown;
/* 22 */     if (this.reverse && this.prevNum * this.num > 0.0F) {
/* 23 */       this.num = -this.num;
/*    */     }
/*    */   }
/*    */   
/*    */   private static double easeInterpolate(double x) {
/* 28 */     return 3.0D * Math.pow(x, 2.0D) - 2.0D * Math.pow(x, 3.0D);
/*    */   }
/*    */   
/*    */   public void setReverse(boolean reverse) {
/* 32 */     this.reverse = reverse;
/*    */   }
/*    */   
/*    */   public float getValue() {
/* 36 */     long periodTime = (new Date()).getTime() - this.prevTime;
/* 37 */     long repeat = periodTime / this.periodMs;
/* 38 */     long partialTime = periodTime % this.periodMs;
/* 39 */     this.prevTime += repeat * this.periodMs;
/* 40 */     double x = easeInterpolate(partialTime / this.periodMs);
/* 41 */     if (repeat == 1L) {
/* 42 */       this.prevNum = this.num;
/* 43 */       this.num = this.random.nextFloat() * (this.rangeUp - this.rangeDown) + this.rangeDown;
/* 44 */       if (this.reverse && this.prevNum * this.num > 0.0F) {
/* 45 */         this.num = -this.num;
/*    */       }
/* 47 */     } else if (repeat > 1L) {
/* 48 */       this.prevNum = this.random.nextFloat() * (this.rangeUp - this.rangeDown) + this.rangeDown;
/* 49 */       this.num = this.random.nextFloat() * (this.rangeUp - this.rangeDown) + this.rangeDown;
/* 50 */       if (this.reverse && this.prevNum * this.num > 0.0F) {
/* 51 */         this.num = -this.num;
/*    */       }
/*    */     } 
/* 54 */     return (float)(this.prevNum * (1.0D - x) + this.num * x);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\math\PerlinNoise.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */