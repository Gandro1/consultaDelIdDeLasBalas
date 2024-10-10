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
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunFireModeAdjustData;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class AmmoSpeedModifier
/*     */   implements IAttachmentModifier<Modifier, Float>
/*     */ {
/*     */   public static final String ID = "ammo_speed";
/*     */   
/*     */   public String getId() {
/*  31 */     return "ammo_speed";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  36 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  37 */     return new BulletSpeedJsonProperty(data.getAmmoSpeed());
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Float> initCache(ItemStack gunItem, GunData gunData) {
/*  42 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  43 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  44 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*  45 */     float speed = gunData.getBulletData().getSpeed();
/*  46 */     if (fireModeAdjustData != null) {
/*  47 */       speed += fireModeAdjustData.getSpeed();
/*     */     }
/*  49 */     return new CacheValue(Float.valueOf(speed));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Float> cache) {
/*  54 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Float)cache.getValue()).floatValue());
/*  55 */     cache.setValue(Float.valueOf((float)eval));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  61 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  62 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  63 */     GunFireModeAdjustData fireModeAdjustData = gunData.getFireModeAdjustData(fireMode);
/*  64 */     float ammoSpeed = gunData.getBulletData().getSpeed();
/*  65 */     if (fireModeAdjustData != null) {
/*  66 */       ammoSpeed += fireModeAdjustData.getSpeed();
/*     */     }
/*  68 */     float ammoSpeedModifier = ((Float)cacheProperty.getCache("ammo_speed")).floatValue() - ammoSpeed;
/*     */     
/*  70 */     double ammoSpeedPercent = Math.min(ammoSpeed / 600.0D, 1.0D);
/*  71 */     double ammoSpeedModifierPercent = Math.min(ammoSpeedModifier / 600.0D, 1.0D);
/*     */     
/*  73 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.ammo_speed";
/*  74 */     String positivelyString = String.format("%dm/s §a(+%d)", new Object[] { Integer.valueOf(Math.round(ammoSpeed)), Integer.valueOf(Math.round(ammoSpeedModifier)) });
/*  75 */     String negativelyString = String.format("%dm/s §c(%d)", new Object[] { Integer.valueOf(Math.round(ammoSpeed)), Integer.valueOf(Math.round(ammoSpeedModifier)) });
/*  76 */     String defaultString = String.format("%dm/s", new Object[] { Integer.valueOf(Math.round(ammoSpeed)) });
/*  77 */     boolean positivelyBetter = true;
/*     */     
/*  79 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(ammoSpeedPercent, ammoSpeedModifierPercent, Float.valueOf(ammoSpeedModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  80 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  86 */     return 1;
/*     */   }
/*     */   
/*     */   public static class BulletSpeedJsonProperty extends JsonProperty<Modifier> {
/*     */     public BulletSpeedJsonProperty(Modifier value) {
/*  91 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  96 */       Modifier ammoSpeed = (Modifier)getValue();
/*  97 */       if (ammoSpeed != null) {
/*  98 */         double eval = AttachmentPropertyManager.eval(ammoSpeed, 300.0D);
/*  99 */         if (eval > 300.0D) {
/* 100 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.ammo_speed.increase").m_130940_(ChatFormatting.GREEN));
/* 101 */         } else if (eval < 300.0D) {
/* 102 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.ammo_speed.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("ammo_speed")
/*     */     @Nullable
/* 109 */     private Modifier ammoSpeed = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getAmmoSpeed() {
/* 115 */       return this.ammoSpeed;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AmmoSpeedModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */