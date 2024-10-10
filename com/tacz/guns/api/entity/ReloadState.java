/*     */ package com.tacz.guns.api.entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReloadState
/*     */ {
/*     */   public static final int NOT_RELOADING_COUNTDOWN = -1;
/*     */   protected StateType stateType;
/*     */   protected long countDown;
/*     */   
/*     */   public ReloadState() {
/*  18 */     this.stateType = StateType.NOT_RELOADING;
/*  19 */     this.countDown = -1L;
/*     */   }
/*     */   
/*     */   public ReloadState(ReloadState src) {
/*  23 */     this.stateType = src.stateType;
/*  24 */     this.countDown = src.countDown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateType getStateType() {
/*  31 */     return this.stateType;
/*     */   }
/*     */   
/*     */   public void setStateType(StateType stateType) {
/*  35 */     this.stateType = stateType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCountDown() {
/*  42 */     if (this.stateType == StateType.NOT_RELOADING) {
/*  43 */       return -1L;
/*     */     }
/*  45 */     return this.countDown;
/*     */   }
/*     */   
/*     */   public void setCountDown(long countDown) {
/*  49 */     this.countDown = countDown;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  54 */     if (o instanceof ReloadState) { ReloadState reloadState = (ReloadState)o;
/*  55 */       return (reloadState.stateType.equals(this.stateType) && reloadState.countDown == this.countDown); }
/*     */     
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum StateType
/*     */   {
/*  65 */     NOT_RELOADING,
/*     */ 
/*     */ 
/*     */     
/*  69 */     EMPTY_RELOAD_FEEDING,
/*     */ 
/*     */ 
/*     */     
/*  73 */     EMPTY_RELOAD_FINISHING,
/*     */ 
/*     */ 
/*     */     
/*  77 */     TACTICAL_RELOAD_FEEDING,
/*     */ 
/*     */ 
/*     */     
/*  81 */     TACTICAL_RELOAD_FINISHING;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isReloadingEmpty() {
/*  87 */       return (this == EMPTY_RELOAD_FEEDING || this == EMPTY_RELOAD_FINISHING);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isReloadingTactical() {
/*  94 */       return (this == TACTICAL_RELOAD_FEEDING || this == TACTICAL_RELOAD_FINISHING);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isReloading() {
/* 101 */       return (isReloadingEmpty() || isReloadingTactical());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isReloadFinishing() {
/* 108 */       return (this == EMPTY_RELOAD_FINISHING || this == TACTICAL_RELOAD_FINISHING);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\entity\ReloadState.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */