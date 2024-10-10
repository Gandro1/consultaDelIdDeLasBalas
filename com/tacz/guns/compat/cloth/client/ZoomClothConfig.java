/*    */ package com.tacz.guns.compat.cloth.client;
/*    */ import com.tacz.guns.config.client.ZoomConfig;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class ZoomClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 11 */     ConfigCategory zoom = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.client.zoom"));
/*    */ 
/*    */ 
/*    */     
/* 15 */     Objects.requireNonNull(ZoomConfig.SCREEN_DISTANCE_COEFFICIENT); zoom.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((Component)Component.m_237115_("config.tacz.client.zoom.screen_distance_coefficient"), ((Double)ZoomConfig.SCREEN_DISTANCE_COEFFICIENT.get()).doubleValue()).setMin(0.0D).setMax(3.0D).setDefaultValue(1.33D).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.zoom.screen_distance_coefficient.desc") }).setSaveConsumer(ZoomConfig.SCREEN_DISTANCE_COEFFICIENT::set).build());
/*    */ 
/*    */ 
/*    */     
/* 19 */     Objects.requireNonNull(ZoomConfig.ZOOM_SENSITIVITY_BASE_MULTIPLIER); zoom.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((Component)Component.m_237115_("config.tacz.client.zoom.zoom_sensitivity_base_multiplier"), ((Double)ZoomConfig.ZOOM_SENSITIVITY_BASE_MULTIPLIER.get()).doubleValue()).setMin(0.0D).setMax(2.0D).setDefaultValue(1.0D).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.zoom.zoom_sensitivity_base_multiplier.desc") }).setSaveConsumer(ZoomConfig.ZOOM_SENSITIVITY_BASE_MULTIPLIER::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\client\ZoomClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */