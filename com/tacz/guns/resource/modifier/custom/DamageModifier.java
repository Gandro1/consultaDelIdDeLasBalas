/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.config.sync.SyncConfig;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunFireModeAdjustData;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ 
/*     */ public class DamageModifier
/*     */   implements IAttachmentModifier<Modifier, LinkedList<ExtraDamage.DistanceDamagePair>>
/*     */ {
/*     */   public static final String ID = "damage";
/*     */   
/*     */   public String getId() {
/*  37 */     return "damage";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  42 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  43 */     return new DamageJsonProperty(data.getDamage());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CacheValue<LinkedList<ExtraDamage.DistanceDamagePair>> initCache(ItemStack gunItem, GunData gunData) {
/*  49 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  50 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  51 */     BulletData bulletData = gunData.getBulletData();
/*  52 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */     
/*  55 */     float rawDamage = bulletData.getDamageAmount();
/*     */     
/*  57 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */     
/*  59 */     float fireAdjustDamageAmount = (fireModeAdjustData != null) ? fireModeAdjustData.getDamageAmount() : 0.0F;
/*     */ 
/*     */     
/*  62 */     LinkedList<ExtraDamage.DistanceDamagePair> cacheValue = Lists.newLinkedList();
/*  63 */     if (extraDamage != null && extraDamage.getDamageAdjust() != null) {
/*  64 */       for (ExtraDamage.DistanceDamagePair pair : extraDamage.getDamageAdjust()) {
/*  65 */         float finalBaseDamage = pair.getDamage() + fireAdjustDamageAmount;
/*  66 */         cacheValue.add(new ExtraDamage.DistanceDamagePair(pair.getDistance(), (float)(finalBaseDamage * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue())));
/*     */       } 
/*     */     } else {
/*  69 */       float finalBaseDamage = rawDamage + fireAdjustDamageAmount;
/*  70 */       cacheValue.add(new ExtraDamage.DistanceDamagePair(2.1474836E9F, (float)(finalBaseDamage * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue())));
/*     */     } 
/*  72 */     return new CacheValue(cacheValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<LinkedList<ExtraDamage.DistanceDamagePair>> cache) {
/*  77 */     LinkedList<ExtraDamage.DistanceDamagePair> cacheValue = (LinkedList<ExtraDamage.DistanceDamagePair>)cache.getValue();
/*  78 */     LinkedList<ExtraDamage.DistanceDamagePair> modifiedValue = new LinkedList<>();
/*  79 */     for (ExtraDamage.DistanceDamagePair pair : cacheValue) {
/*  80 */       float base = pair.getDamage();
/*  81 */       float eval = (float)AttachmentPropertyManager.eval(modifiers, base);
/*  82 */       modifiedValue.add(new ExtraDamage.DistanceDamagePair(pair.getDistance(), eval));
/*     */     } 
/*  84 */     cache.setValue(modifiedValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  91 */     LinkedList<ExtraDamage.DistanceDamagePair> damagePairModifier = (LinkedList<ExtraDamage.DistanceDamagePair>)cacheProperty.getCache("damage");
/*  92 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  93 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  94 */     BulletData bulletData = gunData.getBulletData();
/*  95 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */     
/*  98 */     float rawDamage = bulletData.getDamageAmount();
/*     */     
/* 100 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */ 
/*     */     
/* 103 */     float finalBase = (fireModeAdjustData != null) ? fireModeAdjustData.getDamageAmount() : 0.0F;
/* 104 */     if (extraDamage != null && extraDamage.getDamageAdjust() != null) {
/* 105 */       finalBase += ((ExtraDamage.DistanceDamagePair)extraDamage.getDamageAdjust().get(0)).getDamage();
/*     */     } else {
/* 107 */       finalBase += rawDamage;
/*     */     } 
/* 109 */     finalBase = (float)(finalBase * ((Double)SyncConfig.DAMAGE_BASE_MULTIPLIER.get()).doubleValue());
/* 110 */     float modifier = ((ExtraDamage.DistanceDamagePair)damagePairModifier.get(0)).getDamage() - finalBase;
/*     */     
/* 112 */     double percent = Math.min(finalBase / 100.0D, 1.0D);
/* 113 */     double modifierPercent = Math.min(modifier / 100.0D, 1.0D);
/*     */     
/* 115 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.damage";
/* 116 */     String positivelyString = String.format("%.2f §a(+%.2f)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/* 117 */     String negativelyString = String.format("%.2f §c(%.2f)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/* 118 */     String defaultString = String.format("%.2f", new Object[] { Float.valueOf(finalBase) });
/* 119 */     boolean positivelyBetter = true;
/*     */     
/* 121 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(percent, modifierPercent, Float.valueOf(modifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/* 122 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/* 128 */     return 1;
/*     */   }
/*     */   
/*     */   public static class DamageJsonProperty extends JsonProperty<Modifier> {
/*     */     public DamageJsonProperty(Modifier value) {
/* 133 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 138 */       Modifier value = (Modifier)getValue();
/* 139 */       if (value != null) {
/* 140 */         double eval = AttachmentPropertyManager.eval(value, 9.0D);
/* 141 */         int damage = (int)Math.round(eval);
/* 142 */         if (damage > 9) {
/* 143 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.damage.increase").m_130940_(ChatFormatting.GREEN));
/* 144 */         } else if (damage < 9) {
/* 145 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.damage.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("damage")
/*     */     @Nullable
/* 152 */     private Modifier damage = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getDamage() {
/* 158 */       return this.damage;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\DamageModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */