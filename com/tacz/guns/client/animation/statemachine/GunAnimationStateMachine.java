/*     */ package com.tacz.guns.client.animation.statemachine;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.AnimationController;
/*     */ import com.tacz.guns.api.client.animation.AnimationPlan;
/*     */ import com.tacz.guns.api.client.animation.ObjectAnimation;
/*     */ import com.tacz.guns.api.client.animation.ObjectAnimationRunner;
/*     */ import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.ints.IntSet;
/*     */ import java.util.ArrayDeque;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.player.Input;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class GunAnimationStateMachine
/*     */ {
/*  26 */   protected static final IntSet BLENDING_TRACKS = (IntSet)new IntLinkedOpenHashSet();
/*  27 */   protected static int TRACK_INDEX_TOP = 0;
/*     */   
/*  29 */   public static final int HOLDING_POSE_STATIC_TRACK = staticTrack();
/*  30 */   public static final int BOLT_CATCH_STATIC_TRACK = staticTrack();
/*  31 */   public static final int MAIN_TRACK = staticTrack();
/*  32 */   public static final int MOVEMENT_TRACK = blendingTrack();
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final int[] SHOOTING_TRACKS = new int[] { 
/*  37 */       blendingTrack(), blendingTrack(), blendingTrack(), blendingTrack(), 
/*  38 */       blendingTrack(), blendingTrack(), blendingTrack(), blendingTrack(), 
/*  39 */       blendingTrack(), blendingTrack(), blendingTrack(), blendingTrack() };
/*     */   
/*     */   protected AnimationController controller;
/*     */   
/*     */   protected boolean noAmmo = false;
/*     */   
/*     */   protected boolean magExtended = false;
/*     */   
/*     */   protected boolean onGround = true;
/*     */   
/*     */   protected boolean pauseWalkAndRun = false;
/*     */   protected boolean isAiming = false;
/*  51 */   protected float baseDistanceWalked = 0.0F;
/*  52 */   protected float keepDistanceWalked = 0.0F;
/*  53 */   protected WalkDirection lastWalkDirection = WalkDirection.NONE;
/*     */   protected boolean isWalkAiming = false;
/*     */   
/*     */   public GunAnimationStateMachine(AnimationController controller) {
/*  57 */     this.controller = controller;
/*  58 */     for (int i = 0; i < TRACK_INDEX_TOP; i++) {
/*  59 */       controller.setBlending(i, BLENDING_TRACKS.contains(i));
/*     */     }
/*     */   }
/*     */   
/*     */   protected static int staticTrack() {
/*  64 */     return TRACK_INDEX_TOP++;
/*     */   }
/*     */   
/*     */   protected static int blendingTrack() {
/*  68 */     int track = TRACK_INDEX_TOP++;
/*  69 */     BLENDING_TRACKS.add(track);
/*  70 */     return track;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGunShoot() {
/*  75 */     if (isPlayingInspectAnimation()) {
/*  76 */       this.controller.removeAnimation(MAIN_TRACK);
/*     */     }
/*  78 */     for (int track : SHOOTING_TRACKS) {
/*  79 */       if (tryRunShootAnimation(track)) {
/*     */         return;
/*     */       }
/*     */     } 
/*  83 */     this.controller.runAnimation(SHOOTING_TRACKS[0], "shoot", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.0F);
/*     */   }
/*     */   
/*     */   public void onGunReload() {
/*  87 */     if (this.noAmmo) {
/*  88 */       if (this.magExtended && this.controller.containPrototype("reload_empty_extended")) {
/*  89 */         this.controller.runAnimation(MAIN_TRACK, "reload_empty_extended", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */       } else {
/*  91 */         this.controller.runAnimation(MAIN_TRACK, "reload_empty", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */       }
/*     */     
/*  94 */     } else if (this.magExtended && this.controller.containPrototype("reload_tactical_extended")) {
/*  95 */       this.controller.runAnimation(MAIN_TRACK, "reload_tactical_extended", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */     } else {
/*  97 */       this.controller.runAnimation(MAIN_TRACK, "reload_tactical", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGunBolt() {
/* 103 */     this.controller.runAnimation(MAIN_TRACK, "bolt", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */   }
/*     */   
/*     */   public void onBayonetAttack(int count) {
/* 107 */     String animationName = "melee_bayonet_" + Mth.m_14045_(count + 1, 1, 3);
/* 108 */     this.controller.runAnimation(MAIN_TRACK, animationName, ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */   }
/*     */   
/*     */   public void onStockAttack() {
/* 112 */     this.controller.runAnimation(MAIN_TRACK, "melee_stock", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */   }
/*     */   
/*     */   public void onPushAttack() {
/* 116 */     this.controller.runAnimation(MAIN_TRACK, "melee_push", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */   }
/*     */   
/*     */   public void onGunDraw() {
/* 120 */     this.controller.runAnimation(MOVEMENT_TRACK, "idle", ObjectAnimation.PlayType.LOOP, 0.0F);
/* 121 */     this.lastWalkDirection = WalkDirection.NONE;
/* 122 */     this.controller.runAnimation(MAIN_TRACK, "draw", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.0F);
/*     */   }
/*     */   
/*     */   public void onShooterRun(float walkDist) {
/* 126 */     if (isPlayingRunIntroOrLoop()) {
/* 127 */       if (!this.onGround && !isPlayingRunHold()) {
/* 128 */         this.controller.runAnimation(MOVEMENT_TRACK, "run_hold", ObjectAnimation.PlayType.LOOP, 0.6F);
/* 129 */         this.isWalkAiming = false;
/* 130 */         this.lastWalkDirection = WalkDirection.NONE;
/*     */       } 
/*     */       return;
/*     */     } 
/* 134 */     if (this.onGround) {
/* 135 */       ArrayDeque<AnimationPlan> deque = new ArrayDeque<>();
/* 136 */       if (!isPlayingRunHold()) {
/* 137 */         deque.add(new AnimationPlan("run_start", ObjectAnimation.PlayType.PLAY_ONCE_HOLD, 0.2F));
/*     */       }
/* 139 */       deque.add(new AnimationPlan("run", ObjectAnimation.PlayType.LOOP, 0.2F));
/* 140 */       this.controller.queueAnimation(MOVEMENT_TRACK, deque);
/* 141 */       this.isWalkAiming = false;
/* 142 */       this.lastWalkDirection = WalkDirection.NONE;
/* 143 */       this.baseDistanceWalked = walkDist;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onGunPutAway(float putAwayTimeS) {
/* 148 */     this.controller.runAnimation(MAIN_TRACK, "put_away", ObjectAnimation.PlayType.PLAY_ONCE_HOLD, putAwayTimeS * 0.75F);
/*     */     
/* 150 */     ObjectAnimationRunner runner = this.controller.getAnimation(MAIN_TRACK);
/* 151 */     if (runner != null) {
/* 152 */       if (runner.isRunning() && "put_away".equals((runner.getAnimation()).name)) {
/* 153 */         long progress = (long)(Math.max(runner.getAnimation().getMaxEndTimeS() - putAwayTimeS, 0.0F) * 1.0E9D);
/* 154 */         runner.setProgressNs(progress);
/*     */         return;
/*     */       } 
/* 157 */       if (runner.getTransitionTo() != null && "put_away".equals((runner.getTransitionTo().getAnimation()).name)) {
/* 158 */         long progress = (long)(Math.max(runner.getTransitionTo().getAnimation().getMaxEndTimeS() - putAwayTimeS, 0.0F) * 1.0E9D);
/* 159 */         runner.getTransitionTo().setProgressNs(progress);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onShooterIdle() {
/* 165 */     if (isPlayingIdleAnimation()) {
/*     */       return;
/*     */     }
/* 168 */     ObjectAnimationRunner runner = this.controller.getAnimation(MOVEMENT_TRACK);
/* 169 */     if (runner != null && (runner.isRunning() || runner.isTransitioning()) && 
/* 170 */       !isPlayingWalkAnimation() && !isPlayingRunIntroOrLoop() && !isPlayingRunHold()) {
/*     */       return;
/*     */     }
/*     */     
/* 174 */     this.isWalkAiming = false;
/* 175 */     this.lastWalkDirection = WalkDirection.NONE;
/* 176 */     ArrayDeque<AnimationPlan> deque = new ArrayDeque<>();
/* 177 */     if (isPlayingRunIntroOrLoop()) {
/* 178 */       deque.add(new AnimationPlan("run_end", ObjectAnimation.PlayType.PLAY_ONCE_HOLD, 0.3F));
/*     */     }
/* 180 */     deque.add(new AnimationPlan("idle", ObjectAnimation.PlayType.LOOP, 0.4F));
/* 181 */     this.controller.queueAnimation(MOVEMENT_TRACK, deque);
/*     */   }
/*     */   
/*     */   public void onGunInspect() {
/* 185 */     if (this.noAmmo) {
/* 186 */       this.controller.runAnimation(MAIN_TRACK, "inspect_empty", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */     } else {
/* 188 */       this.controller.runAnimation(MAIN_TRACK, "inspect", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.2F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onShooterWalk(Input input, float walkDist) {
/* 193 */     if (!this.onGround && !isPlayingIdleAnimation()) {
/* 194 */       this.controller.runAnimation(MOVEMENT_TRACK, "idle", ObjectAnimation.PlayType.LOOP, 0.6F);
/* 195 */       this.isWalkAiming = false;
/* 196 */       this.lastWalkDirection = WalkDirection.NONE;
/*     */       return;
/*     */     } 
/* 199 */     if (this.onGround) {
/*     */       
/* 201 */       if (this.isAiming) {
/* 202 */         if (this.isWalkAiming) {
/*     */           return;
/*     */         }
/* 205 */         this.isWalkAiming = true;
/* 206 */         this.lastWalkDirection = WalkDirection.NONE;
/* 207 */         ArrayDeque<AnimationPlan> arrayDeque = new ArrayDeque<>();
/* 208 */         if (isPlayingRunIntroOrLoop() || isPlayingRunHold()) {
/* 209 */           arrayDeque.add(new AnimationPlan("run_end", ObjectAnimation.PlayType.PLAY_ONCE_HOLD, 0.3F));
/*     */         }
/* 211 */         arrayDeque.add(new AnimationPlan("walk_aiming", ObjectAnimation.PlayType.LOOP, 0.3F));
/* 212 */         this.controller.queueAnimation(MOVEMENT_TRACK, arrayDeque);
/* 213 */         this.baseDistanceWalked = walkDist;
/*     */         return;
/*     */       } 
/* 216 */       WalkDirection direction = WalkDirection.fromInput(input);
/*     */       
/* 218 */       if (direction == this.lastWalkDirection) {
/*     */         return;
/*     */       }
/* 221 */       this.isWalkAiming = false;
/* 222 */       this.lastWalkDirection = direction;
/* 223 */       ArrayDeque<AnimationPlan> deque = new ArrayDeque<>();
/* 224 */       if (isPlayingRunIntroOrLoop() || isPlayingRunHold()) {
/* 225 */         deque.add(new AnimationPlan("run_end", ObjectAnimation.PlayType.PLAY_ONCE_HOLD, 0.3F));
/*     */       }
/* 227 */       switch (direction) {
/*     */         case FORWARD:
/* 229 */           deque.add(new AnimationPlan("walk_forward", ObjectAnimation.PlayType.LOOP, 0.4F)); break;
/*     */         case BACKWARD:
/* 231 */           deque.add(new AnimationPlan("walk_backward", ObjectAnimation.PlayType.LOOP, 0.4F)); break;
/*     */         case SIDE_WAY:
/* 233 */           deque.add(new AnimationPlan("walk_sideway", ObjectAnimation.PlayType.LOOP, 0.4F)); break;
/*     */       } 
/* 235 */       this.controller.queueAnimation(MOVEMENT_TRACK, deque);
/* 236 */       this.baseDistanceWalked = walkDist;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGunFireSelect() {}
/*     */ 
/*     */   
/*     */   public void onGunCatchBolt() {
/* 245 */     if (!isPlayingAnimation(BOLT_CATCH_STATIC_TRACK, new String[] { "static_bolt_caught" })) {
/* 246 */       this.controller.runAnimation(BOLT_CATCH_STATIC_TRACK, "static_bolt_caught", ObjectAnimation.PlayType.LOOP, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine setNoAmmo(boolean noAmmo) {
/* 251 */     this.noAmmo = noAmmo;
/* 252 */     return this;
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine setMagExtended(boolean magExtended) {
/* 256 */     this.magExtended = magExtended;
/* 257 */     return this;
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine setOnGround(boolean onGround) {
/* 261 */     this.onGround = onGround;
/* 262 */     return this;
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine setPauseWalkAndRun(boolean pause) {
/* 266 */     this.pauseWalkAndRun = pause;
/* 267 */     return this;
/*     */   }
/*     */   
/*     */   public GunAnimationStateMachine setAiming(boolean isAiming) {
/* 271 */     this.isAiming = isAiming;
/* 272 */     return this;
/*     */   }
/*     */   
/*     */   public AnimationController getController() {
/* 276 */     return this.controller;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldHideCrossHair() {
/* 283 */     if (isPlayingInspectAnimation()) {
/* 284 */       return true;
/*     */     }
/* 286 */     return (isPlayingRunHold() || isPlayingRunLoop());
/*     */   }
/*     */   
/*     */   public void update(float partialTicks, Entity entity) {
/* 290 */     ObjectAnimationRunner runner = this.controller.getAnimation(MOVEMENT_TRACK);
/* 291 */     if (runner != null) {
/*     */ 
/*     */       
/* 294 */       float distanceWalked, deltaDistanceWalked = entity.f_19787_ - entity.f_19867_;
/*     */       
/* 296 */       if (this.pauseWalkAndRun) {
/*     */         
/* 298 */         distanceWalked = this.keepDistanceWalked;
/* 299 */         this.baseDistanceWalked = entity.f_19787_ + deltaDistanceWalked * partialTicks - this.keepDistanceWalked;
/*     */       } else {
/*     */         
/* 302 */         distanceWalked = entity.f_19787_ + deltaDistanceWalked * partialTicks - this.baseDistanceWalked;
/* 303 */         this.keepDistanceWalked = distanceWalked;
/*     */       } 
/* 305 */       String animationName = (runner.getAnimation()).name;
/* 306 */       if ((isNamedWalkAnimation(animationName) || "run".equals(animationName)) && runner.isRunning()) {
/* 307 */         runner.setProgressNs((long)(runner.getAnimation().getMaxEndTimeS() * distanceWalked % 2.0F / 2.0F * 1.0E9F));
/*     */       }
/* 309 */       if (runner.isTransitioning() && runner.getTransitionTo() != null) {
/* 310 */         animationName = (runner.getTransitionTo().getAnimation()).name;
/* 311 */         if (isNamedWalkAnimation(animationName) || "run".equals(animationName)) {
/* 312 */           runner.getTransitionTo().setProgressNs((long)(runner.getTransitionTo().getAnimation().getMaxEndTimeS() * distanceWalked % 2.0F / 2.0F * 1.0E9F));
/*     */         }
/*     */       } 
/*     */     } 
/* 316 */     this.controller.update();
/*     */   }
/*     */   
/*     */   public boolean isPlayingAnimation(int track, @Nonnull String... names) {
/* 320 */     ObjectAnimationRunner runner = this.controller.getAnimation(track);
/* 321 */     if (runner != null) {
/* 322 */       String animationName = (runner.getAnimation()).name;
/* 323 */       if (runner.isRunning()) {
/* 324 */         for (String name : names) {
/* 325 */           if (name.equals(animationName)) {
/* 326 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/* 330 */       if (runner.isTransitioning() && runner.getTransitionTo() != null) {
/* 331 */         animationName = (runner.getTransitionTo().getAnimation()).name;
/* 332 */         for (String name : names) {
/* 333 */           if (name.equals(animationName)) {
/* 334 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 339 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunAnimation() {
/* 343 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run_start", "run", "run_hold", "run_end" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunIntroOrLoop() {
/* 347 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run", "run_start" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunStart() {
/* 351 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run_start" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunLoop() {
/* 355 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunHold() {
/* 359 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run_hold" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingRunEnd() {
/* 363 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "run_end" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingWalkAnimation() {
/* 367 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "walk_forward", "walk_backward", "walk_sideway", "walk_aiming" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingIdleAnimation() {
/* 371 */     return isPlayingAnimation(MOVEMENT_TRACK, new String[] { "idle" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingShootAnimation() {
/* 375 */     for (int track : SHOOTING_TRACKS) {
/* 376 */       ObjectAnimationRunner runner = this.controller.getAnimation(track);
/* 377 */       if (runner != null) {
/* 378 */         if (runner.isRunning()) {
/* 379 */           return true;
/*     */         }
/* 381 */         if (runner.isTransitioning() && runner.getTransitionTo() != null) {
/* 382 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 386 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isPlayingInspectAnimation() {
/* 390 */     return isPlayingAnimation(MAIN_TRACK, new String[] { "inspect", "inspect_empty" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingReloadAnimation() {
/* 394 */     return isPlayingAnimation(MAIN_TRACK, new String[] { "reload_empty", "reload_tactical" });
/*     */   }
/*     */   
/*     */   public boolean isPlayingDrawAnimation() {
/* 398 */     return isPlayingAnimation(MAIN_TRACK, new String[] { "draw" });
/*     */   }
/*     */   
/*     */   public void onGunReleaseBolt() {
/* 402 */     this.controller.removeAnimation(BOLT_CATCH_STATIC_TRACK);
/*     */   }
/*     */   
/*     */   public void onIdleHoldingPose() {
/* 406 */     if (!isPlayingAnimation(HOLDING_POSE_STATIC_TRACK, new String[] { "static_idle" })) {
/* 407 */       this.controller.runAnimation(HOLDING_POSE_STATIC_TRACK, "static_idle", ObjectAnimation.PlayType.LOOP, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isNamedWalkAnimation(String animationName) {
/* 412 */     return ("walk_sideway".equals(animationName) || "walk_forward".equals(animationName) || "walk_backward".equals(animationName) || "walk_aiming"
/* 413 */       .equals(animationName));
/*     */   }
/*     */   
/*     */   private boolean tryRunShootAnimation(int track) {
/* 417 */     ObjectAnimationRunner runner = this.controller.getAnimation(track);
/* 418 */     if (runner != null && runner.isRunning() && "shoot".equals((runner.getAnimation()).name)) {
/* 419 */       return false;
/*     */     }
/* 421 */     if (runner != null && runner.getTransitionTo() != null && "shoot".equals((runner.getTransitionTo().getAnimation()).name)) {
/* 422 */       return false;
/*     */     }
/* 424 */     this.controller.runAnimation(track, "shoot", ObjectAnimation.PlayType.PLAY_ONCE_STOP, 0.0F);
/* 425 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\animation\statemachine\GunAnimationStateMachine.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */