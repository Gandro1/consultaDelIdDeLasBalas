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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AccessorIntData
/*     */   extends AbstractAccessorData
/*     */   implements AccessorData
/*     */ {
/*     */   private final boolean unsigned;
/*     */   
/*     */   public AccessorIntData(int componentType, ByteBuffer bufferViewByteBuffer, int byteOffset, int numElements, int numComponentsPerElement, Integer byteStride) {
/*  73 */     super(int.class, bufferViewByteBuffer, byteOffset, numElements, numComponentsPerElement, 4, byteStride);
/*     */     
/*  75 */     AccessorDatas.validateIntType(componentType);
/*     */     
/*  77 */     this.unsigned = AccessorDatas.isUnsignedType(componentType);
/*  78 */     AccessorDatas.validateCapacity(byteOffset, getNumElements(), 
/*  79 */         getByteStridePerElement(), bufferViewByteBuffer.capacity());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnsigned() {
/*  88 */     return this.unsigned;
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
/*     */   public int get(int elementIndex, int componentIndex) {
/* 101 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 102 */     return getBufferViewByteBuffer().getInt(byteIndex);
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
/*     */   public int get(int globalComponentIndex) {
/* 115 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 117 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 118 */     return get(elementIndex, componentIndex);
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
/*     */   public void set(int elementIndex, int componentIndex, int value) {
/* 131 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 132 */     getBufferViewByteBuffer().putInt(byteIndex, value);
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
/*     */   public void set(int globalComponentIndex, int value) {
/* 145 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 147 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 148 */     set(elementIndex, componentIndex, value);
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
/*     */   public long getLong(int elementIndex, int componentIndex) {
/* 164 */     int value = get(elementIndex, componentIndex);
/* 165 */     return this.unsigned ? Integer.toUnsignedLong(value) : value;
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
/*     */   public long getLong(int globalComponentIndex) {
/* 180 */     int value = get(globalComponentIndex);
/* 181 */     return this.unsigned ? Integer.toUnsignedLong(value) : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] computeMin() {
/* 192 */     int[] result = new int[getNumComponentsPerElement()];
/* 193 */     Arrays.fill(result, 2147483647);
/* 194 */     for (int e = 0; e < getNumElements(); e++) {
/* 195 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 196 */         result[c] = Math.min(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 199 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] computeMax() {
/* 210 */     int[] result = new int[getNumComponentsPerElement()];
/* 211 */     Arrays.fill(result, -2147483648);
/* 212 */     for (int e = 0; e < getNumElements(); e++) {
/* 213 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 214 */         result[c] = Math.max(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 217 */     return result;
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
/*     */   public long[] computeMinLong() {
/* 229 */     long[] result = new long[getNumComponentsPerElement()];
/* 230 */     Arrays.fill(result, Long.MAX_VALUE);
/* 231 */     for (int e = 0; e < getNumElements(); e++) {
/* 232 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 233 */         result[c] = Math.min(result[c], getLong(e, c));
/*     */       }
/*     */     } 
/* 236 */     return result;
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
/*     */   public long[] computeMaxLong() {
/* 248 */     long[] result = new long[getNumComponentsPerElement()];
/* 249 */     Arrays.fill(result, Long.MIN_VALUE);
/* 250 */     for (int e = 0; e < getNumElements(); e++) {
/* 251 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 252 */         result[c] = Math.max(result[c], getLong(e, c));
/*     */       }
/*     */     } 
/* 255 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer createByteBuffer() {
/* 260 */     int totalNumComponents = getTotalNumComponents();
/* 261 */     int totalBytes = totalNumComponents * getNumBytesPerComponent();
/*     */     
/* 263 */     ByteBuffer result = ByteBuffer.allocateDirect(totalBytes).order(ByteOrder.nativeOrder());
/* 264 */     for (int i = 0; i < totalNumComponents; i++) {
/* 265 */       int component = get(i);
/* 266 */       result.putInt(component);
/*     */     } 
/* 268 */     result.position(0);
/* 269 */     return result;
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
/* 283 */     StringBuilder sb = new StringBuilder();
/* 284 */     int nc = getNumComponentsPerElement();
/* 285 */     sb.append("[");
/* 286 */     for (int e = 0; e < getNumElements(); e++) {
/* 287 */       if (e > 0) {
/* 288 */         sb.append(", ");
/* 289 */         if (elementsPerRow > 0 && e % elementsPerRow == 0) {
/* 290 */           sb.append("\n ");
/*     */         }
/*     */       } 
/* 293 */       if (nc > 1) {
/* 294 */         sb.append("(");
/*     */       }
/* 296 */       for (int c = 0; c < nc; c++) {
/* 297 */         if (c > 0) {
/* 298 */           sb.append(", ");
/*     */         }
/* 300 */         long component = getLong(e, c);
/* 301 */         sb.append(String.format(locale, format, new Object[] { Long.valueOf(component) }));
/*     */       } 
/* 303 */       if (nc > 1) {
/* 304 */         sb.append(")");
/*     */       }
/*     */     } 
/* 307 */     sb.append("]");
/* 308 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorIntData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */