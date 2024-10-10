/*     */ package com.tacz.guns.api.client.animation.gltf;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Buffers
/*     */ {
/*     */   public static ByteBuffer createSlice(ByteBuffer byteBuffer) {
/*  53 */     if (byteBuffer == null) {
/*  54 */       return null;
/*     */     }
/*  56 */     return byteBuffer.slice().order(byteBuffer.order());
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
/*     */   
/*     */   public static ByteBuffer createSlice(ByteBuffer byteBuffer, int position, int length) {
/*  74 */     if (byteBuffer == null) {
/*  75 */       return null;
/*     */     }
/*  77 */     int oldPosition = byteBuffer.position();
/*  78 */     int oldLimit = byteBuffer.limit();
/*     */     try {
/*  80 */       int newLimit = position + length;
/*  81 */       if (newLimit > byteBuffer.capacity()) {
/*  82 */         throw new IllegalArgumentException("The new limit is " + newLimit + ", but the capacity is " + byteBuffer
/*     */             
/*  84 */             .capacity());
/*     */       }
/*  86 */       byteBuffer.limit(newLimit);
/*  87 */       byteBuffer.position(position);
/*  88 */       ByteBuffer slice = byteBuffer.slice();
/*  89 */       slice.order(byteBuffer.order());
/*  90 */       return slice;
/*     */     } finally {
/*  92 */       byteBuffer.limit(oldLimit);
/*  93 */       byteBuffer.position(oldPosition);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteBuffer create(byte[] data) {
/* 105 */     return create(data, 0, data.length);
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
/*     */   public static ByteBuffer create(byte[] data, int offset, int length) {
/* 118 */     ByteBuffer byteBuffer = ByteBuffer.allocateDirect(length);
/* 119 */     byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
/* 120 */     byteBuffer.put(data, offset, length);
/* 121 */     byteBuffer.position(0);
/* 122 */     return byteBuffer;
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
/*     */   public static ByteBuffer create(int size) {
/* 134 */     ByteBuffer byteBuffer = ByteBuffer.allocateDirect(size);
/* 135 */     byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
/* 136 */     return byteBuffer;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\Buffers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */