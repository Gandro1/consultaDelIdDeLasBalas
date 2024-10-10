/*    */ package com.tacz.guns.item;
/*    */ 
/*    */ import com.tacz.guns.client.renderer.item.AmmoItemRenderer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
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
/*    */ class null
/*    */   implements IClientItemExtensions
/*    */ {
/*    */   public BlockEntityWithoutLevelRenderer getCustomRenderer() {
/* 73 */     Minecraft minecraft = Minecraft.m_91087_();
/* 74 */     return (BlockEntityWithoutLevelRenderer)new AmmoItemRenderer(minecraft.m_167982_(), minecraft.m_167973_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\AmmoItem$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */