/*    */ package com.tacz.guns.api.entity;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface KnockBackModifier
/*    */ {
/*    */   static KnockBackModifier fromLivingEntity(LivingEntity entity) {
/* 14 */     return (KnockBackModifier)entity;
/*    */   }
/*    */   
/*    */   void resetKnockBackStrength();
/*    */   
/*    */   double getKnockBackStrength();
/*    */   
/*    */   void setKnockBackStrength(double paramDouble);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\entity\KnockBackModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */