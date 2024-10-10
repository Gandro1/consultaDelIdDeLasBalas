/*    */ package com.tacz.guns.client.resource.pojo.display.ammo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.client.resource.pojo.TransformScale;
/*    */ 
/*    */ public class AmmoTransform {
/*    */   @SerializedName("scale")
/*    */   private TransformScale scale;
/*    */   
/*    */   public static AmmoTransform getDefault() {
/* 11 */     AmmoTransform ammoTransform = new AmmoTransform();
/* 12 */     ammoTransform.scale = TransformScale.getAmmoDefault();
/* 13 */     return ammoTransform;
/*    */   }
/*    */   
/*    */   public TransformScale getScale() {
/* 17 */     return this.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\ammo\AmmoTransform.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */