/*    */ package com.tacz.guns.api.item.gun;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class GunItemManager
/*    */ {
/* 10 */   private static final Map<String, RegistryObject<? extends AbstractGunItem>> GUN_ITEM_MAP = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerGunItem(String name, RegistryObject<? extends AbstractGunItem> registryObject) {
/* 16 */     GUN_ITEM_MAP.put(name, registryObject);
/*    */   }
/*    */   
/*    */   public static RegistryObject<? extends AbstractGunItem> getGunItemRegistryObject(String key) {
/* 20 */     return GUN_ITEM_MAP.get(key);
/*    */   }
/*    */   
/*    */   public static Collection<RegistryObject<? extends AbstractGunItem>> getAllGunItems() {
/* 24 */     return GUN_ITEM_MAP.values();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\gun\GunItemManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */