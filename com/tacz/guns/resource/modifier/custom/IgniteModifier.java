/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.modifier.CacheValue;
/*    */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Ignite;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class IgniteModifier
/*    */   implements IAttachmentModifier<Ignite, Ignite>
/*    */ {
/*    */   public static final String ID = "ignite";
/*    */   
/*    */   public String getId() {
/* 24 */     return "ignite";
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonProperty<Ignite> readJson(String json) {
/* 29 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/* 30 */     return new IgniteJsonProperty(data.getIgnite());
/*    */   }
/*    */ 
/*    */   
/*    */   public CacheValue<Ignite> initCache(ItemStack gunItem, GunData gunData) {
/* 35 */     Ignite ignite = gunData.getBulletData().getIgnite();
/* 36 */     return new CacheValue(ignite);
/*    */   }
/*    */ 
/*    */   
/*    */   public void eval(List<Ignite> modifiedValues, CacheValue<Ignite> cache) {
/* 41 */     Ignite cacheValue = (Ignite)cache.getValue();
/* 42 */     List<Boolean> igniteEntityValues = Lists.newArrayList();
/* 43 */     igniteEntityValues.add(Boolean.valueOf(cacheValue.isIgniteEntity()));
/* 44 */     List<Boolean> igniteBlockValues = Lists.newArrayList();
/* 45 */     igniteBlockValues.add(Boolean.valueOf(cacheValue.isIgniteBlock()));
/* 46 */     modifiedValues.forEach(v -> {
/*    */           igniteEntityValues.add(Boolean.valueOf(v.isIgniteEntity()));
/*    */           igniteBlockValues.add(Boolean.valueOf(v.isIgniteBlock()));
/*    */         });
/* 50 */     boolean igniteEntity = AttachmentPropertyManager.eval(igniteEntityValues, false);
/* 51 */     boolean igniteBlock = AttachmentPropertyManager.eval(igniteBlockValues, false);
/* 52 */     cache.setValue(new Ignite(igniteEntity, igniteBlock));
/*    */   }
/*    */   
/*    */   public static class IgniteJsonProperty extends JsonProperty<Ignite> {
/*    */     public IgniteJsonProperty(Ignite value) {
/* 57 */       super(value);
/*    */     }
/*    */ 
/*    */     
/*    */     public void initComponents() {
/* 62 */       Ignite value = (Ignite)getValue();
/* 63 */       if (value == null) {
/*    */         return;
/*    */       }
/* 66 */       if (value.isIgniteEntity()) {
/* 67 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ignite.entity").m_130940_(ChatFormatting.GREEN));
/*    */       }
/* 69 */       if (value.isIgniteBlock())
/* 70 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ignite.block").m_130940_(ChatFormatting.GREEN)); 
/*    */     }
/*    */   }
/*    */   
/*    */   private static class Data {
/*    */     @SerializedName("ignite")
/* 76 */     private Ignite ignite = new Ignite(false);
/*    */ 
/*    */     
/*    */     @Nullable
/*    */     public Ignite getIgnite() {
/* 81 */       return this.ignite;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\IgniteModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */