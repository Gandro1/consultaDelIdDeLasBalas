/*    */ package com.tacz.guns.config.client;
/*    */ 
/*    */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class RenderConfig {
/*    */   public static ForgeConfigSpec.IntValue GUN_LOD_RENDER_DISTANCE;
/*    */   public static ForgeConfigSpec.IntValue BULLET_HOLE_PARTICLE_LIFE;
/*    */   public static ForgeConfigSpec.DoubleValue BULLET_HOLE_PARTICLE_FADE_THRESHOLD;
/*    */   public static ForgeConfigSpec.EnumValue<CrosshairType> CROSSHAIR_TYPE;
/*    */   public static ForgeConfigSpec.DoubleValue HIT_MARKET_START_POSITION;
/*    */   public static ForgeConfigSpec.BooleanValue HEAD_SHOT_DEBUG_HITBOX;
/*    */   public static ForgeConfigSpec.BooleanValue GUN_HUD_ENABLE;
/*    */   public static ForgeConfigSpec.BooleanValue KILL_AMOUNT_ENABLE;
/*    */   public static ForgeConfigSpec.DoubleValue KILL_AMOUNT_DURATION_SECOND;
/*    */   public static ForgeConfigSpec.IntValue TARGET_RENDER_DISTANCE;
/*    */   public static ForgeConfigSpec.BooleanValue FIRST_PERSON_BULLET_TRACER_ENABLE;
/*    */   public static ForgeConfigSpec.BooleanValue DISABLE_INTERACT_HUD_TEXT;
/*    */   public static ForgeConfigSpec.IntValue DAMAGE_COUNTER_RESET_TIME;
/*    */   public static ForgeConfigSpec.BooleanValue DISABLE_MOVEMENT_ATTRIBUTE_FOV;
/*    */   
/*    */   public static void init(ForgeConfigSpec.Builder builder) {
/* 23 */     builder.push("render");
/*    */     
/* 25 */     builder.comment("How far to display the lod model, 0 means always display");
/* 26 */     GUN_LOD_RENDER_DISTANCE = builder.defineInRange("GunLodRenderDistance", 0, 0, 2147483647);
/*    */     
/* 28 */     builder.comment("The existence time of bullet hole particles, in tick");
/* 29 */     BULLET_HOLE_PARTICLE_LIFE = builder.defineInRange("BulletHoleParticleLife", 400, 0, 2147483647);
/*    */     
/* 31 */     builder.comment("The threshold for fading out when rendering bullet hole particles");
/* 32 */     BULLET_HOLE_PARTICLE_FADE_THRESHOLD = builder.defineInRange("BulletHoleParticleFadeThreshold", 0.98D, 0.0D, 1.0D);
/*    */     
/* 34 */     builder.comment("The crosshair when holding a gun");
/* 35 */     CROSSHAIR_TYPE = builder.defineEnum("CrosshairType", (Enum)CrosshairType.DOT_1);
/*    */     
/* 37 */     builder.comment("The starting position of the hit marker");
/* 38 */     HIT_MARKET_START_POSITION = builder.defineInRange("HitMarketStartPosition", 4.0D, -1024.0D, 1024.0D);
/*    */     
/* 40 */     builder.comment("Whether or not to display the head shot's hitbox");
/* 41 */     HEAD_SHOT_DEBUG_HITBOX = builder.define("HeadShotDebugHitbox", false);
/*    */     
/* 43 */     builder.comment("Whether or not to display the gun's HUD");
/* 44 */     GUN_HUD_ENABLE = builder.define("GunHUDEnable", true);
/*    */     
/* 46 */     builder.comment("Whether or not to display the kill amount");
/* 47 */     KILL_AMOUNT_ENABLE = builder.define("KillAmountEnable", true);
/*    */     
/* 49 */     builder.comment("The duration of the kill amount, in second");
/* 50 */     KILL_AMOUNT_DURATION_SECOND = builder.defineInRange("KillAmountDurationSecond", 3.0D, 0.0D, Double.MAX_VALUE);
/*    */     
/* 52 */     builder.comment("The farthest render distance of the target, including minecarts type");
/* 53 */     TARGET_RENDER_DISTANCE = builder.defineInRange("TargetRenderDistance", 128, 0, 2147483647);
/*    */     
/* 55 */     builder.comment("Whether or not to render first person bullet trail");
/* 56 */     FIRST_PERSON_BULLET_TRACER_ENABLE = builder.define("FirstPersonBulletTracerEnable", true);
/*    */     
/* 58 */     builder.comment("Disable the interact hud text in center of the screen");
/* 59 */     DISABLE_INTERACT_HUD_TEXT = builder.define("DisableInteractHudText", false);
/*    */     
/* 61 */     builder.comment("Max time the damage counter will reset");
/* 62 */     DAMAGE_COUNTER_RESET_TIME = builder.defineInRange("DamageCounterResetTime", 2000, 10, 2147483647);
/*    */     
/* 64 */     builder.comment("Disable the fov effect from the movement speed attribute while holding a gun");
/* 65 */     DISABLE_MOVEMENT_ATTRIBUTE_FOV = builder.define("DisableMovementAttributeFov", true);
/*    */     
/* 67 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\config\client\RenderConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */