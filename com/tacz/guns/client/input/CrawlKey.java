/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.config.client.KeyConfig;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
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
/*    */ public class CrawlKey {
/* 25 */   public static final KeyMapping CRAWL_KEY = new KeyMapping("key.tacz.crawl.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 67, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onCrawlPress(InputEvent.Key event) {
/* 34 */     if (InputExtraCheck.isInGame() && CRAWL_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/* 35 */       IClientPlayerGunOperator operator; if (!((Boolean)SyncConfig.ENABLE_CRAWL.get()).booleanValue()) {
/*    */         return;
/*    */       }
/* 38 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 39 */       if (player == null || player.m_5833_() || player.m_20159_()) {
/*    */         return;
/*    */       }
/* 42 */       if (player instanceof IClientPlayerGunOperator) { operator = (IClientPlayerGunOperator)player; }
/*    */       else
/*    */       { return; }
/* 45 */        if (IGun.mainhandHoldGun((LivingEntity)player)) {
/* 46 */         boolean action = true;
/* 47 */         if (!((Boolean)KeyConfig.HOLD_TO_CRAWL.get()).booleanValue()) {
/* 48 */           action = !operator.isCrawl();
/*    */         }
/* 50 */         if (event.getAction() == 1) {
/* 51 */           IClientPlayerGunOperator.fromLocalPlayer(player).crawl(action);
/*    */         }
/* 53 */         if (((Boolean)KeyConfig.HOLD_TO_CRAWL.get()).booleanValue() && event.getAction() == 0)
/* 54 */           IClientPlayerGunOperator.fromLocalPlayer(player).crawl(false); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean onCrawlControllerPress(boolean isPress) {
/*    */     IClientPlayerGunOperator operator;
/* 61 */     if (!InputExtraCheck.isInGame()) {
/* 62 */       return false;
/*    */     }
/* 64 */     if (!((Boolean)SyncConfig.ENABLE_CRAWL.get()).booleanValue()) {
/* 65 */       return false;
/*    */     }
/* 67 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 68 */     if (player == null || player.m_5833_() || player.m_20159_()) {
/* 69 */       return false;
/*    */     }
/* 71 */     if (player instanceof IClientPlayerGunOperator) { operator = (IClientPlayerGunOperator)player; }
/* 72 */     else { return false; }
/*    */     
/* 74 */     if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/* 75 */       return false;
/*    */     }
/* 77 */     boolean action = true;
/* 78 */     if (!((Boolean)KeyConfig.HOLD_TO_CRAWL.get()).booleanValue()) {
/* 79 */       action = !operator.isCrawl();
/*    */     }
/* 81 */     if (isPress) {
/* 82 */       IClientPlayerGunOperator.fromLocalPlayer(player).crawl(action);
/* 83 */       return true;
/*    */     } 
/* 85 */     if (((Boolean)KeyConfig.HOLD_TO_CRAWL.get()).booleanValue()) {
/* 86 */       IClientPlayerGunOperator.fromLocalPlayer(player).crawl(false);
/* 87 */       return true;
/*    */     } 
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\CrawlKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */