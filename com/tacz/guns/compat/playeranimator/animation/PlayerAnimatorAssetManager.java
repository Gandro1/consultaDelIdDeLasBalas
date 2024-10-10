/*    */ package com.tacz.guns.compat.playeranimator.animation;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
/*    */ import dev.kosmx.playerAnim.core.data.gson.AnimationSerializing;
/*    */ import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public enum PlayerAnimatorAssetManager {
/*    */   private final HashMap<ResourceLocation, HashMap<String, KeyframeAnimation>> animations;
/* 17 */   INSTANCE;
/*    */   PlayerAnimatorAssetManager() {
/* 19 */     this.animations = new HashMap<>();
/*    */   }
/*    */   void putAnimation(ResourceLocation id, InputStream stream) throws IOException {
/* 22 */     List<KeyframeAnimation> keyframeAnimations = AnimationSerializing.deserializeAnimation(stream);
/* 23 */     for (KeyframeAnimation animation : keyframeAnimations) {
/* 24 */       Object object = animation.extraData.get("name"); if (object instanceof String) { String text = (String)object;
/* 25 */         String name = PlayerAnimationRegistry.serializeTextToString(text).toLowerCase(Locale.ENGLISH);
/* 26 */         ((HashMap<String, KeyframeAnimation>)this.animations.computeIfAbsent(id, k -> Maps.newHashMap())).put(name, animation); }
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   Optional<KeyframeAnimation> getAnimations(ResourceLocation id, String name) {
/* 32 */     HashMap<String, KeyframeAnimation> animationHashMap = this.animations.get(id);
/* 33 */     if (animationHashMap == null) {
/* 34 */       return Optional.empty();
/*    */     }
/* 36 */     return Optional.ofNullable(animationHashMap.get(name));
/*    */   }
/*    */   
/*    */   public boolean containsKey(ResourceLocation id) {
/* 40 */     return this.animations.containsKey(id);
/*    */   }
/*    */   
/*    */   public void clearAll() {
/* 44 */     this.animations.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\animation\PlayerAnimatorAssetManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */