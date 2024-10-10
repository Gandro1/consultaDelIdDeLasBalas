/*    */ package com.tacz.guns.api.event.server;
/*    */ 
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ public class AmmoHitBlockEvent
/*    */   extends Event
/*    */ {
/*    */   private final Level level;
/*    */   private final BlockHitResult hitResult;
/*    */   private final BlockState state;
/*    */   private final EntityKineticBullet ammo;
/*    */   
/*    */   public AmmoHitBlockEvent(Level level, BlockHitResult hitResult, BlockState state, EntityKineticBullet ammo) {
/* 19 */     this.level = level;
/* 20 */     this.hitResult = hitResult;
/* 21 */     this.state = state;
/* 22 */     this.ammo = ammo;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelable() {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public Level getLevel() {
/* 31 */     return this.level;
/*    */   }
/*    */   
/*    */   public BlockHitResult getHitResult() {
/* 35 */     return this.hitResult;
/*    */   }
/*    */   
/*    */   public BlockState getState() {
/* 39 */     return this.state;
/*    */   }
/*    */   
/*    */   public EntityKineticBullet getAmmo() {
/* 43 */     return this.ammo;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\server\AmmoHitBlockEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */