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
/*    */ 
/*    */ public class BonesItem
/*    */ {
/*    */   @SerializedName("cubes")
/*    */   private List<CubesItem> cubes;
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   @SerializedName("pivot")
/*    */   private List<Float> pivot;
/*    */   @SerializedName("rotation")
/*    */   private List<Float> rotation;
/*    */   @SerializedName("parent")
/*    */   private String parent;
/*    */   @SerializedName("mirror")
/*    */   private boolean mirror = false;
/*    */   
/*    */   @Nullable
/*    */   public List<CubesItem> getCubes() {
/* 30 */     return this.cubes;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 34 */     return this.name;
/*    */   }
/*    */   
/*    */   public List<Float> getPivot() {
/* 38 */     return this.pivot;
/*    */   }
/*    */   
/*    */   public List<Float> getRotation() {
/* 42 */     return this.rotation;
/*    */   }
/*    */   
/*    */   public String getParent() {
/* 46 */     return this.parent;
/*    */   }
/*    */   
/*    */   public boolean isMirror() {
/* 50 */     return this.mirror;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "BonesItem{cubes = '" + this.cubes + "',name = '" + this.name + "',pivot = '" + this.pivot + "',rotation = '" + this.rotation + "',parent = '" + this.parent + "'}";
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\BonesItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */