/*    */ package com.tacz.guns.client.resource.pojo.display.attachment;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttachmentDisplay
/*    */ {
/*    */   @SerializedName("slot")
/*    */   private ResourceLocation slotTextureLocation;
/*    */   @SerializedName("model")
/*    */   private ResourceLocation model;
/*    */   @SerializedName("texture")
/*    */   private ResourceLocation texture;
/*    */   @SerializedName("lod")
/*    */   @Nullable
/*    */   private AttachmentLod attachmentLod;
/*    */   @SerializedName("adapter")
/*    */   @Nullable
/*    */   private String adapterNodeName;
/*    */   @SerializedName("show_muzzle")
/*    */   private boolean showMuzzle = false;
/*    */   @SerializedName("zoom")
/*    */   @Nullable
/*    */   private float[] zoom;
/*    */   @SerializedName("scope")
/*    */   private boolean isScope = false;
/*    */   @SerializedName("sight")
/*    */   private boolean isSight = false;
/*    */   @SerializedName("fov")
/* 41 */   private float fov = 70.0F;
/*    */ 
/*    */   
/*    */   @SerializedName("sounds")
/* 45 */   private Map<String, ResourceLocation> sounds = Maps.newHashMap();
/*    */   
/*    */   public ResourceLocation getSlotTextureLocation() {
/* 48 */     return this.slotTextureLocation;
/*    */   }
/*    */   
/*    */   public ResourceLocation getModel() {
/* 52 */     return this.model;
/*    */   }
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 56 */     return this.texture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AttachmentLod getAttachmentLod() {
/* 61 */     return this.attachmentLod;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getAdapterNodeName() {
/* 66 */     return this.adapterNodeName;
/*    */   }
/*    */   
/*    */   public boolean isShowMuzzle() {
/* 70 */     return this.showMuzzle;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public float[] getZoom() {
/* 75 */     return this.zoom;
/*    */   }
/*    */   
/*    */   public boolean isScope() {
/* 79 */     return this.isScope;
/*    */   }
/*    */   
/*    */   public boolean isSight() {
/* 83 */     return this.isSight;
/*    */   }
/*    */   
/*    */   public float getFov() {
/* 87 */     return this.fov;
/*    */   }
/*    */   
/*    */   public Map<String, ResourceLocation> getSounds() {
/* 91 */     return this.sounds;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\attachment\AttachmentDisplay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */