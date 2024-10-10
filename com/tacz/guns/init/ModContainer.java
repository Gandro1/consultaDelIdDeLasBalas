/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.inventory.GunSmithTableMenu;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ModContainer
/*    */ {
/* 11 */   public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "tacz");
/*    */   
/* 13 */   public static final RegistryObject<MenuType<GunSmithTableMenu>> GUN_SMITH_TABLE_MENU = CONTAINER_TYPE.register("gun_smith_table_menu", () -> GunSmithTableMenu.TYPE);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */