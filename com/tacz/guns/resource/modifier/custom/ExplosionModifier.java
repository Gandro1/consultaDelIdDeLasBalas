/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.tacz.guns.api.modifier.CacheValue;
/*     */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.CommonGunPackLoader;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.ExplosionData;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class ExplosionModifier
/*     */   implements IAttachmentModifier<ExplosionModifier.ExplosionModifierValue, ExplosionData>
/*     */ {
/*     */   public static final String ID = "explosion";
/*     */   
/*     */   public String getId() {
/*  25 */     return "explosion";
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonProperty<ExplosionModifierValue> readJson(String json) {
/*  30 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/*  31 */     return new ExplosionJsonProperty(data.getExplosion());
/*     */   }
/*     */ 
/*     */   
/*     */   public CacheValue<ExplosionData> initCache(ItemStack gunItem, GunData gunData) {
/*  36 */     ExplosionData explosionData = gunData.getBulletData().getExplosionData();
/*  37 */     if (explosionData == null) {
/*  38 */       explosionData = new ExplosionData(false, 0.5F, 2.0F, false, 30, false);
/*     */     }
/*  40 */     return new CacheValue(explosionData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void eval(List<ExplosionModifierValue> modifiedValues, CacheValue<ExplosionData> cache) {
/*  45 */     ExplosionData cacheValue = (ExplosionData)cache.getValue();
/*     */     
/*  47 */     List<Boolean> explodeValues = Lists.newArrayList();
/*  48 */     explodeValues.add(Boolean.valueOf(cacheValue.isExplode()));
/*  49 */     List<Modifier> radiusValues = Lists.newArrayList();
/*  50 */     List<Modifier> damageValues = Lists.newArrayList();
/*  51 */     List<Boolean> knockbackValues = Lists.newArrayList();
/*  52 */     knockbackValues.add(Boolean.valueOf(cacheValue.isKnockback()));
/*  53 */     List<Boolean> destroyBlockValues = Lists.newArrayList();
/*  54 */     destroyBlockValues.add(Boolean.valueOf(cacheValue.isDestroyBlock()));
/*  55 */     List<Modifier> delayValues = Lists.newArrayList();
/*     */     
/*  57 */     modifiedValues.forEach(v -> {
/*     */           explodeValues.add(Boolean.valueOf(v.explode));
/*     */           
/*     */           radiusValues.add(v.radius);
/*     */           damageValues.add(v.damage);
/*     */           knockbackValues.add(Boolean.valueOf(v.knockback));
/*     */           destroyBlockValues.add(Boolean.valueOf(v.destroyBlock));
/*     */           delayValues.add(v.delay);
/*     */         });
/*  66 */     boolean explode = (cacheValue.isExplode() || AttachmentPropertyManager.eval(explodeValues, false));
/*     */     
/*  68 */     if (!explode) {
/*     */       return;
/*     */     }
/*  71 */     float radius = (float)AttachmentPropertyManager.eval(radiusValues, cacheValue.getRadius());
/*  72 */     float damage = (float)AttachmentPropertyManager.eval(damageValues, cacheValue.getDamage());
/*  73 */     boolean knockback = AttachmentPropertyManager.eval(knockbackValues, false);
/*  74 */     boolean destroyBlock = AttachmentPropertyManager.eval(destroyBlockValues, false);
/*  75 */     int delay = (int)AttachmentPropertyManager.eval(delayValues, cacheValue.getDelay());
/*  76 */     ExplosionData explosionData = new ExplosionData(true, radius, damage, knockback, delay, destroyBlock);
/*  77 */     cache.setValue(explosionData);
/*     */   }
/*     */   
/*     */   public static class ExplosionJsonProperty extends JsonProperty<ExplosionModifierValue> {
/*     */     public ExplosionJsonProperty(ExplosionModifier.ExplosionModifierValue value) {
/*  82 */       super(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void initComponents() {
/*  87 */       ExplosionModifier.ExplosionModifierValue modifierValue = (ExplosionModifier.ExplosionModifierValue)getValue();
/*  88 */       if (modifierValue != null && modifierValue.explode)
/*  89 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.explosion").m_130940_(ChatFormatting.GOLD)); 
/*     */     } }
/*     */   
/*     */   private static class Data {
/*     */     @Nullable
/*     */     @SerializedName("explosion")
/*  95 */     private ExplosionModifier.ExplosionModifierValue explosion = null;
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public ExplosionModifier.ExplosionModifierValue getExplosion() {
/* 101 */       return this.explosion;
/*     */     }
/*     */   }
/*     */   public static class ExplosionModifierValue { @SerializedName("explode")
/*     */     private boolean explode; @SerializedName("radius")
/*     */     private Modifier radius; @SerializedName("damage")
/*     */     private Modifier damage;
/*     */     
/* 109 */     public ExplosionModifierValue() { this.explode = false;
/*     */ 
/*     */       
/* 112 */       this.radius = new Modifier();
/*     */ 
/*     */       
/* 115 */       this.damage = new Modifier();
/*     */ 
/*     */       
/* 118 */       this.knockback = false;
/*     */ 
/*     */       
/* 121 */       this.destroyBlock = false;
/*     */ 
/*     */       
/* 124 */       this.delay = new Modifier(); } @SerializedName("knockback")
/*     */     private boolean knockback; @SerializedName("destroy_block")
/*     */     private boolean destroyBlock; @SerializedName("delay")
/*     */     private Modifier delay; public boolean isExplode() {
/* 128 */       return this.explode;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\ExplosionModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */