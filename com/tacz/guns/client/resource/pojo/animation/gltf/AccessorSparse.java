/*     */ package com.tacz.guns.client.resource.pojo.animation.gltf;
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
/*     */ public class AccessorSparse
/*     */ {
/*     */   private Integer count;
/*     */   private AccessorSparseIndices indices;
/*     */   private AccessorSparseValues values;
/*     */   
/*     */   public Integer getCount() {
/*  30 */     return this.count;
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
/*     */   public void setCount(Integer count) {
/*  44 */     if (count == null) {
/*  45 */       throw new NullPointerException("Invalid value for count: " + count + ", may not be null");
/*     */     }
/*  47 */     if (count.intValue() < 1) {
/*  48 */       throw new IllegalArgumentException("count < 1");
/*     */     }
/*  50 */     this.count = count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessorSparseIndices getIndices() {
/*  61 */     return this.indices;
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
/*     */   public void setIndices(AccessorSparseIndices indices) {
/*  73 */     if (indices == null) {
/*  74 */       throw new NullPointerException("Invalid value for indices: " + indices + ", may not be null");
/*     */     }
/*  76 */     this.indices = indices;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessorSparseValues getValues() {
/*  86 */     return this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(AccessorSparseValues values) {
/*  97 */     if (values == null) {
/*  98 */       throw new NullPointerException("Invalid value for values: " + values + ", may not be null");
/*     */     }
/* 100 */     this.values = values;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AccessorSparse.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */