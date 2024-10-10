/*    */ package com.tacz.guns.api.client.animation;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimationChannelContent
/*    */ {
/*    */   public float[] keyframeTimeS;
/*    */   public float[][] values;
/*    */   public LerpMode[] lerpModes;
/*    */   
/*    */   public AnimationChannelContent() {}
/*    */   
/*    */   public AnimationChannelContent(AnimationChannelContent source) {
/* 22 */     if (source.keyframeTimeS != null) {
/* 23 */       this.keyframeTimeS = Arrays.copyOf(source.keyframeTimeS, source.keyframeTimeS.length);
/*    */     }
/* 25 */     if (source.values != null)
/*    */     {
/* 27 */       this
/*    */         
/* 29 */         .values = (float[][])Arrays.<float[]>stream(source.values).map(values -> Arrays.copyOf(values, values.length)).toArray(x$0 -> new float[x$0][]);
/*    */     }
/* 31 */     if (source.lerpModes != null)
/* 32 */       this.lerpModes = Arrays.<LerpMode>copyOf(source.lerpModes, source.lerpModes.length); 
/*    */   }
/*    */   
/*    */   public enum LerpMode
/*    */   {
/* 37 */     LINEAR, SPHERICAL_LINEAR, CATMULLROM, SPHERICAL_SQUAD;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\AnimationChannelContent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */