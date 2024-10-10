/*    */ package com.tacz.guns.api.item.builder;
/*    */ import com.tacz.guns.api.DefaultAssets;
/*    */ import com.tacz.guns.api.item.IAttachment;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class AttachmentItemBuilder {
/* 10 */   private int count = 1;
/* 11 */   private ResourceLocation attachmentId = DefaultAssets.DEFAULT_ATTACHMENT_ID;
/* 12 */   private ResourceLocation skinId = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AttachmentItemBuilder create() {
/* 18 */     return new AttachmentItemBuilder();
/*    */   }
/*    */   
/*    */   public AttachmentItemBuilder setCount(int count) {
/* 22 */     this.count = Math.max(count, 1);
/* 23 */     return this;
/*    */   }
/*    */   
/*    */   public AttachmentItemBuilder setId(ResourceLocation id) {
/* 27 */     this.attachmentId = id;
/* 28 */     return this;
/*    */   }
/*    */   
/*    */   public AttachmentItemBuilder setSkinId(ResourceLocation skinId) {
/* 32 */     this.skinId = skinId;
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   public ItemStack build() {
/* 37 */     ItemStack attachment = new ItemStack((ItemLike)ModItems.ATTACHMENT.get(), this.count);
/* 38 */     Item item = attachment.m_41720_(); if (item instanceof IAttachment) { IAttachment iAttachment = (IAttachment)item;
/* 39 */       iAttachment.setAttachmentId(attachment, this.attachmentId);
/* 40 */       iAttachment.setSkinId(attachment, this.skinId); }
/*    */     
/* 42 */     return attachment;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\builder\AttachmentItemBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */