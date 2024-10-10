/*    */ package com.tacz.guns.api.client.animation;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class AnimationSoundChannelContent
/*    */ {
/*    */   public double[] keyframeTimeS;
/*    */   public ResourceLocation[] keyframeSoundName;
/*    */   
/*    */   public AnimationSoundChannelContent() {}
/*    */   
/*    */   public AnimationSoundChannelContent(AnimationSoundChannelContent source) {
/* 15 */     if (source.keyframeTimeS != null) {
/* 16 */       this.keyframeTimeS = Arrays.copyOf(source.keyframeTimeS, source.keyframeTimeS.length);
/*    */     }
/* 18 */     if (source.keyframeSoundName != null)
/* 19 */       this.keyframeSoundName = Arrays.<ResourceLocation>copyOf(source.keyframeSoundName, source.keyframeSoundName.length); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\AnimationSoundChannelContent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */