/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ class ModParticleType<T extends ParticleOptions>
/*    */   extends ParticleType<T>
/*    */ {
/*    */   private final Codec<T> codec;
/*    */   
/*    */   public ModParticleType(boolean overrideLimiter, ParticleOptions.Deserializer<T> deserializer, Codec<T> codec) {
/* 23 */     super(overrideLimiter, deserializer);
/* 24 */     this.codec = codec;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Codec<T> m_7652_() {
/* 29 */     return this.codec;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModParticles$ModParticleType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */