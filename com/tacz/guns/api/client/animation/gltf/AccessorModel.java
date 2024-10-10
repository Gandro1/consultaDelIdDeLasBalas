/*     */ package com.tacz.guns.api.client.animation.gltf;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorData;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorDatas;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.Accessors;
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
/*     */ public class AccessorModel
/*     */ {
/*     */   private final int componentType;
/*     */   private final ElementType elementType;
/*     */   private final int count;
/*     */   private int byteOffset;
/*     */   private BufferViewModel bufferViewModel;
/*     */   private int byteStride;
/*     */   private AccessorData accessorData;
/*     */   private Number[] max;
/*     */   private Number[] min;
/*     */   
/*     */   public AccessorModel(int componentType, int count, ElementType elementType) {
/*  59 */     this.componentType = componentType;
/*  60 */     this.count = count;
/*  61 */     this.elementType = elementType;
/*     */   }
/*     */   
/*     */   public BufferViewModel getBufferViewModel() {
/*  65 */     return this.bufferViewModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBufferViewModel(BufferViewModel bufferViewModel) {
/*  74 */     this.bufferViewModel = bufferViewModel;
/*     */   }
/*     */   
/*     */   public int getComponentType() {
/*  78 */     return this.componentType;
/*     */   }
/*     */   
/*     */   public Class<?> getComponentDataType() {
/*  82 */     return Accessors.getDataTypeForAccessorComponentType(
/*  83 */         getComponentType());
/*     */   }
/*     */   
/*     */   public int getComponentSizeInBytes() {
/*  87 */     return Accessors.getNumBytesForAccessorComponentType(this.componentType);
/*     */   }
/*     */   
/*     */   public int getElementSizeInBytes() {
/*  91 */     return this.elementType.getNumComponents() * getComponentSizeInBytes();
/*     */   }
/*     */   
/*     */   public int getByteOffset() {
/*  95 */     return this.byteOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteOffset(int byteOffset) {
/* 104 */     this.byteOffset = byteOffset;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 108 */     return this.count;
/*     */   }
/*     */   
/*     */   public ElementType getElementType() {
/* 112 */     return this.elementType;
/*     */   }
/*     */   
/*     */   public int getByteStride() {
/* 116 */     return this.byteStride;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteStride(int byteStride) {
/* 126 */     this.byteStride = byteStride;
/*     */   }
/*     */   
/*     */   public AccessorData getAccessorData() {
/* 130 */     if (this.accessorData == null) {
/* 131 */       this.accessorData = AccessorDatas.create(this);
/*     */     }
/* 133 */     return this.accessorData;
/*     */   }
/*     */   
/*     */   public Number[] getMin() {
/* 137 */     if (this.min == null) {
/* 138 */       this.min = AccessorDatas.computeMin(getAccessorData());
/*     */     }
/* 140 */     return (Number[])this.min.clone();
/*     */   }
/*     */   
/*     */   public Number[] getMax() {
/* 144 */     if (this.max == null) {
/* 145 */       this.max = AccessorDatas.computeMax(getAccessorData());
/*     */     }
/* 147 */     return (Number[])this.max.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\AccessorModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */