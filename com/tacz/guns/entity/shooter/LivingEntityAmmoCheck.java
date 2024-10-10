/*    */ package com.tacz.guns.entity.shooter;
/*    */ 
/*    */ import com.tacz.guns.config.common.GunConfig;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public class LivingEntityAmmoCheck {
/*    */   private final LivingEntity shooter;
/*    */   
/*    */   public LivingEntityAmmoCheck(LivingEntity shooter) {
/* 11 */     this.shooter = shooter;
/*    */   }
/*    */   
/*    */   public boolean needCheckAmmo() {
/* 15 */     LivingEntity livingEntity = this.shooter; if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/* 16 */       return !player.m_7500_(); }
/*    */     
/* 18 */     return true;
/*    */   }
/*    */   
/*    */   public boolean consumesAmmoOrNot() {
/* 22 */     LivingEntity livingEntity = this.shooter; if (livingEntity instanceof Player) { Player player = (Player)livingEntity;
/* 23 */       return (!player.m_7500_() || ((Boolean)GunConfig.CREATIVE_PLAYER_CONSUME_AMMO.get()).booleanValue()); }
/*    */     
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityAmmoCheck.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */