/*     */ package com.tacz.guns.api.client.animation.gltf.accessor;
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
/*     */ public class Accessors
/*     */ {
/*     */   public static int getNumComponentsForAccessorType(String accessorType) {
/*  32 */     switch (accessorType) {
/*     */       case "SCALAR":
/*  34 */         return 1;
/*     */       case "VEC2":
/*  36 */         return 2;
/*     */       case "VEC3":
/*  38 */         return 3;
/*     */       case "VEC4":
/*  40 */         return 4;
/*     */       case "MAT2":
/*  42 */         return 4;
/*     */       case "MAT3":
/*  44 */         return 9;
/*     */       case "MAT4":
/*  46 */         return 16;
/*     */     } 
/*     */ 
/*     */     
/*  50 */     throw new IllegalArgumentException("Invalid accessor type: " + accessorType);
/*     */   }
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
/*     */   public static int getNumBytesForAccessorComponentType(int componentType) {
/*  74 */     switch (componentType) {
/*     */       case 5120:
/*  76 */         return 1;
/*     */       case 5121:
/*  78 */         return 1;
/*     */       case 5122:
/*  80 */         return 2;
/*     */       case 5123:
/*  82 */         return 2;
/*     */       case 5124:
/*  84 */         return 4;
/*     */       case 5125:
/*  86 */         return 4;
/*     */       case 5126:
/*  88 */         return 4;
/*     */     } 
/*     */ 
/*     */     
/*  92 */     throw new IllegalArgumentException("Invalid accessor component type: " + componentType);
/*     */   }
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
/*     */   public static Class<?> getDataTypeForAccessorComponentType(int componentType) {
/* 116 */     switch (componentType) {
/*     */       case 5120:
/* 118 */         return byte.class;
/*     */       case 5121:
/* 120 */         return byte.class;
/*     */       case 5122:
/* 122 */         return short.class;
/*     */       case 5123:
/* 124 */         return short.class;
/*     */       case 5124:
/* 126 */         return int.class;
/*     */       case 5125:
/* 128 */         return int.class;
/*     */       case 5126:
/* 130 */         return float.class;
/*     */     } 
/*     */ 
/*     */     
/* 134 */     throw new IllegalArgumentException("Invalid accessor component type: " + componentType);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\Accessors.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */