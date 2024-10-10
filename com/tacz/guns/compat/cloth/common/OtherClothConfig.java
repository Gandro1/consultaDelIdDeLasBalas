/*    */ package com.tacz.guns.compat.cloth.common;
/*    */ import com.tacz.guns.config.common.OtherConfig;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class OtherClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 11 */     ConfigCategory other = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.common.other"));
/*    */ 
/*    */ 
/*    */     
/* 15 */     Objects.requireNonNull(OtherConfig.DEFAULT_PACK_DEBUG); other.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.other.default_pack_debug"), ((Boolean)OtherConfig.DEFAULT_PACK_DEBUG.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.other.default_pack_debug.desc") }).setSaveConsumer(OtherConfig.DEFAULT_PACK_DEBUG::set).build());
/*    */ 
/*    */ 
/*    */     
/* 19 */     Objects.requireNonNull(OtherConfig.TARGET_SOUND_DISTANCE); other.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.common.other.target_sound_distance"), ((Integer)OtherConfig.TARGET_SOUND_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(128).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.other.target_sound_distance.desc") }).setSaveConsumer(OtherConfig.TARGET_SOUND_DISTANCE::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\common\OtherClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */