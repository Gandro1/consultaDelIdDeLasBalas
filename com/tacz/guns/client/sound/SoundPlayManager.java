/*     */ package com.tacz.guns.client.sound;
/*     */ 
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.config.common.GunConfig;
/*     */ import com.tacz.guns.init.ModSounds;
/*     */ import com.tacz.guns.network.message.ServerMessageSound;
/*     */ import com.tacz.guns.sound.SoundManager;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.resources.sounds.SoundInstance;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SoundPlayManager
/*     */ {
/*     */   private static boolean DRY_SOUND_TRACK = true;
/*  34 */   private static GunSoundInstance tmpSoundInstance = null;
/*     */   
/*     */   public static GunSoundInstance playClientSound(Entity entity, @Nullable ResourceLocation name, float volume, float pitch, int distance) {
/*  37 */     Minecraft minecraft = Minecraft.m_91087_();
/*  38 */     GunSoundInstance instance = new GunSoundInstance((SoundEvent)ModSounds.GUN.get(), SoundSource.PLAYERS, volume, pitch, entity, distance, name);
/*  39 */     minecraft.m_91106_().m_120367_((SoundInstance)instance);
/*  40 */     return instance;
/*     */   }
/*     */   
/*     */   public static void stopPlayGunSound() {
/*  44 */     if (tmpSoundInstance != null) {
/*  45 */       tmpSoundInstance.setStop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void stopPlayGunSound(ClientGunIndex gunIndex, String animationName) {
/*  50 */     if (tmpSoundInstance != null && 
/*  51 */       tmpSoundInstance.getRegistryName() != null && tmpSoundInstance.getRegistryName().equals(gunIndex.getSounds(animationName))) {
/*  52 */       tmpSoundInstance.setStop();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void playerRefitSound(ItemStack attachmentItem, LocalPlayer player, String soundName) {
/*  58 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachmentItem);
/*  59 */     if (iAttachment == null) {
/*     */       return;
/*     */     }
/*  62 */     ResourceLocation attachmentId = iAttachment.getAttachmentId(attachmentItem);
/*  63 */     TimelessAPI.getClientAttachmentIndex(attachmentId).ifPresent(index -> {
/*     */           Map<String, ResourceLocation> sounds = index.getSounds();
/*     */           if (sounds.containsKey(soundName)) {
/*     */             ResourceLocation resourceLocation = sounds.get(soundName);
/*     */             playClientSound((Entity)player, resourceLocation, 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   public static void playShootSound(LivingEntity entity, ClientGunIndex gunIndex) {
/*  73 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.SHOOT_SOUND), 0.8F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playSilenceSound(LivingEntity entity, ClientGunIndex gunIndex) {
/*  77 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.SILENCE_SOUND), 0.6F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playDryFireSound(LivingEntity entity, ClientGunIndex gunIndex) {
/*  81 */     if (DRY_SOUND_TRACK) {
/*  82 */       playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.DRY_FIRE_SOUND), 1.0F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*  83 */       DRY_SOUND_TRACK = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetDryFireSound() {
/*  91 */     DRY_SOUND_TRACK = true;
/*     */   }
/*     */   
/*     */   public static void playReloadSound(LivingEntity entity, ClientGunIndex gunIndex, boolean noAmmo) {
/*  95 */     if (noAmmo) {
/*  96 */       tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.RELOAD_EMPTY_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */     } else {
/*  98 */       tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.RELOAD_TACTICAL_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void playInspectSound(LivingEntity entity, ClientGunIndex gunIndex, boolean noAmmo) {
/* 103 */     if (noAmmo) {
/* 104 */       tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.INSPECT_EMPTY_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */     } else {
/* 106 */       tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.INSPECT_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void playBoltSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 111 */     tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.BOLT_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playDrawSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 115 */     tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.DRAW_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playPutAwaySound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 119 */     tmpSoundInstance = playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.PUT_AWAY_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playFireSelectSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 123 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.FIRE_SELECT), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playMeleeBayonetSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 127 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.MELEE_BAYONET), 1.0F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playMeleePushSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 131 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.MELEE_PUSH), 1.0F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playMeleeStockSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 135 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.MELEE_STOCK), 1.0F, 0.9F + entity.m_217043_().m_188501_() * 0.125F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playHeadHitSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 139 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.HEAD_HIT_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playFleshHitSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 143 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.FLESH_HIT_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   
/*     */   public static void playKillSound(LivingEntity entity, ClientGunIndex gunIndex) {
/* 147 */     playClientSound((Entity)entity, gunIndex.getSounds(SoundManager.KILL_SOUND), 1.0F, 1.0F, ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue());
/*     */   }
/*     */   public static void playMessageSound(ServerMessageSound message) {
/*     */     LivingEntity livingEntity;
/* 151 */     ClientLevel level = (Minecraft.m_91087_()).f_91073_;
/* 152 */     if (level != null) { Entity entity = level.m_6815_(message.getEntityId()); if (entity instanceof LivingEntity) { livingEntity = (LivingEntity)entity; } else { return; }
/*     */        }
/*     */     else { return; }
/* 155 */      ResourceLocation gunId = message.getGunId();
/* 156 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*     */           ResourceLocation soundId = index.getSounds(message.getSoundName());
/*     */           if (soundId == null)
/*     */             return; 
/*     */           playClientSound((Entity)livingEntity, soundId, message.getVolume(), message.getPitch(), message.getDistance());
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\sound\SoundPlayManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */