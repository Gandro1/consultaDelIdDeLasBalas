/*    */ package com.tacz.guns.item;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public enum GunTooltipPart
/*    */ {
/*  8 */   DESCRIPTION,
/*  9 */   AMMO_INFO,
/* 10 */   BASE_INFO,
/* 11 */   EXTRA_DAMAGE_INFO,
/* 12 */   UPGRADES_TIP,
/* 13 */   PACK_INFO;
/*    */   GunTooltipPart() {
/* 15 */     this.mask = 1 << ordinal();
/*    */   }
/*    */   public int getMask() {
/* 18 */     return this.mask;
/*    */   }
/*    */   private final int mask;
/*    */   public static int getHideFlags(ItemStack stack) {
/* 22 */     CompoundTag tag = stack.m_41783_();
/* 23 */     if (tag != null && tag.m_128425_("HideFlags", 99)) {
/* 24 */       return tag.m_128451_("HideFlags");
/*    */     }
/* 26 */     return stack.m_41720_().getDefaultTooltipHideFlags(stack);
/*    */   }
/*    */   
/*    */   public static void setHideFlags(ItemStack stack, int mask) {
/* 30 */     stack.m_41784_().m_128405_("HideFlags", mask);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\GunTooltipPart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */