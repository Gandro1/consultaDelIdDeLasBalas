/*     */ package com.tacz.guns.client.resource.pojo.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import java.lang.reflect.Type;
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
/*     */ public class Deserializer
/*     */   implements JsonDeserializer<CubesItem>
/*     */ {
/*     */   public CubesItem deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
/*  87 */     CubesItem cube = (CubesItem)(new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create().fromJson(json, CubesItem.class);
/*  88 */     if (json.isJsonObject()) {
/*  89 */       JsonObject obj = json.getAsJsonObject();
/*     */       
/*  91 */       JsonElement uvElement = obj.get("uv");
/*  92 */       if (uvElement.isJsonArray()) {
/*  93 */         cube.uv = Lists.newArrayList();
/*  94 */         JsonArray array = uvElement.getAsJsonArray();
/*  95 */         for (int i = 0; i < array.size(); i++) {
/*  96 */           cube.uv.add(Float.valueOf(array.get(i).getAsFloat()));
/*     */         }
/*     */       } 
/*  99 */       if (uvElement.isJsonObject()) {
/* 100 */         cube.faceUv = (FaceUVsItem)(new Gson()).fromJson(uvElement, FaceUVsItem.class);
/*     */       }
/*     */ 
/*     */       
/* 104 */       JsonElement mirrorElement = obj.get("mirror");
/* 105 */       if (mirrorElement != null && mirrorElement.isJsonPrimitive()) {
/* 106 */         JsonPrimitive primitive = mirrorElement.getAsJsonPrimitive();
/* 107 */         if (primitive.isBoolean()) {
/* 108 */           cube.mirror = primitive.getAsBoolean();
/* 109 */           cube.hasMirror = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 113 */     return cube;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\CubesItem$Deserializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */