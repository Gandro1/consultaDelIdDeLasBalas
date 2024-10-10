/*    */ package com.tacz.guns.mixin.common;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({ServerPlayer.class})
/*    */ public class ServerPlayerMixin {
/*    */   @Inject(method = {"restoreFrom"}, at = {@At("RETURN")})
/*    */   public void initialGunOperateData(ServerPlayer pThat, boolean pKeepEverything, CallbackInfo ci) {
/* 14 */     ServerPlayer player = (ServerPlayer)this;
/* 15 */     IGunOperator.fromLivingEntity((LivingEntity)player).initialData();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\common\ServerPlayerMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */