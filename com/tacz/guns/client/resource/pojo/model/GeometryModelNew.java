/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class GeometryModelNew
/*    */ {
/*    */   @SerializedName("description")
/*    */   private Description description;
/*    */   @SerializedName("bones")
/*    */   @Nullable
/*    */   private List<BonesItem> bones;
/*    */   
/*    */   public Description getDescription() {
/* 17 */     return this.description;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BonesItem> getBones() {
/* 22 */     return this.bones;
/*    */   }
/*    */   
/*    */   public GeometryModelNew deco() {
/* 26 */     if (this.bones != null) {
/* 27 */       this.bones.forEach(bonesItem -> {
/*    */             if (bonesItem.getCubes() != null) {
/*    */               bonesItem.getCubes().forEach(());
/*    */             }
/*    */           });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\GeometryModelNew.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */