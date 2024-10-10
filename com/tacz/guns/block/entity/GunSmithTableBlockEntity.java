/*    */ package com.tacz.guns.block.entity;
/*    */ import com.tacz.guns.init.ModBlocks;
/*    */ import com.tacz.guns.inventory.GunSmithTableMenu;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*    */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class GunSmithTableBlockEntity extends BlockEntity implements MenuProvider {
/* 23 */   public static final BlockEntityType<GunSmithTableBlockEntity> TYPE = BlockEntityType.Builder.m_155273_(GunSmithTableBlockEntity::new, new Block[] { (Block)ModBlocks.GUN_SMITH_TABLE.get() }).m_58966_(null);
/*    */   
/*    */   public GunSmithTableBlockEntity(BlockPos pos, BlockState blockState) {
/* 26 */     super(TYPE, pos, blockState);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Packet<ClientGamePacketListener> m_58483_() {
/* 32 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.m_195640_(this);
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public AABB getRenderBoundingBox() {
/* 38 */     return new AABB(this.f_58858_.m_7918_(-2, 0, -2), this.f_58858_.m_7918_(2, 1, 2));
/*    */   }
/*    */ 
/*    */   
/*    */   public Component m_5446_() {
/* 43 */     return (Component)Component.m_237113_("Gun Smith Table");
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
/* 49 */     return (AbstractContainerMenu)new GunSmithTableMenu(id, inventory);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\block\entity\GunSmithTableBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */