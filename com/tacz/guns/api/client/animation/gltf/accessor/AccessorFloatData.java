/*     */ package com.tacz.guns.api.client.animation.gltf.accessor;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AccessorFloatData
/*     */   extends AbstractAccessorData
/*     */   implements AccessorData
/*     */ {
/*     */   public AccessorFloatData(int componentType, ByteBuffer bufferViewByteBuffer, int byteOffset, int numElements, int numComponentsPerElement, Integer byteStride) {
/*  68 */     super(float.class, bufferViewByteBuffer, byteOffset, numElements, numComponentsPerElement, 4, byteStride);
/*     */     
/*  70 */     AccessorDatas.validateFloatType(componentType);
/*     */     
/*  72 */     AccessorDatas.validateCapacity(byteOffset, getNumElements(), 
/*  73 */         getByteStridePerElement(), bufferViewByteBuffer.capacity());
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
/*     */   public float get(int elementIndex, int componentIndex) {
/*  86 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/*  87 */     return getBufferViewByteBuffer().getFloat(byteIndex);
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
/*     */   public float get(int globalComponentIndex) {
/* 100 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 102 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 103 */     return get(elementIndex, componentIndex);
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
/*     */   public void set(int elementIndex, int componentIndex, float value) {
/* 116 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 117 */     getBufferViewByteBuffer().putFloat(byteIndex, value);
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
/*     */   public void set(int globalComponentIndex, float value) {
/* 130 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 132 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 133 */     set(elementIndex, componentIndex, value);
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
/*     */   public float[] computeMin() {
/* 145 */     float[] result = new float[getNumComponentsPerElement()];
/* 146 */     Arrays.fill(result, Float.MAX_VALUE);
/* 147 */     for (int e = 0; e < getNumElements(); e++) {
/* 148 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 149 */         result[c] = Math.min(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] computeMax() {
/* 163 */     float[] result = new float[getNumComponentsPerElement()];
/* 164 */     Arrays.fill(result, -3.4028235E38F);
/* 165 */     for (int e = 0; e < getNumElements(); e++) {
/* 166 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 167 */         result[c] = Math.max(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 170 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer createByteBuffer() {
/* 175 */     int totalNumComponents = getTotalNumComponents();
/* 176 */     int totalBytes = totalNumComponents * getNumBytesPerComponent();
/*     */     
/* 178 */     ByteBuffer result = ByteBuffer.allocateDirect(totalBytes).order(ByteOrder.nativeOrder());
/* 179 */     for (int i = 0; i < totalNumComponents; i++) {
/* 180 */       float component = get(i);
/* 181 */       result.putFloat(component);
/*     */     } 
/* 183 */     result.position(0);
/* 184 */     return result;
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
/*     */   public String createString(Locale locale, String format, int elementsPerRow) {
/* 198 */     StringBuilder sb = new StringBuilder();
/* 199 */     int nc = getNumComponentsPerElement();
/* 200 */     sb.append("[");
/* 201 */     for (int e = 0; e < getNumElements(); e++) {
/* 202 */       if (e > 0) {
/* 203 */         sb.append(", ");
/* 204 */         if (elementsPerRow > 0 && e % elementsPerRow == 0) {
/* 205 */           sb.append("\n ");
/*     */         }
/*     */       } 
/* 208 */       if (nc > 1) {
/* 209 */         sb.append("(");
/*     */       }
/* 211 */       for (int c = 0; c < nc; c++) {
/* 212 */         if (c > 0) {
/* 213 */           sb.append(", ");
/*     */         }
/* 215 */         float component = get(e, c);
/* 216 */         sb.append(String.format(locale, format, new Object[] { Float.valueOf(component) }));
/*     */       } 
/* 218 */       if (nc > 1) {
/* 219 */         sb.append(")");
/*     */       }
/*     */     } 
/* 222 */     sb.append("]");
/* 223 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorFloatData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */