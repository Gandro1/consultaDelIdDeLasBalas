/*    */ package com.tacz.guns.crafting;
/*    */ 
/*    */ import com.tacz.guns.init.ModRecipe;
/*    */ import com.tacz.guns.resource.pojo.data.recipe.TableRecipe;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.level.Level;
/*    */ 
/*    */ public class GunSmithTableRecipe implements Recipe<Inventory> {
/*    */   private final ResourceLocation id;
/*    */   private final GunSmithTableResult result;
/*    */   private final List<GunSmithTableIngredient> inputs;
/*    */   
/*    */   public GunSmithTableRecipe(ResourceLocation id, GunSmithTableResult result, List<GunSmithTableIngredient> inputs) {
/* 22 */     this.id = id;
/* 23 */     this.result = result;
/* 24 */     this.inputs = inputs;
/*    */   }
/*    */   
/*    */   public GunSmithTableRecipe(ResourceLocation id, TableRecipe tableRecipe) {
/* 28 */     this(id, tableRecipe.getResult(), tableRecipe.getMaterials());
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean matches(Inventory playerInventory, Level level) {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public ItemStack assemble(Inventory playerInventory, RegistryAccess registryAccess) {
/* 40 */     return ItemStack.f_41583_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8004_(int pWidth, int pHeight) {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack m_8043_(RegistryAccess registryAccess) {
/* 50 */     return this.result.getResult().m_41777_();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation m_6423_() {
/* 55 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> m_7707_() {
/* 60 */     return (RecipeSerializer)ModRecipe.GUN_SMITH_TABLE_RECIPE_SERIALIZER.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeType<?> m_6671_() {
/* 65 */     return ModRecipe.GUN_SMITH_TABLE_CRAFTING;
/*    */   }
/*    */   
/*    */   public ItemStack getOutput() {
/* 69 */     return this.result.getResult();
/*    */   }
/*    */   
/*    */   public List<GunSmithTableIngredient> getInputs() {
/* 73 */     return this.inputs;
/*    */   }
/*    */   
/*    */   public GunSmithTableResult getResult() {
/* 77 */     return this.result;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\crafting\GunSmithTableRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */