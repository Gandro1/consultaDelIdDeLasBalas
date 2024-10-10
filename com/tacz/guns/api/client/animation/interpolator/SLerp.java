/*    */ package com.tacz.guns.api.client.animation.interpolator;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationChannelContent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SLerp
/*    */   implements Interpolator
/*    */ {
/*    */   private AnimationChannelContent content;
/*    */   
/*    */   public void compile(AnimationChannelContent content) {
/* 13 */     this.content = content;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] interpolate(int indexFrom, int indexTo, float alpha) {
/*    */     float s0, s1;
/* 19 */     int offset = ((this.content.values[indexFrom]).length == 8) ? 4 : 0;
/* 20 */     float ax = this.content.values[indexFrom][offset];
/* 21 */     float ay = this.content.values[indexFrom][1 + offset];
/* 22 */     float az = this.content.values[indexFrom][2 + offset];
/* 23 */     float aw = this.content.values[indexFrom][3 + offset];
/* 24 */     float bx = (indexFrom == indexTo) ? this.content.values[indexFrom][offset] : this.content.values[indexTo][0];
/* 25 */     float by = (indexFrom == indexTo) ? this.content.values[indexFrom][1 + offset] : this.content.values[indexTo][1];
/* 26 */     float bz = (indexFrom == indexTo) ? this.content.values[indexFrom][2 + offset] : this.content.values[indexTo][2];
/* 27 */     float bw = (indexFrom == indexTo) ? this.content.values[indexFrom][3 + offset] : this.content.values[indexTo][3];
/*    */     
/* 29 */     float[] result = new float[4];
/*    */     
/* 31 */     float dot = ax * bx + ay * by + az * bz + aw * bw;
/* 32 */     if (dot < 0.0F) {
/* 33 */       bx = -bx;
/* 34 */       by = -by;
/* 35 */       bz = -bz;
/* 36 */       bw = -bw;
/* 37 */       dot = -dot;
/*    */     } 
/* 39 */     float epsilon = 1.0E-6F;
/*    */     
/* 41 */     if (1.0D - dot > epsilon) {
/* 42 */       float omega = (float)Math.acos(dot);
/* 43 */       float invSinOmega = 1.0F / (float)Math.sin(omega);
/* 44 */       s0 = (float)Math.sin((1.0D - alpha) * omega) * invSinOmega;
/* 45 */       s1 = (float)Math.sin((alpha * omega)) * invSinOmega;
/*    */     } else {
/* 47 */       s0 = 1.0F - alpha;
/* 48 */       s1 = alpha;
/*    */     } 
/* 50 */     float rx = s0 * ax + s1 * bx;
/* 51 */     float ry = s0 * ay + s1 * by;
/* 52 */     float rz = s0 * az + s1 * bz;
/* 53 */     float rw = s0 * aw + s1 * bw;
/* 54 */     result[0] = rx;
/* 55 */     result[1] = ry;
/* 56 */     result[2] = rz;
/* 57 */     result[3] = rw;
/* 58 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public SLerp clone() {
/*    */     try {
/* 64 */       SLerp sLerp = (SLerp)super.clone();
/* 65 */       sLerp.content = this.content;
/* 66 */       return sLerp;
/* 67 */     } catch (CloneNotSupportedException e) {
/* 68 */       e.printStackTrace();
/* 69 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\SLerp.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */