/*    */ package com.tacz.guns.init;
/*    */ import com.tacz.guns.entity.sync.ModSyncedEntityData;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public final class CommonRegistry {
/*    */   @SubscribeEvent
/*    */   public static void onSetupEvent(FMLCommonSetupEvent event) {
/* 17 */     event.enqueueWork(NetworkHandler::init);
/* 18 */     event.enqueueWork(ModSyncedEntityData::init);
/*    */   }
/*    */   private static boolean LOAD_COMPLETE = false;
/*    */   @SubscribeEvent
/*    */   public static void onLoadComplete(FMLLoadCompleteEvent event) {
/* 23 */     LOAD_COMPLETE = true;
/*    */   }
/*    */   
/*    */   public static boolean isLoadComplete() {
/* 27 */     return LOAD_COMPLETE;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void registerAttributes(EntityAttributeModificationEvent event) {
/* 32 */     event.getTypes().forEach(type -> event.add(type, (Attribute)ModAttributes.BULLET_RESISTANCE.get()));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\CommonRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */