/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class MuzzleFlash {
/*    */   @SerializedName("texture")
/*  7 */   private ResourceLocation texture = null;
/*    */   
/*    */   @SerializedName("scale")
/* 10 */   private float scale = 1.0F;
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 14 */     return this.texture;
/*    */   }
/*    */   
/*    */   public float getScale() {
/* 18 */     return this.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\MuzzleFlash.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */