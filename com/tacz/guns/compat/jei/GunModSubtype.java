/*    */ package com.tacz.guns.compat.jei;
/*    */ import com.tacz.guns.api.item.IAmmo;
/*    */ import com.tacz.guns.api.item.IAmmoBox;
/*    */ import com.tacz.guns.api.item.IAttachment;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class GunModSubtype {
/*    */   public static IIngredientSubtypeInterpreter<ItemStack> getAmmoSubtype() {
/* 12 */     return (stack, context) -> {
/*    */         Item patt483$temp = stack.m_41720_();
/*    */         if (patt483$temp instanceof IAmmo) {
/*    */           IAmmo iAmmo = (IAmmo)patt483$temp;
/*    */           return iAmmo.getAmmoId(stack).toString();
/*    */         } 
/*    */         return "";
/*    */       };
/*    */   } public static IIngredientSubtypeInterpreter<ItemStack> getGunSubtype() {
/* 21 */     return (stack, context) -> {
/*    */         Item patt800$temp = stack.m_41720_();
/*    */         if (patt800$temp instanceof IGun) {
/*    */           IGun iGun = (IGun)patt800$temp;
/*    */           return iGun.getGunId(stack).toString();
/*    */         } 
/*    */         return "";
/*    */       };
/*    */   } public static IIngredientSubtypeInterpreter<ItemStack> getAttachmentSubtype() {
/* 30 */     return (stack, context) -> {
/*    */         Item patt1120$temp = stack.m_41720_();
/*    */         if (patt1120$temp instanceof IAttachment) {
/*    */           IAttachment iAttachment = (IAttachment)patt1120$temp;
/*    */           return iAttachment.getAttachmentId(stack).toString();
/*    */         } 
/*    */         return "";
/*    */       };
/*    */   } public static IIngredientSubtypeInterpreter<ItemStack> getAmmoBoxSubtype() {
/* 39 */     return (stack, context) -> {
/*    */         Item patt1465$temp = stack.m_41720_();
/*    */         if (patt1465$temp instanceof IAmmoBox) {
/*    */           IAmmoBox iAmmoBox = (IAmmoBox)patt1465$temp;
/*    */           return iAmmoBox.isAllTypeCreative(stack) ? "all_type_creative" : (iAmmoBox.isCreative(stack) ? "creative" : String.format("level_%d", new Object[] { Integer.valueOf(iAmmoBox.getAmmoLevel(stack)) }));
/*    */         } 
/*    */         return "";
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\jei\GunModSubtype.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */