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
/*     */ public final class AccessorShortData
/*     */   extends AbstractAccessorData
/*     */   implements AccessorData
/*     */ {
/*     */   private final boolean unsigned;
/*     */   
/*     */   public AccessorShortData(int componentType, ByteBuffer bufferViewByteBuffer, int byteOffset, int numElements, int numComponentsPerElement, Integer byteStride) {
/*  73 */     super(short.class, bufferViewByteBuffer, byteOffset, numElements, numComponentsPerElement, 2, byteStride);
/*     */     
/*  75 */     AccessorDatas.validateShortType(componentType);
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
/*     */   public short get(int elementIndex, int componentIndex) {
/* 101 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 102 */     return getBufferViewByteBuffer().getShort(byteIndex);
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
/*     */   public short get(int globalComponentIndex) {
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
/*     */   public void set(int elementIndex, int componentIndex, short value) {
/* 131 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 132 */     getBufferViewByteBuffer().putShort(byteIndex, value);
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
/*     */   public void set(int globalComponentIndex, short value) {
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
/*     */   
/*     */   public int getInt(int elementIndex, int componentIndex) {
/* 165 */     short value = get(elementIndex, componentIndex);
/* 166 */     return this.unsigned ? Short.toUnsignedInt(value) : value;
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
/*     */   public int getInt(int globalComponentIndex) {
/* 181 */     short value = get(globalComponentIndex);
/* 182 */     return this.unsigned ? Short.toUnsignedInt(value) : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] computeMin() {
/* 193 */     short[] result = new short[getNumComponentsPerElement()];
/* 194 */     Arrays.fill(result, '翿');
/* 195 */     for (int e = 0; e < getNumElements(); e++) {
/* 196 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 197 */         result[c] = (short)Math.min(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 200 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] computeMax() {
/* 211 */     short[] result = new short[getNumComponentsPerElement()];
/* 212 */     Arrays.fill(result, -32768);
/* 213 */     for (int e = 0; e < getNumElements(); e++) {
/* 214 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 215 */         result[c] = (short)Math.max(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 218 */     return result;
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
/*     */   public int[] computeMinInt() {
/* 230 */     int[] result = new int[getNumComponentsPerElement()];
/* 231 */     Arrays.fill(result, 2147483647);
/* 232 */     for (int e = 0; e < getNumElements(); e++) {
/* 233 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 234 */         result[c] = Math.min(result[c], getInt(e, c));
/*     */       }
/*     */     } 
/* 237 */     return result;
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
/*     */   public int[] computeMaxInt() {
/* 249 */     int[] result = new int[getNumComponentsPerElement()];
/* 250 */     Arrays.fill(result, -2147483648);
/* 251 */     for (int e = 0; e < getNumElements(); e++) {
/* 252 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 253 */         result[c] = Math.max(result[c], getInt(e, c));
/*     */       }
/*     */     } 
/* 256 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer createByteBuffer() {
/* 261 */     int totalNumComponents = getTotalNumComponents();
/* 262 */     int totalBytes = totalNumComponents * getNumBytesPerComponent();
/*     */     
/* 264 */     ByteBuffer result = ByteBuffer.allocateDirect(totalBytes).order(ByteOrder.nativeOrder());
/* 265 */     for (int i = 0; i < totalNumComponents; i++) {
/* 266 */       short component = get(i);
/* 267 */       result.putShort(component);
/*     */     } 
/* 269 */     result.position(0);
/* 270 */     return result;
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
/* 284 */     StringBuilder sb = new StringBuilder();
/* 285 */     int nc = getNumComponentsPerElement();
/* 286 */     sb.append("[");
/* 287 */     for (int e = 0; e < getNumElements(); e++) {
/* 288 */       if (e > 0) {
/* 289 */         sb.append(", ");
/* 290 */         if (elementsPerRow > 0 && e % elementsPerRow == 0) {
/* 291 */           sb.append("\n ");
/*     */         }
/*     */       } 
/* 294 */       if (nc > 1) {
/* 295 */         sb.append("(");
/*     */       }
/* 297 */       for (int c = 0; c < nc; c++) {
/* 298 */         if (c > 0) {
/* 299 */           sb.append(", ");
/*     */         }
/* 301 */         int component = getInt(e, c);
/* 302 */         sb.append(String.format(locale, format, new Object[] { Integer.valueOf(component) }));
/*     */       } 
/* 304 */       if (nc > 1) {
/* 305 */         sb.append(")");
/*     */       }
/*     */     } 
/* 308 */     sb.append("]");
/* 309 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorShortData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */