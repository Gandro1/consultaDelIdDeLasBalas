/*    */ package com.tacz.guns.compat.jei.entry;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.GunTabType;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*    */ import com.tacz.guns.api.item.builder.GunItemBuilder;
/*    */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttachmentQueryEntry
/*    */ {
/*    */   private final ItemStack attachmentStack;
/*    */   private List<ItemStack> allowGunStacks;
/*    */   private List<ItemStack> extraAllowGunStacks;
/*    */   
/*    */   public AttachmentQueryEntry(ResourceLocation attachmentId, GunTabType type) {
/* 31 */     this.attachmentStack = AttachmentItemBuilder.create().setId(attachmentId).build();
/* 32 */     this.allowGunStacks = Lists.newArrayList();
/* 33 */     this.extraAllowGunStacks = Lists.newArrayList();
/* 34 */     addAllAllowGuns(type);
/* 35 */     dividedGuns();
/*    */   }
/*    */   
/*    */   public static List<AttachmentQueryEntry> getAllAttachmentQueryEntries() {
/* 39 */     List<AttachmentQueryEntry> entries = Lists.newArrayList();
/* 40 */     TimelessAPI.getAllCommonAttachmentIndex().forEach(entry -> {
/*    */           if (((CommonAttachmentIndex)entry.getValue()).getPojo().isHidden()) {
/*    */             return;
/*    */           }
/*    */           for (GunTabType tabType : GunTabType.values()) {
/*    */             AttachmentQueryEntry queryEntry = new AttachmentQueryEntry((ResourceLocation)entry.getKey(), tabType);
/*    */             if (!queryEntry.getAllowGunStacks().isEmpty()) {
/*    */               entries.add(queryEntry);
/*    */             }
/*    */           } 
/*    */         });
/* 51 */     return entries;
/*    */   }
/*    */   
/*    */   public ItemStack getAttachmentStack() {
/* 55 */     return this.attachmentStack;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getAllowGunStacks() {
/* 59 */     return this.allowGunStacks;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getExtraAllowGunStacks() {
/* 63 */     return this.extraAllowGunStacks;
/*    */   }
/*    */   
/*    */   private void addAllAllowGuns(GunTabType type) {
/* 67 */     TimelessAPI.getAllCommonGunIndex().forEach(entry -> {
/*    */           String tabType = type.name().toLowerCase(Locale.US);
/*    */           String gunType = ((CommonGunIndex)entry.getValue()).getType();
/*    */           if (tabType.equals(gunType)) {
/*    */             IGun iGun;
/*    */             ItemStack gun = GunItemBuilder.create().setId((ResourceLocation)entry.getKey()).build();
/*    */             Item patt2473$temp = gun.m_41720_();
/*    */             if (patt2473$temp instanceof IGun) {
/*    */               iGun = (IGun)patt2473$temp;
/*    */             } else {
/*    */               return;
/*    */             } 
/*    */             if (iGun.allowAttachment(gun, this.attachmentStack))
/*    */               this.allowGunStacks.add(gun); 
/*    */           } 
/*    */         }); } private void dividedGuns() {
/* 83 */     int size = this.allowGunStacks.size();
/* 84 */     if (size >= 60) {
/* 85 */       this.extraAllowGunStacks = this.allowGunStacks.subList(60, size);
/* 86 */       this.allowGunStacks = this.allowGunStacks.subList(0, 60);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\jei\entry\AttachmentQueryEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */