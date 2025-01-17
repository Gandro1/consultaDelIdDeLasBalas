/*    */ package com.tacz.guns.config.common;
/*    */ 
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class GunConfig {
/*    */   public static ForgeConfigSpec.IntValue DEFAULT_GUN_FIRE_SOUND_DISTANCE;
/*    */   public static ForgeConfigSpec.IntValue DEFAULT_GUN_OTHER_SOUND_DISTANCE;
/*    */   public static ForgeConfigSpec.BooleanValue CREATIVE_PLAYER_CONSUME_AMMO;
/*    */   public static ForgeConfigSpec.BooleanValue AUTO_RELOAD_WHEN_RESPAWN;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 12 */     builder.push("gun");
/*    */     
/* 14 */     builder.comment("The default fire sound range (block)");
/* 15 */     DEFAULT_GUN_FIRE_SOUND_DISTANCE = builder.defineInRange("DefaultGunFireSoundDistance", 64, 0, 2147483647);
/*    */     
/* 17 */     builder.comment("The range (block) of other gun sound, reloading sound etc.");
/* 18 */     DEFAULT_GUN_OTHER_SOUND_DISTANCE = builder.defineInRange("DefaultGunOtherSoundDistance", 16, 0, 2147483647);
/*    */     
/* 20 */     builder.comment("Whether or not the player will consume ammo in creative mode");
/* 21 */     CREATIVE_PLAYER_CONSUME_AMMO = builder.define("CreativePlayerConsumeAmmo", true);
/*    */     
/* 23 */     builder.comment("Auto reload all the guns in player inventory, useful for pvp servers");
/* 24 */     AUTO_RELOAD_WHEN_RESPAWN = builder.define("AutoReloadWhenRespawn", false);
/*    */     
/* 26 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\common\GunConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */