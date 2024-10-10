/*    */ package com.tacz.guns.client.model.papi;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class AmmoCountPapi
/*    */   implements Function<ItemStack, String>
/*    */ {
/*    */   public static final String NAME = "ammo_count";
/*    */   
/*    */   public String apply(ItemStack stack) {
/* 17 */     IGun iGun = IGun.getIGunOrNull(stack);
/* 18 */     if (iGun != null) {
/* 19 */       ResourceLocation gunId = iGun.getGunId(stack);
/* 20 */       ClientGunIndex gunIndex = TimelessAPI.getClientGunIndex(gunId).orElse(null);
/* 21 */       if (gunIndex == null) {
/* 22 */         return "";
/*    */       }
/* 24 */       int ammoCount = iGun.getCurrentAmmoCount(stack) + ((iGun.hasBulletInBarrel(stack) && gunIndex.getGunData().getBolt() != Bolt.OPEN_BOLT) ? 1 : 0);
/* 25 */       return "" + ammoCount;
/*    */     } 
/* 27 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\papi\AmmoCountPapi.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */