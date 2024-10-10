/*     */ package com.tacz.guns.api.client.animation.gltf.accessor;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AccessorSparseUtils
/*     */ {
/*  10 */   private static final Logger logger = Logger.getLogger(AccessorSparseUtils.class.getName());
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
/*     */   private static int[] extractIndices(AccessorData accessorData) {
/*  32 */     if (accessorData.getComponentType() == byte.class) {
/*  33 */       AccessorByteData accessorByteData = (AccessorByteData)accessorData;
/*     */       
/*  35 */       int numElements = accessorByteData.getNumElements();
/*  36 */       int[] indices = new int[numElements];
/*  37 */       for (int i = 0; i < numElements; i++) {
/*  38 */         indices[i] = accessorByteData.getInt(i, 0);
/*     */       }
/*  40 */       return indices;
/*     */     } 
/*  42 */     if (accessorData.getComponentType() == short.class) {
/*  43 */       AccessorShortData accessorShortData = (AccessorShortData)accessorData;
/*     */       
/*  45 */       int numElements = accessorShortData.getNumElements();
/*  46 */       int[] indices = new int[numElements];
/*  47 */       for (int i = 0; i < numElements; i++) {
/*  48 */         indices[i] = accessorShortData.getInt(i, 0);
/*     */       }
/*  50 */       return indices;
/*     */     } 
/*  52 */     if (accessorData.getComponentType() == int.class) {
/*  53 */       AccessorIntData accessorIntData = (AccessorIntData)accessorData;
/*     */       
/*  55 */       int numElements = accessorIntData.getNumElements();
/*  56 */       int[] indices = new int[numElements];
/*  57 */       for (int i = 0; i < numElements; i++) {
/*  58 */         indices[i] = accessorIntData.get(i, 0);
/*     */       }
/*  60 */       return indices;
/*     */     } 
/*  62 */     throw new IllegalArgumentException("Invalid type for indices: " + accessorData
/*  63 */         .getComponentType());
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
/*     */   public static void substituteAccessorData(AccessorData denseAccessorData, AccessorData baseAccessorData, AccessorData sparseIndicesAccessorData, AccessorData sparseValuesAccessorData) {
/*  99 */     Class<?> componentType = denseAccessorData.getComponentType();
/* 100 */     if (componentType == byte.class) {
/* 101 */       AccessorByteData sparseValuesAccessorByteData = (AccessorByteData)sparseValuesAccessorData;
/*     */       
/* 103 */       AccessorByteData baseAccessorByteData = (AccessorByteData)baseAccessorData;
/*     */       
/* 105 */       AccessorByteData denseAccessorByteData = (AccessorByteData)denseAccessorData;
/*     */       
/* 107 */       substituteByteAccessorData(denseAccessorByteData, baseAccessorByteData, sparseIndicesAccessorData, sparseValuesAccessorByteData);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 112 */     else if (componentType == short.class) {
/* 113 */       AccessorShortData sparseValuesAccessorShortData = (AccessorShortData)sparseValuesAccessorData;
/*     */       
/* 115 */       AccessorShortData baseAccessorShortData = (AccessorShortData)baseAccessorData;
/*     */       
/* 117 */       AccessorShortData denseAccessorShortData = (AccessorShortData)denseAccessorData;
/*     */       
/* 119 */       substituteShortAccessorData(denseAccessorShortData, baseAccessorShortData, sparseIndicesAccessorData, sparseValuesAccessorShortData);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 124 */     else if (componentType == int.class) {
/* 125 */       AccessorIntData sparseValuesAccessorIntData = (AccessorIntData)sparseValuesAccessorData;
/*     */       
/* 127 */       AccessorIntData baseAccessorIntData = (AccessorIntData)baseAccessorData;
/*     */       
/* 129 */       AccessorIntData denseAccessorIntData = (AccessorIntData)denseAccessorData;
/*     */       
/* 131 */       substituteIntAccessorData(denseAccessorIntData, baseAccessorIntData, sparseIndicesAccessorData, sparseValuesAccessorIntData);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 136 */     else if (componentType == float.class) {
/* 137 */       AccessorFloatData sparseValuesAccessorFloatData = (AccessorFloatData)sparseValuesAccessorData;
/*     */       
/* 139 */       AccessorFloatData baseAccessorFloatData = (AccessorFloatData)baseAccessorData;
/*     */       
/* 141 */       AccessorFloatData denseAccessorFloatData = (AccessorFloatData)denseAccessorData;
/*     */ 
/*     */       
/* 144 */       substituteFloatAccessorData(denseAccessorFloatData, baseAccessorFloatData, sparseIndicesAccessorData, sparseValuesAccessorFloatData);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 150 */       logger.warning("Invalid component type for accessor: " + componentType);
/*     */     } 
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
/*     */   private static void substituteByteAccessorData(AccessorByteData denseAccessorData, AccessorByteData baseAccessorData, AccessorData sparseIndicesAccessorData, AccessorByteData sparseValuesAccessorData) {
/* 170 */     int numElements = denseAccessorData.getNumElements();
/*     */     
/* 172 */     int numComponentsPerElement = denseAccessorData.getNumComponentsPerElement();
/*     */     
/* 174 */     if (baseAccessorData != null)
/*     */     {
/* 176 */       for (int e = 0; e < numElements; e++) {
/* 177 */         for (int c = 0; c < numComponentsPerElement; c++) {
/* 178 */           byte value = baseAccessorData.get(e, c);
/* 179 */           denseAccessorData.set(e, c, value);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 185 */     int[] indices = extractIndices(sparseIndicesAccessorData);
/* 186 */     for (int i = 0; i < indices.length; i++) {
/* 187 */       int targetElementIndex = indices[i];
/* 188 */       for (int c = 0; c < numComponentsPerElement; c++) {
/* 189 */         byte substitution = sparseValuesAccessorData.get(i, c);
/* 190 */         denseAccessorData.set(targetElementIndex, c, substitution);
/*     */       } 
/*     */     } 
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
/*     */   private static void substituteShortAccessorData(AccessorShortData denseAccessorData, AccessorShortData baseAccessorData, AccessorData sparseIndicesAccessorData, AccessorShortData sparseValuesAccessorData) {
/* 210 */     int numElements = denseAccessorData.getNumElements();
/*     */     
/* 212 */     int numComponentsPerElement = denseAccessorData.getNumComponentsPerElement();
/*     */     
/* 214 */     if (baseAccessorData != null)
/*     */     {
/* 216 */       for (int e = 0; e < numElements; e++) {
/* 217 */         for (int c = 0; c < numComponentsPerElement; c++) {
/* 218 */           short value = baseAccessorData.get(e, c);
/* 219 */           denseAccessorData.set(e, c, value);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 225 */     int[] indices = extractIndices(sparseIndicesAccessorData);
/* 226 */     for (int i = 0; i < indices.length; i++) {
/* 227 */       int targetElementIndex = indices[i];
/* 228 */       for (int c = 0; c < numComponentsPerElement; c++) {
/* 229 */         short substitution = sparseValuesAccessorData.get(i, c);
/* 230 */         denseAccessorData.set(targetElementIndex, c, substitution);
/*     */       } 
/*     */     } 
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
/*     */   private static void substituteIntAccessorData(AccessorIntData denseAccessorData, AccessorIntData baseAccessorData, AccessorData sparseIndicesAccessorData, AccessorIntData sparseValuesAccessorData) {
/* 250 */     int numElements = denseAccessorData.getNumElements();
/*     */     
/* 252 */     int numComponentsPerElement = denseAccessorData.getNumComponentsPerElement();
/*     */     
/* 254 */     if (baseAccessorData != null)
/*     */     {
/* 256 */       for (int e = 0; e < numElements; e++) {
/* 257 */         for (int c = 0; c < numComponentsPerElement; c++) {
/* 258 */           int value = baseAccessorData.get(e, c);
/* 259 */           denseAccessorData.set(e, c, value);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 265 */     int[] indices = extractIndices(sparseIndicesAccessorData);
/* 266 */     for (int i = 0; i < indices.length; i++) {
/* 267 */       int targetElementIndex = indices[i];
/* 268 */       for (int c = 0; c < numComponentsPerElement; c++) {
/* 269 */         int substitution = sparseValuesAccessorData.get(i, c);
/* 270 */         denseAccessorData.set(targetElementIndex, c, substitution);
/*     */       } 
/*     */     } 
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
/*     */   private static void substituteFloatAccessorData(AccessorFloatData denseAccessorData, AccessorFloatData baseAccessorData, AccessorData sparseIndicesAccessorData, AccessorFloatData sparseValuesAccessorData) {
/* 290 */     int numElements = denseAccessorData.getNumElements();
/*     */     
/* 292 */     int numComponentsPerElement = denseAccessorData.getNumComponentsPerElement();
/*     */     
/* 294 */     if (baseAccessorData != null)
/*     */     {
/* 296 */       for (int e = 0; e < numElements; e++) {
/* 297 */         for (int c = 0; c < numComponentsPerElement; c++) {
/* 298 */           float value = baseAccessorData.get(e, c);
/* 299 */           denseAccessorData.set(e, c, value);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 305 */     int[] indices = extractIndices(sparseIndicesAccessorData);
/* 306 */     for (int i = 0; i < indices.length; i++) {
/* 307 */       int targetElementIndex = indices[i];
/* 308 */       for (int c = 0; c < numComponentsPerElement; c++) {
/* 309 */         float substitution = sparseValuesAccessorData.get(i, c);
/* 310 */         denseAccessorData.set(targetElementIndex, c, substitution);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorSparseUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */