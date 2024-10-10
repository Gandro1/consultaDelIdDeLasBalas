/*    */ package com.tacz.guns.network.message.event;
/*    */ 
/*    */ import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageGunHurt
/*    */ {
/*    */   private final int bulletId;
/*    */   private final int hurtEntityId;
/*    */   private final int attackerId;
/*    */   private final ResourceLocation gunId;
/*    */   private final float amount;
/*    */   private final boolean isHeadShot;
/*    */   private final float headshotMultiplier;
/*    */   
/*    */   public ServerMessageGunHurt(int bulletId, int hurtEntityId, int attackerId, ResourceLocation gunId, float amount, boolean isHeadShot, float headshotMultiplier) {
/* 29 */     this.bulletId = bulletId;
/* 30 */     this.hurtEntityId = hurtEntityId;
/* 31 */     this.attackerId = attackerId;
/* 32 */     this.gunId = gunId;
/* 33 */     this.amount = amount;
/* 34 */     this.isHeadShot = isHeadShot;
/* 35 */     this.headshotMultiplier = headshotMultiplier;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageGunHurt message, FriendlyByteBuf buf) {
/* 39 */     buf.writeInt(message.bulletId);
/* 40 */     buf.writeInt(message.hurtEntityId);
/* 41 */     buf.writeInt(message.attackerId);
/* 42 */     buf.m_130085_(message.gunId);
/* 43 */     buf.writeFloat(message.amount);
/* 44 */     buf.writeBoolean(message.isHeadShot);
/* 45 */     buf.writeFloat(message.headshotMultiplier);
/*    */   }
/*    */   
/*    */   public static ServerMessageGunHurt decode(FriendlyByteBuf buf) {
/* 49 */     int bulletId = buf.readInt();
/* 50 */     int hurtEntityId = buf.readInt();
/* 51 */     int attackerId = buf.readInt();
/* 52 */     ResourceLocation gunId = buf.m_130281_();
/* 53 */     float amount = buf.readFloat();
/* 54 */     boolean isHeadShot = buf.readBoolean();
/* 55 */     float headshotMultiplier = buf.readFloat();
/* 56 */     return new ServerMessageGunHurt(bulletId, hurtEntityId, attackerId, gunId, amount, isHeadShot, headshotMultiplier);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageGunHurt message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 60 */     NetworkEvent.Context context = contextSupplier.get();
/* 61 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 62 */       context.enqueueWork(() -> onHurt(message));
/*    */     }
/* 64 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void onHurt(ServerMessageGunHurt message) {
/* 69 */     ClientLevel level = (Minecraft.m_91087_()).f_91073_;
/* 70 */     if (level == null) {
/*    */       return;
/*    */     }
/* 73 */     Entity bullet = level.m_6815_(message.bulletId);
/* 74 */     Entity hurtEntity = level.m_6815_(message.hurtEntityId);
/* 75 */     Entity entity1 = level.m_6815_(message.attackerId); LivingEntity livingEntity = (LivingEntity)entity1, attacker = (entity1 instanceof LivingEntity) ? livingEntity : null;
/* 76 */     MinecraftForge.EVENT_BUS.post((Event)new EntityHurtByGunEvent.Post(bullet, hurtEntity, attacker, message.gunId, message.amount, null, message.isHeadShot, message.headshotMultiplier, LogicalSide.CLIENT));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\event\ServerMessageGunHurt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */