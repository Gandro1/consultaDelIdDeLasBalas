/*    */ package com.tacz.guns.resource.pojo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GunIndexPOJO
/*    */ {
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   @SerializedName("tooltip")
/*    */   @Nullable
/*    */   private String tooltip;
/*    */   @SerializedName("display")
/*    */   private ResourceLocation display;
/*    */   @SerializedName("data")
/*    */   private ResourceLocation data;
/*    */   @SerializedName("type")
/*    */   private String type;
/*    */   @SerializedName("item_type")
/* 25 */   private String itemType = "modern_kinetic";
/*    */   
/*    */   @SerializedName("sort")
/*    */   private int sort;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return this.name;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getTooltip() {
/* 37 */     return this.tooltip;
/*    */   }
/*    */   
/*    */   public ResourceLocation getDisplay() {
/* 41 */     return this.display;
/*    */   }
/*    */   
/*    */   public ResourceLocation getData() {
/* 45 */     return this.data;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 49 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getItemType() {
/* 53 */     return this.itemType;
/*    */   }
/*    */   
/*    */   public int getSort() {
/* 57 */     return this.sort;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\GunIndexPOJO.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */