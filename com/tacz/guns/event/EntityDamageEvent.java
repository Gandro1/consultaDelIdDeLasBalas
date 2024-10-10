/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.init.ModAttributes;
/*    */ import com.tacz.guns.init.ModDamageTypes;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class EntityDamageEvent {
/*    */   @SubscribeEvent(priority = EventPriority.LOW)
/*    */   public static void onLivingHurt(LivingHurtEvent event) {
/* 17 */     if (event.getSource().m_269533_(ModDamageTypes.BULLETS_TAG)) {
/* 18 */       LivingEntity living = event.getEntity();
/*    */       
/* 20 */       AttributeInstance resistance = living.m_21051_((Attribute)ModAttributes.BULLET_RESISTANCE.get());
/* 21 */       if (resistance != null) {
/* 22 */         float modifiedDamage = event.getAmount() * (float)(1.0D - resistance.m_22135_());
/* 23 */         event.setAmount(modifiedDamage);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\EntityDamageEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */