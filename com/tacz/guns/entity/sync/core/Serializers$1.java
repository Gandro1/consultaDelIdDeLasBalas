/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ import net.minecraft.nbt.ByteTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements IDataSerializer<Boolean>
/*    */ {
/*    */   public void write(FriendlyByteBuf buf, Boolean value) {
/* 23 */     buf.writeBoolean(value.booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean read(FriendlyByteBuf buf) {
/* 28 */     return Boolean.valueOf(buf.readBoolean());
/*    */   }
/*    */ 
/*    */   
/*    */   public Tag write(Boolean value) {
/* 33 */     return (Tag)ByteTag.m_128273_(value.booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean read(Tag tag) {
/* 38 */     return Boolean.valueOf((((ByteTag)tag).m_7063_() != 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */