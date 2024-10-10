/*    */ package com.tacz.guns.resource.pojo;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AmmoIndexPOJO
/*    */ {
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   @SerializedName("display")
/*    */   private ResourceLocation display;
/*    */   @SerializedName("stack_size")
/*    */   private int stackSize;
/*    */   @SerializedName("tooltip")
/*    */   @Nullable
/*    */   private String tooltip;
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public ResourceLocation getDisplay() {
/* 27 */     return this.display;
/*    */   }
/*    */   
/*    */   public int getStackSize() {
/* 31 */     return this.stackSize;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getTooltip() {
/* 36 */     return this.tooltip;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\AmmoIndexPOJO.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */