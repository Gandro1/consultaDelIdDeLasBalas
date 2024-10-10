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
/*     */ public class AimInaccuracyModifier
/*     */   implements IAttachmentModifier<Map<InaccuracyType, Modifier>, Map<InaccuracyType, Float>>
/*     */ {
/*     */   public static final String ID = "aim_inaccuracy";
/*     */   
/*     */   public String getId() {
/*  35 */     return "aim_inaccuracy";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Map<InaccuracyType, Modifier>> readJson(String json) {
/*  40 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  41 */     Modifier inaccuracy = data.getAimInaccuracy();
/*     */     
/*  43 */     Map<InaccuracyType, Modifier> jsonProperties = Maps.newHashMap();
/*  44 */     for (InaccuracyType type : InaccuracyType.values()) {
/*  45 */       if (type.isAim())
/*     */       {
/*     */         
/*  48 */         jsonProperties.put(type, inaccuracy); } 
/*     */     } 
/*  50 */     return new AimInaccuracyJsonProperty(jsonProperties);
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Map<InaccuracyType, Float>> initCache(ItemStack gunItem, GunData gunData) {
/*  55 */     Map<InaccuracyType, Float> tmp = Maps.newHashMap();
/*  56 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  57 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  58 */     gunData.getInaccuracy().forEach((type, value) -> {
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
/*  71 */     return new CacheValue(tmp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Map<InaccuracyType, Modifier>> modifiedValues, CacheValue<Map<InaccuracyType, Float>> cache) {
/*  76 */     Map<InaccuracyType, Float> result = Maps.newHashMap();
/*  77 */     Map<InaccuracyType, List<Modifier>> tmpModified = Maps.newHashMap();
/*     */     
/*  79 */     for (InaccuracyType type : InaccuracyType.values()) {
/*  80 */       if (type.isAim())
/*     */       {
/*     */         
/*  83 */         for (Map<InaccuracyType, Modifier> value : modifiedValues) {
/*  84 */           ((List<Modifier>)tmpModified.computeIfAbsent(type, t -> Lists.newArrayList())).add(value.get(type));
/*     */         }
/*     */       }
/*     */     } 
/*  88 */     ((Map)cache.getValue()).forEach((type, value) -> {
/*     */           if (!type.isAim()) {
/*     */             result.put(type, value);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           double eval = AttachmentPropertyManager.eval((List)tmpModified.get(type), ((Float)((Map)cache.getValue()).get(type)).floatValue());
/*     */           result.put(type, Float.valueOf((float)eval));
/*     */         });
/*  98 */     cache.setValue(result);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/* 104 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/* 105 */     FireMode fireMode = iGun.getFireMode(gunItem);
/* 106 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*     */     
/* 108 */     float aimInaccuracy = gunData.getInaccuracy(InaccuracyType.AIM);
/* 109 */     if (fireModeAdjustData != null) {
/* 110 */       aimInaccuracy += fireModeAdjustData.getAimInaccuracy();
/*     */     }
/*     */     
/* 113 */     aimInaccuracy = (1.0F - aimInaccuracy) * 100.0F;
/* 114 */     float inaccuracyModifier = ((Float)((Map)cacheProperty.getCache("aim_inaccuracy")).get(InaccuracyType.AIM)).floatValue();
/* 115 */     inaccuracyModifier = (1.0F - inaccuracyModifier) * 100.0F - aimInaccuracy;
/* 116 */     double aimInaccuracyPercent = Math.min(aimInaccuracy / 100.0D, 1.0D);
/* 117 */     double inaccuracyModifierPercent = Math.min(inaccuracyModifier / 100.0D, 1.0D);
/*     */ 
/*     */     
/* 120 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.aim_inaccuracy";
/* 121 */     String positivelyString = String.format("%.1f%% §a(+%.1f%%)", new Object[] { Float.valueOf(aimInaccuracy), Float.valueOf(inaccuracyModifier) });
/* 122 */     String negativelyString = String.format("%.1f%% §c(%.1f%%)", new Object[] { Float.valueOf(aimInaccuracy), Float.valueOf(inaccuracyModifier) });
/* 123 */     String defaultString = String.format("%.1f%%", new Object[] { Float.valueOf(aimInaccuracy) });
/* 124 */     boolean positivelyBetter = true;
/*     */     
/* 126 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(aimInaccuracyPercent, inaccuracyModifierPercent, Float.valueOf(inaccuracyModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/* 127 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/* 133 */     return 1;
/*     */   }
/*     */   
/*     */   public static class AimInaccuracyJsonProperty extends JsonProperty<Map<InaccuracyType, Modifier>> {
/*     */     public AimInaccuracyJsonProperty(Map<InaccuracyType, Modifier> value) {
/* 138 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 143 */       Map<InaccuracyType, Modifier> value = (Map<InaccuracyType, Modifier>)getValue();
/* 144 */       float inaccuracyAddend = 0.0F;
/* 145 */       if (value != null && value.containsKey(InaccuracyType.AIM)) {
/*     */         
/* 147 */         double eval = AttachmentPropertyManager.eval(value.get(InaccuracyType.AIM), 0.15000000596046448D);
/* 148 */         inaccuracyAddend = (float)(eval - 0.15000000596046448D);
/*     */       } 
/*     */       
/* 151 */       if (inaccuracyAddend > 0.0F) {
/* 152 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.aim_inaccuracy.decrease").m_130940_(ChatFormatting.RED));
/* 153 */       } else if (inaccuracyAddend < 0.0F) {
/* 154 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.aim_inaccuracy.increase").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Data {
/*     */     @Nullable
/*     */     @SerializedName("aim_inaccuracy")
/*     */     private Modifier aimInaccuracy;
/*     */     
/*     */     @Nullable
/*     */     public Modifier getAimInaccuracy() {
/* 166 */       return this.aimInaccuracy;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AimInaccuracyModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */