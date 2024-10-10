/*     */ package com.tacz.guns.client.model.bedrock;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.tacz.guns.client.model.IFunctionalRenderer;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.client.resource.pojo.model.BonesItem;
/*     */ import com.tacz.guns.client.resource.pojo.model.CubesItem;
/*     */ import com.tacz.guns.client.resource.pojo.model.Description;
/*     */ import com.tacz.guns.client.resource.pojo.model.FaceUVsItem;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ 
/*     */ public class BedrockModel {
/*  19 */   public static BedrockModel dummyModel = new BedrockModel();
/*     */ 
/*     */ 
/*     */   
/*  23 */   protected final HashMap<String, ModelRendererWrapper> modelMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*  27 */   protected final HashMap<String, BonesItem> indexBones = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*  31 */   protected final List<BedrockPart> shouldRender = new LinkedList<>();
/*     */ 
/*     */ 
/*     */   
/*  35 */   protected List<IFunctionalRenderer> delegateRenderers = new ArrayList<>();
/*     */ 
/*     */   
/*     */   @Nullable
/*  39 */   protected Vec3 offset = null;
/*     */ 
/*     */   
/*     */   @Nullable
/*  43 */   protected Vec2 size = null;
/*     */   
/*     */   public BedrockModel(BedrockModelPOJO pojo, BedrockVersion version) {
/*  46 */     if (version == BedrockVersion.LEGACY) {
/*  47 */       loadLegacyModel(pojo);
/*     */     }
/*  49 */     if (version == BedrockVersion.NEW) {
/*  50 */       loadNewModel(pojo);
/*     */     }
/*     */     
/*  53 */     for (ModelRendererWrapper rendererWrapper : this.modelMap.values()) {
/*  54 */       if ((rendererWrapper.getModelRenderer()).name != null && (rendererWrapper.getModelRenderer()).name.endsWith("_illuminated")) {
/*  55 */         (rendererWrapper.getModelRenderer()).illuminated = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected BedrockModel() {}
/*     */   
/*     */   public void delegateRender(IFunctionalRenderer renderer) {
/*  64 */     this.delegateRenderers.add(renderer);
/*     */   }
/*     */   
/*     */   private void setRotationAngle(BedrockPart modelRenderer, float x, float y, float z) {
/*  68 */     modelRenderer.xRot = x;
/*  69 */     modelRenderer.yRot = y;
/*  70 */     modelRenderer.zRot = z;
/*  71 */     modelRenderer.setInitRotationAngle(x, y, z);
/*     */   }
/*     */   
/*     */   protected void loadNewModel(BedrockModelPOJO pojo) {
/*  75 */     assert pojo.getGeometryModelNew() != null;
/*  76 */     pojo.getGeometryModelNew().deco();
/*  77 */     if (pojo.getGeometryModelNew().getBones() == null) {
/*     */       return;
/*     */     }
/*  80 */     Description description = pojo.getGeometryModelNew().getDescription();
/*     */     
/*  82 */     int texWidth = description.getTextureWidth();
/*  83 */     int texHeight = description.getTextureHeight();
/*     */     
/*  85 */     List<Float> offset = description.getVisibleBoundsOffset();
/*  86 */     float offsetX = ((Float)offset.get(0)).floatValue();
/*  87 */     float offsetY = ((Float)offset.get(1)).floatValue();
/*  88 */     float offsetZ = ((Float)offset.get(2)).floatValue();
/*  89 */     this.offset = new Vec3(offsetX, offsetY, offsetZ);
/*  90 */     float width = description.getVisibleBoundsWidth() / 2.0F;
/*  91 */     float height = description.getVisibleBoundsHeight() / 2.0F;
/*  92 */     this.size = new Vec2(width, height);
/*     */ 
/*     */     
/*  95 */     for (BonesItem bones : pojo.getGeometryModelNew().getBones()) {
/*     */       
/*  97 */       this.indexBones.putIfAbsent(bones.getName(), bones);
/*     */ 
/*     */       
/* 100 */       this.modelMap.putIfAbsent(bones.getName(), new ModelRendererWrapper(new BedrockPart(bones.getName())));
/*     */     } 
/*     */ 
/*     */     
/* 104 */     for (BonesItem bones : pojo.getGeometryModelNew().getBones()) {
/*     */       
/* 106 */       String name = bones.getName();
/*     */       
/* 108 */       List<Float> rotation = bones.getRotation();
/*     */       
/* 110 */       String parent = bones.getParent();
/*     */       
/* 112 */       BedrockPart model = ((ModelRendererWrapper)this.modelMap.get(name)).getModelRenderer();
/*     */ 
/*     */       
/* 115 */       model.mirror = bones.isMirror();
/*     */ 
/*     */       
/* 118 */       model.setPos(convertPivot(bones, 0), convertPivot(bones, 1), convertPivot(bones, 2));
/*     */ 
/*     */       
/* 121 */       if (rotation != null) {
/* 122 */         setRotationAngle(model, convertRotation(((Float)rotation.get(0)).floatValue()), convertRotation(((Float)rotation.get(1)).floatValue()), convertRotation(((Float)rotation.get(2)).floatValue()));
/*     */       }
/*     */ 
/*     */       
/* 126 */       if (parent != null) {
/* 127 */         BedrockPart parentPart = ((ModelRendererWrapper)this.modelMap.get(parent)).getModelRenderer();
/* 128 */         parentPart.addChild(model);
/* 129 */         model.parent = parentPart;
/*     */       } else {
/*     */         
/* 132 */         this.shouldRender.add(model);
/* 133 */         model.parent = null;
/*     */       } 
/*     */ 
/*     */       
/* 137 */       if (bones.getCubes() == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 142 */       for (CubesItem cube : bones.getCubes()) {
/* 143 */         List<Float> uv = cube.getUv();
/* 144 */         FaceUVsItem faceUv = cube.getFaceUv();
/* 145 */         List<Float> size = cube.getSize();
/* 146 */         List<Float> cubeRotation = cube.getRotation();
/* 147 */         boolean mirror = cube.isMirror();
/* 148 */         float inflate = cube.getInflate();
/*     */ 
/*     */         
/* 151 */         if (cubeRotation == null) {
/* 152 */           if (faceUv == null) {
/* 153 */             model.cubes.add(new BedrockCubeBox(((Float)uv.get(0)).floatValue(), ((Float)uv.get(1)).floatValue(), 
/* 154 */                   convertOrigin(bones, cube, 0), convertOrigin(bones, cube, 1), convertOrigin(bones, cube, 2), ((Float)size
/* 155 */                   .get(0)).floatValue(), ((Float)size.get(1)).floatValue(), ((Float)size.get(2)).floatValue(), inflate, mirror, texWidth, texHeight));
/*     */             continue;
/*     */           } 
/* 158 */           model.cubes.add(new BedrockCubePerFace(
/* 159 */                 convertOrigin(bones, cube, 0), convertOrigin(bones, cube, 1), convertOrigin(bones, cube, 2), ((Float)size
/* 160 */                 .get(0)).floatValue(), ((Float)size.get(1)).floatValue(), ((Float)size.get(2)).floatValue(), inflate, texWidth, texHeight, faceUv));
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 166 */         BedrockPart cubeRenderer = new BedrockPart(null);
/* 167 */         cubeRenderer.setPos(convertPivot(bones, cube, 0), convertPivot(bones, cube, 1), convertPivot(bones, cube, 2));
/* 168 */         setRotationAngle(cubeRenderer, convertRotation(((Float)cubeRotation.get(0)).floatValue()), convertRotation(((Float)cubeRotation.get(1)).floatValue()), convertRotation(((Float)cubeRotation.get(2)).floatValue()));
/* 169 */         if (faceUv == null) {
/* 170 */           cubeRenderer.cubes.add(new BedrockCubeBox(((Float)uv.get(0)).floatValue(), ((Float)uv.get(1)).floatValue(), 
/* 171 */                 convertOrigin(cube, 0), convertOrigin(cube, 1), convertOrigin(cube, 2), ((Float)size
/* 172 */                 .get(0)).floatValue(), ((Float)size.get(1)).floatValue(), ((Float)size.get(2)).floatValue(), inflate, mirror, texWidth, texHeight));
/*     */         } else {
/*     */           
/* 175 */           cubeRenderer.cubes.add(new BedrockCubePerFace(
/* 176 */                 convertOrigin(cube, 0), convertOrigin(cube, 1), convertOrigin(cube, 2), ((Float)size
/* 177 */                 .get(0)).floatValue(), ((Float)size.get(1)).floatValue(), ((Float)size.get(2)).floatValue(), inflate, texWidth, texHeight, faceUv));
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 182 */         model.addChild(cubeRenderer);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadLegacyModel(BedrockModelPOJO pojo) {
/* 189 */     assert pojo.getGeometryModelLegacy() != null;
/* 190 */     pojo.getGeometryModelLegacy().deco();
/* 191 */     if (pojo.getGeometryModelLegacy().getBones() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 196 */     int texWidth = pojo.getGeometryModelLegacy().getTextureWidth();
/* 197 */     int texHeight = pojo.getGeometryModelLegacy().getTextureHeight();
/*     */     
/* 199 */     List<Float> offset = pojo.getGeometryModelLegacy().getVisibleBoundsOffset();
/* 200 */     float offsetX = ((Float)offset.get(0)).floatValue();
/* 201 */     float offsetY = ((Float)offset.get(1)).floatValue();
/* 202 */     float offsetZ = ((Float)offset.get(2)).floatValue();
/* 203 */     this.offset = new Vec3(offsetX, offsetY, offsetZ);
/* 204 */     float width = pojo.getGeometryModelLegacy().getVisibleBoundsWidth() / 2.0F;
/* 205 */     float height = pojo.getGeometryModelLegacy().getVisibleBoundsHeight() / 2.0F;
/* 206 */     this.size = new Vec2(width, height);
/*     */ 
/*     */     
/* 209 */     for (BonesItem bones : pojo.getGeometryModelLegacy().getBones()) {
/*     */       
/* 211 */       this.indexBones.putIfAbsent(bones.getName(), bones);
/*     */ 
/*     */       
/* 214 */       this.modelMap.putIfAbsent(bones.getName(), new ModelRendererWrapper(new BedrockPart(bones.getName())));
/*     */     } 
/*     */ 
/*     */     
/* 218 */     for (BonesItem bones : pojo.getGeometryModelLegacy().getBones()) {
/*     */       
/* 220 */       String name = bones.getName();
/*     */       
/* 222 */       List<Float> rotation = bones.getRotation();
/*     */       
/* 224 */       String parent = bones.getParent();
/*     */       
/* 226 */       BedrockPart model = ((ModelRendererWrapper)this.modelMap.get(name)).getModelRenderer();
/*     */ 
/*     */       
/* 229 */       model.mirror = bones.isMirror();
/*     */ 
/*     */       
/* 232 */       model.setPos(convertPivot(bones, 0), convertPivot(bones, 1), convertPivot(bones, 2));
/*     */ 
/*     */       
/* 235 */       if (rotation != null) {
/* 236 */         setRotationAngle(model, convertRotation(((Float)rotation.get(0)).floatValue()), convertRotation(((Float)rotation.get(1)).floatValue()), convertRotation(((Float)rotation.get(2)).floatValue()));
/*     */       }
/*     */ 
/*     */       
/* 240 */       if (parent != null) {
/* 241 */         ((ModelRendererWrapper)this.modelMap.get(parent)).getModelRenderer().addChild(model);
/*     */       } else {
/*     */         
/* 244 */         this.shouldRender.add(model);
/*     */       } 
/*     */ 
/*     */       
/* 248 */       if (bones.getCubes() == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 253 */       for (CubesItem cube : bones.getCubes()) {
/* 254 */         List<Float> uv = cube.getUv();
/* 255 */         List<Float> size = cube.getSize();
/* 256 */         boolean mirror = cube.isMirror();
/* 257 */         float inflate = cube.getInflate();
/*     */         
/* 259 */         model.cubes.add(new BedrockCubeBox(((Float)uv.get(0)).floatValue(), ((Float)uv.get(1)).floatValue(), 
/* 260 */               convertOrigin(bones, cube, 0), convertOrigin(bones, cube, 1), convertOrigin(bones, cube, 2), ((Float)size
/* 261 */               .get(0)).floatValue(), ((Float)size.get(1)).floatValue(), ((Float)size.get(2)).floatValue(), inflate, mirror, texWidth, texHeight));
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */   protected float convertPivot(BonesItem bones, int index) {
/* 281 */     if (bones.getParent() != null) {
/* 282 */       if (index == 1) {
/* 283 */         return ((Float)((BonesItem)this.indexBones.get(bones.getParent())).getPivot().get(index)).floatValue() - ((Float)bones.getPivot().get(index)).floatValue();
/*     */       }
/* 285 */       return ((Float)bones.getPivot().get(index)).floatValue() - ((Float)((BonesItem)this.indexBones.get(bones.getParent())).getPivot().get(index)).floatValue();
/*     */     } 
/*     */     
/* 288 */     if (index == 1) {
/* 289 */       return 24.0F - ((Float)bones.getPivot().get(index)).floatValue();
/*     */     }
/* 291 */     return ((Float)bones.getPivot().get(index)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float convertPivot(BonesItem parent, CubesItem cube, int index) {
/* 297 */     assert cube.getPivot() != null;
/* 298 */     if (index == 1) {
/* 299 */       return ((Float)parent.getPivot().get(index)).floatValue() - ((Float)cube.getPivot().get(index)).floatValue();
/*     */     }
/* 301 */     return ((Float)cube.getPivot().get(index)).floatValue() - ((Float)parent.getPivot().get(index)).floatValue();
/*     */   }
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
/*     */   protected float convertOrigin(BonesItem bone, CubesItem cube, int index) {
/* 315 */     if (index == 1) {
/* 316 */       return ((Float)bone.getPivot().get(index)).floatValue() - ((Float)cube.getOrigin().get(index)).floatValue() - ((Float)cube.getSize().get(index)).floatValue();
/*     */     }
/* 318 */     return ((Float)cube.getOrigin().get(index)).floatValue() - ((Float)bone.getPivot().get(index)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float convertOrigin(CubesItem cube, int index) {
/* 323 */     assert cube.getPivot() != null;
/* 324 */     if (index == 1) {
/* 325 */       return ((Float)cube.getPivot().get(index)).floatValue() - ((Float)cube.getOrigin().get(index)).floatValue() - ((Float)cube.getSize().get(index)).floatValue();
/*     */     }
/* 327 */     return ((Float)cube.getOrigin().get(index)).floatValue() - ((Float)cube.getPivot().get(index)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float convertRotation(float degree) {
/* 335 */     return (float)(degree * Math.PI / 180.0D);
/*     */   }
/*     */   
/*     */   public BedrockPart getNode(String nodeName) {
/* 339 */     ModelRendererWrapper rendererWrapper = this.modelMap.get(nodeName);
/* 340 */     if (rendererWrapper != null) {
/* 341 */       return rendererWrapper.getModelRenderer();
/*     */     }
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BonesItem getBone(String name) {
/* 348 */     return this.indexBones.get(name);
/*     */   }
/*     */   
/*     */   public void render(PoseStack matrixStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay) {
/* 352 */     render(matrixStack, transformType, renderType, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void render(PoseStack matrixStack, ItemDisplayContext transformType, RenderType renderType, int light, int overlay, float red, float green, float blue, float alpha) {
/* 356 */     MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/* 357 */     VertexConsumer builder = bufferSource.m_6299_(renderType);
/*     */     
/* 359 */     matrixStack.m_85836_();
/* 360 */     for (BedrockPart model : this.shouldRender) {
/* 361 */       model.render(matrixStack, transformType, builder, light, overlay, red, green, blue, alpha);
/*     */     }
/* 363 */     matrixStack.m_85849_();
/* 364 */     if (!OculusCompat.endBatch(bufferSource)) {
/* 365 */       bufferSource.m_109912_(renderType);
/*     */     }
/*     */     
/* 368 */     for (IFunctionalRenderer renderer : this.delegateRenderers) {
/* 369 */       renderer.render(matrixStack, builder, transformType, light, overlay);
/*     */     }
/* 371 */     this.delegateRenderers = new ArrayList<>();
/*     */   }
/*     */   
/*     */   protected List<BedrockPart> getPath(@Nullable ModelRendererWrapper rendererWrapper) {
/* 375 */     if (rendererWrapper == null) {
/* 376 */       return null;
/*     */     }
/* 378 */     BedrockPart part = rendererWrapper.getModelRenderer();
/* 379 */     List<BedrockPart> path = new ArrayList<>();
/* 380 */     Stack<BedrockPart> stack = new Stack<>();
/*     */     do {
/* 382 */       stack.push(part);
/* 383 */       part = part.getParent();
/* 384 */     } while (part != null);
/* 385 */     while (!stack.isEmpty()) {
/* 386 */       part = stack.pop();
/* 387 */       path.add(part);
/*     */     } 
/* 389 */     return path;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3 getOffset() {
/* 394 */     return this.offset;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec2 getSize() {
/* 399 */     return this.size;
/*     */   }
/*     */   
/*     */   public List<BedrockPart> getShouldRender() {
/* 403 */     return this.shouldRender;
/*     */   }
/*     */   
/*     */   public HashMap<String, BonesItem> getIndexBones() {
/* 407 */     return this.indexBones;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */