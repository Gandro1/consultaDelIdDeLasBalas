/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.ListTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class DataHolderCapabilityProvider
/*    */   implements ICapabilitySerializable<ListTag> {
/* 18 */   public static final Capability<DataHolder> CAPABILITY = CapabilityManager.get(new CapabilityToken<DataHolder>() {  }
/*    */     );
/* 20 */   private final DataHolder holder = new DataHolder();
/* 21 */   private final LazyOptional<DataHolder> optional = LazyOptional.of(() -> this.holder);
/*    */   
/*    */   public void invalidate() {
/* 24 */     this.optional.invalidate();
/*    */   }
/*    */ 
/*    */   
/*    */   public ListTag serializeNBT() {
/* 29 */     ListTag list = new ListTag();
/* 30 */     this.holder.dataMap.forEach((key, entry) -> {
/*    */           if (key.save()) {
/*    */             CompoundTag keyTag = new CompoundTag();
/*    */             keyTag.m_128359_("ClassKey", key.classKey().id().toString());
/*    */             keyTag.m_128359_("DataKey", key.id().toString());
/*    */             keyTag.m_128365_("Value", entry.writeValue());
/*    */             list.add(keyTag);
/*    */           } 
/*    */         });
/* 39 */     return list;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(ListTag listTag) {
/* 44 */     this.holder.dataMap.clear();
/* 45 */     listTag.forEach(entryTag -> {
/*    */           CompoundTag keyTag = (CompoundTag)entryTag;
/*    */           ResourceLocation classKey = ResourceLocation.m_135820_(keyTag.m_128461_("ClassKey"));
/*    */           ResourceLocation dataKey = ResourceLocation.m_135820_(keyTag.m_128461_("DataKey"));
/*    */           Tag value = keyTag.m_128423_("Value");
/*    */           SyncedClassKey<?> syncedClassKey = SyncedEntityData.instance().getClassKey(classKey);
/*    */           if (syncedClassKey == null) {
/*    */             return;
/*    */           }
/*    */           SyncedDataKey<?, ?> syncedDataKey = SyncedEntityData.instance().getKey(syncedClassKey, dataKey);
/*    */           if (syncedDataKey == null || !syncedDataKey.save()) {
/*    */             return;
/*    */           }
/*    */           DataEntry<?, ?> entry = new DataEntry<>(syncedDataKey);
/*    */           entry.readValue(value);
/*    */           this.holder.dataMap.put(syncedDataKey, entry);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
/* 67 */     return CAPABILITY.orEmpty(cap, this.optional);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\DataHolderCapabilityProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */