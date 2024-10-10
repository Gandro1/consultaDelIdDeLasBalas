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
/*    */   implements IDataSerializer<Byte>
/*    */ {
/*    */   public void write(FriendlyByteBuf buf, Byte value) {
/* 45 */     buf.writeByte(value.byteValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Byte read(FriendlyByteBuf buf) {
/* 50 */     return Byte.valueOf(buf.readByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public Tag write(Byte value) {
/* 55 */     return (Tag)ByteTag.m_128266_(value.byteValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Byte read(Tag tag) {
/* 60 */     return Byte.valueOf(((ByteTag)tag).m_7063_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */