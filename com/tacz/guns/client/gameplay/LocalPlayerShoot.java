/*     */ package com.tacz.guns.client.gameplay;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ShootResult;
/*     */ import com.tacz.guns.api.event.common.GunFireEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerShoot;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import it.unimi.dsi.fastutil.Pair;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LocalPlayerShoot {
/*     */   private static final Predicate<IGunOperator> SHOOT_LOCKED_CONDITION;
/*     */   private final LocalPlayerDataHolder data;
/*     */   private final LocalPlayer player;
/*     */   
/*     */   static {
/*  37 */     SHOOT_LOCKED_CONDITION = (operator -> (operator.getSynShootCoolDown() > 0L));
/*     */   }
/*     */ 
/*     */   
/*     */   public LocalPlayerShoot(LocalPlayerDataHolder data, LocalPlayer player) {
/*  42 */     this.data = data;
/*  43 */     this.player = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public ShootResult shoot() {
/*     */     IGun iGun;
/*  49 */     if (System.currentTimeMillis() - LocalPlayerDataHolder.clientClickButtonTimestamp < 50L) {
/*  50 */       return ShootResult.COOL_DOWN;
/*     */     }
/*     */     
/*  53 */     if (!this.data.isShootRecorded) {
/*  54 */       return ShootResult.COOL_DOWN;
/*     */     }
/*     */     
/*  57 */     if (this.data.clientStateLock && this.data.lockedCondition != SHOOT_LOCKED_CONDITION && this.data.lockedCondition != null) {
/*  58 */       this.data.isShootRecorded = true;
/*     */       
/*  60 */       return ShootResult.IS_DRAWING;
/*     */     } 
/*     */     
/*  63 */     ItemStack mainhandItem = this.player.m_21205_();
/*  64 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*  65 */     else { return ShootResult.NOT_GUN; }
/*     */     
/*  67 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/*  68 */     Optional<ClientGunIndex> gunIndexOptional = TimelessAPI.getClientGunIndex(gunId);
/*  69 */     if (gunIndexOptional.isEmpty()) {
/*  70 */       return ShootResult.ID_NOT_EXIST;
/*     */     }
/*  72 */     ClientGunIndex gunIndex = gunIndexOptional.get();
/*  73 */     GunData gunData = gunIndex.getGunData();
/*  74 */     long coolDown = getCoolDown(iGun, mainhandItem, gunData);
/*     */     
/*  76 */     if (coolDown >= 50L) {
/*  77 */       return ShootResult.COOL_DOWN;
/*     */     }
/*     */     
/*  80 */     IGunOperator gunOperator = IGunOperator.fromLivingEntity((LivingEntity)this.player);
/*     */     
/*  82 */     if (gunOperator.getSynReloadState().getStateType().isReloading()) {
/*  83 */       return ShootResult.IS_RELOADING;
/*     */     }
/*     */     
/*  86 */     if (gunOperator.getSynDrawCoolDown() != 0L) {
/*  87 */       return ShootResult.IS_DRAWING;
/*     */     }
/*     */     
/*  90 */     if (gunOperator.getSynMeleeCoolDown() != 0L) {
/*  91 */       return ShootResult.IS_MELEE;
/*     */     }
/*     */     
/*  94 */     Bolt boltType = gunIndex.getGunData().getBolt();
/*  95 */     boolean hasAmmoInBarrel = (iGun.hasBulletInBarrel(mainhandItem) && boltType != Bolt.OPEN_BOLT);
/*  96 */     int ammoCount = iGun.getCurrentAmmoCount(mainhandItem) + (hasAmmoInBarrel ? 1 : 0);
/*  97 */     if (ammoCount < 1) {
/*  98 */       SoundPlayManager.playDryFireSound((LivingEntity)this.player, gunIndex);
/*  99 */       return ShootResult.NO_AMMO;
/*     */     } 
/*     */     
/* 102 */     if (boltType == Bolt.MANUAL_ACTION && !hasAmmoInBarrel) {
/* 103 */       IClientPlayerGunOperator.fromLocalPlayer(this.player).bolt();
/* 104 */       return ShootResult.NEED_BOLT;
/*     */     } 
/*     */     
/* 107 */     if (gunOperator.getSynSprintTime() > 0.0F) {
/* 108 */       return ShootResult.IS_SPRINTING;
/*     */     }
/*     */     
/* 111 */     if (MinecraftForge.EVENT_BUS.post((Event)new GunShootEvent((LivingEntity)this.player, mainhandItem, LogicalSide.CLIENT))) {
/* 112 */       return ShootResult.FORGE_EVENT_CANCEL;
/*     */     }
/*     */     
/* 115 */     this.data.lockState(SHOOT_LOCKED_CONDITION);
/* 116 */     this.data.isShootRecorded = false;
/*     */     
/* 118 */     doShoot(gunIndex, iGun, mainhandItem, gunData, coolDown);
/* 119 */     return ShootResult.SUCCESS;
/*     */   }
/*     */   
/*     */   private void doShoot(ClientGunIndex gunIndex, IGun iGun, ItemStack mainhandItem, GunData gunData, long delay) {
/* 123 */     FireMode fireMode = iGun.getFireMode(mainhandItem);
/* 124 */     Bolt boltType = gunIndex.getGunData().getBolt();
/*     */     
/* 126 */     boolean consumeAmmo = IGunOperator.fromLivingEntity((LivingEntity)this.player).consumesAmmoOrNot();
/* 127 */     boolean hasAmmoInBarrel = (iGun.hasBulletInBarrel(mainhandItem) && boltType != Bolt.OPEN_BOLT);
/* 128 */     int ammoCount = consumeAmmo ? (iGun.getCurrentAmmoCount(mainhandItem) + (hasAmmoInBarrel ? 1 : 0)) : Integer.MAX_VALUE;
/*     */     
/* 130 */     long period = (fireMode == FireMode.BURST) ? gunData.getBurstShootInterval() : 1L;
/*     */     
/* 132 */     int maxCount = Math.min(ammoCount, (fireMode == FireMode.BURST) ? gunData.getBurstData().getCount() : 1);
/*     */     
/* 134 */     AtomicInteger count = new AtomicInteger(0);
/* 135 */     LocalPlayerDataHolder.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> { if (count.get() == 0) this.data.isShootRecorded = true;  if (count.get() >= maxCount || this.player.m_21224_()) { ScheduledFuture<?> future = (ScheduledFuture)Thread.currentThread(); future.cancel(false); return; }  if (count.get() == 0) { if (this.data.clientStateLock && this.data.lockedCondition != SHOOT_LOCKED_CONDITION && this.data.lockedCondition != null) return;  this.data.clientShootTimestamp = System.currentTimeMillis(); NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerShoot()); }  boolean fire = !MinecraftForge.EVENT_BUS.post((Event)new GunFireEvent((LivingEntity)this.player, mainhandItem, LogicalSide.CLIENT)); if (fire) { GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine(); if (animationStateMachine != null) animationStateMachine.onGunShoot();  boolean useSilenceSound = useSilenceSound(); Minecraft.m_91087_().m_18689_(()); }  count.getAndIncrement(); }delay, period, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useSilenceSound() {
/* 183 */     AttachmentCacheProperty cacheProperty = IGunOperator.fromLivingEntity((LivingEntity)this.player).getCacheProperty();
/* 184 */     if (cacheProperty != null) {
/* 185 */       Pair<Integer, Boolean> silence = (Pair<Integer, Boolean>)cacheProperty.getCache("silence");
/* 186 */       return ((Boolean)silence.right()).booleanValue();
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */   private long getCoolDown(IGun iGun, ItemStack mainHandItem, GunData gunData) {
/*     */     long coolDown;
/* 192 */     FireMode fireMode = iGun.getFireMode(mainHandItem);
/*     */     
/* 194 */     if (fireMode == FireMode.BURST) {
/* 195 */       coolDown = (long)(gunData.getBurstData().getMinInterval() * 1000.0D) - System.currentTimeMillis() - this.data.clientShootTimestamp;
/*     */     } else {
/* 197 */       coolDown = gunData.getShootInterval((LivingEntity)this.player, fireMode) - System.currentTimeMillis() - this.data.clientShootTimestamp;
/*     */     } 
/* 199 */     return Math.max(coolDown, 0L);
/*     */   }
/*     */   
/*     */   public long getClientShootCoolDown() {
/* 203 */     ItemStack mainHandItem = this.player.m_21205_();
/* 204 */     IGun iGun = IGun.getIGunOrNull(mainHandItem);
/* 205 */     if (iGun == null) {
/* 206 */       return -1L;
/*     */     }
/* 208 */     ResourceLocation gunId = iGun.getGunId(mainHandItem);
/* 209 */     Optional<CommonGunIndex> gunIndexOptional = TimelessAPI.getCommonGunIndex(gunId);
/* 210 */     return ((Long)gunIndexOptional.<Long>map(commonGunIndex -> Long.valueOf(getCoolDown(iGun, mainHandItem, commonGunIndex.getGunData()))).orElse(Long.valueOf(-1L))).longValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerShoot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */