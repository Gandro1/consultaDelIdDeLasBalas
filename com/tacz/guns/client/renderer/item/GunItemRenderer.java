/*     */ package com.tacz.guns.client.renderer.item;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.model.SlotModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.TransformScale;
/*     */ import com.tacz.guns.util.RenderDistance;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.client.model.geom.EntityModelSet;
/*     */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GunItemRenderer
/*     */   extends BlockEntityWithoutLevelRenderer
/*     */ {
/*  34 */   private static final SlotModel SLOT_GUN_MODEL = new SlotModel();
/*     */   
/*     */   public GunItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
/*  37 */     super(pBlockEntityRenderDispatcher, pEntityModelSet);
/*     */   }
/*     */   
/*     */   private static void applyPositioningNodeTransform(List<BedrockPart> nodePath, PoseStack poseStack, Vector3f scale) {
/*  41 */     if (nodePath == null) {
/*     */       return;
/*     */     }
/*  44 */     if (scale == null) {
/*  45 */       scale = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     
/*  48 */     poseStack.m_85837_(0.0D, 1.5D, 0.0D);
/*  49 */     for (int i = nodePath.size() - 1; i >= 0; i--) {
/*  50 */       BedrockPart t = nodePath.get(i);
/*  51 */       poseStack.m_252781_(Axis.f_252495_.m_252961_(t.xRot));
/*  52 */       poseStack.m_252781_(Axis.f_252392_.m_252961_(t.yRot));
/*  53 */       poseStack.m_252781_(Axis.f_252393_.m_252961_(t.zRot));
/*  54 */       if (t.getParent() != null) {
/*  55 */         poseStack.m_252880_(-t.x * scale.x() / 16.0F, -t.y * scale.y() / 16.0F, -t.z * scale.z() / 16.0F);
/*     */       } else {
/*  57 */         poseStack.m_252880_(-t.x * scale.x() / 16.0F, (1.5F - t.y / 16.0F) * scale.y(), -t.z * scale.z() / 16.0F);
/*     */       } 
/*     */     } 
/*  60 */     poseStack.m_85837_(0.0D, -1.5D, 0.0D);
/*     */   }
/*     */   
/*     */   public void m_108829_(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
/*     */     IGun iGun;
/*  65 */     Item item = stack.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/*     */     else
/*     */     { return; }
/*  68 */      ResourceLocation gunId = iGun.getGunId(stack);
/*  69 */     poseStack.m_85836_();
/*  70 */     TimelessAPI.getClientGunIndex(gunId).ifPresentOrElse(gunIndex -> {
/*     */           BedrockGunModel gunModel;
/*     */           
/*     */           ResourceLocation gunTexture;
/*     */           
/*     */           if (transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (transformType == ItemDisplayContext.GUI) {
/*     */             poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */             
/*     */             poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */             
/*     */             VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(gunIndex.getSlotTexture()));
/*     */             
/*     */             SLOT_GUN_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           Pair<BedrockGunModel, ResourceLocation> lodModel = gunIndex.getLodModel();
/*     */           
/*     */           if (lodModel == null || RenderDistance.inRenderHighPolyModelDistance(poseStack)) {
/*     */             gunModel = gunIndex.getGunModel();
/*     */             gunTexture = gunIndex.getModelTexture();
/*     */           } else {
/*     */             gunModel = (BedrockGunModel)lodModel.getLeft();
/*     */             gunTexture = (ResourceLocation)lodModel.getRight();
/*     */           } 
/*     */           poseStack.m_85837_(0.5D, 2.0D, 0.5D);
/*     */           poseStack.m_85841_(-1.0F, -1.0F, 1.0F);
/*     */           applyPositioningTransform(transformType, gunIndex.getTransform().getScale(), gunModel, poseStack);
/*     */           applyScaleTransform(transformType, gunIndex.getTransform().getScale(), poseStack);
/*     */           RenderType renderType = RenderType.m_110452_(gunTexture);
/*     */           gunModel.render(poseStack, stack, transformType, renderType, pPackedLight, pPackedOverlay);
/*     */         }() -> {
/*     */           poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */           VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
/*     */           SLOT_GUN_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */         });
/* 116 */     poseStack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyPositioningTransform(ItemDisplayContext transformType, TransformScale scale, BedrockGunModel model, PoseStack poseStack) {
/* 121 */     switch (transformType) { case FIXED:
/* 122 */         applyPositioningNodeTransform(model.getFixedOriginPath(), poseStack, scale.getFixed()); break;
/* 123 */       case GROUND: applyPositioningNodeTransform(model.getGroundOriginPath(), poseStack, scale.getGround()); break;
/* 124 */       case THIRD_PERSON_RIGHT_HAND: case THIRD_PERSON_LEFT_HAND: applyPositioningNodeTransform(model.getThirdPersonHandOriginPath(), poseStack, scale.getThirdPerson());
/*     */         break; }
/*     */   
/*     */   }
/*     */   private void applyScaleTransform(ItemDisplayContext transformType, TransformScale scale, PoseStack poseStack) {
/* 129 */     if (scale == null) {
/*     */       return;
/*     */     }
/* 132 */     Vector3f vector3f = null;
/* 133 */     switch (transformType) { case FIXED:
/* 134 */         vector3f = scale.getFixed(); break;
/* 135 */       case GROUND: vector3f = scale.getGround(); break;
/* 136 */       case THIRD_PERSON_RIGHT_HAND: case THIRD_PERSON_LEFT_HAND: vector3f = scale.getThirdPerson(); break; }
/*     */     
/* 138 */     if (vector3f != null) {
/* 139 */       poseStack.m_85837_(0.0D, 1.5D, 0.0D);
/* 140 */       poseStack.m_85841_(vector3f.x(), vector3f.y(), vector3f.z());
/* 141 */       poseStack.m_85837_(0.0D, -1.5D, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\item\GunItemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */