/*    */ package com.tacz.guns.network;
/*    */ 
/*    */ import java.util.function.IntSupplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LoginIndexHolder
/*    */   implements IntSupplier
/*    */ {
/*    */   private int loginIndex;
/*    */   
/*    */   public int getLoginIndex() {
/* 13 */     return this.loginIndex;
/*    */   }
/*    */   
/*    */   public void setLoginIndex(int loginIndex) {
/* 17 */     this.loginIndex = loginIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAsInt() {
/* 22 */     return getLoginIndex();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\LoginIndexHolder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */