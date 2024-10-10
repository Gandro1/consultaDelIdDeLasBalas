/*     */ package com.tacz.guns.api.client.animation.gltf.accessor;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Objects;
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
/*     */ abstract class AbstractAccessorData
/*     */   implements AccessorData
/*     */ {
/*     */   private final Class<?> componentType;
/*     */   private final ByteBuffer bufferViewByteBuffer;
/*     */   private final int byteOffset;
/*     */   private final int numElements;
/*     */   private final int numComponentsPerElement;
/*     */   private final int numBytesPerComponent;
/*     */   private final int byteStridePerElement;
/*     */   
/*     */   AbstractAccessorData(Class<?> componentType, ByteBuffer bufferViewByteBuffer, int byteOffset, int numElements, int numComponentsPerElement, int numBytesPerComponent, Integer byteStride) {
/*  91 */     Objects.requireNonNull(bufferViewByteBuffer, "The bufferViewByteBuffer is null");
/*     */ 
/*     */     
/*  94 */     this.componentType = componentType;
/*  95 */     this.bufferViewByteBuffer = bufferViewByteBuffer;
/*  96 */     this.byteOffset = byteOffset;
/*  97 */     this.numElements = numElements;
/*  98 */     this.numComponentsPerElement = numComponentsPerElement;
/*  99 */     this.numBytesPerComponent = numBytesPerComponent;
/* 100 */     if (byteStride == null || byteStride.intValue() == 0) {
/* 101 */       this.byteStridePerElement = numComponentsPerElement * numBytesPerComponent;
/*     */     } else {
/*     */       
/* 104 */       this.byteStridePerElement = byteStride.intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final Class<?> getComponentType() {
/* 110 */     return this.componentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNumElements() {
/* 115 */     return this.numElements;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNumComponentsPerElement() {
/* 120 */     return this.numComponentsPerElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getTotalNumComponents() {
/* 125 */     return this.numElements * this.numComponentsPerElement;
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
/*     */   protected final int getByteIndex(int elementIndex, int componentIndex) {
/* 137 */     return this.byteOffset + elementIndex * this.byteStridePerElement + componentIndex * this.numBytesPerComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final ByteBuffer getBufferViewByteBuffer() {
/* 147 */     return this.bufferViewByteBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getByteStridePerElement() {
/* 156 */     return this.byteStridePerElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getNumBytesPerComponent() {
/* 165 */     return this.numBytesPerComponent;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AbstractAccessorData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */