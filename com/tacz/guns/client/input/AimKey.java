/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.config.client.KeyConfig;
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
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class AimKey {
/* 25 */   public static final KeyMapping AIM_KEY = new KeyMapping("key.tacz.aim.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.MOUSE, 1, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onAimPress(InputEvent.MouseButton.Post event) {
/* 34 */     if (InputExtraCheck.isInGame() && AIM_KEY.m_90830_(event.getButton())) {
/* 35 */       IClientPlayerGunOperator operator; LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 36 */       if (player == null || player.m_5833_()) {
/*    */         return;
/*    */       }
/* 39 */       if (player instanceof IClientPlayerGunOperator) { operator = (IClientPlayerGunOperator)player; }
/*    */       else
/*    */       { return; }
/* 42 */        if (IGun.mainhandHoldGun((LivingEntity)player)) {
/* 43 */         boolean action = true;
/* 44 */         if (!((Boolean)KeyConfig.HOLD_TO_AIM.get()).booleanValue()) {
/* 45 */           action = !operator.isAim();
/*    */         }
/* 47 */         if (event.getAction() == 1) {
/* 48 */           IClientPlayerGunOperator.fromLocalPlayer(player).aim(action);
/*    */         }
/* 50 */         if (((Boolean)KeyConfig.HOLD_TO_AIM.get()).booleanValue() && event.getAction() == 0)
/* 51 */           IClientPlayerGunOperator.fromLocalPlayer(player).aim(false); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean onAimControllerPress(boolean isPress) {
/*    */     IClientPlayerGunOperator operator;
/* 58 */     if (!InputExtraCheck.isInGame()) {
/* 59 */       return false;
/*    */     }
/* 61 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 62 */     if (player == null || player.m_5833_()) {
/* 63 */       return false;
/*    */     }
/* 65 */     if (player instanceof IClientPlayerGunOperator) { operator = (IClientPlayerGunOperator)player; }
/* 66 */     else { return false; }
/*    */     
/* 68 */     if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/* 69 */       return false;
/*    */     }
/* 71 */     boolean action = true;
/* 72 */     if (!((Boolean)KeyConfig.HOLD_TO_AIM.get()).booleanValue()) {
/* 73 */       action = !operator.isAim();
/*    */     }
/* 75 */     if (isPress) {
/* 76 */       IClientPlayerGunOperator.fromLocalPlayer(player).aim(action);
/* 77 */       return true;
/*    */     } 
/* 79 */     if (((Boolean)KeyConfig.HOLD_TO_AIM.get()).booleanValue()) {
/* 80 */       IClientPlayerGunOperator.fromLocalPlayer(player).aim(false);
/* 81 */       return true;
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */   @SubscribeEvent
/*    */   public static void cancelAim(TickEvent.ClientTickEvent event) {
/*    */     IClientPlayerGunOperator operator;
/* 88 */     if (event.phase != TickEvent.Phase.END) {
/*    */       return;
/*    */     }
/* 91 */     Minecraft mc = Minecraft.m_91087_();
/* 92 */     LocalPlayer player = mc.f_91074_;
/* 93 */     if (player instanceof IClientPlayerGunOperator) { operator = (IClientPlayerGunOperator)player; }
/*    */     else
/*    */     { return; }
/* 96 */      if (operator.isAim() && !InputExtraCheck.isInGame())
/* 97 */       IClientPlayerGunOperator.fromLocalPlayer(player).aim(false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\AimKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */