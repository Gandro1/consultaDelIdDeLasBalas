/*     */ package com.tacz.guns.entity.sync.core;
/*     */ 
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ 
/*     */ public final class SyncedDataKey<E extends Entity, T> extends Record {
/*     */   private final Pair<ResourceLocation, ResourceLocation> pairKey;
/*     */   private final ResourceLocation id;
/*     */   private final SyncedClassKey<E> classKey;
/*     */   private final IDataSerializer<T> serializer;
/*     */   private final Supplier<T> defaultValueSupplier;
/*     */   private final boolean save;
/*     */   private final boolean persistent;
/*     */   private final SyncMode syncMode;
/*     */   
/*  15 */   public SyncedDataKey(Pair<ResourceLocation, ResourceLocation> pairKey, ResourceLocation id, SyncedClassKey<E> classKey, IDataSerializer<T> serializer, Supplier<T> defaultValueSupplier, boolean save, boolean persistent, SyncMode syncMode) { this.pairKey = pairKey; this.id = id; this.classKey = classKey; this.serializer = serializer; this.defaultValueSupplier = defaultValueSupplier; this.save = save; this.persistent = persistent; this.syncMode = syncMode; } public Pair<ResourceLocation, ResourceLocation> pairKey() { return this.pairKey; } public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/tacz/guns/entity/sync/core/SyncedDataKey;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #15	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/tacz/guns/entity/sync/core/SyncedDataKey;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*  15 */     //   0	7	0	this	Lcom/tacz/guns/entity/sync/core/SyncedDataKey<TE;TT;>; } public ResourceLocation id() { return this.id; } public SyncedClassKey<E> classKey() { return this.classKey; } public IDataSerializer<T> serializer() { return this.serializer; } public Supplier<T> defaultValueSupplier() { return this.defaultValueSupplier; } public boolean save() { return this.save; } public boolean persistent() { return this.persistent; } public SyncMode syncMode() { return this.syncMode; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E extends Entity, T> Builder<E, T> builder(SyncedClassKey<E> entityClass, IDataSerializer<T> serializer) {
/*  20 */     return new Builder<>(entityClass, serializer);
/*     */   }
/*     */   
/*     */   public void setValue(E entity, T value) {
/*  24 */     SyncedEntityData.instance().set(entity, this, value);
/*     */   }
/*     */   
/*     */   public T getValue(E entity) {
/*  28 */     return SyncedEntityData.instance().get(entity, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  33 */     if (this == o) {
/*  34 */       return true;
/*     */     }
/*  36 */     if (o == null || getClass() != o.getClass()) {
/*  37 */       return false;
/*     */     }
/*  39 */     SyncedDataKey<?, ?> that = (SyncedDataKey<?, ?>)o;
/*  40 */     return Objects.equals(this.pairKey, that.pairKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  45 */     return this.pairKey.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum SyncMode
/*     */   {
/*  52 */     NONE(false, false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     ALL(true, true),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     TRACKING_ONLY(true, false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     SELF_ONLY(false, true);
/*     */     
/*     */     final boolean tracking;
/*     */     final boolean self;
/*     */     
/*     */     SyncMode(boolean tracking, boolean self) {
/*  76 */       this.tracking = tracking;
/*  77 */       this.self = self;
/*     */     }
/*     */     
/*     */     public boolean isTracking() {
/*  81 */       return this.tracking;
/*     */     }
/*     */     
/*     */     public boolean isSelf() {
/*  85 */       return this.self;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Builder<E extends Entity, T> {
/*     */     private final SyncedClassKey<E> classKey;
/*     */     private final IDataSerializer<T> serializer;
/*     */     private ResourceLocation id;
/*     */     private Supplier<T> defaultValueSupplier;
/*     */     private boolean save = false;
/*     */     private boolean persistent = true;
/*  96 */     private SyncedDataKey.SyncMode syncMode = SyncedDataKey.SyncMode.ALL;
/*     */     
/*     */     private Builder(SyncedClassKey<E> classKey, IDataSerializer<T> serializer) {
/*  99 */       this.classKey = classKey;
/* 100 */       this.serializer = serializer;
/*     */     }
/*     */     
/*     */     public SyncedDataKey<E, T> build() {
/* 104 */       Validate.notNull(this.id, "Missing 'id' when building synced data key", new Object[0]);
/* 105 */       Validate.notNull(this.defaultValueSupplier, "Missing 'defaultValueSupplier' when building synced data key", new Object[0]);
/* 106 */       Pair<ResourceLocation, ResourceLocation> pairKey = Pair.of(this.classKey.id(), this.id);
/* 107 */       return new SyncedDataKey<>(pairKey, this.id, this.classKey, this.serializer, this.defaultValueSupplier, this.save, this.persistent, this.syncMode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> id(ResourceLocation id) {
/* 114 */       this.id = id;
/* 115 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> id(String id) {
/* 122 */       this.id = new ResourceLocation(id);
/* 123 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Builder<E, T> key(String key) {
/* 133 */       return id(key);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> defaultValueSupplier(Supplier<T> defaultValueSupplier) {
/* 140 */       this.defaultValueSupplier = defaultValueSupplier;
/* 141 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> saveToFile() {
/* 149 */       this.save = true;
/* 150 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> resetOnDeath() {
/* 158 */       this.persistent = false;
/* 159 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<E, T> syncMode(SyncedDataKey.SyncMode mode) {
/* 167 */       this.syncMode = mode;
/* 168 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\SyncedDataKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */