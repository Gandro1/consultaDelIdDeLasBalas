/*    */ package com.tacz.guns.compat.playeranimator.animation;
/*    */ 
/*    */ import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
/*    */ import dev.kosmx.playerAnim.core.util.Vec3f;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Pose;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public class AdjustmentYRotModifier
/*    */   implements Function<String, Optional<AdjustmentModifier.PartModifier>> {
/*    */   private final Player player;
/*    */   
/*    */   private AdjustmentYRotModifier(Player player) {
/* 17 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<AdjustmentModifier.PartModifier> apply(String partName) {
/* 22 */     Minecraft mc = Minecraft.m_91087_();
/* 23 */     if (this.player.equals(mc.f_91074_) && mc.f_91080_ != null) {
/* 24 */       return Optional.empty();
/*    */     }
/*    */     
/* 27 */     if (this.player.m_20202_() != null && "body".equals(partName)) {
/* 28 */       return Optional.empty();
/*    */     }
/*    */     
/* 31 */     float partialTick = mc.getPartialTick();
/* 32 */     float yBodyRot = Mth.m_14189_(partialTick, this.player.f_20884_, this.player.f_20883_);
/* 33 */     float yHeadRot = Mth.m_14189_(partialTick, this.player.f_20886_, this.player.f_20885_);
/* 34 */     float xRot = Mth.m_14179_(partialTick, this.player.f_19860_, this.player.m_146909_());
/*    */     
/* 36 */     float yaw = yHeadRot - yBodyRot;
/* 37 */     yaw = Mth.m_14177_(yaw);
/* 38 */     yaw = Mth.m_14036_(yaw, -85.0F, 85.0F);
/*    */     
/* 40 */     float pitch = Mth.m_14177_(xRot);
/*    */     
/* 42 */     switch (partName) { case "body": return 
/*    */           
/* 44 */           (!this.player.m_6069_() && this.player.m_20089_() == Pose.SWIMMING) ? 
/* 45 */           Optional.<AdjustmentModifier.PartModifier>of(new AdjustmentModifier.PartModifier(new Vec3f(0.0F, 0.0F, -yaw * 0.017453292F), Vec3f.ZERO)) : 
/*    */           
/* 47 */           Optional.<AdjustmentModifier.PartModifier>of(new AdjustmentModifier.PartModifier(new Vec3f(0.0F, -yaw * 0.017453292F, 0.0F), Vec3f.ZERO));
/*    */       case "head":
/*    */       case "leftArm":
/*    */       case "rightArm":
/* 51 */        }  return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public static AdjustmentModifier getModifier(Player player) {
/* 56 */     return new AdjustmentModifier(new AdjustmentYRotModifier(player));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\animation\AdjustmentYRotModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */