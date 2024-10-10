/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SyncMode
/*    */ {
/* 52 */   NONE(false, false),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   ALL(true, true),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   TRACKING_ONLY(true, false),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   SELF_ONLY(false, true);
/*    */   
/*    */   final boolean tracking;
/*    */   final boolean self;
/*    */   
/*    */   SyncMode(boolean tracking, boolean self) {
/* 76 */     this.tracking = tracking;
/* 77 */     this.self = self;
/*    */   }
/*    */   
/*    */   public boolean isTracking() {
/* 81 */     return this.tracking;
/*    */   }
/*    */   
/*    */   public boolean isSelf() {
/* 85 */     return this.self;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\SyncedDataKey$SyncMode.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */