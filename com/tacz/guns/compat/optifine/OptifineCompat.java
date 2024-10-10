/*    */ package com.tacz.guns.compat.optifine;
/*    */ 
/*    */ public class OptifineCompat {
/*  4 */   private static final boolean IS_OPTIFINE_INSTALLED = isClassFound("net.optifine.Config");
/*    */   
/*    */   public static boolean isOptifineInstalled() {
/*  7 */     return IS_OPTIFINE_INSTALLED;
/*    */   }
/*    */   
/*    */   public static boolean isClassFound(String className) {
/*    */     try {
/* 12 */       Class.forName(className, false, Thread.currentThread().getContextClassLoader());
/* 13 */       return true;
/* 14 */     } catch (ClassNotFoundException e) {
/* 15 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\optifine\OptifineCompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */