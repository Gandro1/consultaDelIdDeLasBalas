/*     */ package com.tacz.guns.entity.sync.core;
/*     */ 
/*     */ import net.minecraft.nbt.LongTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
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
/*     */ class null
/*     */   implements IDataSerializer<Long>
/*     */ {
/*     */   public void write(FriendlyByteBuf buf, Long value) {
/* 111 */     buf.writeLong(value.longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Long read(FriendlyByteBuf buf) {
/* 116 */     return Long.valueOf(buf.readLong());
/*     */   }
/*     */ 
/*     */   
/*     */   public Tag write(Long value) {
/* 121 */     return (Tag)LongTag.m_128882_(value.longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Long read(Tag tag) {
/* 126 */     return Long.valueOf(((LongTag)tag).m_7046_());
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers$5.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */