/*    */ package com.tacz.guns.compat.playeranimator;
/*    */ 
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.compat.playeranimator.animation.AnimationDataRegisterFactory;
/*    */ import com.tacz.guns.compat.playeranimator.animation.AnimationManager;
/*    */ import com.tacz.guns.compat.playeranimator.animation.PlayerAnimatorAssetManager;
/*    */ import com.tacz.guns.compat.playeranimator.animation.PlayerAnimatorLoader;
/*    */ import java.io.File;
/*    */ import java.util.zip.ZipFile;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ 
/*    */ 
/*    */ public class PlayerAnimatorCompat
/*    */ {
/* 19 */   public static ResourceLocation LOWER_ANIMATION = new ResourceLocation("tacz", "lower_animation");
/* 20 */   public static ResourceLocation LOOP_UPPER_ANIMATION = new ResourceLocation("tacz", "loop_upper_animation");
/* 21 */   public static ResourceLocation ONCE_UPPER_ANIMATION = new ResourceLocation("tacz", "once_upper_animation");
/* 22 */   public static ResourceLocation ROTATION_ANIMATION = new ResourceLocation("tacz", "rotation");
/*    */   
/*    */   private static final String MOD_ID = "playeranimator";
/*    */   private static boolean INSTALLED = false;
/*    */   
/*    */   public static void init() {
/* 28 */     INSTALLED = ModList.get().isLoaded("playeranimator");
/* 29 */     if (isInstalled()) {
/* 30 */       AnimationDataRegisterFactory.registerData();
/* 31 */       MinecraftForge.EVENT_BUS.register(new AnimationManager());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean loadAnimationFromZip(ZipFile zipFile, String zipPath) {
/* 36 */     if (isInstalled()) {
/* 37 */       return PlayerAnimatorLoader.load(zipFile, zipPath);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public static void loadAnimationFromFile(File file) {
/* 43 */     if (isInstalled()) {
/* 44 */       PlayerAnimatorLoader.load(file);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void clearAllAnimationCache() {
/* 49 */     if (isInstalled()) {
/* 50 */       PlayerAnimatorAssetManager.INSTANCE.clearAll();
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean hasPlayerAnimator3rd(LivingEntity livingEntity, ClientGunIndex gunIndex) {
/* 55 */     if (isInstalled() && livingEntity instanceof AbstractClientPlayer) {
/* 56 */       return AnimationManager.hasPlayerAnimator3rd(gunIndex);
/*    */     }
/* 58 */     return false;
/*    */   }
/*    */   
/*    */   public static void stopAllAnimation(LivingEntity livingEntity) {
/* 62 */     if (isInstalled() && livingEntity instanceof AbstractClientPlayer) { AbstractClientPlayer player = (AbstractClientPlayer)livingEntity;
/* 63 */       AnimationManager.stopAllAnimation(player); }
/*    */   
/*    */   }
/*    */   
/*    */   public static void playAnimation(LivingEntity livingEntity, ClientGunIndex gunIndex, float limbSwingAmount) {
/* 68 */     if (isInstalled() && livingEntity instanceof AbstractClientPlayer) { AbstractClientPlayer player = (AbstractClientPlayer)livingEntity;
/* 69 */       AnimationManager.playLowerAnimation(player, gunIndex, limbSwingAmount);
/* 70 */       AnimationManager.playLoopUpperAnimation(player, gunIndex, limbSwingAmount);
/* 71 */       AnimationManager.playRotationAnimation(player, gunIndex); }
/*    */   
/*    */   }
/*    */   
/*    */   public static boolean isInstalled() {
/* 76 */     return INSTALLED;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\playeranimator\PlayerAnimatorCompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */