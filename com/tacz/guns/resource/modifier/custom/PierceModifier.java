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
/*     */ public class PierceModifier
/*     */   implements IAttachmentModifier<Modifier, Integer>
/*     */ {
/*     */   public static final String ID = "pierce";
/*     */   
/*     */   public String getId() {
/*  27 */     return "pierce";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<Modifier> readJson(String json) {
/*  32 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  33 */     return new DamageJsonProperty(data.getPierce());
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<Integer> initCache(ItemStack gunItem, GunData gunData) {
/*  38 */     int pierce = gunData.getBulletData().getPierce();
/*  39 */     return new CacheValue(Integer.valueOf(pierce));
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<Modifier> modifiers, CacheValue<Integer> cache) {
/*  44 */     double eval = AttachmentPropertyManager.eval(modifiers, ((Integer)cache.getValue()).intValue());
/*  45 */     cache.setValue(Integer.valueOf((int)Math.round(eval)));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public List<IAttachmentModifier.DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/*  51 */     int pierce = gunData.getBulletData().getPierce();
/*  52 */     int pierceModifier = ((Integer)cacheProperty.getCache("pierce")).intValue() - pierce;
/*     */     
/*  54 */     double piercePercent = Math.min(pierce / 10.0D, 1.0D);
/*  55 */     double pierceModifierPercent = Math.min(pierceModifier / 10.0D, 1.0D);
/*     */     
/*  57 */     String titleKey = "gui.tacz.gun_refit.property_diagrams.pierce";
/*  58 */     String positivelyString = String.format("%d §a(+%d)", new Object[] { Integer.valueOf(pierce), Integer.valueOf(pierceModifier) });
/*  59 */     String negativelyString = String.format("%d §c(%d)", new Object[] { Integer.valueOf(pierce), Integer.valueOf(pierceModifier) });
/*  60 */     String defaultString = String.format("%d", new Object[] { Integer.valueOf(pierce) });
/*  61 */     boolean positivelyBetter = true;
/*     */     
/*  63 */     IAttachmentModifier.DiagramsData diagramsData = new IAttachmentModifier.DiagramsData(piercePercent, pierceModifierPercent, Integer.valueOf(pierceModifier), titleKey, positivelyString, negativelyString, defaultString, positivelyBetter);
/*  64 */     return Collections.singletonList(diagramsData);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public int getDiagramsDataSize() {
/*  70 */     return 1;
/*     */   }
/*     */   
/*     */   public static class DamageJsonProperty extends JsonProperty<Modifier> {
/*     */     public DamageJsonProperty(Modifier value) {
/*  75 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  80 */       Modifier pierce = (Modifier)getValue();
/*  81 */       if (pierce != null) {
/*  82 */         long eval = Math.round(AttachmentPropertyManager.eval(pierce, 5.0D));
/*  83 */         eval = Math.max(eval, 1L);
/*  84 */         if (eval > 5L) {
/*  85 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.pierce.increase").m_130940_(ChatFormatting.GREEN));
/*  86 */         } else if (eval < 5L) {
/*  87 */           this.components.add(Component.m_237115_("tooltip.tacz.attachment.pierce.decrease").m_130940_(ChatFormatting.RED));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static class Data { @SerializedName("pierce")
/*     */     @Nullable
/*  94 */     private Modifier pierce = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Modifier getPierce() {
/* 100 */       return this.pierce;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\PierceModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */