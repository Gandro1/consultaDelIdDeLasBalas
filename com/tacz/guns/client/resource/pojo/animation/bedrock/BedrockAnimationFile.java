/*    */ package com.tacz.guns.client.resource.pojo.animation.bedrock;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class BedrockAnimationFile
/*    */ {
/*    */   @SerializedName("version")
/*    */   private String version;
/*    */   @SerializedName("animations")
/*    */   private Map<String, BedrockAnimation> animations;
/*    */   
/*    */   public String getVersion() {
/* 15 */     return this.version;
/*    */   }
/*    */   
/*    */   public Map<String, BedrockAnimation> getAnimations() {
/* 19 */     return this.animations;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\bedrock\BedrockAnimationFile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */