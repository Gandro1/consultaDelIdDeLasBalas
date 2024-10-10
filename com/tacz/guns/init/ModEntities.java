/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import com.tacz.guns.entity.TargetMinecart;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ModEntities
/*    */ {
/* 12 */   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "tacz");
/*    */   
/* 14 */   public static RegistryObject<EntityType<EntityKineticBullet>> BULLET = ENTITY_TYPES.register("bullet", () -> EntityKineticBullet.TYPE);
/* 15 */   public static RegistryObject<EntityType<TargetMinecart>> TARGET_MINECART = ENTITY_TYPES.register("target_minecart", () -> TargetMinecart.TYPE);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModEntities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */