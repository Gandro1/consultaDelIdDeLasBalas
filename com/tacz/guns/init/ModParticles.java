/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.tacz.guns.particles.BulletHoleOption;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ModParticles
/*    */ {
/* 14 */   public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "tacz");
/*    */   
/* 16 */   public static final RegistryObject<ParticleType<BulletHoleOption>> BULLET_HOLE = PARTICLE_TYPES.register("bullet_hole", () -> new ModParticleType<>(false, BulletHoleOption.DESERIALIZER, BulletHoleOption.CODEC));
/*    */   
/*    */   private static class ModParticleType<T extends ParticleOptions>
/*    */     extends ParticleType<T> {
/*    */     private final Codec<T> codec;
/*    */     
/*    */     public ModParticleType(boolean overrideLimiter, ParticleOptions.Deserializer<T> deserializer, Codec<T> codec) {
/* 23 */       super(overrideLimiter, deserializer);
/* 24 */       this.codec = codec;
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public Codec<T> m_7652_() {
/* 29 */       return this.codec;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModParticles.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */