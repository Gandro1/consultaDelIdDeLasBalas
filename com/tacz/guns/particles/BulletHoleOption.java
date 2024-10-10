/*    */ package com.tacz.guns.particles;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class BulletHoleOption implements ParticleOptions {
/*    */   static {
/* 16 */     CODEC = RecordCodecBuilder.create(builder -> builder.group((App)Codec.INT.fieldOf("dir").forGetter(()), (App)Codec.LONG.fieldOf("pos").forGetter(()), (App)Codec.STRING.fieldOf("ammo_id").forGetter(()), (App)Codec.STRING.fieldOf("gun_id").forGetter(())).apply((Applicative)builder, BulletHoleOption::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<BulletHoleOption> CODEC;
/*    */ 
/*    */   
/* 24 */   public static final ParticleOptions.Deserializer<BulletHoleOption> DESERIALIZER = new ParticleOptions.Deserializer<BulletHoleOption>()
/*    */     {
/*    */       public BulletHoleOption fromCommand(ParticleType<BulletHoleOption> particleType, StringReader reader) throws CommandSyntaxException {
/* 27 */         reader.expect(' ');
/* 28 */         int dir = reader.readInt();
/* 29 */         reader.expect(' ');
/* 30 */         long pos = reader.readLong();
/* 31 */         reader.expect(' ');
/* 32 */         String ammoId = reader.readString();
/* 33 */         reader.expect(' ');
/* 34 */         String gunId = reader.readString();
/* 35 */         return new BulletHoleOption(dir, pos, ammoId, gunId);
/*    */       }
/*    */ 
/*    */       
/*    */       public BulletHoleOption fromNetwork(ParticleType<BulletHoleOption> particleType, FriendlyByteBuf buffer) {
/* 40 */         return new BulletHoleOption(buffer.m_130242_(), buffer.readLong(), buffer.m_130277_(), buffer.m_130277_());
/*    */       }
/*    */     };
/*    */   
/*    */   private final Direction direction;
/*    */   private final BlockPos pos;
/*    */   private final String ammoId;
/*    */   private final String gunId;
/*    */   
/*    */   public BulletHoleOption(int dir, long pos, String ammoId, String gunId) {
/* 50 */     this.direction = Direction.values()[dir];
/* 51 */     this.pos = BlockPos.m_122022_(pos);
/* 52 */     this.ammoId = ammoId;
/* 53 */     this.gunId = gunId;
/*    */   }
/*    */   
/*    */   public BulletHoleOption(Direction dir, BlockPos pos, String ammoId, String gunId) {
/* 57 */     this.direction = dir;
/* 58 */     this.pos = pos;
/* 59 */     this.ammoId = ammoId;
/* 60 */     this.gunId = gunId;
/*    */   }
/*    */   
/*    */   public Direction getDirection() {
/* 64 */     return this.direction;
/*    */   }
/*    */   
/*    */   public BlockPos getPos() {
/* 68 */     return this.pos;
/*    */   }
/*    */   
/*    */   public String getAmmoId() {
/* 72 */     return this.ammoId;
/*    */   }
/*    */   
/*    */   public String getGunId() {
/* 76 */     return this.gunId;
/*    */   }
/*    */ 
/*    */   
/*    */   public ParticleType<?> m_6012_() {
/* 81 */     return (ParticleType)ModParticles.BULLET_HOLE.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7711_(FriendlyByteBuf buffer) {
/* 86 */     buffer.m_130068_((Enum)this.direction);
/* 87 */     buffer.m_130064_(this.pos);
/* 88 */     buffer.m_130070_(this.ammoId);
/* 89 */     buffer.m_130070_(this.gunId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String m_5942_() {
/* 94 */     return "" + ForgeRegistries.PARTICLE_TYPES.getKey(m_6012_()) + " " + ForgeRegistries.PARTICLE_TYPES.getKey(m_6012_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\particles\BulletHoleOption.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */