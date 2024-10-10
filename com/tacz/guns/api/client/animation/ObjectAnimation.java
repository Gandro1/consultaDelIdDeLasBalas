/*     */ package com.tacz.guns.api.client.animation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectAnimation
/*     */ {
/*     */   public final String name;
/*  20 */   private final Map<String, List<ObjectAnimationChannel>> channels = new HashMap<>();
/*     */   
/*     */   @Nullable
/*     */   private ObjectAnimationSoundChannel soundChannel;
/*     */   @Nonnull
/*  25 */   public PlayType playType = PlayType.PLAY_ONCE_HOLD;
/*     */ 
/*     */ 
/*     */   
/*  29 */   private float maxEndTimeS = 0.0F;
/*     */   
/*     */   protected ObjectAnimation(@Nonnull String name) {
/*  32 */     this.name = Objects.<String>requireNonNull(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectAnimation(ObjectAnimation source) {
/*  41 */     this.name = source.name;
/*  42 */     this.playType = source.playType;
/*  43 */     this.maxEndTimeS = source.maxEndTimeS;
/*  44 */     for (Map.Entry<String, List<ObjectAnimationChannel>> entry : source.channels.entrySet()) {
/*  45 */       List<ObjectAnimationChannel> newList = new ArrayList<>();
/*  46 */       for (ObjectAnimationChannel channel : entry.getValue()) {
/*  47 */         ObjectAnimationChannel newChannel = new ObjectAnimationChannel(channel.type, channel.content);
/*  48 */         newChannel.node = channel.node;
/*  49 */         newChannel.interpolator = channel.interpolator;
/*  50 */         newList.add(newChannel);
/*     */       } 
/*  52 */       this.channels.put(entry.getKey(), newList);
/*     */     } 
/*  54 */     if (source.soundChannel != null) {
/*  55 */       this.soundChannel = new ObjectAnimationSoundChannel(source.soundChannel.content);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addChannel(ObjectAnimationChannel channel) {
/*  60 */     this.channels.compute(channel.node, (node, list) -> {
/*     */           if (list == null) {
/*     */             list = new ArrayList();
/*     */           }
/*     */           list.add(channel);
/*     */           return list;
/*     */         });
/*  67 */     if (channel.getEndTimeS() > this.maxEndTimeS) {
/*  68 */       this.maxEndTimeS = channel.getEndTimeS();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setSoundChannel(@Nonnull ObjectAnimationSoundChannel soundChannel) {
/*  73 */     if (soundChannel.getEndTimeS() > this.maxEndTimeS) {
/*  74 */       this.maxEndTimeS = (float)soundChannel.getEndTimeS();
/*     */     }
/*  76 */     this.soundChannel = soundChannel;
/*     */   }
/*     */   
/*     */   public Map<String, List<ObjectAnimationChannel>> getChannels() {
/*  80 */     return this.channels;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ObjectAnimationSoundChannel getSoundChannel() {
/*  85 */     return this.soundChannel;
/*     */   }
/*     */   
/*     */   public void applyAnimationListeners(AnimationListenerSupplier supplier) {
/*  89 */     for (List<ObjectAnimationChannel> channelList : this.channels.values()) {
/*  90 */       for (ObjectAnimationChannel channel : channelList) {
/*  91 */         AnimationListener listener = supplier.supplyListeners(channel.node, channel.type);
/*  92 */         if (listener != null) {
/*  93 */           channel.addListener(listener);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(boolean blend, float fromTimeNs, float toTimeNs) {
/* 103 */     for (List<ObjectAnimationChannel> channels : this.channels.values()) {
/* 104 */       for (ObjectAnimationChannel channel : channels) {
/* 105 */         channel.update(fromTimeNs / 1.0E9F, blend);
/*     */       }
/*     */     } 
/* 108 */     if ((Minecraft.m_91087_()).f_91074_ != null && this.soundChannel != null)
/*     */     {
/* 110 */       this.soundChannel.playSound(fromTimeNs / 1.0E9D, toTimeNs / 1.0E9D, (Entity)(Minecraft.m_91087_()).f_91074_, 16, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getMaxEndTimeS() {
/* 115 */     return this.maxEndTimeS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PlayType
/*     */   {
/* 122 */     PLAY_ONCE_HOLD,
/*     */ 
/*     */ 
/*     */     
/* 126 */     PLAY_ONCE_STOP,
/*     */ 
/*     */ 
/*     */     
/* 130 */     LOOP;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\ObjectAnimation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */