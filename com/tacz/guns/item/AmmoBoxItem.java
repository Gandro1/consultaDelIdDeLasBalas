/*     */ package com.tacz.guns.item;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.api.item.IAmmoBox;
/*     */ import com.tacz.guns.api.item.builder.AmmoItemBuilder;
/*     */ import com.tacz.guns.api.item.nbt.AmmoBoxItemDataAccessor;
/*     */ import com.tacz.guns.config.sync.SyncConfig;
/*     */ import com.tacz.guns.init.ModItems;
/*     */ import com.tacz.guns.inventory.tooltip.AmmoBoxTooltip;
/*     */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.SlotAccess;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.ClickAction;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.item.CreativeModeTab;
/*     */ import net.minecraft.world.item.DyeableLeatherItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class AmmoBoxItem extends Item implements DyeableLeatherItem, AmmoBoxItemDataAccessor {
/*  38 */   public static final ResourceLocation PROPERTY_NAME = new ResourceLocation("tacz", "ammo_statue");
/*     */   
/*     */   public static final int IRON_LEVEL = 0;
/*     */   
/*     */   public static final int GOLD_LEVEL = 1;
/*     */   
/*     */   public static final int DIAMOND_LEVEL = 2;
/*     */   
/*     */   private static final String DISPLAY_TAG = "display";
/*     */   private static final String COLOR_TAG = "color";
/*     */   private static final int OPEN = 0;
/*     */   private static final int CLOSE = 1;
/*     */   private static final int CREATIVE_INDEX = 6;
/*     */   private static final int ALL_TYPE_CREATIVE_INDEX = 8;
/*     */   
/*     */   public AmmoBoxItem() {
/*  54 */     super((new Item.Properties()).m_41487_(1));
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static int getColor(ItemStack stack, int tintIndex) {
/*  59 */     return (tintIndex > 0) ? -1 : getTagColor(stack);
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static float getStatue(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
/*  64 */     int openStatue = 0;
/*  65 */     int ammoLevel = 0;
/*  66 */     Item item = stack.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item;
/*  67 */       if (iAmmoBox.isAllTypeCreative(stack)) {
/*  68 */         return 8.0F;
/*     */       }
/*  70 */       openStatue = getOpenStatue(stack, iAmmoBox);
/*  71 */       if (iAmmoBox.isCreative(stack)) {
/*  72 */         return (openStatue + 6);
/*     */       }
/*  74 */       ammoLevel = getLevelStatue(stack, iAmmoBox); }
/*     */     
/*  76 */     return (openStatue + 2 * ammoLevel);
/*     */   }
/*     */   
/*     */   private static int getOpenStatue(ItemStack stack, IAmmoBox iAmmoBox) {
/*  80 */     boolean idIsEmpty = iAmmoBox.getAmmoId(stack).equals(DefaultAssets.EMPTY_AMMO_ID);
/*  81 */     boolean countIsZero = (iAmmoBox.getAmmoCount(stack) <= 0);
/*  82 */     if (idIsEmpty || countIsZero) {
/*  83 */       return 0;
/*     */     }
/*  85 */     return 1;
/*     */   }
/*     */   
/*     */   private static int getLevelStatue(ItemStack stack, IAmmoBox iAmmoBox) {
/*  89 */     return iAmmoBox.getAmmoLevel(stack);
/*     */   }
/*     */   
/*     */   private static int getTagColor(ItemStack stack) {
/*  93 */     CompoundTag compoundtag = stack.m_41737_("display");
/*  94 */     return (compoundtag != null && compoundtag.m_128425_("color", 99)) ? compoundtag.m_128451_("color") : 7503211;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142305_(ItemStack stack, ItemStack pOther, Slot slot, ClickAction action, Player player, SlotAccess access) {
/*  99 */     return super.m_142305_(stack, pOther, slot, action, player, access);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_142207_(ItemStack ammoBox, Slot slot, ClickAction action, Player player) {
/* 105 */     if (action == ClickAction.SECONDARY) {
/*     */       
/* 107 */       ItemStack slotItem = slot.m_7993_();
/* 108 */       ResourceLocation boxAmmoId = getAmmoId(ammoBox);
/*     */ 
/*     */       
/* 111 */       if (slotItem.m_41619_()) {
/*     */         
/* 113 */         if (isAllTypeCreative(ammoBox) || isCreative(ammoBox)) {
/* 114 */           return false;
/*     */         }
/*     */         
/* 117 */         if (boxAmmoId.equals(DefaultAssets.EMPTY_AMMO_ID)) {
/* 118 */           return false;
/*     */         }
/*     */         
/* 121 */         int boxAmmoCount = getAmmoCount(ammoBox);
/* 122 */         if (boxAmmoCount <= 0) {
/* 123 */           return false;
/*     */         }
/* 125 */         TimelessAPI.getCommonAmmoIndex(boxAmmoId).ifPresent(index -> {
/*     */               int takeCount = Math.min(index.getStackSize(), boxAmmoCount);
/*     */               
/*     */               ItemStack takeAmmo = AmmoItemBuilder.create().setId(boxAmmoId).setCount(takeCount).build();
/*     */               slot.m_150659_(takeAmmo);
/*     */               int remainCount = boxAmmoCount - takeCount;
/*     */               setAmmoCount(ammoBox, remainCount);
/*     */               if (remainCount <= 0) {
/*     */                 setAmmoId(ammoBox, DefaultAssets.EMPTY_AMMO_ID);
/*     */               }
/*     */               playRemoveOneSound((Entity)player);
/*     */             });
/* 137 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 141 */       Item item = slotItem.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/*     */         
/* 143 */         if (isAllTypeCreative(ammoBox)) {
/* 144 */           return false;
/*     */         }
/* 146 */         ResourceLocation slotAmmoId = iAmmo.getAmmoId(slotItem);
/*     */         
/* 148 */         if (slotAmmoId.equals(DefaultAssets.EMPTY_AMMO_ID)) {
/* 149 */           return false;
/*     */         }
/*     */         
/* 152 */         if (boxAmmoId.equals(DefaultAssets.EMPTY_AMMO_ID)) {
/* 153 */           setAmmoId(ammoBox, slotAmmoId);
/* 154 */         } else if (!slotAmmoId.equals(boxAmmoId)) {
/* 155 */           return false;
/*     */         } 
/* 157 */         TimelessAPI.getCommonAmmoIndex(slotAmmoId).ifPresent(index -> {
/*     */               if (isCreative(ammoBox)) {
/*     */                 setAmmoCount(ammoBox, 2147483647);
/*     */                 
/*     */                 return;
/*     */               } 
/*     */               
/*     */               int boxAmmoCount = getAmmoCount(ammoBox);
/*     */               int boxLevelMultiplier = getAmmoLevel(ammoBox) + 1;
/*     */               int maxSize = index.getStackSize() * ((Integer)SyncConfig.AMMO_BOX_STACK_SIZE.get()).intValue() * boxLevelMultiplier;
/*     */               int needCount = maxSize - boxAmmoCount;
/*     */               ItemStack takeItem = slot.m_150647_(slotItem.m_41613_(), needCount, player);
/*     */               setAmmoCount(ammoBox, boxAmmoCount + takeItem.m_41613_());
/*     */             });
/* 171 */         playInsertSound((Entity)player);
/* 172 */         return true; }
/*     */     
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   private void playRemoveOneSound(Entity entity) {
/* 179 */     entity.m_5496_(SoundEvents.f_184216_, 0.8F, 0.8F + entity.m_9236_().m_213780_().m_188501_() * 0.4F);
/*     */   }
/*     */   
/*     */   private void playInsertSound(Entity entity) {
/* 183 */     entity.m_5496_(SoundEvents.f_184215_, 0.8F, 0.8F + entity.m_9236_().m_213780_().m_188501_() * 0.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(ItemStack stack) {
/* 188 */     if (isAllTypeCreative(stack) || isCreative(stack)) {
/* 189 */       return false;
/*     */     }
/* 191 */     return (!getAmmoId(stack).equals(DefaultAssets.EMPTY_AMMO_ID) && getAmmoCount(stack) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(ItemStack stack) {
/* 196 */     ResourceLocation ammoId = getAmmoId(stack);
/* 197 */     int ammoCount = getAmmoCount(stack);
/* 198 */     int boxLevelMultiplier = getAmmoLevel(stack) + 1;
/*     */ 
/*     */ 
/*     */     
/* 202 */     double widthPercent = ((Double)TimelessAPI.getCommonAmmoIndex(ammoId).map(index -> { double totalCount = (index.getStackSize() * ((Integer)SyncConfig.AMMO_BOX_STACK_SIZE.get()).intValue() * boxLevelMultiplier); return Double.valueOf(ammoCount / totalCount); }).orElse(Double.valueOf(0.0D))).doubleValue();
/* 203 */     return (int)Math.min(1.0D + 12.0D * widthPercent, 13.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public Component m_7626_(ItemStack stack) {
/* 208 */     if (isAllTypeCreative(stack)) {
/* 209 */       return (Component)Component.m_237115_("item.tacz.ammo_box.all_type_creative").m_130940_(ChatFormatting.DARK_PURPLE);
/*     */     }
/* 211 */     if (isCreative(stack)) {
/* 212 */       return (Component)Component.m_237115_("item.tacz.ammo_box.creative").m_130940_(ChatFormatting.DARK_PURPLE);
/*     */     }
/* 214 */     int ammoLevel = getAmmoLevel(stack);
/* 215 */     switch (ammoLevel) {
/*     */       case 1:
/* 217 */         return (Component)Component.m_237115_("item.tacz.ammo_box.gold").m_130940_(ChatFormatting.YELLOW);
/*     */       
/*     */       case 2:
/* 220 */         return (Component)Component.m_237115_("item.tacz.ammo_box.diamond").m_130940_(ChatFormatting.AQUA);
/*     */     } 
/*     */     
/* 223 */     return (Component)Component.m_237115_("item.tacz.ammo_box.iron");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_5812_(ItemStack stack) {
/* 230 */     if (isAllTypeCreative(stack) || isCreative(stack)) {
/* 231 */       return true;
/*     */     }
/* 233 */     return super.m_5812_(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(ItemStack stack) {
/* 238 */     return Mth.m_14169_(0.33333334F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void fillItemCategory(CreativeModeTab.Output output) {
/* 242 */     ItemStack ammoBox = ((Item)ModItems.AMMO_BOX.get()).m_7968_();
/* 243 */     Item item = ammoBox.m_41720_(); if (item instanceof IAmmoBox) { IAmmoBox iAmmoBox = (IAmmoBox)item;
/*     */       
/* 245 */       output.m_246342_(iAmmoBox.setAmmoLevel(ammoBox.m_41777_(), 0));
/* 246 */       output.m_246342_(iAmmoBox.setAmmoLevel(ammoBox.m_41777_(), 1));
/* 247 */       output.m_246342_(iAmmoBox.setAmmoLevel(ammoBox.m_41777_(), 2));
/*     */ 
/*     */       
/* 250 */       output.m_246342_(iAmmoBox.setCreative(ammoBox.m_41777_(), false));
/* 251 */       output.m_246342_(iAmmoBox.setCreative(ammoBox.m_41777_(), true)); }
/*     */   
/*     */   }
/*     */   
/*     */   public Optional<TooltipComponent> m_142422_(ItemStack stack) {
/*     */     IAmmoBox iAmmoBox;
/* 257 */     Item item = stack.m_41720_(); if (item instanceof IAmmoBox) { iAmmoBox = (IAmmoBox)item; }
/* 258 */     else { return Optional.empty(); }
/*     */     
/* 260 */     ResourceLocation ammoId = iAmmoBox.getAmmoId(stack);
/* 261 */     if (ammoId.equals(DefaultAssets.EMPTY_AMMO_ID)) {
/* 262 */       return Optional.empty();
/*     */     }
/* 264 */     int ammoCount = iAmmoBox.getAmmoCount(stack);
/* 265 */     if (ammoCount <= 0) {
/* 266 */       return Optional.empty();
/*     */     }
/* 268 */     ItemStack ammoStack = AmmoItemBuilder.create().setId(ammoId).build();
/* 269 */     return (Optional)Optional.of(new AmmoBoxTooltip(stack, ammoStack, ammoCount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(ItemStack stack, @Nullable Level pLevel, List<Component> components, TooltipFlag isAdvanced) {
/* 274 */     if (isAllTypeCreative(stack)) {
/* 275 */       components.add(Component.m_237115_("tooltip.tacz.ammo_box.usage.all_type_creative").m_130940_(ChatFormatting.GOLD));
/*     */       return;
/*     */     } 
/* 278 */     if (isCreative(stack)) {
/* 279 */       components.add(Component.m_237115_("tooltip.tacz.ammo_box.usage.creative.1").m_130940_(ChatFormatting.YELLOW));
/* 280 */       components.add(Component.m_237115_("tooltip.tacz.ammo_box.usage.creative.2").m_130940_(ChatFormatting.YELLOW));
/*     */       return;
/*     */     } 
/* 283 */     components.add(Component.m_237115_("tooltip.tacz.ammo_box.usage.deposit").m_130940_(ChatFormatting.GRAY));
/* 284 */     components.add(Component.m_237115_("tooltip.tacz.ammo_box.usage.remove").m_130940_(ChatFormatting.GRAY));
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\AmmoBoxItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */