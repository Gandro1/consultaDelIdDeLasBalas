/*     */ package com.tacz.guns.api.client.animation;
/*     */ 
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ final class MyIterator
/*     */   implements Iterator<Integer>
/*     */ {
/*     */   private final int modCount;
/*     */   @Nullable
/*     */   private Iterator<Integer> iterator;
/*     */   private int nextIndex;
/*     */   
/*     */   public MyIterator(int modCount) {
/*  70 */     this.modCount = modCount;
/*  71 */     int index = findNextNotEmptyList(-1);
/*  72 */     if (index != -1) {
/*  73 */       this.iterator = ((LinkedList<Integer>)paramDiscreteTrackArray.tracks.get(index)).iterator();
/*  74 */       this.nextIndex = findNextNotEmptyList(index);
/*     */     } else {
/*  76 */       this.iterator = null;
/*  77 */       this.nextIndex = -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/*  83 */     checkForModifications();
/*  84 */     if (this.iterator != null && this.iterator.hasNext()) {
/*  85 */       return true;
/*     */     }
/*  87 */     return (this.nextIndex != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer next() throws IllegalStateException {
/*  92 */     checkForModifications();
/*  93 */     if (this.iterator != null && this.iterator.hasNext()) {
/*  94 */       return this.iterator.next();
/*     */     }
/*  96 */     if (this.nextIndex != -1) {
/*  97 */       this.iterator = ((LinkedList<Integer>)DiscreteTrackArray.this.tracks.get(this.nextIndex)).iterator();
/*  98 */       this.nextIndex = findNextNotEmptyList(this.nextIndex);
/*  99 */       return this.iterator.next();
/*     */     } 
/* 101 */     throw new IllegalStateException("No more elements");
/*     */   }
/*     */   
/*     */   private void checkForModifications() {
/* 105 */     if (DiscreteTrackArray.this.modCount != this.modCount) {
/* 106 */       throw new ConcurrentModificationException("Container modified during iteration");
/*     */     }
/*     */   }
/*     */   
/*     */   private int findNextNotEmptyList(int index) {
/* 111 */     int i = index + 1;
/* 112 */     while (i < DiscreteTrackArray.this.tracks.size()) {
/* 113 */       LinkedList<Integer> list = DiscreteTrackArray.this.tracks.get(i);
/* 114 */       if (list != null && !list.isEmpty()) {
/* 115 */         return i;
/*     */       }
/* 117 */       i++;
/*     */     } 
/* 119 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\DiscreteTrackArray$MyIterator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */