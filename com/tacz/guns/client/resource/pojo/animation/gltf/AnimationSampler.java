/*     */ package com.tacz.guns.client.resource.pojo.animation.gltf;
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
/*     */ public class AnimationSampler
/*     */ {
/*     */   private Integer input;
/*     */   private String interpolation;
/*     */   private Integer output;
/*     */   
/*     */   public Integer getInput() {
/*  28 */     return this.input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Integer input) {
/*  38 */     if (input == null) {
/*  39 */       throw new NullPointerException("Invalid value for input: " + input + ", may not be null");
/*     */     }
/*  41 */     this.input = input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInterpolation() {
/*  52 */     return this.interpolation;
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
/*     */   public void setInterpolation(String interpolation) {
/*  65 */     if (interpolation == null) {
/*  66 */       this.interpolation = interpolation;
/*     */       return;
/*     */     } 
/*  69 */     if (!"LINEAR".equals(interpolation) && !"STEP".equals(interpolation) && !"CUBICSPLINE".equals(interpolation)) {
/*  70 */       throw new IllegalArgumentException("Invalid value for interpolation: " + interpolation + ", valid: [LINEAR, STEP, CUBICSPLINE]");
/*     */     }
/*  72 */     this.interpolation = interpolation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String defaultInterpolation() {
/*  82 */     return "LINEAR";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getOutput() {
/*  92 */     return this.output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutput(Integer output) {
/* 103 */     if (output == null) {
/* 104 */       throw new NullPointerException("Invalid value for output: " + output + ", may not be null");
/*     */     }
/* 106 */     this.output = output;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\animation\gltf\AnimationSampler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */