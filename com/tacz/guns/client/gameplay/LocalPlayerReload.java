/*     */ package com.tacz.guns.client.gameplay;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunReloadEvent;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.api.item.IAmmoBox;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerReloadGun;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.util.AttachmentDataUtils;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LocalPlayerReload {
/*     */   private final LocalPlayerDataHolder data;
/*     */   
/*     */   public LocalPlayerReload(LocalPlayerDataHolder data, LocalPlayer player) {
/*  31 */     this.data = data;
/*  32 */     this.player = player;
/*     */   }
/*     */   private final LocalPlayer player;
/*     */   public void reload() {
/*     */     AbstractGunItem gunItem;
/*  37 */     ItemStack mainhandItem = this.player.m_21205_();
/*  38 */     Item item = mainhandItem.m_41720_(); if (item instanceof AbstractGunItem) { gunItem = (AbstractGunItem)item; }
/*     */     else
/*     */     { return; }
/*  41 */      ResourceLocation gunId = gunItem.getGunId(mainhandItem);
/*  42 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */           if (this.data.clientStateLock) {
/*     */             return;
/*     */           }
/*     */           boolean canReload = gunItem.canReload((LivingEntity)this.player, mainhandItem);
/*     */           if (IGunOperator.fromLivingEntity((LivingEntity)this.player).needCheckAmmo() && !canReload) {
/*     */             return;
/*     */           }
/*     */           this.data.lockState(());
/*     */           if (MinecraftForge.EVENT_BUS.post((Event)new GunReloadEvent((LivingEntity)this.player, this.player.m_21205_(), LogicalSide.CLIENT))) {
/*     */             return;
/*     */           }
/*     */           NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerReloadGun());
/*     */           doReload((IGun)gunItem, gunIndex, mainhandItem);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doReload(IGun iGun, ClientGunIndex gunIndex, ItemStack mainhandItem) {
/*  66 */     GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*  67 */     if (animationStateMachine != null) {
/*  68 */       boolean noAmmo; Bolt boltType = gunIndex.getGunData().getBolt();
/*     */       
/*  70 */       if (boltType == Bolt.OPEN_BOLT) {
/*  71 */         noAmmo = (iGun.getCurrentAmmoCount(mainhandItem) <= 0);
/*     */       } else {
/*  73 */         noAmmo = !iGun.hasBulletInBarrel(mainhandItem);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  78 */       SoundPlayManager.stopPlayGunSound();
/*  79 */       SoundPlayManager.playReloadSound((LivingEntity)this.player, gunIndex, noAmmo);
/*  80 */       animationStateMachine.setNoAmmo(noAmmo).onGunReload();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void playMagExtendedAnimation(ItemStack mainhandItem, IGun iGun, GunAnimationStateMachine animationStateMachine) {
/*  86 */     ResourceLocation extendedMagId = iGun.getAttachmentId(mainhandItem, AttachmentType.EXTENDED_MAG);
/*  87 */     if (!DefaultAssets.isEmptyAttachmentId(extendedMagId)) {
/*  88 */       TimelessAPI.getCommonAttachmentIndex(extendedMagId).ifPresent(index -> animationStateMachine.setMagExtended((index.getData().getExtendedMagLevel() > 0)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inventoryHasAmmo(IGun iGun, ClientGunIndex gunIndex, ItemStack mainhandItem) {
/*  97 */     int maxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(mainhandItem, gunIndex.getGunData());
/*  98 */     if (iGun.getCurrentAmmoCount(mainhandItem) >= maxAmmoCount) {
/*  99 */       return false;
/*     */     }
/* 101 */     if (iGun.useDummyAmmo(mainhandItem)) {
/* 102 */       return (iGun.getDummyAmmoAmount(mainhandItem) > 0);
/*     */     }
/*     */     
/* 105 */     Inventory inventory = this.player.m_150109_();
/* 106 */     for (int i = 0; i < inventory.m_6643_(); i++) {
/* 107 */       ItemStack checkAmmo = inventory.m_8020_(i);
/* 108 */       Item item = checkAmmo.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item; if (iAmmo.isAmmoOfGun(mainhandItem, checkAmmo))
/* 109 */           return true;  }
/*     */       
/* 111 */       item = checkAmmo.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item; if (iAmmoBox.isAmmoBoxOfGun(mainhandItem, checkAmmo))
/* 112 */           return true;  }
/*     */     
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerReload.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */