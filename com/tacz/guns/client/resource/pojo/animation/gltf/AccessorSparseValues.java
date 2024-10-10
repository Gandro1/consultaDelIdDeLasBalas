/*    */ package com.tacz.guns.client.resource.pojo.animation.gltf;
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
/*    */ public class AccessorSparseValues
/*    */ {
/*    */   private Integer bufferView;
/*    */   private Integer byteOffset;
/*    */   
/*    */   public Integer getBufferView() {
/* 26 */     return this.bufferView;
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
/*    */   public void setBufferView(Integer bufferView) {
/* 38 */     if (bufferView == null) {
/* 39 */       throw new NullPointerException("Invalid value for bufferView: " + bufferView + ", may not be null");
/*    */     }
/* 41 */     this.bufferView = bufferView;
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
/*    */   public Integer getByteOffset() {
/* 53 */     return this.byteOffset;
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
/*    */ 
/*    */   
/*    */   public void setByteOffset(Integer byteOffset) {
/* 67 */     if (byteOffset == null) {
/* 68 */       this.byteOffset = byteOffset;
/*    */       return;
/*    */     } 
/* 71 */     if (byteOffset.intValue() < 0) {
/* 72 */       throw new IllegalArgumentException("byteOffset < 0");
/*    */     }
/* 74 */     this.byteOffset = byteOffset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer defaultByteOffset() {
/* 84 */     return Integer.valueOf(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AccessorSparseValues.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */