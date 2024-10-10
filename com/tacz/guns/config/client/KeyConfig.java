/*    */ package com.tacz.guns.config.client;
/*    */ 
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class KeyConfig {
/*    */   public static ForgeConfigSpec.BooleanValue HOLD_TO_AIM;
/*    */   public static ForgeConfigSpec.BooleanValue HOLD_TO_CRAWL;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 10 */     builder.push("key");
/*    */     
/* 12 */     builder.comment("True if you want to hold the right mouse button to aim");
/* 13 */     HOLD_TO_AIM = builder.define("HoldToAim", true);
/*    */     
/* 15 */     builder.comment("True if you want to hold the crawl button to crawl");
/* 16 */     HOLD_TO_CRAWL = builder.define("HoldToCrawl", true);
/*    */     
/* 18 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\client\KeyConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */