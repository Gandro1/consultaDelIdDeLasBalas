/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.api.client.event.BeforeRenderHandEvent;
/*    */ import com.tacz.guns.api.client.other.KeepingItemRenderer;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.client.renderer.ItemInHandRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ItemInHandRenderer.class})
/*    */ public class ItemInHandRendererMixin
/*    */   implements KeepingItemRenderer
/*    */ {
/*    */   @Shadow
/*    */   private float f_109302_;
/*    */   @Shadow
/*    */   private float f_109303_;
/*    */   @Shadow
/*    */   private ItemStack f_109300_;
/*    */   
/*    */   @Inject(method = {"renderHandsWithItems"}, at = {@At("HEAD")})
/*    */   public void beforeHandRender(float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource.BufferSource pBuffer, LocalPlayer pPlayerEntity, int pCombinedLight, CallbackInfo ci) {
/* 37 */     MinecraftForge.EVENT_BUS.post((Event)new BeforeRenderHandEvent(pMatrixStack)); } @Unique
/*    */   private ItemStack tacz$KeepItem; @Unique
/*    */   private long tacz$KeepTimeMs; @Unique
/*    */   private long tacz$KeepTimestamp; @Inject(method = {"tick"}, at = {@At("HEAD")})
/*    */   public void cancelEquippedProgress(CallbackInfo ci) {
/* 42 */     if ((Minecraft.m_91087_()).f_91074_ == null) {
/*    */       return;
/*    */     }
/* 45 */     if (this.tacz$KeepItem != null) {
/* 46 */       long time = System.currentTimeMillis() - this.tacz$KeepTimestamp;
/* 47 */       if (time < this.tacz$KeepTimeMs) {
/* 48 */         this.f_109302_ = 1.0F;
/* 49 */         this.f_109303_ = 1.0F;
/* 50 */         this.f_109300_ = this.tacz$KeepItem;
/*    */         return;
/*    */       } 
/*    */     } 
/* 54 */     ItemStack itemStack = (Minecraft.m_91087_()).f_91074_.m_21205_();
/* 55 */     IGun iGun = IGun.getIGunOrNull(itemStack);
/* 56 */     if (iGun != null) {
/* 57 */       this.f_109302_ = 1.0F;
/* 58 */       this.f_109303_ = 1.0F;
/* 59 */       this.f_109300_ = itemStack;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Unique
/*    */   public void keep(ItemStack itemStack, long timeMs) {
/* 66 */     long time = System.currentTimeMillis() - this.tacz$KeepTimestamp;
/* 67 */     if (time < this.tacz$KeepTimeMs) {
/*    */       return;
/*    */     }
/* 70 */     this.tacz$KeepItem = itemStack;
/* 71 */     this.f_109300_ = itemStack;
/* 72 */     this.tacz$KeepTimeMs = timeMs;
/* 73 */     this.tacz$KeepTimestamp = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getCurrentItem() {
/* 78 */     return this.f_109300_;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\ItemInHandRendererMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */