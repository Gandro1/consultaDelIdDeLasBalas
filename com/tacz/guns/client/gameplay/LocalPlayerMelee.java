/*     */ package com.tacz.guns.client.gameplay;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunMeleeEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerMelee;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.MeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunDefaultMeleeData;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LocalPlayerMelee {
/*     */   private final LocalPlayerDataHolder data;
/*  26 */   private int meleeCounter = 0; private final LocalPlayer player;
/*     */   
/*     */   public LocalPlayerMelee(LocalPlayerDataHolder data, LocalPlayer player) {
/*  29 */     this.data = data;
/*  30 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void melee() {
/*     */     IGun iGun;
/*  35 */     if (this.data.clientStateLock) {
/*     */       return;
/*     */     }
/*     */     
/*  39 */     ItemStack mainhandItem = this.player.m_21205_();
/*  40 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  43 */      ResourceLocation gunId = iGun.getGunId(mainhandItem);
/*     */     
/*  45 */     ResourceLocation muzzleId = iGun.getAttachmentId(mainhandItem, AttachmentType.MUZZLE);
/*  46 */     MeleeData muzzleMeleeData = getMeleeData(muzzleId);
/*  47 */     if (muzzleMeleeData != null) {
/*  48 */       doMuzzleMelee(gunId);
/*     */       
/*     */       return;
/*     */     } 
/*  52 */     ResourceLocation stockId = iGun.getAttachmentId(mainhandItem, AttachmentType.STOCK);
/*  53 */     MeleeData stockMeleeData = getMeleeData(stockId);
/*  54 */     if (stockMeleeData != null) {
/*  55 */       doStockMelee(gunId);
/*     */       
/*     */       return;
/*     */     } 
/*  59 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*     */           GunDefaultMeleeData defaultMeleeData = index.getGunData().getMeleeData().getDefaultMeleeData();
/*     */           if (defaultMeleeData == null) {
/*     */             return;
/*     */           }
/*     */           String animationType = defaultMeleeData.getAnimationType();
/*     */           if ("melee_stock".equals(animationType)) {
/*     */             doStockMelee(gunId);
/*     */             return;
/*     */           } 
/*     */           doPushMelee(gunId);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean prepareMelee() {
/*  75 */     this.data.lockState(operator -> (operator.getSynMeleeCoolDown() > 0L));
/*     */     
/*  77 */     GunMeleeEvent gunMeleeEvent = new GunMeleeEvent((LivingEntity)this.player, this.player.m_21205_(), LogicalSide.CLIENT);
/*  78 */     return !MinecraftForge.EVENT_BUS.post((Event)gunMeleeEvent);
/*     */   }
/*     */   
/*     */   private void doMuzzleMelee(ResourceLocation gunId) {
/*  82 */     if (prepareMelee()) {
/*  83 */       TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */             SoundPlayManager.playMeleeBayonetSound((LivingEntity)this.player, gunIndex);
/*     */             NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerMelee());
/*     */             GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */             if (animationStateMachine != null) {
/*     */               animationStateMachine.onBayonetAttack(this.meleeCounter);
/*     */               this.meleeCounter = (this.meleeCounter + 1) % 3;
/*     */             } 
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doStockMelee(ResourceLocation gunId) {
/*  99 */     if (prepareMelee()) {
/* 100 */       TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */             SoundPlayManager.playMeleeStockSound((LivingEntity)this.player, gunIndex);
/*     */             NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerMelee());
/*     */             GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */             if (animationStateMachine != null) {
/*     */               animationStateMachine.onStockAttack();
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doPushMelee(ResourceLocation gunId) {
/* 115 */     if (prepareMelee()) {
/* 116 */       TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */             SoundPlayManager.playMeleePushSound((LivingEntity)this.player, gunIndex);
/*     */             NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerMelee());
/*     */             GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */             if (animationStateMachine != null) {
/*     */               animationStateMachine.onPushAttack();
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private MeleeData getMeleeData(ResourceLocation attachmentId) {
/* 132 */     if (DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/* 133 */       return null;
/*     */     }
/* 135 */     return TimelessAPI.getClientAttachmentIndex(attachmentId).map(index -> index.getData().getMeleeData()).orElse(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerMelee.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */