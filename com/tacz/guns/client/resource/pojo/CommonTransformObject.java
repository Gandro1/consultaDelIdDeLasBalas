/*     */ package com.tacz.guns.client.resource.pojo;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector3fc;
/*     */ 
/*     */ public class CommonTransformObject {
/*     */   @Nullable
/*     */   private Vector3f translation;
/*     */   @Nullable
/*     */   private Vector3f rotation;
/*     */   
/*     */   public CommonTransformObject(@Nullable Vector3f translation, @Nullable Vector3f rotation, @Nullable Vector3f scale) {
/*  22 */     this.translation = translation;
/*  23 */     this.rotation = rotation;
/*  24 */     this.scale = scale;
/*     */   } @Nullable
/*     */   private Vector3f scale; public CommonTransformObject() {}
/*     */   @Nonnull
/*     */   public Vector3f getTranslation() {
/*  29 */     if (this.translation == null) {
/*  30 */       this.translation = new Vector3f(0.0F, 0.0F, 0.0F);
/*     */     }
/*  32 */     return this.translation;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public Vector3f getRotation() {
/*  37 */     if (this.rotation == null) {
/*  38 */       this.rotation = new Vector3f(0.0F, 0.0F, 0.0F);
/*     */     }
/*  40 */     return this.rotation;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public Vector3f getScale() {
/*  45 */     if (this.scale == null) {
/*  46 */       this.scale = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */     }
/*  48 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/*  53 */     if (other instanceof CommonTransformObject) { boolean flag1, flag2, flag3; CommonTransformObject object = (CommonTransformObject)other;
/*     */       
/*  55 */       if (this.translation == null || object.translation == null) {
/*  56 */         flag1 = (this.translation == null && object.translation == null);
/*     */       } else {
/*  58 */         flag1 = this.translation.equals(object.translation);
/*     */       } 
/*  60 */       if (this.rotation == null || object.rotation == null) {
/*  61 */         flag2 = (this.rotation == null && object.rotation == null);
/*     */       } else {
/*  63 */         flag2 = this.rotation.equals(object.rotation);
/*     */       } 
/*  65 */       if (this.scale == null || object.scale == null) {
/*  66 */         flag3 = (this.scale == null && object.scale == null);
/*     */       } else {
/*  68 */         flag3 = this.scale.equals(object.scale);
/*     */       } 
/*  70 */       return (flag1 && flag2 && flag3); }
/*     */     
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   public CommonTransformObject lerp(CommonTransformObject target, float alpha) {
/*  76 */     CommonTransformObject object = new CommonTransformObject();
/*  77 */     object.translation = (this.translation == null) ? new Vector3f(0.0F, 0.0F, 0.0F) : new Vector3f((Vector3fc)this.translation);
/*  78 */     object.rotation = (this.rotation == null) ? new Vector3f(0.0F, 0.0F, 0.0F) : new Vector3f((Vector3fc)this.rotation);
/*  79 */     object.scale = (this.scale == null) ? new Vector3f(1.0F, 1.0F, 1.0F) : new Vector3f((Vector3fc)this.scale);
/*  80 */     object.translation.lerp((Vector3fc)target.getTranslation(), alpha);
/*  81 */     object.rotation.lerp((Vector3fc)target.getRotation(), alpha);
/*  82 */     object.scale.lerp((Vector3fc)target.getScale(), alpha);
/*  83 */     return object;
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer<CommonTransformObject>, JsonSerializer<CommonTransformObject> {
/*     */     public CommonTransformObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  89 */       if (json.isJsonObject()) {
/*  90 */         CommonTransformObject transformObject = new CommonTransformObject();
/*  91 */         JsonObject jsonObject = json.getAsJsonObject();
/*  92 */         JsonElement translationElement = jsonObject.get("translation");
/*  93 */         JsonElement rotationElement = jsonObject.get("rotation");
/*  94 */         JsonElement scaleElement = jsonObject.get("scale");
/*  95 */         if (translationElement != null) {
/*  96 */           transformObject.translation = (Vector3f)context.deserialize(translationElement, Vector3f.class);
/*     */         }
/*  98 */         if (rotationElement != null) {
/*  99 */           transformObject.rotation = (Vector3f)context.deserialize(rotationElement, Vector3f.class);
/*     */         }
/* 101 */         if (scaleElement != null) {
/* 102 */           transformObject.scale = (Vector3f)context.deserialize(scaleElement, Vector3f.class);
/*     */         }
/* 104 */         return transformObject;
/*     */       } 
/* 106 */       throw new JsonSyntaxException("Expected " + json + " to be a CommonTransformObject because it's not an JsonObject");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(CommonTransformObject src, Type typeOfSrc, JsonSerializationContext context) {
/* 112 */       JsonObject jsonObject = new JsonObject();
/* 113 */       if (src.translation != null) {
/* 114 */         jsonObject.add("translation", context.serialize(src.translation));
/*     */       }
/* 116 */       if (src.rotation != null) {
/* 117 */         jsonObject.add("rotation", context.serialize(src.rotation));
/*     */       }
/* 119 */       if (src.scale != null) {
/* 120 */         jsonObject.add("scale", context.serialize(src.scale));
/*     */       }
/* 122 */       return (JsonElement)jsonObject;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\CommonTransformObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */