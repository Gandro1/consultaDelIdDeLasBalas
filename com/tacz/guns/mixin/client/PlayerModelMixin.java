/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.tacz.guns.api.client.other.KeepingItemRenderer;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.PlayerModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({PlayerModel.class})
/*    */ public class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_103374_;
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_103375_;
/*    */   
/*    */   public PlayerModelMixin(ModelPart part) {
/* 29 */     super(part);
/*    */   }
/*    */   
/*    */   @Inject(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = {@At("TAIL")})
/*    */   private void setRotationAnglesTail(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
/* 34 */     if (entityIn instanceof Player) { Player player = (Player)entityIn; }
/*    */     else
/*    */     { return; }
/*    */ 
/*    */ 
/*    */     
/* 40 */     ItemStack currentItem = KeepingItemRenderer.getRenderer().getCurrentItem();
/* 41 */     if (ageInTicks == 0.0F && IGun.getIGunOrNull(currentItem) != null) {
/* 42 */       tacz$resetAll(this.f_102811_);
/* 43 */       tacz$resetAll(this.f_102812_);
/* 44 */       this.f_103375_.m_104315_(this.f_102811_);
/* 45 */       this.f_103374_.m_104315_(this.f_102812_);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Unique
/*    */   private void tacz$resetAll(ModelPart part) {
/* 54 */     part.f_104203_ = 0.0F;
/* 55 */     part.f_104204_ = 0.0F;
/* 56 */     part.f_104205_ = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\PlayerModelMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */