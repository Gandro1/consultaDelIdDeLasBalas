/*     */ package com.tacz.guns.sound;
/*     */ 
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ServerMessageSound;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ 
/*     */ public class SoundManager
/*     */ {
/*  16 */   public static String SHOOT_SOUND = "shoot";
/*     */ 
/*     */ 
/*     */   
/*  20 */   public static String SHOOT_3P_SOUND = "shoot_3p";
/*     */ 
/*     */ 
/*     */   
/*  24 */   public static String SILENCE_SOUND = "silence";
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static String SILENCE_3P_SOUND = "silence_3p";
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static String MELEE_BAYONET = "melee_bayonet";
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static String MELEE_PUSH = "melee_push";
/*     */ 
/*     */ 
/*     */   
/*  40 */   public static String MELEE_STOCK = "melee_stock";
/*     */ 
/*     */ 
/*     */   
/*  44 */   public static String DRY_FIRE_SOUND = "dry_fire";
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static String RELOAD_EMPTY_SOUND = "reload_empty";
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static String RELOAD_TACTICAL_SOUND = "reload_tactical";
/*     */ 
/*     */ 
/*     */   
/*  56 */   public static String INSPECT_EMPTY_SOUND = "inspect_empty";
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static String INSPECT_SOUND = "inspect";
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static String DRAW_SOUND = "draw";
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static String PUT_AWAY_SOUND = "put_away";
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static String BOLT_SOUND = "bolt";
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static String FIRE_SELECT = "fire_select";
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static String HEAD_HIT_SOUND = "head_hit";
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static String FLESH_HIT_SOUND = "flesh_hit";
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static String KILL_SOUND = "kill";
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static String UNINSTALL_SOUND = "uninstall";
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static String INSTALL_SOUND = "install";
/*     */   
/*     */   public static void sendSoundToNearby(LivingEntity sourceEntity, int distance, ResourceLocation gunId, String soundName, float volume, float pitch) {
/*  99 */     Level level = sourceEntity.m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/* 100 */       BlockPos pos = sourceEntity.m_20183_();
/* 101 */       ServerMessageSound soundMessage = new ServerMessageSound(sourceEntity.m_19879_(), gunId, soundName, volume, pitch, distance);
/* 102 */       (serverLevel.m_7726_()).f_8325_.m_183262_(new ChunkPos(pos), false).stream()
/* 103 */         .filter(p -> (p.m_20275_(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()) < (distance * distance)))
/* 104 */         .filter(p -> (p.m_19879_() != sourceEntity.m_19879_()))
/* 105 */         .forEach(p -> NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()), soundMessage)); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\sound\SoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */