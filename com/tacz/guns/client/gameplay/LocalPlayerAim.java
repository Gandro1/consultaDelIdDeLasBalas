/*     */ package com.tacz.guns.client.gameplay;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerAim;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class LocalPlayerAim {
/*     */   private final LocalPlayerDataHolder data;
/*     */   
/*     */   public LocalPlayerAim(LocalPlayerDataHolder data, LocalPlayer player) {
/*  21 */     this.data = data;
/*  22 */     this.player = player;
/*     */   }
/*     */   private final LocalPlayer player;
/*     */   public void aim(boolean isAim) {
/*     */     IGun iGun;
/*  27 */     ItemStack mainhandItem = this.player.m_21205_();
/*  28 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  31 */      ResourceLocation gunId = iGun.getGunId(mainhandItem);
/*  32 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */           this.data.clientIsAiming = isAim;
/*     */           NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerAim(isAim));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public float getClientAimingProgress(float partialTicks) {
/*  40 */     return Mth.m_14179_(partialTicks, LocalPlayerDataHolder.oldAimingProgress, this.data.clientAimingProgress);
/*     */   }
/*     */   
/*     */   public boolean isAim() {
/*  44 */     return this.data.clientIsAiming;
/*     */   }
/*     */   
/*     */   public boolean cancelSprint(LocalPlayer player, boolean pSprinting) {
/*  48 */     IGunOperator gunOperator = IGunOperator.fromLivingEntity((LivingEntity)player);
/*  49 */     boolean isAiming = gunOperator.getSynIsAiming();
/*  50 */     ReloadState.StateType reloadStateType = gunOperator.getSynReloadState().getStateType();
/*  51 */     if (isAiming || (reloadStateType.isReloading() && !reloadStateType.isReloadFinishing())) {
/*  52 */       return false;
/*     */     }
/*  54 */     return pSprinting;
/*     */   }
/*     */   
/*     */   public void tickAimingProgress() {
/*     */     IGun iGun;
/*  59 */     ItemStack mainhandItem = this.player.m_21205_();
/*     */     
/*  61 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*  62 */     else { this.data.clientAimingProgress = 0.0F;
/*  63 */       LocalPlayerDataHolder.oldAimingProgress = 0.0F;
/*     */       
/*     */       return; }
/*     */     
/*  67 */     if (System.currentTimeMillis() - this.data.clientDrawTimestamp < 0L) {
/*  68 */       this.data.clientIsAiming = false;
/*     */     }
/*  70 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/*  71 */     TimelessAPI.getCommonGunIndex(gunId).ifPresentOrElse(index -> {
/*     */           float alphaProgress = getAlphaProgress(index.getGunData());
/*     */           aimProgressCalculate(alphaProgress);
/*     */         }() -> {
/*     */           this.data.clientAimingProgress = 0.0F;
/*     */           LocalPlayerDataHolder.oldAimingProgress = 0.0F;
/*     */         });
/*     */   }
/*     */   
/*     */   private void aimProgressCalculate(float alphaProgress) {
/*  81 */     LocalPlayerDataHolder.oldAimingProgress = this.data.clientAimingProgress;
/*  82 */     if (this.data.clientIsAiming) {
/*     */       
/*  84 */       this.data.clientAimingProgress += alphaProgress;
/*  85 */       if (this.data.clientAimingProgress > 1.0F) {
/*  86 */         this.data.clientAimingProgress = 1.0F;
/*     */       }
/*     */     } else {
/*     */       
/*  90 */       this.data.clientAimingProgress -= alphaProgress;
/*  91 */       if (this.data.clientAimingProgress < 0.0F) {
/*  92 */         this.data.clientAimingProgress = 0.0F;
/*     */       }
/*     */     } 
/*  95 */     this.data.clientAimingTimestamp = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private float getAlphaProgress(GunData gunData) {
/*  99 */     float aimTime = gunData.getAimTime();
/* 100 */     IGunOperator operator = IGunOperator.fromLivingEntity((LivingEntity)this.player);
/* 101 */     if (operator.getCacheProperty() != null) {
/* 102 */       aimTime = ((Float)operator.getCacheProperty().getCache("ads")).floatValue();
/*     */     }
/* 104 */     aimTime = Math.max(0.0F, aimTime);
/* 105 */     return (float)(System.currentTimeMillis() - this.data.clientAimingTimestamp + 1L) / aimTime * 1000.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerAim.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */