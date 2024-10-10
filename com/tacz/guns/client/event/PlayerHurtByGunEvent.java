/*    */ package com.tacz.guns.client.event;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
/*    */ import com.tacz.guns.client.renderer.other.GunHurtBobTweak;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class PlayerHurtByGunEvent {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerHurtByGun(EntityHurtByGunEvent event) {
/* 19 */     LogicalSide logicalSide = event.getLogicalSide();
/* 20 */     if (logicalSide != LogicalSide.CLIENT) {
/*    */       return;
/*    */     }
/* 23 */     Entity hurtEntity = event.getHurtEntity();
/* 24 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*    */     
/* 26 */     if (player != null && player.equals(hurtEntity)) {
/* 27 */       ResourceLocation gunId = event.getGunId();
/* 28 */       TimelessAPI.getCommonGunIndex(gunId).ifPresent(index -> {
/*    */             float tweakMultiplier = index.getGunData().getHurtBobTweakMultiplier();
/*    */             GunHurtBobTweak.markTimestamp(tweakMultiplier);
/*    */           });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\PlayerHurtByGunEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */