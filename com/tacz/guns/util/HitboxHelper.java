/*     */ package com.tacz.guns.util;
/*     */ 
/*     */ import com.tacz.guns.config.common.OtherConfig;
/*     */ import java.util.LinkedList;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class HitboxHelper
/*     */ {
/*  17 */   private static final WeakHashMap<Player, LinkedList<Vec3>> PLAYER_POSITION = new WeakHashMap<>();
/*     */   
/*  19 */   private static final WeakHashMap<Player, LinkedList<AABB>> PLAYER_HITBOXES = new WeakHashMap<>();
/*     */   
/*  21 */   private static final WeakHashMap<Player, LinkedList<Vec3>> PLAYER_VELOCITY = new WeakHashMap<>();
/*     */   
/*  23 */   private static final int SAVE_TICK = Mth.m_14107_(((Double)OtherConfig.SERVER_HITBOX_LATENCY_MAX_SAVE_MS.get()).doubleValue() / 1000.0D * 20.0D + 0.5D);
/*     */   
/*     */   public static void onPlayerTick(Player player) {
/*  26 */     if (player.m_5833_()) {
/*  27 */       PLAYER_POSITION.remove(player);
/*  28 */       PLAYER_HITBOXES.remove(player);
/*  29 */       PLAYER_VELOCITY.remove(player);
/*     */       return;
/*     */     } 
/*  32 */     LinkedList<Vec3> positions = PLAYER_POSITION.computeIfAbsent(player, p -> new LinkedList());
/*  33 */     LinkedList<AABB> boxes = PLAYER_HITBOXES.computeIfAbsent(player, p -> new LinkedList());
/*  34 */     LinkedList<Vec3> velocities = PLAYER_VELOCITY.computeIfAbsent(player, p -> new LinkedList());
/*  35 */     positions.addFirst(player.m_20182_());
/*  36 */     boxes.addFirst(player.m_20191_());
/*  37 */     velocities.addFirst(getPlayerVelocity(player));
/*     */     
/*  39 */     if (positions.size() > 2) {
/*  40 */       positions.removeLast();
/*     */     }
/*     */     
/*  43 */     if (boxes.size() > SAVE_TICK) {
/*  44 */       boxes.removeLast();
/*  45 */       velocities.removeLast();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void onPlayerLoggedOut(Player player) {
/*  50 */     PLAYER_POSITION.remove(player);
/*  51 */     PLAYER_HITBOXES.remove(player);
/*  52 */     PLAYER_VELOCITY.remove(player);
/*     */   }
/*     */   
/*     */   public static Vec3 getPlayerVelocity(Player entity) {
/*  56 */     LinkedList<Vec3> positions = PLAYER_POSITION.computeIfAbsent(entity, player -> new LinkedList());
/*  57 */     if (positions.size() > 1) {
/*  58 */       Vec3 currPos = positions.getFirst();
/*  59 */       Vec3 prevPos = positions.getLast();
/*  60 */       return new Vec3(currPos.f_82479_ - prevPos.f_82479_, currPos.f_82480_ - prevPos.f_82480_, currPos.f_82481_ - prevPos.f_82481_);
/*     */     } 
/*  62 */     return new Vec3(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public static AABB getBoundingBox(Player entity, int ping) {
/*  66 */     if (PLAYER_HITBOXES.containsKey(entity)) {
/*  67 */       LinkedList<AABB> boxes = PLAYER_HITBOXES.get(entity);
/*  68 */       int index = Mth.m_14045_(ping, 0, boxes.size() - 1);
/*  69 */       return boxes.get(index);
/*     */     } 
/*  71 */     return entity.m_20191_();
/*     */   }
/*     */   
/*     */   public static Vec3 getVelocity(Player entity, int ping) {
/*  75 */     if (PLAYER_VELOCITY.containsKey(entity)) {
/*  76 */       LinkedList<Vec3> velocities = PLAYER_VELOCITY.get(entity);
/*  77 */       int index = Mth.m_14045_(ping, 0, velocities.size() - 1);
/*  78 */       return velocities.get(index);
/*     */     } 
/*  80 */     return getPlayerVelocity(entity);
/*     */   }
/*     */   
/*     */   public static AABB getFixedBoundingBox(Entity entity, Entity owner) {
/*  84 */     AABB boundingBox = entity.m_20191_();
/*  85 */     Vec3 velocity = new Vec3(entity.m_20185_() - entity.f_19790_, entity.m_20186_() - entity.f_19791_, entity.m_20189_() - entity.f_19792_);
/*     */     
/*  87 */     if (((Boolean)OtherConfig.SERVER_HITBOX_LATENCY_FIX.get()).booleanValue() && entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity; if (owner instanceof ServerPlayer) { ServerPlayer serverPlayerOwner = (ServerPlayer)owner;
/*  88 */         int ping = Mth.m_14107_(serverPlayerOwner.f_8943_ / 1000.0D * 20.0D + 0.5D);
/*  89 */         boundingBox = getBoundingBox((Player)player, ping);
/*  90 */         velocity = getVelocity((Player)player, ping); }
/*     */        }
/*     */     
/*  93 */     double expandHeight = (entity instanceof Player && !entity.m_6047_()) ? 0.0625D : 0.0D;
/*  94 */     boundingBox = boundingBox.m_82363_(0.0D, expandHeight, 0.0D);
/*     */     
/*  96 */     boundingBox = boundingBox.m_82363_(velocity.f_82479_, velocity.f_82480_, velocity.f_82481_);
/*     */     
/*  98 */     double playerHitboxOffset = ((Double)OtherConfig.SERVER_HITBOX_OFFSET.get()).doubleValue();
/*  99 */     if (entity instanceof ServerPlayer) {
/* 100 */       if (entity.m_20202_() != null) {
/* 101 */         boundingBox = boundingBox.m_82383_(velocity.m_82542_(playerHitboxOffset / 2.0D, playerHitboxOffset / 2.0D, playerHitboxOffset / 2.0D));
/*     */       }
/* 103 */       boundingBox = boundingBox.m_82383_(velocity.m_82542_(playerHitboxOffset, playerHitboxOffset, playerHitboxOffset));
/*     */     } 
/*     */     
/* 106 */     if (entity.m_20202_() != null || entity instanceof com.tacz.guns.api.entity.ITargetEntity) {
/* 107 */       boundingBox = boundingBox.m_82383_(velocity.m_82542_(-2.5D, -2.5D, -2.5D));
/*     */     }
/* 109 */     boundingBox = boundingBox.m_82383_(velocity.m_82542_(-5.0D, -5.0D, -5.0D));
/* 110 */     return boundingBox;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\HitboxHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */