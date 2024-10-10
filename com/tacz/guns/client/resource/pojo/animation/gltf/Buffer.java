/*    */ package com.tacz.guns.client.resource.pojo.animation.gltf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Buffer
/*    */ {
/*    */   private String uri;
/*    */   private Integer byteLength;
/*    */   
/*    */   public String getUri() {
/* 20 */     return this.uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setUri(String uri) {
/* 29 */     if (uri == null) {
/* 30 */       this.uri = uri;
/*    */       return;
/*    */     } 
/* 33 */     this.uri = uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getByteLength() {
/* 43 */     return this.byteLength;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setByteLength(Integer byteLength) {
/* 56 */     if (byteLength == null) {
/* 57 */       throw new NullPointerException("Invalid value for byteLength: " + byteLength + ", may not be null");
/*    */     }
/* 59 */     if (byteLength.intValue() < 1) {
/* 60 */       throw new IllegalArgumentException("byteLength < 1");
/*    */     }
/* 62 */     this.byteLength = byteLength;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\Buffer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */