/*     */ package com.tacz.guns.client.model;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.client.animation.AnimationListener;
/*     */ import com.tacz.guns.api.client.animation.ObjectAnimationChannel;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*     */ import com.tacz.guns.client.model.bedrock.ModelRendererWrapper;
/*     */ import com.tacz.guns.client.model.functional.AttachmentRender;
/*     */ import com.tacz.guns.client.model.functional.LeftHandRender;
/*     */ import com.tacz.guns.client.model.functional.MuzzleFlashRender;
/*     */ import com.tacz.guns.client.model.functional.RightHandRender;
/*     */ import com.tacz.guns.client.model.functional.ShellRender;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.TextShow;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockModelPOJO;
/*     */ import com.tacz.guns.client.resource.pojo.model.BedrockVersion;
/*     */ import com.tacz.guns.util.RenderHelper;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class BedrockGunModel extends BedrockAnimatedModel {
/*  39 */   protected final EnumMap<AttachmentType, List<BedrockPart>> refitAttachmentViewPath = Maps.newEnumMap(AttachmentType.class);
/*  40 */   private final EnumMap<AttachmentType, ItemStack> currentAttachmentItem = Maps.newEnumMap(AttachmentType.class);
/*  41 */   private final Set<String> adapterToRender = Sets.newHashSet();
/*     */   
/*     */   @Nullable
/*     */   protected List<BedrockPart> ironSightPath;
/*     */   
/*     */   @Nullable
/*     */   protected List<BedrockPart> idleSightPath;
/*     */   @Nullable
/*     */   protected List<BedrockPart> thirdPersonHandOriginPath;
/*     */   @Nullable
/*     */   protected List<BedrockPart> fixedOriginPath;
/*     */   @Nullable
/*     */   protected List<BedrockPart> groundOriginPath;
/*     */   @Nullable
/*     */   protected List<BedrockPart> scopePosPath;
/*     */   @Nullable
/*     */   protected List<BedrockPart> muzzleFlashPosPath;
/*     */   @Nullable
/*     */   protected BedrockPart root;
/*     */   @Nullable
/*     */   protected BedrockPart magazineNode;
/*     */   @Nullable
/*     */   protected BedrockPart additionalMagazineNode;
/*     */   private boolean renderHand = true;
/*     */   private ItemStack currentGunItem;
/*  66 */   private int currentExtendMagLevel = 0;
/*     */   
/*     */   public BedrockGunModel(BedrockModelPOJO pojo, BedrockVersion version) {
/*  69 */     super(pojo, version);
/*     */     
/*  71 */     this.magazineNode = Optional.<ModelRendererWrapper>ofNullable((ModelRendererWrapper)this.modelMap.get("magazine")).map(ModelRendererWrapper::getModelRenderer).orElse(null);
/*  72 */     this.additionalMagazineNode = Optional.<ModelRendererWrapper>ofNullable((ModelRendererWrapper)this.modelMap.get("additional_magazine")).map(ModelRendererWrapper::getModelRenderer).orElse(null);
/*     */ 
/*     */     
/*  75 */     setFunctionalRenderer("lefthand_pos", bedrockPart -> new LeftHandRender(this));
/*     */     
/*  77 */     setFunctionalRenderer("righthand_pos", bedrockPart -> new RightHandRender(this));
/*     */     
/*  79 */     setFunctionalRenderer("muzzle_flash", bedrockPart -> new MuzzleFlashRender(this));
/*     */     
/*  81 */     setFunctionalRenderer("shell", bedrockPart -> new ShellRender(this));
/*     */     
/*  83 */     setFunctionalRenderer("bullet_in_barrel", bedrockPart -> ammoHiddenRender(bedrockPart, ()));
/*     */     
/*  85 */     setFunctionalRenderer("bullet_in_mag", bedrockPart -> ammoHiddenRender(bedrockPart, ()));
/*     */     
/*  87 */     setFunctionalRenderer("bullet_chain", bedrockPart -> ammoHiddenRender(bedrockPart, ()));
/*     */     
/*  89 */     setFunctionalRenderer("mount", bedrockPart -> scopeHiddenRender(bedrockPart, ()));
/*     */     
/*  91 */     setFunctionalRenderer("carry", bedrockPart -> scopeHiddenRender(bedrockPart, ()));
/*     */     
/*  93 */     setFunctionalRenderer("sight_folded", bedrockPart -> scopeHiddenRender(bedrockPart, ()));
/*     */     
/*  95 */     setFunctionalRenderer("sight", bedrockPart -> scopeHiddenRender(bedrockPart, ()));
/*     */     
/*  97 */     setFunctionalRenderer("mag_extended_1", bedrockPart -> extendedMagHiddenRender(bedrockPart, 1));
/*     */     
/*  99 */     setFunctionalRenderer("mag_extended_2", bedrockPart -> extendedMagHiddenRender(bedrockPart, 2));
/*     */     
/* 101 */     setFunctionalRenderer("mag_extended_3", bedrockPart -> extendedMagHiddenRender(bedrockPart, 3));
/*     */     
/* 103 */     setFunctionalRenderer("mag_standard", bedrockPart -> extendedMagHiddenRender(bedrockPart, 0));
/*     */     
/* 105 */     setFunctionalRenderer("additional_magazine", this::renderAdditionalMagazine);
/*     */     
/* 107 */     setFunctionalRenderer("handguard_default", this::handguardDefaultRender);
/*     */     
/* 109 */     setFunctionalRenderer("handguard_tactical", this::handguardTacticalRender);
/*     */     
/* 111 */     cacheOtherPath();
/*     */     
/* 113 */     cacheRefitAttachmentViewPath();
/*     */     
/* 115 */     allAttachmentRender();
/*     */     
/* 117 */     setFunctionalRenderer("attachment_adapter", this::attachmentAdapterNodeRender);
/*     */   }
/*     */   
/*     */   private void cacheOtherPath() {
/* 121 */     this.ironSightPath = getPath((ModelRendererWrapper)this.modelMap.get("iron_view"));
/* 122 */     this.idleSightPath = getPath((ModelRendererWrapper)this.modelMap.get("idle_view"));
/* 123 */     this.thirdPersonHandOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("thirdperson_hand"));
/* 124 */     this.fixedOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("fixed"));
/* 125 */     this.groundOriginPath = getPath((ModelRendererWrapper)this.modelMap.get("ground"));
/* 126 */     this.muzzleFlashPosPath = getPath((ModelRendererWrapper)this.modelMap.get("muzzle_flash"));
/* 127 */     this.scopePosPath = getPath((ModelRendererWrapper)this.modelMap.get(AttachmentType.SCOPE.name().toLowerCase() + "_pos"));
/* 128 */     this.root = Optional.<ModelRendererWrapper>ofNullable((ModelRendererWrapper)this.modelMap.get("root")).map(ModelRendererWrapper::getModelRenderer).orElse(null);
/*     */   }
/*     */   
/*     */   private void cacheRefitAttachmentViewPath() {
/* 132 */     for (AttachmentType type : AttachmentType.values()) {
/* 133 */       if (type == AttachmentType.NONE) {
/* 134 */         this.refitAttachmentViewPath.put(type, getPath((ModelRendererWrapper)this.modelMap.get("refit_view")));
/*     */       } else {
/*     */         
/* 137 */         String nodeName = "refit_" + type.name().toLowerCase() + "_view";
/* 138 */         this.refitAttachmentViewPath.put(type, getPath((ModelRendererWrapper)this.modelMap.get(nodeName)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   private IFunctionalRenderer attachmentAdapterNodeRender(BedrockPart bedrockPart) {
/* 144 */     for (ObjectListIterator<BedrockPart> objectListIterator = bedrockPart.children.iterator(); objectListIterator.hasNext(); ) { BedrockPart child = objectListIterator.next();
/* 145 */       if (child.name == null) {
/* 146 */         child.visible = false;
/*     */         continue;
/*     */       } 
/* 149 */       child.visible = this.adapterToRender.contains(child.name); }
/*     */     
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   private void allAttachmentRender() {
/* 155 */     for (AttachmentType type : AttachmentType.values()) {
/*     */       
/* 157 */       if (type != AttachmentType.NONE && type != AttachmentType.SCOPE) {
/*     */ 
/*     */         
/* 160 */         String positionNodeName = type.name().toLowerCase() + "_pos";
/* 161 */         String defaultNodeName = type.name().toLowerCase() + "_default";
/* 162 */         setFunctionalRenderer(positionNodeName, bedrockPart -> {
/*     */               bedrockPart.visible = false;
/*     */               return (IFunctionalRenderer)new AttachmentRender(this, type);
/*     */             });
/* 166 */         setFunctionalRenderer(defaultNodeName, bedrockPart -> {
/*     */               ItemStack attachmentItem = this.currentAttachmentItem.get(type);
/*     */               if (type == AttachmentType.MUZZLE && checkShowMuzzle(bedrockPart, attachmentItem)) {
/*     */                 return null;
/*     */               }
/* 171 */               bedrockPart.visible = (attachmentItem == null || attachmentItem.m_41619_());
/*     */               return null;
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static boolean checkShowMuzzle(BedrockPart bedrockPart, ItemStack attachmentItem) {
/* 178 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachmentItem);
/* 179 */     if (iAttachment != null) {
/* 180 */       ResourceLocation attachmentId = iAttachment.getAttachmentId(attachmentItem);
/* 181 */       Optional<ClientAttachmentIndex> attachmentIndex = TimelessAPI.getClientAttachmentIndex(attachmentId);
/* 182 */       if (attachmentIndex.isPresent()) {
/* 183 */         bedrockPart.visible = ((ClientAttachmentIndex)attachmentIndex.get()).isShowMuzzle();
/* 184 */         return true;
/*     */       } 
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IFunctionalRenderer handguardTacticalRender(BedrockPart bedrockPart) {
/* 192 */     ItemStack laserItem = this.currentAttachmentItem.get(AttachmentType.LASER);
/* 193 */     ItemStack gripItem = this.currentAttachmentItem.get(AttachmentType.GRIP);
/* 194 */     bedrockPart.visible = (!laserItem.m_41619_() || !gripItem.m_41619_());
/* 195 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IFunctionalRenderer handguardDefaultRender(BedrockPart bedrockPart) {
/* 200 */     ItemStack laserItem = this.currentAttachmentItem.get(AttachmentType.LASER);
/* 201 */     ItemStack gripItem = this.currentAttachmentItem.get(AttachmentType.GRIP);
/* 202 */     bedrockPart.visible = (laserItem.m_41619_() && gripItem.m_41619_());
/* 203 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private IFunctionalRenderer renderAdditionalMagazine(BedrockPart bedrockPart) {
/* 208 */     return (poseStack, vertexBuffer, transformType, light, overlay) -> {
/*     */         if (bedrockPart.visible) {
/*     */           bedrockPart.compile(poseStack.m_85850_(), vertexBuffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */           ObjectListIterator<BedrockPart> objectListIterator = bedrockPart.children.iterator();
/*     */           while (objectListIterator.hasNext()) {
/*     */             BedrockPart part = objectListIterator.next();
/*     */             part.render(poseStack, transformType, vertexBuffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */           } 
/*     */           if (this.magazineNode != null && this.magazineNode.visible) {
/*     */             this.magazineNode.compile(poseStack.m_85850_(), vertexBuffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */             objectListIterator = this.magazineNode.children.iterator();
/*     */             while (objectListIterator.hasNext()) {
/*     */               BedrockPart part = objectListIterator.next();
/*     */               part.render(poseStack, transformType, vertexBuffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       };
/*     */   }
/*     */   public void setTextShowList(Map<String, TextShow> textShowList) {
/* 228 */     textShowList.forEach((name, textShow) -> setFunctionalRenderer(name, ()));
/*     */   }
/*     */   
/*     */   public void render(PoseStack matrixStack, ItemStack gunItem, ItemDisplayContext transformType, RenderType renderType, int light, int overlay) {
/* 232 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 233 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 236 */     this.currentGunItem = gunItem;
/* 237 */     this.currentExtendMagLevel = 0;
/* 238 */     this.adapterToRender.clear();
/*     */     
/* 240 */     for (AttachmentType type : AttachmentType.values()) {
/* 241 */       if (type != AttachmentType.NONE) {
/*     */ 
/*     */         
/* 244 */         ItemStack itemStack = iGun.getAttachment(gunItem, type);
/* 245 */         if (itemStack.m_41619_()) {
/* 246 */           itemStack = iGun.getBuiltinAttachment(gunItem, type);
/*     */         }
/* 248 */         this.currentAttachmentItem.put(type, itemStack);
/* 249 */         IAttachment attachment = IAttachment.getIAttachmentOrNull(itemStack);
/* 250 */         if (attachment != null) {
/* 251 */           TimelessAPI.getClientAttachmentIndex(attachment.getAttachmentId(itemStack)).ifPresent(index -> {
/*     */                 if (type == AttachmentType.EXTENDED_MAG) {
/*     */                   this.currentExtendMagLevel = index.getData().getExtendedMagLevel();
/*     */                 }
/*     */                 
/*     */                 if (index.getAdapterNodeName() != null) {
/*     */                   this.adapterToRender.add(index.getAdapterNodeName());
/*     */                 }
/*     */               });
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     ItemStack attachmentItem = this.currentAttachmentItem.get(AttachmentType.SCOPE);
/* 265 */     IAttachment iAttachment = IAttachment.getIAttachmentOrNull(attachmentItem);
/* 266 */     if (this.scopePosPath != null && attachmentItem != null && !attachmentItem.m_41619_()) {
/* 267 */       matrixStack.m_85836_();
/* 268 */       for (BedrockPart bedrockPart : this.scopePosPath) {
/* 269 */         bedrockPart.translateAndRotateAndScale(matrixStack);
/*     */       }
/* 271 */       AttachmentRender.renderAttachment(attachmentItem, matrixStack, transformType, light, overlay);
/* 272 */       matrixStack.m_85849_();
/*     */       
/* 274 */       if (iAttachment != null) {
/* 275 */         Optional<ClientAttachmentIndex> attachmentIndex = TimelessAPI.getClientAttachmentIndex(iAttachment.getAttachmentId(attachmentItem));
/* 276 */         attachmentIndex.ifPresent(index -> {
/*     */               if (index.isScope()) {
/*     */                 RenderHelper.enableItemEntityStencilTest();
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/* 283 */     RenderSystem.stencilFunc(514, 0, 255);
/* 284 */     RenderSystem.stencilOp(7680, 7680, 7680);
/* 285 */     render(matrixStack, transformType, renderType, light, overlay);
/* 286 */     RenderHelper.disableItemEntityStencilTest();
/* 287 */     RenderSystem.clearStencil(0);
/* 288 */     RenderSystem.clear(1024, Minecraft.f_91002_);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IFunctionalRenderer ammoHiddenRender(BedrockPart bedrockPart, Predicate<IGun> predicate) {
/* 293 */     IGun iGun = IGun.getIGunOrNull(this.currentGunItem);
/* 294 */     if (iGun != null) {
/* 295 */       bedrockPart.visible = predicate.test(iGun);
/*     */     }
/* 297 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private IFunctionalRenderer scopeHiddenRender(BedrockPart bedrockPart, Predicate<ItemStack> predicate) {
/* 303 */     ItemStack scopeItem = this.currentAttachmentItem.get(AttachmentType.SCOPE);
/* 304 */     bedrockPart.visible = predicate.test(scopeItem);
/* 305 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IFunctionalRenderer extendedMagHiddenRender(BedrockPart bedrockPart, int level) {
/* 310 */     bedrockPart.visible = (this.currentExtendMagLevel == level);
/* 311 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnimationListener supplyListeners(String nodeName, ObjectAnimationChannel.ChannelType type) {
/* 316 */     AnimationListener listener = super.supplyListeners(nodeName, type);
/* 317 */     if (listener == null) {
/* 318 */       return null;
/*     */     }
/* 320 */     if (nodeName.equals("additional_magazine"))
/*     */     {
/* 322 */       return (AnimationListener)new ModelAdditionalMagazineListener(listener, this);
/*     */     }
/* 324 */     return listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cleanAnimationTransform() {
/* 329 */     super.cleanAnimationTransform();
/* 330 */     if (this.additionalMagazineNode != null) {
/* 331 */       this.additionalMagazineNode.visible = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public EnumMap<AttachmentType, ItemStack> getCurrentAttachmentItem() {
/* 336 */     return this.currentAttachmentItem;
/*     */   }
/*     */   
/*     */   public ItemStack getCurrentGunItem() {
/* 340 */     return this.currentGunItem;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockPart getAdditionalMagazineNode() {
/* 345 */     return this.additionalMagazineNode;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getIronSightPath() {
/* 350 */     return this.ironSightPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getIdleSightPath() {
/* 355 */     return this.idleSightPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getThirdPersonHandOriginPath() {
/* 360 */     return this.thirdPersonHandOriginPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getFixedOriginPath() {
/* 365 */     return this.fixedOriginPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getGroundOriginPath() {
/* 370 */     return this.groundOriginPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getMuzzleFlashPosPath() {
/* 375 */     return this.muzzleFlashPosPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getScopePosPath() {
/* 380 */     return this.scopePosPath;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<BedrockPart> getRefitAttachmentViewPath(AttachmentType type) {
/* 385 */     return this.refitAttachmentViewPath.get(type);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BedrockPart getRootNode() {
/* 390 */     return this.root;
/*     */   }
/*     */   
/*     */   public boolean getRenderHand() {
/* 394 */     return this.renderHand;
/*     */   }
/*     */   
/*     */   public void setRenderHand(boolean renderHand) {
/* 398 */     this.renderHand = renderHand;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\BedrockGunModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */