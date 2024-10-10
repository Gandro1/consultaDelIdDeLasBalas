/*    */ package com.tacz.guns.client.model.bedrock;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.client.resource.pojo.model.FaceItem;
/*    */ import com.tacz.guns.client.resource.pojo.model.FaceUVsItem;
/*    */ import net.minecraft.core.Direction;
/*    */ import org.joml.Matrix3f;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ import org.joml.Vector3f;
/*    */ import org.joml.Vector4f;
/*    */ 
/*    */ public class BedrockCubePerFace implements BedrockCube {
/* 14 */   private static final BedrockVertex EMPTY = new BedrockVertex(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 15 */   private static final BedrockVertex[] EMPTY_VERTEX = new BedrockVertex[] { EMPTY, EMPTY, EMPTY, EMPTY };
/*    */   public final float minX;
/*    */   public final float minY;
/*    */   public final float minZ;
/*    */   public final float maxX;
/*    */   public final float maxY;
/*    */   public final float maxZ;
/*    */   private final BedrockPolygon[] polygons;
/*    */   
/*    */   public BedrockCubePerFace(float x, float y, float z, float width, float height, float depth, float delta, float texWidth, float texHeight, FaceUVsItem faces) {
/* 25 */     this.minX = x;
/* 26 */     this.minY = y;
/* 27 */     this.minZ = z;
/* 28 */     this.maxX = x + width;
/* 29 */     this.maxY = y + height;
/* 30 */     this.maxZ = z + depth;
/* 31 */     this.polygons = new BedrockPolygon[6];
/*    */     
/* 33 */     float xEnd = x + width;
/* 34 */     float yEnd = y + height;
/* 35 */     float zEnd = z + depth;
/* 36 */     x -= delta;
/* 37 */     y -= delta;
/* 38 */     z -= delta;
/* 39 */     xEnd += delta;
/* 40 */     yEnd += delta;
/* 41 */     zEnd += delta;
/*    */     
/* 43 */     BedrockVertex vertex1 = new BedrockVertex(x, y, z, 0.0F, 0.0F);
/* 44 */     BedrockVertex vertex2 = new BedrockVertex(xEnd, y, z, 0.0F, 8.0F);
/* 45 */     BedrockVertex vertex3 = new BedrockVertex(xEnd, yEnd, z, 8.0F, 8.0F);
/* 46 */     BedrockVertex vertex4 = new BedrockVertex(x, yEnd, z, 8.0F, 0.0F);
/* 47 */     BedrockVertex vertex5 = new BedrockVertex(x, y, zEnd, 0.0F, 0.0F);
/* 48 */     BedrockVertex vertex6 = new BedrockVertex(xEnd, y, zEnd, 0.0F, 8.0F);
/* 49 */     BedrockVertex vertex7 = new BedrockVertex(xEnd, yEnd, zEnd, 8.0F, 8.0F);
/* 50 */     BedrockVertex vertex8 = new BedrockVertex(x, yEnd, zEnd, 8.0F, 0.0F);
/*    */     
/* 52 */     this.polygons[2] = getTexturedQuad(new BedrockVertex[] { vertex6, vertex5, vertex1, vertex2 }, texWidth, texHeight, Direction.DOWN, faces);
/* 53 */     this.polygons[3] = getTexturedQuad(new BedrockVertex[] { vertex3, vertex4, vertex8, vertex7 }, texWidth, texHeight, Direction.UP, faces);
/* 54 */     this.polygons[1] = getTexturedQuad(new BedrockVertex[] { vertex1, vertex5, vertex8, vertex4 }, texWidth, texHeight, Direction.WEST, faces);
/* 55 */     this.polygons[4] = getTexturedQuad(new BedrockVertex[] { vertex2, vertex1, vertex4, vertex3 }, texWidth, texHeight, Direction.NORTH, faces);
/* 56 */     this.polygons[0] = getTexturedQuad(new BedrockVertex[] { vertex6, vertex2, vertex3, vertex7 }, texWidth, texHeight, Direction.EAST, faces);
/* 57 */     this.polygons[5] = getTexturedQuad(new BedrockVertex[] { vertex5, vertex6, vertex7, vertex8 }, texWidth, texHeight, Direction.SOUTH, faces);
/*    */   }
/*    */   
/*    */   private BedrockPolygon getTexturedQuad(BedrockVertex[] positionsIn, float texWidth, float texHeight, Direction direction, FaceUVsItem faces) {
/* 61 */     FaceItem face = faces.getFace(direction);
/* 62 */     float u1 = face.getUv()[0];
/* 63 */     float v1 = face.getUv()[1];
/* 64 */     float u2 = u1 + face.getUvSize()[0];
/* 65 */     float v2 = v1 + face.getUvSize()[1];
/* 66 */     if (face == FaceItem.EMPTY) {
/* 67 */       return new BedrockPolygon(EMPTY_VERTEX, u1, v1, u2, v2, texWidth, texHeight, false, direction);
/*    */     }
/* 69 */     return new BedrockPolygon(positionsIn, u1, v1, u2, v2, texWidth, texHeight, false, direction);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void compile(PoseStack.Pose pose, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
/* 75 */     Matrix4f matrix4f = pose.m_252922_();
/* 76 */     Matrix3f matrix3f = pose.m_252943_();
/*    */     
/* 78 */     for (BedrockPolygon polygon : this.polygons) {
/* 79 */       Vector3f vector3f = new Vector3f((Vector3fc)polygon.normal);
/* 80 */       vector3f.mul((Matrix3fc)matrix3f);
/* 81 */       float nx = vector3f.x();
/* 82 */       float ny = vector3f.y();
/* 83 */       float nz = vector3f.z();
/*    */       
/* 85 */       for (BedrockVertex vertex : polygon.vertices) {
/* 86 */         float x = vertex.pos.x() / 16.0F;
/* 87 */         float y = vertex.pos.y() / 16.0F;
/* 88 */         float z = vertex.pos.z() / 16.0F;
/* 89 */         Vector4f vector4f = new Vector4f(x, y, z, 1.0F);
/* 90 */         vector4f.mul((Matrix4fc)matrix4f);
/* 91 */         consumer.m_5954_(vector4f.x(), vector4f.y(), vector4f.z(), red, green, blue, alpha, vertex.u, vertex.v, overlay, light, nx, ny, nz);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockCubePerFace.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */