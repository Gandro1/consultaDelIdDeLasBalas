/*     */ package com.tacz.guns.client.model.bedrock;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ public class ModelRendererWrapper {
/*     */   private final BedrockPart modelRenderer;
/*     */   
/*     */   public ModelRendererWrapper(BedrockPart modelRenderer) {
/*  12 */     this.modelRenderer = modelRenderer;
/*     */   }
/*     */   
/*     */   public BedrockPart getModelRenderer() {
/*  16 */     return this.modelRenderer;
/*     */   }
/*     */   
/*     */   public float getRotateAngleX() {
/*  20 */     return this.modelRenderer.xRot;
/*     */   }
/*     */   
/*     */   public void setRotateAngleX(float xRot) {
/*  24 */     this.modelRenderer.xRot = xRot;
/*     */   }
/*     */   
/*     */   public float getRotateAngleY() {
/*  28 */     return this.modelRenderer.yRot;
/*     */   }
/*     */   
/*     */   public void setRotateAngleY(float yRot) {
/*  32 */     this.modelRenderer.yRot = yRot;
/*     */   }
/*     */   
/*     */   public float getRotateAngleZ() {
/*  36 */     return this.modelRenderer.zRot;
/*     */   }
/*     */   
/*     */   public void setRotateAngleZ(float zRot) {
/*  40 */     this.modelRenderer.zRot = zRot;
/*     */   }
/*     */   
/*     */   public float getOffsetX() {
/*  44 */     return this.modelRenderer.offsetX;
/*     */   }
/*     */   
/*     */   public void setOffsetX(float offsetX) {
/*  48 */     this.modelRenderer.offsetX = offsetX;
/*     */   }
/*     */   
/*     */   public float getOffsetY() {
/*  52 */     return this.modelRenderer.offsetY;
/*     */   }
/*     */   
/*     */   public void setOffsetY(float offsetY) {
/*  56 */     this.modelRenderer.offsetY = offsetY;
/*     */   }
/*     */   
/*     */   public float getOffsetZ() {
/*  60 */     return this.modelRenderer.offsetZ;
/*     */   }
/*     */   
/*     */   public void setOffsetZ(float offsetZ) {
/*  64 */     this.modelRenderer.offsetZ = offsetZ;
/*     */   }
/*     */   
/*     */   public void addOffsetX(float offsetX) {
/*  68 */     setOffsetX(getOffsetX() + offsetX);
/*     */   }
/*     */   
/*     */   public void addOffsetY(float offsetY) {
/*  72 */     setOffsetY(getOffsetY() + offsetY);
/*     */   }
/*     */   
/*     */   public void addOffsetZ(float offsetZ) {
/*  76 */     setOffsetZ(getOffsetZ() + offsetZ);
/*     */   }
/*     */   
/*     */   public float getRotationPointX() {
/*  80 */     return this.modelRenderer.x;
/*     */   }
/*     */   
/*     */   public float getRotationPointY() {
/*  84 */     return this.modelRenderer.y;
/*     */   }
/*     */   
/*     */   public float getRotationPointZ() {
/*  88 */     return this.modelRenderer.z;
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/*  92 */     return !this.modelRenderer.visible;
/*     */   }
/*     */   
/*     */   public void setHidden(boolean hidden) {
/*  96 */     this.modelRenderer.visible = !hidden;
/*     */   }
/*     */   
/*     */   public float getInitRotateAngleX() {
/* 100 */     return this.modelRenderer.getInitRotX();
/*     */   }
/*     */   
/*     */   public float getInitRotateAngleY() {
/* 104 */     return this.modelRenderer.getInitRotY();
/*     */   }
/*     */   
/*     */   public float getInitRotateAngleZ() {
/* 108 */     return this.modelRenderer.getInitRotZ();
/*     */   }
/*     */   
/*     */   public Quaternionf getAdditionalQuaternion() {
/* 112 */     return this.modelRenderer.additionalQuaternion;
/*     */   }
/*     */   
/*     */   public void setAdditionalQuaternion(Quaternionf quaternion) {
/* 116 */     this.modelRenderer.additionalQuaternion = quaternion;
/*     */   }
/*     */   
/*     */   public float getScaleX() {
/* 120 */     return this.modelRenderer.xScale;
/*     */   }
/*     */   
/*     */   public void setScaleX(float scaleX) {
/* 124 */     this.modelRenderer.xScale = scaleX;
/*     */   }
/*     */   
/*     */   public float getScaleY() {
/* 128 */     return this.modelRenderer.yScale;
/*     */   }
/*     */   
/*     */   public void setScaleY(float scaleY) {
/* 132 */     this.modelRenderer.yScale = scaleY;
/*     */   }
/*     */   
/*     */   public float getScaleZ() {
/* 136 */     return this.modelRenderer.zScale;
/*     */   }
/*     */   
/*     */   public void setScaleZ(float scaleZ) {
/* 140 */     this.modelRenderer.zScale = scaleZ;
/*     */   }
/*     */   
/*     */   public void render(PoseStack poseStack, ItemDisplayContext transformType, VertexConsumer consumer, int light, int overlay) {
/* 144 */     this.modelRenderer.render(poseStack, transformType, consumer, light, overlay);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\ModelRendererWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */