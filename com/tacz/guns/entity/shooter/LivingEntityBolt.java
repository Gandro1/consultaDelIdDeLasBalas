/*    */ package com.tacz.guns.entity.shooter;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class LivingEntityBolt {
/*    */   private final ShooterDataHolder data;
/*    */   private final LivingEntityDrawGun draw;
/*    */   private final LivingEntityShoot shoot;
/*    */   
/*    */   public LivingEntityBolt(ShooterDataHolder data, LivingEntityDrawGun draw, LivingEntityShoot shoot) {
/* 19 */     this.data = data;
/* 20 */     this.draw = draw;
/* 21 */     this.shoot = shoot;
/*    */   }
/*    */   public void bolt() {
/*    */     IGun iGun;
/* 25 */     if (this.data.currentGunItem == null) {
/*    */       return;
/*    */     }
/* 28 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 29 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/* 32 */      ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 33 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           if (this.shoot.getShootCoolDown() != 0L) {
/*    */             return;
/*    */           }
/*    */           if (this.data.reloadStateType.isReloading()) {
/*    */             return;
/*    */           }
/*    */           if (this.draw.getDrawCoolDown() != 0L) {
/*    */             return;
/*    */           }
/*    */           if (this.data.boltCoolDown >= 0L) {
/*    */             return;
/*    */           }
/*    */           Bolt boltType = gunIndex.getGunData().getBolt();
/*    */           if (boltType != Bolt.MANUAL_ACTION) {
/*    */             return;
/*    */           }
/*    */           if (iGun.hasBulletInBarrel(currentGunItem)) {
/*    */             return;
/*    */           }
/*    */           if (iGun.getCurrentAmmoCount(currentGunItem) == 0) {
/*    */             return;
/*    */           }
/*    */           this.data.boltTimestamp = System.currentTimeMillis();
/*    */           this.data.boltCoolDown = 0L;
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
/*    */   public void tickBolt() {
/*    */     IGun iGun;
/* 71 */     if (this.data.boltCoolDown == -1L) {
/*    */       return;
/*    */     }
/* 74 */     if (this.data.currentGunItem == null) {
/* 75 */       this.data.boltCoolDown = -1L;
/*    */       return;
/*    */     } 
/* 78 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 79 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 80 */     else { this.data.boltCoolDown = -1L;
/*    */       return; }
/*    */     
/* 83 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 84 */     Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(gunId);
/* 85 */     this.data
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 93 */       .boltCoolDown = ((Long)gunIndex.<Long>map(index -> { long coolDown = (long)(index.getGunData().getBoltActionTime() * 1000.0F) - System.currentTimeMillis() - this.data.boltTimestamp; coolDown -= 5L; return (coolDown < 0L) ? Long.valueOf(0L) : Long.valueOf(coolDown); }).orElse(Long.valueOf(-1L))).longValue();
/* 94 */     if (this.data.boltCoolDown == 0L) {
/* 95 */       if (iGun instanceof AbstractGunItem) { AbstractGunItem logicGun = (AbstractGunItem)iGun;
/* 96 */         logicGun.bolt(currentGunItem); }
/*    */       
/* 98 */       this.data.boltCoolDown = -1L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityBolt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */