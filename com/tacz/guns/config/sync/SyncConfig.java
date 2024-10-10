/*    */ package com.tacz.guns.config.sync;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyncConfig
/*    */ {
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> INTERACT_KEY_WHITELIST_BLOCKS;
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> INTERACT_KEY_WHITELIST_ENTITIES;
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> INTERACT_KEY_BLACKLIST_BLOCKS;
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> INTERACT_KEY_BLACKLIST_ENTITIES;
/*    */   public static ForgeConfigSpec.DoubleValue DAMAGE_BASE_MULTIPLIER;
/*    */   public static ForgeConfigSpec.DoubleValue ARMOR_IGNORE_BASE_MULTIPLIER;
/*    */   public static ForgeConfigSpec.DoubleValue HEAD_SHOT_BASE_MULTIPLIER;
/*    */   public static ForgeConfigSpec.DoubleValue WEIGHT_SPEED_MULTIPLIER;
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> HEAD_SHOT_AABB;
/*    */   public static ForgeConfigSpec.IntValue AMMO_BOX_STACK_SIZE;
/*    */   public static ForgeConfigSpec.ConfigValue<List<List<String>>> CLIENT_GUN_PACK_DOWNLOAD_URLS;
/*    */   public static ForgeConfigSpec.BooleanValue ENABLE_CRAWL;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 31 */     interactKey(builder);
/* 32 */     baseMultiplier(builder);
/* 33 */     misc(builder);
/*    */   }
/*    */   
/*    */   public static void interactKey(ForgeConfigSpec.Builder builder) {
/* 37 */     builder.push("interact_key");
/*    */     
/* 39 */     builder.comment("These whitelist blocks can be interacted with when the interact key is pressed");
/* 40 */     INTERACT_KEY_WHITELIST_BLOCKS = builder.define("InteractKeyWhitelistBlocks", Lists.newArrayList());
/*    */     
/* 42 */     builder.comment("These whitelist entities can be interacted with when the interact key is pressed");
/* 43 */     INTERACT_KEY_WHITELIST_ENTITIES = builder.define("InteractKeyWhitelistEntities", Lists.newArrayList());
/*    */     
/* 45 */     builder.comment("These blacklist blocks can be interacted with when the interact key is pressed");
/* 46 */     INTERACT_KEY_BLACKLIST_BLOCKS = builder.define("InteractKeyBlacklistBlocks", Lists.newArrayList());
/*    */     
/* 48 */     builder.comment("These blacklist entities can be interacted with when the interact key is pressed");
/* 49 */     INTERACT_KEY_BLACKLIST_ENTITIES = builder.define("InteractKeyBlacklistEntities", Lists.newArrayList());
/*    */     
/* 51 */     builder.pop();
/*    */   }
/*    */   
/*    */   private static void baseMultiplier(ForgeConfigSpec.Builder builder) {
/* 55 */     builder.push("base_multiplier");
/*    */     
/* 57 */     builder.comment("All base damage number is multiplied by this factor");
/* 58 */     DAMAGE_BASE_MULTIPLIER = builder.defineInRange("DamageBaseMultiplier", 1.0D, 0.0D, Double.MAX_VALUE);
/*    */     
/* 60 */     builder.comment("All armor ignore damage number is multiplied by this factor");
/* 61 */     ARMOR_IGNORE_BASE_MULTIPLIER = builder.defineInRange("ArmorIgnoreBaseMultiplier", 1.0D, 0.0D, Double.MAX_VALUE);
/*    */     
/* 63 */     builder.comment("All head shot damage number is multiplied by this factor");
/* 64 */     HEAD_SHOT_BASE_MULTIPLIER = builder.defineInRange("HeadShotBaseMultiplier", 1.0D, 0.0D, Double.MAX_VALUE);
/*    */     
/* 66 */     builder.comment("The movement speed will decrease per kg of weight. 0.015 means 1.5% speed decrease per kg. Set a negative value to disable this feature");
/* 67 */     WEIGHT_SPEED_MULTIPLIER = builder.defineInRange("WeightSpeedMultiplier", 0.015D, -1.0D, Double.MAX_VALUE);
/*    */     
/* 69 */     builder.pop();
/*    */   }
/*    */   
/*    */   private static void misc(ForgeConfigSpec.Builder builder) {
/* 73 */     builder.push("misc");
/*    */     
/* 75 */     builder.comment("The entity's head hitbox during the headshot");
/* 76 */     builder.comment("Format: touhou_little_maid:maid [-0.5, 1.0, -0.5, 0.5, 1.5, 0.5]");
/* 77 */     HEAD_SHOT_AABB = builder.define("HeadShotAABB", Lists.newArrayList());
/*    */     
/* 79 */     builder.comment("The maximum stack size of ammo that the ammo box can hold");
/* 80 */     AMMO_BOX_STACK_SIZE = builder.defineInRange("AmmoBoxStackSize", 3, 1, 2147483647);
/*    */     
/* 82 */     builder.comment("The gun pack that the client player needs to download, needs to fill in the URL and the SHA1 value of the file");
/* 83 */     CLIENT_GUN_PACK_DOWNLOAD_URLS = builder.define("ClientGunPackDownloadUrls", Lists.newArrayList());
/*    */     
/* 85 */     builder.comment("Whether or not players are allowed to use the crawl feature");
/* 86 */     ENABLE_CRAWL = builder.define("EnableCrawl", true);
/*    */     
/* 88 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\sync\SyncConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */