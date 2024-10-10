/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.block.entity.GunSmithTableBlockEntity;
/*    */ import com.tacz.guns.block.entity.StatueBlockEntity;
/*    */ import com.tacz.guns.block.entity.TargetBlockEntity;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModBlocks
/*    */ {
/* 20 */   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "tacz");
/* 21 */   public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "tacz");
/*    */   
/* 23 */   public static RegistryObject<Block> GUN_SMITH_TABLE = BLOCKS.register("gun_smith_table", com.tacz.guns.block.GunSmithTableBlock::new);
/* 24 */   public static RegistryObject<Block> TARGET = BLOCKS.register("target", com.tacz.guns.block.TargetBlock::new);
/* 25 */   public static RegistryObject<Block> STATUE = BLOCKS.register("statue", com.tacz.guns.block.StatueBlock::new);
/*    */   
/* 27 */   public static RegistryObject<BlockEntityType<GunSmithTableBlockEntity>> GUN_SMITH_TABLE_BE = TILE_ENTITIES.register("gun_smith_table", () -> GunSmithTableBlockEntity.TYPE);
/* 28 */   public static RegistryObject<BlockEntityType<TargetBlockEntity>> TARGET_BE = TILE_ENTITIES.register("target", () -> TargetBlockEntity.TYPE);
/* 29 */   public static RegistryObject<BlockEntityType<StatueBlockEntity>> STATUE_BE = TILE_ENTITIES.register("statue", () -> StatueBlockEntity.TYPE);
/* 30 */   public static final TagKey<Block> BULLET_IGNORE_BLOCKS = BlockTags.create(new ResourceLocation("tacz", "bullet_ignore"));
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModBlocks.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */