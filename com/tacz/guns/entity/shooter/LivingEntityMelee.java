/*     */ package com.tacz.guns.entity.shooter;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.event.common.GunMeleeEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunMelee;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.MeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunDefaultMeleeData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunMeleeData;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LivingEntityMelee {
/*     */   private final LivingEntity shooter;
/*     */   
/*     */   public LivingEntityMelee(LivingEntity shooter, ShooterDataHolder data, LivingEntityDrawGun draw) {
/*  30 */     this.shooter = shooter;
/*  31 */     this.data = data;
/*  32 */     this.draw = draw;
/*     */   }
/*     */   private final ShooterDataHolder data; private final LivingEntityDrawGun draw;
/*     */   public void melee() {
/*  36 */     if (this.data.currentGunItem == null) {
/*     */       return;
/*     */     }
/*     */     
/*  40 */     if (this.draw.getDrawCoolDown() != 0L) {
/*     */       return;
/*     */     }
/*     */     
/*  44 */     if (this.data.boltCoolDown >= 0L) {
/*     */       return;
/*     */     }
/*  47 */     long coolDown = getMeleeCoolDown();
/*  48 */     if (coolDown != 0L) {
/*     */       return;
/*     */     }
/*  51 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/*     */     
/*  53 */     if (MinecraftForge.EVENT_BUS.post((Event)new GunMeleeEvent(this.shooter, currentGunItem, LogicalSide.SERVER))) {
/*     */       return;
/*     */     }
/*  56 */     NetworkHandler.sendToTrackingEntity(new ServerMessageGunMelee(this.shooter.m_19879_(), currentGunItem), (Entity)this.shooter);
/*  57 */     Item item = currentGunItem.m_41720_(); if (item instanceof AbstractGunItem) { AbstractGunItem logicGun = (AbstractGunItem)item;
/*  58 */       this.data.meleeTimestamp = System.currentTimeMillis();
/*     */       
/*  60 */       ResourceLocation muzzleId = logicGun.getAttachmentId(currentGunItem, AttachmentType.MUZZLE);
/*  61 */       MeleeData muzzleMeleeData = getMeleeData(muzzleId);
/*  62 */       if (muzzleMeleeData != null) {
/*  63 */         float prepTime = muzzleMeleeData.getPrepTime();
/*  64 */         this.data.meleePrepTickCount = (int)Math.max(0.0F, prepTime * 20.0F);
/*     */         
/*     */         return;
/*     */       } 
/*  68 */       ResourceLocation stockId = logicGun.getAttachmentId(currentGunItem, AttachmentType.STOCK);
/*  69 */       MeleeData stockMeleeData = getMeleeData(stockId);
/*  70 */       if (stockMeleeData != null) {
/*  71 */         float prepTime = stockMeleeData.getPrepTime();
/*  72 */         this.data.meleePrepTickCount = (int)Math.max(0.0F, prepTime * 20.0F);
/*     */         
/*     */         return;
/*     */       } 
/*  76 */       ResourceLocation gunId = logicGun.getGunId(currentGunItem);
/*  77 */       TimelessAPI.getCommonGunIndex(gunId).ifPresent(index -> {
/*     */             GunDefaultMeleeData defaultMeleeData = index.getGunData().getMeleeData().getDefaultMeleeData();
/*     */             if (defaultMeleeData == null) {
/*     */               return;
/*     */             }
/*     */             float prepTime = defaultMeleeData.getPrepTime();
/*     */             this.data.meleePrepTickCount = (int)Math.max(0.0F, prepTime * 20.0F);
/*     */           }); }
/*     */   
/*     */   }
/*     */   
/*     */   public void scheduleTickMelee() {
/*  89 */     if (this.data.meleePrepTickCount > 0) {
/*  90 */       this.data.meleePrepTickCount--;
/*     */       return;
/*     */     } 
/*  93 */     if (this.data.meleePrepTickCount == 0) {
/*  94 */       this.data.meleePrepTickCount = -1;
/*  95 */       if (this.data.currentGunItem == null) {
/*     */         return;
/*     */       }
/*  98 */       ItemStack currentGunItem = this.data.currentGunItem.get();
/*  99 */       Item item = currentGunItem.m_41720_(); if (item instanceof AbstractGunItem) { AbstractGunItem logicGun = (AbstractGunItem)item;
/* 100 */         logicGun.melee(this.shooter, currentGunItem); }
/*     */     
/*     */     } 
/*     */   }
/*     */   public long getMeleeCoolDown() {
/*     */     IGun iGun;
/* 106 */     if (this.data.currentGunItem == null) {
/* 107 */       return 0L;
/*     */     }
/* 109 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 110 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 111 */     else { return 0L; }
/*     */     
/* 113 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 114 */     Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(gunId);
/* 115 */     return ((Long)gunIndex.<Long>map(index -> {
/*     */           GunMeleeData meleeData = index.getGunData().getMeleeData();
/*     */           
/*     */           ResourceLocation muzzleId = iGun.getAttachmentId(currentGunItem, AttachmentType.MUZZLE);
/*     */           
/*     */           MeleeData muzzleMeleeData = getMeleeData(muzzleId);
/*     */           
/*     */           if (muzzleMeleeData != null) {
/*     */             return Long.valueOf(getTotalCooldownTime(meleeData, muzzleMeleeData.getCooldown()));
/*     */           }
/*     */           
/*     */           ResourceLocation stockId = iGun.getAttachmentId(currentGunItem, AttachmentType.STOCK);
/*     */           MeleeData stockMeleeData = getMeleeData(stockId);
/*     */           if (stockMeleeData != null) {
/*     */             return Long.valueOf(getTotalCooldownTime(meleeData, stockMeleeData.getCooldown()));
/*     */           }
/*     */           GunDefaultMeleeData defaultMeleeData = meleeData.getDefaultMeleeData();
/*     */           float defaultMeleeCooldownTime = (defaultMeleeData == null) ? 0.0F : defaultMeleeData.getCooldown();
/*     */           return Long.valueOf(getTotalCooldownTime(meleeData, defaultMeleeCooldownTime));
/* 134 */         }).orElse(Long.valueOf(-1L))).longValue();
/*     */   }
/*     */   
/*     */   private long getTotalCooldownTime(GunMeleeData meleeData, float extraCooldownTime) {
/* 138 */     float totalCooldownTime = meleeData.getCooldown() + extraCooldownTime;
/* 139 */     long coolDown = (long)(totalCooldownTime * 1000.0F) - System.currentTimeMillis() - this.data.meleeTimestamp;
/*     */     
/* 141 */     coolDown -= 5L;
/* 142 */     if (coolDown < 0L) {
/* 143 */       return 0L;
/*     */     }
/* 145 */     return coolDown;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private MeleeData getMeleeData(ResourceLocation attachmentId) {
/* 150 */     if (DefaultAssets.isEmptyAttachmentId(attachmentId)) {
/* 151 */       return null;
/*     */     }
/* 153 */     return TimelessAPI.getCommonAttachmentIndex(attachmentId).map(index -> index.getData().getMeleeData()).orElse(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityMelee.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */