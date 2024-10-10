/*    */ package com.tacz.guns.network.message.event;
/*    */ 
/*    */ import com.tacz.guns.api.event.common.GunDrawEvent;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageGunDraw
/*    */ {
/*    */   private final int entityId;
/*    */   
/*    */   public ServerMessageGunDraw(int entityId, ItemStack previousGunItem, ItemStack currentGunItem) {
/* 23 */     this.entityId = entityId;
/* 24 */     this.previousGunItem = previousGunItem;
/* 25 */     this.currentGunItem = currentGunItem;
/*    */   }
/*    */   private final ItemStack previousGunItem; private final ItemStack currentGunItem;
/*    */   public static void encode(ServerMessageGunDraw message, FriendlyByteBuf buf) {
/* 29 */     buf.m_130130_(message.entityId);
/* 30 */     buf.m_130055_(message.previousGunItem);
/* 31 */     buf.m_130055_(message.currentGunItem);
/*    */   }
/*    */   
/*    */   public static ServerMessageGunDraw decode(FriendlyByteBuf buf) {
/* 35 */     int entityId = buf.m_130242_();
/* 36 */     ItemStack previousGunItem = buf.m_130267_();
/* 37 */     ItemStack currentGunItem = buf.m_130267_();
/* 38 */     return new ServerMessageGunDraw(entityId, previousGunItem, currentGunItem);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageGunDraw message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 42 */     NetworkEvent.Context context = contextSupplier.get();
/* 43 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 44 */       context.enqueueWork(() -> doClientEvent(message));
/*    */     }
/* 46 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void doClientEvent(ServerMessageGunDraw message) {
/* 51 */     ClientLevel level = (Minecraft.m_91087_()).f_91073_;
/* 52 */     if (level == null) {
/*    */       return;
/*    */     }
/* 55 */     Entity entity = level.m_6815_(message.entityId); if (entity instanceof LivingEntity) { LivingEntity livingEntity = (LivingEntity)entity;
/* 56 */       GunDrawEvent gunDrawEvent = new GunDrawEvent(livingEntity, message.previousGunItem, message.currentGunItem, LogicalSide.CLIENT);
/* 57 */       MinecraftForge.EVENT_BUS.post((Event)gunDrawEvent); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\event\ServerMessageGunDraw.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */