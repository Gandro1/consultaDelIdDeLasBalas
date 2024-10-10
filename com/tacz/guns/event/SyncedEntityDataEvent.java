/*     */ package com.tacz.guns.event;
/*     */ import com.tacz.guns.entity.sync.core.DataEntry;
/*     */ import com.tacz.guns.entity.sync.core.DataHolder;
/*     */ import com.tacz.guns.entity.sync.core.DataHolderCapabilityProvider;
/*     */ import com.tacz.guns.entity.sync.core.SyncedDataKey;
/*     */ import com.tacz.guns.entity.sync.core.SyncedEntityData;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ServerMessageUpdateEntityData;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinLevelEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public final class SyncedEntityDataEvent {
/*     */   @SubscribeEvent
/*     */   public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
/*  29 */     if (SyncedEntityData.instance().hasSyncedDataKey(((Entity)event.getObject()).getClass())) {
/*  30 */       DataHolderCapabilityProvider provider = new DataHolderCapabilityProvider();
/*  31 */       event.addCapability(new ResourceLocation("tacz", "synced_entity_data"), (ICapabilityProvider)provider);
/*     */       
/*  33 */       if (!(event.getObject() instanceof ServerPlayer)) {
/*  34 */         Objects.requireNonNull(provider); event.addListener(provider::invalidate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStartTracking(PlayerEvent.StartTracking event) {
/*  41 */     if (!event.getEntity().m_9236_().m_5776_()) {
/*  42 */       Entity entity = event.getTarget();
/*  43 */       DataHolder holder = SyncedEntityData.instance().getDataHolder(entity);
/*  44 */       if (holder != null) {
/*  45 */         List<DataEntry<?, ?>> entries = holder.gatherAll();
/*  46 */         entries.removeIf(entry -> !entry.getKey().syncMode().isTracking());
/*  47 */         if (!entries.isEmpty()) {
/*  48 */           NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new ServerMessageUpdateEntityData(entity.m_19879_(), entries));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
/*  56 */     Entity entity = event.getEntity();
/*  57 */     if (entity instanceof Player) { Player player = (Player)entity; if (!event.getLevel().m_5776_()) {
/*  58 */         DataHolder holder = SyncedEntityData.instance().getDataHolder((Entity)player);
/*  59 */         if (holder != null) {
/*  60 */           List<DataEntry<?, ?>> entries = holder.gatherAll();
/*  61 */           if (!entries.isEmpty())
/*  62 */             NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new ServerMessageUpdateEntityData(player.m_19879_(), entries)); 
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerClone(PlayerEvent.Clone event) {
/*  70 */     Player original = event.getOriginal();
/*  71 */     original.reviveCaps();
/*  72 */     DataHolder oldHolder = SyncedEntityData.instance().getDataHolder((Entity)original);
/*  73 */     if (oldHolder == null) {
/*     */       return;
/*     */     }
/*  76 */     original.invalidateCaps();
/*  77 */     Player player = event.getEntity();
/*  78 */     DataHolder newHolder = SyncedEntityData.instance().getDataHolder((Entity)player);
/*  79 */     if (newHolder == null) {
/*     */       return;
/*     */     }
/*  82 */     Map<SyncedDataKey<?, ?>, DataEntry<?, ?>> dataMap = new HashMap<>(oldHolder.dataMap);
/*  83 */     if (event.isWasDeath()) {
/*  84 */       dataMap.entrySet().removeIf(entry -> !((SyncedDataKey)entry.getKey()).persistent());
/*     */     }
/*  86 */     newHolder.dataMap = dataMap;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onServerTick(TickEvent.ServerTickEvent event) {
/*  91 */     SyncedEntityData instance = SyncedEntityData.instance();
/*  92 */     if (event.side != LogicalSide.SERVER) {
/*     */       return;
/*     */     }
/*  95 */     if (event.phase != TickEvent.Phase.END) {
/*     */       return;
/*     */     }
/*  98 */     if (!instance.isDirty()) {
/*     */       return;
/*     */     }
/* 101 */     List<Entity> dirtyEntities = instance.getDirtyEntities();
/* 102 */     if (dirtyEntities.isEmpty()) {
/* 103 */       instance.setDirty(false);
/*     */       return;
/*     */     } 
/* 106 */     for (Entity entity : dirtyEntities) {
/* 107 */       DataHolder holder = instance.getDataHolder(entity);
/* 108 */       if (holder == null || !holder.isDirty()) {
/*     */         continue;
/*     */       }
/* 111 */       List<DataEntry<?, ?>> entries = holder.gatherDirty();
/* 112 */       if (entries.isEmpty()) {
/*     */         continue;
/*     */       }
/* 115 */       List<DataEntry<?, ?>> selfEntries = (List<DataEntry<?, ?>>)entries.stream().filter(entry -> entry.getKey().syncMode().isSelf()).collect(Collectors.toList());
/* 116 */       if (!selfEntries.isEmpty() && entity instanceof ServerPlayer) {
/* 117 */         NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), new ServerMessageUpdateEntityData(entity.m_19879_(), selfEntries));
/*     */       }
/* 119 */       List<DataEntry<?, ?>> trackingEntries = (List<DataEntry<?, ?>>)entries.stream().filter(entry -> entry.getKey().syncMode().isTracking()).collect(Collectors.toList());
/* 120 */       if (!trackingEntries.isEmpty()) {
/* 121 */         NetworkHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new ServerMessageUpdateEntityData(entity.m_19879_(), trackingEntries));
/*     */       }
/* 123 */       holder.clean();
/*     */     } 
/* 125 */     dirtyEntities.clear();
/* 126 */     instance.setDirty(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\SyncedEntityDataEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */