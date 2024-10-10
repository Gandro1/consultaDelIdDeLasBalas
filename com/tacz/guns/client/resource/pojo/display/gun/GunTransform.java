/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.client.resource.pojo.TransformScale;
/*    */ 
/*    */ public class GunTransform {
/*    */   @SerializedName("scale")
/*    */   private TransformScale scale;
/*    */   
/*    */   public static GunTransform getDefault() {
/* 11 */     GunTransform gunTransform = new GunTransform();
/* 12 */     gunTransform.scale = TransformScale.getGunDefault();
/* 13 */     return gunTransform;
/*    */   }
/*    */   
/*    */   public TransformScale getScale() {
/* 17 */     return this.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\GunTransform.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */