/*    */ package com.tacz.guns.resource.pojo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttachmentIndexPOJO
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
/*    */   private AttachmentType type;
/*    */   @SerializedName("hidden")
/*    */   private boolean hidden = false;
/*    */   
/*    */   public String getName() {
/* 30 */     return this.name;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getTooltip() {
/* 35 */     return this.tooltip;
/*    */   }
/*    */   
/*    */   public ResourceLocation getDisplay() {
/* 39 */     return this.display;
/*    */   }
/*    */   
/*    */   public ResourceLocation getData() {
/* 43 */     return this.data;
/*    */   }
/*    */   
/*    */   public AttachmentType getType() {
/* 47 */     return this.type;
/*    */   }
/*    */   
/*    */   public boolean isHidden() {
/* 51 */     return this.hidden;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\AttachmentIndexPOJO.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */