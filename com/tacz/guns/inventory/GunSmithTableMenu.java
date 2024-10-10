/*    */ package com.tacz.guns.inventory;
/*    */ import com.tacz.guns.crafting.GunSmithTableIngredient;
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
/*    */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.item.ItemEntity;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.extensions.IForgeMenuType;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ 
/*    */ public class GunSmithTableMenu extends AbstractContainerMenu {
/*    */   static {
/* 22 */     TYPE = IForgeMenuType.create((windowId, inv, data) -> new GunSmithTableMenu(windowId, inv));
/*    */   } public static final MenuType<GunSmithTableMenu> TYPE;
/*    */   public GunSmithTableMenu(int id, Inventory inventory) {
/* 25 */     super(TYPE, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack m_7648_(Player player, int pIndex) {
/* 30 */     return ItemStack.f_41583_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(Player player) {
/* 35 */     return player.m_6084_();
/*    */   }
/*    */   
/*    */   public void doCraft(ResourceLocation recipeId, Player player) {
/* 39 */     TimelessAPI.getRecipe(recipeId).ifPresent(recipe -> player.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\inventory\GunSmithTableMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */