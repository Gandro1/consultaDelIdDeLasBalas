/*     */ package com.tacz.guns.entity.sync.core;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Builder<E extends Entity, T>
/*     */ {
/*     */   private final SyncedClassKey<E> classKey;
/*     */   private final IDataSerializer<T> serializer;
/*     */   private ResourceLocation id;
/*     */   private Supplier<T> defaultValueSupplier;
/*     */   private boolean save = false;
/*     */   private boolean persistent = true;
/*  96 */   private SyncedDataKey.SyncMode syncMode = SyncedDataKey.SyncMode.ALL;
/*     */   
/*     */   private Builder(SyncedClassKey<E> classKey, IDataSerializer<T> serializer) {
/*  99 */     this.classKey = classKey;
/* 100 */     this.serializer = serializer;
/*     */   }
/*     */   
/*     */   public SyncedDataKey<E, T> build() {
/* 104 */     Validate.notNull(this.id, "Missing 'id' when building synced data key", new Object[0]);
/* 105 */     Validate.notNull(this.defaultValueSupplier, "Missing 'defaultValueSupplier' when building synced data key", new Object[0]);
/* 106 */     Pair<ResourceLocation, ResourceLocation> pairKey = Pair.of(this.classKey.id(), this.id);
/* 107 */     return new SyncedDataKey<>(pairKey, this.id, this.classKey, this.serializer, this.defaultValueSupplier, this.save, this.persistent, this.syncMode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> id(ResourceLocation id) {
/* 114 */     this.id = id;
/* 115 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> id(String id) {
/* 122 */     this.id = new ResourceLocation(id);
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Builder<E, T> key(String key) {
/* 133 */     return id(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> defaultValueSupplier(Supplier<T> defaultValueSupplier) {
/* 140 */     this.defaultValueSupplier = defaultValueSupplier;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> saveToFile() {
/* 149 */     this.save = true;
/* 150 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> resetOnDeath() {
/* 158 */     this.persistent = false;
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder<E, T> syncMode(SyncedDataKey.SyncMode mode) {
/* 167 */     this.syncMode = mode;
/* 168 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\SyncedDataKey$Builder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */