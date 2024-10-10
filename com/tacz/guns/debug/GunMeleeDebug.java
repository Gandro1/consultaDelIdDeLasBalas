/*    */ package com.tacz.guns.debug;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ public class GunMeleeDebug {
/*    */   public static void showRange(LivingEntity user, int distance, Vec3 centrePos, Vec3 eyeVec, float rangeAngle) {
/*    */     ServerLevel serverLevel;
/* 10 */     Level level = user.m_9236_(); if (level instanceof ServerLevel) { serverLevel = (ServerLevel)level; }
/*    */     else
/*    */     { return; }
/*    */     
/* 14 */     int half = distance / 2;
/* 15 */     Vec3 startPos = user.m_146892_().m_82492_(half, half, half);
/*    */     
/* 17 */     for (int i = 0; i < distance * 2; i++) {
/* 18 */       for (int j = 0; j < distance * 2; j++) {
/* 19 */         for (int k = 0; k < distance * 2; k++) {
/*    */           
/* 21 */           Vec3 tmpPos = startPos.m_82520_(i / 2.0D, j / 2.0D, k / 2.0D);
/*    */           
/* 23 */           Vec3 targetVec = tmpPos.m_82546_(centrePos);
/*    */           
/* 25 */           double targetLength = targetVec.m_82553_();
/*    */           
/* 27 */           if (targetLength >= distance) {
/*    */ 
/*    */ 
/*    */             
/* 31 */             double degree = Math.toDegrees(Math.acos(targetVec.m_82526_(eyeVec) / targetLength * distance));
/*    */             
/* 33 */             if (degree < (rangeAngle / 2.0F))
/* 34 */               serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123744_, tmpPos.f_82479_, tmpPos.f_82480_, tmpPos.f_82481_, 1, 0.0D, 0.0D, 0.0D, 0.0D); 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\debug\GunMeleeDebug.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */