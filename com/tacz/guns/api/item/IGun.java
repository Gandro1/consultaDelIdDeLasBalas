/*    */ package com.tacz.guns.api.item;
/*    */ 
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.api.item.gun.FireMode;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IGun
/*    */ {
/*    */   @Nullable
/*    */   static IGun getIGunOrNull(@Nullable ItemStack stack) {
/* 25 */     if (stack == null) {
/* 26 */       return null;
/*    */     }
/* 28 */     Item item = stack.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 29 */       return iGun; }
/*    */     
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static boolean mainhandHoldGun(LivingEntity livingEntity) {
/* 38 */     return livingEntity.m_21205_().m_41720_() instanceof IGun;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static FireMode getMainhandFireMode(LivingEntity livingEntity) {
/* 45 */     ItemStack mainhandItem = livingEntity.m_21205_();
/* 46 */     Item item = mainhandItem.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 47 */       return iGun.getFireMode(mainhandItem); }
/*    */     
/* 49 */     return FireMode.UNKNOWN;
/*    */   }
/*    */   
/*    */   float getAimingZoom(ItemStack paramItemStack);
/*    */   
/*    */   boolean useDummyAmmo(ItemStack paramItemStack);
/*    */   
/*    */   int getDummyAmmoAmount(ItemStack paramItemStack);
/*    */   
/*    */   void setDummyAmmoAmount(ItemStack paramItemStack, int paramInt);
/*    */   
/*    */   void addDummyAmmoAmount(ItemStack paramItemStack, int paramInt);
/*    */   
/*    */   boolean hasMaxDummyAmmo(ItemStack paramItemStack);
/*    */   
/*    */   int getMaxDummyAmmoAmount(ItemStack paramItemStack);
/*    */   
/*    */   void setMaxDummyAmmoAmount(ItemStack paramItemStack, int paramInt);
/*    */   
/*    */   boolean hasAttachmentLock(ItemStack paramItemStack);
/*    */   
/*    */   void setAttachmentLock(ItemStack paramItemStack, boolean paramBoolean);
/*    */   
/*    */   @Nonnull
/*    */   ResourceLocation getGunId(ItemStack paramItemStack);
/*    */   
/*    */   void setGunId(ItemStack paramItemStack, @Nullable ResourceLocation paramResourceLocation);
/*    */   
/*    */   int getLevel(int paramInt);
/*    */   
/*    */   int getExp(int paramInt);
/*    */   
/*    */   int getMaxLevel();
/*    */   
/*    */   int getLevel(ItemStack paramItemStack);
/*    */   
/*    */   int getExp(ItemStack paramItemStack);
/*    */   
/*    */   int getExpToNextLevel(ItemStack paramItemStack);
/*    */   
/*    */   int getExpCurrentLevel(ItemStack paramItemStack);
/*    */   
/*    */   FireMode getFireMode(ItemStack paramItemStack);
/*    */   
/*    */   void setFireMode(ItemStack paramItemStack, @Nullable FireMode paramFireMode);
/*    */   
/*    */   int getCurrentAmmoCount(ItemStack paramItemStack);
/*    */   
/*    */   void setCurrentAmmoCount(ItemStack paramItemStack, int paramInt);
/*    */   
/*    */   void reduceCurrentAmmoCount(ItemStack paramItemStack);
/*    */   
/*    */   void dropAllAmmo(Player paramPlayer, ItemStack paramItemStack);
/*    */   
/*    */   @Nonnull
/*    */   ItemStack getAttachment(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   @Nonnull
/*    */   ItemStack getBuiltinAttachment(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   @Nullable
/*    */   CompoundTag getAttachmentTag(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   @Nonnull
/*    */   ResourceLocation getBuiltInAttachmentId(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   @Nonnull
/*    */   ResourceLocation getAttachmentId(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   void installAttachment(@Nonnull ItemStack paramItemStack1, @Nonnull ItemStack paramItemStack2);
/*    */   
/*    */   void unloadAttachment(@Nonnull ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   boolean allowAttachment(ItemStack paramItemStack1, ItemStack paramItemStack2);
/*    */   
/*    */   boolean allowAttachmentType(ItemStack paramItemStack, AttachmentType paramAttachmentType);
/*    */   
/*    */   boolean hasBulletInBarrel(ItemStack paramItemStack);
/*    */   
/*    */   void setBulletInBarrel(ItemStack paramItemStack, boolean paramBoolean);
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\IGun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */