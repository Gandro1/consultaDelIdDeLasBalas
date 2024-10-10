/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import com.tacz.guns.api.client.event.SwapItemWithOffHand;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class InventoryEvent
/*    */ {
/* 20 */   private static int oldHotbarSelected = -1;
/* 21 */   private static ItemStack oldHotbarSelectItem = ItemStack.f_41583_;
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerChangeSelect(TickEvent.ClientTickEvent event) {
/* 25 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 26 */     if (player == null) {
/*    */       return;
/*    */     }
/* 29 */     Inventory inventory = player.m_150109_();
/*    */     
/* 31 */     if (oldHotbarSelected != inventory.f_35977_) {
/* 32 */       if (oldHotbarSelected == -1) {
/* 33 */         IClientPlayerGunOperator.fromLocalPlayer(player).draw(ItemStack.f_41583_);
/*    */       } else {
/* 35 */         IClientPlayerGunOperator.fromLocalPlayer(player).draw(inventory.m_8020_(oldHotbarSelected));
/*    */       } 
/* 37 */       oldHotbarSelected = inventory.f_35977_;
/* 38 */       oldHotbarSelectItem = inventory.m_8020_(inventory.f_35977_).m_41777_();
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     ItemStack currentItem = inventory.m_8020_(inventory.f_35977_);
/* 43 */     if (!ItemStack.m_41728_(oldHotbarSelectItem, currentItem)) {
/* 44 */       if (!isSame(oldHotbarSelectItem, currentItem)) {
/* 45 */         IClientPlayerGunOperator.fromLocalPlayer(player).draw(oldHotbarSelectItem);
/*    */       }
/* 47 */       oldHotbarSelectItem = currentItem.m_41777_();
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerSwapMainHand(SwapItemWithOffHand event) {
/* 53 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 54 */     if (player == null) {
/*    */       return;
/*    */     }
/* 57 */     IClientPlayerGunOperator.fromLocalPlayer(player).draw(player.m_21205_());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
/* 63 */     oldHotbarSelected = -1;
/* 64 */     oldHotbarSelectItem = ItemStack.f_41583_;
/*    */   }
/*    */   
/*    */   private static boolean isSame(ItemStack i, ItemStack j) {
/* 68 */     IGun iGun1 = IGun.getIGunOrNull(i);
/* 69 */     IGun iGun2 = IGun.getIGunOrNull(j);
/* 70 */     if (iGun1 != null && iGun2 != null) {
/* 71 */       return iGun1.getGunId(i).equals(iGun2.getGunId(j));
/*    */     }
/* 73 */     if (i.m_41619_() || j.m_41619_()) {
/* 74 */       return (i.m_41619_() && j.m_41619_());
/*    */     }
/* 76 */     return ItemStack.m_41728_(i, j);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\InventoryEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */