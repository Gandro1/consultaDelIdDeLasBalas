/*    */ package com.tacz.guns.client.animation.statemachine;
/*    */ 
/*    */ import net.minecraft.client.player.Input;
/*    */ 
/*    */ public enum WalkDirection {
/*  6 */   FORWARD,
/*  7 */   SIDE_WAY,
/*  8 */   BACKWARD,
/*  9 */   NONE;
/*    */   
/*    */   public static WalkDirection fromInput(Input input) {
/* 12 */     if (input.f_108568_) {
/* 13 */       return FORWARD;
/*    */     }
/* 15 */     if (input.f_108569_) {
/* 16 */       return BACKWARD;
/*    */     }
/* 18 */     if (input.f_108570_ || input.f_108571_) {
/* 19 */       return SIDE_WAY;
/*    */     }
/* 21 */     return NONE;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\animation\statemachine\WalkDirection.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */