/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeometryModelLegacy
/*    */ {
/*    */   @SerializedName("bones")
/*    */   @Nullable
/*    */   private List<BonesItem> bones;
/*    */   @SerializedName("textureheight")
/*    */   private int textureHeight;
/*    */   @SerializedName("texturewidth")
/*    */   private int textureWidth;
/*    */   @SerializedName("visible_bounds_height")
/*    */   private float visibleBoundsHeight;
/*    */   @SerializedName("visible_bounds_width")
/*    */   private float visibleBoundsWidth;
/*    */   @SerializedName("visible_bounds_offset")
/*    */   private List<Float> visibleBoundsOffset;
/*    */   
/*    */   @Nullable
/*    */   public List<BonesItem> getBones() {
/* 30 */     return this.bones;
/*    */   }
/*    */   
/*    */   public int getTextureHeight() {
/* 34 */     return this.textureHeight;
/*    */   }
/*    */   
/*    */   public int getTextureWidth() {
/* 38 */     return this.textureWidth;
/*    */   }
/*    */   
/*    */   public float getVisibleBoundsHeight() {
/* 42 */     return this.visibleBoundsHeight;
/*    */   }
/*    */   
/*    */   public float getVisibleBoundsWidth() {
/* 46 */     return this.visibleBoundsWidth;
/*    */   }
/*    */   
/*    */   public List<Float> getVisibleBoundsOffset() {
/* 50 */     return this.visibleBoundsOffset;
/*    */   }
/*    */   
/*    */   public GeometryModelLegacy deco() {
/* 54 */     if (this.bones != null) {
/* 55 */       this.bones.forEach(bonesItem -> {
/*    */             if (bonesItem.getCubes() != null) {
/*    */               bonesItem.getCubes().forEach(());
/*    */             }
/*    */           });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 65 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\GeometryModelLegacy.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */