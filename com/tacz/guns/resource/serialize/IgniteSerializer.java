/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Ignite;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ 
/*    */ public class IgniteSerializer implements JsonDeserializer<Ignite> {
/*    */   public Ignite deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 12 */     if (GsonHelper.m_144767_(json)) {
/* 13 */       boolean ignite = GsonHelper.m_13877_(json, "ignite");
/* 14 */       return new Ignite(ignite);
/* 15 */     }  if (json.isJsonObject()) {
/* 16 */       JsonObject jsonObject = json.getAsJsonObject();
/* 17 */       boolean igniteEntity = GsonHelper.m_13855_(jsonObject, "entity", false);
/* 18 */       boolean igniteBlock = GsonHelper.m_13855_(jsonObject, "block", false);
/* 19 */       return new Ignite(igniteEntity, igniteBlock);
/*    */     } 
/* 21 */     throw new JsonSyntaxException("Expected " + json + " to be a Pair because it's not an array");
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\IgniteSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */