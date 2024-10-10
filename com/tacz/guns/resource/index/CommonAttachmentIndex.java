/*    */ package com.tacz.guns.resource.index;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import com.tacz.guns.resource.pojo.AttachmentIndexPOJO;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonAttachmentIndex
/*    */ {
/*    */   private AttachmentData data;
/*    */   private AttachmentType type;
/*    */   private AttachmentIndexPOJO pojo;
/*    */   
/*    */   public static CommonAttachmentIndex getInstance(ResourceLocation id, AttachmentIndexPOJO attachmentIndexPOJO) throws IllegalArgumentException {
/* 19 */     CommonAttachmentIndex index = new CommonAttachmentIndex();
/* 20 */     index.pojo = attachmentIndexPOJO;
/* 21 */     checkIndex(attachmentIndexPOJO, index);
/* 22 */     checkData(id, attachmentIndexPOJO, index);
/* 23 */     return index;
/*    */   }
/*    */   
/*    */   private static void checkIndex(AttachmentIndexPOJO attachmentIndexPOJO, CommonAttachmentIndex index) {
/* 27 */     Preconditions.checkArgument((attachmentIndexPOJO != null), "index object file is empty");
/* 28 */     Preconditions.checkArgument((attachmentIndexPOJO.getType() != null), "attachment type must be nonnull.");
/* 29 */     index.type = attachmentIndexPOJO.getType();
/*    */   }
/*    */   
/*    */   private static void checkData(ResourceLocation id, AttachmentIndexPOJO attachmentIndexPOJO, CommonAttachmentIndex index) {
/* 33 */     ResourceLocation pojoData = attachmentIndexPOJO.getData();
/* 34 */     Preconditions.checkArgument((pojoData != null), "index object missing pojoData field");
/* 35 */     AttachmentData data = CommonAssetManager.INSTANCE.getAttachmentData(pojoData);
/* 36 */     Preconditions.checkArgument((data != null), "there is no corresponding data file");
/* 37 */     index.data = data;
/*    */   }
/*    */   
/*    */   public AttachmentData getData() {
/* 41 */     return this.data;
/*    */   }
/*    */   
/*    */   public AttachmentType getType() {
/* 45 */     return this.type;
/*    */   }
/*    */   
/*    */   public AttachmentIndexPOJO getPojo() {
/* 49 */     return this.pojo;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\index\CommonAttachmentIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */