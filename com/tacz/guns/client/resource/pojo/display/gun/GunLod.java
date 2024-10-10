/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class GunLod {
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


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\GunLod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */