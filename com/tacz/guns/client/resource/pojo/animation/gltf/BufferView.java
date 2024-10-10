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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferView
/*     */ {
/*     */   private Integer buffer;
/*     */   private Integer byteOffset;
/*     */   private Integer byteLength;
/*     */   private Integer byteStride;
/*     */   private Integer target;
/*     */   
/*     */   public Integer getBuffer() {
/*  38 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBuffer(Integer buffer) {
/*  48 */     if (buffer == null) {
/*  49 */       throw new NullPointerException("Invalid value for buffer: " + buffer + ", may not be null");
/*     */     }
/*  51 */     this.buffer = buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getByteOffset() {
/*  62 */     return this.byteOffset;
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
/*     */   public Integer getByteLength() {
/* 102 */     return this.byteLength;
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
/*     */   public void setByteLength(Integer byteLength) {
/* 115 */     if (byteLength == null) {
/* 116 */       throw new NullPointerException("Invalid value for byteLength: " + byteLength + ", may not be null");
/*     */     }
/* 118 */     if (byteLength.intValue() < 1) {
/* 119 */       throw new IllegalArgumentException("byteLength < 1");
/*     */     }
/* 121 */     this.byteLength = byteLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getByteStride() {
/* 132 */     return this.byteStride;
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
/*     */   public void setByteStride(Integer byteStride) {
/* 145 */     if (byteStride == null) {
/* 146 */       this.byteStride = byteStride;
/*     */       return;
/*     */     } 
/* 149 */     if (byteStride.intValue() > 252) {
/* 150 */       throw new IllegalArgumentException("byteStride > 252");
/*     */     }
/* 152 */     if (byteStride.intValue() < 4) {
/* 153 */       throw new IllegalArgumentException("byteStride < 4");
/*     */     }
/* 155 */     this.byteStride = byteStride;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getTarget() {
/* 166 */     return this.target;
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
/*     */   public void setTarget(Integer target) {
/* 179 */     if (target == null) {
/* 180 */       this.target = target;
/*     */       return;
/*     */     } 
/* 183 */     if (target.intValue() != 34962 && target.intValue() != 34963) {
/* 184 */       throw new IllegalArgumentException("Invalid value for target: " + target + ", valid: [34962, 34963]");
/*     */     }
/* 186 */     this.target = target;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\BufferView.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */