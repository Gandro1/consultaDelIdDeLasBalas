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
/*    */ public class FireSelectKey {
/* 23 */   public static final KeyMapping FIRE_SELECT_KEY = new KeyMapping("key.tacz.fire_select.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 71, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onFireSelectKeyPress(InputEvent.Key event) {
/* 32 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && FIRE_SELECT_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 33 */       doFireSelectLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onFireSelectMousePress(InputEvent.MouseButton.Post event) {
/* 39 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && FIRE_SELECT_KEY.m_90830_(event.getButton())) {
/* 40 */       doFireSelectLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean onFireSelectControllerPress(boolean isPress) {
/* 45 */     if (InputExtraCheck.isInGame() && isPress) {
/* 46 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 47 */       if (player == null || player.m_5833_()) {
/* 48 */         return false;
/*    */       }
/* 50 */       if (IGun.mainhandHoldGun((LivingEntity)player)) {
/* 51 */         IClientPlayerGunOperator.fromLocalPlayer(player).fireSelect();
/* 52 */         return true;
/*    */       } 
/*    */     } 
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   private static void doFireSelectLogic() {
/* 59 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 60 */     if (player == null || player.m_5833_()) {
/*    */       return;
/*    */     }
/* 63 */     if (IGun.mainhandHoldGun((LivingEntity)player))
/* 64 */       IClientPlayerGunOperator.fromLocalPlayer(player).fireSelect(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\FireSelectKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */