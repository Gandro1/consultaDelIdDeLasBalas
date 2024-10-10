/*    */ package com.tacz.guns.item;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IAttachment;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*    */ import com.tacz.guns.api.item.nbt.AttachmentItemDataAccessor;
/*    */ import com.tacz.guns.client.renderer.item.AttachmentItemRenderer;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*    */ import com.tacz.guns.inventory.tooltip.AttachmentItemTooltip;
/*    */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*    */ import net.minecraft.core.NonNullList;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.inventory.tooltip.TooltipComponent;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*    */ 
/*    */ public class AttachmentItem extends Item implements AttachmentItemDataAccessor {
/*    */   public AttachmentItem() {
/* 30 */     super((new Item.Properties()).m_41487_(1));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public Component m_7626_(@Nonnull ItemStack stack) {
/* 37 */     ResourceLocation attachmentId = getAttachmentId(stack);
/* 38 */     Optional<ClientAttachmentIndex> attachmentIndex = TimelessAPI.getClientAttachmentIndex(attachmentId);
/* 39 */     if (attachmentIndex.isPresent()) {
/* 40 */       return (Component)Component.m_237115_(((ClientAttachmentIndex)attachmentIndex.get()).getName());
/*    */     }
/* 42 */     return super.m_7626_(stack);
/*    */   }
/*    */   
/*    */   public static NonNullList<ItemStack> fillItemCategory(AttachmentType type) {
/* 46 */     NonNullList<ItemStack> stacks = NonNullList.m_122779_();
/* 47 */     TimelessAPI.getAllCommonAttachmentIndex().forEach(entry -> {
/*    */           if (((CommonAttachmentIndex)entry.getValue()).getPojo().isHidden()) {
/*    */             return;
/*    */           }
/*    */           if (type.equals(((CommonAttachmentIndex)entry.getValue()).getType())) {
/*    */             ItemStack itemStack = AttachmentItemBuilder.create().setId((ResourceLocation)entry.getKey()).build();
/*    */             stacks.add(itemStack);
/*    */           } 
/*    */         });
/* 56 */     return stacks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
/* 61 */     consumer.accept(new IClientItemExtensions()
/*    */         {
/*    */           public BlockEntityWithoutLevelRenderer getCustomRenderer() {
/* 64 */             Minecraft minecraft = Minecraft.m_91087_();
/* 65 */             return (BlockEntityWithoutLevelRenderer)new AttachmentItemRenderer(minecraft.m_167982_(), minecraft.m_167973_());
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public AttachmentType getType(ItemStack attachmentStack) {
/* 73 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachmentStack);
/* 74 */     if (iAttachment != null) {
/* 75 */       ResourceLocation id = iAttachment.getAttachmentId(attachmentStack);
/* 76 */       return TimelessAPI.getCommonAttachmentIndex(id).map(CommonAttachmentIndex::getType).orElse(AttachmentType.NONE);
/*    */     } 
/* 78 */     return AttachmentType.NONE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<TooltipComponent> m_142422_(ItemStack stack) {
/* 84 */     return (Optional)Optional.of(new AttachmentItemTooltip(getAttachmentId(stack), getType(stack)));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\AttachmentItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */