/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Silence
/*     */ {
/*     */   @Deprecated
/*     */   @SerializedName("distance_addend")
/* 102 */   private int distanceAddend = 0;
/*     */   
/*     */   @Nullable
/*     */   @SerializedName("distance")
/* 106 */   private Modifier distance = null;
/*     */ 
/*     */   
/*     */   @SerializedName("use_silence_sound")
/*     */   private boolean useSilenceSound = false;
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getDistanceAddend() {
/* 115 */     return this.distanceAddend;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Modifier getDistance() {
/* 120 */     return this.distance;
/*     */   }
/*     */   
/*     */   public boolean isUseSilenceSound() {
/* 124 */     return this.useSilenceSound;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\SilenceModifier$Silence.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */