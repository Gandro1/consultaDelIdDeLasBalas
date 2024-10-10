/*     */ package com.tacz.guns.api.item.gun;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.entity.IGunOperator;
/*     */ import com.tacz.guns.api.item.GunTabType;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.api.item.IAmmoBox;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.builder.GunItemBuilder;
/*     */ import com.tacz.guns.client.renderer.item.GunItemRenderer;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*     */ import com.tacz.guns.resource.index.CommonGunIndex;
/*     */ import com.tacz.guns.resource.pojo.data.gun.Bolt;
/*     */ import com.tacz.guns.resource.pojo.data.gun.FeedType;
/*     */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*     */ import com.tacz.guns.util.AttachmentDataUtils;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ 
/*     */ public abstract class AbstractGunItem extends Item implements IGun {
/*     */   protected AbstractGunItem(Item.Properties pProperties) {
/*  43 */     super(pProperties);
/*     */   }
/*     */   
/*     */   private static Comparator<Map.Entry<ResourceLocation, CommonGunIndex>> idNameSort() {
/*  47 */     return Comparator.comparingInt(m -> ((CommonGunIndex)m.getValue()).getSort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canReload(LivingEntity shooter, ItemStack gunItem) {
/*  74 */     ResourceLocation gunId = getGunId(gunItem);
/*  75 */     CommonGunIndex gunIndex = TimelessAPI.getCommonGunIndex(gunId).orElse(null);
/*  76 */     if (gunIndex == null) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     int currentAmmoCount = getCurrentAmmoCount(gunItem);
/*  81 */     int maxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(gunItem, gunIndex.getGunData());
/*  82 */     if (currentAmmoCount >= maxAmmoCount) {
/*  83 */       return false;
/*     */     }
/*  85 */     if (useDummyAmmo(gunItem)) {
/*  86 */       return (getDummyAmmoAmount(gunItem) > 0);
/*     */     }
/*  88 */     return ((Boolean)shooter.getCapability(ForgeCapabilities.ITEM_HANDLER, null).map(cap -> {
/*     */           for (int i = 0; i < cap.getSlots(); i++) {
/*     */             ItemStack checkAmmoStack = cap.getStackInSlot(i); Item patt3544$temp = checkAmmoStack.m_41720_(); if (patt3544$temp instanceof IAmmo) {
/*     */               IAmmo iAmmo = (IAmmo)patt3544$temp; if (iAmmo.isAmmoOfGun(gunItem, checkAmmoStack))
/*     */                 return Boolean.valueOf(true); 
/*     */             }  Item patt3712$temp = checkAmmoStack.m_41720_(); if (patt3712$temp instanceof IAmmoBox) {
/*     */               IAmmoBox iAmmoBox = (IAmmoBox)patt3712$temp;
/*     */               if (iAmmoBox.isAmmoBoxOfGun(gunItem, checkAmmoStack))
/*     */                 return Boolean.valueOf(true); 
/*     */             } 
/*     */           } 
/*     */           return Boolean.valueOf(false);
/* 100 */         }).orElse(Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doReload(LivingEntity shooter, ItemStack gunItem, boolean loadBarrel) {
/* 110 */     ResourceLocation gunId = getGunId(gunItem);
/* 111 */     CommonGunIndex gunIndex = TimelessAPI.getCommonGunIndex(gunId).orElse(null);
/* 112 */     if (gunIndex == null) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     int maxAmmoCount = AttachmentDataUtils.getAmmoCountWithAttachment(gunItem, gunIndex.getGunData());
/* 117 */     int currentAmmoCount = getCurrentAmmoCount(gunItem);
/* 118 */     int needAmmoCount = maxAmmoCount - currentAmmoCount;
/*     */     
/* 120 */     int updatedAmmoCount = currentAmmoCount;
/* 121 */     switch (gunIndex.getGunData().getReloadData().getType()) {
/*     */       case MAGAZINE:
/* 123 */         if (IGunOperator.fromLivingEntity(shooter).needCheckAmmo()) {
/* 124 */           if (useDummyAmmo(gunItem)) {
/* 125 */             updatedAmmoCount += findAndExtractDummyAmmo(gunItem, needAmmoCount); break;
/*     */           } 
/* 127 */           updatedAmmoCount += ((Integer)shooter.getCapability(ForgeCapabilities.ITEM_HANDLER, null)
/* 128 */             .map(cap -> Integer.valueOf(findAndExtractInventoryAmmos(cap, gunItem, needAmmoCount)))
/* 129 */             .orElse(Integer.valueOf(0))).intValue();
/*     */           break;
/*     */         } 
/* 132 */         updatedAmmoCount = maxAmmoCount;
/*     */         break;
/*     */       
/*     */       case FUEL:
/* 136 */         if (IGunOperator.fromLivingEntity(shooter).needCheckAmmo()) {
/* 137 */           if (useDummyAmmo(gunItem)) {
/* 138 */             if (findAndExtractDummyAmmo(gunItem, 1) > 0)
/* 139 */               updatedAmmoCount = maxAmmoCount; 
/*     */             break;
/*     */           } 
/* 142 */           if (((Integer)shooter.getCapability(ForgeCapabilities.ITEM_HANDLER, null)
/* 143 */             .map(cap -> Integer.valueOf(findAndExtractInventoryAmmos(cap, gunItem, 1)))
/* 144 */             .orElse(Integer.valueOf(0))).intValue() > 0) {
/* 145 */             updatedAmmoCount = maxAmmoCount;
/*     */           }
/*     */           break;
/*     */         } 
/* 149 */         updatedAmmoCount = maxAmmoCount;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     finishReload(gunItem, updatedAmmoCount, loadBarrel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropAllAmmo(Player player, ItemStack gunItem) {
/* 162 */     int ammoCount = getCurrentAmmoCount(gunItem);
/* 163 */     if (ammoCount <= 0) {
/*     */       return;
/*     */     }
/* 166 */     ResourceLocation gunId = getGunId(gunItem);
/* 167 */     TimelessAPI.getCommonGunIndex(gunId).ifPresent(index -> {
/*     */           if (useDummyAmmo(gunItem)) {
/*     */             setCurrentAmmoCount(gunItem, 0);
/*     */             if (index.getGunData().getReloadData().getType().equals(FeedType.FUEL)) {
/*     */               return;
/*     */             }
/*     */             addDummyAmmoAmount(gunItem, ammoCount);
/*     */             return;
/*     */           } 
/*     */           ResourceLocation ammoId = index.getGunData().getAmmoId();
/*     */           if (player.m_7500_()) {
/*     */             int maxAmmCount = AttachmentDataUtils.getAmmoCountWithAttachment(gunItem, index.getGunData());
/*     */             setCurrentAmmoCount(gunItem, maxAmmCount);
/*     */             return;
/*     */           } 
/*     */           if (index.getGunData().getReloadData().getType().equals(FeedType.FUEL)) {
/*     */             setCurrentAmmoCount(gunItem, 0);
/*     */             return;
/*     */           } 
/*     */           TimelessAPI.getCommonAmmoIndex(ammoId).ifPresent(());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int findAndExtractInventoryAmmos(IItemHandler itemHandler, ItemStack gunItem, int needAmmoCount) {
/* 214 */     int cnt = needAmmoCount;
/*     */     
/* 216 */     for (int i = 0; i < itemHandler.getSlots(); i++) {
/* 217 */       ItemStack checkAmmoStack = itemHandler.getStackInSlot(i);
/* 218 */       Item item = checkAmmoStack.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item; if (iAmmo.isAmmoOfGun(gunItem, checkAmmoStack)) {
/* 219 */           ItemStack extractItem = itemHandler.extractItem(i, cnt, false);
/* 220 */           cnt -= extractItem.m_41613_();
/* 221 */           if (cnt <= 0)
/*     */             break; 
/*     */         }  }
/*     */       
/* 225 */       item = checkAmmoStack.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item; if (iAmmoBox.isAmmoBoxOfGun(gunItem, checkAmmoStack)) {
/* 226 */           int boxAmmoCount = iAmmoBox.getAmmoCount(checkAmmoStack);
/* 227 */           int extractCount = Math.min(boxAmmoCount, cnt);
/* 228 */           int remainCount = boxAmmoCount - extractCount;
/* 229 */           iAmmoBox.setAmmoCount(checkAmmoStack, remainCount);
/* 230 */           if (remainCount <= 0) {
/* 231 */             iAmmoBox.setAmmoId(checkAmmoStack, DefaultAssets.EMPTY_AMMO_ID);
/*     */           }
/* 233 */           cnt -= extractCount;
/* 234 */           if (cnt <= 0)
/*     */             break; 
/*     */         }  }
/*     */     
/*     */     } 
/* 239 */     return needAmmoCount - cnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int findAndExtractDummyAmmo(ItemStack gunItem, int needAmmoCount) {
/* 249 */     int dummyAmmoCount = getDummyAmmoAmount(gunItem);
/* 250 */     int extractCount = Math.min(dummyAmmoCount, needAmmoCount);
/* 251 */     addDummyAmmoAmount(gunItem, -extractCount);
/* 252 */     return extractCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finishReload(ItemStack gunItem, int ammoCount, boolean loadBarrel) {
/* 263 */     ResourceLocation gunId = getGunId(gunItem);
/* 264 */     Bolt boltType = TimelessAPI.getCommonGunIndex(gunId).map(index -> index.getGunData().getBolt()).orElse(null);
/* 265 */     setCurrentAmmoCount(gunItem, ammoCount);
/* 266 */     if (loadBarrel && (boltType == Bolt.MANUAL_ACTION || boltType == Bolt.CLOSED_BOLT)) {
/* 267 */       reduceCurrentAmmoCount(gunItem);
/* 268 */       setBulletInBarrel(gunItem, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void reloadAmmo(ItemStack gunItem, int ammoCount, boolean loadBarrel) {
/* 281 */     throw new UnsupportedOperationException("this method is deprecated, please use ‘doReload’ instead");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean allowAttachment(ItemStack gun, ItemStack attachmentItem) {
/* 289 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachmentItem);
/* 290 */     IGun iGun = IGun.getIGunOrNull(gun);
/* 291 */     if (iGun != null && iAttachment != null) {
/* 292 */       ResourceLocation gunId = iGun.getGunId(gun);
/* 293 */       ResourceLocation attachmentId = iAttachment.getAttachmentId(attachmentItem);
/* 294 */       return AllowAttachmentTagMatcher.match(gunId, attachmentId);
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean allowAttachmentType(ItemStack gun, AttachmentType type) {
/* 304 */     IGun iGun = IGun.getIGunOrNull(gun);
/* 305 */     if (iGun != null) {
/* 306 */       return ((Boolean)TimelessAPI.getCommonGunIndex(iGun.getGunId(gun)).map(gunIndex -> {
/*     */             List<AttachmentType> allowAttachments = gunIndex.getGunData().getAllowAttachments();
/*     */ 
/*     */ 
/*     */             
/*     */             return (allowAttachments == null) ? Boolean.valueOf(false) : Boolean.valueOf(allowAttachments.contains(type));
/* 312 */           }).orElse(Boolean.valueOf(false))).booleanValue();
/*     */     }
/* 314 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public Component m_7626_(@Nonnull ItemStack stack) {
/* 325 */     ResourceLocation gunId = getGunId(stack);
/* 326 */     Optional<ClientGunIndex> gunIndex = TimelessAPI.getClientGunIndex(gunId);
/* 327 */     if (gunIndex.isPresent()) {
/* 328 */       return (Component)Component.m_237115_(((ClientGunIndex)gunIndex.get()).getName());
/*     */     }
/* 330 */     return super.m_7626_(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NonNullList<ItemStack> fillItemCategory(GunTabType type) {
/* 337 */     NonNullList<ItemStack> stacks = NonNullList.m_122779_();
/* 338 */     TimelessAPI.getAllCommonGunIndex().stream().sorted(idNameSort()).forEach(entry -> {
/*     */           CommonGunIndex index = (CommonGunIndex)entry.getValue();
/*     */           
/*     */           GunData gunData = index.getGunData();
/*     */           
/*     */           String key = type.name().toLowerCase(Locale.US);
/*     */           
/*     */           String indexType = index.getType();
/*     */           
/*     */           if (key.equals(indexType)) {
/*     */             ItemStack itemStack = GunItemBuilder.create().setId((ResourceLocation)entry.getKey()).setFireMode(gunData.getFireModeSet().get(0)).setAmmoCount(gunData.getAmmoAmount()).setAmmoInBarrel(true).build();
/*     */             
/*     */             stacks.add(itemStack);
/*     */           } 
/*     */         });
/* 353 */     return stacks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
/* 361 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
/* 369 */     consumer.accept(new IClientItemExtensions()
/*     */         {
/*     */           public BlockEntityWithoutLevelRenderer getCustomRenderer() {
/* 372 */             Minecraft minecraft = Minecraft.m_91087_();
/* 373 */             return (BlockEntityWithoutLevelRenderer)new GunItemRenderer(minecraft.m_167982_(), minecraft.m_167973_());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Optional<TooltipComponent> m_142422_(ItemStack stack) {
/* 384 */     Item item = stack.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 385 */       Optional<CommonGunIndex> optional = TimelessAPI.getCommonGunIndex(getGunId(stack));
/* 386 */       if (optional.isPresent()) {
/* 387 */         CommonGunIndex gunIndex = optional.get();
/* 388 */         ResourceLocation ammoId = gunIndex.getGunData().getAmmoId();
/* 389 */         return (Optional)Optional.of(new GunTooltip(stack, iGun, ammoId, gunIndex));
/*     */       }  }
/*     */     
/* 392 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   public abstract void bolt(ItemStack paramItemStack);
/*     */   
/*     */   public abstract void shoot(ItemStack paramItemStack, Supplier<Float> paramSupplier1, Supplier<Float> paramSupplier2, boolean paramBoolean, LivingEntity paramLivingEntity);
/*     */   
/*     */   public abstract void fireSelect(ItemStack paramItemStack);
/*     */   
/*     */   public abstract void melee(LivingEntity paramLivingEntity, ItemStack paramItemStack);
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\item\gun\AbstractGunItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */