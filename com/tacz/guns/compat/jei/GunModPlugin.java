/*    */ package com.tacz.guns.compat.jei;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.tacz.guns.api.item.gun.GunItemManager;
/*    */ import com.tacz.guns.compat.jei.category.AttachmentQueryCategory;
/*    */ import com.tacz.guns.compat.jei.category.GunSmithTableCategory;
/*    */ import com.tacz.guns.compat.jei.entry.AttachmentQueryEntry;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import mezz.jei.api.IModPlugin;
/*    */ import mezz.jei.api.JeiPlugin;
/*    */ import mezz.jei.api.constants.VanillaTypes;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import mezz.jei.api.registration.IRecipeCatalystRegistration;
/*    */ import mezz.jei.api.registration.IRecipeCategoryRegistration;
/*    */ import mezz.jei.api.registration.IRecipeRegistration;
/*    */ import mezz.jei.api.registration.ISubtypeRegistration;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ @JeiPlugin
/*    */ public class GunModPlugin implements IModPlugin {
/* 22 */   private static final ResourceLocation UID = new ResourceLocation("tacz", "jei");
/*    */ 
/*    */   
/*    */   public void registerCategories(IRecipeCategoryRegistration registration) {
/* 26 */     registration.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new GunSmithTableCategory(registration.getJeiHelpers().getGuiHelper()) });
/* 27 */     registration.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new AttachmentQueryCategory(registration.getJeiHelpers().getGuiHelper()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerRecipes(IRecipeRegistration registration) {
/* 32 */     registration.addRecipes(GunSmithTableCategory.GUN_SMITH_TABLE, Lists.newArrayList(TimelessAPI.getAllRecipes().values()));
/* 33 */     registration.addRecipes(AttachmentQueryCategory.ATTACHMENT_QUERY, AttachmentQueryEntry.getAllAttachmentQueryEntries());
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
/* 38 */     registration.addRecipeCatalyst(((Item)ModItems.GUN_SMITH_TABLE.get()).m_7968_(), new RecipeType[] { GunSmithTableCategory.GUN_SMITH_TABLE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerItemSubtypes(ISubtypeRegistration registration) {
/* 43 */     registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, ModItems.AMMO.get(), GunModSubtype.getAmmoSubtype());
/* 44 */     registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, ModItems.ATTACHMENT.get(), GunModSubtype.getAttachmentSubtype());
/* 45 */     registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, ModItems.AMMO_BOX.get(), GunModSubtype.getAmmoBoxSubtype());
/* 46 */     GunItemManager.getAllGunItems().forEach(item -> registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, item.get(), GunModSubtype.getGunSubtype()));
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getPluginUid() {
/* 51 */     return UID;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\jei\GunModPlugin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */