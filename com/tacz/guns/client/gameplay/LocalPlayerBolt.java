/*    */ package com.tacz.guns.client.gameplay;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.ClientMessagePlayerBoltGun;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class LocalPlayerBolt {
/*    */   private final LocalPlayerDataHolder data;
/*    */   
/*    */   public LocalPlayerBolt(LocalPlayerDataHolder data, LocalPlayer player) {
/* 19 */     this.data = data;
/* 20 */     this.player = player;
/*    */   }
/*    */   private final LocalPlayer player;
/*    */   public void bolt() {
/*    */     IGun iGun;
/* 25 */     if (this.data.clientStateLock) {
/*    */       return;
/*    */     }
/* 28 */     if (this.data.isBolting) {
/*    */       return;
/*    */     }
/* 31 */     ItemStack mainhandItem = this.player.m_21205_();
/* 32 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/* 35 */      ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 36 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           Bolt boltType = gunIndex.getGunData().getBolt();
/*    */           if (boltType != Bolt.MANUAL_ACTION) {
/*    */             return;
/*    */           }
/*    */           if (iGun.hasBulletInBarrel(mainhandItem)) {
/*    */             return;
/*    */           }
/*    */           if (iGun.getCurrentAmmoCount(mainhandItem) == 0) {
/*    */             return;
/*    */           }
/*    */           this.data.lockState(());
/*    */           this.data.isBolting = true;
/*    */           NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerBoltGun());
/*    */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*    */           if (animationStateMachine != null) {
/*    */             SoundPlayManager.playBoltSound((LivingEntity)this.player, gunIndex);
/*    */             animationStateMachine.onGunBolt();
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickAutoBolt() {
/*    */     IGun iGun;
/* 65 */     ItemStack mainhandItem = this.player.m_21205_();
/* 66 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 67 */     else { this.data.isBolting = false;
/*    */       return; }
/*    */     
/* 70 */     bolt();
/* 71 */     if (this.data.isBolting)
/*    */     {
/* 73 */       if (iGun.hasBulletInBarrel(mainhandItem))
/* 74 */         this.data.isBolting = false; 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerBolt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */