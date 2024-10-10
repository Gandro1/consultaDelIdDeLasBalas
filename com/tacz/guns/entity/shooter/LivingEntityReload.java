/*     */ package com.tacz.guns.entity.shooter;
/*     */ 
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.api.event.common.GunReloadEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunReload;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunReloadData;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LivingEntityReload {
/*     */   private final LivingEntity shooter;
/*     */   private final ShooterDataHolder data;
/*     */   
/*     */   public LivingEntityReload(LivingEntity shooter, ShooterDataHolder data, LivingEntityDrawGun draw, LivingEntityShoot shoot) {
/*  30 */     this.shooter = shooter;
/*  31 */     this.data = data;
/*  32 */     this.draw = draw;
/*  33 */     this.shoot = shoot;
/*     */   } private final LivingEntityDrawGun draw; private final LivingEntityShoot shoot;
/*     */   public void reload() {
/*     */     AbstractGunItem gunItem;
/*  37 */     if (this.data.currentGunItem == null) {
/*     */       return;
/*     */     }
/*  40 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/*  41 */     Item item = currentGunItem.m_41720_(); if (item instanceof AbstractGunItem) { gunItem = (AbstractGunItem)item; }
/*     */     else
/*     */     { return; }
/*  44 */      ResourceLocation gunId = gunItem.getGunId(currentGunItem);
/*  45 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(gunIndex -> {
/*     */           if (this.data.reloadStateType.isReloading()) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (this.shoot.getShootCoolDown() != 0L) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (this.draw.getDrawCoolDown() != 0L) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (this.data.boltCoolDown >= 0L) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (IGunOperator.fromLivingEntity(this.shooter).needCheckAmmo() && !gunItem.canReload(this.shooter, currentGunItem)) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (MinecraftForge.EVENT_BUS.post((Event)new GunReloadEvent(this.shooter, currentGunItem, LogicalSide.SERVER))) {
/*     */             return;
/*     */           }
/*     */           
/*     */           NetworkHandler.sendToTrackingEntity(new ServerMessageGunReload(this.shooter.m_19879_(), currentGunItem), (Entity)this.shooter);
/*     */           Bolt boltType = gunIndex.getGunData().getBolt();
/*  72 */           int ammoCount = gunItem.getCurrentAmmoCount(currentGunItem) + ((gunItem.hasBulletInBarrel(currentGunItem) && boltType != Bolt.OPEN_BOLT) ? 1 : 0);
/*     */           if (ammoCount <= 0) {
/*     */             this.data.reloadStateType = ReloadState.StateType.EMPTY_RELOAD_FEEDING;
/*     */           } else {
/*     */             this.data.reloadStateType = ReloadState.StateType.TACTICAL_RELOAD_FEEDING;
/*     */           } 
/*     */           this.data.reloadTimestamp = System.currentTimeMillis();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ReloadState tickReloadState() {
/*     */     IGun iGun;
/*  86 */     ReloadState reloadState = new ReloadState();
/*  87 */     reloadState.setStateType(ReloadState.StateType.NOT_RELOADING);
/*  88 */     reloadState.setCountDown(-1L);
/*     */     
/*  90 */     if (this.data.reloadTimestamp == -1L || this.data.currentGunItem == null) {
/*  91 */       return reloadState;
/*     */     }
/*  93 */     Item item = ((ItemStack)this.data.currentGunItem.get()).m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*  94 */     else { return reloadState; }
/*     */     
/*  96 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/*     */     
/*  98 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/*  99 */     Optional<CommonGunIndex> gunIndexOptional = TimelessAPI.getCommonGunIndex(gunId);
/* 100 */     if (gunIndexOptional.isEmpty()) {
/* 101 */       return reloadState;
/*     */     }
/* 103 */     GunData gunData = ((CommonGunIndex)gunIndexOptional.get()).getGunData();
/* 104 */     GunReloadData reloadData = gunData.getReloadData();
/*     */     
/* 106 */     long countDown = -1L;
/* 107 */     ReloadState.StateType stateType = this.data.reloadStateType;
/* 108 */     long progressTime = System.currentTimeMillis() - this.data.reloadTimestamp;
/* 109 */     if (stateType.isReloadingEmpty()) {
/* 110 */       long feedTime = (long)(reloadData.getFeed().getEmptyTime() * 1000.0F);
/* 111 */       long finishingTime = (long)(reloadData.getCooldown().getEmptyTime() * 1000.0F);
/* 112 */       if (progressTime < feedTime) {
/* 113 */         stateType = ReloadState.StateType.EMPTY_RELOAD_FEEDING;
/* 114 */         countDown = feedTime - progressTime;
/* 115 */       } else if (progressTime < finishingTime) {
/* 116 */         stateType = ReloadState.StateType.EMPTY_RELOAD_FINISHING;
/* 117 */         countDown = finishingTime - progressTime;
/*     */       } else {
/* 119 */         stateType = ReloadState.StateType.NOT_RELOADING;
/* 120 */         this.data.reloadTimestamp = -1L;
/*     */       } 
/* 122 */     } else if (stateType.isReloadingTactical()) {
/* 123 */       long feedTime = (long)(reloadData.getFeed().getTacticalTime() * 1000.0F);
/* 124 */       long finishingTime = (long)(reloadData.getCooldown().getTacticalTime() * 1000.0F);
/* 125 */       if (progressTime < feedTime) {
/* 126 */         stateType = ReloadState.StateType.TACTICAL_RELOAD_FEEDING;
/* 127 */         countDown = feedTime - progressTime;
/* 128 */       } else if (progressTime < finishingTime) {
/* 129 */         stateType = ReloadState.StateType.TACTICAL_RELOAD_FINISHING;
/* 130 */         countDown = finishingTime - progressTime;
/*     */       } else {
/* 132 */         stateType = ReloadState.StateType.NOT_RELOADING;
/* 133 */         this.data.reloadTimestamp = -1L;
/*     */       } 
/*     */     } 
/* 136 */     if (this.data.reloadStateType == ReloadState.StateType.EMPTY_RELOAD_FEEDING && 
/* 137 */       stateType == ReloadState.StateType.EMPTY_RELOAD_FINISHING && 
/* 138 */       iGun instanceof AbstractGunItem) { AbstractGunItem abstractGunItem = (AbstractGunItem)iGun; if (this.data.currentGunItem != null) {
/* 139 */         abstractGunItem.doReload(this.shooter, currentGunItem, true);
/*     */       } }
/*     */ 
/*     */     
/* 143 */     if (this.data.reloadStateType == ReloadState.StateType.TACTICAL_RELOAD_FEEDING && 
/* 144 */       stateType == ReloadState.StateType.TACTICAL_RELOAD_FINISHING && 
/* 145 */       iGun instanceof AbstractGunItem) { AbstractGunItem abstractGunItem = (AbstractGunItem)iGun; if (this.data.currentGunItem != null) {
/* 146 */         abstractGunItem.doReload(this.shooter, currentGunItem, false);
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.data.reloadStateType = stateType;
/*     */     
/* 153 */     reloadState.setStateType(stateType);
/* 154 */     reloadState.setCountDown(countDown);
/* 155 */     return reloadState;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityReload.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */