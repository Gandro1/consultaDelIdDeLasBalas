/*    */ package com.tacz.guns.api.client.animation.gltf;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BufferModel
/*    */ {
/*    */   private String uri;
/*    */   private ByteBuffer bufferData;
/*    */   
/*    */   public String getUri() {
/* 17 */     return this.uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setUri(String uri) {
/* 26 */     this.uri = uri;
/*    */   }
/*    */   
/*    */   public int getByteLength() {
/* 30 */     return this.bufferData.capacity();
/*    */   }
/*    */   
/*    */   public ByteBuffer getBufferData() {
/* 34 */     return Buffers.createSlice(this.bufferData);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBufferData(ByteBuffer bufferData) {
/* 43 */     this.bufferData = bufferData;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\BufferModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */