/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.api.entity.KnockBackModifier;
/*    */ import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class KnockbackChange {
/*    */   @SubscribeEvent
/*    */   public static void onKnockback(LivingKnockBackEvent event) {
/* 12 */     KnockBackModifier modifier = KnockBackModifier.fromLivingEntity(event.getEntity());
/* 13 */     double strength = modifier.getKnockBackStrength();
/* 14 */     if (strength >= 0.0D)
/* 15 */       event.setStrength((float)strength); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\KnockbackChange.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */