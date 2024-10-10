/*    */ package com.tacz.guns.api.client.animation.statemachine;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationController;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuaAnimationStateMachine<T extends AnimationStateContext>
/*    */   extends AnimationStateMachine<LuaContextWrapper<T>>
/*    */ {
/*    */   Consumer<LuaContextWrapper<T>> initializeFunc;
/*    */   Consumer<LuaContextWrapper<T>> exitFunc;
/*    */   
/*    */   LuaAnimationStateMachine(AnimationController animationController) {
/* 19 */     super(animationController);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setContextOverride(@Nonnull T context) {
/* 27 */     if (this.context == null) {
/* 28 */       this.context = new LuaContextWrapper<>(context);
/*    */     } else {
/* 30 */       T oldContext = this.context.getContext();
/* 31 */       if (oldContext != context) {
/* 32 */         if (areSameClass(oldContext, context)) {
/* 33 */           this.context.setContext(context);
/*    */         } else {
/* 35 */           this.context = new LuaContextWrapper<>(context);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T getContextOverride() {
/* 46 */     if (this.context == null) {
/* 47 */       return null;
/*    */     }
/* 49 */     return this.context.getContext();
/*    */   }
/*    */ 
/*    */   
/*    */   public void initialize() {
/* 54 */     super.initialize();
/* 55 */     this.initializeFunc.accept(this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void exit() {
/* 60 */     this.exitFunc.accept(this.context);
/* 61 */     super.exit();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public LuaContextWrapper<T> getContext() {
/* 71 */     throw new UnsupportedOperationException("call getContextOverride instead");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setContext(@Nonnull LuaContextWrapper<T> ignore) {
/* 81 */     throw new UnsupportedOperationException("call setContextOverride instead");
/*    */   }
/*    */   
/*    */   private static boolean areSameClass(Object obj1, Object obj2) {
/* 85 */     if (obj1 == null || obj2 == null) {
/* 86 */       return false;
/*    */     }
/* 88 */     return obj1.getClass().equals(obj2.getClass());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\LuaAnimationStateMachine.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */