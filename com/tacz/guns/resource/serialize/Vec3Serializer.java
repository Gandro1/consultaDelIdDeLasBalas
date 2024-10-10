/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ public class Vec3Serializer implements JsonDeserializer<Vec3>, JsonSerializer<Vec3> {
/*    */   public Vec3 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 12 */     if (json.isJsonArray()) {
/* 13 */       JsonArray array = json.getAsJsonArray();
/* 14 */       JsonElement xElement = array.get(0);
/* 15 */       JsonElement yElement = array.get(1);
/* 16 */       JsonElement zElement = array.get(2);
/* 17 */       double x = GsonHelper.m_144769_(xElement, "(array i=0)");
/* 18 */       double y = GsonHelper.m_144769_(yElement, "(array i=1)");
/* 19 */       double z = GsonHelper.m_144769_(zElement, "(array i=2)");
/* 20 */       return new Vec3(x, y, z);
/*    */     } 
/* 22 */     throw new JsonSyntaxException("Expected " + json + " to be a Vec3 because it's not an array");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Vec3 src, Type typeOfSrc, JsonSerializationContext context) {
/* 28 */     JsonArray array = new JsonArray(3);
/* 29 */     array.set(0, (JsonElement)new JsonPrimitive(Double.valueOf(src.m_7096_())));
/* 30 */     array.set(1, (JsonElement)new JsonPrimitive(Double.valueOf(src.m_7098_())));
/* 31 */     array.set(2, (JsonElement)new JsonPrimitive(Double.valueOf(src.m_7094_())));
/* 32 */     return (JsonElement)array;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\Vec3Serializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */