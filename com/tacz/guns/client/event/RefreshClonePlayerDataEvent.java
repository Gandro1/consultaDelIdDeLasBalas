/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.util.DelayedTask;
/*    */ import java.util.function.BooleanSupplier;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class RefreshClonePlayerDataEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClientPlayerClone(ClientPlayerNetworkEvent.Clone event) {
/* 22 */     LocalPlayer newPlayer = event.getNewPlayer();
/*    */ 
/*    */     
/* 25 */     DelayedTask.add(() -> IGunOperator.fromLivingEntity((LivingEntity)newPlayer).initialData(), 10);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 33 */     if (event.phase == TickEvent.Phase.START)
/*    */       try {
/* 35 */         DelayedTask.SUPPLIERS.removeIf(BooleanSupplier::getAsBoolean);
/* 36 */       } catch (Exception e) {
/* 37 */         DelayedTask.SUPPLIERS.clear();
/* 38 */         GunMod.LOGGER.catching(e);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\RefreshClonePlayerDataEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */