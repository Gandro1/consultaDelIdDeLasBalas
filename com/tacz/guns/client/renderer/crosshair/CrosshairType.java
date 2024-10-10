/*    */ package com.tacz.guns.client.renderer.crosshair;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public enum CrosshairType
/*    */ {
/*    */   private static final Map<CrosshairType, ResourceLocation> CACHE;
/* 11 */   EMPTY,
/* 12 */   DOT_1,
/* 13 */   CIRCLE_1,
/* 14 */   CIRCLE_2,
/* 15 */   CIRCLE_3,
/* 16 */   CROSS_1,
/* 17 */   CROSS_2,
/* 18 */   CROSS_3,
/* 19 */   CROSS_4,
/* 20 */   CROSS_5,
/* 21 */   CROSS_6,
/* 22 */   LINE_1,
/* 23 */   LINE_2,
/* 24 */   LINE_3,
/* 25 */   SQUARE_1,
/* 26 */   SQUARE_2,
/* 27 */   SQUARE_3,
/* 28 */   SQUARE_4,
/* 29 */   SQUARE_5,
/* 30 */   SQUARE_6,
/* 31 */   TRIDENT_1,
/* 32 */   TRIDENT_2;
/*    */   static {
/* 34 */     CACHE = Maps.newHashMap();
/*    */   }
/*    */   public static ResourceLocation getTextureLocation(CrosshairType type) {
/* 37 */     ResourceLocation location = CACHE.get(type);
/* 38 */     if (location == null) {
/* 39 */       location = new ResourceLocation("tacz", "textures/crosshair/normal/%s.png".formatted(new Object[] { type.name().toLowerCase(Locale.US) }));
/* 40 */       CACHE.put(type, location);
/*    */     } 
/* 42 */     return location;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\crosshair\CrosshairType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */