/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public final class EnterServerEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onLoggedInServer(PlayerEvent.PlayerLoggedInEvent event) {
/* 17 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/* 18 */       CommonGunPackNetwork.syncClient(serverPlayer); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\EnterServerEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */