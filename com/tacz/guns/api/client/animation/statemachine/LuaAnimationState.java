/*    */ package com.tacz.guns.api.client.animation.statemachine;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import org.luaj.vm2.LuaError;
/*    */ import org.luaj.vm2.LuaFunction;
/*    */ import org.luaj.vm2.LuaString;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuaAnimationState<T extends AnimationStateContext>
/*    */   implements AnimationState<LuaContextWrapper<T>>
/*    */ {
/*    */   @Nonnull
/*    */   private final LuaTable luaTable;
/*    */   @Nullable
/*    */   private final LuaFunction updateFunction;
/*    */   
/*    */   LuaAnimationState(@Nonnull LuaTable luaTable) {
/* 22 */     this.luaTable = luaTable;
/* 23 */     this.updateFunction = checkLuaFunction("update");
/* 24 */     this.enterFunction = checkLuaFunction("entry");
/* 25 */     this.exitFunction = checkLuaFunction("exit");
/* 26 */     this.transitionFunction = checkLuaFunction("transition");
/*    */   } @Nullable
/*    */   private final LuaFunction enterFunction; @Nullable
/*    */   private final LuaFunction exitFunction; @Nullable
/*    */   private final LuaFunction transitionFunction; public void update(LuaContextWrapper<T> context) {
/* 31 */     if (this.updateFunction != null) {
/* 32 */       this.updateFunction.call(context.getLuaContext());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void entryAction(LuaContextWrapper<T> context) {
/* 38 */     if (this.enterFunction != null) {
/* 39 */       this.enterFunction.call(context.getLuaContext());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void exitAction(LuaContextWrapper<T> context) {
/* 45 */     if (this.exitFunction != null) {
/* 46 */       this.exitFunction.call(context.getLuaContext());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AnimationState<LuaContextWrapper<T>> transition(LuaContextWrapper<T> context, String condition) {
/* 52 */     if (this.transitionFunction != null) {
/* 53 */       LuaString conditionToLua = LuaString.valueOf(condition);
/* 54 */       LuaValue nextStateTable = this.transitionFunction.call(context.getLuaContext(), (LuaValue)conditionToLua);
/* 55 */       if (nextStateTable.istable())
/* 56 */         return new LuaAnimationState((LuaTable)nextStateTable); 
/* 57 */       if (nextStateTable.isnil()) {
/* 58 */         return null;
/*    */       }
/* 60 */       throw new LuaError("the return of function 'transition' must be table or nil");
/*    */     } 
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   private LuaFunction checkLuaFunction(String funcName) {
/* 66 */     LuaValue value = this.luaTable.get(funcName);
/* 67 */     if (value.isfunction())
/* 68 */       return (LuaFunction)value; 
/* 69 */     if (value.isnil()) {
/* 70 */       return null;
/*    */     }
/* 72 */     throw new LuaError("the type of field '" + funcName + "' must be function or nil");
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\LuaAnimationState.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */