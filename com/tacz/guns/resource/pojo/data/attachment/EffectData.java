/*    */ package com.tacz.guns.resource.pojo.data.attachment;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class EffectData {
/*    */   @SerializedName("id")
/*    */   @Nullable
/*    */   private ResourceLocation effectId;
/*    */   @SerializedName("time")
/* 12 */   private int time = 0;
/*    */   
/*    */   @SerializedName("amplifier")
/* 15 */   private int amplifier = 0;
/*    */   
/*    */   @SerializedName("hide_particles")
/*    */   private boolean hideParticles = false;
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getEffectId() {
/* 23 */     return this.effectId;
/*    */   }
/*    */   
/*    */   public int getTime() {
/* 27 */     return this.time;
/*    */   }
/*    */   
/*    */   public int getAmplifier() {
/* 31 */     return this.amplifier;
/*    */   }
/*    */   
/*    */   public boolean isHideParticles() {
/* 35 */     return this.hideParticles;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\attachment\EffectData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */