/*    */ package com.tacz.guns.network.message.event;
/*    */ 
/*    */ import com.tacz.guns.api.event.common.GunFireEvent;
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
/*    */ public class ServerMessageGunFire {
/*    */   private final int shooterId;
/*    */   
/*    */   public ServerMessageGunFire(int shooterId, ItemStack gunItemStack) {
/* 22 */     this.shooterId = shooterId;
/* 23 */     this.gunItemStack = gunItemStack;
/*    */   }
/*    */   private final ItemStack gunItemStack;
/*    */   public static void encode(ServerMessageGunFire message, FriendlyByteBuf buf) {
/* 27 */     buf.m_130130_(message.shooterId);
/* 28 */     buf.m_130055_(message.gunItemStack);
/*    */   }
/*    */   
/*    */   public static ServerMessageGunFire decode(FriendlyByteBuf buf) {
/* 32 */     int shooterId = buf.m_130242_();
/* 33 */     ItemStack gunItemStack = buf.m_130267_();
/* 34 */     return new ServerMessageGunFire(shooterId, gunItemStack);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageGunFire message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 38 */     NetworkEvent.Context context = contextSupplier.get();
/* 39 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 40 */       context.enqueueWork(() -> doClientEvent(message));
/*    */     }
/* 42 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void doClientEvent(ServerMessageGunFire message) {
/* 47 */     ClientLevel level = (Minecraft.m_91087_()).f_91073_;
/* 48 */     if (level == null) {
/*    */       return;
/*    */     }
/* 51 */     Entity entity = level.m_6815_(message.shooterId); if (entity instanceof LivingEntity) { LivingEntity shooter = (LivingEntity)entity;
/* 52 */       GunFireEvent gunFireEvent = new GunFireEvent(shooter, message.gunItemStack, LogicalSide.CLIENT);
/* 53 */       MinecraftForge.EVENT_BUS.post((Event)gunFireEvent); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\event\ServerMessageGunFire.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */