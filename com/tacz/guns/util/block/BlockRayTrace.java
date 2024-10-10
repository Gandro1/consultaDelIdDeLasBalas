/*     */ package com.tacz.guns.util.block;
/*     */ import com.tacz.guns.config.common.AmmoConfig;
/*     */ import com.tacz.guns.init.ModBlocks;
/*     */ import java.util.List;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ public final class BlockRayTrace {
/*     */   static {
/*  25 */     IGNORES = (input -> (input != null && input.m_204336_(ModBlocks.BULLET_IGNORE_BLOCKS)));
/*     */   }
/*     */   private static final Predicate<BlockState> IGNORES;
/*     */   public static BlockHitResult rayTraceBlocks(Level level, ClipContext context) {
/*  29 */     return performRayTrace(context, (rayTraceContext, blockPos) -> {
/*     */           BlockState blockState = level.m_8055_(blockPos);
/*     */           
/*     */           List<String> ids = (List<String>)AmmoConfig.PASS_THROUGH_BLOCKS.get();
/*     */           ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(blockState.m_60734_());
/*  34 */           return (blockId != null && ids.contains(blockId.toString())) ? null : (IGNORES.test(blockState) ? null : getBlockHitResult(level, rayTraceContext, blockPos, blockState));
/*     */         }rayTraceContext -> {
/*     */           Vec3 vec3 = rayTraceContext.m_45702_().m_82546_(rayTraceContext.m_45693_());
/*     */           return BlockHitResult.m_82426_(rayTraceContext.m_45693_(), Direction.m_122366_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_), BlockPos.m_274446_((Position)rayTraceContext.m_45693_()));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static BlockHitResult getBlockHitResult(Level level, ClipContext rayTraceContext, BlockPos blockPos, BlockState blockState) {
/*  50 */     FluidState fluidState = level.m_6425_(blockPos);
/*  51 */     Vec3 startVec = rayTraceContext.m_45702_();
/*  52 */     Vec3 endVec = rayTraceContext.m_45693_();
/*  53 */     VoxelShape blockShape = rayTraceContext.m_45694_(blockState, (BlockGetter)level, blockPos);
/*  54 */     BlockHitResult blockResult = level.m_45558_(startVec, endVec, blockPos, blockShape, blockState);
/*  55 */     VoxelShape fluidShape = rayTraceContext.m_45698_(fluidState, (BlockGetter)level, blockPos);
/*  56 */     BlockHitResult fluidResult = fluidShape.m_83220_(startVec, endVec, blockPos);
/*  57 */     double blockDistance = (blockResult == null) ? Double.MAX_VALUE : rayTraceContext.m_45702_().m_82557_(blockResult.m_82450_());
/*  58 */     double fluidDistance = (fluidResult == null) ? Double.MAX_VALUE : rayTraceContext.m_45702_().m_82557_(fluidResult.m_82450_());
/*  59 */     return (blockDistance <= fluidDistance) ? blockResult : fluidResult;
/*     */   }
/*     */   
/*     */   private static <T> T performRayTrace(ClipContext context, BiFunction<ClipContext, BlockPos, T> hitFunction, Function<ClipContext, T> missFactory) {
/*  63 */     Vec3 startVec = context.m_45702_();
/*  64 */     Vec3 endVec = context.m_45693_();
/*  65 */     if (!startVec.equals(endVec)) {
/*  66 */       double startX = Mth.m_14139_(-1.0E-7D, endVec.f_82479_, startVec.f_82479_);
/*  67 */       double startY = Mth.m_14139_(-1.0E-7D, endVec.f_82480_, startVec.f_82480_);
/*  68 */       double startZ = Mth.m_14139_(-1.0E-7D, endVec.f_82481_, startVec.f_82481_);
/*  69 */       double endX = Mth.m_14139_(-1.0E-7D, startVec.f_82479_, endVec.f_82479_);
/*  70 */       double endY = Mth.m_14139_(-1.0E-7D, startVec.f_82480_, endVec.f_82480_);
/*  71 */       double endZ = Mth.m_14139_(-1.0E-7D, startVec.f_82481_, endVec.f_82481_);
/*     */       
/*  73 */       int blockX = Mth.m_14107_(endX);
/*  74 */       int blockY = Mth.m_14107_(endY);
/*  75 */       int blockZ = Mth.m_14107_(endZ);
/*     */       
/*  77 */       BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(blockX, blockY, blockZ);
/*  78 */       T t = hitFunction.apply(context, mutablePos);
/*  79 */       if (t != null) {
/*  80 */         return t;
/*     */       }
/*     */       
/*  83 */       double deltaX = startX - endX;
/*  84 */       double deltaY = startY - endY;
/*  85 */       double deltaZ = startZ - endZ;
/*  86 */       int signX = Mth.m_14205_(deltaX);
/*  87 */       int signY = Mth.m_14205_(deltaY);
/*  88 */       int signZ = Mth.m_14205_(deltaZ);
/*  89 */       double d9 = (signX == 0) ? Double.MAX_VALUE : (signX / deltaX);
/*  90 */       double d10 = (signY == 0) ? Double.MAX_VALUE : (signY / deltaY);
/*  91 */       double d11 = (signZ == 0) ? Double.MAX_VALUE : (signZ / deltaZ);
/*  92 */       double d12 = d9 * ((signX > 0) ? (1.0D - Mth.m_14185_(endX)) : Mth.m_14185_(endX));
/*  93 */       double d13 = d10 * ((signY > 0) ? (1.0D - Mth.m_14185_(endY)) : Mth.m_14185_(endY));
/*  94 */       double d14 = d11 * ((signZ > 0) ? (1.0D - Mth.m_14185_(endZ)) : Mth.m_14185_(endZ));
/*     */       
/*  96 */       while (d12 <= 1.0D || d13 <= 1.0D || d14 <= 1.0D) {
/*  97 */         if (d12 < d13) {
/*  98 */           if (d12 < d14) {
/*  99 */             blockX += signX;
/* 100 */             d12 += d9;
/*     */           } else {
/* 102 */             blockZ += signZ;
/* 103 */             d14 += d11;
/*     */           } 
/* 105 */         } else if (d13 < d14) {
/* 106 */           blockY += signY;
/* 107 */           d13 += d10;
/*     */         } else {
/* 109 */           blockZ += signZ;
/* 110 */           d14 += d11;
/*     */         } 
/*     */         
/* 113 */         T t1 = hitFunction.apply(context, mutablePos.m_122178_(blockX, blockY, blockZ));
/* 114 */         if (t1 != null) {
/* 115 */           return t1;
/*     */         }
/*     */       } 
/*     */     } 
/* 119 */     return missFactory.apply(context);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\block\BlockRayTrace.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */