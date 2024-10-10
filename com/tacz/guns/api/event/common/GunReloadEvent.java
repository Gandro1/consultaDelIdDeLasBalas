/*    */ package com.tacz.guns.api.event.common;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ 
/*    */ 
/*    */ public class GunReloadEvent
/*    */   extends Event
/*    */ {
/*    */   private final LivingEntity entity;
/*    */   private final ItemStack gunItemStack;
/*    */   private final LogicalSide logicalSide;
/*    */   
/*    */   public GunReloadEvent(LivingEntity entity, ItemStack gunItemStack, LogicalSide side) {
/* 17 */     this.entity = entity;
/* 18 */     this.gunItemStack = gunItemStack;
/* 19 */     this.logicalSide = side;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelable() {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public LivingEntity getEntity() {
/* 28 */     return this.entity;
/*    */   }
/*    */   
/*    */   public ItemStack getGunItemStack() {
/* 32 */     return this.gunItemStack;
/*    */   }
/*    */   
/*    */   public LogicalSide getLogicalSide() {
/* 36 */     return this.logicalSide;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\GunReloadEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */