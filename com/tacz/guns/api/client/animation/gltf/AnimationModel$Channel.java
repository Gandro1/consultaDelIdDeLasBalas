/*     */ package com.tacz.guns.api.client.animation.gltf;
/*     */ 
/*     */ import java.util.Objects;
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
/*     */ public final class Channel
/*     */   extends Record
/*     */ {
/*     */   private final AnimationModel.Sampler sampler;
/*     */   private final NodeModel nodeModel;
/*     */   private final String path;
/*     */   
/*     */   public String path() {
/*  87 */     return this.path; } public NodeModel nodeModel() { return this.nodeModel; } public AnimationModel.Sampler sampler() { return this.sampler; }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #87	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #87	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;
/*     */   }
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #87	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/tacz/guns/api/client/animation/gltf/AnimationModel$Channel;
/*     */   }
/*     */   
/*     */   public Channel(AnimationModel.Sampler sampler, NodeModel nodeModel, String path) {
/* 100 */     this.sampler = Objects.<AnimationModel.Sampler>requireNonNull(sampler, "The sampler may not be null");
/*     */     
/* 102 */     this.nodeModel = nodeModel;
/* 103 */     this.path = Objects.<String>requireNonNull(path, "The path may not be null");
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\AnimationModel$Channel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */