/*     */ package com.tacz.guns.client.model.bedrock;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.renderer.LightTexture;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class BedrockPart {
/*     */   @Nullable
/*     */   public final String name;
/*  21 */   public final ObjectList<BedrockCube> cubes = (ObjectList<BedrockCube>)new ObjectArrayList();
/*  22 */   public final ObjectList<BedrockPart> children = (ObjectList<BedrockPart>)new ObjectArrayList();
/*     */   
/*     */   public float x;
/*     */   
/*     */   public float y;
/*     */   
/*     */   public float z;
/*     */   public float xRot;
/*     */   public float yRot;
/*     */   public float zRot;
/*     */   public float offsetX;
/*     */   public float offsetY;
/*     */   public float offsetZ;
/*     */   public boolean visible = true;
/*     */   public boolean illuminated = false;
/*     */   public boolean mirror;
/*  38 */   public Quaternionf additionalQuaternion = new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F);
/*  39 */   public float xScale = 1.0F;
/*  40 */   public float yScale = 1.0F;
/*  41 */   public float zScale = 1.0F;
/*     */   protected BedrockPart parent;
/*     */   private float initRotX;
/*     */   private float initRotY;
/*     */   private float initRotZ;
/*     */   
/*     */   public BedrockPart(@Nullable String name) {
/*  48 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setPos(float x, float y, float z) {
/*  52 */     this.x = x;
/*  53 */     this.y = y;
/*  54 */     this.z = z;
/*     */   }
/*     */   
/*     */   public void render(PoseStack poseStack, ItemDisplayContext transformType, VertexConsumer consumer, int light, int overlay) {
/*  58 */     render(poseStack, transformType, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void render(PoseStack poseStack, ItemDisplayContext transformType, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
/*  62 */     int cubePackedLight = light;
/*  63 */     if (this.illuminated)
/*     */     {
/*  65 */       cubePackedLight = LightTexture.m_109885_(15, 15);
/*     */     }
/*  67 */     if (this.visible && (
/*  68 */       !this.cubes.isEmpty() || !this.children.isEmpty())) {
/*  69 */       poseStack.m_85836_();
/*  70 */       translateAndRotateAndScale(poseStack);
/*  71 */       compile(poseStack.m_85850_(), consumer, cubePackedLight, overlay, red, green, blue, alpha);
/*     */       
/*  73 */       for (ObjectListIterator<BedrockPart> objectListIterator = this.children.iterator(); objectListIterator.hasNext(); ) { BedrockPart part = objectListIterator.next();
/*  74 */         part.render(poseStack, transformType, consumer, cubePackedLight, overlay, red, green, blue, alpha); }
/*     */ 
/*     */       
/*  77 */       poseStack.m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateAndRotateAndScale(PoseStack poseStack) {
/*  83 */     poseStack.m_252880_(this.offsetX, this.offsetY, this.offsetZ);
/*  84 */     poseStack.m_252880_(this.x / 16.0F, this.y / 16.0F, this.z / 16.0F);
/*  85 */     if (this.zRot != 0.0F) {
/*  86 */       poseStack.m_252781_(Axis.f_252403_.m_252961_(this.zRot));
/*     */     }
/*  88 */     if (this.yRot != 0.0F) {
/*  89 */       poseStack.m_252781_(Axis.f_252436_.m_252961_(this.yRot));
/*     */     }
/*  91 */     if (this.xRot != 0.0F) {
/*  92 */       poseStack.m_252781_(Axis.f_252529_.m_252961_(this.xRot));
/*     */     }
/*  94 */     poseStack.m_252781_(this.additionalQuaternion);
/*  95 */     poseStack.m_85841_(this.xScale, this.yScale, this.zScale);
/*     */   }
/*     */   
/*     */   public void compile(PoseStack.Pose pose, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
/*  99 */     for (ObjectListIterator<BedrockCube> objectListIterator = this.cubes.iterator(); objectListIterator.hasNext(); ) { BedrockCube bedrockCube = objectListIterator.next();
/* 100 */       bedrockCube.compile(pose, consumer, light, overlay, red, green, blue, alpha); }
/*     */   
/*     */   }
/*     */   
/*     */   public BedrockCube getRandomCube(Random random) {
/* 105 */     return (BedrockCube)this.cubes.get(random.nextInt(this.cubes.size()));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 109 */     return this.cubes.isEmpty();
/*     */   }
/*     */   
/*     */   public void setInitRotationAngle(float x, float y, float z) {
/* 113 */     this.initRotX = x;
/* 114 */     this.initRotY = y;
/* 115 */     this.initRotZ = z;
/*     */   }
/*     */   
/*     */   public float getInitRotX() {
/* 119 */     return this.initRotX;
/*     */   }
/*     */   
/*     */   public float getInitRotY() {
/* 123 */     return this.initRotY;
/*     */   }
/*     */   
/*     */   public float getInitRotZ() {
/* 127 */     return this.initRotZ;
/*     */   }
/*     */   
/*     */   public void addChild(BedrockPart model) {
/* 131 */     this.children.add(model);
/*     */   }
/*     */   
/*     */   public BedrockPart getParent() {
/* 135 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\bedrock\BedrockPart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */