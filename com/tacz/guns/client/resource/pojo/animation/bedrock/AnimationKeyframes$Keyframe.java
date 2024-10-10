/*    */ package com.tacz.guns.client.resource.pojo.animation.bedrock;
/*    */ public final class Keyframe extends Record { @Nullable
/*    */   private final Vector3f pre; @Nullable
/*    */   private final Vector3f post; @Nullable
/*    */   private final Vector3f data;
/*    */   @Nullable
/*    */   private final String lerpMode;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/tacz/guns/client/resource/pojo/animation/bedrock/AnimationKeyframes$Keyframe;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/* 21 */   public Keyframe(@Nullable Vector3f pre, @Nullable Vector3f post, @Nullable Vector3f data, @Nullable String lerpMode) { this.pre = pre; this.post = post; this.data = data; this.lerpMode = lerpMode; } @Nullable public Vector3f pre() { return this.pre; } @Nullable public Vector3f post() { return this.post; } @Nullable public Vector3f data() { return this.data; } @Nullable public String lerpMode() { return this.lerpMode; }
/*    */    }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\bedrock\AnimationKeyframes$Keyframe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */