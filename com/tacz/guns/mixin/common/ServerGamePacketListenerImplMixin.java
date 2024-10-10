/*    */ package com.tacz.guns.mixin.common;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.tacz.guns.api.entity.IGunOperator;
/*    */ import com.tacz.guns.api.entity.ReloadState;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.server.network.ServerGamePacketListenerImpl;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ 
/*    */ @Mixin({ServerGamePacketListenerImpl.class})
/*    */ public class ServerGamePacketListenerImplMixin {
/*    */   @WrapOperation(method = {"handlePlayerCommand"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setSprinting(Z)V")})
/*    */   public void cancelSprintCommand(ServerPlayer player, boolean sprint, Operation<Void> original) {
/* 16 */     IGunOperator gunOperator = IGunOperator.fromLivingEntity((LivingEntity)player);
/* 17 */     boolean isAiming = gunOperator.getSynIsAiming();
/* 18 */     ReloadState.StateType reloadStateType = gunOperator.getSynReloadState().getStateType();
/* 19 */     if (isAiming || (reloadStateType.isReloading() && !reloadStateType.isReloadFinishing())) {
/* 20 */       original.call(new Object[] { player, Boolean.valueOf(false) });
/*    */     } else {
/* 22 */       original.call(new Object[] { player, Boolean.valueOf(sprint) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\common\ServerGamePacketListenerImplMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */