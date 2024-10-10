/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.lang.reflect.Type;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ 
/*    */ public class PairSerializer implements JsonDeserializer<Pair<Float, Float>>, JsonSerializer<Pair<Float, Float>> {
/*    */   public Pair<Float, Float> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 12 */     if (json.isJsonArray()) {
/* 13 */       JsonArray array = json.getAsJsonArray();
/* 14 */       if (array.size() < 2) {
/* 15 */         throw new JsonSyntaxException("Expected " + json + " length of the array must >= 2");
/*    */       }
/* 17 */       JsonElement leftElement = array.get(0);
/* 18 */       JsonElement rightElement = array.get(1);
/* 19 */       float left = GsonHelper.m_13888_(leftElement, "(array i=0)");
/* 20 */       float right = GsonHelper.m_13888_(rightElement, "(array i=1)");
/* 21 */       if (left > right) {
/* 22 */         throw new JsonSyntaxException("Expected " + json + " left must <= right");
/*    */       }
/* 24 */       return Pair.of(Float.valueOf(left), Float.valueOf(right));
/*    */     } 
/* 26 */     throw new JsonSyntaxException("Expected " + json + " to be a Pair because it's not an array");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Pair<Float, Float> src, Type typeOfSrc, JsonSerializationContext context) {
/* 32 */     JsonArray array = new JsonArray(2);
/* 33 */     array.set(0, (JsonElement)new JsonPrimitive((Number)src.getLeft()));
/* 34 */     array.set(1, (JsonElement)new JsonPrimitive((Number)src.getRight()));
/* 35 */     return (JsonElement)array;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\PairSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */