/*     */ package com.tacz.guns.api.client.animation;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class AnimationController
/*     */ {
/*  13 */   protected final ArrayList<ObjectAnimationRunner> currentRunners = new ArrayList<>();
/*  14 */   protected final ArrayList<Boolean> blending = new ArrayList<>();
/*     */   private final AnimationListenerSupplier listenerSupplier;
/*  16 */   private final ArrayList<Queue<AnimationPlan>> animationQueue = new ArrayList<>();
/*  17 */   protected Map<String, ObjectAnimation> prototypes = Maps.newHashMap(); @Nullable
/*  18 */   protected Iterable<Integer> updatingTrackArray = null;
/*     */   
/*     */   public AnimationController(List<ObjectAnimation> animationPrototypes, AnimationListenerSupplier model) {
/*  21 */     for (ObjectAnimation prototype : animationPrototypes) {
/*  22 */       if (prototype == null) {
/*     */         continue;
/*     */       }
/*  25 */       this.prototypes.put(prototype.name, prototype);
/*     */     } 
/*  27 */     this.listenerSupplier = model;
/*     */   }
/*     */   
/*     */   public void providePrototypeIfAbsent(String name, Supplier<ObjectAnimation> supplier) {
/*  31 */     if (!this.prototypes.containsKey(name)) {
/*  32 */       this.prototypes.put(name, supplier.get());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containPrototype(String name) {
/*  37 */     return this.prototypes.containsKey(name);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ObjectAnimationRunner getAnimation(int track) {
/*  42 */     if (track >= this.currentRunners.size()) {
/*  43 */       return null;
/*     */     }
/*  45 */     return this.currentRunners.get(track);
/*     */   }
/*     */   
/*     */   public void removeAnimation(int track) {
/*  49 */     if (track < this.currentRunners.size()) {
/*  50 */       this.currentRunners.set(track, null);
/*     */     }
/*  52 */     if (track < this.animationQueue.size()) {
/*  53 */       this.animationQueue.set(track, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void queueAnimation(int track, Queue<AnimationPlan> queue) {
/*  59 */     for (int i = this.animationQueue.size(); i <= track; i++) {
/*  60 */       this.animationQueue.add(null);
/*     */     }
/*  62 */     this.animationQueue.set(track, queue);
/*  63 */     if (queue != null) {
/*  64 */       AnimationPlan plan = null;
/*  65 */       while (plan == null && !queue.isEmpty()) {
/*  66 */         plan = queue.poll();
/*     */       }
/*  68 */       if (plan != null) {
/*  69 */         run(track, plan.animationName, plan.playType, plan.transitionTimeS);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void runAnimation(int track, String animationName, ObjectAnimation.PlayType playType, float transitionTimeS) {
/*  76 */     if (track < this.animationQueue.size()) {
/*  77 */       this.animationQueue.set(track, null);
/*     */     }
/*  79 */     run(track, animationName, playType, transitionTimeS);
/*     */   }
/*     */   
/*     */   private synchronized void run(int track, String animationName, ObjectAnimation.PlayType playType, float transitionTimeS) {
/*  83 */     ObjectAnimation prototype = this.prototypes.get(animationName);
/*  84 */     if (prototype == null) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     for (int i = this.currentRunners.size(); i <= track; i++) {
/*  89 */       this.currentRunners.add(null);
/*     */     }
/*     */     
/*  92 */     ObjectAnimation animation = new ObjectAnimation(prototype);
/*  93 */     animation.applyAnimationListeners(this.listenerSupplier);
/*  94 */     animation.playType = playType;
/*  95 */     ObjectAnimationRunner runner = new ObjectAnimationRunner(animation);
/*  96 */     runner.setProgressNs(0L);
/*  97 */     runner.run();
/*     */     
/*  99 */     ObjectAnimationRunner oldRunner = this.currentRunners.get(track);
/* 100 */     if (transitionTimeS > 0.0F) {
/* 101 */       if (oldRunner != null) {
/* 102 */         oldRunner.transition(runner, (long)(transitionTimeS * 1.0E9D));
/*     */       } else {
/* 104 */         this.currentRunners.set(track, runner);
/*     */       } 
/*     */     } else {
/* 107 */       this.currentRunners.set(track, runner);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlending(int track, boolean blend) {
/* 113 */     for (int i = this.blending.size(); i <= track; i++) {
/* 114 */       this.blending.add(Boolean.valueOf(false));
/*     */     }
/* 116 */     this.blending.set(track, Boolean.valueOf(blend));
/*     */   }
/*     */   @Nullable
/*     */   public Iterable<Integer> getUpdatingTrackArray() {
/* 120 */     return this.updatingTrackArray;
/*     */   }
/*     */   
/*     */   public void setUpdatingTrackArray(@Nullable Iterable<Integer> updatingTrackArray) {
/* 124 */     this.updatingTrackArray = updatingTrackArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void update() {
/* 129 */     if (this.updatingTrackArray != null) {
/* 130 */       this.updatingTrackArray.forEach(this::updateByTrack);
/*     */     } else {
/* 132 */       for (int i = 0; i < this.currentRunners.size(); i++) {
/* 133 */         updateByTrack(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateByTrack(int track) {
/* 139 */     if (track >= this.currentRunners.size()) {
/*     */       return;
/*     */     }
/* 142 */     boolean blend = (track < this.blending.size()) ? ((Boolean)this.blending.get(track)).booleanValue() : false;
/* 143 */     ObjectAnimationRunner runner = this.currentRunners.get(track);
/* 144 */     if (runner == null) {
/*     */       return;
/*     */     }
/*     */     
/* 148 */     if (runner.isRunning() || runner.isHolding() || runner.isTransitioning()) {
/* 149 */       runner.update(blend);
/*     */     }
/*     */     
/* 152 */     if (runner.getTransitionTo() != null) {
/* 153 */       runner.getTransitionTo().update(blend);
/* 154 */       if (!runner.isTransitioning()) {
/* 155 */         this.currentRunners.set(track, runner.getTransitionTo());
/* 156 */         runner = runner.getTransitionTo();
/*     */       } 
/*     */     } 
/*     */     
/* 160 */     if ((runner.isHolding() || runner.isStopped()) && !runner.isTransitioning() && 
/* 161 */       track < this.animationQueue.size()) {
/* 162 */       Queue<AnimationPlan> queue = this.animationQueue.get(track);
/* 163 */       if (queue != null) {
/* 164 */         AnimationPlan plan = null;
/* 165 */         while (plan == null && !queue.isEmpty()) {
/* 166 */           plan = queue.poll();
/*     */         }
/* 168 */         if (plan != null)
/* 169 */           run(track, plan.animationName, plan.playType, plan.transitionTimeS); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\AnimationController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */