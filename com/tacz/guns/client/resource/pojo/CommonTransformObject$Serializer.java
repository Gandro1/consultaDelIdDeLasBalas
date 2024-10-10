/*     */ package com.tacz.guns.client.resource.pojo;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Serializer
/*     */   implements JsonDeserializer<CommonTransformObject>, JsonSerializer<CommonTransformObject>
/*     */ {
/*     */   public CommonTransformObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  89 */     if (json.isJsonObject()) {
/*  90 */       CommonTransformObject transformObject = new CommonTransformObject();
/*  91 */       JsonObject jsonObject = json.getAsJsonObject();
/*  92 */       JsonElement translationElement = jsonObject.get("translation");
/*  93 */       JsonElement rotationElement = jsonObject.get("rotation");
/*  94 */       JsonElement scaleElement = jsonObject.get("scale");
/*  95 */       if (translationElement != null) {
/*  96 */         transformObject.translation = (Vector3f)context.deserialize(translationElement, Vector3f.class);
/*     */       }
/*  98 */       if (rotationElement != null) {
/*  99 */         transformObject.rotation = (Vector3f)context.deserialize(rotationElement, Vector3f.class);
/*     */       }
/* 101 */       if (scaleElement != null) {
/* 102 */         transformObject.scale = (Vector3f)context.deserialize(scaleElement, Vector3f.class);
/*     */       }
/* 104 */       return transformObject;
/*     */     } 
/* 106 */     throw new JsonSyntaxException("Expected " + json + " to be a CommonTransformObject because it's not an JsonObject");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement serialize(CommonTransformObject src, Type typeOfSrc, JsonSerializationContext context) {
/* 112 */     JsonObject jsonObject = new JsonObject();
/* 113 */     if (src.translation != null) {
/* 114 */       jsonObject.add("translation", context.serialize(src.translation));
/*     */     }
/* 116 */     if (src.rotation != null) {
/* 117 */       jsonObject.add("rotation", context.serialize(src.rotation));
/*     */     }
/* 119 */     if (src.scale != null) {
/* 120 */       jsonObject.add("scale", context.serialize(src.scale));
/*     */     }
/* 122 */     return (JsonElement)jsonObject;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\CommonTransformObject$Serializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */