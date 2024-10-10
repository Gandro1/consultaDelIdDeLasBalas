/*    */ package com.tacz.guns.client.input;
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.gui.GunRefitScreen;
/*    */ import com.tacz.guns.util.InputExtraCheck;
/*    */ import net.minecraft.client.KeyMapping;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
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
/*    */ public class RefitKey {
/* 23 */   public static final KeyMapping REFIT_KEY = new KeyMapping("key.tacz.refit.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 90, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onRefitPress(InputEvent.Key event) {
/* 32 */     if (event.getAction() == 1 && REFIT_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 33 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 34 */       if (player == null || player.m_5833_()) {
/*    */         return;
/*    */       }
/* 37 */       if (InputExtraCheck.isInGame()) {
/* 38 */         if (IGun.mainhandHoldGun((LivingEntity)player) && (Minecraft.m_91087_()).f_91080_ == null) {
/* 39 */           IGun iGun = IGun.getIGunOrNull(player.m_21205_());
/* 40 */           if (iGun != null && iGun.hasAttachmentLock(player.m_21205_())) {
/*    */             return;
/*    */           }
/* 43 */           Minecraft.m_91087_().m_91152_((Screen)new GunRefitScreen());
/*    */         } 
/* 45 */       } else if ((Minecraft.m_91087_()).f_91080_ instanceof GunRefitScreen) {
/* 46 */         Minecraft.m_91087_().m_91152_(null);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\RefitKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */