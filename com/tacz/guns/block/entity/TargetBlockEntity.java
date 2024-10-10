/*     */ package com.tacz.guns.block.entity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.tacz.guns.block.TargetBlock;
/*     */ import com.tacz.guns.config.common.OtherConfig;
/*     */ import com.tacz.guns.init.ModBlocks;
/*     */ import com.tacz.guns.init.ModSounds;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.Nameable;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.entity.SkullBlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ 
/*     */ public class TargetBlockEntity
/*     */   extends BlockEntity
/*     */   implements Nameable {
/*  33 */   public static final BlockEntityType<TargetBlockEntity> TYPE = BlockEntityType.Builder.m_155273_(TargetBlockEntity::new, new Block[] { (Block)ModBlocks.TARGET.get() }).m_58966_(null);
/*     */   
/*     */   private static final int RESET_TIME = 100;
/*     */   
/*     */   private static final String OWNER_TAG = "Owner";
/*     */   
/*     */   private static final String CUSTOM_NAME_TAG = "CustomName";
/*  40 */   public float rot = 0.0F;
/*  41 */   public float oRot = 0.0F;
/*     */   @Nullable
/*     */   private GameProfile owner;
/*     */   
/*     */   public TargetBlockEntity(BlockPos pos, BlockState blockState) {
/*  46 */     super(TYPE, pos, blockState);
/*     */   } @Nullable
/*     */   private Component name;
/*     */   public static void clientTick(Level level, BlockPos pos, BlockState state, TargetBlockEntity pBlockEntity) {
/*  50 */     pBlockEntity.oRot = pBlockEntity.rot;
/*  51 */     if (((Boolean)state.m_61143_((Property)TargetBlock.STAND)).booleanValue()) {
/*  52 */       pBlockEntity.rot = Math.max(pBlockEntity.rot - 18.0F, 0.0F);
/*     */     } else {
/*  54 */       pBlockEntity.rot = Math.min(pBlockEntity.rot + 45.0F, 90.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GameProfile getOwner() {
/*  60 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setOwner(@Nullable GameProfile owner) {
/*  64 */     this.owner = owner;
/*  65 */     SkullBlockEntity.m_155738_(this.owner, gameProfile -> {
/*     */           this.owner = gameProfile;
/*     */           refresh();
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/*  73 */     super.m_142466_(tag);
/*  74 */     if (tag.m_128425_("Owner", 10)) {
/*  75 */       this.owner = NbtUtils.m_129228_(tag.m_128469_("Owner"));
/*     */     }
/*  77 */     if (tag.m_128425_("CustomName", 8)) {
/*  78 */       this.name = (Component)Component.Serializer.m_130701_(tag.m_128461_("CustomName"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/*  84 */     super.m_183515_(tag);
/*  85 */     if (this.owner != null) {
/*  86 */       tag.m_128365_("Owner", (Tag)NbtUtils.m_129230_(new CompoundTag(), this.owner));
/*     */     }
/*  88 */     if (this.name != null) {
/*  89 */       tag.m_128359_("CustomName", Component.Serializer.m_130703_(this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Component m_7755_() {
/*  95 */     return (this.name != null) ? this.name : (Component)Component.m_237119_();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Component m_7770_() {
/* 101 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setCustomName(Component name) {
/* 105 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_58483_() {
/* 110 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.m_195640_(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag m_5995_() {
/* 115 */     return m_187482_();
/*     */   }
/*     */   
/*     */   public void refresh() {
/* 119 */     m_6596_();
/* 120 */     if (this.f_58857_ != null) {
/* 121 */       BlockState state = this.f_58857_.m_8055_(this.f_58858_);
/* 122 */       this.f_58857_.m_7260_(this.f_58858_, state, state, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AABB getRenderBoundingBox() {
/* 128 */     return new AABB(this.f_58858_.m_7918_(-2, 0, -2), this.f_58858_.m_7918_(2, 2, 2));
/*     */   }
/*     */   
/*     */   public void hit(Level level, BlockState state, BlockHitResult hit, boolean isUpperBlock) {
/* 132 */     if (this.f_58857_ != null && ((Boolean)state.m_61143_((Property)TargetBlock.STAND)).booleanValue()) {
/* 133 */       BlockPos blockPos = hit.m_82425_();
/*     */       
/* 135 */       if (isUpperBlock) {
/* 136 */         blockPos = blockPos.m_7495_();
/* 137 */         state = level.m_8055_(blockPos);
/*     */       } 
/* 139 */       int redstoneStrength = TargetBlock.getRedstoneStrength(hit, isUpperBlock);
/* 140 */       level.m_7731_(blockPos, (BlockState)((BlockState)state.m_61124_((Property)TargetBlock.STAND, Boolean.valueOf(false))).m_61124_((Property)TargetBlock.OUTPUT_POWER, Integer.valueOf(redstoneStrength)), 3);
/* 141 */       level.m_186460_(blockPos, state.m_60734_(), 100);
/*     */ 
/*     */       
/* 144 */       float volume = ((Integer)OtherConfig.TARGET_SOUND_DISTANCE.get()).intValue() / 16.0F;
/* 145 */       volume = Math.max(volume, 0.0F);
/* 146 */       level.m_5594_(null, blockPos, (SoundEvent)ModSounds.TARGET_HIT.get(), SoundSource.BLOCKS, volume, this.f_58857_.f_46441_.m_188501_() * 0.1F + 0.9F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\entity\TargetBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */