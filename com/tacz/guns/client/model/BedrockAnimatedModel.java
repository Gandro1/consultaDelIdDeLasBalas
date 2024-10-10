/*     */ package com.tacz.guns.client.model;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.AnimationListener;
/*     */ import com.tacz.guns.api.client.animation.AnimationListenerSupplier;
/*     */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockModel;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*     */ import com.tacz.guns.client.model.listener.camera.CameraAnimationObject;
/*     */ import com.tacz.guns.client.model.listener.constraint.ConstraintObject;
/*     */ import com.tacz.guns.client.model.listener.model.ModelRotateListener;
/*     */ import com.tacz.guns.client.model.listener.model.ModelScaleListener;
/*     */ import com.tacz.guns.client.model.listener.model.ModelTranslateListener;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.client.resource.pojo.model.BonesItem;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ public class BedrockAnimatedModel
/*     */   extends BedrockModel implements AnimationListenerSupplier {
/*     */   public static final String CAMERA_NODE_NAME = "camera";
/*     */   public static final String CONSTRAINT_NODE = "constraint";
/*  27 */   private final CameraAnimationObject cameraAnimationObject = new CameraAnimationObject();
/*     */   
/*     */   @Nullable
/*     */   protected List<BedrockPart> constraintPath;
/*     */   @Nullable
/*     */   private ConstraintObject constraintObject;
/*     */   
/*     */   public BedrockAnimatedModel(BedrockModelPOJO pojo, BedrockVersion version) {
/*  35 */     super(pojo, version);
/*     */     
/*  37 */     ModelRendererWrapper cameraRendererWrapper = (ModelRendererWrapper)this.modelMap.get("camera");
/*  38 */     if (cameraRendererWrapper != null) {
/*  39 */       this.cameraAnimationObject.cameraRenderer = cameraRendererWrapper;
/*     */     }
/*     */     
/*  42 */     this.constraintPath = getPath((ModelRendererWrapper)this.modelMap.get("constraint"));
/*  43 */     if (this.constraintPath != null) {
/*  44 */       this.constraintObject = new ConstraintObject();
/*  45 */       BedrockPart constraintNode = this.constraintPath.get(this.constraintPath.size() - 1);
/*  46 */       if (this.shouldRender.contains(constraintNode)) {
/*  47 */         this.constraintObject.bonesItem = (BonesItem)this.indexBones.get("constraint");
/*     */       } else {
/*  49 */         this.constraintObject.node = constraintNode;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getConstraintPath() {
/*  56 */     return this.constraintPath;
/*     */   }
/*     */   
/*     */   public void cleanAnimationTransform() {
/*  60 */     for (ModelRendererWrapper rendererWrapper : this.modelMap.values()) {
/*  61 */       rendererWrapper.setOffsetX(0.0F);
/*  62 */       rendererWrapper.setOffsetY(0.0F);
/*  63 */       rendererWrapper.setOffsetZ(0.0F);
/*  64 */       rendererWrapper.getAdditionalQuaternion().set(0.0F, 0.0F, 0.0F, 1.0F);
/*  65 */       rendererWrapper.setScaleX(1.0F);
/*  66 */       rendererWrapper.setScaleY(1.0F);
/*  67 */       rendererWrapper.setScaleZ(1.0F);
/*     */     } 
/*  69 */     if (this.constraintObject != null) {
/*  70 */       this.constraintObject.rotationConstraint.set(0.0F, 0.0F, 0.0F);
/*  71 */       this.constraintObject.translationConstraint.set(0.0F, 0.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cleanCameraAnimationTransform() {
/*  76 */     this.cameraAnimationObject.rotationQuaternion = new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionalRenderer(String node, Function<BedrockPart, IFunctionalRenderer> function) {
/*  84 */     ModelRendererWrapper wrapper = (ModelRendererWrapper)this.modelMap.get(node);
/*  85 */     if (wrapper == null)
/*  86 */     { FunctionalBedrockPart functionalPart = new FunctionalBedrockPart(function, node);
/*  87 */       this.modelMap.put(node, new ModelRendererWrapper(functionalPart)); }
/*  88 */     else { BedrockPart bedrockPart = wrapper.getModelRenderer(); if (bedrockPart instanceof FunctionalBedrockPart) { FunctionalBedrockPart functionalPart = (FunctionalBedrockPart)bedrockPart;
/*  89 */         functionalPart.functionalRenderer = function; }
/*     */        }
/*     */   
/*     */   }
/*     */   @Nonnull
/*     */   public CameraAnimationObject getCameraAnimationObject() {
/*  95 */     return this.cameraAnimationObject;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ConstraintObject getConstraintObject() {
/* 100 */     return this.constraintObject;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadNewModel(BedrockModelPOJO pojo) {
/* 105 */     assert pojo.getGeometryModelNew() != null;
/* 106 */     pojo.getGeometryModelNew().deco();
/* 107 */     if (pojo.getGeometryModelNew().getBones() == null) {
/*     */       return;
/*     */     }
/* 110 */     for (BonesItem bones : pojo.getGeometryModelNew().getBones()) {
/*     */       
/* 112 */       FunctionalBedrockPart bedrockPart = new FunctionalBedrockPart(null, bones.getName());
/* 113 */       this.modelMap.putIfAbsent(bones.getName(), new ModelRendererWrapper(bedrockPart));
/*     */     } 
/* 115 */     super.loadNewModel(pojo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadLegacyModel(BedrockModelPOJO pojo) {
/* 120 */     assert pojo.getGeometryModelLegacy() != null;
/* 121 */     pojo.getGeometryModelLegacy().deco();
/* 122 */     if (pojo.getGeometryModelLegacy().getBones() == null) {
/*     */       return;
/*     */     }
/* 125 */     for (BonesItem bones : pojo.getGeometryModelLegacy().getBones()) {
/*     */       
/* 127 */       FunctionalBedrockPart bedrockPart = new FunctionalBedrockPart(null, bones.getName());
/* 128 */       this.modelMap.putIfAbsent(bones.getName(), new ModelRendererWrapper(bedrockPart));
/*     */     } 
/* 130 */     super.loadLegacyModel(pojo);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnimationListener supplyListeners(String nodeName, ObjectAnimationChannel.ChannelType type) {
/* 135 */     ModelRendererWrapper model = (ModelRendererWrapper)this.modelMap.get(nodeName);
/* 136 */     if (model == null) {
/* 137 */       return null;
/*     */     }
/* 139 */     AnimationListener cameraListener = this.cameraAnimationObject.supplyListeners(nodeName, type);
/* 140 */     if (cameraListener != null) {
/* 141 */       return cameraListener;
/*     */     }
/* 143 */     if (this.constraintObject != null) {
/* 144 */       AnimationListener constraintListener = this.constraintObject.supplyListeners(nodeName, type);
/* 145 */       if (constraintListener != null) {
/* 146 */         return constraintListener;
/*     */       }
/*     */     } 
/* 149 */     if (type.equals(ObjectAnimationChannel.ChannelType.TRANSLATION)) {
/* 150 */       return (AnimationListener)new ModelTranslateListener(this, model, nodeName);
/*     */     }
/* 152 */     if (type.equals(ObjectAnimationChannel.ChannelType.ROTATION)) {
/* 153 */       return (AnimationListener)new ModelRotateListener(model);
/*     */     }
/* 155 */     if (type.equals(ObjectAnimationChannel.ChannelType.SCALE)) {
/* 156 */       return (AnimationListener)new ModelScaleListener(model);
/*     */     }
/* 158 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\BedrockAnimatedModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */