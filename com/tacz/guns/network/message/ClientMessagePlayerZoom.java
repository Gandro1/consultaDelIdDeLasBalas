/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ClientMessagePlayerZoom
/*    */ {
/*    */   public static void encode(ClientMessagePlayerZoom message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ClientMessagePlayerZoom decode(FriendlyByteBuf buf) {
/* 15 */     return new ClientMessagePlayerZoom();
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerZoom message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 19 */     NetworkEvent.Context context = contextSupplier.get();
/* 20 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 21 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).zoom();
/*    */           });
/*    */     }
/* 29 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerZoom.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */