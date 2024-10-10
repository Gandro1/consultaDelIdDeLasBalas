/*    */ package com.tacz.guns.api.item;
/*    */ 
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IAttachment
/*    */ {
/*    */   @Nullable
/*    */   static IAttachment getIAttachmentOrNull(@Nullable ItemStack stack) {
/* 16 */     if (stack == null) {
/* 17 */       return null;
/*    */     }
/* 19 */     Item item = stack.m_41720_(); if (item instanceof IAttachment) { IAttachment iAttachment = (IAttachment)item;
/* 20 */       return iAttachment; }
/*    */     
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   ResourceLocation getAttachmentId(ItemStack paramItemStack);
/*    */   
/*    */   void setAttachmentId(ItemStack paramItemStack, @Nullable ResourceLocation paramResourceLocation);
/*    */   
/*    */   @Nullable
/*    */   ResourceLocation getSkinId(ItemStack paramItemStack);
/*    */   
/*    */   void setSkinId(ItemStack paramItemStack, @Nullable ResourceLocation paramResourceLocation);
/*    */   
/*    */   int getZoomNumber(ItemStack paramItemStack);
/*    */   
/*    */   void setZoomNumber(ItemStack paramItemStack, int paramInt);
/*    */   
/*    */   @Nonnull
/*    */   AttachmentType getType(ItemStack paramItemStack);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\IAttachment.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */