/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Description
/*    */ {
/*    */   @SerializedName("texture_height")
/*    */   private int textureHeight;
/*    */   @SerializedName("texture_width")
/*    */   private int textureWidth;
/*    */   @SerializedName("visible_bounds_height")
/*    */   private float visibleBoundsHeight;
/*    */   @SerializedName("visible_bounds_width")
/*    */   private float visibleBoundsWidth;
/*    */   @SerializedName("visible_bounds_offset")
/*    */   private List<Float> visibleBoundsOffset;
/*    */   
/*    */   public int getTextureHeight() {
/* 24 */     return this.textureHeight;
/*    */   }
/*    */   
/*    */   public int getTextureWidth() {
/* 28 */     return this.textureWidth;
/*    */   }
/*    */   
/*    */   public float getVisibleBoundsHeight() {
/* 32 */     return this.visibleBoundsHeight;
/*    */   }
/*    */   
/*    */   public float getVisibleBoundsWidth() {
/* 36 */     return this.visibleBoundsWidth;
/*    */   }
/*    */   
/*    */   public List<Float> getVisibleBoundsOffset() {
/* 40 */     return this.visibleBoundsOffset;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\Description.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */