/*    */ package com.tacz.guns.compat.cloth.common;
/*    */ import com.tacz.guns.config.common.GunConfig;
/*    */ import java.util.Objects;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class GunClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 11 */     ConfigCategory gun = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.common.gun"));
/*    */ 
/*    */ 
/*    */     
/* 15 */     Objects.requireNonNull(GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE); gun.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.common.gun.default_gun_fire_sound_distance"), ((Integer)GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(64).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.gun.default_gun_fire_sound_distance.desc") }).setSaveConsumer(GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 19 */     Objects.requireNonNull(GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE); gun.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.common.gun.default_gun_other_sound_distance"), ((Integer)GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(16).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.gun.default_gun_other_sound_distance.desc") }).setSaveConsumer(GunConfig.DEFAULT_GUN_OTHER_SOUND_DISTANCE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 23 */     Objects.requireNonNull(GunConfig.CREATIVE_PLAYER_CONSUME_AMMO); gun.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.gun.creative_player_consume_ammo"), ((Boolean)GunConfig.CREATIVE_PLAYER_CONSUME_AMMO.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.gun.creative_player_consume_ammo.desc") }).setSaveConsumer(GunConfig.CREATIVE_PLAYER_CONSUME_AMMO::set).build());
/*    */ 
/*    */ 
/*    */     
/* 27 */     Objects.requireNonNull(GunConfig.AUTO_RELOAD_WHEN_RESPAWN); gun.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.common.gun.auto_reload_when_respawn"), ((Boolean)GunConfig.AUTO_RELOAD_WHEN_RESPAWN.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.common.gun.auto_reload_when_respawn.desc") }).setSaveConsumer(GunConfig.AUTO_RELOAD_WHEN_RESPAWN::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\common\GunClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */