/*    */ package com.tacz.guns.event;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*    */ import com.tacz.guns.config.common.GunConfig;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class PlayerRespawnEvent {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/* 17 */     if (!((Boolean)GunConfig.AUTO_RELOAD_WHEN_RESPAWN.get()).booleanValue()) {
/*    */       return;
/*    */     }
/* 20 */     Player player = event.getEntity();
/* 21 */     (player.m_150109_()).f_35974_.forEach(currentGunItem -> {
/*    */           AbstractGunItem iGun;
/*    */           Item patt830$temp = currentGunItem.m_41720_();
/*    */           if (patt830$temp instanceof AbstractGunItem) {
/*    */             iGun = (AbstractGunItem)patt830$temp;
/*    */           } else {
/*    */             return;
/*    */           } 
/*    */           TimelessAPI.getCommonGunIndex(iGun.getGunId(currentGunItem)).ifPresent(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\PlayerRespawnEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */