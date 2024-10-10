/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.ClientMessagePlayerZoom;
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
/*    */ public class ZoomKey
/*    */ {
/* 24 */   public static final KeyMapping ZOOM_KEY = new KeyMapping("key.tacz.zoom.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 86, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onZoomKeyPress(InputEvent.Key event) {
/* 33 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && ZOOM_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 34 */       doZoomLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onZoomMousePress(InputEvent.MouseButton.Post event) {
/* 40 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && ZOOM_KEY.m_90830_(event.getButton())) {
/* 41 */       doZoomLogic();
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean onZoomControllerPress(boolean isPress) {
/* 46 */     if (InputExtraCheck.isInGame() && isPress) {
/* 47 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 48 */       if (player == null || player.m_5833_()) {
/* 49 */         return false;
/*    */       }
/* 51 */       IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 52 */       if (operator.isAim()) {
/* 53 */         NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerZoom());
/* 54 */         return true;
/*    */       } 
/*    */     } 
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   private static void doZoomLogic() {
/* 61 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 62 */     if (player == null || player.m_5833_()) {
/*    */       return;
/*    */     }
/* 65 */     IClientPlayerGunOperator operator = IClientPlayerGunOperator.fromLocalPlayer(player);
/* 66 */     if (operator.isAim())
/* 67 */       NetworkHandler.CHANNEL.sendToServer(new ClientMessagePlayerZoom()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\ZoomKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */