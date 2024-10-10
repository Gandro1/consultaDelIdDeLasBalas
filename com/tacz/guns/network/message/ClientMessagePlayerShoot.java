/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientMessagePlayerShoot
/*    */ {
/*    */   public static void encode(ClientMessagePlayerShoot message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ClientMessagePlayerShoot decode(FriendlyByteBuf buf) {
/* 18 */     return new ClientMessagePlayerShoot();
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerShoot message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 22 */     NetworkEvent.Context context = contextSupplier.get();
/* 23 */     if (context.getDirection().getReceptionSide().isServer())
/* 24 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null)
/*    */               return; 
/*    */             Objects.requireNonNull(entity);
/*    */             Objects.requireNonNull(entity);
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).shoot(entity::m_146909_, entity::m_146908_);
/*    */           }); 
/* 32 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerShoot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */