/*     */ package com.tacz.guns.entity.sync.core;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.tacz.guns.GunMod;
/*     */ import com.tacz.guns.network.message.handshake.ServerMessageSyncedEntityDataMapping;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Reference2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraftforge.fml.util.thread.EffectiveSide;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ public class SyncedEntityData {
/*  27 */   private static final Marker SYNCED_ENTITY_DATA_MARKER = MarkerManager.getMarker("SYNCED_ENTITY_DATA_TAC_COPY");
/*     */   
/*     */   private static SyncedEntityData INSTANCE;
/*  30 */   private final Set<SyncedClassKey<?>> registeredClassKeys = new HashSet<>();
/*  31 */   private final Object2ObjectMap<ResourceLocation, SyncedClassKey<?>> idToClassKey = (Object2ObjectMap<ResourceLocation, SyncedClassKey<?>>)new Object2ObjectOpenHashMap();
/*  32 */   private final Object2ObjectMap<String, SyncedClassKey<?>> classNameToClassKey = (Object2ObjectMap<String, SyncedClassKey<?>>)new Object2ObjectOpenHashMap();
/*  33 */   private final Object2BooleanMap<String> clientClassNameCapabilityCache = (Object2BooleanMap<String>)new Object2BooleanOpenHashMap();
/*  34 */   private final Object2BooleanMap<String> serverClassNameCapabilityCache = (Object2BooleanMap<String>)new Object2BooleanOpenHashMap();
/*     */   
/*  36 */   private final Set<SyncedDataKey<?, ?>> registeredDataKeys = new HashSet<>();
/*  37 */   private final Reference2ObjectMap<SyncedClassKey<?>, HashMap<ResourceLocation, SyncedDataKey<?, ?>>> classToKeys = (Reference2ObjectMap<SyncedClassKey<?>, HashMap<ResourceLocation, SyncedDataKey<?, ?>>>)new Reference2ObjectOpenHashMap();
/*  38 */   private final Reference2IntMap<SyncedDataKey<?, ?>> internalIds = (Reference2IntMap<SyncedDataKey<?, ?>>)new Reference2IntOpenHashMap();
/*  39 */   private final Int2ReferenceMap<SyncedDataKey<?, ?>> syncedIdToKey = (Int2ReferenceMap<SyncedDataKey<?, ?>>)new Int2ReferenceOpenHashMap();
/*     */   
/*  41 */   private final AtomicInteger nextIdTracker = new AtomicInteger();
/*  42 */   private final List<Entity> dirtyEntities = new ArrayList<>();
/*     */ 
/*     */   
/*     */   private boolean dirty = false;
/*     */ 
/*     */   
/*     */   public static SyncedEntityData instance() {
/*  49 */     if (INSTANCE == null) {
/*  50 */       INSTANCE = new SyncedEntityData();
/*     */     }
/*  52 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   private <E extends Entity> void registerClassKey(SyncedClassKey<E> classKey) {
/*  56 */     if (!this.registeredClassKeys.contains(classKey)) {
/*  57 */       this.registeredClassKeys.add(classKey);
/*  58 */       this.idToClassKey.put(classKey.id(), classKey);
/*  59 */       this.classNameToClassKey.put(classKey.entityClass().getName(), classKey);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized <E extends Entity, T> void registerDataKey(SyncedDataKey<E, T> dataKey) {
/*  69 */     ResourceLocation keyId = dataKey.id();
/*  70 */     SyncedClassKey<E> classKey = dataKey.classKey();
/*  71 */     if (CommonRegistry.isLoadComplete()) {
/*  72 */       throw new IllegalStateException(String.format("Tried to register synced data key %s for %s after game initialization", new Object[] { keyId, classKey.id() }));
/*     */     }
/*  74 */     if (this.registeredDataKeys.contains(dataKey)) {
/*  75 */       throw new IllegalArgumentException(String.format("The synced data key %s for %s is already registered", new Object[] { keyId, classKey.id() }));
/*     */     }
/*     */     
/*  78 */     registerClassKey(dataKey.classKey());
/*  79 */     this.registeredDataKeys.add(dataKey);
/*  80 */     ((HashMap<ResourceLocation, SyncedDataKey<E, T>>)this.classToKeys.computeIfAbsent(classKey, c -> new HashMap<>())).put(keyId, dataKey);
/*  81 */     int nextId = this.nextIdTracker.getAndIncrement();
/*  82 */     this.internalIds.put(dataKey, nextId);
/*  83 */     this.syncedIdToKey.put(nextId, dataKey);
/*  84 */     GunMod.LOGGER.info(SYNCED_ENTITY_DATA_MARKER, "Registered synced data key {} for {}", dataKey.id(), classKey.id());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <E extends Entity, T> void set(E entity, SyncedDataKey<?, ?> key, T value) {
/*  95 */     if (!this.registeredDataKeys.contains(key)) {
/*  96 */       String keys = this.registeredDataKeys.stream().map(k -> k.pairKey().toString()).collect(Collectors.joining(",", "[", "]"));
/*  97 */       GunMod.LOGGER.info(SYNCED_ENTITY_DATA_MARKER, "Registered keys before throwing exception: {}", keys);
/*  98 */       throw new IllegalArgumentException(String.format("The synced data key %s for %s is not registered!", new Object[] { key.id(), key.classKey().id() }));
/*     */     } 
/* 100 */     DataHolder holder = getDataHolder((Entity)entity);
/* 101 */     if (holder != null && holder.set(entity, key, value) && 
/* 102 */       !entity.m_9236_().m_5776_()) {
/* 103 */       this.dirty = true;
/* 104 */       this.dirtyEntities.add((Entity)entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <E extends Entity, T> T get(E entity, SyncedDataKey<E, T> key) {
/* 117 */     if (!this.registeredDataKeys.contains(key)) {
/* 118 */       String keys = this.registeredDataKeys.stream().map(k -> k.pairKey().toString()).collect(Collectors.joining(",", "[", "]"));
/* 119 */       GunMod.LOGGER.info(SYNCED_ENTITY_DATA_MARKER, "Registered keys before throwing exception: {}", keys);
/* 120 */       throw new IllegalArgumentException(String.format("The synced data key %s for %s is not registered!", new Object[] { key.id(), key.classKey().id() }));
/*     */     } 
/* 122 */     DataHolder holder = getDataHolder((Entity)entity);
/* 123 */     return (holder != null) ? holder.<E, T>get(key) : key.defaultValueSupplier().get();
/*     */   }
/*     */   
/*     */   public int getInternalId(SyncedDataKey<?, ?> key) {
/* 127 */     return this.internalIds.getInt(key);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public SyncedClassKey<?> getClassKey(ResourceLocation id) {
/* 132 */     return (SyncedClassKey)this.idToClassKey.get(id);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public SyncedDataKey<?, ?> getKey(int id) {
/* 137 */     return (SyncedDataKey<?, ?>)this.syncedIdToKey.get(id);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public SyncedDataKey<?, ?> getKey(SyncedClassKey<?> classKey, ResourceLocation dataKey) {
/* 142 */     Map<ResourceLocation, SyncedDataKey<?, ?>> keys = (Map<ResourceLocation, SyncedDataKey<?, ?>>)(instance()).classToKeys.get(classKey);
/* 143 */     if (keys == null) {
/* 144 */       return null;
/*     */     }
/* 146 */     return keys.get(dataKey);
/*     */   }
/*     */   
/*     */   public Set<SyncedDataKey<?, ?>> getKeys() {
/* 150 */     return (Set<SyncedDataKey<?, ?>>)ImmutableSet.copyOf(this.registeredDataKeys);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DataHolder getDataHolder(Entity entity) {
/* 155 */     return entity.getCapability(DataHolderCapabilityProvider.CAPABILITY, null).resolve().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSyncedDataKey(Class<? extends Entity> entityClass) {
/* 162 */     Object2BooleanMap<String> cache = EffectiveSide.get().isClient() ? this.clientClassNameCapabilityCache : this.serverClassNameCapabilityCache;
/*     */ 
/*     */     
/* 165 */     return cache.computeIfAbsent(entityClass.getName(), c -> {
/*     */           for (Class<?> targetClass = entityClass; !targetClass.isAssignableFrom(Entity.class); targetClass = targetClass.getSuperclass()) {
/*     */             if (this.classNameToClassKey.containsKey(targetClass.getName())) {
/*     */               return true;
/*     */             }
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateMappings(ServerMessageSyncedEntityDataMapping message) {
/* 179 */     this.syncedIdToKey.clear();
/*     */     
/* 181 */     List<Pair<ResourceLocation, ResourceLocation>> missingKeys = new ArrayList<>();
/* 182 */     message.getKeyMap().forEach((classId, list) -> {
/*     */           SyncedClassKey<?> classKey = (SyncedClassKey)this.idToClassKey.get(classId);
/*     */ 
/*     */           
/*     */           if (classKey == null || !this.classToKeys.containsKey(classKey)) {
/*     */             list.forEach(());
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */ 
/*     */           
/*     */           Map<ResourceLocation, SyncedDataKey<?, ?>> keys = (Map<ResourceLocation, SyncedDataKey<?, ?>>)this.classToKeys.get(classKey);
/*     */ 
/*     */           
/*     */           list.forEach(());
/*     */         });
/*     */     
/* 200 */     if (!missingKeys.isEmpty()) {
/* 201 */       String keys = missingKeys.stream().map(Object::toString).collect(Collectors.joining(",", "[", "]"));
/* 202 */       GunMod.LOGGER.info(SYNCED_ENTITY_DATA_MARKER, "Received unknown synced keys: {}", keys);
/*     */     } 
/*     */     
/* 205 */     return missingKeys.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean isDirty() {
/* 209 */     return this.dirty;
/*     */   }
/*     */   
/*     */   public void setDirty(boolean dirty) {
/* 213 */     this.dirty = dirty;
/*     */   }
/*     */   
/*     */   public List<Entity> getDirtyEntities() {
/* 217 */     return this.dirtyEntities;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\SyncedEntityData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */