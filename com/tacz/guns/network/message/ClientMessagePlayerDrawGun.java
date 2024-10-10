/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientMessagePlayerDrawGun
/*    */ {
/*    */   public static void encode(ClientMessagePlayerDrawGun message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ClientMessagePlayerDrawGun decode(FriendlyByteBuf buf) {
/* 19 */     return new ClientMessagePlayerDrawGun();
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerDrawGun message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 23 */     NetworkEvent.Context context = contextSupplier.get();
/* 24 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 25 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             Inventory inventory = entity.m_150109_();
/*    */             int selected = inventory.f_35977_;
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).draw(());
/*    */           });
/*    */     }
/* 35 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerDrawGun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */