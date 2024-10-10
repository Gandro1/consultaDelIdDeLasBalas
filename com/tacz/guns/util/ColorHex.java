/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public final class ColorHex {
/*  7 */   private static final Pattern COLOR_HEX = Pattern.compile("^#([0-9A-Fa-f]{6})$");
/*    */   
/*    */   public static float[] colorTextToRbgFloatArray(String colorText) {
/* 10 */     int colorHex = colorTextToRbgInt(colorText);
/* 11 */     float r = (colorHex >> 16 & 0xFF) / 255.0F;
/* 12 */     float g = (colorHex >> 8 & 0xFF) / 255.0F;
/* 13 */     float b = (colorHex & 0xFF) / 255.0F;
/* 14 */     return new float[] { r, g, b };
/*    */   }
/*    */   
/*    */   public static int colorTextToRbgInt(String colorText) {
/* 18 */     Matcher matcher = COLOR_HEX.matcher(colorText);
/* 19 */     if (!matcher.find()) {
/* 20 */       return 16777215;
/*    */     }
/* 22 */     return Integer.parseInt(matcher.group(1), 16);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\ColorHex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */