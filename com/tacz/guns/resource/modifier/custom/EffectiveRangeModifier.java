/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class EffectiveRangeModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "effective_range";
/*     */   
/*     */   public String getId() {
/*  29 */     return "effective_range";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  34 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  35 */     return new EffectiveRangeJsonProperty(data.getEffectiveRange());
/*     */   }
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*     */     float effectiveRange;
/*  40 */     LinkedList<ExtraDamage.DistanceDamagePair> damageAdjust = null;
/*  41 */     if (gunData.getBulletData().getExtraDamage() != null) {
/*  42 */       damageAdjust = gunData.getBulletData().getExtraDamage().getDamageAdjust();
/*     */     }
/*     */     
/*  45 */     if (damageAdjust != null) {
/*  46 */       effectiveRange = ((ExtraDamage.DistanceDamagePair)damageAdjust.get(0)).getDistance();
/*     */     } else {
/*  48 */       effectiveRange = 2.1474836E9F;
/*     */     } 
/*  50 */     return new CacheValue(Float.valueOf(effectiveRange));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  55 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  56 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  63 */     float effectiveRange, distanceModifier = ((Float)cacheProperty.getCache("effective_range")).floatValue();
/*  64 */     LinkedList<ExtraDamage.DistanceDamagePair> damageAdjust = null;
/*  65 */     if (gunData.getBulletData().getExtraDamage() != null) {
/*  66 */       damageAdjust = gunData.getBulletData().getExtraDamage().getDamageAdjust();
/*     */     }
/*     */     
/*  69 */     if (damageAdjust != null) {
/*  70 */       effectiveRange = ((ExtraDamage.DistanceDamagePair)damageAdjust.get(0)).getDistance();
/*     */     } else {
/*  72 */       effectiveRange = 0.0F;
/*     */     } 
/*  74 */     float modifier = distanceModifier - effectiveRange;
/*     */     
/*  76 */     double percent = Math.min(effectiveRange / 100.0D, 1.0D);
/*  77 */     double modifierPercent = Math.min(modifier / 100.0D, 1.0D);
/*     */     
/*  79 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.effective_range";
/*  80 */     String positivelyString = String.format("%.1fm §a(+%.1f)", new Object[] { Float.valueOf(effectiveRange), Float.valueOf(modifier) });
/*  81 */     String negativelyString = String.format("%.1fm §c(%.1f)", new Object[] { Float.valueOf(effectiveRange), Float.valueOf(modifier) });
/*  82 */     String defaultString = String.format("%.1fm", new Object[] { Float.valueOf(effectiveRange) });
/*  83 */     boolean positivelyBetter = true;
/*     */     
/*  85 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(percent, modifierPercent, Float.valueOf(modifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  86 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  92 */     return 1;
/*     */   }
/*     */   
/*     */   public static class EffectiveRangeJsonProperty extends JsonProperty<Modifier> {
/*     */     public EffectiveRangeJsonProperty(Modifier value) {
/*  97 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 102 */       Modifier value = (Modifier)getValue();
/* 103 */       if (value != null) {
/* 104 */         double eval = AttachmentPropertyManager.eval(value, 25.0D);
/* 105 */         if (eval > 25.0D) {
/* 106 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.effective_range.increase").m_130940_(ChatFormatting.GREEN));
/* 107 */         } else if (eval < 25.0D) {
/* 108 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.effective_range.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("effective_range")
/*     */     @Nullable
/* 115 */     private Modifier effectiveRange = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getEffectiveRange() {
/* 121 */       return this.effectiveRange;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\EffectiveRangeModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */