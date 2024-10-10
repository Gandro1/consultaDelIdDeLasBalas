/*     */ package com.tacz.guns.util.math;
/*     */ 
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Quaternionf;
/*     */ import org.joml.Quaternionfc;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class MathUtil {
/*  13 */   public static final float[] QUATERNION_ONE = new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
/*     */   
/*     */   public static double magnificationToFovMultiplier(double magnification, double currentFov) {
/*  16 */     return magnificationToFov(magnification, currentFov) / currentFov;
/*     */   }
/*     */   
/*     */   public static double magnificationToFov(double magnification, double currentFov) {
/*  20 */     double currentTan = Math.tan(Math.toRadians(currentFov / 2.0D));
/*  21 */     double newTan = currentTan / magnification;
/*  22 */     return Math.toDegrees(Math.atan(newTan)) * 2.0D;
/*     */   }
/*     */   
/*     */   public static double fovToMagnification(double currentFov, double originFov) {
/*  26 */     return Math.tan(Math.toRadians(originFov / 2.0D)) / Math.tan(Math.toRadians(currentFov / 2.0D));
/*     */   }
/*     */   
/*     */   public static double zoomSensitivityRatio(double currentFov, double originFov, double coefficient) {
/*  30 */     return Math.atan(Math.tan(Math.toRadians(currentFov / 2.0D)) * coefficient) / 
/*  31 */       Math.atan(Math.tan(Math.toRadians(originFov / 2.0D)) * coefficient);
/*     */   }
/*     */   
/*     */   public static double copySign(double magnitude, double sign) {
/*  35 */     return Math.abs(magnitude) * ((sign < 0.0D) ? -1 : true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] toQuaternion(float pitch, float yaw, float roll) {
/*  46 */     double cy = Math.cos(roll * 0.5D);
/*  47 */     double sy = Math.sin(roll * 0.5D);
/*  48 */     double cp = Math.cos(yaw * 0.5D);
/*  49 */     double sp = Math.sin(yaw * 0.5D);
/*  50 */     double cr = Math.cos(pitch * 0.5D);
/*  51 */     double sr = Math.sin(pitch * 0.5D);
/*  52 */     return new float[] { (float)(cy * cp * sr - sy * sp * cr), (float)(sy * cp * sr + cy * sp * cr), (float)(sy * cp * cr - cy * sp * sr), (float)(cy * cp * cr + sy * sp * sr) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void toQuaternion(float pitch, float yaw, float roll, @Nonnull Quaternionf quaternion) {
/*  68 */     double cy = Math.cos(roll * 0.5D);
/*  69 */     double sy = Math.sin(roll * 0.5D);
/*  70 */     double cp = Math.cos(yaw * 0.5D);
/*  71 */     double sp = Math.sin(yaw * 0.5D);
/*  72 */     double cr = Math.cos(pitch * 0.5D);
/*  73 */     double sr = Math.sin(pitch * 0.5D);
/*     */     
/*  75 */     quaternion.set((float)(cy * cp * sr - sy * sp * cr), (float)(sy * cp * sr + cy * sp * cr), (float)(sy * cp * cr - cy * sp * sr), (float)(cy * cp * cr + sy * sp * sr));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] toEulerAngles(Quaternionf q) {
/*  89 */     float[] angles = new float[3];
/*     */     
/*  91 */     double sinrCosp = (2.0F * (q.w() * q.x() + q.y() * q.z()));
/*  92 */     double cosrCosp = (1.0F - 2.0F * (q.x() * q.x() + q.y() * q.y()));
/*  93 */     angles[0] = (float)Math.atan2(sinrCosp, cosrCosp);
/*     */     
/*  95 */     double sinp = (2.0F * (q.w() * q.y() - q.x() * q.z()));
/*  96 */     if (Math.abs(sinp) >= 1.0D) {
/*     */       
/*  98 */       angles[1] = (float)copySign(1.5707963267948966D, sinp);
/*     */     } else {
/* 100 */       angles[1] = (float)Math.asin(sinp);
/*     */     } 
/*     */     
/* 103 */     double sinyCosp = (2.0F * (q.w() * q.z() + q.y() * q.x()));
/* 104 */     double cosyCosp = (1.0F - 2.0F * (q.y() * q.y() + q.z() * q.z()));
/* 105 */     angles[2] = (float)Math.atan2(sinyCosp, cosyCosp);
/* 106 */     return angles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] toEulerAngles(float[] q) {
/* 115 */     float[] angles = new float[3];
/*     */     
/* 117 */     double sinrCosp = (2.0F * (q[3] * q[0] + q[1] * q[2]));
/* 118 */     double cosrCosp = (1.0F - 2.0F * (q[0] * q[0] + q[1] * q[1]));
/* 119 */     angles[0] = (float)Math.atan2(sinrCosp, cosrCosp);
/*     */     
/* 121 */     double sinp = (2.0F * (q[3] * q[1] - q[2] * q[0]));
/* 122 */     if (Math.abs(sinp) >= 1.0D) {
/* 123 */       angles[1] = (float)copySign(1.5707963267948966D, sinp);
/*     */     } else {
/* 125 */       angles[1] = (float)Math.asin(sinp);
/*     */     } 
/*     */     
/* 128 */     double sinyCosp = (2.0F * (q[3] * q[2] + q[1] * q[0]));
/* 129 */     double cosyCosp = (1.0F - 2.0F * (q[1] * q[1] + q[2] * q[2]));
/* 130 */     angles[2] = (float)Math.atan2(sinyCosp, cosyCosp);
/* 131 */     return angles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double toDegreePositive(double angle) {
/* 140 */     while (angle < 0.0D) {
/* 141 */       angle += 6.283185307179586D;
/*     */     }
/* 143 */     return Math.toDegrees(angle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] inverseQuaternion(float[] quaternion) {
/* 152 */     float[] result = new float[4];
/*     */     
/* 154 */     result[0] = -quaternion[0];
/* 155 */     result[1] = -quaternion[1];
/* 156 */     result[2] = -quaternion[2];
/* 157 */     result[3] = quaternion[3];
/*     */     
/* 159 */     float m2 = quaternion[0] * quaternion[0] + quaternion[1] * quaternion[1] + quaternion[2] * quaternion[2] + quaternion[3] * quaternion[3];
/* 160 */     result[0] = result[0] / m2;
/* 161 */     result[1] = result[1] / m2;
/* 162 */     result[2] = result[2] / m2;
/* 163 */     result[3] = result[3] / m2;
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   public static float[] mulQuaternion(float[] q1, float[] q2) {
/* 168 */     return new float[] {
/* 169 */         Math.fma(q1[3], q2[0], Math.fma(q1[0], q2[3], Math.fma(q1[1], q2[2], -q1[2] * q2[1]))), 
/* 170 */         Math.fma(q1[3], q2[1], Math.fma(-q1[0], q2[2], Math.fma(q1[1], q2[3], q1[2] * q2[0]))), 
/* 171 */         Math.fma(q1[3], q2[2], Math.fma(q1[0], q2[1], Math.fma(-q1[1], q2[0], q1[2] * q2[3]))), 
/* 172 */         Math.fma(q1[3], q2[3], Math.fma(-q1[0], q2[0], Math.fma(-q1[1], q2[1], -q1[2] * q2[2])))
/*     */       };
/*     */   }
/*     */   
/*     */   public static void blendQuaternion(Quaternionf to, Quaternionf from) {
/* 177 */     Quaternionf q1 = new Quaternionf((Quaternionfc)to);
/* 178 */     Quaternionf q2 = new Quaternionf((Quaternionfc)from);
/* 179 */     normalizeQuaternion(q1);
/* 180 */     normalizeQuaternion(q2);
/* 181 */     logQuaternion(q1);
/* 182 */     logQuaternion(q2);
/* 183 */     q1.set(q1.x() + q2.x(), q1.y() + q2.y(), q1.z() + q2.z(), q1.w() + q2.w());
/* 184 */     expQuaternion(q1);
/* 185 */     normalizeQuaternion(q1);
/* 186 */     to.set(q1.x(), q1.y(), q1.z(), q1.w());
/*     */   }
/*     */   
/*     */   public static void normalizeQuaternion(Quaternionf q) {
/* 190 */     float f = q.x() * q.x() + q.y() * q.y() + q.z() * q.z() + q.w() * q.w();
/* 191 */     if (f > 0.0F) {
/* 192 */       float f1 = (float)Mth.m_14193_(f);
/* 193 */       q.set(f1 * q.x(), f1 * q.y(), f1 * q.z(), f1 * q.w());
/*     */     } else {
/* 195 */       q.set(0.0F, 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void logQuaternion(Quaternionf q) {
/* 200 */     double norm = Math.sqrt((q.x() * q.x() + q.y() * q.y() + q.z() * q.z() + q.w() * q.w()));
/* 201 */     double vec = Math.sqrt((q.x() * q.x() + q.y() * q.y() + q.z() * q.z()));
/* 202 */     double i = q.w() / norm;
/* 203 */     if (i > 1.0D) {
/* 204 */       i = 1.0D;
/*     */     }
/* 206 */     if (i < -1.0D) {
/* 207 */       i = -1.0D;
/*     */     }
/* 209 */     double theta = Math.acos(i);
/* 210 */     double factor = (vec == 0.0D) ? 0.0D : (theta / vec);
/* 211 */     q.set(
/* 212 */         (float)(q.x() * factor), 
/* 213 */         (float)(q.y() * factor), 
/* 214 */         (float)(q.z() * factor), 
/* 215 */         (float)Math.log(norm));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void expQuaternion(Quaternionf q) {
/* 220 */     double magnitude = Math.sqrt((q.x() * q.x() + q.y() * q.y() + q.z() * q.z()));
/* 221 */     double expW = Math.exp(q.w());
/* 222 */     double sinMagnitude = Math.sin(magnitude);
/* 223 */     double coef = (magnitude == 0.0D) ? 0.0D : (expW * sinMagnitude / magnitude);
/* 224 */     q.set(
/* 225 */         (float)(coef * q.x()), 
/* 226 */         (float)(coef * q.y()), 
/* 227 */         (float)(coef * q.z()), 
/* 228 */         (float)(expW * Math.cos(magnitude)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float[] slerp(float[] from, float[] to, float alpha) {
/* 233 */     float s0, s1, ax = from[0];
/* 234 */     float ay = from[1];
/* 235 */     float az = from[2];
/* 236 */     float aw = from[3];
/* 237 */     float bx = to[0];
/* 238 */     float by = to[1];
/* 239 */     float bz = to[2];
/* 240 */     float bw = to[3];
/*     */     
/* 242 */     float dot = ax * bx + ay * by + az * bz + aw * bw;
/* 243 */     if (dot < 0.0F) {
/* 244 */       bx = -bx;
/* 245 */       by = -by;
/* 246 */       bz = -bz;
/* 247 */       bw = -bw;
/* 248 */       dot = -dot;
/*     */     } 
/* 250 */     float epsilon = 1.0E-6F;
/*     */     
/* 252 */     if (1.0D - dot > epsilon) {
/* 253 */       float omega = (float)Math.acos(dot);
/* 254 */       float invSinOmega = 1.0F / (float)Math.sin(omega);
/* 255 */       s0 = (float)Math.sin((1.0D - alpha) * omega) * invSinOmega;
/* 256 */       s1 = (float)Math.sin((alpha * omega)) * invSinOmega;
/*     */     } else {
/* 258 */       s0 = 1.0F - alpha;
/* 259 */       s1 = alpha;
/*     */     } 
/* 261 */     float[] result = new float[4];
/* 262 */     result[0] = s0 * ax + s1 * bx;
/* 263 */     result[1] = s0 * ay + s1 * by;
/* 264 */     result[2] = s0 * az + s1 * bz;
/* 265 */     result[3] = s0 * aw + s1 * bw;
/* 266 */     return result;
/*     */   }
/*     */   
/*     */   public static Quaternionf toQuaternion(float[] q) {
/* 270 */     return new Quaternionf(q[0], q[1], q[2], q[3]);
/*     */   }
/*     */   
/*     */   public static Quaternionf slerp(Quaternionf from, Quaternionf to, float alpha) {
/* 274 */     float s0, s1, ax = from.x();
/* 275 */     float ay = from.y();
/* 276 */     float az = from.z();
/* 277 */     float aw = from.w();
/* 278 */     float bx = to.x();
/* 279 */     float by = to.y();
/* 280 */     float bz = to.z();
/* 281 */     float bw = to.w();
/*     */     
/* 283 */     float dot = ax * bx + ay * by + az * bz + aw * bw;
/* 284 */     if (dot < 0.0F) {
/* 285 */       bx = -bx;
/* 286 */       by = -by;
/* 287 */       bz = -bz;
/* 288 */       bw = -bw;
/* 289 */       dot = -dot;
/*     */     } 
/* 291 */     float epsilon = 1.0E-6F;
/*     */     
/* 293 */     if (1.0D - dot > epsilon) {
/* 294 */       float omega = (float)Math.acos(dot);
/* 295 */       float invSinOmega = 1.0F / (float)Math.sin(omega);
/* 296 */       s0 = (float)Math.sin((1.0D - alpha) * omega) * invSinOmega;
/* 297 */       s1 = (float)Math.sin((alpha * omega)) * invSinOmega;
/*     */     } else {
/* 299 */       s0 = 1.0F - alpha;
/* 300 */       s1 = alpha;
/*     */     } 
/* 302 */     float rx = s0 * ax + s1 * bx;
/* 303 */     float ry = s0 * ay + s1 * by;
/* 304 */     float rz = s0 * az + s1 * bz;
/* 305 */     float rw = s0 * aw + s1 * bw;
/* 306 */     return new Quaternionf(rx, ry, rz, rw);
/*     */   }
/*     */   
/*     */   public static Vector3f getEulerAngles(Matrix4f matrix) {
/* 310 */     Vector3f dest = new Vector3f();
/* 311 */     matrix.getEulerAnglesZYX(dest);
/* 312 */     return dest;
/*     */   }
/*     */   
/*     */   public static float[] solveEquations(float[][] coefficients, float[] constants) {
/* 316 */     int n = constants.length;
/*     */     
/* 318 */     for (int pivot = 0; pivot < n - 1; pivot++) {
/* 319 */       for (int row = pivot + 1; row < n; row++) {
/* 320 */         float factor = coefficients[row][pivot] / coefficients[pivot][pivot];
/* 321 */         for (int col = pivot; col < n; col++) {
/* 322 */           coefficients[row][col] = coefficients[row][col] - coefficients[pivot][col] * factor;
/*     */         }
/* 324 */         constants[row] = constants[row] - constants[pivot] * factor;
/*     */       } 
/*     */     } 
/*     */     
/* 328 */     float[] solution = new float[n];
/* 329 */     for (int i = n - 1; i >= 0; i--) {
/* 330 */       float sum = 0.0F;
/* 331 */       for (int j = i + 1; j < n; j++) {
/* 332 */         sum += coefficients[i][j] * solution[j];
/*     */       }
/* 334 */       solution[i] = (constants[i] - sum) / coefficients[i][i];
/*     */     } 
/* 336 */     return solution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] getRelativeQuaternion(float[] qa, float[] qb) {
/* 348 */     float[][] coefficients = { { qa[3], -qa[2], qa[1], qa[0] }, { qa[2], qa[3], -qa[0], qa[1] }, { -qa[1], qa[0], qa[3], qa[2] }, { -qa[0], -qa[1], -qa[2], qa[3] } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     float[] constants = { qb[0], qb[1], qb[2], qb[3] };
/* 355 */     return solveEquations(coefficients, constants);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Quaternionf getRelativeQuaternion(Quaternionf qa, Quaternionf qb) {
/* 371 */     float[][] coefficients = { { qa.w(), -qa.z(), qa.y(), qa.x() }, { qa.z(), qa.w(), -qa.x(), qa.y() }, { -qa.y(), qa.x(), qa.w(), qa.z() }, { -qa.x(), -qa.y(), -qa.z(), qa.w() } };
/*     */     
/* 373 */     float[] constants = { qb.x(), qb.y(), qb.z(), qb.w() };
/* 374 */     float[] result = solveEquations(coefficients, constants);
/* 375 */     return new Quaternionf(result[0], result[1], result[2], result[3]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void applyMatrixLerp(Matrix4f fromMatrix, Matrix4f toMatrix, Matrix4f resultMatrix, float alpha) {
/* 385 */     Vector3f translation = new Vector3f(toMatrix.m30() - fromMatrix.m30(), toMatrix.m31() - fromMatrix.m31(), toMatrix.m32() - fromMatrix.m32());
/* 386 */     translation.mul(alpha);
/*     */     
/* 388 */     Vector3f fromRotation = getEulerAngles(fromMatrix);
/* 389 */     float[] qFrom = toQuaternion(fromRotation.x(), fromRotation.y(), fromRotation.z());
/* 390 */     Vector3f toRotation = getEulerAngles(toMatrix);
/* 391 */     float[] qTo = toQuaternion(toRotation.x(), toRotation.y(), toRotation.z());
/* 392 */     float[] qRelative = getRelativeQuaternion(qFrom, qTo);
/* 393 */     Quaternionf qLerped = toQuaternion(slerp(QUATERNION_ONE, qRelative, alpha));
/*     */     
/* 395 */     resultMatrix.m30(resultMatrix.m30() + translation.x);
/* 396 */     resultMatrix.m31(resultMatrix.m31() + translation.y);
/* 397 */     resultMatrix.m32(resultMatrix.m32() + translation.z);
/* 398 */     resultMatrix.rotate((Quaternionfc)qLerped);
/*     */   }
/*     */   
/*     */   public static Pair<Float, Vector3f> getAngleAndAxis(Quaternionf quaternion) {
/* 402 */     double angle = 2.0D * Math.acos(quaternion.w());
/* 403 */     double sin = Math.sin(angle / 2.0D);
/*     */     
/* 405 */     if (sin == 0.0D) {
/* 406 */       return Pair.of(Float.valueOf(0.0F), new Vector3f(0.0F, 0.0F, 0.0F));
/*     */     }
/* 408 */     Vector3f axis = new Vector3f(quaternion.x(), quaternion.y(), quaternion.z());
/* 409 */     axis.mul((float)(1.0D / sin));
/* 410 */     return Pair.of(Float.valueOf((float)angle), axis);
/*     */   }
/*     */   
/*     */   public static Pair<Float, Vector3f> getAngleAndAxis(float[] quaternion) {
/* 414 */     double angle = 2.0D * Math.acos(quaternion[3]);
/* 415 */     double sin = Math.sin(angle / 2.0D);
/*     */     
/* 417 */     if (sin == 0.0D) {
/* 418 */       return Pair.of(Float.valueOf(0.0F), new Vector3f(0.0F, 0.0F, 0.0F));
/*     */     }
/* 420 */     Vector3f axis = new Vector3f(quaternion[0], quaternion[1], quaternion[2]);
/* 421 */     axis.mul((float)(1.0D / sin));
/* 422 */     return Pair.of(Float.valueOf((float)angle), axis);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Quaternionf multiplyQuaternion(Quaternionf quaternion, float multiplier) {
/* 427 */     Pair<Float, Vector3f> angleAndAxis = getAngleAndAxis(quaternion);
/* 428 */     float newAngle = ((Float)angleAndAxis.getLeft()).floatValue() * multiplier;
/* 429 */     Vector3f axis = (Vector3f)angleAndAxis.getRight();
/* 430 */     double sin = Math.sin((newAngle / 2.0F));
/* 431 */     double cos = Math.cos((newAngle / 2.0F));
/* 432 */     axis.mul((float)sin);
/* 433 */     return new Quaternionf(axis.x(), axis.y(), axis.z(), (float)cos);
/*     */   }
/*     */   
/*     */   public static float[] multiplyQuaternion(float[] quaternion, float multiplier) {
/* 437 */     Pair<Float, Vector3f> angleAndAxis = getAngleAndAxis(quaternion);
/* 438 */     float newAngle = ((Float)angleAndAxis.getLeft()).floatValue() * multiplier;
/* 439 */     Vector3f axis = (Vector3f)angleAndAxis.getRight();
/* 440 */     double sin = Math.sin((newAngle / 2.0F));
/* 441 */     double cos = Math.cos((newAngle / 2.0F));
/* 442 */     axis.mul((float)sin);
/* 443 */     return new float[] { axis.x(), axis.y(), axis.z(), (float)cos };
/*     */   }
/*     */   
/*     */   public static double getTwoVecAngle(Vec3 v1, Vec3 v2) {
/* 447 */     double dotProduct = v1.f_82479_ * v2.f_82479_ + v1.f_82480_ * v2.f_82480_ + v1.f_82481_ * v2.f_82481_;
/* 448 */     double magnitude1 = Math.sqrt(v1.f_82479_ * v1.f_82479_ + v1.f_82480_ * v1.f_82480_ + v1.f_82481_ * v1.f_82481_);
/* 449 */     double magnitude2 = Math.sqrt(v2.f_82479_ * v2.f_82479_ + v2.f_82480_ * v2.f_82480_ + v2.f_82481_ * v2.f_82481_);
/* 450 */     if (magnitude1 * magnitude2 == 0.0D) {
/* 451 */       return -1.0D;
/*     */     }
/* 453 */     double cos = dotProduct / magnitude1 * magnitude2;
/* 454 */     return Math.acos(cos);
/*     */   }
/*     */   
/*     */   public static float splineCurve(float[] y, float tension, float alpha) {
/* 458 */     if (y.length != 4) {
/* 459 */       throw new IllegalArgumentException("y value length must be 4 when doing catmull-rom spline");
/*     */     }
/* 461 */     if (tension < 0.0F || tension > 1.0F) {
/* 462 */       throw new IllegalArgumentException("tension must be 0~1 when doing catmull-rom spline");
/*     */     }
/* 464 */     float v0 = (y[2] - y[0]) * 0.5F;
/* 465 */     float v1 = (y[3] - y[1]) * 0.5F;
/* 466 */     float t2 = alpha * alpha;
/* 467 */     float t3 = alpha * t2;
/* 468 */     float h1 = 2.0F * t3 - 3.0F * t2 + 1.0F;
/* 469 */     float h2 = -2.0F * t3 + 3.0F * t2;
/* 470 */     float h3 = t3 - 2.0F * t2 + alpha;
/* 471 */     float h4 = t3 - t2;
/* 472 */     return h1 * y[1] + h2 * y[2] + h3 * v0 + h4 * v1;
/*     */   }
/*     */   
/*     */   public static float[] quaternionSplineCurve(float[][] quaternions, float tension, float alpha) {
/* 476 */     if (quaternions.length != 4) {
/* 477 */       throw new IllegalArgumentException("y value length must be 4 when doing catmull-rom spline");
/*     */     }
/* 479 */     if (tension < 0.0F || tension > 1.0F) {
/* 480 */       throw new IllegalArgumentException("tension must be 0~1 when doing catmull-rom spline");
/*     */     }
/* 482 */     float[] angles0 = toEulerAngles(quaternions[0]);
/* 483 */     float[] angles1 = toEulerAngles(quaternions[1]);
/* 484 */     float[] angles2 = toEulerAngles(quaternions[2]);
/* 485 */     float[] angles3 = toEulerAngles(quaternions[3]);
/* 486 */     float[] result = new float[3];
/* 487 */     for (int i = 0; i < 3; i++) {
/* 488 */       float[] input = { angles0[i], angles1[i], angles2[i], angles3[i] };
/* 489 */       result[i] = splineCurve(input, tension, alpha);
/*     */     } 
/* 491 */     return toQuaternion(result[0], result[1], result[2]);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\math\MathUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */