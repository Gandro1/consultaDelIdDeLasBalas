/*    */ package com.tacz.guns.compat.controllable;
/*    */ 
/*    */ import net.minecraftforge.fml.ModList;
/*    */ 
/*    */ public class ControllableCompat {
/*    */   private static final String MOD_ID = "controllable";
/*    */   
/*    */   public static void init() {
/*  9 */     if (ModList.get().isLoaded("controllable"))
/* 10 */       ControllableInner.init(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\controllable\ControllableCompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */