/*    */ package com.tacz.guns.event;
/*    */ 
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class TravelToDimensionEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onTravelToDimension(EntityTravelToDimensionEvent event) {
/* 18 */     Entity entity = event.getEntity();
/* 19 */     if (entity instanceof LivingEntity) { LivingEntity livingEntity = (LivingEntity)entity; if (livingEntity.m_21205_().m_41720_() instanceof com.tacz.guns.api.item.IGun)
/* 20 */         IGunOperator.fromLivingEntity(livingEntity).initialData();  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\TravelToDimensionEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */