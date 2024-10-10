/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
/*    */ import com.tacz.guns.api.event.common.EntityKillByGunEvent;
/*    */ import com.tacz.guns.client.gui.overlay.KillAmountOverlay;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class ClientHitMark {
/* 23 */   public static long lastHitTimestamp = 0L;
/* 24 */   public static float damageAmount = 0.0F;
/*    */   @SubscribeEvent
/*    */   public static void onEntityHurt(EntityHurtByGunEvent event) {
/* 27 */     LogicalSide logicalSide = event.getLogicalSide();
/* 28 */     if (logicalSide != LogicalSide.CLIENT) {
/*    */       return;
/*    */     }
/* 31 */     LivingEntity attacker = event.getAttacker();
/* 32 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 33 */     Entity hurtEntity = event.getHurtEntity();
/* 34 */     if (player != null && player.equals(attacker) && hurtEntity != null) {
/* 35 */       ResourceLocation gunId = event.getGunId();
/* 36 */       RenderCrosshairEvent.markHitTimestamp();
/* 37 */       if (event.isHeadShot()) {
/* 38 */         RenderCrosshairEvent.markHeadShotTimestamp();
/* 39 */         TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> SoundPlayManager.playHeadHitSound((LivingEntity)player, index));
/*    */       } else {
/* 41 */         TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> SoundPlayManager.playFleshHitSound((LivingEntity)player, index));
/*    */       } 
/*    */       
/* 44 */       if (hurtEntity instanceof com.tacz.guns.entity.TargetMinecart) {
/* 45 */         if (System.currentTimeMillis() - lastHitTimestamp < ((Integer)RenderConfig.DAMAGE_COUNTER_RESET_TIME.get()).intValue()) {
/* 46 */           damageAmount += event.getAmount();
/*    */         } else {
/* 48 */           damageAmount = event.getAmount();
/*    */         } 
/* 50 */         float distance = player.m_20270_(event.getHurtEntity());
/* 51 */         player.m_5661_((Component)Component.m_237110_("message.tacz.target_minecart.hit", new Object[] { String.format("%.1f", new Object[] { Float.valueOf(damageAmount) }), String.format("%.2f", new Object[] { Float.valueOf(distance) }) }), true);
/*    */         
/* 53 */         lastHitTimestamp = System.currentTimeMillis();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityKill(EntityKillByGunEvent event) {
/* 60 */     LogicalSide logicalSide = event.getLogicalSide();
/* 61 */     if (logicalSide != LogicalSide.CLIENT) {
/*    */       return;
/*    */     }
/* 64 */     LivingEntity attacker = event.getAttacker();
/* 65 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 66 */     if (player != null && player.equals(attacker)) {
/* 67 */       RenderCrosshairEvent.markKillTimestamp();
/* 68 */       KillAmountOverlay.markTimestamp();
/* 69 */       TimelessAPI.getClientGunIndex(event.getGunId()).ifPresent(index -> SoundPlayManager.playKillSound((LivingEntity)player, index));
/* 70 */       if (event.isHeadShot())
/* 71 */         RenderCrosshairEvent.markHeadShotTimestamp(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\ClientHitMark.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */