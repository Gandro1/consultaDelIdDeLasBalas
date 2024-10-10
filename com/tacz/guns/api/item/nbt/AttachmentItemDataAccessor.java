/*    */ package com.tacz.guns.api.item.nbt;
/*    */ 
/*    */ import com.tacz.guns.api.DefaultAssets;
/*    */ import com.tacz.guns.api.item.IAttachment;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public interface AttachmentItemDataAccessor
/*    */   extends IAttachment
/*    */ {
/*    */   public static final String ATTACHMENT_ID_TAG = "AttachmentId";
/*    */   public static final String SKIN_ID_TAG = "Skin";
/*    */   public static final String ZOOM_NUMBER_TAG = "ZoomNumber";
/*    */   
/*    */   @Nonnull
/*    */   static ResourceLocation getAttachmentIdFromTag(@Nullable CompoundTag nbt) {
/* 21 */     if (nbt == null) {
/* 22 */       return DefaultAssets.EMPTY_ATTACHMENT_ID;
/*    */     }
/* 24 */     if (nbt.m_128425_("AttachmentId", 8)) {
/* 25 */       ResourceLocation attachmentId = ResourceLocation.m_135820_(nbt.m_128461_("AttachmentId"));
/* 26 */       return Objects.<ResourceLocation>requireNonNullElse(attachmentId, DefaultAssets.EMPTY_ATTACHMENT_ID);
/*    */     } 
/* 28 */     return DefaultAssets.EMPTY_ATTACHMENT_ID;
/*    */   }
/*    */   
/*    */   static int getZoomNumberFromTag(@Nullable CompoundTag nbt) {
/* 32 */     if (nbt == null) {
/* 33 */       return 0;
/*    */     }
/* 35 */     if (nbt.m_128425_("ZoomNumber", 3)) {
/* 36 */       return nbt.m_128451_("ZoomNumber");
/*    */     }
/* 38 */     return 0;
/*    */   }
/*    */   
/*    */   static void setZoomNumberToTag(CompoundTag nbt, int zoomNumber) {
/* 42 */     nbt.m_128405_("ZoomNumber", zoomNumber);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   default ResourceLocation getAttachmentId(ItemStack attachmentStack) {
/* 48 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 49 */     return getAttachmentIdFromTag(nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   default void setAttachmentId(ItemStack attachmentStack, @Nullable ResourceLocation attachmentId) {
/* 54 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 55 */     if (attachmentId != null) {
/* 56 */       nbt.m_128359_("AttachmentId", attachmentId.toString());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default ResourceLocation getSkinId(ItemStack attachmentStack) {
/* 63 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 64 */     if (nbt.m_128425_("Skin", 8)) {
/* 65 */       return ResourceLocation.m_135820_(nbt.m_128461_("Skin"));
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   default void setSkinId(ItemStack attachmentStack, @Nullable ResourceLocation skinId) {
/* 72 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 73 */     if (skinId != null) {
/* 74 */       nbt.m_128359_("Skin", skinId.toString());
/*    */     } else {
/* 76 */       nbt.m_128473_("Skin");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   default int getZoomNumber(ItemStack attachmentStack) {
/* 82 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 83 */     return getZoomNumberFromTag(nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   default void setZoomNumber(ItemStack attachmentStack, int zoomNumber) {
/* 88 */     CompoundTag nbt = attachmentStack.m_41784_();
/* 89 */     setZoomNumberToTag(nbt, zoomNumber);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\nbt\AttachmentItemDataAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */