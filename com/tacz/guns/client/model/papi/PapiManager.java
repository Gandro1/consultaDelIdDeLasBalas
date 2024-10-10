/*    */ package com.tacz.guns.client.model.papi;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.resources.language.I18n;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public final class PapiManager
/*    */ {
/* 14 */   private static final Map<String, Function<ItemStack, String>> PAPI = Maps.newHashMap();
/*    */ 
/*    */   
/*    */   static {
/* 18 */     addPapi("player_name", new PlayerNamePapi());
/* 19 */     addPapi("ammo_count", new AmmoCountPapi());
/*    */   }
/*    */   
/*    */   public static void addPapi(String textKey, Function<ItemStack, String> function) {
/* 23 */     textKey = "%" + textKey + "%";
/* 24 */     PAPI.put(textKey, function);
/*    */   }
/*    */   
/*    */   public static String getTextShow(String textKey, ItemStack stack) {
/* 28 */     String text = I18n.f_118934_.m_6834_(textKey);
/* 29 */     for (Map.Entry<String, Function<ItemStack, String>> entry : PAPI.entrySet()) {
/* 30 */       String placeholder = entry.getKey();
/* 31 */       String data = ((Function<ItemStack, String>)entry.getValue()).apply(stack);
/* 32 */       text = text.replace(placeholder, data);
/*    */     } 
/* 34 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\papi\PapiManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */