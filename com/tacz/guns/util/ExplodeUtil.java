/*    */ package com.tacz.guns.util;
/*    */ import com.tacz.guns.config.common.AmmoConfig;
/*    */ import com.tacz.guns.util.block.ProjectileExplosion;
/*    */ import net.minecraft.network.protocol.game.ClientboundExplodePacket;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.level.Explosion;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ public class ExplodeUtil {
/*    */   public static void createExplosion(Entity owner, Entity exploder, float damage, float radius, boolean knockback, boolean destroy, Vec3 hitPos) {
/*    */     ServerLevel level;
/* 16 */     Level level1 = exploder.m_9236_(); if (level1 instanceof ServerLevel) { level = (ServerLevel)level1; }
/*    */     else
/*    */     { return; }
/*    */     
/* 20 */     Explosion.BlockInteraction mode = Explosion.BlockInteraction.KEEP;
/* 21 */     if (destroy) {
/* 22 */       mode = Explosion.BlockInteraction.DESTROY;
/*    */     }
/*    */     
/* 25 */     ProjectileExplosion explosion = new ProjectileExplosion((Level)level, owner, exploder, null, null, hitPos.m_7096_(), hitPos.m_7098_(), hitPos.m_7094_(), damage, radius, knockback, mode);
/*    */     
/* 27 */     if (ForgeEventFactory.onExplosionStart((Level)level, (Explosion)explosion)) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     explosion.m_46061_();
/* 32 */     explosion.m_46075_(true);
/* 33 */     if (mode == Explosion.BlockInteraction.KEEP) {
/* 34 */       explosion.m_46080_();
/*    */     }
/*    */     
/* 37 */     level.m_6907_().stream().filter(player -> (Mth.m_14116_((float)player.m_20238_(hitPos)) < ((Integer)AmmoConfig.EXPLOSIVE_AMMO_VISIBLE_DISTANCE.get()).intValue())).forEach(player -> {
/*    */           ClientboundExplodePacket packet = new ClientboundExplodePacket(hitPos.m_7096_(), hitPos.m_7098_(), hitPos.m_7094_(), radius, explosion.m_46081_(), (Vec3)explosion.m_46078_().get(player));
/*    */           player.f_8906_.m_9829_((Packet)packet);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\ExplodeUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */