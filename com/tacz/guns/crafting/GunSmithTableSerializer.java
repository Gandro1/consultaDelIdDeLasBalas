/*    */ package com.tacz.guns.crafting;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GunSmithTableSerializer
/*    */   implements RecipeSerializer<GunSmithTableRecipe>
/*    */ {
/*    */   @Nullable
/*    */   public GunSmithTableRecipe fromJson(ResourceLocation id, JsonObject jsonObject) {
/* 17 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public GunSmithTableRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
/* 24 */     return null;
/*    */   }
/*    */   
/*    */   public void toNetwork(FriendlyByteBuf buffer, GunSmithTableRecipe recipe) {}
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\crafting\GunSmithTableSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */