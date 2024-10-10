/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*    */ 
/*    */ public class GunRecoil
/*    */ {
/* 10 */   private static final SplineInterpolator INTERPOLATOR = new SplineInterpolator();
/*    */   
/*    */   @SerializedName("pitch")
/*    */   @Nullable
/*    */   private GunRecoilKeyFrame[] pitch;
/*    */   
/*    */   @SerializedName("yaw")
/*    */   @Nullable
/*    */   private GunRecoilKeyFrame[] yaw;
/*    */   
/*    */   public GunRecoilKeyFrame[] getPitch() {
/* 21 */     return this.pitch;
/*    */   }
/*    */   
/*    */   public void setPitch(@Nullable GunRecoilKeyFrame[] pitch) {
/* 25 */     this.pitch = pitch;
/*    */   }
/*    */   
/*    */   public GunRecoilKeyFrame[] getYaw() {
/* 29 */     return this.yaw;
/*    */   }
/*    */   
/*    */   public void setYaw(@Nullable GunRecoilKeyFrame[] yaw) {
/* 33 */     this.yaw = yaw;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PolynomialSplineFunction genPitchSplineFunction(float modifier) {
/* 44 */     return getSplineFunction(this.pitch, modifier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PolynomialSplineFunction genYawSplineFunction(float modifier) {
/* 55 */     return getSplineFunction(this.yaw, modifier);
/*    */   }
/*    */   
/*    */   private PolynomialSplineFunction getSplineFunction(GunRecoilKeyFrame[] keyFrames, float modifier) {
/* 59 */     if (keyFrames == null || keyFrames.length == 0) {
/* 60 */       return null;
/*    */     }
/* 62 */     double[] values = new double[keyFrames.length + 1];
/* 63 */     double[] times = new double[keyFrames.length + 1];
/* 64 */     times[0] = 0.0D;
/* 65 */     values[0] = 0.0D; int i;
/* 66 */     for (i = 0; i < keyFrames.length; i++) {
/* 67 */       times[i + 1] = (keyFrames[i].getTime() * 1000.0F + 30.0F);
/*    */     }
/* 69 */     for (i = 0; i < keyFrames.length; i++) {
/* 70 */       float[] value = keyFrames[i].getValue();
/* 71 */       values[i + 1] = (value[0] + Math.random() * (value[1] - value[0])) * modifier;
/*    */     } 
/* 73 */     return INTERPOLATOR.interpolate(times, values);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunRecoil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */