/*     */ package com.tacz.guns.entity.shooter;
/*     */ 
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.ShootResult;
/*     */ import com.tacz.guns.api.event.common.GunShootEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunShoot;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LivingEntityShoot {
/*     */   private final LivingEntity shooter;
/*     */   
/*     */   public LivingEntityShoot(LivingEntity shooter, ShooterDataHolder data, LivingEntityDrawGun draw) {
/*  29 */     this.shooter = shooter;
/*  30 */     this.data = data;
/*  31 */     this.draw = draw;
/*     */   } private final ShooterDataHolder data; private final LivingEntityDrawGun draw;
/*     */   public ShootResult shoot(Supplier<Float> pitch, Supplier<Float> yaw) {
/*     */     IGun iGun;
/*  35 */     if (this.data.currentGunItem == null) {
/*  36 */       return ShootResult.NOT_DRAW;
/*     */     }
/*  38 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/*  39 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*  40 */     else { return ShootResult.NOT_GUN; }
/*     */     
/*  42 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/*  43 */     Optional<CommonGunIndex> gunIndexOptional = TimelessAPI.getCommonGunIndex(gunId);
/*  44 */     if (gunIndexOptional.isEmpty()) {
/*  45 */       return ShootResult.ID_NOT_EXIST;
/*     */     }
/*  47 */     CommonGunIndex gunIndex = gunIndexOptional.get();
/*     */     
/*  49 */     long coolDown = getShootCoolDown();
/*  50 */     if (coolDown == -1L)
/*     */     {
/*  52 */       return ShootResult.UNKNOWN_FAIL;
/*     */     }
/*  54 */     if (coolDown > 0L) {
/*  55 */       return ShootResult.COOL_DOWN;
/*     */     }
/*     */     
/*  58 */     if (this.data.reloadStateType.isReloading()) {
/*  59 */       return ShootResult.IS_RELOADING;
/*     */     }
/*     */     
/*  62 */     if (this.draw.getDrawCoolDown() != 0L) {
/*  63 */       return ShootResult.IS_DRAWING;
/*     */     }
/*     */     
/*  66 */     if (this.data.boltCoolDown >= 0L) {
/*  67 */       return ShootResult.IS_BOLTING;
/*     */     }
/*     */     
/*  70 */     if (this.data.sprintTimeS > 0.0F) {
/*  71 */       return ShootResult.IS_SPRINTING;
/*     */     }
/*  73 */     Bolt boltType = gunIndex.getGunData().getBolt();
/*  74 */     boolean hasAmmoInBarrel = (iGun.hasBulletInBarrel(currentGunItem) && boltType != Bolt.OPEN_BOLT);
/*  75 */     int ammoCount = iGun.getCurrentAmmoCount(currentGunItem) + (hasAmmoInBarrel ? 1 : 0);
/*     */     
/*  77 */     if (ammoCount < 1) {
/*  78 */       return ShootResult.NO_AMMO;
/*     */     }
/*     */     
/*  81 */     if (boltType == Bolt.MANUAL_ACTION && !hasAmmoInBarrel) {
/*  82 */       return ShootResult.NEED_BOLT;
/*     */     }
/*  84 */     if (boltType == Bolt.CLOSED_BOLT && !hasAmmoInBarrel) {
/*  85 */       iGun.reduceCurrentAmmoCount(currentGunItem);
/*  86 */       iGun.setBulletInBarrel(currentGunItem, true);
/*     */     } 
/*     */     
/*  89 */     if (MinecraftForge.EVENT_BUS.post((Event)new GunShootEvent(this.shooter, currentGunItem, LogicalSide.SERVER))) {
/*  90 */       return ShootResult.FORGE_EVENT_CANCEL;
/*     */     }
/*  92 */     NetworkHandler.sendToTrackingEntity(new ServerMessageGunShoot(this.shooter.m_19879_(), currentGunItem), (Entity)this.shooter);
/*     */     
/*  94 */     if (iGun instanceof AbstractGunItem) { AbstractGunItem logicGun = (AbstractGunItem)iGun;
/*  95 */       BulletData bulletData = gunIndex.getBulletData();
/*  96 */       boolean isTracerAmmo = (bulletData.hasTracerAmmo() && this.data.shootCount % (bulletData.getTracerCountInterval() + 1) == 0);
/*  97 */       logicGun.shoot(currentGunItem, pitch, yaw, isTracerAmmo, this.shooter); }
/*     */     
/*  99 */     this.data.shootTimestamp = System.currentTimeMillis();
/* 100 */     this.data.shootCount++;
/* 101 */     return ShootResult.SUCCESS;
/*     */   }
/*     */   public long getShootCoolDown() {
/*     */     IGun iGun;
/* 105 */     if (this.data.currentGunItem == null) {
/* 106 */       return 0L;
/*     */     }
/* 108 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 109 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 110 */     else { return 0L; }
/*     */     
/* 112 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 113 */     Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(gunId);
/* 114 */     FireMode fireMode = iGun.getFireMode(currentGunItem);
/* 115 */     if (fireMode == FireMode.BURST) {
/* 116 */       return ((Long)gunIndex.<Long>map(index -> {
/*     */             long coolDown = (long)(index.getGunData().getBurstData().getMinInterval() * 1000.0D) - System.currentTimeMillis() - this.data.shootTimestamp;
/*     */             
/*     */             coolDown -= 5L;
/*     */             return Long.valueOf(Math.max(coolDown, 0L));
/* 121 */           }).orElse(Long.valueOf(-1L))).longValue();
/*     */     }
/* 123 */     return ((Long)gunIndex.<Long>map(index -> {
/*     */           long coolDown = index.getGunData().getShootInterval(this.shooter, fireMode) - System.currentTimeMillis() - this.data.shootTimestamp;
/*     */           
/*     */           coolDown -= 5L;
/*     */           return Long.valueOf(Math.max(coolDown, 0L));
/* 128 */         }).orElse(Long.valueOf(-1L))).longValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityShoot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */