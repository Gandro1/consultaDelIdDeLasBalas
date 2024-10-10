/*    */ package com.tacz.guns.config.common;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ public class OtherConfig
/*    */ {
/*    */   public static ForgeConfigSpec.BooleanValue DEFAULT_PACK_DEBUG;
/*    */   public static ForgeConfigSpec.IntValue TARGET_SOUND_DISTANCE;
/*    */   public static ForgeConfigSpec.DoubleValue SERVER_HITBOX_OFFSET;
/*    */   public static ForgeConfigSpec.BooleanValue SERVER_HITBOX_LATENCY_FIX;
/*    */   public static ForgeConfigSpec.DoubleValue SERVER_HITBOX_LATENCY_MAX_SAVE_MS;
/*    */   public static ForgeConfigSpec.ConfigValue<List<List<String>>> CLIENT_GUN_PACK_DOWNLOAD_URLS;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 18 */     builder.push("other");
/*    */     
/* 20 */     builder.comment("When enabled, the reload command will not overwrite the default model file under config");
/* 21 */     DEFAULT_PACK_DEBUG = builder.define("DefaultPackDebug", false);
/*    */     
/* 23 */     builder.comment("The farthest sound distance of the target, including minecarts type");
/* 24 */     TARGET_SOUND_DISTANCE = builder.defineInRange("TargetSoundDistance", 128, 0, 2147483647);
/*    */     
/* 26 */     serverConfig(builder);
/*    */     
/* 28 */     builder.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void serverConfig(ForgeConfigSpec.Builder builder) {
/* 35 */     builder.comment("DEV: Server hitbox offset (If the hitbox is ahead, fill in a negative number)");
/* 36 */     SERVER_HITBOX_OFFSET = builder.defineInRange("ServerHitboxOffset", 3.0D, -1.7976931348623157E308D, Double.MAX_VALUE);
/*    */     
/* 38 */     builder.comment("Server hitbox latency fix");
/* 39 */     SERVER_HITBOX_LATENCY_FIX = builder.define("ServerHitboxLatencyFix", true);
/*    */     
/* 41 */     builder.comment("The maximum latency (in milliseconds) for the server hitbox latency fix saved");
/* 42 */     SERVER_HITBOX_LATENCY_MAX_SAVE_MS = builder.defineInRange("ServerHitboxLatencyMaxSaveMs", 1000.0D, 250.0D, Double.MAX_VALUE);
/*    */     
/* 44 */     builder.comment("The gun pack that the client player needs to download, needs to fill in the URL and the SHA1 value of the file");
/* 45 */     CLIENT_GUN_PACK_DOWNLOAD_URLS = builder.define("ClientGunPackDownloadUrls", Lists.newArrayList());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\common\OtherConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */