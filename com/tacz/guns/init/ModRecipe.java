/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegisterEvent;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModRecipe
/*    */ {
/* 19 */   public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "tacz");
/* 20 */   public static RegistryObject<RecipeSerializer<?>> GUN_SMITH_TABLE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("gun_smith_table_crafting", com.tacz.guns.crafting.GunSmithTableSerializer::new);
/*    */   
/*    */   public static RecipeType<GunSmithTableRecipe> GUN_SMITH_TABLE_CRAFTING;
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void register(RegisterEvent event) {
/* 26 */     if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_TYPES)) {
/* 27 */       GUN_SMITH_TABLE_CRAFTING = register(event, "tacz:gun_smith_table_crafting");
/*    */     }
/*    */   }
/*    */   
/*    */   private static <T extends net.minecraft.world.item.crafting.Recipe<?>> RecipeType<T> register(RegisterEvent event, final String key) {
/* 32 */     event.register(ForgeRegistries.Keys.RECIPE_TYPES, new ResourceLocation(key), () -> new RecipeType<T>()
/*    */         {
/*    */           public String toString() {
/* 35 */             return key;
/*    */           }
/*    */         });
/* 38 */     return (RecipeType<T>)ForgeRegistries.RECIPE_TYPES.getValue(new ResourceLocation(key));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */