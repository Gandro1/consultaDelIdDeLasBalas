/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.entity.sync.core.DataEntry;
/*    */ import com.tacz.guns.entity.sync.core.SyncedEntityData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageUpdateEntityData
/*    */ {
/*    */   private final int entityId;
/*    */   private final List<DataEntry<?, ?>> entries;
/*    */   
/*    */   public ServerMessageUpdateEntityData(int entityId, List<DataEntry<?, ?>> entries) {
/* 22 */     this.entityId = entityId;
/* 23 */     this.entries = entries;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageUpdateEntityData message, FriendlyByteBuf buffer) {
/* 27 */     buffer.m_130130_(message.entityId);
/* 28 */     buffer.m_130130_(message.entries.size());
/* 29 */     message.entries.forEach(entry -> entry.write(buffer));
/*    */   }
/*    */   
/*    */   public static ServerMessageUpdateEntityData decode(FriendlyByteBuf buffer) {
/* 33 */     int entityId = buffer.m_130242_();
/* 34 */     int size = buffer.m_130242_();
/* 35 */     List<DataEntry<?, ?>> entries = new ArrayList<>();
/* 36 */     for (int i = 0; i < size; i++) {
/* 37 */       entries.add(DataEntry.read(buffer));
/*    */     }
/* 39 */     return new ServerMessageUpdateEntityData(entityId, entries);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageUpdateEntityData message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 43 */     NetworkEvent.Context context = contextSupplier.get();
/* 44 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 45 */       context.enqueueWork(() -> onHandle(message));
/*    */     }
/* 47 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void onHandle(ServerMessageUpdateEntityData message) {
/* 52 */     ClientLevel clientLevel = (Minecraft.m_91087_()).f_91073_;
/* 53 */     if (clientLevel == null) {
/*    */       return;
/*    */     }
/* 56 */     Entity entity = clientLevel.m_6815_(message.entityId);
/* 57 */     if (entity == null) {
/*    */       return;
/*    */     }
/* 60 */     SyncedEntityData instance = SyncedEntityData.instance();
/* 61 */     message.entries.forEach(entry -> instance.set(entity, entry.getKey(), entry.getValue()));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageUpdateEntityData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */