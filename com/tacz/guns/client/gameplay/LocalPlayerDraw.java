/*     */ package com.tacz.guns.client.gameplay;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.other.KeepingItemRenderer;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunDrawEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.sound.SoundPlayManager;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerDrawGun;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ 
/*     */ public class LocalPlayerDraw {
/*     */   private final LocalPlayerDataHolder data;
/*     */   
/*     */   public LocalPlayerDraw(LocalPlayerDataHolder data, LocalPlayer player) {
/*  27 */     this.data = data;
/*  28 */     this.player = player;
/*     */   }
/*     */   private final LocalPlayer player;
/*     */   
/*     */   public void draw(ItemStack lastItem) {
/*  33 */     resetData();
/*     */ 
/*     */     
/*  36 */     ItemStack currentItem = this.player.m_21205_();
/*  37 */     long drawTime = System.currentTimeMillis() - this.data.clientDrawTimestamp;
/*  38 */     IGun currentGun = IGun.getIGunOrNull(currentItem);
/*  39 */     IGun lastGun = IGun.getIGunOrNull(lastItem);
/*     */ 
/*     */     
/*  42 */     if (drawTime >= 0L) {
/*  43 */       drawTime = getDrawTime(lastItem, lastGun, drawTime);
/*     */     }
/*  45 */     long putAwayTime = Math.abs(drawTime);
/*     */ 
/*     */     
/*  48 */     if ((Minecraft.m_91087_()).f_91072_ != null) {
/*  49 */       (Minecraft.m_91087_()).f_91072_.m_105297_();
/*     */     }
/*  51 */     NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerDrawGun());
/*  52 */     MinecraftForge.EVENT_BUS.post((Event)new GunDrawEvent((LivingEntity)this.player, lastItem, currentItem, LogicalSide.CLIENT));
/*     */ 
/*     */     
/*  55 */     if (drawTime >= 0L) {
/*  56 */       doPutAway(lastItem, lastGun, putAwayTime);
/*     */     }
/*     */ 
/*     */     
/*  60 */     if (currentGun != null) {
/*  61 */       doDraw(currentGun, currentItem, putAwayTime);
/*     */       
/*  63 */       AttachmentPropertyManager.postChangeEvent((LivingEntity)this.player, currentItem);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doDraw(IGun currentGun, ItemStack currentItem, long putAwayTime) {
/*  68 */     TimelessAPI.getClientGunIndex(currentGun.getGunId(currentItem)).ifPresent(gunIndex -> {
/*     */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */           if (animationStateMachine == null) {
/*     */             return;
/*     */           }
/*     */           if (this.data.drawFuture != null) {
/*     */             this.data.drawFuture.cancel(false);
/*     */           }
/*     */           this.data.drawFuture = LocalPlayerDataHolder.SCHEDULED_EXECUTOR_SERVICE.schedule((), putAwayTime, TimeUnit.MILLISECONDS);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doPutAway(ItemStack lastItem, IGun lastGun, long putAwayTime) {
/*  87 */     if (lastGun == null) {
/*     */       return;
/*     */     }
/*  90 */     TimelessAPI.getClientGunIndex(lastGun.getGunId(lastItem)).ifPresent(gunIndex -> {
/*     */           SoundPlayManager.stopPlayGunSound();
/*     */           SoundPlayManager.playPutAwaySound((LivingEntity)this.player, gunIndex);
/*     */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*     */           if (animationStateMachine != null) {
/*     */             animationStateMachine.onGunPutAway((float)putAwayTime / 1000.0F);
/*     */             KeepingItemRenderer.getRenderer().keep(lastItem, putAwayTime);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getDrawTime(ItemStack lastItem, IGun lastGun, long drawTime) {
/* 105 */     if (lastGun != null) {
/*     */       
/* 107 */       Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(lastGun.getGunId(lastItem));
/* 108 */       float putAwayTime = ((Float)gunIndex.<Float>map(index -> Float.valueOf(index.getGunData().getPutAwayTime())).orElse(Float.valueOf(0.0F))).floatValue();
/* 109 */       if ((float)drawTime > putAwayTime * 1000.0F) {
/* 110 */         drawTime = (long)(putAwayTime * 1000.0F);
/*     */       }
/* 112 */       this.data.clientDrawTimestamp = System.currentTimeMillis() + drawTime;
/*     */     } else {
/* 114 */       drawTime = 0L;
/* 115 */       this.data.clientDrawTimestamp = System.currentTimeMillis();
/*     */     } 
/* 117 */     return drawTime;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetData() {
/* 122 */     this.data.lockState(operator -> (operator.getSynDrawCoolDown() > 0L));
/*     */     
/* 124 */     this.data.isShootRecorded = true;
/* 125 */     this.data.clientShootTimestamp = -1L;
/*     */     
/* 127 */     this.data.clientIsAiming = false;
/* 128 */     this.data.clientAimingProgress = 0.0F;
/* 129 */     LocalPlayerDataHolder.oldAimingProgress = 0.0F;
/*     */     
/* 131 */     this.data.isBolting = false;
/*     */     
/* 133 */     if (this.data.clientDrawTimestamp == -1L)
/* 134 */       this.data.clientDrawTimestamp = System.currentTimeMillis(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerDraw.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */