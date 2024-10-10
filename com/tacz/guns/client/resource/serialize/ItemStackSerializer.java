/*    */ package com.tacz.guns.client.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.crafting.CraftingHelper;
/*    */ 
/*    */ public class ItemStackSerializer implements JsonDeserializer<ItemStack> {
/*    */   public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 12 */     if (json.isJsonObject()) {
/* 13 */       JsonObject jsonObject = json.getAsJsonObject();
/* 14 */       return CraftingHelper.getItemStack(jsonObject, true, false);
/*    */     } 
/* 16 */     throw new JsonSyntaxException("Expected " + json + " to be a ItemStack because it's not an object");
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\serialize\ItemStackSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */