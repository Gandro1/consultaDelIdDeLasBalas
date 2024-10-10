/*    */ package com.tacz.guns.client.resource;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import com.tacz.guns.api.client.animation.Animations;
/*    */ import com.tacz.guns.api.client.animation.ObjectAnimation;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimationFile;
/*    */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*    */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InternalAssetLoader
/*    */ {
/* 29 */   public static final ResourceLocation DEFAULT_BULLET_TEXTURE = new ResourceLocation("tacz", "textures/entity/basic_bullet.png");
/* 30 */   public static final ResourceLocation DEFAULT_BULLET_MODEL = new ResourceLocation("tacz", "models/bedrock/basic_bullet.json");
/*    */   
/* 32 */   public static final ResourceLocation TARGET_MINECART_MODEL_LOCATION = new ResourceLocation("tacz", "models/bedrock/target_minecart.json");
/* 33 */   public static final ResourceLocation TARGET_MINECART_TEXTURE_LOCATION = new ResourceLocation("tacz", "textures/entity/target_minecart.png");
/* 34 */   public static final ResourceLocation ENTITY_EMPTY_TEXTURE = new ResourceLocation("tacz", "textures/entity/empty.png");
/*    */   
/* 36 */   public static final ResourceLocation TARGET_MODEL_LOCATION = new ResourceLocation("tacz", "models/bedrock/target.json");
/* 37 */   public static final ResourceLocation TARGET_TEXTURE_LOCATION = new ResourceLocation("tacz", "textures/block/target.png");
/*    */   
/* 39 */   public static final ResourceLocation STATUE_MODEL_LOCATION = new ResourceLocation("tacz", "models/bedrock/statue.json");
/* 40 */   public static final ResourceLocation STATUE_TEXTURE_LOCATION = new ResourceLocation("tacz", "textures/block/statue.png");
/*    */   
/* 42 */   public static final ResourceLocation SMITH_TABLE_MODEL_LOCATION = new ResourceLocation("tacz", "models/bedrock/gun_smith_table.json");
/* 43 */   public static final ResourceLocation SMITH_TABLE_TEXTURE_LOCATION = new ResourceLocation("tacz", "textures/block/gun_smith_table.png");
/*    */   
/* 45 */   private static final ResourceLocation DEFAULT_PISTOL_ANIMATIONS_LOC = new ResourceLocation("tacz", "animations/pistol_default.animation.json");
/* 46 */   private static final ResourceLocation DEFAULT_RIFLE_ANIMATIONS_LOC = new ResourceLocation("tacz", "animations/rifle_default.animation.json");
/*    */   
/* 48 */   private static final Map<ResourceLocation, BedrockModel> BEDROCK_MODELS = Maps.newHashMap();
/*    */   
/*    */   private static List<ObjectAnimation> defaultPistolAnimations;
/*    */   private static List<ObjectAnimation> defaultRifleAnimations;
/*    */   
/*    */   public static void onResourceReload() {
/* 54 */     BedrockAnimationFile pistolAnimationFile = loadAnimations(DEFAULT_PISTOL_ANIMATIONS_LOC);
/* 55 */     BedrockAnimationFile rifleAnimationFile = loadAnimations(DEFAULT_RIFLE_ANIMATIONS_LOC);
/* 56 */     defaultPistolAnimations = Animations.createAnimationFromBedrock(pistolAnimationFile);
/* 57 */     defaultRifleAnimations = Animations.createAnimationFromBedrock(rifleAnimationFile);
/*    */ 
/*    */     
/* 60 */     BEDROCK_MODELS.clear();
/* 61 */     loadBedrockModels(SMITH_TABLE_MODEL_LOCATION);
/* 62 */     loadBedrockModels(TARGET_MODEL_LOCATION);
/* 63 */     loadBedrockModels(TARGET_MINECART_MODEL_LOCATION);
/* 64 */     loadBedrockModels(DEFAULT_BULLET_MODEL);
/* 65 */     loadBedrockModels(STATUE_MODEL_LOCATION);
/*    */   }
/*    */   private static BedrockAnimationFile loadAnimations(ResourceLocation resourceLocation) {
/*    */     
/* 69 */     try { InputStream inputStream = Minecraft.m_91087_().m_91098_().m_215595_(resourceLocation); 
/* 70 */       try { BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/* 71 */         JsonObject json = JsonParser.parseReader(bufferedReader).getAsJsonObject();
/* 72 */         BedrockAnimationFile bedrockAnimationFile = (BedrockAnimationFile)ClientGunPackLoader.GSON.fromJson((JsonElement)json, BedrockAnimationFile.class);
/* 73 */         if (inputStream != null) inputStream.close();  return bedrockAnimationFile; } catch (Throwable throwable) { if (inputStream != null) try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException e)
/* 74 */     { throw new RuntimeException(e); }
/*    */   
/*    */   }
/*    */   private static void loadBedrockModels(ResourceLocation location) {
/*    */     
/* 79 */     try { InputStream stream = Minecraft.m_91087_().m_91098_().m_215595_(location); 
/* 80 */       try { BedrockModelPOJO pojo = (BedrockModelPOJO)ClientGunPackLoader.GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), BedrockModelPOJO.class);
/* 81 */         BEDROCK_MODELS.put(location, new BedrockModel(pojo, BedrockVersion.NEW));
/* 82 */         if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException|com.google.gson.JsonSyntaxException|com.google.gson.JsonIOException e)
/* 83 */     { e.fillInStackTrace(); }
/*    */   
/*    */   }
/*    */   
/*    */   public static List<ObjectAnimation> getDefaultPistolAnimations() {
/* 88 */     return defaultPistolAnimations;
/*    */   }
/*    */   
/*    */   public static List<ObjectAnimation> getDefaultRifleAnimations() {
/* 92 */     return defaultRifleAnimations;
/*    */   }
/*    */   
/*    */   public static Optional<BedrockModel> getBedrockModel(ResourceLocation location) {
/* 96 */     return Optional.ofNullable(BEDROCK_MODELS.get(location));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\InternalAssetLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */