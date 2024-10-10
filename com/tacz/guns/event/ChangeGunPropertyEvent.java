/*    */ package com.tacz.guns.event;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.event.common.AttachmentPropertyEvent;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class ChangeGunPropertyEvent {
/*    */   @SubscribeEvent
/*    */   public static void onAttachmentPropertyEvent(AttachmentPropertyEvent event) {
/* 15 */     ItemStack gunItem = event.getGunItem();
/* 16 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 17 */     if (iGun == null) {
/*    */       return;
/*    */     }
/* 20 */     ResourceLocation gunId = iGun.getGunId(gunItem);
/* 21 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(gunIndex -> event.getCacheProperty().eval(gunItem, gunIndex.getGunData()));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\ChangeGunPropertyEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */