/*    */ package com.tacz.guns.inventory.tooltip;
/*    */ 
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.inventory.tooltip.TooltipComponent;
/*    */ 
/*    */ public class AttachmentItemTooltip implements TooltipComponent {
/*    */   private final ResourceLocation attachmentId;
/*    */   private final AttachmentType type;
/*    */   
/*    */   public AttachmentItemTooltip(ResourceLocation attachmentId, AttachmentType type) {
/* 12 */     this.attachmentId = attachmentId;
/* 13 */     this.type = type;
/*    */   }
/*    */   
/*    */   public ResourceLocation getAttachmentId() {
/* 17 */     return this.attachmentId;
/*    */   }
/*    */   
/*    */   public AttachmentType getType() {
/* 21 */     return this.type;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\inventory\tooltip\AttachmentItemTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */