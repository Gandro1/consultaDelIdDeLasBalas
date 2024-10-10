/*    */ package com.tacz.guns.api.item.builder;
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*    */ import com.tacz.guns.api.item.gun.FireMode;
/*    */ import com.tacz.guns.api.item.gun.GunItemManager;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import java.util.EnumMap;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public final class GunItemBuilder {
/* 18 */   private int count = 1;
/* 19 */   private int ammoCount = 0;
/*    */   private ResourceLocation gunId;
/* 21 */   private FireMode fireMode = FireMode.UNKNOWN;
/*    */   private boolean bulletInBarrel = false;
/* 23 */   private EnumMap<AttachmentType, ResourceLocation> attachments = Maps.newEnumMap(AttachmentType.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static GunItemBuilder create() {
/* 29 */     return new GunItemBuilder();
/*    */   }
/*    */   
/*    */   public GunItemBuilder setCount(int count) {
/* 33 */     this.count = Math.max(count, 1);
/* 34 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder setAmmoCount(int count) {
/* 38 */     this.ammoCount = Math.max(count, 0);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder setId(ResourceLocation id) {
/* 43 */     this.gunId = id;
/* 44 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder setFireMode(FireMode fireMode) {
/* 48 */     this.fireMode = fireMode;
/* 49 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder setAmmoInBarrel(boolean ammoInBarrel) {
/* 53 */     this.bulletInBarrel = ammoInBarrel;
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder putAttachment(AttachmentType type, ResourceLocation attachmentId) {
/* 58 */     this.attachments.put(type, attachmentId);
/* 59 */     return this;
/*    */   }
/*    */   
/*    */   public GunItemBuilder putAllAttachment(EnumMap<AttachmentType, ResourceLocation> attachments) {
/* 63 */     this.attachments = attachments;
/* 64 */     return this;
/*    */   }
/*    */   
/*    */   public ItemStack build() {
/* 68 */     String itemType = TimelessAPI.getCommonGunIndex(this.gunId).map(index -> index.getPojo().getItemType()).orElse(null);
/* 69 */     Preconditions.checkArgument((itemType != null), "Could not found gun id: " + this.gunId);
/*    */     
/* 71 */     RegistryObject<? extends AbstractGunItem> gunItemRegistryObject = GunItemManager.getGunItemRegistryObject(itemType);
/* 72 */     Preconditions.checkArgument((gunItemRegistryObject != null), "Could not found gun item type: " + itemType);
/*    */     
/* 74 */     ItemStack gun = new ItemStack((ItemLike)gunItemRegistryObject.get(), this.count);
/* 75 */     Item item = gun.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 76 */       iGun.setGunId(gun, this.gunId);
/* 77 */       iGun.setFireMode(gun, this.fireMode);
/* 78 */       iGun.setCurrentAmmoCount(gun, this.ammoCount);
/* 79 */       iGun.setBulletInBarrel(gun, this.bulletInBarrel);
/* 80 */       this.attachments.forEach((type, id) -> {
/*    */             ItemStack attachmentStack = AttachmentItemBuilder.create().setId(id).build();
/*    */             iGun.installAttachment(gun, attachmentStack);
/*    */           }); }
/*    */     
/* 85 */     return gun;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\builder\GunItemBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */