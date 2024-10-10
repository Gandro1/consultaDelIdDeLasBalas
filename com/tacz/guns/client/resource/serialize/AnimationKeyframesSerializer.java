/*    */ package com.tacz.guns.client.resource.serialize;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.pojo.animation.bedrock.AnimationKeyframes;
/*    */ import it.unimi.dsi.fastutil.doubles.Double2ObjectRBTreeMap;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class AnimationKeyframesSerializer implements JsonDeserializer<AnimationKeyframes> {
/*    */   public AnimationKeyframes deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 17 */     Double2ObjectRBTreeMap<AnimationKeyframes.Keyframe> keyframes = new Double2ObjectRBTreeMap();
/*    */     
/* 19 */     if (json.isJsonPrimitive()) {
/* 20 */       if (json.getAsJsonPrimitive().isString()) {
/* 21 */         GunMod.LOGGER.warn("Molang is not supported: \"{}\"", json.getAsString());
/* 22 */         return new AnimationKeyframes(keyframes);
/*    */       } 
/* 24 */       float value = json.getAsJsonPrimitive().getAsFloat();
/* 25 */       Vector3f data = new Vector3f(value, value, value);
/* 26 */       AnimationKeyframes.Keyframe keyframe = new AnimationKeyframes.Keyframe(null, null, data, null);
/* 27 */       keyframes.put(0.0D, keyframe);
/* 28 */       return new AnimationKeyframes(keyframes);
/*    */     } 
/*    */ 
/*    */     
/* 32 */     if (json.isJsonArray()) {
/* 33 */       Vector3f data = readVector3f(json.getAsJsonArray());
/* 34 */       AnimationKeyframes.Keyframe keyframe = new AnimationKeyframes.Keyframe(null, null, data, null);
/* 35 */       keyframes.put(0.0D, keyframe);
/* 36 */       return new AnimationKeyframes(keyframes);
/*    */     } 
/*    */     
/* 39 */     if (json.isJsonObject()) {
/* 40 */       JsonObject jsonObject = json.getAsJsonObject();
/* 41 */       for (Map.Entry<String, JsonElement> entrySet : (Iterable<Map.Entry<String, JsonElement>>)jsonObject.entrySet()) {
/* 42 */         double time = Double.parseDouble(entrySet.getKey());
/* 43 */         AnimationKeyframes.Keyframe keyframe = readKeyFrames(entrySet.getValue());
/* 44 */         keyframes.put(time, keyframe);
/*    */       } 
/* 46 */       return new AnimationKeyframes(keyframes);
/*    */     } 
/* 48 */     return new AnimationKeyframes(keyframes);
/*    */   }
/*    */ 
/*    */   
/*    */   private AnimationKeyframes.Keyframe readKeyFrames(JsonElement element) {
/* 53 */     if (element.isJsonArray()) {
/* 54 */       Vector3f data = readVector3f(element.getAsJsonArray());
/* 55 */       return new AnimationKeyframes.Keyframe(null, null, data, null);
/*    */     } 
/*    */     
/* 58 */     if (element.isJsonObject()) {
/* 59 */       JsonObject jsonObject = element.getAsJsonObject();
/* 60 */       String lerpMode = null;
/* 61 */       Vector3f pre = null;
/* 62 */       Vector3f post = null;
/* 63 */       if (jsonObject.has("lerp_mode")) {
/* 64 */         lerpMode = jsonObject.get("lerp_mode").getAsString();
/*    */       }
/* 66 */       if (jsonObject.has("pre") && jsonObject.get("pre").isJsonArray()) {
/* 67 */         JsonArray array = jsonObject.get("pre").getAsJsonArray();
/* 68 */         pre = readVector3f(array);
/*    */       } 
/* 70 */       if (jsonObject.has("post") && jsonObject.get("post").isJsonArray()) {
/* 71 */         JsonArray array = jsonObject.get("post").getAsJsonArray();
/* 72 */         post = readVector3f(array);
/*    */       } 
/* 74 */       return new AnimationKeyframes.Keyframe(pre, post, null, lerpMode);
/*    */     } 
/* 76 */     return new AnimationKeyframes.Keyframe(null, null, null, null);
/*    */   }
/*    */   
/*    */   private Vector3f readVector3f(JsonArray array) {
/* 80 */     JsonElement xElement = array.get(0);
/* 81 */     JsonElement yElement = array.get(1);
/* 82 */     JsonElement zElement = array.get(2);
/* 83 */     float x = readVector3fElement(xElement, "(array i=0)");
/* 84 */     float y = readVector3fElement(yElement, "(array i=1)");
/* 85 */     float z = readVector3fElement(zElement, "(array i=2)");
/* 86 */     return new Vector3f(x, y, z);
/*    */   }
/*    */   
/*    */   private float readVector3fElement(JsonElement element, String memberName) {
/* 90 */     if (element.getAsJsonPrimitive().isString()) {
/* 91 */       GunMod.LOGGER.warn("Molang is not supported: \"{}\"", element.getAsString());
/* 92 */       return 0.0F;
/*    */     } 
/* 94 */     return GsonHelper.m_13888_(element, memberName);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\serialize\AnimationKeyframesSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */