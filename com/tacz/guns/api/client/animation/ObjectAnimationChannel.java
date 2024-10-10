/*     */ package com.tacz.guns.api.client.animation;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.interpolator.Interpolator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ObjectAnimationChannel
/*     */ {
/*     */   public final ChannelType type;
/*  11 */   private final List<AnimationListener> listeners = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public String node;
/*     */ 
/*     */   
/*     */   public AnimationChannelContent content;
/*     */ 
/*     */   
/*     */   public Interpolator interpolator;
/*     */ 
/*     */   
/*     */   boolean transitioning = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectAnimationChannel(ChannelType type) {
/*  28 */     this.type = type;
/*  29 */     this.content = new AnimationChannelContent();
/*     */   }
/*     */   
/*     */   public ObjectAnimationChannel(ChannelType type, AnimationChannelContent content) {
/*  33 */     this.type = type;
/*  34 */     this.content = content;
/*     */   }
/*     */   
/*     */   public void addListener(AnimationListener listener) {
/*  38 */     if (listener.getType().equals(this.type)) {
/*  39 */       this.listeners.add(listener);
/*     */     } else {
/*  41 */       throw new RuntimeException("trying to add wrong type of listener to channel.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeListener(AnimationListener listener) {
/*  46 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public void clearListeners() {
/*  50 */     this.listeners.clear();
/*     */   }
/*     */   
/*     */   public List<AnimationListener> getListeners() {
/*  54 */     return this.listeners;
/*     */   }
/*     */   
/*     */   public float getEndTimeS() {
/*  58 */     return this.content.keyframeTimeS[this.content.keyframeTimeS.length - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(float timeS, boolean blend) {
/*  67 */     if (!this.transitioning) {
/*  68 */       float[] result = getResult(timeS);
/*  69 */       for (AnimationListener listener : this.listeners) {
/*  70 */         listener.update(result, blend);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public float[] getResult(float timeS) {
/*  76 */     int indexFrom = computeIndex(timeS);
/*  77 */     int indexTo = Math.min(this.content.keyframeTimeS.length - 1, indexFrom + 1);
/*  78 */     float alpha = computeAlpha(timeS, indexFrom);
/*  79 */     return this.interpolator.interpolate(indexFrom, indexTo, alpha);
/*     */   }
/*     */   
/*     */   private int computeIndex(float timeS) {
/*  83 */     int index = Arrays.binarySearch(this.content.keyframeTimeS, timeS);
/*  84 */     if (index >= 0) {
/*  85 */       return index;
/*     */     }
/*  87 */     return Math.max(0, -index - 2);
/*     */   }
/*     */   
/*     */   private float computeAlpha(float timeS, int indexFrom) {
/*  91 */     if (timeS <= this.content.keyframeTimeS[0]) {
/*  92 */       return 0.0F;
/*     */     }
/*  94 */     if (timeS >= this.content.keyframeTimeS[this.content.keyframeTimeS.length - 1]) {
/*  95 */       return 1.0F;
/*     */     }
/*  97 */     float local = timeS - this.content.keyframeTimeS[indexFrom];
/*  98 */     float delta = this.content.keyframeTimeS[indexFrom + 1] - this.content.keyframeTimeS[indexFrom];
/*  99 */     return local / delta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ChannelType
/*     */   {
/* 106 */     TRANSLATION,
/*     */ 
/*     */ 
/*     */     
/* 110 */     ROTATION,
/*     */ 
/*     */ 
/*     */     
/* 114 */     SCALE;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\ObjectAnimationChannel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */