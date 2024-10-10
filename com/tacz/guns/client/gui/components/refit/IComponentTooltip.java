/*    */ package com.tacz.guns.client.gui.components.refit;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.Options;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IComponentTooltip
/*    */ {
/*    */   static List<Component> getTooltipFromItem(ItemStack stack) {
/* 18 */     Options options = (Minecraft.m_91087_()).f_91066_;
/* 19 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 20 */     return stack.m_41651_((Player)player, options.f_92125_ ? (TooltipFlag)TooltipFlag.Default.f_256730_ : (TooltipFlag)TooltipFlag.Default.f_256752_);
/*    */   }
/*    */   
/*    */   void renderTooltip(Consumer<List<Component>> paramConsumer);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\components\refit\IComponentTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */