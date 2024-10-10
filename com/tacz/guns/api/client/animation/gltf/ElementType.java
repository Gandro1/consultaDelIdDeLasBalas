/*    */ package com.tacz.guns.api.client.animation.gltf;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ElementType
/*    */ {
/*  7 */   SCALAR(1),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 12 */   VEC2(2),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   VEC3(3),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   VEC4(4),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   MAT2(4),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   MAT3(9),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   MAT4(16);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final int numComponents;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ElementType(int numComponents) {
/* 50 */     this.numComponents = numComponents;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean contains(String s) {
/* 61 */     for (ElementType elementType : values()) {
/* 62 */       if (elementType.name().equals(s)) {
/* 63 */         return true;
/*    */       }
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ElementType forString(String string) {
/* 78 */     if (string == null) {
/* 79 */       return null;
/*    */     }
/* 81 */     if (!contains(string)) {
/* 82 */       return null;
/*    */     }
/* 84 */     return valueOf(string);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumComponents() {
/* 93 */     return this.numComponents;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\ElementType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */