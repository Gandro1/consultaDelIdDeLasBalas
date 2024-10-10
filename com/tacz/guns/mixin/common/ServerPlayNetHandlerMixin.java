/*    */ package com.tacz.guns.mixin.common;
/*    */ 
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.ServerMessageSwapItem;
/*    */ import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.server.network.ServerGamePacketListenerImpl;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({ServerGamePacketListenerImpl.class})
/*    */ public class ServerPlayNetHandlerMixin
/*    */ {
/*    */   @Inject(method = {"handlePlayerAction"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;stopUsingItem()V")})
/*    */   public void applySwapOffhandDraw(ServerboundPlayerActionPacket packetIn, CallbackInfo ci) {
/* 21 */     this.f_9743_.f_36095_.m_38946_();
/* 22 */     NetworkHandler.sendToClientPlayer(new ServerMessageSwapItem(), (Player)this.f_9743_);
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public ServerPlayer f_9743_;
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\common\ServerPlayNetHandlerMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */