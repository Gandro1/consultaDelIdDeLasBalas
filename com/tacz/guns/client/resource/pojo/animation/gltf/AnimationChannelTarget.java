/*    */ package com.tacz.guns.client.resource.pojo.animation.gltf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimationChannelTarget
/*    */ {
/*    */   private Integer node;
/*    */   private String path;
/*    */   
/*    */   public Integer getNode() {
/* 29 */     return this.node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNode(Integer node) {
/* 39 */     if (node == null) {
/* 40 */       this.node = node;
/*    */       return;
/*    */     } 
/* 43 */     this.node = node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPath() {
/* 59 */     return this.path;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPath(String path) {
/* 78 */     if (path == null) {
/* 79 */       throw new NullPointerException("Invalid value for path: " + path + ", may not be null");
/*    */     }
/* 81 */     if (!"translation".equals(path) && !"rotation".equals(path) && !"scale".equals(path) && !"weights".equals(path)) {
/* 82 */       throw new IllegalArgumentException("Invalid value for path: " + path + ", valid: [translation, rotation, scale, weights]");
/*    */     }
/* 84 */     this.path = path;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AnimationChannelTarget.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */