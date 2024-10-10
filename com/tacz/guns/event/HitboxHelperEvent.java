/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.config.common.OtherConfig;
/*    */ import com.tacz.guns.util.HitboxHelper;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class HitboxHelperEvent {
/*    */   @SubscribeEvent(receiveCanceled = true)
/*    */   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
/* 15 */     if (!((Boolean)OtherConfig.SERVER_HITBOX_LATENCY_FIX.get()).booleanValue()) {
/*    */       return;
/*    */     }
/* 18 */     if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
/* 19 */       HitboxHelper.onPlayerTick(event.player);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent(receiveCanceled = true)
/*    */   public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
/* 25 */     HitboxHelper.onPlayerLoggedOut(event.getEntity());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\HitboxHelperEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */