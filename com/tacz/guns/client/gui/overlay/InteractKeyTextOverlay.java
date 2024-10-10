/*    */ package com.tacz.guns.client.gui.overlay;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.input.InteractKey;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import com.tacz.guns.config.util.InteractKeyConfigRead;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.FormattedText;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.EntityHitResult;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*    */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class InteractKeyTextOverlay implements IGuiOverlay {
/*    */   public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
/* 27 */     if (((Boolean)RenderConfig.DISABLE_INTERACT_HUD_TEXT.get()).booleanValue()) {
/*    */       return;
/*    */     }
/* 30 */     Minecraft mc = Minecraft.m_91087_();
/* 31 */     LocalPlayer player = mc.f_91074_;
/* 32 */     if (player == null || player.m_5833_()) {
/*    */       return;
/*    */     }
/* 35 */     if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/*    */       return;
/*    */     }
/* 38 */     HitResult hitResult = mc.f_91077_;
/* 39 */     if (hitResult == null) {
/*    */       return;
/*    */     }
/* 42 */     if (hitResult instanceof BlockHitResult) { BlockHitResult blockHitResult = (BlockHitResult)hitResult;
/* 43 */       renderBlockText(graphics, width, height, blockHitResult, player, mc);
/*    */       return; }
/*    */     
/* 46 */     if (hitResult instanceof EntityHitResult) { EntityHitResult entityHitResult = (EntityHitResult)hitResult;
/* 47 */       renderEntityText(graphics, width, height, entityHitResult, mc); }
/*    */   
/*    */   }
/*    */   
/*    */   private static void renderBlockText(GuiGraphics graphics, int width, int height, BlockHitResult blockHitResult, LocalPlayer player, Minecraft mc) {
/* 52 */     BlockPos blockPos = blockHitResult.m_82425_();
/* 53 */     BlockState block = player.m_9236_().m_8055_(blockPos);
/* 54 */     if (InteractKeyConfigRead.canInteractBlock(block)) {
/* 55 */       renderText(graphics, width, height, mc.f_91062_);
/*    */     }
/*    */   }
/*    */   
/*    */   private static void renderEntityText(GuiGraphics graphics, int width, int height, EntityHitResult entityHitResult, Minecraft mc) {
/* 60 */     Entity entity = entityHitResult.m_82443_();
/* 61 */     if (InteractKeyConfigRead.canInteractEntity(entity)) {
/* 62 */       renderText(graphics, width, height, mc.f_91062_);
/*    */     }
/*    */   }
/*    */   
/*    */   private static void renderText(GuiGraphics graphics, int width, int height, Font font) {
/* 67 */     String keyName = InteractKey.INTERACT_KEY.m_90863_().getString();
/* 68 */     MutableComponent mutableComponent = Component.m_237110_("gui.tacz.interact_key.text.desc", new Object[] { StringUtils.capitalize(keyName) });
/* 69 */     graphics.m_280614_(font, (Component)mutableComponent, (int)((width - font.m_92852_((FormattedText)mutableComponent)) / 2.0F), (int)(height / 2.0F - 25.0F), ChatFormatting.YELLOW.m_126665_().intValue(), false);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\overlay\InteractKeyTextOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */