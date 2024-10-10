/*     */ package com.tacz.guns.entity.sync.core;
/*     */ 
/*     */ import net.minecraft.nbt.IntTag;
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
/*     */ class null
/*     */   implements IDataSerializer<Integer>
/*     */ {
/*     */   public void write(FriendlyByteBuf buf, Integer value) {
/*  89 */     buf.m_130130_(value.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer read(FriendlyByteBuf buf) {
/*  94 */     return Integer.valueOf(buf.m_130242_());
/*     */   }
/*     */ 
/*     */   
/*     */   public Tag write(Integer value) {
/*  99 */     return (Tag)IntTag.m_128679_(value.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer read(Tag tag) {
/* 104 */     return Integer.valueOf(((IntTag)tag).m_7047_());
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers$4.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */