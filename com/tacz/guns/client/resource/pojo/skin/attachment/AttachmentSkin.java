/*    */ package com.tacz.guns.client.resource.pojo.skin.attachment;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class AttachmentSkin {
/*    */   @SerializedName("parent")
/*    */   private ResourceLocation parent;
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   @SerializedName("model")
/*    */   private ResourceLocation model;
/*    */   @SerializedName("texture")
/*    */   private ResourceLocation texture;
/*    */   
/*    */   public ResourceLocation getParent() {
/* 17 */     return this.parent;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 21 */     return this.name;
/*    */   }
/*    */   
/*    */   public ResourceLocation getModel() {
/* 25 */     return this.model;
/*    */   }
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 29 */     return this.texture;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\skin\attachment\AttachmentSkin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */