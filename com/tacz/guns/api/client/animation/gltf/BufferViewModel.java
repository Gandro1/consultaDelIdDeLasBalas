/*     */ package com.tacz.guns.api.client.animation.gltf;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferViewModel
/*     */ {
/*     */   private final Integer target;
/*     */   private BufferModel bufferModel;
/*     */   private int byteOffset;
/*     */   private int byteLength;
/*     */   private Integer byteStride;
/*     */   private Consumer<? super ByteBuffer> sparseSubstitutionCallback;
/*     */   private boolean sparseSubstitutionApplied;
/*     */   
/*     */   public BufferViewModel(Integer target) {
/*  46 */     this.byteOffset = 0;
/*  47 */     this.byteLength = 0;
/*  48 */     this.target = target;
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
/*     */   public void setSparseSubstitutionCallback(Consumer<? super ByteBuffer> sparseSubstitutionCallback) {
/*  60 */     this.sparseSubstitutionCallback = sparseSubstitutionCallback;
/*     */   }
/*     */   
/*     */   public ByteBuffer getBufferViewData() {
/*  64 */     ByteBuffer bufferData = this.bufferModel.getBufferData();
/*     */     
/*  66 */     ByteBuffer bufferViewData = Buffers.createSlice(bufferData, getByteOffset(), getByteLength());
/*  67 */     if (this.sparseSubstitutionCallback != null && !this.sparseSubstitutionApplied) {
/*  68 */       this.sparseSubstitutionCallback.accept(bufferViewData);
/*  69 */       this.sparseSubstitutionApplied = true;
/*     */     } 
/*  71 */     return bufferViewData;
/*     */   }
/*     */   
/*     */   public BufferModel getBufferModel() {
/*  75 */     return this.bufferModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBufferModel(BufferModel bufferModel) {
/*  84 */     this.bufferModel = bufferModel;
/*     */   }
/*     */   
/*     */   public int getByteOffset() {
/*  88 */     return this.byteOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteOffset(int byteOffset) {
/*  97 */     this.byteOffset = byteOffset;
/*     */   }
/*     */   
/*     */   public int getByteLength() {
/* 101 */     return this.byteLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteLength(int byteLength) {
/* 110 */     this.byteLength = byteLength;
/*     */   }
/*     */   
/*     */   public Integer getByteStride() {
/* 114 */     return this.byteStride;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteStride(Integer byteStride) {
/* 125 */     this.byteStride = byteStride;
/*     */   }
/*     */   
/*     */   public Integer getTarget() {
/* 129 */     return this.target;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\BufferViewModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */