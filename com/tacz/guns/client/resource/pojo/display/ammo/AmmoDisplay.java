/*    */ package com.tacz.guns.client.resource.pojo.display.ammo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AmmoDisplay
/*    */ {
/*    */   @SerializedName("model")
/*    */   private ResourceLocation modelLocation;
/*    */   @SerializedName("texture")
/*    */   private ResourceLocation modelTexture;
/*    */   @Nullable
/*    */   @SerializedName("slot")
/*    */   private ResourceLocation slotTextureLocation;
/*    */   @Nullable
/*    */   @SerializedName("entity")
/*    */   private AmmoEntityDisplay ammoEntity;
/*    */   @Nullable
/*    */   @SerializedName("shell")
/*    */   private ShellDisplay shellDisplay;
/*    */   @Nullable
/*    */   @SerializedName("particle")
/*    */   private AmmoParticle particle;
/*    */   @SerializedName("tracer_color")
/* 31 */   private String tracerColor = "0xFFFFFF";
/*    */   
/*    */   @Nullable
/*    */   @SerializedName("transform")
/*    */   private AmmoTransform transform;
/*    */ 
/*    */   
/*    */   public ResourceLocation getModelLocation() {
/* 39 */     return this.modelLocation;
/*    */   }
/*    */   
/*    */   public ResourceLocation getModelTexture() {
/* 43 */     return this.modelTexture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getSlotTextureLocation() {
/* 48 */     return this.slotTextureLocation;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AmmoEntityDisplay getAmmoEntity() {
/* 53 */     return this.ammoEntity;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ShellDisplay getShellDisplay() {
/* 58 */     return this.shellDisplay;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AmmoParticle getParticle() {
/* 63 */     return this.particle;
/*    */   }
/*    */   
/*    */   public String getTracerColor() {
/* 67 */     return this.tracerColor;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AmmoTransform getTransform() {
/* 72 */     return this.transform;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\ammo\AmmoDisplay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */