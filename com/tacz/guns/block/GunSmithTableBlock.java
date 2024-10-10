/*     */ package com.tacz.guns.block;
/*     */ import com.tacz.guns.block.entity.GunSmithTableBlockEntity;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BedPart;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.EnumProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.PushReaction;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GunSmithTableBlock extends BaseEntityBlock {
/*  30 */   public static final VoxelShape BLOCK_AABB = Block.m_49796_(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
/*  31 */   public static final DirectionProperty FACING = BlockStateProperties.f_61374_;
/*  32 */   public static final EnumProperty<BedPart> PART = BlockStateProperties.f_61391_;
/*     */   
/*     */   public GunSmithTableBlock() {
/*  35 */     super(BlockBehaviour.Properties.m_284310_().m_60918_(SoundType.f_56736_).m_60913_(2.0F, 3.0F).m_60955_());
/*  36 */     m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)PART, (Comparable)BedPart.FOOT));
/*     */   }
/*     */   
/*     */   private static Direction getNeighbourDirection(BedPart bedPart, Direction direction) {
/*  40 */     return (bedPart == BedPart.FOOT) ? direction : direction.m_122424_();
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResult m_6227_(BlockState pState, Level level, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {
/*  45 */     if (level.f_46443_) {
/*  46 */       return InteractionResult.SUCCESS;
/*     */     }
/*  48 */     BlockEntity blockEntity = level.m_7702_(pos);
/*  49 */     if (blockEntity instanceof GunSmithTableBlockEntity) { GunSmithTableBlockEntity gunSmithTable = (GunSmithTableBlockEntity)blockEntity;
/*  50 */       player.m_5893_((MenuProvider)gunSmithTable); }
/*     */     
/*  52 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/*  58 */     builder.m_61104_(new Property[] { (Property)FACING, (Property)PART });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntity m_142194_(BlockPos pos, BlockState blockState) {
/*  64 */     return (BlockEntity)new GunSmithTableBlockEntity(pos, blockState);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/*  69 */     Direction direction = context.m_8125_();
/*  70 */     BlockPos clickedPos = context.m_8083_();
/*  71 */     BlockPos relative = clickedPos.m_121945_(direction);
/*  72 */     Level level = context.m_43725_();
/*  73 */     if (level.m_8055_(relative).m_60629_(context) && level.m_6857_().m_61937_(relative)) {
/*  74 */       return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)direction);
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5707_(Level level, BlockPos pos, BlockState blockState, Player player) {
/*  81 */     if (!level.f_46443_ && player.m_7500_()) {
/*  82 */       BedPart bedPart = (BedPart)blockState.m_61143_((Property)PART);
/*  83 */       if (bedPart == BedPart.FOOT) {
/*  84 */         BlockPos blockpos = pos.m_121945_(getNeighbourDirection(bedPart, (Direction)blockState.m_61143_((Property)FACING)));
/*  85 */         BlockState blockstate = level.m_8055_(blockpos);
/*  86 */         if (blockstate.m_60713_((Block)this) && blockstate.m_61143_((Property)PART) == BedPart.HEAD) {
/*  87 */           level.m_7731_(blockpos, Blocks.f_50016_.m_49966_(), 35);
/*  88 */           level.m_5898_(player, 2001, blockpos, Block.m_49956_(blockstate));
/*     */         } 
/*     */       } 
/*     */     } 
/*  92 */     super.m_5707_(level, pos, blockState, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6402_(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
/*  97 */     super.m_6402_(worldIn, pos, state, placer, stack);
/*  98 */     if (!worldIn.f_46443_) {
/*  99 */       BlockPos relative = pos.m_121945_((Direction)state.m_61143_((Property)FACING));
/* 100 */       worldIn.m_7731_(relative, (BlockState)state.m_61124_((Property)PART, (Comparable)BedPart.HEAD), 3);
/* 101 */       worldIn.m_6289_(pos, Blocks.f_50016_);
/* 102 */       state.m_60701_((LevelAccessor)worldIn, pos, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState m_7417_(BlockState state, Direction direction, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
/* 108 */     if (direction == getNeighbourDirection((BedPart)state.m_61143_((Property)PART), (Direction)state.m_61143_((Property)FACING))) {
/* 109 */       return (facingState.m_60713_((Block)this) && facingState.m_61143_((Property)PART) != state.m_61143_((Property)PART)) ? state : Blocks.f_50016_.m_49966_();
/*     */     }
/* 111 */     return super.m_7417_(state, direction, facingState, level, currentPos, facingPos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderShape m_7514_(BlockState pState) {
/* 117 */     return RenderShape.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public PushReaction getPistonPushReaction(BlockState state) {
/* 122 */     return PushReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
/* 127 */     return BLOCK_AABB;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\GunSmithTableBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */