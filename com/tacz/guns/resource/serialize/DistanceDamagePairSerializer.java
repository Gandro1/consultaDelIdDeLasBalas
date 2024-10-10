/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class DistanceDamagePairSerializer implements JsonDeserializer<ExtraDamage.DistanceDamagePair> {
/*    */   public ExtraDamage.DistanceDamagePair deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 13 */     if (!json.isJsonObject()) {
/* 14 */       throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it's not an object");
/*    */     }
/* 16 */     JsonObject jsonObject = json.getAsJsonObject();
/* 17 */     if (!jsonObject.has("distance")) {
/* 18 */       throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it's not has distance field");
/*    */     }
/* 20 */     if (!jsonObject.has("damage")) {
/* 21 */       throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it's not has damage field");
/*    */     }
/* 23 */     if (!jsonObject.get("distance").isJsonPrimitive()) {
/* 24 */       throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it distance field is not a string or number");
/*    */     }
/* 26 */     if (!jsonObject.get("damage").isJsonPrimitive()) {
/* 27 */       throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it damage field is not a number");
/*    */     }
/* 29 */     float distance = 0.0F;
/*    */     
/* 31 */     JsonPrimitive jsonPrimitive = jsonObject.get("distance").getAsJsonPrimitive();
/* 32 */     if (jsonPrimitive.isNumber()) {
/* 33 */       distance = jsonPrimitive.getAsFloat();
/* 34 */     } else if (jsonPrimitive.isString()) {
/* 35 */       if ("infinite".equals(jsonPrimitive.getAsString())) {
/* 36 */         distance = Float.MAX_VALUE;
/*    */       } else {
/* 38 */         throw new JsonSyntaxException("Expected " + json + " to be a DistanceDamagePair because it distance field is not is 'infinite'");
/*    */       } 
/*    */     } 
/* 41 */     float damage = jsonObject.get("damage").getAsFloat();
/* 42 */     return new ExtraDamage.DistanceDamagePair(distance, damage);
/*    */   }
/*    */   
/*    */   private static final String INFINITE = "infinite";
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\DistanceDamagePairSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */