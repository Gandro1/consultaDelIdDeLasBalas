/*    */ package com.tacz.guns.client.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.tacz.guns.client.resource.pojo.animation.bedrock.SoundEffectKeyframes;
/*    */ import it.unimi.dsi.fastutil.doubles.Double2ObjectRBTreeMap;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ 
/*    */ public class SoundEffectKeyframesSerializer implements JsonDeserializer<SoundEffectKeyframes> {
/*    */   public SoundEffectKeyframes deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 16 */     Double2ObjectRBTreeMap<ResourceLocation> keyframes = new Double2ObjectRBTreeMap();
/*    */     
/* 18 */     if (json.isJsonObject()) {
/* 19 */       JsonObject jsonObject = json.getAsJsonObject();
/* 20 */       for (Map.Entry<String, JsonElement> entrySet : (Iterable<Map.Entry<String, JsonElement>>)jsonObject.entrySet()) {
/* 21 */         double time = Double.parseDouble(entrySet.getKey());
/* 22 */         JsonElement value = entrySet.getValue();
/* 23 */         if (value.isJsonObject()) {
/* 24 */           String soundId = GsonHelper.m_13906_(value.getAsJsonObject(), "effect");
/* 25 */           keyframes.put(time, new ResourceLocation(soundId));
/*    */         } 
/*    */       } 
/* 28 */       return new SoundEffectKeyframes(keyframes);
/*    */     } 
/* 30 */     return new SoundEffectKeyframes(keyframes);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\serialize\SoundEffectKeyframesSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */