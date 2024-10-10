/*     */ package com.tacz.guns.entity.shooter;
/*     */ 
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.nbt.AttachmentItemDataAccessor;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class LivingEntityAim {
/*     */   private final LivingEntity shooter;
/*     */   private final ShooterDataHolder data;
/*     */   
/*     */   public LivingEntityAim(LivingEntity shooter, ShooterDataHolder data) {
/*  25 */     this.shooter = shooter;
/*  26 */     this.data = data;
/*     */   }
/*     */   
/*     */   public void aim(boolean isAim) {
/*  30 */     this.data.isAiming = isAim;
/*     */   }
/*     */   public void zoom() {
/*     */     IGun iGun;
/*  34 */     if (this.data.currentGunItem == null) {
/*     */       return;
/*     */     }
/*  37 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/*  38 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  41 */      ResourceLocation scopeId = iGun.getAttachmentId(currentGunItem, AttachmentType.SCOPE);
/*  42 */     CompoundTag scopeTag = iGun.getAttachmentTag(currentGunItem, AttachmentType.SCOPE);
/*  43 */     if (!DefaultAssets.isEmptyAttachmentId(scopeId) && scopeTag != null) {
/*  44 */       TimelessAPI.getCommonAttachmentIndex(scopeId).ifPresent(index -> {
/*     */             int zoomNumber = AttachmentItemDataAccessor.getZoomNumberFromTag(scopeTag);
/*     */             zoomNumber = ++zoomNumber % 2147483646;
/*     */             AttachmentItemDataAccessor.setZoomNumberToTag(scopeTag, zoomNumber);
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAimingProgress() {
/*  56 */     if (this.data.currentGunItem != null) { Item item = ((ItemStack)this.data.currentGunItem.get()).m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  61 */         ItemStack currentGunItem = this.data.currentGunItem.get();
/*     */         
/*  63 */         ResourceLocation gunId = iGun.getGunId(currentGunItem);
/*  64 */         Optional<CommonGunIndex> gunIndexOptional = TimelessAPI.getCommonGunIndex(gunId);
/*  65 */         if (gunIndexOptional.isEmpty()) {
/*  66 */           this.data.aimingProgress = 0.0F;
/*     */           return;
/*     */         } 
/*  69 */         GunData gunData = ((CommonGunIndex)gunIndexOptional.get()).getGunData();
/*  70 */         float aimTime = gunData.getAimTime();
/*  71 */         if (this.data.cacheProperty != null) {
/*  72 */           aimTime = ((Float)this.data.cacheProperty.getCache("ads")).floatValue();
/*     */         }
/*  74 */         aimTime = Math.max(0.0F, aimTime);
/*  75 */         float alphaProgress = (float)(System.currentTimeMillis() - this.data.aimingTimestamp + 1L) / aimTime * 1000.0F;
/*  76 */         if (this.data.isAiming) {
/*     */           
/*  78 */           this.data.aimingProgress += alphaProgress;
/*  79 */           if (this.data.aimingProgress > 1.0F) {
/*  80 */             this.data.aimingProgress = 1.0F;
/*     */           }
/*     */         } else {
/*     */           
/*  84 */           this.data.aimingProgress -= alphaProgress;
/*  85 */           if (this.data.aimingProgress < 0.0F) {
/*  86 */             this.data.aimingProgress = 0.0F;
/*     */           }
/*     */         } 
/*  89 */         this.data.aimingTimestamp = System.currentTimeMillis(); return; }
/*     */        }
/*     */     
/*     */     this.data.aimingProgress = 0.0F;
/*  93 */     this.data.aimingTimestamp = System.currentTimeMillis(); } public void tickSprint() { IGunOperator operator = IGunOperator.fromLivingEntity(this.shooter);
/*  94 */     ReloadState reloadState = operator.getSynReloadState();
/*  95 */     if (this.data.isAiming || (reloadState.getStateType().isReloading() && !reloadState.getStateType().isReloadFinishing())) {
/*  96 */       this.shooter.m_6858_(false);
/*     */     }
/*  98 */     if (this.data.sprintTimestamp == -1L) {
/*  99 */       this.data.sprintTimestamp = System.currentTimeMillis();
/*     */     }
/* 101 */     if (this.data.currentGunItem == null) {
/*     */       return;
/*     */     }
/* 104 */     ItemStack gunItem = this.data.currentGunItem.get();
/* 105 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 106 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 109 */     TimelessAPI.getCommonGunIndex(iGun.getGunId(gunItem)).ifPresentOrElse(gunIndex -> {
/*     */           float gunSprintTime = gunIndex.getGunData().getSprintTime();
/*     */           if (this.shooter.m_20142_() && !this.shooter.m_6047_()) {
/*     */             this.data.sprintTimeS += (float)(System.currentTimeMillis() - this.data.sprintTimestamp) / 1000.0F;
/*     */             if (this.data.sprintTimeS > gunSprintTime) {
/*     */               this.data.sprintTimeS = gunSprintTime;
/*     */             }
/*     */           } else {
/*     */             this.data.sprintTimeS -= (float)(System.currentTimeMillis() - this.data.sprintTimestamp) / 1000.0F;
/*     */             if (this.data.sprintTimeS < 0.0F) {
/*     */               this.data.sprintTimeS = 0.0F;
/*     */             }
/*     */           } 
/*     */         }() -> this.data.sprintTimeS = 0.0F);
/* 123 */     this.data.sprintTimestamp = System.currentTimeMillis(); }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityAim.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */