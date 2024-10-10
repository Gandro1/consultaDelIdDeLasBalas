/*    */ package com.tacz.guns.client.resource.pojo.animation.bedrock;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.Double2ObjectRBTreeMap;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class SoundEffectKeyframes {
/*    */   private final Double2ObjectRBTreeMap<ResourceLocation> keyframes;
/*    */   
/*    */   public SoundEffectKeyframes(Double2ObjectRBTreeMap<ResourceLocation> keyframes) {
/* 10 */     this.keyframes = keyframes;
/*    */   }
/*    */   
/*    */   public Double2ObjectRBTreeMap<ResourceLocation> getKeyframes() {
/* 14 */     return this.keyframes;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\bedrock\SoundEffectKeyframes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */