/*     */ package com.tacz.guns.client.model;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.BufferBuilder;
/*     */ import com.mojang.blaze3d.vertex.BufferUploader;
/*     */ import com.mojang.blaze3d.vertex.DefaultVertexFormat;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.Tesselator;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.compat.oculus.OculusCompat;
/*     */ import com.tacz.guns.util.RenderHelper;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class BedrockAttachmentModel
/*     */   extends BedrockAnimatedModel
/*     */ {
/*     */   private static final String SCOPE_VIEW_NODE = "scope_view";
/*     */   private static final String SCOPE_BODY_NODE = "scope_body";
/*     */   private static final String OCULAR_RING_NODE = "ocular_ring";
/*     */   private static final String DIVISION_NODE = "division";
/*     */   private static final String OCULAR_NODE = "ocular";
/*     */   @Nullable
/*     */   protected List<BedrockPart> scopeViewPath;
/*  39 */   private float scopeViewRadiusModifier = 1.0F; @Nullable protected List<BedrockPart> scopeBodyPath; @Nullable
/*     */   protected List<BedrockPart> ocularRingPath; @Nullable
/*     */   protected List<BedrockPart> ocularNodePath; @Nullable
/*  42 */   protected List<BedrockPart> divisionNodePath; private boolean isScope = true; private boolean isSight = false; public BedrockAttachmentModel(BedrockModelPOJO pojo, BedrockVersion version) { super(pojo, version);
/*  43 */     this.scopeViewPath = getPath((ModelRendererWrapper)this.modelMap.get("scope_view"));
/*  44 */     this.scopeBodyPath = getPath((ModelRendererWrapper)this.modelMap.get("scope_body"));
/*  45 */     this.ocularRingPath = getPath((ModelRendererWrapper)this.modelMap.get("ocular_ring"));
/*  46 */     this.ocularNodePath = getPath((ModelRendererWrapper)this.modelMap.get("ocular"));
/*  47 */     this.divisionNodePath = getPath((ModelRendererWrapper)this.modelMap.get("division"));
/*  48 */     if (this.divisionNodePath != null) {
/*  49 */       ((BedrockPart)this.divisionNodePath.get(this.divisionNodePath.size() - 1)).visible = false;
/*     */     } }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getScopeViewPath() {
/*  55 */     return this.scopeViewPath;
/*     */   }
/*     */   
/*     */   public void setIsScope(boolean isScope) {
/*  59 */     if (isScope) {
/*  60 */       this.isSight = false;
/*     */     }
/*  62 */     this.isScope = isScope;
/*     */   }
/*     */   
/*     */   public void setIsSight(boolean isSight) {
/*  66 */     if (isSight) {
/*  67 */       this.isScope = false;
/*     */     }
/*  69 */     this.isSight = isSight;
/*     */   }
/*     */   
/*     */   public boolean isScope() {
/*  73 */     return this.isScope;
/*     */   }
/*     */   
/*     */   public boolean isSight() {
/*  77 */     return this.isSight;
/*     */   }
/*     */   
/*     */   public void setScopeViewRadiusModifier(float scopeViewRadiusModifier) {
/*  81 */     this.scopeViewRadiusModifier = scopeViewRadiusModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(PoseStack matrixStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay) {
/*  86 */     if (transformType.m_269069_()) {
/*  87 */       if (this.isScope) {
/*  88 */         renderScope(matrixStack, transformType, renderType, light, overlay);
/*  89 */       } else if (this.isSight) {
/*  90 */         renderSight(matrixStack, transformType, renderType, light, overlay);
/*     */       } 
/*     */     } else {
/*  93 */       if (this.scopeBodyPath != null) {
/*  94 */         renderTempPart(matrixStack, transformType, renderType, light, overlay, this.scopeBodyPath);
/*     */       }
/*  96 */       if (this.ocularRingPath != null) {
/*  97 */         renderTempPart(matrixStack, transformType, renderType, light, overlay, this.ocularRingPath);
/*     */       }
/*     */     } 
/* 100 */     super.render(matrixStack, transformType, renderType, light, overlay);
/*     */   }
/*     */   
/*     */   private void renderSight(PoseStack matrixStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay) {
/* 104 */     RenderHelper.enableItemEntityStencilTest();
/* 105 */     if (this.ocularNodePath != null) {
/* 106 */       RenderSystem.colorMask(false, false, false, false);
/* 107 */       RenderSystem.depthMask(false);
/*     */       
/* 109 */       RenderSystem.clearStencil(0);
/* 110 */       RenderSystem.clear(1024, Minecraft.f_91002_);
/* 111 */       RenderSystem.stencilMask(255);
/* 112 */       RenderSystem.stencilFunc(519, 1, 255);
/* 113 */       RenderSystem.stencilOp(7680, 7680, 7681);
/*     */       
/* 115 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.ocularNodePath);
/*     */       
/* 117 */       RenderSystem.stencilOp(7680, 7680, 7680);
/* 118 */       RenderSystem.depthMask(true);
/* 119 */       RenderSystem.colorMask(true, true, true, true);
/*     */     } 
/*     */     
/* 122 */     if (this.divisionNodePath != null) {
/* 123 */       RenderSystem.stencilFunc(514, 1, 255);
/* 124 */       RenderSystem.disableDepthTest();
/* 125 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.divisionNodePath);
/* 126 */       RenderSystem.enableDepthTest();
/*     */     } 
/* 128 */     RenderSystem.stencilFunc(519, 0, 255);
/* 129 */     RenderHelper.disableItemEntityStencilTest();
/*     */     
/* 131 */     if (this.scopeBodyPath != null) {
/* 132 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.scopeBodyPath);
/*     */     }
/* 134 */     super.render(matrixStack, transformType, renderType, light, overlay);
/*     */   }
/*     */   
/*     */   private Vector3f getBedrockPartCenter(PoseStack poseStack, @Nonnull List<BedrockPart> path) {
/* 138 */     poseStack.m_85836_();
/* 139 */     for (BedrockPart part : path) {
/* 140 */       part.translateAndRotateAndScale(poseStack);
/*     */     }
/* 142 */     Vector3f result = new Vector3f(poseStack.m_85850_().m_252922_().m30(), poseStack.m_85850_().m_252922_().m31(), poseStack.m_85850_().m_252922_().m32());
/* 143 */     poseStack.m_85849_();
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderTempPart(PoseStack poseStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay, @Nonnull List<BedrockPart> path) {
/* 149 */     poseStack.m_85836_();
/* 150 */     for (int i = 0; i < path.size() - 1; i++) {
/* 151 */       ((BedrockPart)path.get(i)).translateAndRotateAndScale(poseStack);
/*     */     }
/* 153 */     BedrockPart part = path.get(path.size() - 1);
/* 154 */     part.visible = true;
/* 155 */     MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/* 156 */     VertexConsumer vertexConsumer = bufferSource.m_6299_(renderType);
/* 157 */     part.render(poseStack, transformType, vertexConsumer, light, overlay);
/* 158 */     if (!OculusCompat.endBatch(bufferSource)) {
/* 159 */       bufferSource.m_109912_(renderType);
/*     */     }
/* 161 */     part.visible = false;
/* 162 */     poseStack.m_85849_();
/*     */   }
/*     */   
/*     */   private void renderScope(PoseStack matrixStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay) {
/* 166 */     RenderHelper.enableItemEntityStencilTest();
/*     */     
/* 168 */     RenderSystem.clearStencil(0);
/* 169 */     RenderSystem.clear(1024, Minecraft.f_91002_);
/* 170 */     if (this.ocularRingPath != null) {
/* 171 */       RenderSystem.stencilFunc(519, 0, 255);
/* 172 */       RenderSystem.stencilOp(7680, 7680, 7680);
/*     */       
/* 174 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.ocularRingPath);
/*     */     } 
/* 176 */     if (this.ocularNodePath != null) {
/* 177 */       RenderSystem.colorMask(false, false, false, false);
/* 178 */       RenderSystem.depthMask(false);
/* 179 */       RenderSystem.stencilMask(255);
/* 180 */       RenderSystem.stencilFunc(519, 1, 255);
/* 181 */       RenderSystem.stencilOp(7680, 7680, 7681);
/*     */       
/* 183 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.ocularNodePath);
/*     */       
/* 185 */       RenderSystem.stencilOp(7680, 7680, 7680);
/* 186 */       RenderSystem.depthMask(true);
/* 187 */       RenderSystem.colorMask(true, true, true, true);
/*     */     } 
/* 189 */     if (this.scopeBodyPath != null) {
/* 190 */       RenderSystem.stencilFunc(517, 1, 255);
/* 191 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.scopeBodyPath);
/*     */     } 
/* 193 */     BufferBuilder builder = Tesselator.m_85913_().m_85915_();
/*     */     
/* 195 */     RenderSystem.stencilFunc(514, 1, 255);
/* 196 */     RenderSystem.stencilOp(7680, 7680, 7682);
/* 197 */     RenderSystem.colorMask(false, false, false, false);
/* 198 */     RenderSystem.depthMask(false);
/* 199 */     Vector3f ocularCenter = getBedrockPartCenter(matrixStack, this.ocularNodePath);
/* 200 */     float centerX = ocularCenter.x() * 16.0F * 90.0F;
/* 201 */     float centerY = ocularCenter.y() * 16.0F * 90.0F;
/*     */     
/* 203 */     float rad = 80.0F * this.scopeViewRadiusModifier;
/* 204 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 205 */     if (player != null) {
/* 206 */       rad *= IClientPlayerGunOperator.fromLocalPlayer(player).getClientAimingProgress(Minecraft.m_91087_().m_91296_());
/*     */     }
/* 208 */     builder.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
/* 209 */     builder.m_5483_(centerX, centerY, -90.0D).m_6122_(255, 255, 255, 255).m_5752_();
/* 210 */     for (int i = 0; i <= 90; i++) {
/* 211 */       float angle = i * 6.2831855F / 90.0F;
/* 212 */       float sin = Mth.m_14031_(angle);
/* 213 */       float cos = Mth.m_14089_(angle);
/* 214 */       builder.m_5483_((centerX + cos * rad), (centerY + sin * rad), -90.0D).m_6122_(255, 255, 255, 255).m_5752_();
/*     */     } 
/* 216 */     BufferUploader.m_231202_(builder.m_231175_());
/* 217 */     RenderSystem.depthMask(true);
/* 218 */     RenderSystem.colorMask(true, true, true, true);
/*     */     
/* 220 */     RenderSystem.stencilFunc(514, 1, 255);
/* 221 */     RenderSystem.stencilOp(7680, 7680, 7680);
/* 222 */     renderTempPart(matrixStack, transformType, renderType, light, overlay, this.ocularNodePath);
/*     */     
/* 224 */     if (this.divisionNodePath != null) {
/* 225 */       RenderSystem.stencilFunc(514, 2, 255);
/* 226 */       renderTempPart(matrixStack, transformType, renderType, light, overlay, this.divisionNodePath);
/*     */     } 
/* 228 */     RenderSystem.stencilFunc(519, 0, 255);
/* 229 */     RenderHelper.disableItemEntityStencilTest();
/*     */     
/* 231 */     super.render(matrixStack, transformType, renderType, light, overlay);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\BedrockAttachmentModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */