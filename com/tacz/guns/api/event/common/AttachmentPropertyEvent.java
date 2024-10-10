/*    */ package com.tacz.guns.api.event.common;
/*    */ 
/*    */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttachmentPropertyEvent
/*    */   extends Event
/*    */ {
/*    */   private final ItemStack gunItem;
/*    */   private final AttachmentCacheProperty cacheProperty;
/*    */   
/*    */   public AttachmentPropertyEvent(ItemStack gunItem, AttachmentCacheProperty attachmentProperty) {
/* 17 */     this.gunItem = gunItem;
/* 18 */     this.cacheProperty = attachmentProperty;
/*    */   }
/*    */   
/*    */   public ItemStack getGunItem() {
/* 22 */     return this.gunItem;
/*    */   }
/*    */   
/*    */   public AttachmentCacheProperty getCacheProperty() {
/* 26 */     return this.cacheProperty;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\AttachmentPropertyEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */