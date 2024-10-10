/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BedrockModelPOJO
/*    */ {
/*    */   @SerializedName("format_version")
/*    */   private String formatVersion;
/*    */   @SerializedName("geometry.model")
/*    */   @Nullable
/*    */   private GeometryModelLegacy geometryModelLegacy;
/*    */   @SerializedName("minecraft:geometry")
/*    */   @Nullable
/*    */   private List<GeometryModelNew> geometryModelNew;
/*    */   
/*    */   public String getFormatVersion() {
/* 21 */     return this.formatVersion;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public GeometryModelLegacy getGeometryModelLegacy() {
/* 26 */     return this.geometryModelLegacy;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public GeometryModelNew getGeometryModelNew() {
/* 31 */     if (this.geometryModelNew == null) {
/* 32 */       return null;
/*    */     }
/* 34 */     return this.geometryModelNew.get(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\BedrockModelPOJO.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */