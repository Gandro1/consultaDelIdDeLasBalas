/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.tacz.guns.crafting.GunSmithTableIngredient;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ 
/*    */ public class GunSmithTableIngredientSerializer implements JsonDeserializer<GunSmithTableIngredient> {
/*    */   public GunSmithTableIngredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 13 */     if (json.isJsonObject()) {
/* 14 */       JsonObject jsonObject = json.getAsJsonObject();
/* 15 */       if (!jsonObject.has("item")) {
/* 16 */         throw new JsonSyntaxException("Expected " + jsonObject + " must has a item member");
/*    */       }
/* 18 */       Ingredient ingredient = Ingredient.m_43917_(jsonObject.get("item"));
/* 19 */       int count = 1;
/* 20 */       if (jsonObject.has("count")) {
/* 21 */         count = Math.max(GsonHelper.m_13927_(jsonObject, "count"), 1);
/*    */       }
/* 23 */       return new GunSmithTableIngredient(ingredient, count);
/*    */     } 
/* 25 */     throw new JsonSyntaxException("Expected " + json + " to be a Pair because it's not an object");
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\GunSmithTableIngredientSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */