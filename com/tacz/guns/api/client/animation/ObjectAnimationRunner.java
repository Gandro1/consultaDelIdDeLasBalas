/*     */ package com.tacz.guns.api.client.animation;
/*     */ 
/*     */ import com.tacz.guns.util.math.MathUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ public class ObjectAnimationRunner
/*     */ {
/*     */   @Nonnull
/*     */   private final ObjectAnimation animation;
/*     */   protected long transitionTimeNs;
/*     */   protected ArrayList<float[]> valueFrom;
/*     */   protected ArrayList<float[]> valueRecover;
/*     */   protected ArrayList<ObjectAnimationChannel> transitionFromChannels;
/*     */   protected ArrayList<ObjectAnimationChannel> transitionToChannels;
/*     */   protected ArrayList<ObjectAnimationChannel> recoverChannels;
/*     */   private boolean running = false;
/*     */   private long lastUpdateNs;
/*     */   private long progressNs;
/*     */   private boolean isTransitioning = false;
/*     */   @Nullable
/*     */   private ObjectAnimationRunner transitionTo;
/*     */   private long transitionProgressNs;
/*     */   
/*     */   public ObjectAnimationRunner(@Nonnull ObjectAnimation animation) {
/*  45 */     this.animation = Objects.<ObjectAnimation>requireNonNull(animation);
/*     */   }
/*     */   @Nonnull
/*     */   public ObjectAnimation getAnimation() {
/*  49 */     return this.animation;
/*     */   }
/*     */   @Nullable
/*     */   public ObjectAnimationRunner getTransitionTo() {
/*  53 */     return this.transitionTo;
/*     */   }
/*     */   
/*     */   public boolean isTransitioning() {
/*  57 */     return this.isTransitioning;
/*     */   }
/*     */   
/*     */   public void run() {
/*  61 */     if (!this.running) {
/*  62 */       this.running = true;
/*  63 */       this.lastUpdateNs = System.nanoTime();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pause() {
/*  68 */     this.running = false;
/*     */   }
/*     */   
/*     */   public void hold() {
/*  72 */     this.progressNs = (long)(this.animation.getMaxEndTimeS() * 1.0E9D) + 1L;
/*  73 */     pause();
/*     */   }
/*     */   
/*     */   public void stop() {
/*  77 */     this.progressNs = (long)(this.animation.getMaxEndTimeS() * 1.0E9D) + 2L;
/*  78 */     pause();
/*     */   }
/*     */   
/*     */   public void reset() {
/*  82 */     this.progressNs = 0L;
/*     */   }
/*     */   
/*     */   public long getProgressNs() {
/*  86 */     return this.progressNs;
/*     */   }
/*     */   
/*     */   public void setProgressNs(long progressNs) {
/*  90 */     this.progressNs = progressNs;
/*     */   }
/*     */   
/*     */   public void transition(ObjectAnimationRunner transitionTo, long transitionTimeNS) {
/*  94 */     if (this.transitionTo == null) {
/*  95 */       this.valueFrom = (ArrayList)new ArrayList<>();
/*  96 */       this.valueRecover = (ArrayList)new ArrayList<>();
/*  97 */       this.transitionFromChannels = new ArrayList<>();
/*  98 */       this.transitionToChannels = new ArrayList<>();
/*  99 */       this.recoverChannels = new ArrayList<>();
/* 100 */       this.transitionTo = transitionTo;
/* 101 */       pause();
/* 102 */       for (Map.Entry<String, List<ObjectAnimationChannel>> entry : this.animation.getChannels().entrySet()) {
/* 103 */         List<ObjectAnimationChannel> toChannels = transitionTo.animation.getChannels().get(entry.getKey());
/* 104 */         if (toChannels != null) {
/*     */           
/* 106 */           for (ObjectAnimationChannel channel : entry.getValue()) {
/*     */             
/* 108 */             Optional<ObjectAnimationChannel> toChannel = toChannels.stream().filter(c -> c.type.equals(channel.type)).findAny();
/* 109 */             float[] value = channel.getResult((float)this.progressNs / 1.0E9F);
/* 110 */             if (channel.type == ObjectAnimationChannel.ChannelType.ROTATION && value.length == 3) {
/* 111 */               value = MathUtil.toQuaternion(value[0], value[1], value[2]);
/*     */             }
/* 113 */             if (toChannel.isPresent()) {
/* 114 */               this.valueFrom.add(value);
/* 115 */               this.transitionFromChannels.add(channel);
/* 116 */               this.transitionToChannels.add(toChannel.get());
/*     */               
/* 118 */               ((ObjectAnimationChannel)toChannel.get()).transitioning = true; continue;
/*     */             } 
/* 120 */             this.valueRecover.add(value);
/* 121 */             this.recoverChannels.add(channel);
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 126 */         for (ObjectAnimationChannel channel : entry.getValue()) {
/* 127 */           float[] value = channel.getResult((float)this.progressNs / 1.0E9F);
/* 128 */           if (channel.type == ObjectAnimationChannel.ChannelType.ROTATION && value.length == 3) {
/* 129 */             value = MathUtil.toQuaternion(value[0], value[1], value[2]);
/*     */           }
/* 131 */           this.valueRecover.add(value);
/* 132 */           this.recoverChannels.add(channel);
/*     */         }
/*     */       
/*     */       } 
/* 136 */     } else if (this.isTransitioning) {
/* 137 */       ArrayList<float[]> newValueFrom = (ArrayList)new ArrayList<>();
/* 138 */       ArrayList<float[]> newValueRecover = (ArrayList)new ArrayList<>();
/* 139 */       ArrayList<ObjectAnimationChannel> newTransitionFromChannels = new ArrayList<>();
/* 140 */       ArrayList<ObjectAnimationChannel> newTransitionToChannels = new ArrayList<>();
/* 141 */       ArrayList<ObjectAnimationChannel> newRecoverChannels = new ArrayList<>();
/*     */       
/* 143 */       for (int i = 0; i < this.transitionFromChannels.size(); i++) {
/* 144 */         float[] result; assert this.transitionTo != null;
/* 145 */         ObjectAnimationChannel fromChannel = this.transitionFromChannels.get(i);
/* 146 */         ObjectAnimationChannel toChannel = this.transitionToChannels.get(i);
/* 147 */         float[] from = this.valueFrom.get(i);
/* 148 */         float[] to = toChannel.getResult((float)this.transitionTo.progressNs / 1.0E9F);
/*     */         
/* 150 */         float progress = easeOutCubic(((float)this.transitionProgressNs / (float)this.transitionTimeNs));
/* 151 */         if (fromChannel.type.equals(ObjectAnimationChannel.ChannelType.TRANSLATION)) {
/* 152 */           result = new float[3];
/* 153 */           lerp(from, to, progress, result);
/* 154 */         } else if (fromChannel.type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 155 */           result = new float[4];
/* 156 */           if (to.length == 3) {
/* 157 */             to = MathUtil.toQuaternion(to[0], to[1], to[2]);
/*     */           }
/* 159 */           slerp(from, to, progress, result);
/*     */         } else {
/* 161 */           result = new float[3];
/* 162 */           lerp(from, to, progress, result);
/*     */         } 
/*     */         
/* 165 */         List<ObjectAnimationChannel> newToChannels = transitionTo.animation.getChannels().get(fromChannel.node);
/* 166 */         if (newToChannels != null) {
/*     */           
/* 168 */           Optional<ObjectAnimationChannel> newToChannel = newToChannels.stream().filter(c -> c.type.equals(fromChannel.type)).findAny();
/* 169 */           if (newToChannel.isPresent()) {
/* 170 */             newValueFrom.add(result);
/* 171 */             newTransitionFromChannels.add(fromChannel);
/* 172 */             newTransitionToChannels.add(newToChannel.get());
/*     */             
/* 174 */             ((ObjectAnimationChannel)newToChannel.get()).transitioning = true;
/*     */           } else {
/* 176 */             newValueRecover.add(result);
/* 177 */             newRecoverChannels.add(fromChannel);
/*     */           } 
/*     */         } else {
/* 180 */           newValueRecover.add(result);
/* 181 */           newRecoverChannels.add(fromChannel);
/*     */         } 
/* 183 */         toChannel.transitioning = false;
/*     */       } 
/* 185 */       this.valueFrom = newValueFrom;
/* 186 */       this.valueRecover = newValueRecover;
/* 187 */       this.transitionToChannels = newTransitionToChannels;
/* 188 */       this.transitionFromChannels = newTransitionFromChannels;
/* 189 */       this.recoverChannels = newRecoverChannels;
/* 190 */       this.transitionTo = transitionTo;
/*     */     } 
/* 192 */     this.transitionTimeNs = transitionTimeNS;
/* 193 */     this.transitionProgressNs = 0L;
/* 194 */     this.isTransitioning = true;
/*     */   }
/*     */   
/*     */   public long getTransitionTimeNs() {
/* 198 */     return this.transitionTimeNs;
/*     */   }
/*     */   
/*     */   public long getTransitionProgressNs() {
/* 202 */     return this.transitionProgressNs;
/*     */   }
/*     */   
/*     */   public void setTransitionProgressNs(long progressNs) {
/* 206 */     this.transitionProgressNs = progressNs;
/*     */   }
/*     */   
/*     */   public void stopTransition() {
/* 210 */     this.isTransitioning = false;
/* 211 */     for (ObjectAnimationChannel channel : this.transitionToChannels) {
/* 212 */       channel.transitioning = false;
/*     */     }
/* 214 */     this.transitionTimeNs = 0L;
/* 215 */     this.transitionProgressNs = 0L;
/* 216 */     this.transitionFromChannels = null;
/* 217 */     this.transitionToChannels = null;
/* 218 */     this.recoverChannels = null;
/* 219 */     this.valueFrom = null;
/* 220 */     this.valueRecover = null;
/*     */   }
/*     */   
/*     */   public void update(boolean blend) {
/* 224 */     long currentNs = System.nanoTime();
/* 225 */     long fromTimeNs = this.progressNs;
/*     */     
/* 227 */     if (this.running) {
/* 228 */       this.progressNs += currentNs - this.lastUpdateNs;
/*     */     }
/* 230 */     switch (this.animation.playType) {
/*     */       case PLAY_ONCE_HOLD:
/* 232 */         if (this.progressNs / 1.0E9D > this.animation.getMaxEndTimeS()) {
/* 233 */           hold();
/*     */         }
/*     */         break;
/*     */       case PLAY_ONCE_STOP:
/* 237 */         if (this.progressNs / 1.0E9D > this.animation.getMaxEndTimeS()) {
/* 238 */           stop();
/*     */         }
/*     */         break;
/*     */       case LOOP:
/* 242 */         if (this.progressNs / 1.0E9D > this.animation.getMaxEndTimeS()) {
/* 243 */           if (this.animation.getMaxEndTimeS() == 0.0F) {
/* 244 */             this.progressNs = 0L; break;
/*     */           } 
/* 246 */           this.progressNs %= (long)(this.animation.getMaxEndTimeS() * 1.0E9D);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 252 */     if (this.isTransitioning) {
/* 253 */       this.transitionProgressNs += currentNs - this.lastUpdateNs;
/* 254 */       if (this.transitionProgressNs >= this.transitionTimeNs) {
/* 255 */         stopTransition();
/*     */       } else {
/* 257 */         float transitionProgress = (float)this.transitionProgressNs / (float)this.transitionTimeNs;
/* 258 */         updateTransition(easeOutCubic(transitionProgress), blend);
/*     */       } 
/*     */     } else {
/* 261 */       this.animation.update(blend, (float)fromTimeNs, (float)this.progressNs);
/*     */     } 
/*     */     
/* 264 */     this.lastUpdateNs = currentNs;
/*     */   }
/*     */   
/*     */   public boolean isRunning() {
/* 268 */     return this.running;
/*     */   }
/*     */   
/*     */   public boolean isHolding() {
/* 272 */     return (this.progressNs == (long)(getAnimation().getMaxEndTimeS() * 1.0E9D) + 1L);
/*     */   }
/*     */   
/*     */   public boolean isStopped() {
/* 276 */     return (this.progressNs == (long)(getAnimation().getMaxEndTimeS() * 1.0E9D) + 2L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateTransition(float progress, boolean blend) {
/* 284 */     assert this.transitionTo != null; int i;
/* 285 */     for (i = 0; i < this.transitionToChannels.size(); i++) {
/* 286 */       float[] result; ObjectAnimationChannel fromChannel = this.transitionFromChannels.get(i);
/* 287 */       ObjectAnimationChannel toChannel = this.transitionToChannels.get(i);
/*     */       
/* 289 */       float[] from = this.valueFrom.get(i);
/* 290 */       float[] to = toChannel.getResult((float)this.transitionTo.progressNs / 1.0E9F);
/*     */ 
/*     */       
/* 293 */       if (fromChannel.type.equals(ObjectAnimationChannel.ChannelType.TRANSLATION)) {
/* 294 */         result = new float[3];
/* 295 */         lerp(from, to, progress, result);
/* 296 */       } else if (fromChannel.type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 297 */         result = new float[4];
/* 298 */         if (to.length == 3) {
/* 299 */           to = MathUtil.toQuaternion(to[0], to[1], to[2]);
/*     */         }
/* 301 */         slerp(from, to, progress, result);
/*     */       } else {
/* 303 */         result = new float[3];
/* 304 */         lerp(from, to, progress, result);
/*     */       } 
/* 306 */       for (AnimationListener listener : fromChannel.getListeners()) {
/* 307 */         listener.update(result, blend);
/*     */       }
/*     */     } 
/*     */     
/* 311 */     if (this.animation.playType != ObjectAnimation.PlayType.PLAY_ONCE_STOP) {
/* 312 */       for (i = 0; i < this.recoverChannels.size(); i++) {
/* 313 */         ObjectAnimationChannel channel = this.recoverChannels.get(i);
/* 314 */         float[] from = this.valueRecover.get(i);
/*     */         
/* 316 */         if (channel.type.equals(ObjectAnimationChannel.ChannelType.TRANSLATION)) {
/* 317 */           float[] result = new float[3];
/* 318 */           float[] to = { 0.0F, 0.0F, 0.0F };
/* 319 */           for (AnimationListener listener : channel.getListeners()) {
/* 320 */             lerp(from, to, progress, result);
/* 321 */             listener.update(result, blend);
/*     */           } 
/* 323 */         } else if (channel.type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 324 */           float[] result = new float[4];
/* 325 */           float[] to = { 0.0F, 0.0F, 0.0F, 1.0F };
/* 326 */           for (AnimationListener listener : channel.getListeners()) {
/* 327 */             slerp(from, to, progress, result);
/* 328 */             listener.update(result, blend);
/*     */           } 
/* 330 */         } else if (channel.type.equals(ObjectAnimationChannel.ChannelType.SCALE)) {
/* 331 */           float[] result = new float[3];
/* 332 */           float[] to = { 1.0F, 1.0F, 1.0F };
/* 333 */           for (AnimationListener listener : channel.getListeners()) {
/* 334 */             lerp(from, to, progress, result);
/* 335 */             listener.update(result, blend);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private float easeOutCubic(double x) {
/* 343 */     return (float)(1.0D - Math.pow(1.0D - x, 4.0D));
/*     */   }
/*     */   
/*     */   private void lerp(float[] from, float[] to, float alpha, float[] result) {
/* 347 */     for (int i = 0; i < result.length; i++) {
/* 348 */       result[i] = from[i] * (1.0F - alpha) + to[i] * alpha;
/*     */     }
/*     */   }
/*     */   
/*     */   private void slerp(float[] from, float[] to, float alpha, float[] result) {
/* 353 */     float s0, s1, ax = from[0];
/* 354 */     float ay = from[1];
/* 355 */     float az = from[2];
/* 356 */     float aw = from[3];
/* 357 */     float bx = to[0];
/* 358 */     float by = to[1];
/* 359 */     float bz = to[2];
/* 360 */     float bw = to[3];
/*     */     
/* 362 */     float dot = ax * bx + ay * by + az * bz + aw * bw;
/* 363 */     if (dot < 0.0F) {
/* 364 */       bx = -bx;
/* 365 */       by = -by;
/* 366 */       bz = -bz;
/* 367 */       bw = -bw;
/* 368 */       dot = -dot;
/*     */     } 
/* 370 */     float epsilon = 1.0E-6F;
/*     */     
/* 372 */     if (1.0D - dot > epsilon) {
/* 373 */       float omega = (float)Math.acos(dot);
/* 374 */       float invSinOmega = 1.0F / (float)Math.sin(omega);
/* 375 */       s0 = (float)Math.sin((1.0D - alpha) * omega) * invSinOmega;
/* 376 */       s1 = (float)Math.sin((alpha * omega)) * invSinOmega;
/*     */     } else {
/* 378 */       s0 = 1.0F - alpha;
/* 379 */       s1 = alpha;
/*     */     } 
/* 381 */     float rx = s0 * ax + s1 * bx;
/* 382 */     float ry = s0 * ay + s1 * by;
/* 383 */     float rz = s0 * az + s1 * bz;
/* 384 */     float rw = s0 * aw + s1 * bw;
/* 385 */     result[0] = rx;
/* 386 */     result[1] = ry;
/* 387 */     result[2] = rz;
/* 388 */     result[3] = rw;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\ObjectAnimationRunner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */