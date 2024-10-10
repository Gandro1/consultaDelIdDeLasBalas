/*    */ package com.tacz.guns.client.particle;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoParticle;
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.particle.ParticleEngine;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class AmmoParticleSpawner {
/*    */   public static void addParticle(EntityKineticBullet bullet, ResourceLocation gunId) {
/* 22 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           AmmoParticle gunParticle = gunIndex.getParticle();
/*    */           if (gunParticle == null) {
/*    */             TimelessAPI.getClientAmmoIndex(bullet.getAmmoId()).ifPresent(());
/*    */           } else {
/*    */             spawnParticle(bullet, gunParticle);
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void spawnParticle(EntityKineticBullet bullet, AmmoParticle particle) {
/* 41 */     ParticleOptions particleOptions = particle.getParticleOptions();
/* 42 */     if (particleOptions == null) {
/*    */       return;
/*    */     }
/* 45 */     int count = particle.getCount();
/* 46 */     Vector3f delta = particle.getDelta();
/* 47 */     float particleSpeed = particle.getSpeed();
/* 48 */     ParticleEngine particleEngine = (Minecraft.m_91087_()).f_91061_;
/* 49 */     if (count == 0) {
/* 50 */       double xSpeed = (particleSpeed * delta.x());
/* 51 */       double ySpeed = (particleSpeed * delta.y());
/* 52 */       double zSpeed = (particleSpeed * delta.z());
/* 53 */       Particle result = particleEngine.m_107370_(particleOptions, bullet.m_20185_(), bullet.m_20186_(), bullet.m_20189_(), xSpeed, ySpeed, zSpeed);
/* 54 */       if (result != null) {
/* 55 */         result.m_107257_(particle.getLifeTime());
/*    */       }
/*    */     } else {
/* 58 */       RandomSource random = bullet.getRandom();
/* 59 */       Entity owner = bullet.m_19749_();
/* 60 */       for (int i = 0; i < count; i++) {
/* 61 */         createParticle(bullet, particle, random, delta, particleSpeed, owner, particleEngine, particleOptions);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void createParticle(EntityKineticBullet bullet, AmmoParticle particle, RandomSource random, Vector3f delta, float particleSpeed, Entity owner, ParticleEngine particleEngine, ParticleOptions particleOptions) {
/* 67 */     Vec3 deltaMovement = bullet.m_20184_();
/* 68 */     double deltaMovementRandom = random.m_188500_();
/* 69 */     double offsetX = random.m_188583_() * delta.x() + deltaMovementRandom * deltaMovement.f_82479_;
/* 70 */     double offsetY = random.m_188583_() * delta.y() + deltaMovementRandom * deltaMovement.f_82480_;
/* 71 */     double offsetZ = random.m_188583_() * delta.z() + deltaMovementRandom * deltaMovement.f_82481_;
/* 72 */     double xSpeed = random.m_188583_() * particleSpeed;
/* 73 */     double ySpeed = random.m_188583_() * particleSpeed;
/* 74 */     double zSpeed = random.m_188583_() * particleSpeed;
/*    */     
/* 76 */     double posX = bullet.m_20185_() + offsetX;
/* 77 */     double posY = bullet.m_20186_() + offsetY;
/* 78 */     double posZ = bullet.m_20189_() + offsetZ;
/*    */ 
/*    */     
/* 81 */     if (owner == null || owner.m_20275_(posX, posY, posZ) > 9.0D) {
/* 82 */       Particle result = particleEngine.m_107370_(particleOptions, posX, posY, posZ, xSpeed, ySpeed, zSpeed);
/* 83 */       if (result != null)
/* 84 */         result.m_107257_(particle.getLifeTime()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\particle\AmmoParticleSpawner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */