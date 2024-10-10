/*    */ package com.tacz.guns.compat.jei.category;
/*    */ import com.tacz.guns.crafting.GunSmithTableIngredient;
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import mezz.jei.api.constants.VanillaTypes;
/*    */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*    */ import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
/*    */ import mezz.jei.api.gui.drawable.IDrawable;
/*    */ import mezz.jei.api.gui.drawable.IDrawableStatic;
/*    */ import mezz.jei.api.helpers.IGuiHelper;
/*    */ import mezz.jei.api.ingredients.IIngredientType;
/*    */ import mezz.jei.api.recipe.IFocusGroup;
/*    */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class GunSmithTableCategory implements IRecipeCategory<GunSmithTableRecipe> {
/* 24 */   public static final RecipeType<GunSmithTableRecipe> GUN_SMITH_TABLE = RecipeType.create("tacz", "gun_smith_table", GunSmithTableRecipe.class);
/* 25 */   private static final Component TITLE = (Component)Component.m_237115_("block.tacz.gun_smith_table");
/*    */   private final IDrawableStatic bgDraw;
/*    */   private final IDrawable slotDraw;
/*    */   private final IDrawable iconDraw;
/*    */   
/*    */   public GunSmithTableCategory(IGuiHelper guiHelper) {
/* 31 */     this.bgDraw = guiHelper.createBlankDrawable(160, 40);
/* 32 */     this.slotDraw = (IDrawable)guiHelper.getSlotDrawable();
/* 33 */     this.iconDraw = guiHelper.createDrawableIngredient((IIngredientType)VanillaTypes.ITEM_STACK, ((Item)ModItems.GUN_SMITH_TABLE.get()).m_7968_());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipe(IRecipeLayoutBuilder builder, GunSmithTableRecipe recipe, IFocusGroup focuses) {
/* 38 */     ItemStack output = recipe.getOutput();
/* 39 */     ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.OUTPUT, 3, 12).addItemStack(output)).setBackground(this.slotDraw, -1, -1);
/*    */     
/* 41 */     List<GunSmithTableIngredient> inputs = recipe.getInputs();
/* 42 */     int size = inputs.size();
/*    */     
/* 44 */     if (size < 7) {
/* 45 */       for (int i = 0; i < size; i++) {
/* 46 */         int xOffset = 35 + 20 * i;
/* 47 */         int yOffset = 12;
/* 48 */         ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, xOffset, yOffset).addItemStacks(getInput(inputs, i))).setBackground(this.slotDraw, -1, -1);
/*    */       } 
/*    */     } else {
/*    */       int i;
/*    */       
/* 53 */       for (i = 0; i < 6; i++) {
/* 54 */         int xOffset = 35 + 20 * i;
/* 55 */         int yOffset = 2;
/* 56 */         ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, xOffset, yOffset).addItemStacks(getInput(inputs, i))).setBackground(this.slotDraw, -1, -1);
/*    */       } 
/* 58 */       for (i = 6; i < size; i++) {
/* 59 */         int xOffset = 35 + 20 * (i - 6);
/* 60 */         int yOffset = 22;
/* 61 */         ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, xOffset, yOffset).addItemStacks(getInput(inputs, i))).setBackground(this.slotDraw, -1, -1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private List<ItemStack> getInput(List<GunSmithTableIngredient> inputs, int index) {
/* 67 */     if (index < inputs.size()) {
/* 68 */       GunSmithTableIngredient ingredient = inputs.get(index);
/* 69 */       ItemStack[] items = ingredient.getIngredient().m_43908_();
/* 70 */       Arrays.<ItemStack>stream(items).forEach(stack -> stack.m_41764_(ingredient.getCount()));
/* 71 */       return List.of(items);
/*    */     } 
/* 73 */     return Collections.singletonList(ItemStack.f_41583_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Component getTitle() {
/* 78 */     return TITLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDrawable getBackground() {
/* 83 */     return (IDrawable)this.bgDraw;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDrawable getIcon() {
/* 88 */     return this.iconDraw;
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeType<GunSmithTableRecipe> getRecipeType() {
/* 93 */     return GUN_SMITH_TABLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\jei\category\GunSmithTableCategory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */