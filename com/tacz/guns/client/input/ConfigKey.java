/*    */ package com.tacz.guns.client.input;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import com.tacz.guns.compat.cloth.MenuIntegration;
/*    */ import com.tacz.guns.init.CompatRegistry;
/*    */ import com.tacz.guns.util.InputExtraCheck;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.KeyMapping;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.chat.ClickEvent;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.HoverEvent;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.network.chat.Style;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.InputEvent;
/*    */ import net.minecraftforge.client.settings.IKeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyModifier;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class ConfigKey
/*    */ {
/* 30 */   public static final KeyMapping OPEN_CONFIG_KEY = new KeyMapping("key.tacz.open_config.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.ALT, InputConstants.Type.KEYSYM, 84, "key.category.tacz");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onOpenConfig(InputEvent.Key event) {
/* 39 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && OPEN_CONFIG_KEY
/* 40 */       .m_90832_(event.getKey(), event.getScanCode()) && OPEN_CONFIG_KEY
/* 41 */       .getKeyModifier().equals(KeyModifier.getActiveModifier())) {
/* 42 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 43 */       if (player == null || player.m_5833_()) {
/*    */         return;
/*    */       }
/* 46 */       if (!ModList.get().isLoaded("cloth_config")) {
/* 47 */         ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/cloth-config");
/* 48 */         HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.f_130831_, Component.m_237115_("gui.tacz.cloth_config_warning.download"));
/* 49 */         MutableComponent component = Component.m_237115_("gui.tacz.cloth_config_warning.tips").m_130938_(style -> style.m_131157_(ChatFormatting.BLUE).m_131157_(ChatFormatting.UNDERLINE).m_131142_(clickEvent).m_131144_(hoverEvent));
/*    */         
/* 51 */         player.m_213846_((Component)component);
/*    */       } else {
/* 53 */         CompatRegistry.checkModLoad("cloth_config", () -> Minecraft.m_91087_().m_91152_(MenuIntegration.getConfigScreen(null)));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\ConfigKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */