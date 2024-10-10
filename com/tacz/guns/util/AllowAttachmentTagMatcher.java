/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public final class AllowAttachmentTagMatcher
/*    */ {
/*    */   private static final String TAG_PREFIX = "#";
/*    */   
/*    */   public static boolean match(ResourceLocation gunId, ResourceLocation attachmentId) {
/* 13 */     Set<String> allowAttachmentTags = CommonAssetManager.INSTANCE.getAllowAttachmentTags(gunId);
/*    */     
/* 15 */     if (allowAttachmentTags == null || allowAttachmentTags.isEmpty()) {
/* 16 */       return false;
/*    */     }
/*    */     
/* 19 */     AtomicBoolean searchSignal = new AtomicBoolean(false);
/* 20 */     treeSearch(allowAttachmentTags, attachmentId, searchSignal);
/* 21 */     return searchSignal.get();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void treeSearch(Set<String> tags, ResourceLocation attachmentId, AtomicBoolean searchSignal) {
/* 26 */     for (String tag : tags) {
/*    */       
/* 28 */       if (tag.startsWith("#")) {
/* 29 */         ResourceLocation tagId = new ResourceLocation(tag.substring("#".length()));
/* 30 */         Set<String> attachmentTags = CommonAssetManager.INSTANCE.getAttachmentTags(tagId);
/*    */         
/* 32 */         if (attachmentTags != null && !attachmentTags.isEmpty()) {
/* 33 */           treeSearch(attachmentTags, attachmentId, searchSignal);
/*    */         }
/*    */         
/*    */         continue;
/*    */       } 
/* 38 */       ResourceLocation matchAttachmentId = new ResourceLocation(tag);
/* 39 */       if (attachmentId.equals(matchAttachmentId)) {
/* 40 */         searchSignal.set(true);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\AllowAttachmentTagMatcher.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */