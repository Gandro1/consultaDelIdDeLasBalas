/*     */ package com.tacz.guns.block;
/*     */ import com.tacz.guns.block.entity.StatueBlockEntity;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.SoundType;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.DirectionProperty;
/*     */ import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
/*     */ import net.minecraft.world.level.block.state.properties.EnumProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.PushReaction;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class StatueBlock extends BaseEntityBlock {
/*  32 */   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.f_61401_;
/*  33 */   public static final DirectionProperty FACING = BlockStateProperties.f_61374_;
/*     */   
/*     */   public StatueBlock() {
/*  36 */     super(BlockBehaviour.Properties.m_284310_().m_60918_(SoundType.f_56742_).m_60913_(2.0F, 3.0F).m_60955_());
/*  37 */     m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_())
/*  38 */         .m_61124_((Property)HALF, (Comparable)DoubleBlockHalf.LOWER))
/*  39 */         .m_61124_((Property)FACING, (Comparable)Direction.NORTH));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
/*  46 */     return (((DoubleBlockHalf)state.m_61143_((Property)HALF)).equals(DoubleBlockHalf.LOWER) && level.m_5776_()) ? m_152132_(blockEntityType, (BlockEntityType)ModBlocks.STATUE_BE.get(), StatueBlockEntity::clientTick) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/*  51 */     builder.m_61104_(new Property[] { (Property)HALF, (Property)FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntity m_142194_(BlockPos pPos, BlockState pState) {
/*  57 */     return (pState.m_61143_((Property)HALF) == DoubleBlockHalf.LOWER) ? (BlockEntity)new StatueBlockEntity(pPos, pState) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResult m_6227_(BlockState pState, Level level, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {
/*  62 */     if (level.m_5776_()) {
/*  63 */       return InteractionResult.SUCCESS;
/*     */     }
/*  65 */     if (pState.m_61143_((Property)HALF) == DoubleBlockHalf.UPPER) {
/*  66 */       pos = pos.m_7495_();
/*     */     }
/*     */     
/*  69 */     BlockEntity blockEntity = level.m_7702_(pos);
/*  70 */     if (blockEntity instanceof StatueBlockEntity) { StatueBlockEntity statueBlockEntity = (StatueBlockEntity)blockEntity;
/*  71 */       ItemStack stack = player.m_21120_(pHand);
/*  72 */       if (stack.m_41720_() instanceof com.tacz.guns.api.item.IGun) {
/*  73 */         statueBlockEntity.setGun(stack);
/*  74 */         stack.m_41774_(1);
/*  75 */         return InteractionResult.SUCCESS;
/*     */       } 
/*     */       
/*  78 */       if (stack.m_41619_()) {
/*  79 */         statueBlockEntity.dropItem();
/*  80 */         return InteractionResult.SUCCESS;
/*     */       }  }
/*     */     
/*  83 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/*  89 */     Direction direction = context.m_8125_().m_122424_();
/*  90 */     BlockPos clickedPos = context.m_8083_();
/*  91 */     BlockPos above = clickedPos.m_7494_();
/*  92 */     Level level = context.m_43725_();
/*  93 */     if (level.m_8055_(above).m_60629_(context) && level.m_6857_().m_61937_(above)) {
/*  94 */       return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)direction);
/*     */     }
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6402_(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
/* 101 */     super.m_6402_(world, pos, state, placer, stack);
/* 102 */     if (!world.f_46443_) {
/* 103 */       BlockPos above = pos.m_7494_();
/* 104 */       world.m_7731_(above, (BlockState)state.m_61124_((Property)HALF, (Comparable)DoubleBlockHalf.UPPER), 3);
/* 105 */       world.m_6289_(pos, Blocks.f_50016_);
/* 106 */       state.m_60701_((LevelAccessor)world, pos, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState m_7417_(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
/* 112 */     DoubleBlockHalf half = (DoubleBlockHalf)state.m_61143_((Property)HALF);
/*     */     
/* 114 */     if (facing.m_122434_() == Direction.Axis.Y && ((
/* 115 */       half.equals(DoubleBlockHalf.LOWER) && facing == Direction.UP) || (half.equals(DoubleBlockHalf.UPPER) && facing == Direction.DOWN)))
/*     */     {
/* 117 */       if (!facingState.m_60713_((Block)this)) {
/* 118 */         return Blocks.f_50016_.m_49966_();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 123 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6810_(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
/* 128 */     if (!pState.m_60713_(pNewState.m_60734_())) {
/* 129 */       BlockEntity blockentity = pLevel.m_7702_(pPos);
/* 130 */       if (blockentity instanceof StatueBlockEntity) { StatueBlockEntity statueBlockEntity = (StatueBlockEntity)blockentity;
/* 131 */         statueBlockEntity.dropItem(); }
/*     */       
/* 133 */       super.m_6810_(pState, pLevel, pPos, pNewState, pMovedByPiston);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderShape m_7514_(BlockState state) {
/* 139 */     return RenderShape.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public PushReaction getPistonPushReaction(BlockState state) {
/* 144 */     return PushReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
/* 149 */     super.onBlockExploded(state, level, pos, explosion);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\StatueBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */