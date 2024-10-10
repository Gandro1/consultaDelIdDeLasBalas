/*    */ package com.tacz.guns.client.sound;
/*    */ 
/*    */ import com.mojang.blaze3d.audio.SoundBuffer;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ public class GunSoundInstance extends EntityBoundSoundInstance {
/*    */   private final ResourceLocation registryName;
/*    */   
/*    */   public GunSoundInstance(SoundEvent soundEvent, SoundSource source, float volume, float pitch, Entity entity, int soundDistance, ResourceLocation registryName) {
/* 19 */     super(soundEvent, source, volume, pitch, entity, 943L);
/* 20 */     this.f_119580_ = SoundInstance.Attenuation.NONE;
/* 21 */     this.registryName = registryName;
/* 22 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 23 */     if (player != null) {
/* 24 */       this.f_119573_ = volume * (1.0F - Math.min(1.0F, (float)Math.sqrt(player.m_20275_(this.f_119575_, this.f_119576_, this.f_119577_)) / soundDistance));
/* 25 */       this.f_119573_ *= this.f_119573_;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setStop() {
/* 30 */     m_119609_();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public SoundBuffer getSoundBuffer() {
/* 35 */     return ClientAssetManager.INSTANCE.getSoundBuffers(this.registryName);
/*    */   }
/*    */   
/*    */   public ResourceLocation getRegistryName() {
/* 39 */     return this.registryName;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\sound\GunSoundInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */