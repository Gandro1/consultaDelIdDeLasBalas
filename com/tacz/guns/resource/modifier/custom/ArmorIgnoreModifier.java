/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
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
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class ArmorIgnoreModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "armor_ignore";
/*     */   
/*     */   public String getId() {
/*  35 */     return "armor_ignore";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  40 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  41 */     return new ArmorIgnoreJsonProperty(data.getArmorIgnore());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*  47 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  48 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  49 */     BulletData bulletData = gunData.getBulletData();
/*  50 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */     
/*  53 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */ 
/*     */     
/*  56 */     float finalBase = (extraDamage != null) ? extraDamage.getArmorIgnore() : 0.0F;
/*  57 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getArmorIgnore()) : finalBase;
/*  58 */     finalBase = (float)(finalBase * ((Double)SyncConfig.ARMOR_IGNORE_BASE_MULTIPLIER.get()).doubleValue());
/*  59 */     return new CacheValue(Float.valueOf(finalBase));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  64 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  65 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  72 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  73 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  74 */     BulletData bulletData = gunData.getBulletData();
/*  75 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */     
/*  78 */     ExtraDamage extraDamage = bulletData.getExtraDamage();
/*     */ 
/*     */     
/*  81 */     float finalBase = (extraDamage != null) ? extraDamage.getArmorIgnore() : 0.0F;
/*  82 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getArmorIgnore()) : finalBase;
/*  83 */     finalBase = (float)(finalBase * ((Double)SyncConfig.ARMOR_IGNORE_BASE_MULTIPLIER.get()).doubleValue());
/*  84 */     float modifier = ((Float)cacheProperty.getCache("armor_ignore")).floatValue() - finalBase;
/*     */     
/*  86 */     double percent = Mth.m_14036_(finalBase, 0.0F, 1.0F);
/*  87 */     double modifierPercent = Mth.m_14036_(modifier, 0.0F, 1.0F);
/*  88 */     finalBase *= 100.0F;
/*  89 */     modifier *= 100.0F;
/*     */     
/*  91 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.armor_ignore";
/*  92 */     String positivelyString = String.format("%.1f%% §a(+%.1f%%)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/*  93 */     String negativelyString = String.format("%.1f%% §c(%.1f%%)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/*  94 */     String defaultString = String.format("%.1f%%", new Object[] { Float.valueOf(finalBase) });
/*  95 */     boolean positivelyBetter = true;
/*     */     
/*  97 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(percent, modifierPercent, Float.valueOf(modifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  98 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/* 104 */     return 1;
/*     */   }
/*     */   
/*     */   public static class ArmorIgnoreJsonProperty extends JsonProperty<Modifier> {
/*     */     public ArmorIgnoreJsonProperty(Modifier value) {
/* 109 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 114 */       Modifier value = (Modifier)getValue();
/* 115 */       if (value != null) {
/* 116 */         double eval = AttachmentPropertyManager.eval(value, 0.5D);
/* 117 */         if (eval > 0.5D) {
/* 118 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.armor_ignore.increase").m_130940_(ChatFormatting.GREEN));
/* 119 */         } else if (eval < 0.5D) {
/* 120 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.armor_ignore.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("armor_ignore")
/*     */     @Nullable
/* 127 */     private Modifier armorIgnore = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getArmorIgnore() {
/* 133 */       return this.armorIgnore;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\ArmorIgnoreModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */