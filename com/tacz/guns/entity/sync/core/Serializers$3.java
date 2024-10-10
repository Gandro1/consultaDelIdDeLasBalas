/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ import net.minecraft.nbt.ShortTag;
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
/*    */   implements IDataSerializer<Short>
/*    */ {
/*    */   public void write(FriendlyByteBuf buf, Short value) {
/* 67 */     buf.writeShort(value.shortValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Short read(FriendlyByteBuf buf) {
/* 72 */     return Short.valueOf(buf.readShort());
/*    */   }
/*    */ 
/*    */   
/*    */   public Tag write(Short value) {
/* 77 */     return (Tag)ShortTag.m_129258_(value.shortValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public Short read(Tag tag) {
/* 82 */     return Short.valueOf(((ShortTag)tag).m_7053_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers$3.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */