/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ClientMessagePlayerCrawl {
/*    */   private final boolean isCrawl;
/*    */   
/*    */   public ClientMessagePlayerCrawl(boolean isCrawl) {
/* 15 */     this.isCrawl = isCrawl;
/*    */   }
/*    */   
/*    */   public static void encode(ClientMessagePlayerCrawl message, FriendlyByteBuf buf) {
/* 19 */     buf.writeBoolean(message.isCrawl);
/*    */   }
/*    */   
/*    */   public static ClientMessagePlayerCrawl decode(FriendlyByteBuf buf) {
/* 23 */     return new ClientMessagePlayerCrawl(buf.readBoolean());
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessagePlayerCrawl message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 27 */     NetworkEvent.Context context = contextSupplier.get();
/* 28 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 29 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender();
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             if (!((Boolean)SyncConfig.ENABLE_CRAWL.get()).booleanValue()) {
/*    */               return;
/*    */             }
/*    */             IGunOperator.fromLivingEntity((LivingEntity)entity).crawl(message.isCrawl);
/*    */           });
/*    */     }
/* 40 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessagePlayerCrawl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */