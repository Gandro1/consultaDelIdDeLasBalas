/*    */ package com.tacz.guns.api;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public final class DefaultAssets
/*    */ {
/*  7 */   public static ResourceLocation EMPTY_GUN_ID = new ResourceLocation("tacz", "empty");
/*    */   
/*  9 */   public static ResourceLocation DEFAULT_AMMO_ID = new ResourceLocation("tacz", "762x39");
/* 10 */   public static ResourceLocation EMPTY_AMMO_ID = new ResourceLocation("tacz", "empty");
/*    */   
/* 12 */   public static ResourceLocation DEFAULT_ATTACHMENT_ID = new ResourceLocation("tacz", "sight_sro_dot");
/* 13 */   public static ResourceLocation EMPTY_ATTACHMENT_ID = new ResourceLocation("tacz", "empty");
/*    */   
/* 15 */   public static ResourceLocation DEFAULT_ATTACHMENT_SKIN_ID = new ResourceLocation("tacz", "sight_sro_dot_blue");
/* 16 */   public static ResourceLocation EMPTY_ATTACHMENT_SKIN_ID = new ResourceLocation("tacz", "empty");
/*    */   
/*    */   public static boolean isEmptyAttachmentId(ResourceLocation attachmentId) {
/* 19 */     return EMPTY_ATTACHMENT_ID.equals(attachmentId);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\DefaultAssets.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */