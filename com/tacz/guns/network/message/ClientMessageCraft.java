/*    */ package com.tacz.guns.network.message;
/*    */ import com.tacz.guns.inventory.GunSmithTableMenu;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ClientMessageCraft {
/*    */   private final ResourceLocation recipeId;
/*    */   private final int menuId;
/*    */   
/*    */   public ClientMessageCraft(ResourceLocation recipeId, int menuId) {
/* 16 */     this.recipeId = recipeId;
/* 17 */     this.menuId = menuId;
/*    */   }
/*    */   
/*    */   public static void encode(ClientMessageCraft message, FriendlyByteBuf buf) {
/* 21 */     buf.m_130085_(message.recipeId);
/* 22 */     buf.m_130130_(message.menuId);
/*    */   }
/*    */   
/*    */   public static ClientMessageCraft decode(FriendlyByteBuf buf) {
/* 26 */     return new ClientMessageCraft(buf.m_130281_(), buf.m_130242_());
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessageCraft message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 30 */     NetworkEvent.Context context = contextSupplier.get();
/* 31 */     if (context.getDirection().getReceptionSide().isServer())
/* 32 */       context.enqueueWork(() -> {
/*    */             ServerPlayer entity = context.getSender(); if (entity == null)
/*    */               return;  if (entity.f_36096_.f_38840_ == message.menuId) {
/*    */               AbstractContainerMenu patt1428$temp = entity.f_36096_;
/*    */               if (patt1428$temp instanceof GunSmithTableMenu) {
/*    */                 GunSmithTableMenu menu = (GunSmithTableMenu)patt1428$temp;
/*    */                 menu.doCraft(message.recipeId, (Player)entity);
/*    */               } 
/*    */             } 
/*    */           }); 
/* 42 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessageCraft.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */