/*     */ package com.tacz.guns.client.resource.pojo.animation.gltf;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class Animation
/*     */ {
/*     */   private String name;
/*     */   private List<AnimationChannel> channels;
/*     */   private List<AnimationSampler> samplers;
/*     */   
/*     */   public List<AnimationChannel> getChannels() {
/*  44 */     return this.channels;
/*     */   }
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
/*     */   public void setChannels(List<AnimationChannel> channels) {
/*  63 */     if (channels == null) {
/*  64 */       throw new NullPointerException("Invalid value for channels: " + channels + ", may not be null");
/*     */     }
/*  66 */     if (channels.size() < 1) {
/*  67 */       throw new IllegalArgumentException("Number of channels elements is < 1");
/*     */     }
/*  69 */     this.channels = channels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChannels(AnimationChannel element) {
/*  81 */     if (element == null) {
/*  82 */       throw new NullPointerException("The element may not be null");
/*     */     }
/*  84 */     List<AnimationChannel> oldList = this.channels;
/*  85 */     List<AnimationChannel> newList = new ArrayList<>();
/*  86 */     if (oldList != null) {
/*  87 */       newList.addAll(oldList);
/*     */     }
/*  89 */     newList.add(element);
/*  90 */     this.channels = newList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeChannels(AnimationChannel element) {
/* 102 */     if (element == null) {
/* 103 */       throw new NullPointerException("The element may not be null");
/*     */     }
/* 105 */     List<AnimationChannel> oldList = this.channels;
/* 106 */     List<AnimationChannel> newList = new ArrayList<>();
/* 107 */     if (oldList != null) {
/* 108 */       newList.addAll(oldList);
/*     */     }
/* 110 */     newList.remove(element);
/* 111 */     this.channels = newList;
/*     */   }
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
/*     */   public List<AnimationSampler> getSamplers() {
/* 126 */     return this.samplers;
/*     */   }
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
/*     */   public void setSamplers(List<AnimationSampler> samplers) {
/* 144 */     if (samplers == null) {
/* 145 */       throw new NullPointerException("Invalid value for samplers: " + samplers + ", may not be null");
/*     */     }
/* 147 */     if (samplers.size() < 1) {
/* 148 */       throw new IllegalArgumentException("Number of samplers elements is < 1");
/*     */     }
/* 150 */     this.samplers = samplers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSamplers(AnimationSampler element) {
/* 162 */     if (element == null) {
/* 163 */       throw new NullPointerException("The element may not be null");
/*     */     }
/* 165 */     List<AnimationSampler> oldList = this.samplers;
/* 166 */     List<AnimationSampler> newList = new ArrayList<>();
/* 167 */     if (oldList != null) {
/* 168 */       newList.addAll(oldList);
/*     */     }
/* 170 */     newList.add(element);
/* 171 */     this.samplers = newList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSamplers(AnimationSampler element) {
/* 183 */     if (element == null) {
/* 184 */       throw new NullPointerException("The element may not be null");
/*     */     }
/* 186 */     List<AnimationSampler> oldList = this.samplers;
/* 187 */     List<AnimationSampler> newList = new ArrayList<>();
/* 188 */     if (oldList != null) {
/* 189 */       newList.addAll(oldList);
/*     */     }
/* 191 */     newList.remove(element);
/* 192 */     this.samplers = newList;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 196 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 200 */     this.name = name;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\Animation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */