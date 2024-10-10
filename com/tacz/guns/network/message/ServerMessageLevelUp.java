/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageLevelUp
/*    */ {
/*    */   private final ItemStack gun;
/*    */   private final int level;
/*    */   
/*    */   public ServerMessageLevelUp(ItemStack gun, int level) {
/* 18 */     this.gun = gun;
/* 19 */     this.level = level;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageLevelUp message, FriendlyByteBuf buf) {
/* 23 */     buf.writeItemStack(message.gun, true);
/* 24 */     buf.writeInt(message.level);
/*    */   }
/*    */   
/*    */   public static ServerMessageLevelUp decode(FriendlyByteBuf buf) {
/* 28 */     ItemStack gun = buf.m_130267_();
/* 29 */     int level = buf.readInt();
/* 30 */     return new ServerMessageLevelUp(gun, level);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageLevelUp message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 34 */     NetworkEvent.Context context = contextSupplier.get();
/* 35 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 36 */       context.enqueueWork(() -> onLevelUp(message));
/*    */     }
/* 38 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void onLevelUp(ServerMessageLevelUp message) {
/* 43 */     int level = message.getLevel();
/* 44 */     ItemStack gun = message.getGun();
/* 45 */     LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
/* 46 */     if (localPlayer == null) {
/*    */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getGun() {
/* 67 */     return this.gun;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 71 */     return this.level;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageLevelUp.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */