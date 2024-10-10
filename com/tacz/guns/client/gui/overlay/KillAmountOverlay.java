/*    */ package com.tacz.guns.client.gui.overlay;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*    */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*    */ 
/*    */ public class KillAmountOverlay
/*    */   implements IGuiOverlay
/*    */ {
/* 17 */   private static long killTimestamp = -1L;
/* 18 */   private static int killAmount = 0;
/*    */   
/*    */   public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
/*    */     String text;
/* 22 */     if (!((Boolean)RenderConfig.KILL_AMOUNT_ENABLE.get()).booleanValue()) {
/*    */       return;
/*    */     }
/* 25 */     int timeout = (int)(((Double)RenderConfig.KILL_AMOUNT_DURATION_SECOND.get()).doubleValue() * 1000.0D);
/* 26 */     float colorCount = 30.0F;
/*    */     
/* 28 */     long remainTime = System.currentTimeMillis() - killTimestamp;
/* 29 */     if (remainTime > timeout) {
/*    */       return;
/*    */     }
/* 32 */     Minecraft mc = Minecraft.m_91087_();
/* 33 */     LocalPlayer player = mc.f_91074_;
/* 34 */     if (!(player instanceof com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator)) {
/*    */       return;
/*    */     }
/* 37 */     ItemStack stack = player.m_21205_();
/* 38 */     if (!(stack.m_41720_() instanceof com.tacz.guns.api.item.IGun)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 43 */     if (killAmount < 10) {
/* 44 */       text = "☠ x 0" + killAmount;
/*    */     } else {
/* 46 */       text = "☠ x " + killAmount;
/*    */     } 
/* 48 */     int fontWith = mc.f_91062_.m_92895_(text);
/* 49 */     double fadeOutTime = timeout / 3.0D * 2.0D;
/* 50 */     float hue = (1.0F - Math.min(killAmount / colorCount, 1.0F)) * 0.15F;
/* 51 */     int alpha = 255;
/* 52 */     if (remainTime > fadeOutTime) {
/* 53 */       alpha = 255 - (int)((remainTime - fadeOutTime) / (timeout - fadeOutTime) * 240.0D);
/*    */     }
/* 55 */     int color = Mth.m_14169_(hue, 0.75F, 1.0F) + (alpha << 24);
/*    */     
/* 57 */     RenderSystem.enableBlend();
/* 58 */     RenderSystem.defaultBlendFunc();
/*    */     
/* 60 */     PoseStack poseStack = graphics.m_280168_();
/*    */     
/* 62 */     poseStack.m_85836_();
/*    */     
/* 64 */     poseStack.m_85841_(0.5F, 0.5F, 1.0F);
/* 65 */     graphics.m_280488_(mc.f_91062_, text, (int)(width - fontWith / 2.0F), (height - 45) * 2 - 1, color);
/*    */     
/* 67 */     poseStack.m_85849_();
/* 68 */     RenderSystem.disableBlend();
/*    */   }
/*    */   
/*    */   public static void markTimestamp() {
/* 72 */     int timeout = (int)(((Double)RenderConfig.KILL_AMOUNT_DURATION_SECOND.get()).doubleValue() * 1000.0D);
/* 73 */     if (System.currentTimeMillis() - killTimestamp > timeout) {
/* 74 */       killAmount = 0;
/*    */     }
/* 76 */     killTimestamp = System.currentTimeMillis();
/* 77 */     killAmount++;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\overlay\KillAmountOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */