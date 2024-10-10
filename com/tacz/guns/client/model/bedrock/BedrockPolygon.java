/*    */ package com.tacz.guns.client.model.bedrock;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class BedrockPolygon {
/*    */   public final BedrockVertex[] vertices;
/*    */   public final Vector3f normal;
/*    */   
/*    */   public BedrockPolygon(BedrockVertex[] vertices, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirror, Direction direction) {
/* 11 */     this.vertices = vertices;
/* 12 */     float f = 0.0F / texWidth;
/* 13 */     float f1 = 0.0F / texHeight;
/* 14 */     vertices[0] = vertices[0].remap(u2 / texWidth - f, v1 / texHeight + f1);
/* 15 */     vertices[1] = vertices[1].remap(u1 / texWidth + f, v1 / texHeight + f1);
/* 16 */     vertices[2] = vertices[2].remap(u1 / texWidth + f, v2 / texHeight - f1);
/* 17 */     vertices[3] = vertices[3].remap(u2 / texWidth - f, v2 / texHeight - f1);
/* 18 */     if (mirror) {
/* 19 */       int i = vertices.length;
/* 20 */       for (int j = 0; j < i / 2; j++) {
/* 21 */         BedrockVertex bedrockVertex = vertices[j];
/* 22 */         vertices[j] = vertices[i - 1 - j];
/* 23 */         vertices[i - 1 - j] = bedrockVertex;
/*    */       } 
/*    */     } 
/* 26 */     this.normal = direction.m_253071_();
/* 27 */     if (mirror)
/* 28 */       this.normal.mul(-1.0F, 1.0F, 1.0F); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockPolygon.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */