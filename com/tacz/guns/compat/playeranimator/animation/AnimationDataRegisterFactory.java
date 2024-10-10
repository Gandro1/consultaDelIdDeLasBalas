/*    */ package com.tacz.guns.compat.playeranimator.animation;
/*    */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*    */ import dev.kosmx.playerAnim.api.layered.IAnimation;
/*    */ import dev.kosmx.playerAnim.api.layered.ModifierLayer;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ 
/*    */ public class AnimationDataRegisterFactory {
/*    */   public static void registerData() {
/*  9 */     PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(PlayerAnimatorCompat.LOWER_ANIMATION, 93, player -> new ModifierLayer());
/* 10 */     PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, 94, player -> new ModifierLayer());
/* 11 */     PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, 95, player -> new ModifierLayer());
/* 12 */     PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(PlayerAnimatorCompat.ROTATION_ANIMATION, 96, player -> new ModifierLayer(null, new AbstractModifier[] { (AbstractModifier)AdjustmentYRotModifier.getModifier((Player)player) }));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\animation\AnimationDataRegisterFactory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */