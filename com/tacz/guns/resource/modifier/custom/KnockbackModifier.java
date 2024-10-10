/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.BulletData;
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
/*     */ public class KnockbackModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "knockback";
/*     */   
/*     */   public String getId() {
/*  33 */     return "knockback";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  38 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  39 */     return new KnockbackJsonProperty(data.getKnockback());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*  45 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  46 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  47 */     BulletData bulletData = gunData.getBulletData();
/*  48 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */ 
/*     */     
/*  52 */     float finalBase = bulletData.getKnockback();
/*  53 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getKnockback()) : finalBase;
/*  54 */     return new CacheValue(Float.valueOf(finalBase));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  59 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  60 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  67 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  68 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  69 */     BulletData bulletData = gunData.getBulletData();
/*  70 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */ 
/*     */ 
/*     */     
/*  74 */     float finalBase = bulletData.getKnockback();
/*  75 */     finalBase = (fireModeAdjustData != null) ? (finalBase + fireModeAdjustData.getKnockback()) : finalBase;
/*  76 */     float modifier = ((Float)cacheProperty.getCache("knockback")).floatValue() - finalBase;
/*     */     
/*  78 */     double percent = Mth.m_14036_(finalBase, 0.0F, 1.0F);
/*  79 */     double modifierPercent = Mth.m_14036_(modifier, 0.0F, 1.0F);
/*     */     
/*  81 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.knockback";
/*  82 */     String positivelyString = String.format("%.2f §a(+%.2f)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/*  83 */     String negativelyString = String.format("%.2f §c(%.2f)", new Object[] { Float.valueOf(finalBase), Float.valueOf(modifier) });
/*  84 */     String defaultString = String.format("%.2f", new Object[] { Float.valueOf(finalBase) });
/*  85 */     boolean positivelyBetter = true;
/*     */     
/*  87 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(percent, modifierPercent, Float.valueOf(modifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  88 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  94 */     return 1;
/*     */   }
/*     */   
/*     */   public static class KnockbackJsonProperty extends JsonProperty<Modifier> {
/*     */     public KnockbackJsonProperty(Modifier value) {
/*  99 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 104 */       Modifier value = (Modifier)getValue();
/* 105 */       if (value != null) {
/* 106 */         double eval = AttachmentPropertyManager.eval(value, 0.2D);
/* 107 */         if (eval > 0.2D) {
/* 108 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.knockback.increase").m_130940_(ChatFormatting.GREEN));
/* 109 */         } else if (eval < 0.2D) {
/* 110 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.knockback.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("knockback")
/*     */     @Nullable
/* 117 */     private Modifier knockback = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getKnockback() {
/* 123 */       return this.knockback;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\KnockbackModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */