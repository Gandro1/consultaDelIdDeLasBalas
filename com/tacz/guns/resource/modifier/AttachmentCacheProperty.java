/*    */ package com.tacz.guns.resource.modifier;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.tacz.guns.api.modifier.CacheValue;
/*    */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import com.tacz.guns.util.AttachmentDataUtils;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttachmentCacheProperty
/*    */ {
/* 19 */   private final Map<String, CacheValue> cacheValues = Maps.newHashMap();
/* 20 */   private final Map<String, List<?>> cacheModifiers = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */   
/*    */   public void eval(ItemStack gunItem, GunData gunData) {
/* 25 */     Map<String, IAttachmentModifier<?, ?>> modifiers = AttachmentPropertyManager.getModifiers();
/* 26 */     modifiers.forEach((id, value) -> {
/*    */           this.cacheValues.put(id, value.initCache(gunItem, gunData));
/*    */           
/*    */           this.cacheModifiers.put(id, Lists.newArrayList());
/*    */         });
/*    */     
/* 32 */     AttachmentDataUtils.getAllAttachmentData(gunItem, gunData, data -> data.getModifier().forEach(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     this.cacheValues.forEach((id, value) -> {
/*    */           List cacheModifier = this.cacheModifiers.get(id);
/*    */           
/*    */           if (cacheModifier == null || cacheModifier.isEmpty()) {
/*    */             return;
/*    */           }
/*    */           
/*    */           ((IAttachmentModifier)modifiers.get(id)).eval(cacheModifier, value);
/*    */         });
/*    */     
/* 50 */     this.cacheModifiers.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T getCache(String id) {
/* 55 */     return (T)((CacheValue)this.cacheValues.get(id)).getValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\AttachmentCacheProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */