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
/*     */ public class AdsModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "ads";
/*     */   
/*     */   public String getId() {
/*  27 */     return "ads";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOptionalFields() {
/*  32 */     return "ads_addend";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  39 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  40 */     Modifier ads = data.getAds();
/*     */     
/*  42 */     if (ads == null) {
/*  43 */       ads = new Modifier();
/*  44 */       ads.setAddend(data.getAdsAddendTime());
/*     */     } 
/*  46 */     return new AdsJsonProperty(ads);
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*  51 */     return new CacheValue(Float.valueOf(gunData.getAimTime()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  56 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  57 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  63 */     float aimTime = gunData.getAimTime();
/*  64 */     float adsTimeModifier = ((Float)cacheProperty.getCache("ads")).floatValue() - aimTime;
/*     */     
/*  66 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.ads";
/*  67 */     String positivelyString = String.format("%.2fs §c(+%.2f)", new Object[] { Float.valueOf(aimTime), Float.valueOf(adsTimeModifier) });
/*  68 */     String negativelyString = String.format("%.2fs §a(%.2f)", new Object[] { Float.valueOf(aimTime), Float.valueOf(adsTimeModifier) });
/*  69 */     String defaultString = String.format("%.2fs", new Object[] { Float.valueOf(aimTime) });
/*  70 */     boolean positivelyBetter = false;
/*     */     
/*  72 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(aimTime, adsTimeModifier, Float.valueOf(adsTimeModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  73 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  79 */     return 1;
/*     */   }
/*     */   
/*     */   public static class AdsJsonProperty extends JsonProperty<Modifier> {
/*     */     public AdsJsonProperty(Modifier value) {
/*  84 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  89 */       Modifier value = (Modifier)getValue();
/*  90 */       float adsAddendTime = 0.0F;
/*  91 */       if (value != null) {
/*     */         
/*  93 */         double eval = AttachmentPropertyManager.eval(value, 0.2D);
/*  94 */         adsAddendTime = (float)(eval - 0.2D);
/*     */       } 
/*     */       
/*  97 */       if (adsAddendTime > 0.0F) {
/*  98 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ads.increase").m_130940_(ChatFormatting.RED));
/*  99 */       } else if (adsAddendTime < 0.0F) {
/* 100 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ads.decrease").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data {
/*     */     @Nullable
/*     */     @SerializedName("ads")
/*     */     private Modifier ads;
/*     */     @SerializedName("ads_addend")
/*     */     @Deprecated
/* 110 */     private float adsAddendTime = 0.0F;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getAds() {
/* 116 */       return this.ads;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public float getAdsAddendTime() {
/* 121 */       return this.adsAddendTime;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AdsModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */