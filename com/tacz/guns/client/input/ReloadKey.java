/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.util.InputExtraCheck;
/*    */ import net.minecraft.client.KeyMapping;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.InputEvent;
/*    */ import net.minecraftforge.client.settings.IKeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyModifier;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class ReloadKey {
/* 23 */   public static final KeyMapping RELOAD_KEY = new KeyMapping("key.tacz.reload.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 82, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onReloadPress(InputEvent.Key event) {
/* 32 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && RELOAD_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 33 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 34 */       if (player == null || player.m_5833_()) {
/*    */         return;
/*    */       }
/* 37 */       if (IGun.mainhandHoldGun((LivingEntity)player)) {
/* 38 */         IClientPlayerGunOperator.fromLocalPlayer(player).reload();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean onReloadControllerPress(boolean isPress) {
/* 44 */     if (InputExtraCheck.isInGame() && isPress) {
/* 45 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 46 */       if (player == null || player.m_5833_()) {
/* 47 */         return false;
/*    */       }
/* 49 */       if (IGun.mainhandHoldGun((LivingEntity)player)) {
/* 50 */         IClientPlayerGunOperator.fromLocalPlayer(player).reload();
/* 51 */         return true;
/*    */       } 
/*    */     } 
/* 54 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\ReloadKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */