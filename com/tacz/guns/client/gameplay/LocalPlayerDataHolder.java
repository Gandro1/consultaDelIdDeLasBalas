/*     */ package com.tacz.guns.client.gameplay;
/*     */ 
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ 
/*     */ public class LocalPlayerDataHolder {
/*  14 */   public static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(2);
/*     */ 
/*     */ 
/*     */   
/*  18 */   public static float oldAimingProgress = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*  22 */   public static long clientClickButtonTimestamp = -1L;
/*     */ 
/*     */ 
/*     */   
/*     */   private final LocalPlayer player;
/*     */ 
/*     */ 
/*     */   
/*  30 */   public volatile long clientShootTimestamp = -1L;
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean isShootRecorded = true;
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean clientStateLock = false;
/*     */ 
/*     */   
/*     */   public boolean isBolting = false;
/*     */ 
/*     */   
/*  44 */   public float clientAimingProgress = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*  48 */   public long clientAimingTimestamp = -1L;
/*     */ 
/*     */   
/*     */   public boolean clientIsAiming = false;
/*     */ 
/*     */   
/*  54 */   public long clientDrawTimestamp = -1L;
/*     */ 
/*     */   
/*     */   @Nullable
/*  58 */   public ScheduledFuture<?> drawFuture = null;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*  63 */   public Predicate<IGunOperator> lockedCondition = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public long lockTimestamp = -1L;
/*     */   
/*     */   public LocalPlayerDataHolder(LocalPlayer player) {
/*  71 */     this.player = player;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lockState(@Nullable Predicate<IGunOperator> lockedCondition) {
/*  78 */     this.clientStateLock = true;
/*  79 */     this.lockTimestamp = System.currentTimeMillis();
/*  80 */     this.lockedCondition = lockedCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickStateLock() {
/*  87 */     IGunOperator gunOperator = IGunOperator.fromLivingEntity((LivingEntity)this.player);
/*  88 */     ReloadState reloadState = gunOperator.getSynReloadState();
/*     */ 
/*     */     
/*  91 */     long maxLockTime = 250L;
/*  92 */     long lockTime = System.currentTimeMillis() - this.lockTimestamp;
/*  93 */     if (lockTime < maxLockTime && this.lockedCondition != null && !this.lockedCondition.test(gunOperator)) {
/*     */       return;
/*     */     }
/*  96 */     this.lockedCondition = null;
/*  97 */     if (reloadState.getStateType().isReloading()) {
/*     */       return;
/*     */     }
/* 100 */     long shootCoolDown = gunOperator.getSynShootCoolDown();
/* 101 */     if (shootCoolDown > 0L) {
/*     */       return;
/*     */     }
/* 104 */     if (gunOperator.getSynDrawCoolDown() > 0L) {
/*     */       return;
/*     */     }
/* 107 */     if (gunOperator.getSynBoltCoolDown() >= 0L) {
/*     */       return;
/*     */     }
/* 110 */     if (gunOperator.getSynMeleeCoolDown() > 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     this.clientStateLock = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 122 */     this.isShootRecorded = true;
/* 123 */     this.clientShootTimestamp = -1L;
/*     */     
/* 125 */     this.clientIsAiming = false;
/* 126 */     this.clientAimingProgress = 0.0F;
/* 127 */     oldAimingProgress = 0.0F;
/*     */     
/* 129 */     this.isBolting = false;
/*     */     
/* 131 */     this.clientStateLock = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerDataHolder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */