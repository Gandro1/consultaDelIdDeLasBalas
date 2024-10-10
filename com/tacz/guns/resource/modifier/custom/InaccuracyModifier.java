/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
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
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunFireModeAdjustData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.InaccuracyType;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class InaccuracyModifier
/*     */   implements IAttachmentModifier<Map<InaccuracyType, Modifier>, Map<InaccuracyType, Float>>
/*     */ {
/*     */   public static final String ID = "inaccuracy";
/*     */   
/*     */   public String getId() {
/*  35 */     return "inaccuracy";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOptionalFields() {
/*  40 */     return "inaccuracy_addend";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonProperty<Map<InaccuracyType, Modifier>> readJson(String json) {
/*  46 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  47 */     Modifier inaccuracy = data.getInaccuracy();
/*     */     
/*  49 */     if (inaccuracy == null) {
/*  50 */       float inaccuracyAddendTime = data.getInaccuracyAddendTime();
/*  51 */       inaccuracy = new Modifier();
/*  52 */       inaccuracy.setAddend(inaccuracyAddendTime);
/*     */     } 
/*     */     
/*  55 */     Map<InaccuracyType, Modifier> jsonProperties = Maps.newHashMap();
/*  56 */     for (InaccuracyType type : InaccuracyType.values()) {
/*  57 */       if (!type.isAim())
/*     */       {
/*     */         
/*  60 */         jsonProperties.put(type, inaccuracy); } 
/*     */     } 
/*  62 */     return new InaccuracyJsonProperty(jsonProperties);
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Map<InaccuracyType, Float>> initCache(ItemStack gunItem, GunData gunData) {
/*  67 */     Map<InaccuracyType, Float> tmp = Maps.newHashMap();
/*  68 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  69 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  70 */     gunData.getInaccuracy().forEach((type, value) -> {
/*     */           float inaccuracyAddend = 0.0F;
/*     */           GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */           if (fireModeAdjustData != null) {
/*     */             if (type == InaccuracyType.AIM) {
/*     */               inaccuracyAddend = fireModeAdjustData.getAimInaccuracy();
/*     */             } else {
/*     */               inaccuracyAddend = fireModeAdjustData.getOtherInaccuracy();
/*     */             } 
/*     */           }
/*     */           float inaccuracy = gunData.getInaccuracy(type, inaccuracyAddend);
/*     */           tmp.put(type, Float.valueOf(inaccuracy));
/*     */         });
/*  83 */     return new CacheValue(tmp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Map<InaccuracyType, Modifier>> modifiedValues, CacheValue<Map<InaccuracyType, Float>> cache) {
/*  88 */     Map<InaccuracyType, Float> result = Maps.newHashMap();
/*  89 */     Map<InaccuracyType, List<Modifier>> tmpModified = Maps.newHashMap();
/*     */     
/*  91 */     for (InaccuracyType type : InaccuracyType.values()) {
/*  92 */       if (!type.isAim())
/*     */       {
/*     */         
/*  95 */         for (Map<InaccuracyType, Modifier> value : modifiedValues) {
/*  96 */           ((List<Modifier>)tmpModified.computeIfAbsent(type, t -> Lists.newArrayList())).add(value.get(type));
/*     */         }
/*     */       }
/*     */     } 
/* 100 */     ((Map)cache.getValue()).forEach((type, value) -> {
/*     */           if (type.isAim()) {
/*     */             result.put(type, value);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           double eval = AttachmentPropertyManager.eval((List)tmpModified.get(type), ((Float)((Map)cache.getValue()).get(type)).floatValue());
/*     */           result.put(type, Float.valueOf((float)eval));
/*     */         });
/* 110 */     cache.setValue(result);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/* 116 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/* 117 */     FireMode fireMode = iGun.getFireMode(gunItem);
/* 118 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */     
/* 120 */     float standInaccuracy = gunData.getInaccuracy(InaccuracyType.STAND);
/* 121 */     if (fireModeAdjustData != null) {
/* 122 */       standInaccuracy += fireModeAdjustData.getOtherInaccuracy();
/*     */     }
/*     */     
/* 125 */     float inaccuracyModifier = ((Float)((Map)cacheProperty.getCache("inaccuracy")).get(InaccuracyType.STAND)).floatValue() - standInaccuracy;
/* 126 */     double standInaccuracyPercent = Math.min(standInaccuracy / 10.0D, 1.0D);
/* 127 */     double inaccuracyModifierPercent = Math.min(inaccuracyModifier / 10.0D, 1.0D);
/*     */     
/* 129 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.hipfire_inaccuracy";
/* 130 */     String positivelyString = String.format("%.2f §c(+%.2f)", new Object[] { Float.valueOf(standInaccuracy), Float.valueOf(inaccuracyModifier) });
/* 131 */     String negativelyString = String.format("%.2f §a(%.2f)", new Object[] { Float.valueOf(standInaccuracy), Float.valueOf(inaccuracyModifier) });
/* 132 */     String defaultString = String.format("%.2f", new Object[] { Float.valueOf(standInaccuracy) });
/* 133 */     boolean positivelyBetter = false;
/*     */     
/* 135 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(standInaccuracyPercent, inaccuracyModifierPercent, Float.valueOf(inaccuracyModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/* 136 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/* 142 */     return 1;
/*     */   }
/*     */   
/*     */   public static class InaccuracyJsonProperty extends JsonProperty<Map<InaccuracyType, Modifier>> {
/*     */     public InaccuracyJsonProperty(Map<InaccuracyType, Modifier> value) {
/* 147 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 152 */       Map<InaccuracyType, Modifier> value = (Map<InaccuracyType, Modifier>)getValue();
/* 153 */       float inaccuracyAddend = 0.0F;
/* 154 */       if (value != null && value.containsKey(InaccuracyType.STAND)) {
/*     */         
/* 156 */         double eval = AttachmentPropertyManager.eval(value.get(InaccuracyType.STAND), 5.0D);
/* 157 */         inaccuracyAddend = (float)(eval - 5.0D);
/*     */       } 
/*     */       
/* 160 */       if (inaccuracyAddend > 0.0F) {
/* 161 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.inaccuracy.decrease").m_130940_(ChatFormatting.RED));
/* 162 */       } else if (inaccuracyAddend < 0.0F) {
/* 163 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.inaccuracy.increase").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data {
/*     */     @Nullable
/*     */     @SerializedName("inaccuracy")
/*     */     private Modifier inaccuracy;
/*     */     @SerializedName("inaccuracy_addend")
/*     */     @Deprecated
/* 173 */     private float adsAddendTime = 0.0F;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getInaccuracy() {
/* 179 */       return this.inaccuracy;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public float getInaccuracyAddendTime() {
/* 184 */       return this.adsAddendTime;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\InaccuracyModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */