/*    */ package com.tacz.guns.client.animation.screen;
/*    */ 
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class RefitTransform
/*    */ {
/*    */   private static final float REFIT_SCREEN_TRANSFORM_TIMES = 0.25F;
/* 19 */   private static float refitScreenTransformProgress = 1.0F;
/* 20 */   private static long refitScreenTransformTimestamp = -1L;
/* 21 */   private static AttachmentType oldTransformType = AttachmentType.NONE;
/* 22 */   private static AttachmentType currentTransformType = AttachmentType.NONE;
/* 23 */   private static float refitScreenOpeningProgress = 0.0F;
/* 24 */   private static long refitScreenOpeningTimestamp = -1L;
/*    */   
/*    */   public static void init() {
/* 27 */     refitScreenTransformProgress = 1.0F;
/* 28 */     refitScreenTransformTimestamp = System.currentTimeMillis();
/* 29 */     oldTransformType = AttachmentType.NONE;
/* 30 */     currentTransformType = AttachmentType.NONE;
/*    */   }
/*    */   
/*    */   public static float getOpeningProgress() {
/* 34 */     return refitScreenOpeningProgress;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public static AttachmentType getOldTransformType() {
/* 39 */     return Objects.<AttachmentType>requireNonNullElse(oldTransformType, AttachmentType.NONE);
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public static AttachmentType getCurrentTransformType() {
/* 44 */     return Objects.<AttachmentType>requireNonNullElse(currentTransformType, AttachmentType.NONE);
/*    */   }
/*    */   
/*    */   public static float getTransformProgress() {
/* 48 */     return refitScreenTransformProgress;
/*    */   }
/*    */   
/*    */   public static boolean changeRefitScreenView(AttachmentType attachmentType) {
/* 52 */     if (refitScreenTransformProgress != 1.0F || refitScreenOpeningProgress != 1.0F) {
/* 53 */       return false;
/*    */     }
/* 55 */     oldTransformType = currentTransformType;
/* 56 */     currentTransformType = attachmentType;
/* 57 */     refitScreenTransformProgress = 0.0F;
/* 58 */     refitScreenTransformTimestamp = System.currentTimeMillis();
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void tickInterpolation(TickEvent.RenderTickEvent event) {
/* 65 */     if (refitScreenOpeningTimestamp == -1L) {
/* 66 */       refitScreenOpeningTimestamp = System.currentTimeMillis();
/*    */     }
/* 68 */     if ((Minecraft.m_91087_()).f_91080_ instanceof com.tacz.guns.client.gui.GunRefitScreen) {
/* 69 */       refitScreenOpeningProgress += (float)(System.currentTimeMillis() - refitScreenOpeningTimestamp) / 250.0F;
/* 70 */       if (refitScreenOpeningProgress > 1.0F) {
/* 71 */         refitScreenOpeningProgress = 1.0F;
/*    */       }
/*    */     } else {
/* 74 */       refitScreenOpeningProgress -= (float)(System.currentTimeMillis() - refitScreenOpeningTimestamp) / 250.0F;
/* 75 */       if (refitScreenOpeningProgress < 0.0F) {
/* 76 */         refitScreenOpeningProgress = 0.0F;
/*    */       }
/*    */     } 
/* 79 */     refitScreenOpeningTimestamp = System.currentTimeMillis();
/*    */     
/* 81 */     if (refitScreenTransformTimestamp == -1L) {
/* 82 */       refitScreenTransformTimestamp = System.currentTimeMillis();
/*    */     }
/* 84 */     refitScreenTransformProgress += (float)(System.currentTimeMillis() - refitScreenTransformTimestamp) / 250.0F;
/* 85 */     if (refitScreenTransformProgress > 1.0F) {
/* 86 */       refitScreenTransformProgress = 1.0F;
/*    */     }
/* 88 */     refitScreenTransformTimestamp = System.currentTimeMillis();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\animation\screen\RefitTransform.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */