/*     */ package com.tacz.guns.client.renderer.item;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.client.model.BedrockAmmoModel;
/*     */ import com.tacz.guns.client.model.SlotModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.pojo.TransformScale;
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
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class AmmoItemRenderer
/*     */   extends BlockEntityWithoutLevelRenderer
/*     */ {
/*  30 */   private static final SlotModel SLOT_AMMO_MODEL = new SlotModel();
/*     */   
/*     */   public AmmoItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
/*  33 */     super(pBlockEntityRenderDispatcher, pEntityModelSet);
/*     */   }
/*     */   
/*     */   private static void applyPositioningNodeTransform(List<BedrockPart> nodePath, PoseStack poseStack, Vector3f scale) {
/*  37 */     if (nodePath == null) {
/*     */       return;
/*     */     }
/*  40 */     if (scale == null) {
/*  41 */       scale = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     
/*  44 */     poseStack.m_85837_(0.0D, 1.5D, 0.0D);
/*  45 */     for (int i = nodePath.size() - 1; i >= 0; i--) {
/*  46 */       BedrockPart t = nodePath.get(i);
/*  47 */       poseStack.m_252781_(Axis.f_252495_.m_252961_(t.xRot));
/*  48 */       poseStack.m_252781_(Axis.f_252392_.m_252961_(t.yRot));
/*  49 */       poseStack.m_252781_(Axis.f_252393_.m_252961_(t.zRot));
/*  50 */       if (t.getParent() != null) {
/*  51 */         poseStack.m_252880_(-t.x * scale.x() / 16.0F, -t.y * scale.y() / 16.0F, -t.z * scale.z() / 16.0F);
/*     */       } else {
/*  53 */         poseStack.m_252880_(-t.x * scale.x() / 16.0F, (1.5F - t.y / 16.0F) * scale.y(), -t.z * scale.z() / 16.0F);
/*     */       } 
/*     */     } 
/*  56 */     poseStack.m_85837_(0.0D, -1.5D, 0.0D);
/*     */   }
/*     */   
/*     */   public void m_108829_(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
/*     */     IAmmo iAmmo;
/*  61 */     Item item = stack.m_41720_(); if (item instanceof IAmmo) { iAmmo = (IAmmo)item; }
/*     */     else
/*     */     { return; }
/*  64 */      ResourceLocation ammoId = iAmmo.getAmmoId(stack);
/*  65 */     poseStack.m_85836_();
/*  66 */     TimelessAPI.getClientAmmoIndex(ammoId).ifPresentOrElse(ammoIndex -> {
/*     */           BedrockAmmoModel ammoModel = ammoIndex.getAmmoModel();
/*     */           
/*     */           ResourceLocation modelTexture = ammoIndex.getModelTextureLocation();
/*     */           
/*     */           if (transformType == ItemDisplayContext.GUI || ammoModel == null || modelTexture == null) {
/*     */             poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */             
/*     */             poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */             
/*     */             VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(ammoIndex.getSlotTextureLocation()));
/*     */             
/*     */             SLOT_AMMO_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           poseStack.m_85837_(0.5D, 2.0D, 0.5D);
/*     */           
/*     */           poseStack.m_85841_(-1.0F, -1.0F, 1.0F);
/*     */           
/*     */           applyPositioningTransform(transformType, ammoIndex.getTransform().getScale(), ammoModel, poseStack);
/*     */           applyScaleTransform(transformType, ammoIndex.getTransform().getScale(), poseStack);
/*     */           RenderType renderType = RenderType.m_110452_(modelTexture);
/*     */           ammoModel.render(poseStack, transformType, renderType, pPackedLight, pPackedOverlay);
/*     */         }() -> {
/*     */           poseStack.m_85837_(0.5D, 1.5D, 0.5D);
/*     */           poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
/*     */           VertexConsumer buffer = pBuffer.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
/*     */           SLOT_AMMO_MODEL.m_7695_(poseStack, buffer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */         });
/*  97 */     poseStack.m_85849_();
/*     */   }
/*     */   
/*     */   private void applyPositioningTransform(ItemDisplayContext transformType, TransformScale scale, BedrockAmmoModel model, PoseStack poseStack) {
/* 101 */     switch (transformType) { case FIXED:
/* 102 */         applyPositioningNodeTransform(model.getFixedOriginPath(), poseStack, scale.getFixed()); break;
/* 103 */       case GROUND: applyPositioningNodeTransform(model.getGroundOriginPath(), poseStack, scale.getGround()); break;
/*     */       case THIRD_PERSON_RIGHT_HAND: case THIRD_PERSON_LEFT_HAND:
/* 105 */         applyPositioningNodeTransform(model.getThirdPersonHandOriginPath(), poseStack, scale.getThirdPerson());
/*     */         break; }
/*     */   
/*     */   }
/*     */   private void applyScaleTransform(ItemDisplayContext transformType, TransformScale scale, PoseStack poseStack) {
/* 110 */     if (scale == null) {
/*     */       return;
/*     */     }
/* 113 */     Vector3f vector3f = null;
/* 114 */     switch (transformType) { case FIXED:
/* 115 */         vector3f = scale.getFixed(); break;
/* 116 */       case GROUND: vector3f = scale.getGround(); break;
/* 117 */       case THIRD_PERSON_RIGHT_HAND: case THIRD_PERSON_LEFT_HAND: vector3f = scale.getThirdPerson(); break; }
/*     */     
/* 119 */     if (vector3f != null) {
/* 120 */       poseStack.m_85837_(0.0D, 1.5D, 0.0D);
/* 121 */       poseStack.m_85841_(vector3f.x(), vector3f.y(), vector3f.z());
/* 122 */       poseStack.m_85837_(0.0D, -1.5D, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\item\AmmoItemRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */