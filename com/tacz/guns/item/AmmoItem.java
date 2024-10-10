/*    */ package com.tacz.guns.item;
/*    */ 
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IAmmo;
/*    */ import com.tacz.guns.api.item.builder.AmmoItemBuilder;
/*    */ import com.tacz.guns.api.item.nbt.AmmoItemDataAccessor;
/*    */ import com.tacz.guns.client.renderer.item.AmmoItemRenderer;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*    */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*    */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*    */ import net.minecraft.core.NonNullList;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class AmmoItem extends Item implements AmmoItemDataAccessor {
/*    */   public AmmoItem() {
/* 35 */     super((new Item.Properties()).m_41487_(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxStackSize(ItemStack stack) {
/* 40 */     Item item = stack.m_41720_(); if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/* 41 */       return ((Integer)TimelessAPI.getCommonAmmoIndex(iAmmo.getAmmoId(stack))
/* 42 */         .map(CommonAmmoIndex::getStackSize).orElse(Integer.valueOf(1))).intValue(); }
/*    */     
/* 44 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public Component m_7626_(@Nonnull ItemStack stack) {
/* 51 */     ResourceLocation ammoId = getAmmoId(stack);
/* 52 */     Optional<ClientAmmoIndex> ammoIndex = TimelessAPI.getClientAmmoIndex(ammoId);
/* 53 */     if (ammoIndex.isPresent()) {
/* 54 */       return (Component)Component.m_237115_(((ClientAmmoIndex)ammoIndex.get()).getName());
/*    */     }
/* 56 */     return super.m_7626_(stack);
/*    */   }
/*    */   
/*    */   public static NonNullList<ItemStack> fillItemCategory() {
/* 60 */     NonNullList<ItemStack> stacks = NonNullList.m_122779_();
/* 61 */     TimelessAPI.getAllCommonAmmoIndex().forEach(entry -> {
/*    */           ItemStack itemStack = AmmoItemBuilder.create().setId((ResourceLocation)entry.getKey()).build();
/*    */           stacks.add(itemStack);
/*    */         });
/* 65 */     return stacks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
/* 70 */     consumer.accept(new IClientItemExtensions()
/*    */         {
/*    */           public BlockEntityWithoutLevelRenderer getCustomRenderer() {
/* 73 */             Minecraft minecraft = Minecraft.m_91087_();
/* 74 */             return (BlockEntityWithoutLevelRenderer)new AmmoItemRenderer(minecraft.m_167982_(), minecraft.m_167973_());
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void m_7373_(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag isAdvanced) {
/* 82 */     ResourceLocation ammoId = getAmmoId(stack);
/* 83 */     TimelessAPI.getClientAmmoIndex(ammoId).ifPresent(index -> {
/*    */           String tooltipKey = index.getTooltipKey();
/*    */           
/*    */           if (tooltipKey != null) {
/*    */             components.add(Component.m_237115_(tooltipKey).m_130940_(ChatFormatting.GRAY));
/*    */           }
/*    */         });
/* 90 */     PackInfo packInfoObject = ClientAssetManager.INSTANCE.getPackInfo(ammoId);
/* 91 */     if (packInfoObject != null) {
/* 92 */       MutableComponent component = Component.m_237115_(packInfoObject.getName()).m_130940_(ChatFormatting.BLUE).m_130940_(ChatFormatting.ITALIC);
/* 93 */       components.add(component);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\AmmoItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */