/*     */ package com.tacz.guns.api.client.animation.statemachine;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.AnimationController;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class AnimationStateMachine<T extends AnimationStateContext>
/*     */ {
/*     */   private List<AnimationState<T>> currentStates;
/*     */   protected T context;
/*     */   private Supplier<Iterable<? extends AnimationState<T>>> statesSupplier;
/*     */   @Nonnull
/*     */   private final AnimationController animationController;
/*     */   
/*     */   public AnimationStateMachine(@Nonnull AnimationController animationController) {
/*  39 */     this.animationController = Objects.<AnimationController>requireNonNull(animationController);
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
/*     */   public void update() {
/*  51 */     checkNullPointer();
/*  52 */     this.currentStates.forEach(state -> state.update(this.context));
/*  53 */     this.animationController.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trigger(String condition) {
/*  63 */     checkNullPointer();
/*     */     
/*  65 */     ListIterator<AnimationState<T>> iterator = this.currentStates.listIterator();
/*  66 */     while (iterator.hasNext()) {
/*  67 */       AnimationState<T> state = iterator.next();
/*  68 */       AnimationState<T> nextState = state.transition(this.context, condition);
/*  69 */       if (nextState != null) {
/*  70 */         state.exitAction(this.context);
/*  71 */         iterator.set(nextState);
/*  72 */         nextState.entryAction(this.context);
/*     */       } 
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
/*     */   public void initialize() {
/*  85 */     if (this.context == null) {
/*  86 */       throw new IllegalStateException("Context must not be null before initialization");
/*     */     }
/*  88 */     if (this.currentStates != null) {
/*  89 */       throw new IllegalStateException("State machine is already initialized");
/*     */     }
/*  91 */     this.currentStates = new LinkedList<>();
/*     */     
/*  93 */     Optional.<Supplier<Iterable<? extends AnimationState<T>>>>ofNullable(this.statesSupplier)
/*  94 */       .map(Supplier::get)
/*  95 */       .ifPresent(list -> list.forEach(()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exit() {
/* 106 */     checkNullPointer();
/*     */     
/* 108 */     this.currentStates.forEach(state -> state.exitAction(this.context));
/* 109 */     this.currentStates = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public AnimationController getAnimationController() {
/* 116 */     return this.animationController;
/*     */   }
/*     */   
/*     */   public boolean isInitialized() {
/* 120 */     return (this.currentStates != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T getContext() {
/* 127 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContext(@Nonnull T context) {
/* 134 */     AnimationStateMachine<?> stateMachine = context.getStateMachine();
/* 135 */     if (stateMachine != null && stateMachine != this) {
/* 136 */       throw new IllegalStateException("Context is already used");
/*     */     }
/* 138 */     if (this.context != null) {
/* 139 */       this.context.setStateMachine(null);
/*     */     }
/* 141 */     context.setStateMachine(this);
/* 142 */     this.context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatesSupplier(Supplier<Iterable<? extends AnimationState<T>>> statesSupplier) {
/* 151 */     this.statesSupplier = statesSupplier;
/*     */   }
/*     */   
/*     */   private void checkNullPointer() {
/* 155 */     if (this.context == null) {
/* 156 */       throw new IllegalStateException("Context has not been initialized");
/*     */     }
/* 158 */     if (this.currentStates == null)
/* 159 */       throw new IllegalStateException("State machine has not been initialized"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\AnimationStateMachine.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */