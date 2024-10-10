/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class DataHolder
/*    */ {
/* 12 */   public Map<SyncedDataKey<?, ?>, DataEntry<?, ?>> dataMap = new HashMap<>();
/*    */   
/*    */   private boolean dirty = false;
/*    */   
/*    */   public <E extends net.minecraft.world.entity.Entity, T> boolean set(E entity, SyncedDataKey<?, ?> key, T value) {
/* 17 */     DataEntry<E, T> entry = (DataEntry<E, T>)this.dataMap.computeIfAbsent(key, DataEntry::new);
/* 18 */     if (!entry.getValue().equals(value)) {
/* 19 */       boolean dirty = (!entity.m_9236_().m_5776_() && entry.getKey().syncMode() != SyncedDataKey.SyncMode.NONE);
/* 20 */       entry.setValue(value, dirty);
/* 21 */       this.dirty = dirty;
/* 22 */       return true;
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public <E extends net.minecraft.world.entity.Entity, T> T get(SyncedDataKey<E, T> key) {
/* 30 */     return (T)((DataEntry)this.dataMap.computeIfAbsent(key, DataEntry::new)).getValue();
/*    */   }
/*    */   
/*    */   public boolean isDirty() {
/* 34 */     return this.dirty;
/*    */   }
/*    */   
/*    */   public void clean() {
/* 38 */     this.dirty = false;
/* 39 */     this.dataMap.forEach((key, entry) -> entry.clean());
/*    */   }
/*    */   
/*    */   public List<DataEntry<?, ?>> gatherDirty() {
/* 43 */     return (List<DataEntry<?, ?>>)this.dataMap.values().stream().filter(DataEntry::isDirty).filter(entry -> (entry.getKey().syncMode() != SyncedDataKey.SyncMode.NONE)).collect(Collectors.toList());
/*    */   }
/*    */   
/*    */   public List<DataEntry<?, ?>> gatherAll() {
/* 47 */     return (List<DataEntry<?, ?>>)this.dataMap.values().stream().filter(entry -> (entry.getKey().syncMode() != SyncedDataKey.SyncMode.NONE)).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\DataHolder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */