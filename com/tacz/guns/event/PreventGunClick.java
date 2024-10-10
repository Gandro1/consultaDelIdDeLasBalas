/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class PreventGunClick
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
/* 15 */     ItemStack itemInHand = event.getEntity().m_21120_(InteractionHand.MAIN_HAND);
/* 16 */     if (itemInHand.m_41720_() instanceof com.tacz.guns.api.item.IGun)
/* 17 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\PreventGunClick.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */