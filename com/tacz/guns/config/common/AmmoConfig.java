/*    */ package com.tacz.guns.config.common;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class AmmoConfig
/*    */ {
/*    */   public static ForgeConfigSpec.BooleanValue EXPLOSIVE_AMMO_DESTROYS_BLOCK;
/*    */   public static ForgeConfigSpec.BooleanValue EXPLOSIVE_AMMO_FIRE;
/*    */   public static ForgeConfigSpec.BooleanValue EXPLOSIVE_AMMO_KNOCK_BACK;
/*    */   public static ForgeConfigSpec.IntValue EXPLOSIVE_AMMO_VISIBLE_DISTANCE;
/*    */   public static ForgeConfigSpec.ConfigValue<List<String>> PASS_THROUGH_BLOCKS;
/*    */   public static ForgeConfigSpec.BooleanValue DESTROY_GLASS;
/*    */   public static ForgeConfigSpec.BooleanValue IGNITE_BLOCK;
/*    */   public static ForgeConfigSpec.BooleanValue IGNITE_ENTITY;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 19 */     builder.push("ammo");
/*    */     
/* 21 */     builder.comment("Warning: Ammo with explosive properties can break blocks");
/* 22 */     EXPLOSIVE_AMMO_DESTROYS_BLOCK = builder.define("ExplosiveAmmoDestroysBlock", true);
/*    */     
/* 24 */     builder.comment("Warning: Ammo with explosive properties can set the surroundings on fire");
/* 25 */     EXPLOSIVE_AMMO_FIRE = builder.define("ExplosiveAmmoFire", false);
/*    */     
/* 27 */     builder.comment("Ammo with explosive properties can add knockback effect");
/* 28 */     EXPLOSIVE_AMMO_KNOCK_BACK = builder.define("ExplosiveAmmoKnockBack", true);
/*    */     
/* 30 */     builder.comment("The distance at which the explosion effect can be seen");
/* 31 */     EXPLOSIVE_AMMO_VISIBLE_DISTANCE = builder.defineInRange("ExplosiveAmmoVisibleDistance", 192, 0, 2147483647);
/*    */     
/* 33 */     builder.comment("Those blocks that the ammo can pass through");
/* 34 */     PASS_THROUGH_BLOCKS = builder.define("PassThroughBlocks", Lists.newArrayList());
/*    */     
/* 36 */     builder.comment("Whether a ammo can break the glass");
/* 37 */     DESTROY_GLASS = builder.define("DestroyGlass", true);
/*    */     
/* 39 */     builder.comment("Whether a ammo can ignite the block");
/* 40 */     IGNITE_BLOCK = builder.define("IgniteBlock", true);
/*    */     
/* 42 */     builder.comment("Whether a ammo can ignite the entity");
/* 43 */     IGNITE_ENTITY = builder.define("IgniteEntity", true);
/*    */     
/* 45 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\common\AmmoConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */