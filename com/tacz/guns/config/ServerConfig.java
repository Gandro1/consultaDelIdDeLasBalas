/*    */ package com.tacz.guns.config;
/*    */ 
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerConfig
/*    */ {
/*    */   public static ForgeConfigSpec SERVER_CONFIG_SPEC;
/*    */   
/*    */   public static ForgeConfigSpec init() {
/* 13 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/* 14 */     SyncConfig.init(builder);
/* 15 */     SERVER_CONFIG_SPEC = builder.build();
/* 16 */     return SERVER_CONFIG_SPEC;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\ServerConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */