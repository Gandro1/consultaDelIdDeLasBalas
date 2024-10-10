/*    */ package com.tacz.guns.api.client.animation.gltf.accessor;
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
/*    */ class NumberArrays
/*    */ {
/*    */   static Number[] asNumbers(int[] array) {
/* 47 */     Number[] result = new Number[array.length];
/* 48 */     for (int i = 0; i < array.length; i++) {
/* 49 */       result[i] = Integer.valueOf(array[i]);
/*    */     }
/* 51 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static Number[] asNumbers(long[] array) {
/* 61 */     Number[] result = new Number[array.length];
/* 62 */     for (int i = 0; i < array.length; i++) {
/* 63 */       result[i] = Long.valueOf(array[i]);
/*    */     }
/* 65 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static Number[] asNumbers(float[] array) {
/* 75 */     Number[] result = new Number[array.length];
/* 76 */     for (int i = 0; i < array.length; i++) {
/* 77 */       result[i] = Float.valueOf(array[i]);
/*    */     }
/* 79 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\NumberArrays.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */