/*    */ package com.tacz.guns.compat.cloth.client;
/*    */ import com.tacz.guns.config.client.KeyConfig;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class KeyClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 11 */     ConfigCategory key = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.client.key"));
/*    */ 
/*    */ 
/*    */     
/* 15 */     Objects.requireNonNull(KeyConfig.HOLD_TO_AIM); key.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.key.hold_to_aim"), ((Boolean)KeyConfig.HOLD_TO_AIM.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.key.hold_to_aim.desc") }).setSaveConsumer(KeyConfig.HOLD_TO_AIM::set).build());
/*    */ 
/*    */ 
/*    */     
/* 19 */     Objects.requireNonNull(KeyConfig.HOLD_TO_CRAWL); key.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.key.hold_to_crawl"), ((Boolean)KeyConfig.HOLD_TO_CRAWL.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.key.hold_to_crawl.desc") }).setSaveConsumer(KeyConfig.HOLD_TO_CRAWL::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\client\KeyClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */