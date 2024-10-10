/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.function.BooleanSupplier;
/*    */ 
/*    */ public final class CycleTaskHelper {
/*  8 */   private static final List<CycleTaskTicker> CYCLE_TASKS = new LinkedList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addCycleTask(BooleanSupplier task, long periodMs, int cycles) {
/* 18 */     CycleTaskTicker ticker = new CycleTaskTicker(task, periodMs, cycles);
/* 19 */     if (ticker.tick()) {
/* 20 */       CYCLE_TASKS.add(ticker);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static void tick() {
/* 26 */     CYCLE_TASKS.removeIf(ticker -> !ticker.tick());
/*    */   }
/*    */   
/*    */   private static class CycleTaskTicker {
/*    */     private final BooleanSupplier task;
/*    */     private final float periodS;
/*    */     private final int cycles;
/* 33 */     private long timestamp = -1L;
/* 34 */     private float compensation = 0.0F;
/* 35 */     private int count = 0;
/*    */     
/*    */     private CycleTaskTicker(BooleanSupplier task, long periodMs, int cycles) {
/* 38 */       this.task = task;
/* 39 */       this.periodS = (float)periodMs / 1000.0F;
/* 40 */       this.cycles = cycles;
/*    */     }
/*    */     
/*    */     private boolean tick() {
/* 44 */       if (this.timestamp == -1L) {
/* 45 */         this.timestamp = System.currentTimeMillis();
/* 46 */         if (++this.count > this.cycles) {
/* 47 */           return false;
/*    */         }
/* 49 */         return this.task.getAsBoolean();
/*    */       } 
/* 51 */       float duration = (float)(System.currentTimeMillis() - this.timestamp) / 1000.0F + this.compensation;
/* 52 */       if (duration > this.periodS) {
/* 53 */         this.compensation = duration;
/* 54 */         this.timestamp = System.currentTimeMillis();
/* 55 */         while (this.compensation > this.periodS) {
/* 56 */           if (++this.count > this.cycles) {
/* 57 */             return false;
/*    */           }
/* 59 */           if (!this.task.getAsBoolean()) {
/* 60 */             return false;
/*    */           }
/* 62 */           this.compensation -= this.periodS;
/*    */         } 
/*    */       } 
/* 65 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\CycleTaskHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */