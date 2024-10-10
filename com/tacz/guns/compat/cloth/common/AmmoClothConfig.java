/*    */ package com.tacz.guns.compat.cloth.common;
/*    */ import com.tacz.guns.config.common.AmmoConfig;
/*    */ import java.util.Objects;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class AmmoClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 12 */     ConfigCategory ammo = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.common.ammo"));
/*    */ 
/*    */ 
/*    */     
/* 16 */     Objects.requireNonNull(AmmoConfig.EXPLOSIVE_AMMO_DESTROYS_BLOCK); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_destroys_blocks"), ((Boolean)AmmoConfig.EXPLOSIVE_AMMO_DESTROYS_BLOCK.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_destroys_blocks.desc") }).setSaveConsumer(AmmoConfig.EXPLOSIVE_AMMO_DESTROYS_BLOCK::set).build());
/*    */ 
/*    */ 
/*    */     
/* 20 */     Objects.requireNonNull(AmmoConfig.EXPLOSIVE_AMMO_FIRE); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_fire"), ((Boolean)AmmoConfig.EXPLOSIVE_AMMO_FIRE.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_fire.desc") }).setSaveConsumer(AmmoConfig.EXPLOSIVE_AMMO_FIRE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 24 */     Objects.requireNonNull(AmmoConfig.EXPLOSIVE_AMMO_KNOCK_BACK); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_knock_back"), ((Boolean)AmmoConfig.EXPLOSIVE_AMMO_KNOCK_BACK.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_knock_back.desc") }).setSaveConsumer(AmmoConfig.EXPLOSIVE_AMMO_KNOCK_BACK::set).build());
/*    */ 
/*    */ 
/*    */     
/* 28 */     Objects.requireNonNull(AmmoConfig.EXPLOSIVE_AMMO_VISIBLE_DISTANCE); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_visible_distance"), ((Integer)AmmoConfig.EXPLOSIVE_AMMO_VISIBLE_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(192).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.explosive_ammo_visible_distance.desc") }).setSaveConsumer(AmmoConfig.EXPLOSIVE_AMMO_VISIBLE_DISTANCE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 32 */     Objects.requireNonNull(AmmoConfig.PASS_THROUGH_BLOCKS); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startStrList((Component)Component.m_237115_("config.tacz.common.ammo.pass_through_blocks"), (List)AmmoConfig.PASS_THROUGH_BLOCKS.get()).setDefaultValue(Lists.newArrayList()).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.pass_through_blocks.desc") }).setSaveConsumer(AmmoConfig.PASS_THROUGH_BLOCKS::set).build());
/*    */ 
/*    */ 
/*    */     
/* 36 */     Objects.requireNonNull(AmmoConfig.DESTROY_GLASS); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.destroy_glass"), ((Boolean)AmmoConfig.DESTROY_GLASS.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.destroy_glass.desc") }).setSaveConsumer(AmmoConfig.DESTROY_GLASS::set).build());
/*    */ 
/*    */ 
/*    */     
/* 40 */     Objects.requireNonNull(AmmoConfig.IGNITE_BLOCK); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.ignite_block"), ((Boolean)AmmoConfig.IGNITE_BLOCK.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.ignite_block.desc") }).setSaveConsumer(AmmoConfig.IGNITE_BLOCK::set).build());
/*    */ 
/*    */ 
/*    */     
/* 44 */     Objects.requireNonNull(AmmoConfig.IGNITE_ENTITY); ammo.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.ammo.ignite_entity"), ((Boolean)AmmoConfig.IGNITE_ENTITY.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.ammo.ignite_entity.desc") }).setSaveConsumer(AmmoConfig.IGNITE_ENTITY::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\common\AmmoClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */