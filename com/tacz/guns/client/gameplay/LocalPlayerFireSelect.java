/*    */ package com.tacz.guns.client.gameplay;
/*    */ import com.tacz.guns.api.event.common.GunFireSelectEvent;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*    */ import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.ClientMessagePlayerFireSelect;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ 
/*    */ public class LocalPlayerFireSelect {
/*    */   private final LocalPlayerDataHolder data;
/*    */   
/*    */   public LocalPlayerFireSelect(LocalPlayerDataHolder data, LocalPlayer player) {
/* 23 */     this.data = data;
/* 24 */     this.player = player;
/*    */   }
/*    */   private final LocalPlayer player;
/*    */   public void fireSelect() {
/*    */     IGun iGun;
/* 29 */     if (this.data.clientStateLock) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     ItemStack mainhandItem = this.player.m_21205_();
/* 34 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*    */     else
/*    */     { return; }
/* 37 */      if (MinecraftForge.EVENT_BUS.post((Event)new GunFireSelectEvent((LivingEntity)this.player, this.player.m_21205_(), LogicalSide.CLIENT))) {
/*    */       return;
/*    */     }
/* 40 */     ResourceLocation gunId = iGun.getGunId(mainhandItem);
/* 41 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*    */           SoundPlayManager.playFireSelectSound((LivingEntity)this.player, gunIndex);
/*    */           NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerFireSelect());
/*    */           if (iGun instanceof AbstractGunItem) {
/*    */             AbstractGunItem logicGun = (AbstractGunItem)iGun;
/*    */             logicGun.fireSelect(mainhandItem);
/*    */           } 
/*    */           AttachmentPropertyManager.postChangeEvent((LivingEntity)this.player, mainhandItem);
/*    */           GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
/*    */           if (animationStateMachine != null)
/*    */             animationStateMachine.onGunFireSelect(); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gameplay\LocalPlayerFireSelect.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */