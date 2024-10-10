/*     */ package com.tacz.guns.block.entity;
/*     */ import com.tacz.guns.block.StatueBlock;
/*     */ import com.tacz.guns.init.ModBlocks;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ 
/*     */ public class StatueBlockEntity extends BlockEntity {
/*  23 */   public static final BlockEntityType<StatueBlockEntity> TYPE = BlockEntityType.Builder.m_155273_(StatueBlockEntity::new, new Block[] { (Block)ModBlocks.STATUE.get() }).m_58966_(null);
/*     */   private static final String ITEM_TAG = "Item";
/*  25 */   private ItemStack gunItem = ItemStack.f_41583_;
/*     */   
/*     */   public StatueBlockEntity(BlockPos pPos, BlockState pBlockState) {
/*  28 */     super(TYPE, pPos, pBlockState);
/*     */   }
/*     */   
/*     */   public static void clientTick(Level level, BlockPos blockPos, BlockState state, StatueBlockEntity statueBlockEntity) {
/*  32 */     if (level.m_46467_() % 100L == 0L && !statueBlockEntity.gunItem.m_41619_()) {
/*  33 */       Direction direction = (Direction)state.m_61143_((Property)StatueBlock.FACING);
/*     */       
/*  35 */       double x = blockPos.m_123341_() + direction.m_122429_() * 0.75D + 0.5D;
/*  36 */       double z = blockPos.m_123343_() + direction.m_122431_() * 0.75D + 0.5D;
/*     */       
/*  38 */       double dx = -0.02D + level.f_46441_.m_188500_() * 0.04D;
/*  39 */       double dz = -0.02D + level.f_46441_.m_188500_() * 0.04D;
/*  40 */       double dy = -0.02D + level.f_46441_.m_188500_() * 0.04D;
/*     */       
/*  42 */       level.m_7106_((ParticleOptions)ParticleTypes.f_123810_, x, blockPos.m_123342_() + 2.25D, z, dx, dy, dz);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack getGunItem() {
/*  47 */     return this.gunItem;
/*     */   }
/*     */   
/*     */   public void setGun(ItemStack stack) {
/*  51 */     dropItem();
/*  52 */     this.gunItem = stack.m_41777_();
/*  53 */     if (this.f_58857_ != null) {
/*  54 */       BlockState state = this.f_58857_.m_8055_(this.f_58858_);
/*  55 */       this.f_58857_.m_7260_(this.f_58858_, state, state, 3);
/*     */     } 
/*  57 */     m_6596_();
/*     */   }
/*     */   
/*     */   public void dropItem() {
/*  61 */     if (!this.gunItem.m_41619_() && this.f_58857_ != null) {
/*  62 */       Direction direction = (Direction)m_58900_().m_61143_((Property)StatueBlock.FACING);
/*  63 */       Block.m_49840_(this.f_58857_, this.f_58858_.m_121945_(direction).m_7494_(), this.gunItem);
/*  64 */       this.gunItem = ItemStack.f_41583_;
/*  65 */       if (this.f_58857_ != null) {
/*  66 */         BlockState state = this.f_58857_.m_8055_(this.f_58858_);
/*  67 */         this.f_58857_.m_7260_(this.f_58858_, state, state, 3);
/*     */       } 
/*  69 */       m_6596_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/*  75 */     super.m_142466_(tag);
/*  76 */     if (tag.m_128425_("Item", 10)) {
/*  77 */       this.gunItem = ItemStack.m_41712_(tag.m_128469_("Item"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/*  83 */     super.m_183515_(tag);
/*  84 */     tag.m_128365_("Item", (Tag)this.gunItem.m_41739_(new CompoundTag()));
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag m_5995_() {
/*  89 */     CompoundTag tag = super.m_5995_();
/*  90 */     tag.m_128365_("Item", (Tag)this.gunItem.m_41739_(new CompoundTag()));
/*  91 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public AABB getRenderBoundingBox() {
/*  96 */     return new AABB(this.f_58858_.m_7918_(-2, 0, -2), this.f_58858_.m_7918_(2, 2, 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_58483_() {
/* 101 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.m_195640_(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\entity\StatueBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */