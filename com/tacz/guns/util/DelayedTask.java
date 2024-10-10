/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.LinkedList;
/*    */ import java.util.function.BooleanSupplier;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public final class DelayedTask
/*    */ {
/* 17 */   public static LinkedList<BooleanSupplier> SUPPLIERS = Lists.newLinkedList();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void add(Runnable runnable, int delayedTick) {
/* 23 */     int[] tickArray = { delayedTick };
/* 24 */     SUPPLIERS.add(() -> {
/*    */           tickArray[0] = tickArray[0] - 1;
/*    */           if (tickArray[0] - 1 < 0) {
/*    */             runnable.run();
/*    */             return true;
/*    */           } 
/*    */           return false;
/*    */         });
/*    */   }
/*    */   public static void add(Consumer<Integer> consumer, int delayedTick, int times) {
/* 34 */     int[] tickArray = { delayedTick };
/* 35 */     SUPPLIERS.add(() -> {
/*    */           tickArray[0] = tickArray[0] - 1;
/*    */           if (tickArray[0] - 1 < 0) {
/*    */             consumer.accept(Integer.valueOf(times));
/*    */             return true;
/*    */           } 
/*    */           return false;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\DelayedTask.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */