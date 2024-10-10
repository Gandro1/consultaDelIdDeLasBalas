/*    */ package com.tacz.guns.config.client;
/*    */ 
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class ZoomConfig {
/*    */   public static ForgeConfigSpec.DoubleValue SCREEN_DISTANCE_COEFFICIENT;
/*    */   public static ForgeConfigSpec.DoubleValue ZOOM_SENSITIVITY_BASE_MULTIPLIER;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 10 */     builder.push("Zoom");
/*    */     
/* 12 */     builder.comment("Screen distance coefficient for zoom, using MDV standard, default is MDV133");
/* 13 */     SCREEN_DISTANCE_COEFFICIENT = builder.defineInRange("ScreenDistanceCoefficient", 1.33D, 0.0D, 3.0D);
/*    */     
/* 15 */     builder.comment("Zoom sensitivity is multiplied by this factor");
/* 16 */     ZOOM_SENSITIVITY_BASE_MULTIPLIER = builder.defineInRange("ZoomSensitivityBaseMultiplier", 1.0D, 0.0D, 2.0D);
/*    */     
/* 18 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\client\ZoomConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */