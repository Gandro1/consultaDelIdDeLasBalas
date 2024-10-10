/*    */ package com.tacz.guns.client.resource.index;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.tacz.guns.client.model.BedrockAttachmentModel;
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import com.tacz.guns.client.resource.pojo.skin.attachment.AttachmentSkin;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientAttachmentSkinIndex
/*    */ {
/*    */   private BedrockAttachmentModel model;
/*    */   private ResourceLocation texture;
/*    */   private String name;
/*    */   
/*    */   public static ClientAttachmentSkinIndex getInstance(AttachmentSkin skinPojo) {
/* 19 */     ClientAttachmentSkinIndex index = new ClientAttachmentSkinIndex();
/* 20 */     checkIndex(skinPojo, index);
/* 21 */     checkTextureAndModel(skinPojo, index);
/* 22 */     return index;
/*    */   }
/*    */   
/*    */   private static void checkIndex(AttachmentSkin skinPojo, ClientAttachmentSkinIndex index) {
/* 26 */     Preconditions.checkArgument((skinPojo != null), "skin index file is empty");
/*    */   }
/*    */   
/*    */   private static void checkName(AttachmentSkin skinPojo, ClientAttachmentSkinIndex index) {
/* 30 */     index.name = skinPojo.getName();
/* 31 */     if (StringUtils.isBlank(index.name)) {
/* 32 */       index.name = "custom.tacz.error.no_name";
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static void checkTextureAndModel(AttachmentSkin skinPojo, ClientAttachmentSkinIndex index) {
/* 38 */     ResourceLocation modelLocation = skinPojo.getModel();
/* 39 */     Preconditions.checkArgument((modelLocation != null), "display object missing model field");
/* 40 */     index.model = ClientAssetManager.INSTANCE.getOrLoadAttachmentModel(modelLocation);
/* 41 */     Preconditions.checkArgument((index.model != null), "there is no model data in the model file");
/*    */     
/* 43 */     ResourceLocation textureLocation = skinPojo.getTexture();
/* 44 */     Preconditions.checkArgument((textureLocation != null), "missing default texture");
/* 45 */     index.texture = textureLocation;
/*    */   }
/*    */   
/*    */   public BedrockAttachmentModel getModel() {
/* 49 */     return this.model;
/*    */   }
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 53 */     return this.texture;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 57 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\index\ClientAttachmentSkinIndex.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */