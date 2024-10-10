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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AccessorSparseIndices
/*     */ {
/*     */   private Integer bufferView;
/*     */   private Integer byteOffset;
/*     */   private Integer componentType;
/*     */   
/*     */   public Integer getBufferView() {
/*  33 */     return this.bufferView;
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
/*     */   public void setBufferView(Integer bufferView) {
/*  46 */     if (bufferView == null) {
/*  47 */       throw new NullPointerException("Invalid value for bufferView: " + bufferView + ", may not be null");
/*     */     }
/*  49 */     this.bufferView = bufferView;
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
/*     */   public Integer getByteOffset() {
/*  61 */     return this.byteOffset;
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
/*     */   public void setByteOffset(Integer byteOffset) {
/*  75 */     if (byteOffset == null) {
/*  76 */       this.byteOffset = byteOffset;
/*     */       return;
/*     */     } 
/*  79 */     if (byteOffset.intValue() < 0) {
/*  80 */       throw new IllegalArgumentException("byteOffset < 0");
/*     */     }
/*  82 */     this.byteOffset = byteOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer defaultByteOffset() {
/*  92 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getComponentType() {
/* 102 */     return this.componentType;
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
/*     */   public void setComponentType(Integer componentType) {
/* 115 */     if (componentType == null) {
/* 116 */       throw new NullPointerException("Invalid value for componentType: " + componentType + ", may not be null");
/*     */     }
/* 118 */     if (componentType.intValue() != 5121 && componentType.intValue() != 5123 && componentType.intValue() != 5125) {
/* 119 */       throw new IllegalArgumentException("Invalid value for componentType: " + componentType + ", valid: [5121, 5123, 5125]");
/*     */     }
/* 121 */     this.componentType = componentType;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AccessorSparseIndices.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */