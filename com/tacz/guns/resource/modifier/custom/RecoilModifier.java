/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.api.modifier.ParameterizedCachePair;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunRecoil;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunRecoilKeyFrame;
/*     */ import it.unimi.dsi.fastutil.Pair;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ 
/*     */ public class RecoilModifier
/*     */   implements IAttachmentModifier<Pair<Modifier, Modifier>, ParameterizedCachePair<Float, Float>>
/*     */ {
/*     */   public static final String ID = "recoil";
/*     */   
/*     */   public String getId() {
/*  32 */     return "recoil";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOptionalFields() {
/*  37 */     return "recoil_modifier";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonProperty<Pair<Modifier, Modifier>> readJson(String json) {
/*  43 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  44 */     NewRecoilData newRecoilData = data.newRecoilData;
/*  45 */     OldRecoilData oldRecoilData = data.oldRecoilData;
/*     */     
/*  47 */     if (newRecoilData == null && oldRecoilData != null) {
/*  48 */       Modifier pitch = new Modifier();
/*  49 */       Modifier yaw = new Modifier();
/*  50 */       pitch.setPercent(oldRecoilData.getPitch());
/*  51 */       yaw.setPercent(oldRecoilData.getYaw());
/*  52 */       return new RecoilJsonProperty(Pair.of(pitch, yaw));
/*     */     } 
/*  54 */     assert newRecoilData != null;
/*  55 */     return new RecoilJsonProperty(Pair.of(newRecoilData.getPitch(), newRecoilData.getYaw()));
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<ParameterizedCachePair<Float, Float>> initCache(ItemStack gunItem, GunData gunData) {
/*  60 */     GunRecoil recoil = gunData.getRecoil();
/*  61 */     if (recoil == null) {
/*  62 */       return new CacheValue(ParameterizedCachePair.of(Float.valueOf(0.0F), Float.valueOf(0.0F)));
/*     */     }
/*  64 */     float pitch = getMaxInGunRecoilKeyFrame(recoil.getPitch());
/*  65 */     float yaw = getMaxInGunRecoilKeyFrame(recoil.getYaw());
/*  66 */     return new CacheValue(ParameterizedCachePair.of(Float.valueOf(pitch), Float.valueOf(yaw)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Pair<Modifier, Modifier>> modifiedValues, CacheValue<ParameterizedCachePair<Float, Float>> cache) {
/*  71 */     List<Modifier> yaw = Lists.newArrayList();
/*  72 */     List<Modifier> pitch = Lists.newArrayList();
/*  73 */     for (Pair<Modifier, Modifier> modifiedValue : modifiedValues) {
/*  74 */       pitch.add((Modifier)modifiedValue.left());
/*  75 */       yaw.add((Modifier)modifiedValue.right());
/*     */     } 
/*  77 */     ParameterizedCachePair<Float, Float> newCache = ParameterizedCachePair.of(pitch, yaw, ((ParameterizedCachePair)cache.getValue()).left().getDefaultValue(), ((ParameterizedCachePair)cache
/*  78 */         .getValue()).right().getDefaultValue());
/*  79 */     cache.setValue(newCache);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  85 */     ParameterizedCachePair<Float, Float> propertyCache = (ParameterizedCachePair<Float, Float>)cacheProperty.getCache("recoil");
/*  86 */     GunRecoil recoil = gunData.getRecoil();
/*     */     
/*  88 */     double pitch = ((Float)propertyCache.left().getDefaultValue()).floatValue();
/*  89 */     double pitchModifier = propertyCache.left().eval(getMaxInGunRecoilKeyFrame(recoil.getPitch())) - pitch;
/*  90 */     double pitchPercent = Math.min(pitch / 5.0D, 1.0D);
/*  91 */     double pitchModifierPercent = Math.min(pitchModifier / 5.0D, 1.0D);
/*  92 */     String pitchTitleKey = "gui.tacz.gun_refit.property_diagrams.pitch";
/*  93 */     String pitchPositivelyString = String.format("%.2f §c(+%.2f)", new Object[] { Double.valueOf(pitch), Double.valueOf(pitchModifier) });
/*  94 */     String pitchNegativelyString = String.format("%.2f §a(%.2f)", new Object[] { Double.valueOf(pitch), Double.valueOf(pitchModifier) });
/*  95 */     String pitchDefaultString = String.format("%.2f", new Object[] { Double.valueOf(pitch) });
/*     */     
/*  97 */     double yaw = ((Float)propertyCache.right().getDefaultValue()).floatValue();
/*  98 */     double yawModifier = propertyCache.right().eval(getMaxInGunRecoilKeyFrame(recoil.getYaw())) - yaw;
/*  99 */     double yawPercent = Math.min(yaw / 5.0D, 1.0D);
/* 100 */     double yawModifierPercent = Math.min(yawModifier / 5.0D, 1.0D);
/* 101 */     String yawTitleKey = "gui.tacz.gun_refit.property_diagrams.yaw";
/* 102 */     String yawPositivelyString = String.format("%.2f §c(+%.2f)", new Object[] { Double.valueOf(yaw), Double.valueOf(yawModifier) });
/* 103 */     String yawNegativelyString = String.format("%.2f §a(%.2f)", new Object[] { Double.valueOf(yaw), Double.valueOf(yawModifier) });
/* 104 */     String yawDefaultString = String.format("%.2f", new Object[] { Double.valueOf(yaw) });
/*     */     
/* 106 */     boolean positivelyBetter = false;
/*     */     
/* 108 */     IAttachmentModifier.DiagramsData pitchData = new IAttachmentModifier.DiagramsData(pitchPercent, pitchModifierPercent, Double.valueOf(pitchModifier), pitchTitleKey, pitchPositivelyString, pitchNegativelyString, pitchDefaultString, positivelyBetter);
/* 109 */     IAttachmentModifier.DiagramsData yawData = new IAttachmentModifier.DiagramsData(yawPercent, yawModifierPercent, Double.valueOf(yawModifier), yawTitleKey, yawPositivelyString, yawNegativelyString, yawDefaultString, positivelyBetter);
/* 110 */     return List.of(pitchData, yawData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/* 116 */     return 2;
/*     */   }
/*     */   
/*     */   private static float getMaxInGunRecoilKeyFrame(GunRecoilKeyFrame[] frames) {
/* 120 */     if (frames.length == 0) {
/* 121 */       return 0.0F;
/*     */     }
/* 123 */     float[] value = frames[0].getValue();
/* 124 */     float leftValue = Math.abs(value[0]);
/* 125 */     float rightValue = Math.abs(value[1]);
/* 126 */     return Math.max(leftValue, rightValue);
/*     */   }
/*     */   
/*     */   public static class RecoilJsonProperty extends JsonProperty<Pair<Modifier, Modifier>> {
/*     */     public RecoilJsonProperty(Pair<Modifier, Modifier> value) {
/* 131 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/* 136 */       Pair<Modifier, Modifier> modified = (Pair<Modifier, Modifier>)getValue();
/* 137 */       float pitch = 1.0F;
/* 138 */       float yaw = 1.0F;
/*     */       
/* 140 */       if (modified != null) {
/* 141 */         pitch = (float)AttachmentPropertyManager.eval((Modifier)modified.left(), 1.0D);
/* 142 */         yaw = (float)AttachmentPropertyManager.eval((Modifier)modified.right(), 1.0D);
/*     */       } 
/*     */       
/* 145 */       if (pitch > 1.0F) {
/* 146 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.pitch.increase").m_130940_(ChatFormatting.RED));
/* 147 */       } else if (pitch < 1.0F) {
/* 148 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.pitch.decrease").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/* 150 */       if (yaw > 1.0F) {
/* 151 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.yaw.increase").m_130940_(ChatFormatting.RED));
/* 152 */       } else if (yaw < 1.0F) {
/* 153 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.yaw.decrease").m_130940_(ChatFormatting.GREEN));
/*     */       } 
/*     */     } }
/*     */   public static class Data { @SerializedName("recoil_modifier")
/*     */     @Deprecated
/*     */     @Nullable
/* 159 */     private RecoilModifier.OldRecoilData oldRecoilData = null;
/*     */ 
/*     */     
/*     */     @SerializedName("recoil")
/*     */     @Nullable
/* 164 */     private RecoilModifier.NewRecoilData newRecoilData = null; }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private static class OldRecoilData
/*     */   {
/*     */     @SerializedName("pitch")
/* 171 */     private float pitch = 0.0F;
/*     */     
/*     */     @SerializedName("yaw")
/* 174 */     private float yaw = 0.0F;
/*     */ 
/*     */     
/*     */     public float getPitch() {
/* 178 */       return this.pitch;
/*     */     }
/*     */     
/*     */     public float getYaw() {
/* 182 */       return this.yaw;
/*     */     } }
/*     */   
/*     */   private static class NewRecoilData {
/*     */     @SerializedName("pitch")
/* 187 */     private Modifier pitch = new Modifier();
/*     */     
/*     */     @SerializedName("yaw")
/* 190 */     private Modifier yaw = new Modifier();
/*     */ 
/*     */     
/*     */     public Modifier getPitch() {
/* 194 */       return this.pitch;
/*     */     }
/*     */     
/*     */     public Modifier getYaw() {
/* 198 */       return this.yaw;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\RecoilModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */