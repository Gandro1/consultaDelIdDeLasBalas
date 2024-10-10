/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.config.common.GunConfig;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import it.unimi.dsi.fastutil.Pair;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class SilenceModifier
/*     */   implements IAttachmentModifier<Pair<Modifier, Boolean>, Pair<Integer, Boolean>>
/*     */ {
/*     */   public static final String ID = "silence";
/*     */   
/*     */   public String getId() {
/*  26 */     return "silence";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SilenceJsonProperty readJson(String json) {
/*  32 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  33 */     Silence silence = data.getSilence();
/*  34 */     if (silence == null) {
/*  35 */       return new SilenceJsonProperty(Pair.of(new Modifier(), Boolean.valueOf(false)));
/*     */     }
/*  37 */     Modifier distance = silence.getDistance();
/*     */     
/*  39 */     if (distance == null) {
/*  40 */       distance = new Modifier();
/*  41 */       distance.setAddend(silence.getDistanceAddend());
/*     */     } 
/*  43 */     return new SilenceJsonProperty(Pair.of(distance, Boolean.valueOf(silence.isUseSilenceSound())));
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Pair<Integer, Boolean>> initCache(ItemStack gunItem, GunData gunData) {
/*  48 */     int defaultDistance = ((Integer)GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE.get()).intValue();
/*  49 */     return new CacheValue(Pair.of(Integer.valueOf(defaultDistance), Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Pair<Modifier, Boolean>> modifiedValues, CacheValue<Pair<Integer, Boolean>> cache) {
/*  54 */     List<Modifier> distanceModifiers = Lists.newArrayList();
/*  55 */     List<Boolean> useSilenceSoundModifiers = Lists.newArrayList();
/*  56 */     modifiedValues.forEach(v -> {
/*     */           distanceModifiers.add((Modifier)v.left());
/*     */           useSilenceSoundModifiers.add((Boolean)v.right());
/*     */         });
/*  60 */     Pair<Integer, Boolean> cacheValue = (Pair<Integer, Boolean>)cache.getValue();
/*  61 */     double evalDistance = AttachmentPropertyManager.eval(distanceModifiers, ((Integer)cacheValue.left()).intValue());
/*  62 */     boolean useSilenceSound = AttachmentPropertyManager.eval(useSilenceSoundModifiers, ((Boolean)cacheValue.right()).booleanValue());
/*  63 */     cache.setValue(Pair.of(Integer.valueOf((int)Math.round(evalDistance)), Boolean.valueOf(useSilenceSound)));
/*     */   }
/*     */   
/*     */   public static class SilenceJsonProperty extends JsonProperty<Pair<Modifier, Boolean>> {
/*     */     public SilenceJsonProperty(Pair<Modifier, Boolean> value) {
/*  68 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  73 */       Pair<Modifier, Boolean> value = (Pair<Modifier, Boolean>)getValue();
/*  74 */       if (value != null) {
/*  75 */         int defaultDistance = ((Integer)GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE.get()).intValue();
/*  76 */         double eval = AttachmentPropertyManager.eval((Modifier)value.left(), defaultDistance);
/*  77 */         int distance = (int)Math.round(eval);
/*  78 */         if (distance > defaultDistance) {
/*  79 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.sound_distance.increase").m_130940_(ChatFormatting.RED));
/*  80 */         } else if (distance < defaultDistance) {
/*  81 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.sound_distance.increase").m_130940_(ChatFormatting.GREEN));
/*     */         } 
/*  83 */         if (((Boolean)value.right()).booleanValue())
/*  84 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.silence").m_130940_(ChatFormatting.GREEN)); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Data { @Nullable
/*     */     @SerializedName("silence")
/*  91 */     private SilenceModifier.Silence silence = null;
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public SilenceModifier.Silence getSilence()
/*     */     {
/*  97 */       return this.silence;
/*     */     } }
/*     */   private static class Silence { @Deprecated
/*     */     @SerializedName("distance_addend")
/*     */     private int distanceAddend;
/* 102 */     private Silence() { this.distanceAddend = 0;
/*     */ 
/*     */ 
/*     */       
/* 106 */       this.distance = null;
/*     */ 
/*     */ 
/*     */       
/* 110 */       this.useSilenceSound = false; }
/*     */      @Nullable
/*     */     @SerializedName("distance")
/*     */     private Modifier distance; @Deprecated
/*     */     public int getDistanceAddend() {
/* 115 */       return this.distanceAddend;
/*     */     } @SerializedName("use_silence_sound")
/*     */     private boolean useSilenceSound;
/*     */     @Nullable
/*     */     public Modifier getDistance() {
/* 120 */       return this.distance;
/*     */     }
/*     */     
/*     */     public boolean isUseSilenceSound() {
/* 124 */       return this.useSilenceSound;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\SilenceModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */