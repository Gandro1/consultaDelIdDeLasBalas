/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.util.CycleTaskHelper;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class ServerTickEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onServerTick(TickEvent.ServerTickEvent event) {
/* 13 */     CycleTaskHelper.tick();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\ServerTickEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */