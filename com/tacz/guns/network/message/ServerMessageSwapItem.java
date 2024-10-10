/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.client.event.SwapItemWithOffHand;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerMessageSwapItem
/*    */ {
/*    */   public static void encode(ServerMessageSwapItem message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ServerMessageSwapItem decode(FriendlyByteBuf buf) {
/* 18 */     return new ServerMessageSwapItem();
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageSwapItem message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 22 */     NetworkEvent.Context context = contextSupplier.get();
/* 23 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 24 */       MinecraftForge.EVENT_BUS.post((Event)new SwapItemWithOffHand());
/*    */     }
/* 26 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageSwapItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */