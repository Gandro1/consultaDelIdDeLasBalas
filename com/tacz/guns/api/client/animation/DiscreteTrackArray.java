/*     */ package com.tacz.guns.api.client.animation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DiscreteTrackArray implements Iterable<Integer> {
/*  11 */   private int top = 0;
/*     */   private final ArrayList<LinkedList<Integer>> tracks;
/*  13 */   private int modCount = 0;
/*     */   
/*     */   public DiscreteTrackArray(int size) {
/*  16 */     this.tracks = new ArrayList<>(size);
/*  17 */     for (int i = 0; i < size; i++) {
/*  18 */       this.tracks.add(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public DiscreteTrackArray() {
/*  23 */     this.tracks = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public int addTrackLine() {
/*  27 */     this.modCount++;
/*  28 */     this.tracks.add(null);
/*  29 */     return this.tracks.size() - 1;
/*     */   }
/*     */   
/*     */   public int assignNewTrack(int index) {
/*  33 */     if (this.top == Integer.MAX_VALUE) {
/*  34 */       throw new RuntimeException("Can't assign new track due to overflow");
/*     */     }
/*  36 */     this.modCount++;
/*  37 */     Optional.<LinkedList>ofNullable(this.tracks.get(index)).ifPresentOrElse(list -> list.add(Integer.valueOf(this.top++)), () -> {
/*     */           LinkedList<Integer> list = new LinkedList<>();
/*     */           
/*     */           list.add(Integer.valueOf(this.top++));
/*     */           
/*     */           this.tracks.set(index, list);
/*     */         });
/*     */     
/*  45 */     return this.top;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public List<Integer> getByIndex(int index) {
/*  50 */     LinkedList<Integer> list = this.tracks.get(index);
/*  51 */     if (list == null) {
/*  52 */       return Collections.emptyList();
/*     */     }
/*  54 */     return Collections.unmodifiableList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Iterator<Integer> iterator() {
/*  61 */     return new MyIterator(this.modCount);
/*     */   }
/*     */   
/*     */   private final class MyIterator implements Iterator<Integer> { private final int modCount;
/*     */     @Nullable
/*     */     private Iterator<Integer> iterator;
/*     */     private int nextIndex;
/*     */     
/*     */     public MyIterator(int modCount) {
/*  70 */       this.modCount = modCount;
/*  71 */       int index = findNextNotEmptyList(-1);
/*  72 */       if (index != -1) {
/*  73 */         this.iterator = ((LinkedList<Integer>)DiscreteTrackArray.this.tracks.get(index)).iterator();
/*  74 */         this.nextIndex = findNextNotEmptyList(index);
/*     */       } else {
/*  76 */         this.iterator = null;
/*  77 */         this.nextIndex = -1;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/*  83 */       checkForModifications();
/*  84 */       if (this.iterator != null && this.iterator.hasNext()) {
/*  85 */         return true;
/*     */       }
/*  87 */       return (this.nextIndex != -1);
/*     */     }
/*     */ 
/*     */     
/*     */     public Integer next() throws IllegalStateException {
/*  92 */       checkForModifications();
/*  93 */       if (this.iterator != null && this.iterator.hasNext()) {
/*  94 */         return this.iterator.next();
/*     */       }
/*  96 */       if (this.nextIndex != -1) {
/*  97 */         this.iterator = ((LinkedList<Integer>)DiscreteTrackArray.this.tracks.get(this.nextIndex)).iterator();
/*  98 */         this.nextIndex = findNextNotEmptyList(this.nextIndex);
/*  99 */         return this.iterator.next();
/*     */       } 
/* 101 */       throw new IllegalStateException("No more elements");
/*     */     }
/*     */     
/*     */     private void checkForModifications() {
/* 105 */       if (DiscreteTrackArray.this.modCount != this.modCount) {
/* 106 */         throw new ConcurrentModificationException("Container modified during iteration");
/*     */       }
/*     */     }
/*     */     
/*     */     private int findNextNotEmptyList(int index) {
/* 111 */       int i = index + 1;
/* 112 */       while (i < DiscreteTrackArray.this.tracks.size()) {
/* 113 */         LinkedList<Integer> list = DiscreteTrackArray.this.tracks.get(i);
/* 114 */         if (list != null && !list.isEmpty()) {
/* 115 */           return i;
/*     */         }
/* 117 */         i++;
/*     */       } 
/* 119 */       return -1;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\DiscreteTrackArray.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */