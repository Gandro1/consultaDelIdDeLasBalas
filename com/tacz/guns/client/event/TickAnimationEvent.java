/*    */ package com.tacz.guns.client.event;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class TickAnimationEvent {
/*    */   @SubscribeEvent
/*    */   public static void tickAnimation(TickEvent.ClientTickEvent event) {
/*    */     IGun iGun;
/* 22 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 23 */     if (player == null) {
/*    */       return;
/*    */     }
/* 26 */     IClientPlayerGunOperator clientGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 27 */     ItemStack mainhandItem = player.m_21205_();
/* 28 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/* 31 */      ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 32 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*    */           
/*    */           if (animationStateMachine == null) {
/*    */             return;
/*    */           }
/*    */           
/*    */           animationStateMachine.setAiming((clientGunOperator.getClientAimingProgress(1.0F) == 1.0F));
/*    */           
/*    */           boolean isShooting = (clientGunOperator.getClientShootCoolDown() > 0L);
/*    */           
/*    */           if (isShooting) {
/*    */             animationStateMachine.onShooterIdle();
/*    */           } else if (!player.m_108635_() && player.m_20142_()) {
/*    */             animationStateMachine.setOnGround(player.m_20096_()).onShooterRun(player.f_19787_);
/*    */           } else if (!player.m_108635_() && player.f_108618_.m_108575_().m_165907_() > 0.01D) {
/*    */             animationStateMachine.setOnGround(player.m_20096_()).onShooterWalk(player.f_108618_, player.f_19787_);
/*    */           } else {
/*    */             animationStateMachine.onShooterIdle();
/*    */           } 
/*    */           Bolt boltType = gunIndex.getGunData().getBolt();
/* 53 */           int ammoCount = iGun.getCurrentAmmoCount(mainhandItem) + ((iGun.hasBulletInBarrel(mainhandItem) && boltType != Bolt.OPEN_BOLT) ? 1 : 0);
/*    */           if (ammoCount < 1) {
/*    */             animationStateMachine.onGunCatchBolt();
/*    */           } else {
/*    */             animationStateMachine.onGunReleaseBolt();
/*    */           } 
/*    */           animationStateMachine.onIdleHoldingPose();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\TickAnimationEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */