/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.tacz.guns.client.animation.third.InnerThirdPersonManager;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({HumanoidModel.class})
/*    */ public class HumanoidModelMixin<T extends LivingEntity> {
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_102808_;
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_102810_;
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_102812_;
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart f_102811_;
/*    */   
/*    */   @Inject(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = {@At("TAIL")})
/*    */   private void setRotationAnglesHead(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
/* 31 */     InnerThirdPersonManager.setRotationAnglesHead((LivingEntity)entityIn, this.f_102811_, this.f_102812_, this.f_102810_, this.f_102808_, limbSwingAmount);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\HumanoidModelMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */