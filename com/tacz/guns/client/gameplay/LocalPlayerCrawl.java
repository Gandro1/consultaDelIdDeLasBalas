/*    */ package com.tacz.guns.client.gameplay;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.ClientMessagePlayerCrawl;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class LocalPlayerCrawl
/*    */ {
/*    */   private static final int COOLDOWN_TICKS = 10;
/*    */   private final LocalPlayer player;
/*    */   private boolean isCrawling = false;
/* 19 */   private int crawCooldownTicks = 0;
/*    */   
/*    */   public LocalPlayerCrawl(LocalPlayer player) {
/* 22 */     this.player = player;
/*    */   }
/*    */   
/*    */   public void crawl(boolean isCrawl) {
/*    */     IGun iGun;
/* 27 */     ItemStack mainhandItem = this.player.m_21205_();
/* 28 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/*    */     
/* 32 */     if (this.crawCooldownTicks > 0) {
/*    */       return;
/*    */     }
/* 35 */     if (this.player.m_5833_() || this.player.m_20159_() || !this.player.m_20096_()) {
/*    */       return;
/*    */     }
/* 38 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 39 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           this.isCrawling = isCrawl;
/*    */           this.crawCooldownTicks = 10;
/*    */           NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerCrawl(isCrawl));
/*    */         });
/*    */   }
/*    */   public void tickCrawl() {
/*    */     IGun iGun;
/* 47 */     if (this.crawCooldownTicks > 0) {
/* 48 */       this.crawCooldownTicks--;
/*    */     }
/*    */     
/* 51 */     ItemStack mainhandItem = this.player.m_21205_();
/* 52 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 53 */     else { this.isCrawling = false;
/* 54 */       setCrawlPose();
/*    */       
/*    */       return; }
/*    */     
/* 58 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 59 */     if (TimelessAPI.getCommonGunIndex(gunId).isEmpty()) {
/* 60 */       this.isCrawling = false;
/* 61 */       setCrawlPose();
/*    */       
/*    */       return;
/*    */     } 
/* 65 */     if (this.player.m_5833_() || this.player.m_20159_() || this.player.f_20899_ || this.player.m_6069_() || !this.player.m_20096_()) {
/* 66 */       this.isCrawling = false;
/* 67 */       setCrawlPose();
/*    */       return;
/*    */     } 
/* 70 */     setCrawlPose();
/*    */   }
/*    */   
/*    */   public boolean isCrawling() {
/* 74 */     return this.isCrawling;
/*    */   }
/*    */   
/*    */   private void setCrawlPose() {
/* 78 */     if (this.isCrawling) {
/* 79 */       this.player.setForcedPose(Pose.SWIMMING);
/*    */     } else {
/* 81 */       this.player.setForcedPose(null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerCrawl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */