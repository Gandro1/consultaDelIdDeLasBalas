/*    */ package com.tacz.guns.api.client.animation.gltf;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class NodeModel {
/*  9 */   private final List<NodeModel> children = new ArrayList<>();
/*    */   private String name;
/*    */   private float[] matrix;
/*    */   private float[] translation;
/*    */   private float[] rotation;
/*    */   private float[] scale;
/*    */   private NodeModel parent;
/*    */   
/*    */   private static float[] check(float[] array, int expectedLength) {
/* 18 */     if (array == null) {
/* 19 */       return null;
/*    */     }
/* 21 */     if (array.length != expectedLength) {
/* 22 */       throw new IllegalArgumentException("Expected " + expectedLength + " array elements, but found " + array.length);
/*    */     }
/*    */     
/* 25 */     return array;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 29 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 33 */     this.name = name;
/*    */   }
/*    */   
/*    */   public NodeModel getParent() {
/* 37 */     return this.parent;
/*    */   }
/*    */   
/*    */   public List<NodeModel> getChildren() {
/* 41 */     return Collections.unmodifiableList(this.children);
/*    */   }
/*    */   
/*    */   public void addChild(NodeModel child) {
/* 45 */     Objects.requireNonNull(child, "The child may not be null");
/* 46 */     this.children.add(child);
/* 47 */     child.parent = this;
/*    */   }
/*    */   
/*    */   public float[] getMatrix() {
/* 51 */     return this.matrix;
/*    */   }
/*    */   
/*    */   public void setMatrix(float[] matrix) {
/* 55 */     this.matrix = check(matrix, 16);
/*    */   }
/*    */   
/*    */   public float[] getTranslation() {
/* 59 */     return this.translation;
/*    */   }
/*    */   
/*    */   public void setTranslation(float[] translation) {
/* 63 */     this.translation = check(translation, 3);
/*    */   }
/*    */   
/*    */   public float[] getRotation() {
/* 67 */     return this.rotation;
/*    */   }
/*    */   
/*    */   public void setRotation(float[] rotation) {
/* 71 */     this.rotation = check(rotation, 4);
/*    */   }
/*    */   
/*    */   public float[] getScale() {
/* 75 */     return this.scale;
/*    */   }
/*    */   
/*    */   public void setScale(float[] scale) {
/* 79 */     this.scale = check(scale, 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\NodeModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */