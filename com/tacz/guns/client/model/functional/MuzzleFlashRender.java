/*     */ package com.tacz.guns.client.model.functional;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.model.SlotModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.MuzzleFlash;
/*     */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*     */ import it.unimi.dsi.fastutil.Pair;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix3fc;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ 
/*     */ public class MuzzleFlashRender implements IFunctionalRenderer {
/*  28 */   private static final SlotModel MUZZLE_FLASH_MODEL = new SlotModel(true);
/*     */   
/*     */   private static final long TIME_RANGE = 50L;
/*     */   
/*     */   public static boolean isSelf = false;
/*     */   
/*  34 */   private static long shootTimeStamp = -1L;
/*     */   private static boolean muzzleFlashStartMark = false;
/*  36 */   private static float muzzleFlashRandomRotate = 0.0F;
/*  37 */   private static Matrix3f muzzleFlashNormal = new Matrix3f();
/*  38 */   private static Matrix4f muzzleFlashPose = new Matrix4f();
/*     */   
/*     */   private final BedrockGunModel bedrockGunModel;
/*     */   
/*     */   public MuzzleFlashRender(BedrockGunModel bedrockGunModel) {
/*  43 */     this.bedrockGunModel = bedrockGunModel;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onShoot() {
/*  48 */     shootTimeStamp = System.currentTimeMillis();
/*     */     
/*  50 */     muzzleFlashStartMark = true;
/*     */     
/*  52 */     muzzleFlashRandomRotate = (float)(Math.random() * 360.0D);
/*     */   }
/*     */   
/*     */   private static void renderMuzzleFlash(ResourceLocation gunId, PoseStack poseStack, BedrockModel bedrockModel, long time) {
/*  56 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(gunIndex -> {
/*     */           MuzzleFlash muzzleFlash = gunIndex.getMuzzleFlash();
/*     */           if (muzzleFlash == null) {
/*     */             return;
/*     */           }
/*     */           if (muzzleFlashStartMark) {
/*     */             muzzleFlashNormal = new Matrix3f((Matrix3fc)poseStack.m_85850_().m_252943_());
/*     */             muzzleFlashPose = new Matrix4f((Matrix4fc)poseStack.m_85850_().m_252922_());
/*     */           } 
/*     */           bedrockModel.delegateRender(());
/*     */         });
/*     */   }
/*     */   
/*     */   private static void doRender(int light, int overlay, MuzzleFlash muzzleFlash, long time) {
/*  70 */     if (muzzleFlashNormal != null && muzzleFlashPose != null) {
/*  71 */       float scale = 0.5F * muzzleFlash.getScale();
/*  72 */       float scaleTime = 25.0F;
/*  73 */       scale = ((float)time < scaleTime) ? (scale * (float)time / scaleTime) : scale;
/*  74 */       muzzleFlashStartMark = false;
/*  75 */       MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/*     */ 
/*     */       
/*  78 */       PoseStack poseStack2 = new PoseStack();
/*  79 */       poseStack2.m_85850_().m_252943_().mul((Matrix3fc)muzzleFlashNormal);
/*  80 */       poseStack2.m_85850_().m_252922_().mul((Matrix4fc)muzzleFlashPose);
/*     */ 
/*     */       
/*  83 */       poseStack2.m_85836_();
/*     */       
/*  85 */       poseStack2.m_85841_(scale, scale, scale);
/*  86 */       poseStack2.m_252781_(Axis.f_252403_.m_252977_(muzzleFlashRandomRotate));
/*  87 */       poseStack2.m_252880_(0.0F, -1.0F, 0.0F);
/*  88 */       RenderType renderTypeBg = RenderType.m_110473_(muzzleFlash.getTexture());
/*  89 */       MUZZLE_FLASH_MODEL.m_7695_(poseStack2, bufferSource.m_6299_(renderTypeBg), light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  91 */       poseStack2.m_85849_();
/*     */ 
/*     */       
/*  94 */       poseStack2.m_85836_();
/*     */       
/*  96 */       poseStack2.m_85841_(scale / 2.0F, scale / 2.0F, scale / 2.0F);
/*  97 */       poseStack2.m_252781_(Axis.f_252403_.m_252977_(muzzleFlashRandomRotate));
/*  98 */       poseStack2.m_85837_(0.0D, -0.9D, 0.0D);
/*  99 */       RenderType renderTypeLight = RenderType.m_110436_(muzzleFlash.getTexture(), 1.0F, 1.0F);
/* 100 */       MUZZLE_FLASH_MODEL.m_7695_(poseStack2, bufferSource.m_6299_(renderTypeLight), light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 102 */       poseStack2.m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(PoseStack poseStack, VertexConsumer vertexBuffer, ItemDisplayContext transformType, int light, int overlay) {
/* 109 */     if (OculusCompat.isRenderShadow()) {
/*     */       return;
/*     */     }
/* 112 */     if (!isSelf) {
/*     */       return;
/*     */     }
/* 115 */     long time = System.currentTimeMillis() - shootTimeStamp;
/* 116 */     if (time > 50L) {
/*     */       return;
/*     */     }
/* 119 */     ItemStack currentGunItem = this.bedrockGunModel.getCurrentGunItem();
/* 120 */     IGun iGun = IGun.getIGunOrNull(currentGunItem);
/* 121 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 124 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 125 */     ItemStack muzzleAttachment = (ItemStack)this.bedrockGunModel.getCurrentAttachmentItem().get(AttachmentType.MUZZLE);
/* 126 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(muzzleAttachment);
/* 127 */     if (iAttachment != null) {
/* 128 */       ResourceLocation attachmentId = iAttachment.getAttachmentId(muzzleAttachment);
/* 129 */       TimelessAPI.getCommonAttachmentIndex(attachmentId).ifPresent(index -> {
/*     */             Map<String, JsonProperty<?>> modifier = index.getData().getModifier(); if (modifier.containsKey("silence")) {
/*     */               Object patt5899$temp = ((JsonProperty)modifier.get("silence")).getValue(); if (patt5899$temp instanceof Pair) {
/*     */                 Pair<?, ?> pair = (Pair<?, ?>)patt5899$temp;
/*     */                 if (((Boolean)pair.right()).booleanValue())
/*     */                   return; 
/*     */               } 
/*     */             } 
/*     */             renderMuzzleFlash(gunId, poseStack, (BedrockModel)this.bedrockGunModel, time);
/*     */           });
/*     */     } else {
/* 140 */       renderMuzzleFlash(gunId, poseStack, (BedrockModel)this.bedrockGunModel, time);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\functional\MuzzleFlashRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */