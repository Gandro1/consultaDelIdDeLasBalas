/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.util.HitboxHelper;
/*    */ import java.util.Map;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum InaccuracyType
/*    */ {
/* 17 */   STAND,
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   MOVE,
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   SNEAK,
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   LIE,
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   AIM;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static InaccuracyType getInaccuracyType(LivingEntity livingEntity) {
/* 47 */     float aimingProgress = IGunOperator.fromLivingEntity(livingEntity).getSynAimingProgress();
/*    */     
/* 49 */     if (aimingProgress == 1.0F) {
/* 50 */       return AIM;
/*    */     }
/*    */     
/* 53 */     if (!livingEntity.m_6069_() && livingEntity.m_20089_() == Pose.SWIMMING) {
/* 54 */       return LIE;
/*    */     }
/* 56 */     if (livingEntity.m_20089_() == Pose.CROUCHING) {
/* 57 */       return SNEAK;
/*    */     }
/* 59 */     if (isMove(livingEntity)) {
/* 60 */       return MOVE;
/*    */     }
/* 62 */     return STAND;
/*    */   }
/*    */   
/*    */   public static Map<InaccuracyType, Float> getDefaultInaccuracy() {
/* 66 */     Map<InaccuracyType, Float> inaccuracy = Maps.newHashMap();
/* 67 */     inaccuracy.put(STAND, Float.valueOf(5.0F));
/* 68 */     inaccuracy.put(MOVE, Float.valueOf(5.75F));
/* 69 */     inaccuracy.put(SNEAK, Float.valueOf(3.5F));
/* 70 */     inaccuracy.put(LIE, Float.valueOf(2.5F));
/* 71 */     inaccuracy.put(AIM, Float.valueOf(0.15F));
/* 72 */     return inaccuracy;
/*    */   }
/*    */   
/*    */   private static boolean isMove(LivingEntity livingEntity) {
/* 76 */     double distance = Math.abs(livingEntity.f_19787_ - livingEntity.f_19867_);
/* 77 */     if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/* 78 */       distance = HitboxHelper.getPlayerVelocity(player).m_82553_(); }
/*    */     
/* 80 */     return (distance > 0.05000000074505806D);
/*    */   }
/*    */   
/*    */   public boolean isAim() {
/* 84 */     return (this == AIM);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\InaccuracyType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */