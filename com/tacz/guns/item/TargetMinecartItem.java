/*    */ package com.tacz.guns.item;
/*    */ import com.tacz.guns.entity.TargetMinecart;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.context.UseOnContext;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.BaseRailBlock;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.RailShape;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TargetMinecartItem extends Item {
/*    */   public TargetMinecartItem() {
/* 19 */     super((new Item.Properties()).m_41487_(1));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResult m_6225_(UseOnContext context) {
/* 25 */     Level level = context.m_43725_();
/* 26 */     BlockPos blockpos = context.m_8083_();
/* 27 */     BlockState blockstate = level.m_8055_(blockpos);
/* 28 */     if (!blockstate.m_204336_(BlockTags.f_13034_)) {
/* 29 */       return InteractionResult.FAIL;
/*    */     }
/* 31 */     ItemStack itemstack = context.m_43722_();
/* 32 */     if (!level.f_46443_) {
/* 33 */       Block block = blockstate.m_60734_(); BaseRailBlock baseRailBlock = (BaseRailBlock)block; RailShape railshape = (block instanceof BaseRailBlock) ? baseRailBlock.getRailDirection(blockstate, (BlockGetter)level, blockpos, null) : RailShape.NORTH_SOUTH;
/* 34 */       double yOffset = 0.0D;
/* 35 */       if (railshape.m_61745_()) {
/* 36 */         yOffset = 0.5D;
/*    */       }
/* 38 */       TargetMinecart targetMinecart = new TargetMinecart(level, blockpos.m_123341_() + 0.5D, blockpos.m_123342_() + 0.0625D + yOffset, blockpos.m_123343_() + 0.5D);
/* 39 */       if (itemstack.m_41788_()) {
/* 40 */         targetMinecart.m_6593_(itemstack.m_41786_());
/*    */       }
/* 42 */       level.m_7967_((Entity)targetMinecart);
/* 43 */       level.m_142346_((Entity)context.m_43723_(), GameEvent.f_157810_, blockpos);
/*    */     } 
/* 45 */     itemstack.m_41774_(1);
/* 46 */     return InteractionResult.m_19078_(level.f_46443_);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\TargetMinecartItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */