/*    */ package com.tacz.guns.client.resource.serialize;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class Vector3fSerializer implements JsonDeserializer<Vector3f>, JsonSerializer<Vector3f> {
/*    */   public Vector3f deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 12 */     if (json.isJsonArray()) {
/* 13 */       JsonArray array = json.getAsJsonArray();
/* 14 */       JsonElement xElement = array.get(0);
/* 15 */       JsonElement yElement = array.get(1);
/* 16 */       JsonElement zElement = array.get(2);
/* 17 */       float x = GsonHelper.m_13888_(xElement, "(array i=0)");
/* 18 */       float y = GsonHelper.m_13888_(yElement, "(array i=1)");
/* 19 */       float z = GsonHelper.m_13888_(zElement, "(array i=2)");
/* 20 */       return new Vector3f(x, y, z);
/*    */     } 
/* 22 */     throw new JsonSyntaxException("Expected " + json + " to be a Vector3f because it's not an array");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Vector3f src, Type typeOfSrc, JsonSerializationContext context) {
/* 28 */     JsonArray array = new JsonArray(3);
/* 29 */     array.set(0, (JsonElement)new JsonPrimitive(Float.valueOf(src.x())));
/* 30 */     array.set(1, (JsonElement)new JsonPrimitive(Float.valueOf(src.y())));
/* 31 */     array.set(2, (JsonElement)new JsonPrimitive(Float.valueOf(src.z())));
/* 32 */     return (JsonElement)array;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\serialize\Vector3fSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */