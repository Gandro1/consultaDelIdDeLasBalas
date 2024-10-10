/*    */ package com.tacz.guns.api.client.animation.statemachine;
/*    */ 
/*    */ import com.tacz.guns.api.client.animation.AnimationController;
/*    */ import java.util.LinkedList;
/*    */ import java.util.function.Supplier;
/*    */ import org.luaj.vm2.LuaFunction;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ public class LuaStateMachineFactory<T extends AnimationStateContext>
/*    */ {
/*    */   private AnimationController controller;
/*    */   private LuaFunction initializeFunc;
/*    */   private LuaFunction exitFunc;
/*    */   private LuaFunction statesFunc;
/*    */   
/*    */   public LuaAnimationStateMachine<T> build() {
/* 18 */     checkNullPointer();
/* 19 */     LuaAnimationStateMachine<T> stateMachine = new LuaAnimationStateMachine<>(this.controller);
/* 20 */     stateMachine.initializeFunc = (context -> {
/*    */         if (this.initializeFunc != null) {
/*    */           this.initializeFunc.call(context.getLuaContext());
/*    */         }
/*    */       });
/* 25 */     stateMachine.exitFunc = (context -> {
/*    */         if (this.exitFunc != null) {
/*    */           this.exitFunc.call(context.getLuaContext());
/*    */         }
/*    */       });
/* 30 */     stateMachine.setStatesSupplier(getStatesSupplier());
/* 31 */     return stateMachine;
/*    */   }
/*    */   
/*    */   public LuaStateMachineFactory<T> setController(AnimationController controller) {
/* 35 */     this.controller = controller;
/* 36 */     return this;
/*    */   }
/*    */   
/*    */   public LuaStateMachineFactory<T> setLuaScripts(LuaTable table) {
/* 40 */     this.initializeFunc = checkFunction("initialize", table);
/* 41 */     this.exitFunc = checkFunction("exit", table);
/* 42 */     this.statesFunc = checkFunction("states", table);
/* 43 */     return this;
/*    */   }
/*    */   
/*    */   private LuaFunction checkFunction(String funcName, LuaTable table) {
/* 47 */     LuaValue value = table.get(funcName);
/* 48 */     if (value.isnil()) {
/* 49 */       return null;
/*    */     }
/* 51 */     return value.checkfunction();
/*    */   }
/*    */   
/*    */   private void checkNullPointer() {
/* 55 */     if (this.controller == null) {
/* 56 */       throw new IllegalStateException("controller must not be null before build");
/*    */     }
/*    */   }
/*    */   
/*    */   private Supplier<Iterable<? extends AnimationState<LuaContextWrapper<T>>>> getStatesSupplier() {
/* 61 */     if (this.statesFunc == null) {
/* 62 */       return null;
/*    */     }
/* 64 */     return () -> {
/*    */         LuaTable statesTable = this.statesFunc.call().checktable();
/*    */         LinkedList<LuaAnimationState<T>> states = new LinkedList<>();
/*    */         for (int f = 1; f <= statesTable.length(); f++) {
/*    */           LuaTable stateTable = statesTable.get(f).checktable();
/*    */           states.add(new LuaAnimationState<>(stateTable));
/*    */         } 
/*    */         return states;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\LuaStateMachineFactory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */