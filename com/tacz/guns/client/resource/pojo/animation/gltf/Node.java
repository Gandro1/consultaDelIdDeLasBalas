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
/*     */ public class Node
/*     */ {
/*     */   private String name;
/*     */   private List<Integer> children;
/*     */   private float[] matrix;
/*     */   private float[] rotation;
/*     */   private float[] scale;
/*     */   private float[] translation;
/*     */   
/*     */   public List<Integer> getChildren() {
/*  58 */     return this.children;
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
/*     */   public void setChildren(List<Integer> children) {
/*  73 */     if (children == null) {
/*  74 */       this.children = children;
/*     */       return;
/*     */     } 
/*  77 */     if (children.size() < 1) {
/*  78 */       throw new IllegalArgumentException("Number of children elements is < 1");
/*     */     }
/*  80 */     for (Integer childrenElement : children) {
/*  81 */       if (childrenElement.intValue() < 0) {
/*  82 */         throw new IllegalArgumentException("childrenElement < 0");
/*     */       }
/*     */     } 
/*  85 */     this.children = children;
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
/*     */   public void addChildren(Integer element) {
/*  97 */     if (element == null) {
/*  98 */       throw new NullPointerException("The element may not be null");
/*     */     }
/* 100 */     List<Integer> oldList = this.children;
/* 101 */     List<Integer> newList = new ArrayList<>();
/* 102 */     if (oldList != null) {
/* 103 */       newList.addAll(oldList);
/*     */     }
/* 105 */     newList.add(element);
/* 106 */     this.children = newList;
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
/*     */   public void removeChildren(Integer element) {
/* 120 */     if (element == null) {
/* 121 */       throw new NullPointerException("The element may not be null");
/*     */     }
/* 123 */     List<Integer> oldList = this.children;
/* 124 */     List<Integer> newList = new ArrayList<>();
/* 125 */     if (oldList != null) {
/* 126 */       newList.addAll(oldList);
/*     */     }
/* 128 */     newList.remove(element);
/* 129 */     if (newList.isEmpty()) {
/* 130 */       this.children = null;
/*     */     } else {
/* 132 */       this.children = newList;
/*     */     } 
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
/*     */   public float[] getMatrix() {
/* 148 */     return this.matrix;
/*     */   }
/*     */   
/*     */   public void setMatrix(float[] matrix) {
/* 152 */     if (matrix == null) {
/* 153 */       this.matrix = matrix;
/*     */       return;
/*     */     } 
/* 156 */     if (matrix.length < 16) {
/* 157 */       throw new IllegalArgumentException("Number of matrix elements is < 16");
/*     */     }
/* 159 */     if (matrix.length > 16) {
/* 160 */       throw new IllegalArgumentException("Number of matrix elements is > 16");
/*     */     }
/* 162 */     this.matrix = matrix;
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
/*     */   public float[] getRotation() {
/* 178 */     return this.rotation;
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
/*     */   public void setRotation(float[] rotation) {
/* 196 */     if (rotation == null) {
/* 197 */       this.rotation = rotation;
/*     */       return;
/*     */     } 
/* 200 */     if (rotation.length < 4) {
/* 201 */       throw new IllegalArgumentException("Number of rotation elements is < 4");
/*     */     }
/* 203 */     if (rotation.length > 4) {
/* 204 */       throw new IllegalArgumentException("Number of rotation elements is > 4");
/*     */     }
/* 206 */     for (float rotationElement : rotation) {
/* 207 */       if (rotationElement > 1.0D) {
/* 208 */         throw new IllegalArgumentException("rotationElement > 1.0");
/*     */       }
/* 210 */       if (rotationElement < -1.0D) {
/* 211 */         throw new IllegalArgumentException("rotationElement < -1.0");
/*     */       }
/*     */     } 
/* 214 */     this.rotation = rotation;
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
/*     */   public float[] getScale() {
/* 228 */     return this.scale;
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
/*     */   public void setScale(float[] scale) {
/* 244 */     if (scale == null) {
/* 245 */       this.scale = scale;
/*     */       return;
/*     */     } 
/* 248 */     if (scale.length < 3) {
/* 249 */       throw new IllegalArgumentException("Number of scale elements is < 3");
/*     */     }
/* 251 */     if (scale.length > 3) {
/* 252 */       throw new IllegalArgumentException("Number of scale elements is > 3");
/*     */     }
/* 254 */     this.scale = scale;
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
/*     */   public float[] getTranslation() {
/* 267 */     return this.translation;
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
/*     */   public void setTranslation(float[] translation) {
/* 282 */     if (translation == null) {
/* 283 */       this.translation = translation;
/*     */       return;
/*     */     } 
/* 286 */     if (translation.length < 3) {
/* 287 */       throw new IllegalArgumentException("Number of translation elements is < 3");
/*     */     }
/* 289 */     if (translation.length > 3) {
/* 290 */       throw new IllegalArgumentException("Number of translation elements is > 3");
/*     */     }
/* 292 */     this.translation = translation;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 296 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 300 */     this.name = name;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\Node.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */