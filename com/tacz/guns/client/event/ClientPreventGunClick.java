/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import com.tacz.guns.client.input.InteractKey;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.phys.EntityHitResult;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.InputEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class ClientPreventGunClick
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClickInput(InputEvent.InteractionKeyMappingTriggered event) {
/* 22 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 23 */     if (player == null) {
/*    */       return;
/*    */     }
/*    */     
/* 27 */     if (InteractKey.INTERACT_KEY.m_90857_()) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     ItemStack itemInHand = player.m_21120_(InteractionHand.MAIN_HAND);
/* 32 */     if (itemInHand.m_41720_() instanceof com.tacz.guns.api.item.IGun) {
/*    */       
/* 34 */       HitResult hitResult = (Minecraft.m_91087_()).f_91077_;
/* 35 */       if (hitResult instanceof EntityHitResult) { EntityHitResult entityHitResult = (EntityHitResult)hitResult; if (entityHitResult.m_82443_() instanceof net.minecraft.world.entity.decoration.ItemFrame) {
/*    */           return;
/*    */         } }
/*    */       
/* 39 */       event.setSwingHand(false);
/* 40 */       event.setCanceled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\ClientPreventGunClick.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */