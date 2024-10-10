/*     */ package com.tacz.guns.resource.modifier;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.tacz.guns.GunMod;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.AttachmentPropertyEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.AdsModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.AmmoSpeedModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.DamageModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.IgniteModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.InaccuracyModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.RecoilModifier;
/*     */ import com.tacz.guns.resource.modifier.custom.SilenceModifier;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.script.ScriptException;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ 
/*     */ public class AttachmentPropertyManager {
/*  27 */   private static final ScriptEngine LUAJ_ENGINE = (new LuaScriptEngineFactory()).getScriptEngine();
/*  28 */   private static final Map<String, IAttachmentModifier<?, ?>> MODIFIERS = Maps.newLinkedHashMap();
/*     */   
/*     */   public static void registerModifier() {
/*  31 */     MODIFIERS.put("ads", new AdsModifier());
/*  32 */     MODIFIERS.put("aim_inaccuracy", new AimInaccuracyModifier());
/*  33 */     MODIFIERS.put("ammo_speed", new AmmoSpeedModifier());
/*  34 */     MODIFIERS.put("armor_ignore", new ArmorIgnoreModifier());
/*  35 */     MODIFIERS.put("damage", new DamageModifier());
/*  36 */     MODIFIERS.put("effective_range", new EffectiveRangeModifier());
/*  37 */     MODIFIERS.put("explosion", new ExplosionModifier());
/*  38 */     MODIFIERS.put("head_shot", new HeadShotModifier());
/*  39 */     MODIFIERS.put("ignite", new IgniteModifier());
/*  40 */     MODIFIERS.put("inaccuracy", new InaccuracyModifier());
/*  41 */     MODIFIERS.put("knockback", new KnockbackModifier());
/*  42 */     MODIFIERS.put("pierce", new PierceModifier());
/*  43 */     MODIFIERS.put("recoil", new RecoilModifier());
/*  44 */     MODIFIERS.put("rpm", new RpmModifier());
/*  45 */     MODIFIERS.put("silence", new SilenceModifier());
/*  46 */     MODIFIERS.put("weight_modifier", new WeightModifier());
/*  47 */     MODIFIERS.put("movement_speed", new ExtraMovementModifier());
/*     */   }
/*     */   
/*     */   public static Map<String, IAttachmentModifier<?, ?>> getModifiers() {
/*  51 */     return MODIFIERS;
/*     */   }
/*     */   public static void postChangeEvent(LivingEntity shooter, ItemStack gunItem) {
/*     */     IGun iGun;
/*  55 */     Item item = gunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  58 */      ResourceLocation gunId = iGun.getGunId(gunItem);
/*  59 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(index -> {
/*     */           AttachmentCacheProperty cacheProperty = new AttachmentCacheProperty();
/*     */           MinecraftForge.EVENT_BUS.post((Event)new AttachmentPropertyEvent(gunItem, cacheProperty));
/*     */           IGunOperator.fromLivingEntity(shooter).updateCacheProperty(cacheProperty);
/*     */         });
/*     */   }
/*     */   
/*     */   public static double eval(Modifier modifier, double defaultValue) {
/*  67 */     return eval(Collections.singletonList(modifier), defaultValue);
/*     */   }
/*     */   
/*     */   public static double eval(List<Modifier> modifiers, double defaultValue) {
/*  71 */     double addend = defaultValue;
/*  72 */     double percent = 1.0D;
/*  73 */     double multiplier = 1.0D;
/*  74 */     for (Modifier modifier : modifiers) {
/*  75 */       addend += modifier.getAddend();
/*  76 */       percent += modifier.getPercent();
/*  77 */       multiplier *= Math.max(modifier.getMultiplier(), 0.0D);
/*     */     } 
/*  79 */     percent = Math.max(percent, 0.0D);
/*  80 */     double value = addend * percent * multiplier;
/*  81 */     for (Modifier modifier : modifiers) {
/*  82 */       String function = modifier.getFunction();
/*  83 */       if (StringUtils.isEmpty(function)) {
/*     */         continue;
/*     */       }
/*  86 */       value = functionEval(value, defaultValue, function);
/*     */     } 
/*  88 */     return value;
/*     */   }
/*     */   
/*     */   public static boolean eval(List<Boolean> modified, boolean defaultValue) {
/*  92 */     if (defaultValue)
/*     */     {
/*  94 */       return modified.stream().allMatch(s -> s.booleanValue());
/*     */     }
/*     */     
/*  97 */     return modified.stream().anyMatch(s -> s.booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double functionEval(double value, double defaultValue, String script) {
/* 102 */     script = script.toLowerCase(Locale.ENGLISH);
/* 103 */     LUAJ_ENGINE.put("x", Double.valueOf(value));
/* 104 */     LUAJ_ENGINE.put("r", Double.valueOf(defaultValue));
/*     */     try {
/* 106 */       LUAJ_ENGINE.eval(script);
/* 107 */     } catch (ScriptException e) {
/* 108 */       GunMod.LOGGER.catching(e);
/*     */     } 
/* 110 */     Object object = LUAJ_ENGINE.get("y"); if (object instanceof Number) { Number number = (Number)object;
/* 111 */       return number.doubleValue(); }
/*     */     
/* 113 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\AttachmentPropertyManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */