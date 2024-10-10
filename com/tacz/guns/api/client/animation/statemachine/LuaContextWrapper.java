/*    */ package com.tacz.guns.api.client.animation.statemachine;
/*    */ 
/*    */ import org.luaj.vm2.LuaUserdata;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ 
/*    */ public class LuaContextWrapper<T extends AnimationStateContext> extends AnimationStateContext {
/*    */   private T context;
/*    */   private LuaValue luaContext;
/*    */   
/*    */   public LuaContextWrapper(T context) {
/* 12 */     setContext(context);
/*    */   }
/*    */   
/*    */   public T getContext() {
/* 16 */     return this.context;
/*    */   }
/*    */   
/*    */   public LuaValue getLuaContext() {
/* 20 */     return this.luaContext;
/*    */   }
/*    */   
/*    */   void setContext(T context) {
/* 24 */     this.context = context;
/* 25 */     LuaValue luaValue = this.luaContext; if (luaValue instanceof LuaUserdata) { LuaUserdata userdata = (LuaUserdata)luaValue;
/* 26 */       userdata.m_instance = context; }
/*    */     else
/* 28 */     { this.luaContext = CoerceJavaToLua.coerce(context); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\LuaContextWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */