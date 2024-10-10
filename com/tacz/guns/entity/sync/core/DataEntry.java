/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class DataEntry<E extends Entity, T> {
/*    */   private final SyncedDataKey<E, T> key;
/*    */   private T value;
/*    */   private boolean dirty;
/*    */   
/*    */   public DataEntry(SyncedDataKey<E, T> key) {
/* 14 */     this.key = key;
/* 15 */     this.value = key.defaultValueSupplier().get();
/*    */   }
/*    */   
/*    */   public static DataEntry<?, ?> read(FriendlyByteBuf buffer) {
/* 19 */     SyncedDataKey<?, ?> key = SyncedEntityData.instance().getKey(buffer.m_130242_());
/* 20 */     Validate.notNull(key, "Synced key does not exist for id", new Object[0]);
/* 21 */     DataEntry<?, ?> entry = new DataEntry<>(key);
/* 22 */     entry.readValue(buffer);
/* 23 */     return entry;
/*    */   }
/*    */   
/*    */   public SyncedDataKey<E, T> getKey() {
/* 27 */     return this.key;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 31 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value, boolean dirty) {
/* 35 */     this.value = value;
/* 36 */     this.dirty = dirty;
/*    */   }
/*    */   
/*    */   public boolean isDirty() {
/* 40 */     return this.dirty;
/*    */   }
/*    */   
/*    */   public void clean() {
/* 44 */     this.dirty = false;
/*    */   }
/*    */   
/*    */   public void write(FriendlyByteBuf buffer) {
/* 48 */     int id = SyncedEntityData.instance().getInternalId(this.key);
/* 49 */     buffer.m_130130_(id);
/* 50 */     this.key.serializer().write(buffer, this.value);
/*    */   }
/*    */   
/*    */   public void readValue(FriendlyByteBuf buffer) {
/* 54 */     this.value = getKey().serializer().read(buffer);
/*    */   }
/*    */   
/*    */   public Tag writeValue() {
/* 58 */     return this.key.serializer().write(this.value);
/*    */   }
/*    */   
/*    */   public void readValue(Tag nbt) {
/* 62 */     this.value = this.key.serializer().read(nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\DataEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */