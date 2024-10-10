/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ClientMessagePlayerAim {
/*    */   private final boolean isAim;
/*    */   
/*    */   public ClientMessagePlayerAim(boolean isAim) {
/* 14 */     this.isAim = isAim;
/*    */   }
/*    */   
/*    */   public static void encode(ClientMessagePlayerAim message, FriendlyByteBuf buf) {
/* 18 */     buf.writeBoolean(message.isAim);
/*    */   }
/*    */   
/*    */   public static ClientMessagePlayerAim decode(FriendlyByteBuf buf) {
/* 22 */     return new ClientMessagePlayerAim(buf.readBoolean());
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerAim message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 26 */     NetworkEvent.Context context = contextSupplier.get();
/* 27 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 28 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).aim(message.isAim);
/*    */           });
/*    */     }
/* 36 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerAim.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */