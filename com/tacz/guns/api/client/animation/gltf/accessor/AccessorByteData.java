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
/*     */ public final class AccessorByteData
/*     */   extends AbstractAccessorData
/*     */   implements AccessorData
/*     */ {
/*     */   private final boolean unsigned;
/*     */   
/*     */   public AccessorByteData(int componentType, ByteBuffer bufferViewByteBuffer, int byteOffset, int numElements, int numComponentsPerElement, Integer byteStride) {
/*  72 */     super(byte.class, bufferViewByteBuffer, byteOffset, numElements, numComponentsPerElement, 1, byteStride);
/*     */     
/*  74 */     AccessorDatas.validateByteType(componentType);
/*  75 */     this.unsigned = AccessorDatas.isUnsignedType(componentType);
/*  76 */     AccessorDatas.validateCapacity(byteOffset, getNumElements(), 
/*  77 */         getByteStridePerElement(), bufferViewByteBuffer.capacity());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnsigned() {
/*  86 */     return this.unsigned;
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
/*     */   public byte get(int elementIndex, int componentIndex) {
/*  99 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 100 */     return getBufferViewByteBuffer().get(byteIndex);
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
/*     */   public byte get(int globalComponentIndex) {
/* 113 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 115 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 116 */     return get(elementIndex, componentIndex);
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
/*     */   public void set(int elementIndex, int componentIndex, byte value) {
/* 129 */     int byteIndex = getByteIndex(elementIndex, componentIndex);
/* 130 */     getBufferViewByteBuffer().put(byteIndex, value);
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
/*     */   public void set(int globalComponentIndex, byte value) {
/* 143 */     int elementIndex = globalComponentIndex / getNumComponentsPerElement();
/*     */     
/* 145 */     int componentIndex = globalComponentIndex % getNumComponentsPerElement();
/* 146 */     set(elementIndex, componentIndex, value);
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
/* 163 */     byte value = get(elementIndex, componentIndex);
/* 164 */     return this.unsigned ? Byte.toUnsignedInt(value) : value;
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
/* 179 */     byte value = get(globalComponentIndex);
/* 180 */     return this.unsigned ? Byte.toUnsignedInt(value) : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] computeMin() {
/* 191 */     byte[] result = new byte[getNumComponentsPerElement()];
/* 192 */     Arrays.fill(result, 127);
/* 193 */     for (int e = 0; e < getNumElements(); e++) {
/* 194 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 195 */         result[c] = (byte)Math.min(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 198 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] computeMax() {
/* 209 */     byte[] result = new byte[getNumComponentsPerElement()];
/* 210 */     Arrays.fill(result, -128);
/* 211 */     for (int e = 0; e < getNumElements(); e++) {
/* 212 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 213 */         result[c] = (byte)Math.max(result[c], get(e, c));
/*     */       }
/*     */     } 
/* 216 */     return result;
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
/* 228 */     int[] result = new int[getNumComponentsPerElement()];
/* 229 */     Arrays.fill(result, 2147483647);
/* 230 */     for (int e = 0; e < getNumElements(); e++) {
/* 231 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 232 */         result[c] = Math.min(result[c], getInt(e, c));
/*     */       }
/*     */     } 
/* 235 */     return result;
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
/* 247 */     int[] result = new int[getNumComponentsPerElement()];
/* 248 */     Arrays.fill(result, -2147483648);
/* 249 */     for (int e = 0; e < getNumElements(); e++) {
/* 250 */       for (int c = 0; c < getNumComponentsPerElement(); c++) {
/* 251 */         result[c] = Math.max(result[c], getInt(e, c));
/*     */       }
/*     */     } 
/* 254 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer createByteBuffer() {
/* 259 */     int totalNumComponents = getTotalNumComponents();
/* 260 */     int totalBytes = totalNumComponents * getNumBytesPerComponent();
/*     */     
/* 262 */     ByteBuffer result = ByteBuffer.allocateDirect(totalBytes).order(ByteOrder.nativeOrder());
/* 263 */     for (int i = 0; i < totalNumComponents; i++) {
/* 264 */       byte component = get(i);
/* 265 */       result.put(component);
/*     */     } 
/* 267 */     result.position(0);
/* 268 */     return result;
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
/* 282 */     StringBuilder sb = new StringBuilder();
/* 283 */     int nc = getNumComponentsPerElement();
/* 284 */     sb.append("[");
/* 285 */     for (int e = 0; e < getNumElements(); e++) {
/* 286 */       if (e > 0) {
/* 287 */         sb.append(", ");
/* 288 */         if (elementsPerRow > 0 && e % elementsPerRow == 0) {
/* 289 */           sb.append("\n ");
/*     */         }
/*     */       } 
/* 292 */       if (nc > 1) {
/* 293 */         sb.append("(");
/*     */       }
/* 295 */       for (int c = 0; c < nc; c++) {
/* 296 */         if (c > 0) {
/* 297 */           sb.append(", ");
/*     */         }
/* 299 */         int component = getInt(e, c);
/* 300 */         sb.append(String.format(locale, format, new Object[] { Integer.valueOf(component) }));
/*     */       } 
/* 302 */       if (nc > 1) {
/* 303 */         sb.append(")");
/*     */       }
/*     */     } 
/* 306 */     sb.append("]");
/* 307 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorByteData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */