/*    */ package com.tacz.guns.client.model.papi;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class PlayerNamePapi
/*    */   implements Function<ItemStack, String>
/*    */ {
/*    */   public static final String NAME = "player_name";
/*    */   
/*    */   public String apply(ItemStack stack) {
/* 14 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 15 */     if (player != null) {
/* 16 */       return player.m_7755_().getString();
/*    */     }
/* 18 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\papi\PlayerNamePapi.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */