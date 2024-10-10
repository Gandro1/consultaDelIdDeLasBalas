/*     */ package com.tacz.guns.block;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.tacz.guns.block.entity.TargetBlockEntity;
/*     */ import com.tacz.guns.entity.EntityKineticBullet;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.BooleanProperty;
/*     */ import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.PushReaction;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.phys.shapes.Shapes;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TargetBlock extends BaseEntityBlock {
/*  39 */   public static final IntegerProperty OUTPUT_POWER = BlockStateProperties.f_61426_;
/*  40 */   public static final DirectionProperty FACING = BlockStateProperties.f_61374_;
/*  41 */   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.f_61401_;
/*  42 */   public static final BooleanProperty STAND = BooleanProperty.m_61465_("stand");
/*  43 */   public static final VoxelShape BOX_BOTTOM_STAND_X = Shapes.m_83110_(Block.m_49796_(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), Block.m_49796_(6.0D, 13.0D, 2.0D, 10.0D, 16.0D, 14.0D));
/*  44 */   public static final VoxelShape BOX_BOTTOM_STAND_Z = Shapes.m_83110_(Block.m_49796_(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), Block.m_49796_(2.0D, 13.0D, 6.0D, 14.0D, 16.0D, 10.0D));
/*  45 */   public static final VoxelShape BOX_BOTTOM_DOWN = Block.m_49796_(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
/*  46 */   public static final VoxelShape BOX_UPPER_X = Block.m_49796_(6.0D, 0.0D, 2.0D, 10.0D, 16.0D, 14.0D);
/*  47 */   public static final VoxelShape BOX_UPPER_Z = Block.m_49796_(2.0D, 0.0D, 6.0D, 14.0D, 16.0D, 10.0D);
/*     */   
/*     */   public TargetBlock() {
/*  50 */     super(BlockBehaviour.Properties.m_284310_().m_60918_(SoundType.f_56736_).m_60913_(2.0F, 3.0F).m_60955_());
/*  51 */     m_49959_((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)HALF, (Comparable)DoubleBlockHalf.LOWER)).m_61124_((Property)STAND, Boolean.valueOf(true))).m_61124_((Property)OUTPUT_POWER, Integer.valueOf(0)));
/*     */   }
/*     */   
/*     */   public static int getRedstoneStrength(BlockHitResult hit, boolean isUpperBlock) {
/*     */     double distance;
/*  56 */     if (!isUpperBlock) {
/*  57 */       return 1;
/*     */     }
/*  59 */     Vec3 hitLocation = hit.m_82450_();
/*  60 */     Direction direction = hit.m_82434_();
/*     */     
/*  62 */     double x = Math.abs(Mth.m_14185_(hitLocation.f_82479_) - 0.5D);
/*  63 */     double y = Math.abs(Mth.m_14185_(hitLocation.f_82480_) - 0.32D);
/*  64 */     double z = Math.abs(Mth.m_14185_(hitLocation.f_82481_) - 0.5D);
/*  65 */     Direction.Axis axis = direction.m_122434_();
/*     */     
/*  67 */     if (axis == Direction.Axis.Y) {
/*  68 */       distance = Math.max(x, z);
/*  69 */     } else if (axis == Direction.Axis.Z) {
/*  70 */       distance = Math.max(x, y);
/*     */     } else {
/*  72 */       distance = Math.max(y, z);
/*     */     } 
/*     */     
/*  75 */     double percent = Mth.m_14008_((0.25D - distance) / 0.25D, 0.0D, 1.0D);
/*  76 */     return Math.max(1, Mth.m_14165_(15.0D * percent));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
/*  82 */     return (((DoubleBlockHalf)state.m_61143_((Property)HALF)).equals(DoubleBlockHalf.LOWER) && level.m_5776_()) ? m_152132_(blockEntityType, (BlockEntityType)ModBlocks.TARGET_BE.get(), TargetBlockEntity::clientTick) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/*  87 */     builder.m_61104_(new Property[] { (Property)FACING, (Property)HALF, (Property)STAND, (Property)OUTPUT_POWER });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntity m_142194_(BlockPos pos, BlockState blockState) {
/*  93 */     if (((DoubleBlockHalf)blockState.m_61143_((Property)HALF)).equals(DoubleBlockHalf.LOWER)) {
/*  94 */       return (BlockEntity)new TargetBlockEntity(pos, blockState);
/*     */     }
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
/* 102 */     boolean stand = ((Boolean)state.m_61143_((Property)STAND)).booleanValue();
/* 103 */     boolean axis = ((Direction)state.m_61143_((Property)FACING)).m_122434_().equals(Direction.Axis.X);
/* 104 */     if (((DoubleBlockHalf)state.m_61143_((Property)HALF)).equals(DoubleBlockHalf.UPPER)) {
/* 105 */       return stand ? (axis ? BOX_UPPER_X : BOX_UPPER_Z) : Shapes.m_83040_();
/*     */     }
/* 107 */     return stand ? (axis ? BOX_BOTTOM_STAND_X : BOX_BOTTOM_STAND_Z) : BOX_BOTTOM_DOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_213897_(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
/* 113 */     if (!((Boolean)state.m_61143_((Property)STAND)).booleanValue()) {
/* 114 */       level.m_7731_(pos, (BlockState)((BlockState)state.m_61124_((Property)STAND, Boolean.valueOf(true))).m_61124_((Property)OUTPUT_POWER, Integer.valueOf(0)), 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5581_(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
/* 120 */     if (hit.m_82434_().m_122424_().equals(state.m_61143_((Property)FACING))) {
/* 121 */       if (((DoubleBlockHalf)state.m_61143_((Property)HALF)).equals(DoubleBlockHalf.LOWER)) {
/* 122 */         world.m_141902_(hit.m_82425_(), TargetBlockEntity.TYPE).ifPresent(e -> e.hit(world, state, hit, false));
/* 123 */       } else if (((DoubleBlockHalf)state.m_61143_((Property)HALF)).equals(DoubleBlockHalf.UPPER)) {
/* 124 */         world.m_141902_(hit.m_82425_().m_7495_(), TargetBlockEntity.TYPE).ifPresent(e -> e.hit(world, state, hit, true));
/*     */       } 
/*     */       
/* 127 */       if (!world.m_5776_()) { Entity entity = projectile.m_19749_(); if (entity instanceof Player) { Player player = (Player)entity; if (((Boolean)state.m_61143_((Property)STAND)).booleanValue() && 
/* 128 */             projectile instanceof EntityKineticBullet) { EntityKineticBullet bullet = (EntityKineticBullet)projectile;
/* 129 */             String formattedDamage = String.format("%.1f", new Object[] { Float.valueOf(bullet.getDamage(hit.m_82450_())) });
/* 130 */             String formattedDistance = String.format("%.2f", new Object[] { Double.valueOf(hit.m_82450_().m_82554_(player.m_20182_())) });
/* 131 */             player.m_5661_((Component)Component.m_237110_("message.tacz.target_minecart.hit", new Object[] { formattedDamage, formattedDistance }), true); }
/*     */            }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState m_7417_(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
/* 140 */     DoubleBlockHalf half = (DoubleBlockHalf)state.m_61143_((Property)HALF);
/* 141 */     boolean stand = ((Boolean)state.m_61143_((Property)STAND)).booleanValue();
/*     */     
/* 143 */     if (facing.m_122434_() == Direction.Axis.Y && ((
/* 144 */       half.equals(DoubleBlockHalf.LOWER) && facing == Direction.UP) || (half.equals(DoubleBlockHalf.UPPER) && facing == Direction.DOWN))) {
/*     */       
/* 146 */       if (!facingState.m_60713_((Block)this)) {
/* 147 */         return Blocks.f_50016_.m_49966_();
/*     */       }
/*     */       
/* 150 */       if (((Boolean)facingState.m_61143_((Property)STAND)).booleanValue() != stand) {
/* 151 */         return (BlockState)((BlockState)state.m_61124_((Property)STAND, facingState.m_61143_((Property)STAND))).m_61124_((Property)OUTPUT_POWER, facingState.m_61143_((Property)OUTPUT_POWER));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 157 */     if (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.m_60710_((LevelReader)level, currentPos)) {
/* 158 */       return Blocks.f_50016_.m_49966_();
/*     */     }
/* 160 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/* 166 */     Direction direction = context.m_8125_();
/* 167 */     BlockPos clickedPos = context.m_8083_();
/* 168 */     BlockPos above = clickedPos.m_7494_();
/* 169 */     Level level = context.m_43725_();
/* 170 */     if (level.m_8055_(above).m_60629_(context) && level.m_6857_().m_61937_(above)) {
/* 171 */       return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)direction);
/*     */     }
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6402_(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
/* 178 */     super.m_6402_(world, pos, state, placer, stack);
/* 179 */     if (!world.f_46443_) {
/* 180 */       BlockPos above = pos.m_7494_();
/* 181 */       world.m_7731_(above, (BlockState)state.m_61124_((Property)HALF, (Comparable)DoubleBlockHalf.UPPER), 3);
/* 182 */       world.m_6289_(pos, Blocks.f_50016_);
/* 183 */       state.m_60701_((LevelAccessor)world, pos, 3);
/* 184 */       if (stack.m_41788_()) {
/* 185 */         BlockEntity blockentity = world.m_7702_(pos);
/* 186 */         if (blockentity instanceof TargetBlockEntity) { TargetBlockEntity e = (TargetBlockEntity)blockentity;
/* 187 */           GameProfile gameprofile = new GameProfile(null, stack.m_41786_().getString());
/* 188 */           e.setOwner(gameprofile);
/* 189 */           e.setCustomName(stack.m_41786_());
/* 190 */           e.refresh(); }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
/* 198 */     BlockPos blockPos = (state.m_61143_((Property)HALF) == DoubleBlockHalf.LOWER) ? pos : pos.m_7495_();
/* 199 */     BlockEntity blockentity = level.m_7702_(blockPos);
/* 200 */     if (blockentity instanceof TargetBlockEntity) { TargetBlockEntity e = (TargetBlockEntity)blockentity;
/* 201 */       return (new ItemStack((ItemLike)this)).m_41714_(e.m_7770_()); }
/*     */     
/* 203 */     return super.getCloneItemStack(state, target, level, pos, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7898_(BlockState state, LevelReader level, BlockPos pos) {
/* 208 */     BlockPos blockpos = pos.m_7495_();
/* 209 */     BlockState blockstate = level.m_8055_(blockpos);
/* 210 */     if (state.m_61143_((Property)HALF) == DoubleBlockHalf.LOWER) {
/* 211 */       return true;
/*     */     }
/* 213 */     return blockstate.m_60713_((Block)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7899_(BlockState state) {
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_6378_(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
/* 223 */     return ((Integer)blockState.m_61143_((Property)OUTPUT_POWER)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6807_(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
/* 228 */     if (!level.m_5776_() && !state.m_60713_(oldState.m_60734_()) && (
/* 229 */       (Integer)state.m_61143_((Property)OUTPUT_POWER)).intValue() > 0 && !level.m_183326_().m_183582_(pos, this)) {
/* 230 */       level.m_7731_(pos, (BlockState)state.m_61124_((Property)OUTPUT_POWER, Integer.valueOf(0)), 18);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderShape m_7514_(BlockState state) {
/* 237 */     return RenderShape.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public PushReaction getPistonPushReaction(BlockState state) {
/* 242 */     return PushReaction.DESTROY;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\TargetBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */