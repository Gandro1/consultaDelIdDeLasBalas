/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.tacz.guns.client.gameplay.LocalPlayerDataHolder;
/*    */ import net.minecraft.client.gui.components.AbstractButton;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({AbstractButton.class})
/*    */ public class AbstractButtonMixin
/*    */ {
/*    */   @Inject(method = {"onClick(DD)V"}, at = {@At("HEAD")})
/*    */   public void onClickHead(double mouseX, double mouseY, CallbackInfo ci) {
/* 17 */     LocalPlayerDataHolder.clientClickButtonTimestamp = System.currentTimeMillis();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\AbstractButtonMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */