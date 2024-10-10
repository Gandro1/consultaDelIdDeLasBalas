/*    */ package com.tacz.guns.client.init;
/*    */ import com.tacz.guns.client.particle.BulletHoleParticle;
/*    */ import com.tacz.guns.init.ModParticles;
/*    */ import net.minecraft.client.particle.ParticleProvider;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ 
/*    */ @EventBusSubscriber(modid = "tacz", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ParticleFactoryRegistry {
/*    */   @SubscribeEvent
/*    */   public static void onRegisterParticleFactory(RegisterParticleProvidersEvent event) {
/* 15 */     event.registerSpecial((ParticleType)ModParticles.BULLET_HOLE.get(), (ParticleProvider)new BulletHoleParticle.Provider());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\init\ParticleFactoryRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */