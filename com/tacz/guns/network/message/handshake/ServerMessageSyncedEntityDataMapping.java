/*    */ package com.tacz.guns.network.message.handshake;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.entity.sync.core.SyncedDataKey;
/*    */ import com.tacz.guns.entity.sync.core.SyncedEntityData;
/*    */ import com.tacz.guns.network.LoginIndexHolder;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.CountDownLatch;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public class ServerMessageSyncedEntityDataMapping extends LoginIndexHolder implements IMessage<ServerMessageSyncedEntityDataMapping> {
/* 22 */   public static final Marker HANDSHAKE = MarkerManager.getMarker("TACZ_HANDSHAKE");
/*    */   
/*    */   private Map<ResourceLocation, List<Pair<ResourceLocation, Integer>>> keyMap;
/*    */   
/*    */   public ServerMessageSyncedEntityDataMapping() {}
/*    */   
/*    */   private ServerMessageSyncedEntityDataMapping(Map<ResourceLocation, List<Pair<ResourceLocation, Integer>>> keyMap) {
/* 29 */     this.keyMap = keyMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(ServerMessageSyncedEntityDataMapping message, FriendlyByteBuf buffer) {
/* 34 */     Set<SyncedDataKey<?, ?>> keys = SyncedEntityData.instance().getKeys();
/* 35 */     buffer.writeInt(keys.size());
/* 36 */     keys.forEach(key -> {
/*    */           int id = SyncedEntityData.instance().getInternalId(key);
/*    */           buffer.m_130085_(key.classKey().id());
/*    */           buffer.m_130085_(key.id());
/*    */           buffer.m_130130_(id);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public ServerMessageSyncedEntityDataMapping decode(FriendlyByteBuf buffer) {
/* 46 */     int size = buffer.readInt();
/* 47 */     Map<ResourceLocation, List<Pair<ResourceLocation, Integer>>> keyMap = new HashMap<>();
/* 48 */     for (int i = 0; i < size; i++) {
/* 49 */       ResourceLocation classId = buffer.m_130281_();
/* 50 */       ResourceLocation keyId = buffer.m_130281_();
/* 51 */       int id = buffer.m_130242_();
/* 52 */       ((List<Pair>)keyMap.computeIfAbsent(classId, c -> new ArrayList())).add(Pair.of(keyId, Integer.valueOf(id)));
/*    */     } 
/* 54 */     return new ServerMessageSyncedEntityDataMapping(keyMap);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(ServerMessageSyncedEntityDataMapping message, Supplier<NetworkEvent.Context> supplier) {
/* 59 */     GunMod.LOGGER.debug(HANDSHAKE, "Received synced key mappings from server");
/* 60 */     CountDownLatch block = new CountDownLatch(1);
/* 61 */     ((NetworkEvent.Context)supplier.get()).enqueueWork(() -> {
/*    */           if (!SyncedEntityData.instance().updateMappings(message)) {
/*    */             ((NetworkEvent.Context)supplier.get()).getNetworkManager().m_129507_((Component)Component.m_237113_("Connection closed - [TacZ] Received unknown synced data keys."));
/*    */           }
/*    */           block.countDown();
/*    */         });
/*    */     try {
/* 68 */       block.await();
/* 69 */     } catch (InterruptedException e) {
/* 70 */       e.printStackTrace();
/*    */     } 
/* 72 */     ((NetworkEvent.Context)supplier.get()).setPacketHandled(true);
/* 73 */     NetworkHandler.HANDSHAKE_CHANNEL.reply(new Acknowledge(), supplier.get());
/*    */   }
/*    */   
/*    */   public Map<ResourceLocation, List<Pair<ResourceLocation, Integer>>> getKeyMap() {
/* 77 */     return this.keyMap;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\handshake\ServerMessageSyncedEntityDataMapping.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */