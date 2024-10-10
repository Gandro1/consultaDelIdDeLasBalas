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
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class CubesItem
/*     */ {
/*     */   private List<Float> uv;
/*     */   private FaceUVsItem faceUv;
/*     */   private boolean mirror = false;
/*     */   private boolean hasMirror = false;
/*     */   
/*     */   public List<Float> getUv() {
/*  39 */     return this.uv; } @Expose @SerializedName("inflate") private float inflate; @Expose @SerializedName("size") private List<Float> size; @Expose @SerializedName("origin") private List<Float> origin; @Expose
/*     */   @SerializedName("rotation")
/*     */   private List<Float> rotation; @Expose
/*     */   @SerializedName("pivot")
/*     */   private List<Float> pivot; @Nullable
/*  44 */   public FaceUVsItem getFaceUv() { return this.faceUv; }
/*     */ 
/*     */   
/*     */   public boolean isMirror() {
/*  48 */     return this.mirror;
/*     */   }
/*     */   
/*     */   public void setMirror(boolean mirror) {
/*  52 */     this.mirror = mirror;
/*     */   }
/*     */   
/*     */   public boolean isHasMirror() {
/*  56 */     return this.hasMirror;
/*     */   }
/*     */   
/*     */   public float getInflate() {
/*  60 */     return this.inflate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Float> getSize() {
/*  67 */     return this.size;
/*     */   }
/*     */   
/*     */   public List<Float> getOrigin() {
/*  71 */     return this.origin;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Float> getRotation() {
/*  76 */     return this.rotation;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Float> getPivot() {
/*  81 */     return this.pivot;
/*     */   }
/*     */   
/*     */   public static class Deserializer
/*     */     implements JsonDeserializer<CubesItem> {
/*     */     public CubesItem deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
/*  87 */       CubesItem cube = (CubesItem)(new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create().fromJson(json, CubesItem.class);
/*  88 */       if (json.isJsonObject()) {
/*  89 */         JsonObject obj = json.getAsJsonObject();
/*     */         
/*  91 */         JsonElement uvElement = obj.get("uv");
/*  92 */         if (uvElement.isJsonArray()) {
/*  93 */           cube.uv = Lists.newArrayList();
/*  94 */           JsonArray array = uvElement.getAsJsonArray();
/*  95 */           for (int i = 0; i < array.size(); i++) {
/*  96 */             cube.uv.add(Float.valueOf(array.get(i).getAsFloat()));
/*     */           }
/*     */         } 
/*  99 */         if (uvElement.isJsonObject()) {
/* 100 */           cube.faceUv = (FaceUVsItem)(new Gson()).fromJson(uvElement, FaceUVsItem.class);
/*     */         }
/*     */ 
/*     */         
/* 104 */         JsonElement mirrorElement = obj.get("mirror");
/* 105 */         if (mirrorElement != null && mirrorElement.isJsonPrimitive()) {
/* 106 */           JsonPrimitive primitive = mirrorElement.getAsJsonPrimitive();
/* 107 */           if (primitive.isBoolean()) {
/* 108 */             cube.mirror = primitive.getAsBoolean();
/* 109 */             cube.hasMirror = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 113 */       return cube;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\CubesItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */