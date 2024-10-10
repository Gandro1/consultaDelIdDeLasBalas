/*    */ package com.tacz.guns.inventory.tooltip;
/*    */ 
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.inventory.tooltip.TooltipComponent;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class GunTooltip implements TooltipComponent {
/*    */   private final ItemStack gun;
/*    */   private final IGun iGun;
/*    */   private final ResourceLocation ammoId;
/*    */   private final CommonGunIndex gunIndex;
/*    */   
/*    */   public GunTooltip(ItemStack gun, IGun iGun, ResourceLocation ammoId, CommonGunIndex gunIndex) {
/* 16 */     this.gun = gun;
/* 17 */     this.iGun = iGun;
/* 18 */     this.ammoId = ammoId;
/* 19 */     this.gunIndex = gunIndex;
/*    */   }
/*    */   
/*    */   public ItemStack getGun() {
/* 23 */     return this.gun;
/*    */   }
/*    */   
/*    */   public IGun getIGun() {
/* 27 */     return this.iGun;
/*    */   }
/*    */   
/*    */   public ResourceLocation getAmmoId() {
/* 31 */     return this.ammoId;
/*    */   }
/*    */   
/*    */   public CommonGunIndex getGunIndex() {
/* 35 */     return this.gunIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\inventory\tooltip\GunTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */