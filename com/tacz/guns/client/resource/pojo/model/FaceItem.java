/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class FaceItem {
/*  6 */   public static final FaceItem EMPTY = empty();
/*    */   
/*    */   @SerializedName("uv")
/*    */   private float[] uv;
/*    */   
/*    */   @SerializedName("uv_size")
/*    */   private float[] uvSize;
/*    */   
/*    */   public static FaceItem single16X() {
/* 15 */     FaceItem face = new FaceItem();
/* 16 */     face.uv = new float[] { 0.0F, 0.0F };
/* 17 */     face.uvSize = new float[] { 16.0F, 16.0F };
/* 18 */     return face;
/*    */   }
/*    */   
/*    */   private static FaceItem empty() {
/* 22 */     FaceItem face = new FaceItem();
/* 23 */     face.uv = new float[] { 0.0F, 0.0F };
/* 24 */     face.uvSize = new float[] { 0.0F, 0.0F };
/* 25 */     return face;
/*    */   }
/*    */   
/*    */   public float[] getUv() {
/* 29 */     return this.uv;
/*    */   }
/*    */   
/*    */   public float[] getUvSize() {
/* 33 */     return this.uvSize;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\FaceItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */