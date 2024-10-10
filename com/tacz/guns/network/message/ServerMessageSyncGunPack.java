/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.client.resource.ClientReloadManager;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import com.tacz.guns.resource.network.DataType;
/*    */ import java.util.EnumMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ public class ServerMessageSyncGunPack
/*    */ {
/*    */   private final EnumMap<DataType, Map<ResourceLocation, String>> cache;
/*    */   
/*    */   public ServerMessageSyncGunPack(EnumMap<DataType, Map<ResourceLocation, String>> cache) {
/* 21 */     this.cache = cache;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageSyncGunPack message, FriendlyByteBuf buf) {
/* 25 */     CommonGunPackNetwork.toNetwork(message.cache, buf);
/*    */   }
/*    */   
/*    */   public static ServerMessageSyncGunPack decode(FriendlyByteBuf buf) {
/* 29 */     return new ServerMessageSyncGunPack(CommonGunPackNetwork.fromNetworkCache(buf));
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageSyncGunPack message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 33 */     NetworkEvent.Context context = contextSupplier.get();
/* 34 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 35 */       context.enqueueWork(() -> doSync(message));
/*    */     }
/* 37 */     context.setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMap<DataType, Map<ResourceLocation, String>> getCache() {
/* 42 */     return this.cache;
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void doSync(ServerMessageSyncGunPack message) {
/* 47 */     ClientReloadManager.cacheAll(message);
/* 48 */     ClientReloadManager.reloadAllPack();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageSyncGunPack.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */