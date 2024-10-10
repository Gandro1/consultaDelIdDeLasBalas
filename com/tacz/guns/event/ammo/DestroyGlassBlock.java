/*    */ package com.tacz.guns.event.ammo;
/*    */ 
/*    */ import com.tacz.guns.api.event.server.AmmoHitBlockEvent;
/*    */ import com.tacz.guns.config.common.AmmoConfig;
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class DestroyGlassBlock
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onAmmoHitBlock(AmmoHitBlockEvent event) {
/* 21 */     Level level = event.getLevel();
/* 22 */     BlockState state = event.getState();
/* 23 */     BlockPos pos = event.getHitResult().m_82425_();
/* 24 */     EntityKineticBullet ammo = event.getAmmo();
/* 25 */     Block stateBlock = state.m_60734_();
/* 26 */     NoteBlockInstrument instrument = state.m_280603_();
/* 27 */     if (((Boolean)AmmoConfig.DESTROY_GLASS.get()).booleanValue() && (stateBlock instanceof net.minecraft.world.level.block.AbstractGlassBlock || stateBlock instanceof net.minecraft.world.level.block.StainedGlassPaneBlock || (stateBlock instanceof net.minecraft.world.level.block.IronBarsBlock && instrument
/*    */       
/* 29 */       .equals(NoteBlockInstrument.HAT))))
/* 30 */       level.m_46953_(pos, false, ammo.m_19749_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\ammo\DestroyGlassBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */