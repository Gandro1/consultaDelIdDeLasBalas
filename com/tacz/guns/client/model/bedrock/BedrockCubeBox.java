/*    */ package com.tacz.guns.client.model.bedrock;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.core.Direction;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix3fc;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ import org.joml.Vector3f;
/*    */ import org.joml.Vector3fc;
/*    */ import org.joml.Vector4f;
/*    */ 
/*    */ public class BedrockCubeBox
/*    */   implements BedrockCube {
/*    */   public final float minX;
/*    */   public final float minY;
/*    */   public final float minZ;
/*    */   
/*    */   public BedrockCubeBox(float texOffX, float texOffY, float x, float y, float z, float width, float height, float depth, float delta, boolean mirror, float texWidth, float texHeight) {
/* 21 */     this.minX = x;
/* 22 */     this.minY = y;
/* 23 */     this.minZ = z;
/* 24 */     this.maxX = x + width;
/* 25 */     this.maxY = y + height;
/* 26 */     this.maxZ = z + depth;
/* 27 */     this.polygons = new BedrockPolygon[6];
/*    */     
/* 29 */     float xEnd = x + width;
/* 30 */     float yEnd = y + height;
/* 31 */     float zEnd = z + depth;
/* 32 */     x -= delta;
/* 33 */     y -= delta;
/* 34 */     z -= delta;
/* 35 */     xEnd += delta;
/* 36 */     yEnd += delta;
/* 37 */     zEnd += delta;
/*    */     
/* 39 */     if (mirror) {
/* 40 */       float tmp = xEnd;
/* 41 */       xEnd = x;
/* 42 */       x = tmp;
/*    */     } 
/*    */     
/* 45 */     BedrockVertex vertex1 = new BedrockVertex(x, y, z, 0.0F, 0.0F);
/* 46 */     BedrockVertex vertex2 = new BedrockVertex(xEnd, y, z, 0.0F, 8.0F);
/* 47 */     BedrockVertex vertex3 = new BedrockVertex(xEnd, yEnd, z, 8.0F, 8.0F);
/* 48 */     BedrockVertex vertex4 = new BedrockVertex(x, yEnd, z, 8.0F, 0.0F);
/* 49 */     BedrockVertex vertex5 = new BedrockVertex(x, y, zEnd, 0.0F, 0.0F);
/* 50 */     BedrockVertex vertex6 = new BedrockVertex(xEnd, y, zEnd, 0.0F, 8.0F);
/* 51 */     BedrockVertex vertex7 = new BedrockVertex(xEnd, yEnd, zEnd, 8.0F, 8.0F);
/* 52 */     BedrockVertex vertex8 = new BedrockVertex(x, yEnd, zEnd, 8.0F, 0.0F);
/*    */     
/* 54 */     int dx = (int)width;
/* 55 */     int dy = (int)height;
/* 56 */     int dz = (int)depth;
/*    */     
/* 58 */     float p1 = texOffX + dz;
/* 59 */     float p2 = texOffX + dz + dx;
/* 60 */     float p3 = texOffX + dz + dx + dx;
/* 61 */     float p4 = texOffX + dz + dx + dz;
/* 62 */     float p5 = texOffX + dz + dx + dz + dx;
/* 63 */     float p6 = texOffY + dz;
/* 64 */     float p7 = texOffY + dz + dy;
/* 65 */     float p8 = texOffY;
/* 66 */     float p9 = texOffX;
/*    */     
/* 68 */     this.polygons[2] = new BedrockPolygon(new BedrockVertex[] { vertex6, vertex5, vertex1, vertex2 }, p1, p8, p2, p6, texWidth, texHeight, mirror, Direction.DOWN);
/* 69 */     this.polygons[3] = new BedrockPolygon(new BedrockVertex[] { vertex3, vertex4, vertex8, vertex7 }, p2, p6, p3, p8, texWidth, texHeight, mirror, Direction.UP);
/* 70 */     this.polygons[1] = new BedrockPolygon(new BedrockVertex[] { vertex1, vertex5, vertex8, vertex4 }, p9, p6, p1, p7, texWidth, texHeight, mirror, Direction.WEST);
/* 71 */     this.polygons[4] = new BedrockPolygon(new BedrockVertex[] { vertex2, vertex1, vertex4, vertex3 }, p1, p6, p2, p7, texWidth, texHeight, mirror, Direction.NORTH);
/* 72 */     this.polygons[0] = new BedrockPolygon(new BedrockVertex[] { vertex6, vertex2, vertex3, vertex7 }, p2, p6, p4, p7, texWidth, texHeight, mirror, Direction.EAST);
/* 73 */     this.polygons[5] = new BedrockPolygon(new BedrockVertex[] { vertex5, vertex6, vertex7, vertex8 }, p4, p6, p5, p7, texWidth, texHeight, mirror, Direction.SOUTH);
/*    */   }
/*    */   public final float maxX; public final float maxY; public final float maxZ; private final BedrockPolygon[] polygons;
/*    */   
/*    */   public void compile(PoseStack.Pose pose, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
/* 78 */     Matrix4f matrix4f = pose.m_252922_();
/* 79 */     Matrix3f matrix3f = pose.m_252943_();
/*    */     
/* 81 */     for (BedrockPolygon polygon : this.polygons) {
/* 82 */       Vector3f vector3f = new Vector3f((Vector3fc)polygon.normal);
/* 83 */       vector3f.mul((Matrix3fc)matrix3f);
/* 84 */       float nx = vector3f.x();
/* 85 */       float ny = vector3f.y();
/* 86 */       float nz = vector3f.z();
/*    */       
/* 88 */       for (BedrockVertex vertex : polygon.vertices) {
/* 89 */         float x = vertex.pos.x() / 16.0F;
/* 90 */         float y = vertex.pos.y() / 16.0F;
/* 91 */         float z = vertex.pos.z() / 16.0F;
/* 92 */         Vector4f vector4f = new Vector4f(x, y, z, 1.0F);
/* 93 */         vector4f.mul((Matrix4fc)matrix4f);
/* 94 */         consumer.m_5954_(vector4f.x(), vector4f.y(), vector4f.z(), red, green, blue, alpha, vertex.u, vertex.v, overlay, light, nx, ny, nz);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockCubeBox.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */