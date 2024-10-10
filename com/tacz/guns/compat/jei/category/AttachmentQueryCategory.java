/*    */ package com.tacz.guns.compat.jei.category;
/*    */ import com.tacz.guns.compat.jei.entry.AttachmentQueryEntry;
/*    */ import com.tacz.guns.init.ModCreativeTabs;
/*    */ import java.util.List;
/*    */ import mezz.jei.api.constants.VanillaTypes;
/*    */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*    */ import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
/*    */ import mezz.jei.api.gui.drawable.IDrawable;
/*    */ import mezz.jei.api.gui.drawable.IDrawableStatic;
/*    */ import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
/*    */ import mezz.jei.api.helpers.IGuiHelper;
/*    */ import mezz.jei.api.ingredients.IIngredientType;
/*    */ import mezz.jei.api.recipe.IFocusGroup;
/*    */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.CreativeModeTab;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class AttachmentQueryCategory implements IRecipeCategory<AttachmentQueryEntry> {
/* 25 */   public static final RecipeType<AttachmentQueryEntry> ATTACHMENT_QUERY = RecipeType.create("tacz", "attachment_query", AttachmentQueryEntry.class);
/*    */   public static final int MAX_GUN_SHOW_COUNT = 60;
/* 27 */   private static final Component TITLE = (Component)Component.m_237115_("jei.tacz.attachment_query.title");
/*    */   private final IDrawableStatic bgDraw;
/*    */   private final IDrawable slotDraw;
/*    */   private final IDrawable iconDraw;
/*    */   
/*    */   public AttachmentQueryCategory(IGuiHelper guiHelper) {
/* 33 */     this.bgDraw = guiHelper.createBlankDrawable(160, 145);
/* 34 */     this.slotDraw = (IDrawable)guiHelper.getSlotDrawable();
/* 35 */     this.iconDraw = guiHelper.createDrawableIngredient((IIngredientType)VanillaTypes.ITEM_STACK, ((CreativeModeTab)ModCreativeTabs.ATTACHMENT_SCOPE_TAB.get()).m_40787_());
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw(AttachmentQueryEntry entry, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
/* 40 */     List<ItemStack> extraAllowGunStacks = entry.getExtraAllowGunStacks();
/* 41 */     if (!extraAllowGunStacks.isEmpty()) {
/* 42 */       Font font = (Minecraft.m_91087_()).f_91062_;
/* 43 */       guiGraphics.m_280614_(font, (Component)Component.m_237115_("jei.tacz.attachment_query.more"), 128, 134, 5592405, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipe(IRecipeLayoutBuilder builder, AttachmentQueryEntry entry, IFocusGroup focuses) {
/* 49 */     ItemStack attachmentStack = entry.getAttachmentStack();
/* 50 */     List<ItemStack> allowGunStacks = entry.getAllowGunStacks();
/* 51 */     List<ItemStack> extraAllowGunStacks = entry.getExtraAllowGunStacks();
/*    */ 
/*    */     
/* 54 */     ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.OUTPUT, 72, 0).addItemStack(attachmentStack)).setBackground(this.slotDraw, -1, -1);
/*    */ 
/*    */     
/* 57 */     int xOffset = 0;
/* 58 */     int yOffset = 20;
/* 59 */     for (int i = 0; i < allowGunStacks.size(); i++) {
/* 60 */       int column = i % 9;
/* 61 */       int row = i / 9;
/* 62 */       xOffset = column * 18;
/* 63 */       yOffset = 20 + row * 18;
/* 64 */       ItemStack gun = allowGunStacks.get(i);
/* 65 */       ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, xOffset, yOffset).addItemStack(gun)).setBackground(this.slotDraw, -1, -1);
/*    */     } 
/*    */ 
/*    */     
/* 69 */     if (!extraAllowGunStacks.isEmpty()) {
/* 70 */       ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, xOffset + 18, yOffset).addItemStacks(extraAllowGunStacks)).setBackground(this.slotDraw, -1, -1);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeType<AttachmentQueryEntry> getRecipeType() {
/* 76 */     return ATTACHMENT_QUERY;
/*    */   }
/*    */ 
/*    */   
/*    */   public Component getTitle() {
/* 81 */     return TITLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDrawable getBackground() {
/* 86 */     return (IDrawable)this.bgDraw;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDrawable getIcon() {
/* 91 */     return this.iconDraw;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\jei\category\AttachmentQueryCategory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */