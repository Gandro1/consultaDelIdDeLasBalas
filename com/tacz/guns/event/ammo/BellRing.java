/*    */ package com.tacz.guns.event.ammo;
/*    */ import com.tacz.guns.api.event.server.AmmoHitBlockEvent;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.BellBlock;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class BellRing {
/*    */   @SubscribeEvent
/*    */   public static void onAmmoHitBlock(AmmoHitBlockEvent event) {
/* 15 */     Level level = event.getLevel();
/* 16 */     BlockState state = event.getState();
/* 17 */     BlockHitResult hitResult = event.getHitResult();
/* 18 */     Block block = state.m_60734_(); if (block instanceof BellBlock) { BellBlock bell = (BellBlock)block;
/* 19 */       bell.m_49712_(level, hitResult.m_82425_(), hitResult.m_82434_()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\event\ammo\BellRing.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */