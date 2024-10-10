/*    */ package com.tacz.guns.client.model;
/*    */ 
/*    */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*    */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*    */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class BedrockAmmoModel
/*    */   extends BedrockModel {
/*    */   private static final String FIXED_ORIGIN_NODE = "fixed";
/*    */   private static final String GROUND_ORIGIN_NODE = "ground";
/*    */   private static final String THIRD_PERSON_HAND_ORIGIN_NODE = "thirdperson_hand";
/*    */   @Nullable
/*    */   protected List<BedrockPart> fixedOriginPath;
/*    */   @Nullable
/*    */   protected List<BedrockPart> groundOriginPath;
/*    */   @Nullable
/*    */   protected List<BedrockPart> thirdPersonHandOriginPath;
/*    */   
/*    */   public BedrockAmmoModel(BedrockModelPOJO pojo, BedrockVersion version) {
/* 24 */     super(pojo, version);
/* 25 */     this.fixedOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("fixed"));
/* 26 */     this.groundOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("ground"));
/* 27 */     this.thirdPersonHandOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("thirdperson_hand"));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BedrockPart> getFixedOriginPath() {
/* 32 */     return this.fixedOriginPath;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BedrockPart> getGroundOriginPath() {
/* 37 */     return this.groundOriginPath;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BedrockPart> getThirdPersonHandOriginPath() {
/* 42 */     return this.thirdPersonHandOriginPath;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\BedrockAmmoModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */