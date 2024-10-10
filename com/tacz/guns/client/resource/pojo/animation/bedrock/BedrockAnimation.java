/*    */ package com.tacz.guns.client.resource.pojo.animation.bedrock;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BedrockAnimation
/*    */ {
/*    */   @SerializedName("loop")
/*    */   private boolean loop;
/*    */   @SerializedName("animation_length")
/*    */   private double animationLength;
/*    */   @SerializedName("bones")
/*    */   @Nullable
/*    */   private Map<String, AnimationBone> bones;
/*    */   @SerializedName("sound_effects")
/*    */   private SoundEffectKeyframes soundEffects;
/*    */   
/*    */   public boolean isLoop() {
/* 23 */     return this.loop;
/*    */   }
/*    */   
/*    */   public double getAnimationLength() {
/* 27 */     return this.animationLength;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Map<String, AnimationBone> getBones() {
/* 32 */     return this.bones;
/*    */   }
/*    */   
/*    */   public SoundEffectKeyframes getSoundEffects() {
/* 36 */     return this.soundEffects;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\bedrock\BedrockAnimation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */