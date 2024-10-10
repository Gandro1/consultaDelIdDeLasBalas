/*    */ package com.tacz.guns.entity.shooter;
/*    */ import com.tacz.guns.api.event.common.GunFireSelectEvent;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.event.ServerMessageGunFireSelect;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ 
/*    */ public class LivingEntityFireSelect {
/*    */   private final LivingEntity shooter;
/*    */   
/*    */   public LivingEntityFireSelect(LivingEntity shooter, ShooterDataHolder data) {
/* 19 */     this.shooter = shooter;
/* 20 */     this.data = data;
/*    */   } private final ShooterDataHolder data;
/*    */   public void fireSelect() {
/*    */     IGun iGun;
/* 24 */     if (this.data.currentGunItem == null) {
/*    */       return;
/*    */     }
/* 27 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 28 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/* 31 */      if (MinecraftForge.EVENT_BUS.post((Event)new GunFireSelectEvent(this.shooter, currentGunItem, LogicalSide.SERVER))) {
/*    */       return;
/*    */     }
/* 34 */     NetworkHandler.sendToTrackingEntity(new ServerMessageGunFireSelect(this.shooter.m_19879_(), currentGunItem), (Entity)this.shooter);
/* 35 */     if (iGun instanceof AbstractGunItem) { AbstractGunItem logicGun = (AbstractGunItem)iGun;
/* 36 */       logicGun.fireSelect(currentGunItem);
/*    */       
/* 38 */       AttachmentPropertyManager.postChangeEvent(this.shooter, currentGunItem); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityFireSelect.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */