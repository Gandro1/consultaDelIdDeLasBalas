/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.util.InputExtraCheck;
/*    */ import net.minecraft.client.KeyMapping;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
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
/*    */ public class MeleeKey
/*    */ {
/* 22 */   public static final KeyMapping MELEE_KEY = new KeyMapping("key.tacz.melee.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 86, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onMeleeKeyPress(InputEvent.Key event) {
/* 31 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && MELEE_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 32 */       doMeleeLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onMeleeMousePress(InputEvent.MouseButton.Post event) {
/* 38 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && MELEE_KEY.m_90830_(event.getButton())) {
/* 39 */       doMeleeLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean onMeleeControllerPress(boolean isPress) {
/* 44 */     if (InputExtraCheck.isInGame() && isPress) {
/* 45 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 46 */       if (player == null || player.m_5833_()) {
/* 47 */         return false;
/*    */       }
/* 49 */       IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 50 */       if (!operator.isAim()) {
/* 51 */         operator.melee();
/* 52 */         return true;
/*    */       } 
/*    */     } 
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   private static void doMeleeLogic() {
/* 59 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 60 */     if (player == null || player.m_5833_()) {
/*    */       return;
/*    */     }
/* 63 */     IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 64 */     if (!operator.isAim())
/* 65 */       operator.melee(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\MeleeKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */