/*    */ package com.tacz.guns.init;
/*    */ import com.tacz.guns.entity.sync.core.DataHolder;
/*    */ import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class CapabilityRegistry {
/*    */   @SubscribeEvent
/*    */   public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
/* 12 */     event.register(DataHolder.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\CapabilityRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */