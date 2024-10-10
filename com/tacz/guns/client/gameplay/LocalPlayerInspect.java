/*    */ package com.tacz.guns.client.gameplay;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class LocalPlayerInspect {
/*    */   private final LocalPlayerDataHolder data;
/*    */   
/*    */   public LocalPlayerInspect(LocalPlayerDataHolder data, LocalPlayer player) {
/* 17 */     this.data = data;
/* 18 */     this.player = player;
/*    */   }
/*    */   private final LocalPlayer player;
/*    */   public void inspect() {
/*    */     IGun iGun;
/* 23 */     ItemStack mainhandItem = this.player.m_21205_();
/* 24 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/*    */     
/* 28 */     if (this.data.clientStateLock) {
/*    */       return;
/*    */     }
/* 31 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 32 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           boolean noAmmo;
/*    */           Bolt boltType = gunIndex.getGunData().getBolt();
/*    */           if (boltType == Bolt.OPEN_BOLT) {
/*    */             noAmmo = (iGun.getCurrentAmmoCount(mainhandItem) <= 0);
/*    */           } else {
/*    */             noAmmo = !iGun.hasBulletInBarrel(mainhandItem);
/*    */           } 
/*    */           SoundPlayManager.stopPlayGunSound();
/*    */           SoundPlayManager.playInspectSound((LivingEntity)this.player, gunIndex, noAmmo);
/*    */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*    */           if (animationStateMachine != null)
/*    */             animationStateMachine.setNoAmmo(noAmmo).onGunInspect(); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerInspect.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */