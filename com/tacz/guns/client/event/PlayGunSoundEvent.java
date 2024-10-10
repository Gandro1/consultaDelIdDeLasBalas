/*    */ package com.tacz.guns.client.event;
/*    */ import com.mojang.blaze3d.audio.SoundBuffer;
/*    */ import com.tacz.guns.client.sound.GunSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class PlayGunSoundEvent {
/*    */   @SubscribeEvent
/*    */   public static void onPlaySoundSource(PlaySoundSourceEvent event) {
/* 14 */     SoundInstance soundInstance = event.getSound(); if (soundInstance instanceof GunSoundInstance) { GunSoundInstance instance = (GunSoundInstance)soundInstance;
/* 15 */       SoundBuffer soundBuffer = instance.getSoundBuffer();
/* 16 */       if (soundBuffer != null) {
/* 17 */         event.getChannel().m_83656_(soundBuffer);
/* 18 */         event.getChannel().m_83672_();
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\PlayGunSoundEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */