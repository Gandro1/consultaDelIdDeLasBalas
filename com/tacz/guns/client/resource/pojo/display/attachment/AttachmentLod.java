/*    */ package com.tacz.guns.client.resource.pojo.display.attachment;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class AttachmentLod {
/*    */   @SerializedName("model")
/*    */   private ResourceLocation modelLocation;
/*    */   @SerializedName("texture")
/*    */   private ResourceLocation modelTexture;
/*    */   
/*    */   public ResourceLocation getModelLocation() {
/* 13 */     return this.modelLocation;
/*    */   }
/*    */   
/*    */   public ResourceLocation getModelTexture() {
/* 17 */     return this.modelTexture;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\attachment\AttachmentLod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */