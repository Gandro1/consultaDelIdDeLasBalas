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
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class WeightModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "weight_modifier";
/*     */   
/*     */   public String getId() {
/*  27 */     return "weight_modifier";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  33 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  34 */     Modifier weightModifier = data.getWeightModifier();
/*     */     
/*  36 */     if (weightModifier == null) {
/*  37 */       weightModifier = new Modifier();
/*  38 */       weightModifier.setAddend(data.getWeightAddend());
/*     */     } 
/*  40 */     return new WeightJsonProperty(weightModifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*  45 */     return new CacheValue(Float.valueOf(gunData.getWeight()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  50 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  51 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOptionalFields() {
/*  56 */     return "weight";
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  62 */     float weight = gunData.getWeight();
/*  63 */     float modified = ((Float)cacheProperty.getCache("weight_modifier")).floatValue() - weight;
/*     */     
/*  65 */     double percent = Math.min(weight / 20.0D, 1.0D);
/*  66 */     double modifierPercent = Math.min(modified / 20.0D, 1.0D);
/*     */     
/*  68 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.weight";
/*  69 */     String positivelyString = String.format("%.2fkg §c(+%.2f)", new Object[] { Float.valueOf(weight), Float.valueOf(modified) });
/*  70 */     String negativelyString = String.format("%.2fkg §a(%.2f)", new Object[] { Float.valueOf(weight), Float.valueOf(modified) });
/*  71 */     String defaultString = String.format("%.2fkg", new Object[] { Float.valueOf(weight) });
/*  72 */     boolean positivelyBetter = false;
/*     */     
/*  74 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(percent, modifierPercent, Float.valueOf(modified), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  75 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  81 */     return 1;
/*     */   }
/*     */   
/*     */   public static class WeightJsonProperty extends JsonProperty<Modifier> {
/*     */     public WeightJsonProperty(Modifier value) {
/*  86 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  91 */       Modifier value = (Modifier)getValue();
/*  92 */       float adsAddendTime = 0.0F;
/*  93 */       if (value != null) {
/*     */         
/*  95 */         double eval = AttachmentPropertyManager.eval(value, 0.2D);
/*  96 */         adsAddendTime = (float)(eval - 0.2D);
/*     */       } 
/*     */       
/*  99 */       if (adsAddendTime > 0.0F) {
/* 100 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.weight.increase").m_130940_(ChatFormatting.RED));
/* 101 */       } else if (adsAddendTime < 0.0F) {
/* 102 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.weight.decrease").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data {
/*     */     @Nullable
/*     */     @SerializedName("weight_modifier")
/*     */     private Modifier weightModifier;
/*     */     @SerializedName("weight")
/*     */     @Deprecated
/* 112 */     private float weightAddend = 0.0F;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getWeightModifier() {
/* 118 */       return this.weightModifier;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public float getWeightAddend() {
/* 123 */       return this.weightAddend;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\WeightModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */