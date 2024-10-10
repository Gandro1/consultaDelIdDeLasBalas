/*    */ package com.tacz.guns.compat.shouldersurfing;
/*    */ 
/*    */ import net.minecraftforge.fml.ModList;
/*    */ 
/*    */ public final class ShoulderSurfingCompat {
/*    */   private static final String MOD_ID = "shouldersurfing";
/*    */   private static boolean INSTALLED = false;
/*    */   
/*    */   public static void init() {
/* 10 */     INSTALLED = ModList.get().isLoaded("shouldersurfing");
/*    */   }
/*    */   
/*    */   public static boolean showCrosshair() {
/* 14 */     if (INSTALLED) {
/* 15 */       return ShoulderSurfingCompatInner.showCrosshair();
/*    */     }
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\shouldersurfing\ShoulderSurfingCompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */