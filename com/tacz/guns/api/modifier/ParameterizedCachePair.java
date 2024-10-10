/*    */ package com.tacz.guns.api.modifier;
/*    */ 
/*    */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*    */ import it.unimi.dsi.fastutil.Pair;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParameterizedCachePair<L, R>
/*    */   implements Pair<ParameterizedCache<L>, ParameterizedCache<R>>
/*    */ {
/*    */   private final Pair<ParameterizedCache<L>, ParameterizedCache<R>> value;
/*    */   
/*    */   private ParameterizedCachePair(Pair<ParameterizedCache<L>, ParameterizedCache<R>> value) {
/* 15 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public ParameterizedCache<L> left() {
/* 20 */     return (ParameterizedCache<L>)this.value.left();
/*    */   }
/*    */ 
/*    */   
/*    */   public ParameterizedCache<R> right() {
/* 25 */     return (ParameterizedCache<R>)this.value.right();
/*    */   }
/*    */   
/*    */   public static <L, R> ParameterizedCachePair<L, R> of(L defaultLeft, R defaultRight) {
/* 29 */     return new ParameterizedCachePair<>(Pair.of(ParameterizedCache.of(defaultLeft), ParameterizedCache.of(defaultRight)));
/*    */   }
/*    */   
/*    */   public static <L, R> ParameterizedCachePair<L, R> of(List<Modifier> left, List<Modifier> right, L defaultLeft, R defaultRight) {
/* 33 */     return new ParameterizedCachePair<>(Pair.of(new ParameterizedCache<>(left, defaultLeft), new ParameterizedCache<>(right, defaultRight)));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\modifier\ParameterizedCachePair.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */