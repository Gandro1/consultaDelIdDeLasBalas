/*    */ package com.tacz.guns.config;
/*    */ 
/*    */ import com.tacz.guns.config.common.AmmoConfig;
/*    */ import com.tacz.guns.config.common.GunConfig;
/*    */ import com.tacz.guns.config.common.OtherConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public final class CommonConfig {
/*    */   public static ForgeConfigSpec init() {
/* 10 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/* 11 */     GunConfig.init(builder);
/* 12 */     AmmoConfig.init(builder);
/* 13 */     OtherConfig.init(builder);
/* 14 */     return builder.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\CommonConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */