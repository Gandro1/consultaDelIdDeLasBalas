/*    */ package com.tacz.guns.client.model.bedrock;
/*    */ 
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class BedrockVertex {
/*    */   public final Vector3f pos;
/*    */   public final float u;
/*    */   public final float v;
/*    */   
/*    */   public BedrockVertex(float x, float y, float z, float u, float v) {
/* 11 */     this(new Vector3f(x, y, z), u, v);
/*    */   }
/*    */   
/*    */   public BedrockVertex(Vector3f pos, float u, float v) {
/* 15 */     this.pos = pos;
/* 16 */     this.u = u;
/* 17 */     this.v = v;
/*    */   }
/*    */   
/*    */   public BedrockVertex remap(float u, float v) {
/* 21 */     return new BedrockVertex(this.pos, u, v);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockVertex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */