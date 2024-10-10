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
/*     */ public class RpmModifier
/*     */   implements IAttachmentModifier<Modifier, Integer>
/*     */ {
/*     */   public static final String ID = "rpm";
/*     */   
/*     */   public String getId() {
/*  30 */     return "rpm";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  35 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  36 */     return new RpmJsonProperty(data.getRpm());
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Integer> initCache(ItemStack gunItem, GunData gunData) {
/*  41 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  42 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*  43 */     int roundsPerMinute = gunData.getRoundsPerMinute(fireMode);
/*  44 */     return new CacheValue(Integer.valueOf(roundsPerMinute));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Integer> cache) {
/*  49 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Integer)cache.getValue()).intValue());
/*  50 */     cache.setValue(Integer.valueOf((int)Math.round(eval)));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  56 */     IGun iGun = Objects.<IGun>requireNonNull(IGun.getIGunOrNull(gunItem));
/*  57 */     FireMode fireMode = iGun.getFireMode(gunItem);
/*     */     
/*  59 */     int rpm = gunData.getRoundsPerMinute(fireMode);
/*  60 */     int rpmModifier = ((Integer)cacheProperty.getCache("rpm")).intValue() - rpm;
/*     */     
/*  62 */     double rpmPercent = Math.min(rpm / 1200.0D, 1.0D);
/*  63 */     double rpmModifierPercent = Math.min(rpmModifier / 1200.0D, 1.0D);
/*     */     
/*  65 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.rpm";
/*  66 */     String positivelyString = String.format("%drpm §a(+%d)", new Object[] { Integer.valueOf(rpm), Integer.valueOf(rpmModifier) });
/*  67 */     String negativelyString = String.format("%drpm §c(%d)", new Object[] { Integer.valueOf(rpm), Integer.valueOf(rpmModifier) });
/*  68 */     String defaultString = String.format("%drpm", new Object[] { Integer.valueOf(rpm) });
/*  69 */     boolean positivelyBetter = true;
/*     */     
/*  71 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(rpmPercent, rpmModifierPercent, Integer.valueOf(rpmModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  72 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  78 */     return 1;
/*     */   }
/*     */   
/*     */   public static class RpmJsonProperty extends JsonProperty<Modifier> {
/*     */     public RpmJsonProperty(Modifier value) {
/*  83 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  88 */       Modifier value = (Modifier)getValue();
/*  89 */       if (value != null) {
/*  90 */         double eval = AttachmentPropertyManager.eval(value, 300.0D);
/*  91 */         int rpm = (int)Math.round(eval);
/*  92 */         if (rpm > 300) {
/*  93 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.rpm.increase").m_130940_(ChatFormatting.GREEN));
/*  94 */         } else if (rpm < 300) {
/*  95 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.rpm.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("rpm")
/*     */     @Nullable
/* 102 */     private Modifier rpm = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getRpm() {
/* 108 */       return this.rpm;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\RpmModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */