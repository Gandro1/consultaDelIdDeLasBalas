/*    */ package com.tacz.guns.util.math;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ public class SecondOrderDynamics {
/*  7 */   public static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(15, Thread::new); private final float k1;
/*    */   
/*    */   static {
/* 10 */     for (int i = 0; i < 15; i++) {
/* 11 */       executorService.execute(() -> {
/*    */           
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private final float k2;
/*    */   
/*    */   private final float k3;
/*    */   
/*    */   private float py;
/*    */   
/*    */   private float pyd;
/*    */   
/*    */   private float px;
/*    */   
/*    */   private float target;
/*    */ 
/*    */   
/*    */   public SecondOrderDynamics(float f, float z, float r, float x0) {
/* 33 */     this.k1 = (float)(z / Math.PI * f);
/* 34 */     this.k2 = (float)(1.0D / 6.283185307179586D * f * 6.283185307179586D * f);
/* 35 */     this.k3 = (float)((r * z) / 6.283185307179586D * f);
/*    */     
/* 37 */     this.py = this.px = x0;
/* 38 */     this.pyd = 0.0F;
/*    */     
/* 40 */     this.target = x0;
/*    */     
/* 42 */     executorService.execute(this::update);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float update(float x) {
/* 49 */     this.target = x;
/* 50 */     return get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float get() {
/* 55 */     if (Float.isNaN(this.py)) {
/* 56 */       this.py = 0.0F;
/*    */     }
/* 58 */     if (Float.isNaN(this.pyd)) {
/* 59 */       this.pyd = 0.0F;
/*    */     }
/* 61 */     return this.py + 0.05F * this.pyd;
/*    */   }
/*    */ 
/*    */   
/*    */   private void update() {
/*    */     while (true) {
/* 67 */       if (Float.isNaN(this.py)) {
/* 68 */         this.py = 0.0F;
/*    */       }
/* 70 */       if (Float.isNaN(this.pyd)) {
/* 71 */         this.pyd = 0.0F;
/*    */       }
/*    */       
/* 74 */       float t = 0.05F;
/* 75 */       float xd = (this.target - this.px) / t;
/* 76 */       float y = this.py + t * this.pyd;
/*    */       
/* 78 */       this.pyd += t * (this.px + this.k3 * xd - this.py - this.k1 * this.pyd) / this.k2;
/* 79 */       this.px = this.target;
/* 80 */       this.py = y;
/*    */       
/*    */       try {
/* 83 */         Thread.sleep(6L);
/* 84 */       } catch (InterruptedException e) {
/* 85 */         Thread.currentThread().interrupt();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\math\SecondOrderDynamics.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */