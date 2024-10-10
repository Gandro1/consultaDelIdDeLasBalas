/*    */ package com.tacz.guns.network.message.event;
/*    */ 
/*    */ import com.tacz.guns.api.event.common.EntityKillByGunEvent;
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
/*    */ public class ServerMessageGunKill
/*    */ {
/*    */   private final int bulletId;
/*    */   private final int killEntityId;
/*    */   private final int attackerId;
/*    */   private final ResourceLocation gunId;
/*    */   private final boolean isHeadShot;
/*    */   private final float baseDamage;
/*    */   private final float headshotMultiplier;
/*    */   
/*    */   public ServerMessageGunKill(int bulletId, int killEntityId, int attackerId, ResourceLocation gunId, float baseDamage, boolean isHeadShot, float headshotMultiplier) {
/* 29 */     this.bulletId = bulletId;
/* 30 */     this.killEntityId = killEntityId;
/* 31 */     this.attackerId = attackerId;
/* 32 */     this.gunId = gunId;
/* 33 */     this.baseDamage = baseDamage;
/* 34 */     this.isHeadShot = isHeadShot;
/* 35 */     this.headshotMultiplier = headshotMultiplier;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageGunKill message, FriendlyByteBuf buf) {
/* 39 */     buf.writeInt(message.bulletId);
/* 40 */     buf.writeInt(message.killEntityId);
/* 41 */     buf.writeInt(message.attackerId);
/* 42 */     buf.m_130085_(message.gunId);
/* 43 */     buf.writeFloat(message.baseDamage);
/* 44 */     buf.writeBoolean(message.isHeadShot);
/* 45 */     buf.writeFloat(message.headshotMultiplier);
/*    */   }
/*    */   
/*    */   public static ServerMessageGunKill decode(FriendlyByteBuf buf) {
/* 49 */     int bulletId = buf.readInt();
/* 50 */     int killEntityId = buf.readInt();
/* 51 */     int attackerId = buf.readInt();
/* 52 */     ResourceLocation gunId = buf.m_130281_();
/* 53 */     float baseDamage = buf.readFloat();
/* 54 */     boolean isHeadShot = buf.readBoolean();
/* 55 */     float headshotMultiplier = buf.readFloat();
/* 56 */     return new ServerMessageGunKill(bulletId, killEntityId, attackerId, gunId, baseDamage, isHeadShot, headshotMultiplier);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageGunKill message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 60 */     NetworkEvent.Context context = contextSupplier.get();
/* 61 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 62 */       context.enqueueWork(() -> onKill(message));
/*    */     }
/* 64 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void onKill(ServerMessageGunKill message) {
/* 69 */     ClientLevel level = (Minecraft.m_91087_()).f_91073_;
/* 70 */     if (level == null) {
/*    */       return;
/*    */     }
/* 73 */     Entity bullet = level.m_6815_(message.bulletId);
/* 74 */     Entity entity1 = level.m_6815_(message.killEntityId); LivingEntity livingEntity = (LivingEntity)entity1, killedEntity = (entity1 instanceof LivingEntity) ? livingEntity : null;
/* 75 */     Entity entity2 = level.m_6815_(message.attackerId); LivingEntity livingEntity1 = (LivingEntity)entity2, attacker = (entity2 instanceof LivingEntity) ? livingEntity1 : null;
/* 76 */     MinecraftForge.EVENT_BUS.post((Event)new EntityKillByGunEvent(bullet, killedEntity, attacker, message.gunId, message.baseDamage, null, message.isHeadShot, message.headshotMultiplier, LogicalSide.CLIENT));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\event\ServerMessageGunKill.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */