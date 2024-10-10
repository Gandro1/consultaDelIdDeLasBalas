/*    */ package com.tacz.guns.config;
/*    */ 
/*    */ import com.tacz.guns.config.client.KeyConfig;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import com.tacz.guns.config.client.ZoomConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class ClientConfig {
/*    */   public static ForgeConfigSpec init() {
/* 10 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/* 11 */     KeyConfig.init(builder);
/* 12 */     RenderConfig.init(builder);
/* 13 */     ZoomConfig.init(builder);
/* 14 */     return builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\ClientConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */