/*     */ package com.tacz.guns.compat.playeranimator.animation;
/*     */ 
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.event.common.GunDrawEvent;
/*     */ import com.tacz.guns.api.event.common.GunMeleeEvent;
/*     */ import com.tacz.guns.api.event.common.GunReloadEvent;
/*     */ import com.tacz.guns.api.event.common.GunShootEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*     */ import dev.kosmx.playerAnim.api.layered.IAnimation;
/*     */ import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
/*     */ import dev.kosmx.playerAnim.api.layered.ModifierLayer;
/*     */ import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
/*     */ import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
/*     */ import dev.kosmx.playerAnim.core.util.Ease;
/*     */ import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
/*     */ import net.minecraft.client.player.AbstractClientPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ 
/*     */ public class AnimationManager {
/*     */   public static boolean hasPlayerAnimator3rd(ClientGunIndex gunIndex) {
/*  28 */     ResourceLocation location = gunIndex.getPlayerAnimator3rd();
/*  29 */     if (location == null) {
/*  30 */       return false;
/*     */     }
/*  32 */     return PlayerAnimatorAssetManager.INSTANCE.containsKey(location);
/*     */   }
/*     */   
/*     */   public static boolean isFlying(AbstractClientPlayer player) {
/*  36 */     return (!player.m_20096_() && (player.m_150110_()).f_35935_);
/*     */   }
/*     */   
/*     */   public static void playRotationAnimation(AbstractClientPlayer player, ClientGunIndex gunIndex) {
/*  40 */     String animationName = "empty";
/*  41 */     ResourceLocation dataId = PlayerAnimatorCompat.ROTATION_ANIMATION;
/*  42 */     ResourceLocation animator3rd = gunIndex.getPlayerAnimator3rd();
/*  43 */     if (animator3rd == null) {
/*     */       return;
/*     */     }
/*  46 */     if (!PlayerAnimatorAssetManager.INSTANCE.containsKey(animator3rd)) {
/*     */       return;
/*     */     }
/*  49 */     PlayerAnimatorAssetManager.INSTANCE.getAnimations(animator3rd, animationName).ifPresent(keyframeAnimation -> {
/*     */           PlayerAnimationAccess.PlayerAssociatedAnimationData associatedData = PlayerAnimationAccess.getPlayerAssociatedData(player);
/*     */           ModifierLayer<IAnimation> modifierLayer = (ModifierLayer<IAnimation>)associatedData.get(dataId);
/*     */           if (modifierLayer == null) {
/*     */             return;
/*     */           }
/*     */           AbstractFadeModifier fadeModifier = AbstractFadeModifier.standardFadeIn(8, Ease.INOUTSINE);
/*     */           modifierLayer.replaceAnimationWithFade(fadeModifier, (IAnimation)new KeyframeAnimationPlayer(keyframeAnimation));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void playLowerAnimation(AbstractClientPlayer player, ClientGunIndex gunIndex, float limbSwingAmount) {
/*  62 */     if (isPlayerLie(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  66 */     if (player.m_20202_() != null) {
/*  67 */       playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "ride_lower");
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     if (isFlying(player)) {
/*  72 */       playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "hold_lower");
/*     */       return;
/*     */     } 
/*  75 */     if (player.m_20142_()) {
/*  76 */       if (player.m_20089_() == Pose.CROUCHING) {
/*  77 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "crouch_walk_lower");
/*     */       } else {
/*  79 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "run_lower");
/*     */       } 
/*     */       return;
/*     */     } 
/*  83 */     if (limbSwingAmount > 0.05D) {
/*  84 */       if (player.m_20089_() == Pose.CROUCHING) {
/*  85 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "crouch_walk_lower");
/*     */       } else {
/*  87 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "walk_lower");
/*     */       } 
/*     */       return;
/*     */     } 
/*  91 */     if (player.m_20089_() == Pose.CROUCHING) {
/*  92 */       playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "crouch_lower");
/*     */     } else {
/*  94 */       playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOWER_ANIMATION, "hold_lower");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void playLoopUpperAnimation(AbstractClientPlayer player, ClientGunIndex gunIndex, float limbSwingAmount) {
/*  99 */     IGunOperator operator = IGunOperator.fromLivingEntity((LivingEntity)player);
/* 100 */     float aimingProgress = operator.getSynAimingProgress();
/* 101 */     if (aimingProgress <= 0.0F) {
/*     */       
/* 103 */       if (!isFlying(player) && player.m_20142_()) {
/* 104 */         if (isPlayerLie(player)) {
/* 105 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "lie_move");
/* 106 */         } else if (player.m_20089_() == Pose.CROUCHING) {
/* 107 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "crouch_walk_upper");
/*     */         } else {
/* 109 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "run_upper");
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 115 */       if (!isFlying(player) && limbSwingAmount > 0.05D) {
/* 116 */         if (isPlayerLie(player)) {
/* 117 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "lie_move");
/* 118 */         } else if (player.m_20089_() == Pose.CROUCHING) {
/* 119 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "crouch_walk_upper");
/*     */         } else {
/* 121 */           playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "walk_upper");
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 126 */       if (isPlayerLie(player)) {
/*     */         
/* 128 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "lie");
/*     */       } else {
/*     */         
/* 131 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "hold_upper");
/*     */       }
/*     */     
/* 134 */     } else if (isPlayerLie(player)) {
/*     */       
/* 136 */       if (!isFlying(player) && limbSwingAmount > 0.05D) {
/* 137 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "lie_move");
/*     */       } else {
/* 139 */         playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "lie_aim");
/*     */       } 
/*     */     } else {
/*     */       
/* 143 */       playLoopAnimation(player, gunIndex, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION, "aim_upper");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void playLoopAnimation(AbstractClientPlayer player, ClientGunIndex gunIndex, ResourceLocation dataId, String animationName) {
/* 150 */     ResourceLocation animator3rd = gunIndex.getPlayerAnimator3rd();
/* 151 */     if (animator3rd == null) {
/*     */       return;
/*     */     }
/* 154 */     if (!PlayerAnimatorAssetManager.INSTANCE.containsKey(animator3rd)) {
/*     */       return;
/*     */     }
/* 157 */     PlayerAnimatorAssetManager.INSTANCE.getAnimations(animator3rd, animationName).ifPresent(keyframeAnimation -> {
/*     */           PlayerAnimationAccess.PlayerAssociatedAnimationData associatedData = PlayerAnimationAccess.getPlayerAssociatedData(player); ModifierLayer<IAnimation> modifierLayer = (ModifierLayer<IAnimation>)associatedData.get(dataId);
/*     */           if (modifierLayer == null)
/*     */             return; 
/*     */           IAnimation patt7937$temp = modifierLayer.getAnimation();
/*     */           if (patt7937$temp instanceof KeyframeAnimationPlayer) {
/*     */             KeyframeAnimationPlayer animationPlayer = (KeyframeAnimationPlayer)patt7937$temp;
/*     */             if (animationPlayer.isActive()) {
/*     */               Object extraDataName = (animationPlayer.getData()).extraData.get("name");
/*     */               if (extraDataName instanceof String) {
/*     */                 String name = (String)extraDataName;
/*     */                 if (!animationName.equals(name)) {
/*     */                   AbstractFadeModifier abstractFadeModifier = AbstractFadeModifier.standardFadeIn(8, Ease.INOUTSINE);
/*     */                   modifierLayer.replaceAnimationWithFade(abstractFadeModifier, (IAnimation)new KeyframeAnimationPlayer(keyframeAnimation));
/*     */                 } 
/*     */               } 
/*     */               return;
/*     */             } 
/*     */           } 
/*     */           AbstractFadeModifier fadeModifier = AbstractFadeModifier.standardFadeIn(8, Ease.INOUTSINE);
/*     */           modifierLayer.replaceAnimationWithFade(fadeModifier, (IAnimation)new KeyframeAnimationPlayer(keyframeAnimation));
/* 178 */         }); } public static void playOnceAnimation(AbstractClientPlayer player, ClientGunIndex gunIndex, ResourceLocation dataId, String animationName) { ResourceLocation animator3rd = gunIndex.getPlayerAnimator3rd();
/* 179 */     if (animator3rd == null) {
/*     */       return;
/*     */     }
/* 182 */     if (!PlayerAnimatorAssetManager.INSTANCE.containsKey(animator3rd)) {
/*     */       return;
/*     */     }
/* 185 */     PlayerAnimatorAssetManager.INSTANCE.getAnimations(animator3rd, animationName).ifPresent(keyframeAnimation -> {
/*     */           PlayerAnimationAccess.PlayerAssociatedAnimationData associatedData = PlayerAnimationAccess.getPlayerAssociatedData(player);
/*     */           ModifierLayer<IAnimation> modifierLayer = (ModifierLayer<IAnimation>)associatedData.get(dataId);
/*     */           if (modifierLayer == null) {
/*     */             return;
/*     */           }
/*     */           IAnimation animation = modifierLayer.getAnimation();
/*     */           if (animation == null || !animation.isActive()) {
/*     */             AbstractFadeModifier fadeModifier = AbstractFadeModifier.standardFadeIn(8, Ease.INOUTSINE);
/*     */             modifierLayer.replaceAnimationWithFade(fadeModifier, (IAnimation)new KeyframeAnimationPlayer(keyframeAnimation));
/*     */           } 
/*     */         }); }
/*     */ 
/*     */   
/*     */   public static void stopAllAnimation(AbstractClientPlayer player) {
/* 200 */     stopAnimation(player, PlayerAnimatorCompat.LOWER_ANIMATION);
/* 201 */     stopAnimation(player, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION);
/* 202 */     stopAnimation(player, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION);
/* 203 */     stopAnimation(player, PlayerAnimatorCompat.ROTATION_ANIMATION);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void stopAnimation(AbstractClientPlayer player, ResourceLocation dataId) {
/* 208 */     PlayerAnimationAccess.PlayerAssociatedAnimationData associatedData = PlayerAnimationAccess.getPlayerAssociatedData(player);
/* 209 */     ModifierLayer<IAnimation> modifierLayer = (ModifierLayer<IAnimation>)associatedData.get(dataId);
/* 210 */     if (modifierLayer != null && modifierLayer.isActive()) {
/* 211 */       AbstractFadeModifier fadeModifier = AbstractFadeModifier.standardFadeIn(8, Ease.INOUTSINE);
/* 212 */       modifierLayer.replaceAnimationWithFade(fadeModifier, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isPlayerLie(AbstractClientPlayer player) {
/* 218 */     return (!player.m_6069_() && player.m_20089_() == Pose.SWIMMING);
/*     */   }
/*     */   @SubscribeEvent
/*     */   public void onFire(GunShootEvent event) {
/*     */     AbstractClientPlayer player;
/* 223 */     if (event.getLogicalSide().isServer()) {
/*     */       return;
/*     */     }
/* 226 */     LivingEntity shooter = event.getShooter();
/* 227 */     if (shooter instanceof AbstractClientPlayer) { player = (AbstractClientPlayer)shooter; }
/*     */     else
/*     */     { return; }
/* 230 */      ItemStack gunItemStack = event.getGunItemStack();
/* 231 */     IGun iGun = IGun.getIGunOrNull(gunItemStack);
/* 232 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 235 */     TimelessAPI.getClientGunIndex(iGun.getGunId(gunItemStack)).ifPresent(index -> {
/*     */           IGunOperator operator = IGunOperator.fromLivingEntity((LivingEntity)player);
/*     */           float aimingProgress = operator.getSynAimingProgress();
/*     */           if (aimingProgress <= 0.0F) {
/*     */             if (isPlayerLie(player)) {
/*     */               playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "lie_normal_fire");
/*     */             } else {
/*     */               playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "normal_fire_upper");
/*     */             } 
/*     */           } else if (isPlayerLie(player)) {
/*     */             playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "lie_aim_fire");
/*     */           } else {
/*     */             playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "aim_fire_upper");
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onReload(GunReloadEvent event) {
/*     */     AbstractClientPlayer player;
/* 256 */     if (event.getLogicalSide().isServer()) {
/*     */       return;
/*     */     }
/* 259 */     LivingEntity shooter = event.getEntity();
/* 260 */     if (shooter instanceof AbstractClientPlayer) { player = (AbstractClientPlayer)shooter; }
/*     */     else
/*     */     { return; }
/* 263 */      ItemStack gunItemStack = event.getGunItemStack();
/* 264 */     IGun iGun = IGun.getIGunOrNull(gunItemStack);
/* 265 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 268 */     TimelessAPI.getClientGunIndex(iGun.getGunId(gunItemStack)).ifPresent(index -> {
/*     */           if (isPlayerLie(player)) {
/*     */             playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "lie_reload");
/*     */           } else {
/*     */             playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, "reload_upper");
/*     */           } 
/*     */         });
/*     */   }
/*     */   @SubscribeEvent
/*     */   public void onMelee(GunMeleeEvent event) {
/*     */     AbstractClientPlayer player;
/* 279 */     if (event.getLogicalSide().isServer()) {
/*     */       return;
/*     */     }
/* 282 */     LivingEntity shooter = event.getShooter();
/* 283 */     if (shooter instanceof AbstractClientPlayer) { player = (AbstractClientPlayer)shooter; }
/*     */     else
/*     */     { return; }
/* 286 */      ItemStack gunItemStack = event.getGunItemStack();
/* 287 */     IGun iGun = IGun.getIGunOrNull(gunItemStack);
/* 288 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 291 */     int randomIndex = shooter.m_217043_().m_188503_(3);
/* 292 */     switch (randomIndex) { case 0: 
/*     */       case 1: 
/*     */       default:
/* 295 */         break; }  String animationName = "melee_3_upper";
/*     */     
/* 297 */     TimelessAPI.getClientGunIndex(iGun.getGunId(gunItemStack)).ifPresent(index -> playOnceAnimation(player, index, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION, animationName));
/*     */   }
/*     */   @SubscribeEvent
/*     */   public void onDraw(GunDrawEvent event) {
/*     */     AbstractClientPlayer player;
/* 302 */     if (event.getLogicalSide().isServer()) {
/*     */       return;
/*     */     }
/* 305 */     LivingEntity entity = event.getEntity();
/* 306 */     if (entity instanceof AbstractClientPlayer) { player = (AbstractClientPlayer)entity; }
/*     */     else
/*     */     { return; }
/* 309 */      ItemStack currentGunItem = event.getCurrentGunItem();
/* 310 */     ItemStack previousGunItem = event.getPreviousGunItem();
/*     */     
/* 312 */     if (currentGunItem.m_41720_() instanceof IGun && previousGunItem.m_41720_() instanceof IGun) {
/* 313 */       stopAnimation(player, PlayerAnimatorCompat.LOOP_UPPER_ANIMATION);
/* 314 */       stopAnimation(player, PlayerAnimatorCompat.ONCE_UPPER_ANIMATION);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\animation\AnimationManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */