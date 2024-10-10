/*    */ package com.tacz.guns.compat.cloth.client;
/*    */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*    */ import com.tacz.guns.compat.cloth.widget.CrosshairDropdown;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collectors;
/*    */ import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
/*    */ import me.shedaniel.clothconfig2.api.ConfigBuilder;
/*    */ import me.shedaniel.clothconfig2.api.ConfigCategory;
/*    */ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class RenderClothConfig {
/*    */   public static void init(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
/* 18 */     ConfigCategory render = root.getOrCreateCategory((Component)Component.m_237115_("config.tacz.client.render"));
/*    */ 
/*    */ 
/*    */     
/* 22 */     Objects.requireNonNull(RenderConfig.GUN_LOD_RENDER_DISTANCE); render.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.client.render.gun_lod_render_distance"), ((Integer)RenderConfig.GUN_LOD_RENDER_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(0).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.gun_lod_render_distance.desc") }).setSaveConsumer(RenderConfig.GUN_LOD_RENDER_DISTANCE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 26 */     Objects.requireNonNull(RenderConfig.BULLET_HOLE_PARTICLE_LIFE); render.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.client.render.bullet_hole_particle_life"), ((Integer)RenderConfig.BULLET_HOLE_PARTICLE_LIFE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(400).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.bullet_hole_particle_life.desc") }).setSaveConsumer(RenderConfig.BULLET_HOLE_PARTICLE_LIFE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 30 */     Objects.requireNonNull(RenderConfig.BULLET_HOLE_PARTICLE_FADE_THRESHOLD); render.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((Component)Component.m_237115_("config.tacz.client.render.bullet_hole_particle_fade_threshold"), ((Double)RenderConfig.BULLET_HOLE_PARTICLE_FADE_THRESHOLD.get()).doubleValue()).setMin(0.0D).setMax(1.0D).setDefaultValue(0.98D).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.bullet_hole_particle_fade_threshold.desc") }).setSaveConsumer(RenderConfig.BULLET_HOLE_PARTICLE_FADE_THRESHOLD::set).build());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     Objects.requireNonNull(RenderConfig.CROSSHAIR_TYPE); render.addEntry((AbstractConfigListEntry)entryBuilder.startDropdownMenu((Component)Component.m_237115_("config.tacz.client.render.crosshair_type"), CrosshairDropdown.of((CrosshairType)RenderConfig.CROSSHAIR_TYPE.get()), CrosshairDropdown.of()).setSelections((Iterable)Arrays.<CrosshairType>stream(CrosshairType.values()).sorted().sorted(Comparator.comparing(Enum::name)).collect(Collectors.toCollection(java.util.LinkedHashSet::new))).setDefaultValue(CrosshairType.DOT_1).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.crosshair_type.desc") }).setSaveConsumer(RenderConfig.CROSSHAIR_TYPE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 40 */     Objects.requireNonNull(RenderConfig.HIT_MARKET_START_POSITION); render.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((Component)Component.m_237115_("config.tacz.client.render.hit_market_start_position"), ((Double)RenderConfig.HIT_MARKET_START_POSITION.get()).doubleValue()).setMin(-1024.0D).setMax(1024.0D).setDefaultValue(4.0D).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.hit_market_start_position.desc") }).setSaveConsumer(RenderConfig.HIT_MARKET_START_POSITION::set).build());
/*    */ 
/*    */ 
/*    */     
/* 44 */     Objects.requireNonNull(RenderConfig.HEAD_SHOT_DEBUG_HITBOX); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.head_shot_debug_hitbox"), ((Boolean)RenderConfig.HEAD_SHOT_DEBUG_HITBOX.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.head_shot_debug_hitbox.desc") }).setSaveConsumer(RenderConfig.HEAD_SHOT_DEBUG_HITBOX::set).build());
/*    */ 
/*    */ 
/*    */     
/* 48 */     Objects.requireNonNull(RenderConfig.GUN_HUD_ENABLE); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.gun_hud_enable"), ((Boolean)RenderConfig.GUN_HUD_ENABLE.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.gun_hud_enable.desc") }).setSaveConsumer(RenderConfig.GUN_HUD_ENABLE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 52 */     Objects.requireNonNull(RenderConfig.KILL_AMOUNT_ENABLE); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.kill_amount_enable"), ((Boolean)RenderConfig.KILL_AMOUNT_ENABLE.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.kill_amount_enable.desc") }).setSaveConsumer(RenderConfig.KILL_AMOUNT_ENABLE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 56 */     Objects.requireNonNull(RenderConfig.KILL_AMOUNT_DURATION_SECOND); render.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((Component)Component.m_237115_("config.tacz.client.render.kill_amount_duration_second"), ((Double)RenderConfig.KILL_AMOUNT_DURATION_SECOND.get()).doubleValue()).setMin(0.0D).setMax(Double.MAX_VALUE).setDefaultValue(3.0D).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.kill_amount_duration_second.desc") }).setSaveConsumer(RenderConfig.KILL_AMOUNT_DURATION_SECOND::set).build());
/*    */ 
/*    */ 
/*    */     
/* 60 */     Objects.requireNonNull(RenderConfig.TARGET_RENDER_DISTANCE); render.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.client.render.target_render_distance"), ((Integer)RenderConfig.TARGET_RENDER_DISTANCE.get()).intValue()).setMin(0).setMax(2147483647).setDefaultValue(128).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.target_render_distance.desc") }).setSaveConsumer(RenderConfig.TARGET_RENDER_DISTANCE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 64 */     Objects.requireNonNull(RenderConfig.FIRST_PERSON_BULLET_TRACER_ENABLE); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.first_person_bullet_tracer_enable"), ((Boolean)RenderConfig.FIRST_PERSON_BULLET_TRACER_ENABLE.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.first_person_bullet_tracer_enable.desc") }).setSaveConsumer(RenderConfig.FIRST_PERSON_BULLET_TRACER_ENABLE::set).build());
/*    */ 
/*    */ 
/*    */     
/* 68 */     Objects.requireNonNull(RenderConfig.DISABLE_INTERACT_HUD_TEXT); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.disable_interact_hud_text"), ((Boolean)RenderConfig.DISABLE_INTERACT_HUD_TEXT.get()).booleanValue()).setDefaultValue(false).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.disable_interact_hud_text.desc") }).setSaveConsumer(RenderConfig.DISABLE_INTERACT_HUD_TEXT::set).build());
/*    */ 
/*    */ 
/*    */     
/* 72 */     Objects.requireNonNull(RenderConfig.DAMAGE_COUNTER_RESET_TIME); render.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((Component)Component.m_237115_("config.tacz.client.render.damage_counter_reset_time"), ((Integer)RenderConfig.DAMAGE_COUNTER_RESET_TIME.get()).intValue()).setMin(10).setMax(2147483647).setDefaultValue(2000).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.damage_counter_reset_time.desc") }).setSaveConsumer(RenderConfig.DAMAGE_COUNTER_RESET_TIME::set).build());
/*    */ 
/*    */ 
/*    */     
/* 76 */     Objects.requireNonNull(RenderConfig.DISABLE_MOVEMENT_ATTRIBUTE_FOV); render.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((Component)Component.m_237115_("config.tacz.client.render.disable_movement_fov"), ((Boolean)RenderConfig.DISABLE_MOVEMENT_ATTRIBUTE_FOV.get()).booleanValue()).setDefaultValue(true).setTooltip(new Component[] { (Component)Component.m_237115_("config.tacz.client.render.disable_movement_fov.desc") }).setSaveConsumer(RenderConfig.DISABLE_MOVEMENT_ATTRIBUTE_FOV::set).build());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\client\RenderClothConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */