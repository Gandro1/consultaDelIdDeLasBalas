/*    */ package com.tacz.guns.api.client.other;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface KeepingItemRenderer
/*    */ {
/*    */   void keep(ItemStack paramItemStack, long paramLong);
/*    */   
/*    */   ItemStack getCurrentItem();
/*    */   
/*    */   static KeepingItemRenderer getRenderer() {
/* 28 */     return (KeepingItemRenderer)Minecraft.m_91087_().m_91290_().m_234586_();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\other\KeepingItemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */