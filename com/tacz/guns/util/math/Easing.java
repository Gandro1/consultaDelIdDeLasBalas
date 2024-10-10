/*   */ package com.tacz.guns.util.math;
/*   */ 
/*   */ public class Easing {
/*   */   public static double easeOutCubic(double x) {
/* 5 */     return 1.0D - Math.pow(1.0D - x, 3.0D);
/*   */   }
/*   */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\math\Easing.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */