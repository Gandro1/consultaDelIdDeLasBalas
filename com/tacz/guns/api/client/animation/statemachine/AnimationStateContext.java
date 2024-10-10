/*    */ package com.tacz.guns.api.client.animation.statemachine;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationController;
/*    */ import com.tacz.guns.api.client.animation.DiscreteTrackArray;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimationRunner;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class AnimationStateContext
/*    */ {
/*    */   @Nullable
/*    */   public AnimationStateMachine<?> getStateMachine() {
/* 14 */     return this.stateMachine;
/*    */   } @Nullable
/*    */   private AnimationStateMachine<?> stateMachine;
/*    */   public DiscreteTrackArray getTrackArray() {
/* 18 */     if (this.stateMachine == null) {
/* 19 */       throw new IllegalStateException("This context has not been bound to a statemachine yet.");
/*    */     }
/* 21 */     return (DiscreteTrackArray)this.stateMachine.getAnimationController().getUpdatingTrackArray();
/*    */   }
/*    */   
/*    */   public int addTrackLine() {
/* 25 */     return getTrackArray().addTrackLine();
/*    */   }
/*    */   
/*    */   public int assignNewTrack(int index) {
/* 29 */     return getTrackArray().assignNewTrack(index);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int findIdleTrack(int index, boolean interruptHolding) {
/* 39 */     List<Integer> trackList = getTrackArray().getByIndex(index);
/* 40 */     if (this.stateMachine == null) {
/* 41 */       return -1;
/*    */     }
/* 43 */     AnimationController controller = this.stateMachine.getAnimationController();
/* 44 */     for (Iterator<Integer> iterator = trackList.iterator(); iterator.hasNext(); ) { int track = ((Integer)iterator.next()).intValue();
/* 45 */       ObjectAnimationRunner animation = controller.getAnimation(track);
/* 46 */       if (animation == null || animation.isStopped() || (interruptHolding && animation.isHolding())) {
/* 47 */         return track;
/*    */       } }
/*    */     
/* 50 */     return assignNewTrack(index);
/*    */   }
/*    */   
/*    */   void setStateMachine(@Nullable AnimationStateMachine<?> stateMachine) {
/* 54 */     if (this.stateMachine != null) {
/* 55 */       this.stateMachine.getAnimationController().setUpdatingTrackArray(null);
/*    */     }
/* 57 */     this.stateMachine = stateMachine;
/* 58 */     if (stateMachine != null)
/* 59 */       stateMachine.getAnimationController().setUpdatingTrackArray((Iterable)new DiscreteTrackArray()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\AnimationStateContext.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */