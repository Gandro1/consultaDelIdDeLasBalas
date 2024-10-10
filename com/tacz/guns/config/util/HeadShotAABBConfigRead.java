/*    */ package com.tacz.guns.config.util;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ 
/*    */ public class HeadShotAABBConfigRead
/*    */ {
/* 14 */   private static final Map<ResourceLocation, AABB> AABB_CHECK = Maps.newHashMap();
/*    */ 
/*    */   
/* 17 */   private static final Pattern REG = Pattern.compile("^([a-z0-9_.-]+:[a-z0-9/._-]+) *?\\[([-+]?[0-9]*\\.?[0-9]+), *?([-+]?[0-9]*\\.?[0-9]+), *?([-+]?[0-9]*\\.?[0-9]+), *?([-+]?[0-9]*\\.?[0-9]+), *?([-+]?[0-9]*\\.?[0-9]+), *?([-+]?[0-9]*\\.?[0-9]+),*? *?]");
/*    */   
/*    */   public static void init() {
/* 20 */     AABB_CHECK.clear();
/* 21 */     List<String> configData = (List<String>)SyncConfig.HEAD_SHOT_AABB.get();
/* 22 */     for (String text : configData) {
/* 23 */       addCheck(text);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void addCheck(String text) {
/* 28 */     Matcher matcher = REG.matcher(text);
/* 29 */     if (matcher.find()) {
/* 30 */       ResourceLocation id = new ResourceLocation(matcher.group(1));
/* 31 */       double x1 = Double.parseDouble(matcher.group(2));
/* 32 */       double y1 = Double.parseDouble(matcher.group(3));
/* 33 */       double z1 = Double.parseDouble(matcher.group(4));
/* 34 */       double x2 = Double.parseDouble(matcher.group(5));
/* 35 */       double y2 = Double.parseDouble(matcher.group(6));
/* 36 */       double z2 = Double.parseDouble(matcher.group(7));
/* 37 */       AABB aabb = new AABB(x1, y1, z1, x2, y2, z2);
/* 38 */       AABB_CHECK.put(id, aabb);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void clearAABB() {
/* 43 */     AABB_CHECK.clear();
/*    */   }
/*    */   
/*    */   public static AABB getAABB(ResourceLocation id) {
/* 47 */     return AABB_CHECK.get(id);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\confi\\util\HeadShotAABBConfigRead.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */