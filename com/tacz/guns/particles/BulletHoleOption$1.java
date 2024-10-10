/*    */ package com.tacz.guns.particles;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
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
/*    */ class null
/*    */   implements ParticleOptions.Deserializer<BulletHoleOption>
/*    */ {
/*    */   public BulletHoleOption fromCommand(ParticleType<BulletHoleOption> particleType, StringReader reader) throws CommandSyntaxException {
/* 27 */     reader.expect(' ');
/* 28 */     int dir = reader.readInt();
/* 29 */     reader.expect(' ');
/* 30 */     long pos = reader.readLong();
/* 31 */     reader.expect(' ');
/* 32 */     String ammoId = reader.readString();
/* 33 */     reader.expect(' ');
/* 34 */     String gunId = reader.readString();
/* 35 */     return new BulletHoleOption(dir, pos, ammoId, gunId);
/*    */   }
/*    */ 
/*    */   
/*    */   public BulletHoleOption fromNetwork(ParticleType<BulletHoleOption> particleType, FriendlyByteBuf buffer) {
/* 40 */     return new BulletHoleOption(buffer.m_130242_(), buffer.readLong(), buffer.m_130277_(), buffer.m_130277_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\particles\BulletHoleOption$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */