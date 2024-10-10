/*    */ package com.tacz.guns.init;
/*    */ import com.tacz.guns.api.item.gun.GunItemManager;
/*    */ import com.tacz.guns.item.ModernKineticGunItem;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegisterEvent;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModItems {
/* 17 */   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "tacz");
/*    */   
/* 19 */   public static RegistryObject<ModernKineticGunItem> MODERN_KINETIC_GUN = ITEMS.register("modern_kinetic_gun", ModernKineticGunItem::new);
/*    */   
/* 21 */   public static RegistryObject<Item> AMMO = ITEMS.register("ammo", com.tacz.guns.item.AmmoItem::new);
/* 22 */   public static RegistryObject<AttachmentItem> ATTACHMENT = ITEMS.register("attachment", AttachmentItem::new);
/* 23 */   public static RegistryObject<Item> GUN_SMITH_TABLE = ITEMS.register("gun_smith_table", com.tacz.guns.item.GunSmithTableItem::new);
/* 24 */   public static RegistryObject<Item> TARGET = ITEMS.register("target", () -> new BlockItem((Block)ModBlocks.TARGET.get(), new Item.Properties()));
/* 25 */   public static RegistryObject<Item> STATUE = ITEMS.register("statue", () -> new BlockItem((Block)ModBlocks.STATUE.get(), new Item.Properties()));
/* 26 */   public static RegistryObject<Item> AMMO_BOX = ITEMS.register("ammo_box", com.tacz.guns.item.AmmoBoxItem::new);
/* 27 */   public static RegistryObject<Item> TARGET_MINECART = ITEMS.register("target_minecart", com.tacz.guns.item.TargetMinecartItem::new);
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onItemRegister(RegisterEvent event) {
/* 31 */     if (event.getRegistryKey().equals(ForgeRegistries.ITEMS.getRegistryKey()))
/* 32 */       GunItemManager.registerGunItem("modern_kinetic", MODERN_KINETIC_GUN); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModItems.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */