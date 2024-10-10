/*    */ package com.tacz.guns.entity.shooter;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class LivingEntityCrawl {
/*    */   private final LivingEntity shooter;
/*    */   private final ShooterDataHolder data;
/*    */   
/*    */   public LivingEntityCrawl(LivingEntity shooter, ShooterDataHolder data) {
/* 16 */     this.shooter = shooter;
/* 17 */     this.data = data;
/*    */   }
/*    */   
/*    */   public void crawl(boolean isCrawl) {
/* 21 */     this.data.isCrawling = isCrawl;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickCrawling() {
/* 26 */     if (this.data.currentGunItem != null) { Item item = ((ItemStack)this.data.currentGunItem.get()).m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 31 */         ItemStack currentGunItem = this.data.currentGunItem.get();
/*    */         
/* 33 */         ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 34 */         if (TimelessAPI.getCommonGunIndex(gunId).isEmpty()) {
/* 35 */           this.data.isCrawling = false;
/* 36 */           setCrawlPose();
/*    */           
/*    */           return;
/*    */         } 
/* 40 */         if (this.shooter.m_5833_() || this.shooter.m_20159_() || this.shooter.f_20899_ || this.shooter.m_6069_() || !this.shooter.m_20096_()) {
/* 41 */           this.data.isCrawling = false;
/* 42 */           setCrawlPose();
/*    */           return;
/*    */         } 
/* 45 */         setCrawlPose(); return; }
/*    */        }
/*    */     
/*    */     this.data.isCrawling = false;
/* 49 */     setCrawlPose(); } private void setCrawlPose() { if (this.data.isCrawling) {
/* 50 */       LivingEntity livingEntity = this.shooter; if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/* 51 */         player.setForcedPose(Pose.SWIMMING); }
/*    */       else
/* 53 */       { this.shooter.m_20124_(Pose.SWIMMING); }
/*    */     
/*    */     } else {
/* 56 */       LivingEntity livingEntity = this.shooter; if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/* 57 */         player.setForcedPose(null); }
/*    */     
/*    */     }  }
/*    */ 
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityCrawl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */