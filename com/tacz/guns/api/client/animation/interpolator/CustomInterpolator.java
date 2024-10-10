/*     */ package com.tacz.guns.api.client.animation.interpolator;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.AnimationChannelContent;
/*     */ import com.tacz.guns.util.math.MathUtil;
/*     */ import java.util.Arrays;
/*     */ import org.joml.Quaternionf;
/*     */ import org.joml.Quaternionfc;
/*     */ 
/*     */ public class CustomInterpolator
/*     */   implements Interpolator
/*     */ {
/*     */   private AnimationChannelContent content;
/*     */   
/*     */   public void compile(AnimationChannelContent content) {
/*  15 */     this.content = content;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] interpolate(int indexFrom, int indexTo, float alpha) {
/*  20 */     AnimationChannelContent.LerpMode fromLerpMode = this.content.lerpModes[indexFrom];
/*  21 */     AnimationChannelContent.LerpMode toLerpMode = this.content.lerpModes[indexTo];
/*  22 */     if (fromLerpMode == AnimationChannelContent.LerpMode.SPHERICAL_LINEAR && toLerpMode == AnimationChannelContent.LerpMode.SPHERICAL_LINEAR)
/*     */     {
/*  24 */       return doSphericalLinear(indexFrom, indexTo, alpha);
/*     */     }
/*  26 */     if (fromLerpMode == AnimationChannelContent.LerpMode.SPHERICAL_SQUAD || toLerpMode == AnimationChannelContent.LerpMode.SPHERICAL_SQUAD)
/*     */     {
/*  28 */       return doSphericalSquad(indexFrom, indexTo, alpha); } 
/*  29 */     if (fromLerpMode == AnimationChannelContent.LerpMode.CATMULLROM || toLerpMode == AnimationChannelContent.LerpMode.CATMULLROM)
/*     */     {
/*  31 */       return doCatmullromLerp(indexFrom, indexTo, alpha);
/*     */     }
/*     */     
/*  34 */     return doOtherLerp(indexFrom, indexTo, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   private float[] getAsQuaternion(int index, boolean needOffset) {
/*  39 */     float[] result = getValue(index, needOffset);
/*  40 */     if (result.length == 3) {
/*  41 */       result = MathUtil.toQuaternion(result[0], result[1], result[2]);
/*     */     }
/*  43 */     return result;
/*     */   }
/*     */   
/*     */   private float[] getAsThreeAxis(int index, boolean needOffset) {
/*  47 */     float[] result = getValue(index, needOffset);
/*  48 */     if (result.length == 4) {
/*  49 */       result = MathUtil.toEulerAngles(result);
/*     */     }
/*  51 */     return result;
/*     */   }
/*     */   
/*     */   private float[] getValue(int index, boolean needOffset) {
/*  55 */     boolean isQuaternion = ((this.content.values[index]).length == 4 || (this.content.values[index]).length == 8);
/*  56 */     int offset = 0;
/*  57 */     if (needOffset) {
/*  58 */       switch ((this.content.values[index]).length) { case 8: 
/*     */         case 6: 
/*     */         default:
/*  61 */           break; }  offset = 0;
/*     */     } 
/*     */     
/*  64 */     return Arrays.copyOfRange(this.content.values[index], offset, offset + (isQuaternion ? 4 : 3));
/*     */   }
/*     */   
/*     */   private float[] doOtherLerp(int indexFrom, int indexTo, float alpha) {
/*  68 */     if (indexFrom == indexTo) {
/*  69 */       return getValue(indexTo, (alpha > 0.0F));
/*     */     }
/*  71 */     float[] valueFrom = getAsThreeAxis(indexFrom, true);
/*  72 */     float[] valueTo = getAsThreeAxis(indexTo, false);
/*  73 */     float[] result = new float[3];
/*  74 */     for (int i = 0; i < 3; i++) {
/*  75 */       result[i] = valueFrom[i] * (1.0F - alpha) + valueTo[i] * alpha;
/*     */     }
/*  77 */     return result;
/*     */   }
/*     */   
/*     */   private float[] doCatmullromLerp(int indexFrom, int indexTo, float alpha) {
/*  81 */     if (this.content.values.length == 1) {
/*  82 */       return getValue(0, (alpha > 0.0F));
/*     */     }
/*  84 */     float[] vx = new float[4];
/*  85 */     float[] vy = new float[4];
/*  86 */     float[] vz = new float[4];
/*  87 */     int prev = (indexFrom == 0) ? 0 : (indexFrom - 1);
/*  88 */     int next = (indexTo == this.content.values.length - 1) ? (this.content.values.length - 1) : (indexTo + 1);
/*  89 */     float[] valuePrev = getAsThreeAxis(prev, true);
/*  90 */     float[] valueFrom = getAsThreeAxis(indexFrom, false);
/*  91 */     float[] valueTo = getAsThreeAxis(indexTo, false);
/*  92 */     float[] valueNext = getAsThreeAxis(next, false);
/*  93 */     vx[0] = valuePrev[0];
/*  94 */     vy[0] = valuePrev[1];
/*  95 */     vz[0] = valuePrev[2];
/*  96 */     vx[1] = valueFrom[0];
/*  97 */     vy[1] = valueFrom[1];
/*  98 */     vz[1] = valueFrom[2];
/*  99 */     vx[2] = valueTo[0];
/* 100 */     vy[2] = valueTo[1];
/* 101 */     vz[2] = valueTo[2];
/* 102 */     vx[3] = valueNext[0];
/* 103 */     vy[3] = valueNext[1];
/* 104 */     vz[3] = valueNext[2];
/*     */ 
/*     */     
/* 107 */     return new float[] {
/* 108 */         MathUtil.splineCurve(vx, 0.5F, alpha), 
/* 109 */         MathUtil.splineCurve(vy, 0.5F, alpha), 
/* 110 */         MathUtil.splineCurve(vz, 0.5F, alpha)
/*     */       };
/*     */   }
/*     */   
/*     */   private float[] doSphericalLinear(int indexFrom, int indexTo, float alpha) {
/* 115 */     if (this.content.values.length == 1) {
/* 116 */       return getAsQuaternion(0, (alpha > 0.0F));
/*     */     }
/*     */     
/* 119 */     float[] q0 = getAsQuaternion(indexFrom, true);
/* 120 */     float[] q1 = getAsQuaternion(indexTo, false);
/* 121 */     return MathUtil.slerp(q0, q1, alpha);
/*     */   }
/*     */   
/*     */   private float[] doSphericalSquad(int indexFrom, int indexTo, float alpha) {
/* 125 */     if (this.content.values.length == 1) {
/* 126 */       return getAsQuaternion(0, (alpha > 0.0F));
/*     */     }
/* 128 */     int prev = (indexFrom == 0) ? 0 : (indexFrom - 1);
/* 129 */     int next = (indexTo == this.content.values.length - 1) ? (this.content.values.length - 1) : (indexTo + 1);
/* 130 */     float[] q0 = getAsQuaternion(prev, true);
/* 131 */     float[] q1 = getAsQuaternion(indexFrom, false);
/* 132 */     float[] q2 = getAsQuaternion(indexTo, false);
/* 133 */     float[] q3 = getAsQuaternion(next, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     return squad(q0, q1, q2, q3, alpha);
/*     */   }
/*     */   
/*     */   public static float[] squad(float[] q0, float[] q1, float[] q2, float[] q3, float t) {
/* 142 */     float[] s1 = intermediate(MathUtil.toQuaternion(q0), MathUtil.toQuaternion(q1), MathUtil.toQuaternion(q2));
/* 143 */     float[] s2 = intermediate(MathUtil.toQuaternion(q1), MathUtil.toQuaternion(q2), MathUtil.toQuaternion(q3));
/*     */     
/* 145 */     float[] slerp1 = MathUtil.slerp(q1, q2, t);
/* 146 */     float[] slerp2 = MathUtil.slerp(s1, s2, t);
/* 147 */     return MathUtil.slerp(slerp1, slerp2, 2.0F * t * (1.0F - t));
/*     */   }
/*     */   
/*     */   private static float[] intermediate(Quaternionf q0, Quaternionf q1, Quaternionf q2) {
/* 151 */     if (q1.dot(q0) < 0.0F) {
/* 152 */       q0 = reverse(q0);
/*     */     }
/* 154 */     if (q1.dot(q2) < 0.0F) {
/* 155 */       q2 = reverse(q2);
/*     */     }
/* 157 */     Quaternionf q0Conj = q0.conjugate(new Quaternionf());
/* 158 */     Quaternionf q1Conj = q1.conjugate(new Quaternionf());
/* 159 */     Quaternionf m0 = q0Conj.mul((Quaternionfc)q1);
/* 160 */     Quaternionf m1 = q1Conj.mul((Quaternionfc)q2);
/* 161 */     Quaternionf m0Log = log(m0);
/* 162 */     Quaternionf m1Log = log(m1);
/* 163 */     Quaternionf mLogSum = m0Log.add((Quaternionfc)m1Log.mul(-1.0F));
/* 164 */     Quaternionf exp = exp(mLogSum.mul(0.25F));
/* 165 */     Quaternionf result = q1.mul((Quaternionfc)exp);
/* 166 */     return new float[] { result.x, result.y, result.z, result.w };
/*     */   }
/*     */   
/*     */   private static Quaternionf log(Quaternionf q) {
/* 170 */     double sin = Math.sqrt((q.x * q.x + q.y * q.y + q.z * q.z));
/* 171 */     double theta = Math.atan2(sin, q.w);
/* 172 */     Quaternionf result = new Quaternionf((Quaternionfc)q);
/* 173 */     if (sin > 5.000000237487257E-4D) {
/* 174 */       result.mul((float)(theta / sin));
/*     */     }
/* 176 */     result.w = 0.0F;
/* 177 */     return result;
/*     */   }
/*     */   
/*     */   private static Quaternionf exp(Quaternionf q) {
/* 181 */     double theta = Math.sqrt((q.x * q.x + q.y * q.y + q.z * q.z));
/* 182 */     double cos = Math.cos(theta);
/* 183 */     Quaternionf result = new Quaternionf((Quaternionfc)q);
/* 184 */     if (cos < 0.9995D) {
/* 185 */       result.mul((float)(Math.sin(theta) / theta));
/*     */     }
/* 187 */     result.w = (float)cos;
/* 188 */     return result;
/*     */   }
/*     */   
/*     */   private static Quaternionf reverse(Quaternionf q) {
/* 192 */     Quaternionf result = new Quaternionf((Quaternionfc)q);
/* 193 */     q.mul(-1.0F);
/* 194 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomInterpolator clone() {
/*     */     try {
/* 200 */       CustomInterpolator interpolator = (CustomInterpolator)super.clone();
/* 201 */       interpolator.content = this.content;
/* 202 */       return interpolator;
/* 203 */     } catch (CloneNotSupportedException e) {
/* 204 */       e.printStackTrace();
/* 205 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\CustomInterpolator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */