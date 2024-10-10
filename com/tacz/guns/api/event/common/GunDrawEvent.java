/*    */ package com.tacz.guns.api.event.common;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ 
/*    */ 
/*    */ public class GunDrawEvent
/*    */   extends Event
/*    */ {
/*    */   private final LivingEntity entity;
/*    */   private final ItemStack previousGunItem;
/*    */   private final ItemStack currentGunItem;
/*    */   private final LogicalSide logicalSide;
/*    */   
/*    */   public GunDrawEvent(LivingEntity entity, ItemStack previousGunItem, ItemStack currentGunItem, LogicalSide side) {
/* 18 */     this.entity = entity;
/* 19 */     this.previousGunItem = previousGunItem;
/* 20 */     this.currentGunItem = currentGunItem;
/* 21 */     this.logicalSide = side;
/*    */   }
/*    */   
/*    */   public LivingEntity getEntity() {
/* 25 */     return this.entity;
/*    */   }
/*    */   
/*    */   public ItemStack getPreviousGunItem() {
/* 29 */     return this.previousGunItem;
/*    */   }
/*    */   
/*    */   public ItemStack getCurrentGunItem() {
/* 33 */     return this.currentGunItem;
/*    */   }
/*    */   
/*    */   public LogicalSide getLogicalSide() {
/* 37 */     return this.logicalSide;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\GunDrawEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */