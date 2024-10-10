/*    */ package com.tacz.guns.api.modifier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CacheValue<T>
/*    */ {
/*    */   private T value;
/*    */   
/*    */   public CacheValue(T value) {
/* 10 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 14 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 18 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\modifier\CacheValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */