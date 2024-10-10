/*    */ package com.tacz.guns.api.client.animation;
/*    */ 
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class ObjectAnimationSoundChannel
/*    */ {
/*    */   public AnimationSoundChannelContent content;
/*    */   
/*    */   public ObjectAnimationSoundChannel() {}
/*    */   
/*    */   public ObjectAnimationSoundChannel(AnimationSoundChannelContent content) {
/* 18 */     this.content = content;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void playSound(double fromTimeS, double toTimeS, Entity entity, int distance, float volume, float pitch) {
/* 25 */     if (this.content == null) {
/*    */       return;
/*    */     }
/* 28 */     if (fromTimeS == toTimeS) {
/*    */       return;
/*    */     }
/* 31 */     if (fromTimeS > toTimeS && fromTimeS <= getEndTimeS()) {
/* 32 */       playSound(0.0D, toTimeS, entity, distance, volume, pitch);
/* 33 */       toTimeS = getEndTimeS();
/*    */     } 
/* 35 */     int to = computeIndex(toTimeS, false);
/* 36 */     int from = computeIndex(fromTimeS, true);
/* 37 */     float mixVolume = volume;
/*    */     
/* 39 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 40 */     if (player != null) {
/* 41 */       mixVolume *= 1.0F - Math.min(1.0F, (float)Math.sqrt(player.m_20238_(entity.m_20318_(0.0F))) / distance);
/* 42 */       mixVolume *= mixVolume;
/*    */     } 
/* 44 */     for (int i = from + 1; i <= to; i++) {
/* 45 */       ResourceLocation name = this.content.keyframeSoundName[i];
/* 46 */       SoundPlayManager.playClientSound(entity, name, mixVolume, pitch, distance);
/*    */     } 
/*    */   }
/*    */   
/*    */   public double getEndTimeS() {
/* 51 */     return this.content.keyframeTimeS[this.content.keyframeTimeS.length - 1];
/*    */   }
/*    */   
/*    */   private int computeIndex(double timeS, boolean open) {
/* 55 */     int index = Arrays.binarySearch(this.content.keyframeTimeS, timeS);
/* 56 */     if (index >= 0) {
/* 57 */       return open ? (index - 1) : index;
/*    */     }
/* 59 */     return -index - 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\ObjectAnimationSoundChannel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */