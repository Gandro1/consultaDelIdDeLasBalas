/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class LayerGunShow {
/*    */   @SerializedName("pos")
/*  7 */   private Vector3f pos = new Vector3f(-2.0F, 20.0F, 4.0F);
/*    */   
/*    */   @SerializedName("rotate")
/* 10 */   private Vector3f rotate = new Vector3f(0.0F, 0.0F, -30.0F);
/*    */   
/*    */   @SerializedName("scale")
/* 13 */   private Vector3f scale = new Vector3f(0.6F, 0.6F, 0.6F);
/*    */ 
/*    */   
/*    */   public Vector3f getPos() {
/* 17 */     return this.pos;
/*    */   }
/*    */   
/*    */   public Vector3f getRotate() {
/* 21 */     return this.rotate;
/*    */   }
/*    */   
/*    */   public Vector3f getScale() {
/* 25 */     return this.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\LayerGunShow.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */