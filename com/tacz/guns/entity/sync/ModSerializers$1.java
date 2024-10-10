/*    */ package com.tacz.guns.entity.sync;
/*    */ 
/*    */ import com.tacz.guns.api.entity.ReloadState;
/*    */ import com.tacz.guns.entity.sync.core.IDataSerializer;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ 
/*    */ class null
/*    */   implements IDataSerializer<ReloadState>
/*    */ {
/*    */   public void write(FriendlyByteBuf buf, ReloadState value) {
/* 13 */     buf.writeInt(value.getStateType().ordinal());
/* 14 */     buf.writeLong(value.getCountDown());
/*    */   }
/*    */ 
/*    */   
/*    */   public ReloadState read(FriendlyByteBuf buf) {
/* 19 */     ReloadState reloadState = new ReloadState();
/* 20 */     reloadState.setStateType(ReloadState.StateType.values()[buf.readInt()]);
/* 21 */     reloadState.setCountDown(buf.readLong());
/* 22 */     return reloadState;
/*    */   }
/*    */ 
/*    */   
/*    */   public Tag write(ReloadState value) {
/* 27 */     CompoundTag compound = new CompoundTag();
/* 28 */     compound.m_128359_("StateType", value.getStateType().toString());
/* 29 */     compound.m_128356_("CountDown", value.getCountDown());
/* 30 */     return (Tag)compound;
/*    */   }
/*    */ 
/*    */   
/*    */   public ReloadState read(Tag nbt) {
/* 35 */     CompoundTag compound = (CompoundTag)nbt;
/*    */     try {
/* 37 */       ReloadState.StateType stateType = ReloadState.StateType.valueOf(compound.m_128461_("StateType"));
/* 38 */       long countDown = compound.m_128454_("CountDown");
/* 39 */       ReloadState reloadState = new ReloadState();
/* 40 */       reloadState.setStateType(stateType);
/* 41 */       reloadState.setCountDown(countDown);
/* 42 */       return reloadState;
/* 43 */     } catch (IllegalArgumentException illegalArgumentException) {
/*    */       
/* 45 */       return new ReloadState();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\ModSerializers$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */