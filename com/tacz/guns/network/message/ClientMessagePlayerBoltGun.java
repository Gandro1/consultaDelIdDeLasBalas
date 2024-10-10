/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientMessagePlayerBoltGun
/*    */ {
/*    */   public static void encode(ClientMessagePlayerBoltGun message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ClientMessagePlayerBoltGun decode(FriendlyByteBuf buf) {
/* 18 */     return new ClientMessagePlayerBoltGun();
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerBoltGun message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 22 */     NetworkEvent.Context context = contextSupplier.get();
/* 23 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 24 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).bolt();
/*    */           });
/*    */     }
/* 32 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerBoltGun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */