/*    */ package com.tacz.guns;
/*    */ import com.tacz.guns.config.CommonConfig;
/*    */ import com.tacz.guns.init.ModBlocks;
/*    */ import com.tacz.guns.init.ModContainer;
/*    */ import com.tacz.guns.init.ModCreativeTabs;
/*    */ import com.tacz.guns.init.ModEntities;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import com.tacz.guns.init.ModParticles;
/*    */ import com.tacz.guns.init.ModSounds;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ import net.minecraftforge.fml.config.IConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ @Mod("tacz")
/*    */ public class GunMod {
/* 20 */   public static final Logger LOGGER = LogManager.getLogger("tacz");
/*    */   
/*    */   public static final String MOD_ID = "tacz";
/*    */   
/*    */   public static final String DEFAULT_GUN_PACK_NAME = "tacz_default_gun";
/*    */   
/*    */   public GunMod() {
/* 27 */     ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, (IConfigSpec)CommonConfig.init());
/* 28 */     ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, (IConfigSpec)ServerConfig.init());
/* 29 */     ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, (IConfigSpec)ClientConfig.init());
/*    */     
/* 31 */     IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
/* 32 */     ModBlocks.BLOCKS.register(bus);
/* 33 */     ModBlocks.TILE_ENTITIES.register(bus);
/* 34 */     ModCreativeTabs.TABS.register(bus);
/* 35 */     ModItems.ITEMS.register(bus);
/* 36 */     ModEntities.ENTITY_TYPES.register(bus);
/* 37 */     ModRecipe.RECIPE_SERIALIZERS.register(bus);
/* 38 */     ModContainer.CONTAINER_TYPE.register(bus);
/* 39 */     ModSounds.SOUNDS.register(bus);
/* 40 */     ModParticles.PARTICLE_TYPES.register(bus);
/* 41 */     ModAttributes.ATTRIBUTES.register(bus);
/*    */     
/* 43 */     registerDefaultExtraGunPack();
/* 44 */     AttachmentPropertyManager.registerModifier();
/*    */   }
/*    */   
/*    */   private static void registerDefaultExtraGunPack() {
/* 48 */     String jarDefaultPackPath = String.format("/assets/%s/custom/%s", new Object[] { "tacz", "tacz_default_gun" });
/* 49 */     ResourceManager.registerExtraGunPack(GunMod.class, jarDefaultPackPath);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\GunMod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */