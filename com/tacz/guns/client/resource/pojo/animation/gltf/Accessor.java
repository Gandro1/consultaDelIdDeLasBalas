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
/*     */ public class Accessor
/*     */ {
/*     */   private Integer bufferView;
/*     */   private Integer byteOffset;
/*     */   private Integer componentType;
/*     */   private Boolean normalized;
/*     */   private Integer count;
/*     */   private String type;
/*     */   private Number[] max;
/*     */   private Number[] min;
/*     */   private AccessorSparse sparse;
/*     */   
/*     */   public Integer getBufferView() {
/*  65 */     return this.bufferView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBufferView(Integer bufferView) {
/*  74 */     if (bufferView == null) {
/*  75 */       this.bufferView = bufferView;
/*     */       return;
/*     */     } 
/*  78 */     this.bufferView = bufferView;
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
/*  90 */     return this.byteOffset;
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
/* 104 */     if (byteOffset == null) {
/* 105 */       this.byteOffset = byteOffset;
/*     */       return;
/*     */     } 
/* 108 */     if (byteOffset.intValue() < 0) {
/* 109 */       throw new IllegalArgumentException("byteOffset < 0");
/*     */     }
/* 111 */     this.byteOffset = byteOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer defaultByteOffset() {
/* 121 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getComponentType() {
/* 131 */     return this.componentType;
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
/* 144 */     if (componentType == null) {
/* 145 */       throw new NullPointerException("Invalid value for componentType: " + componentType + ", may not be null");
/*     */     }
/* 147 */     if (componentType.intValue() != 5120 && componentType.intValue() != 5121 && componentType.intValue() != 5122 && componentType.intValue() != 5123 && componentType.intValue() != 5125 && componentType.intValue() != 5126) {
/* 148 */       throw new IllegalArgumentException("Invalid value for componentType: " + componentType + ", valid: [5120, 5121, 5122, 5123, 5125, 5126]");
/*     */     }
/* 150 */     this.componentType = componentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNormalized(Boolean normalized) {
/* 161 */     if (normalized == null) {
/* 162 */       this.normalized = normalized;
/*     */       return;
/*     */     } 
/* 165 */     this.normalized = normalized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isNormalized() {
/* 176 */     return this.normalized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean defaultNormalized() {
/* 186 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getCount() {
/* 196 */     return this.count;
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
/*     */   public void setCount(Integer count) {
/* 209 */     if (count == null) {
/* 210 */       throw new NullPointerException("Invalid value for count: " + count + ", may not be null");
/*     */     }
/* 212 */     if (count.intValue() < 1) {
/* 213 */       throw new IllegalArgumentException("count < 1");
/*     */     }
/* 215 */     this.count = count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 226 */     return this.type;
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
/*     */   public void setType(String type) {
/* 240 */     if (type == null) {
/* 241 */       throw new NullPointerException("Invalid value for type: " + type + ", may not be null");
/*     */     }
/* 243 */     if (!"SCALAR".equals(type) && !"VEC2".equals(type) && !"VEC3".equals(type) && !"VEC4".equals(type) && !"MAT2".equals(type) && !"MAT3".equals(type) && !"MAT4".equals(type)) {
/* 244 */       throw new IllegalArgumentException("Invalid value for type: " + type + ", valid: [SCALAR, VEC2, VEC3, VEC4, MAT2, MAT3, MAT4]");
/*     */     }
/* 246 */     this.type = type;
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
/*     */   public Number[] getMax() {
/* 259 */     return this.max;
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
/*     */   public void setMax(Number[] max) {
/* 274 */     if (max == null) {
/* 275 */       this.max = max;
/*     */       return;
/*     */     } 
/* 278 */     if (max.length < 1) {
/* 279 */       throw new IllegalArgumentException("Number of max elements is < 1");
/*     */     }
/* 281 */     if (max.length > 16) {
/* 282 */       throw new IllegalArgumentException("Number of max elements is > 16");
/*     */     }
/* 284 */     this.max = max;
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
/*     */   public Number[] getMin() {
/* 297 */     return this.min;
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
/*     */   public void setMin(Number[] min) {
/* 312 */     if (min == null) {
/* 313 */       this.min = min;
/*     */       return;
/*     */     } 
/* 316 */     if (min.length < 1) {
/* 317 */       throw new IllegalArgumentException("Number of min elements is < 1");
/*     */     }
/* 319 */     if (min.length > 16) {
/* 320 */       throw new IllegalArgumentException("Number of min elements is > 16");
/*     */     }
/* 322 */     this.min = min;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessorSparse getSparse() {
/* 332 */     return this.sparse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSparse(AccessorSparse sparse) {
/* 342 */     if (sparse == null) {
/* 343 */       this.sparse = sparse;
/*     */       return;
/*     */     } 
/* 346 */     this.sparse = sparse;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\Accessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */