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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum StateType
/*     */ {
/*  65 */   NOT_RELOADING,
/*     */ 
/*     */ 
/*     */   
/*  69 */   EMPTY_RELOAD_FEEDING,
/*     */ 
/*     */ 
/*     */   
/*  73 */   EMPTY_RELOAD_FINISHING,
/*     */ 
/*     */ 
/*     */   
/*  77 */   TACTICAL_RELOAD_FEEDING,
/*     */ 
/*     */ 
/*     */   
/*  81 */   TACTICAL_RELOAD_FINISHING;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReloadingEmpty() {
/*  87 */     return (this == EMPTY_RELOAD_FEEDING || this == EMPTY_RELOAD_FINISHING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReloadingTactical() {
/*  94 */     return (this == TACTICAL_RELOAD_FEEDING || this == TACTICAL_RELOAD_FINISHING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReloading() {
/* 101 */     return (isReloadingEmpty() || isReloadingTactical());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReloadFinishing() {
/* 108 */     return (this == EMPTY_RELOAD_FINISHING || this == TACTICAL_RELOAD_FINISHING);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\entity\ReloadState$StateType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */