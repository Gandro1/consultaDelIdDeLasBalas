/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import net.minecraft.world.phys.EntityHitResult;
/*    */ 
/*    */ public class TacHitResult extends EntityHitResult {
/*    */   private final boolean headshot;
/*    */   
/*    */   public TacHitResult(EntityKineticBullet.EntityResult result) {
/* 10 */     super(result.getEntity(), result.getHitPos());
/* 11 */     this.headshot = result.isHeadshot();
/*    */   }
/*    */   
/*    */   public boolean isHeadshot() {
/* 15 */     return this.headshot;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\TacHitResult.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */