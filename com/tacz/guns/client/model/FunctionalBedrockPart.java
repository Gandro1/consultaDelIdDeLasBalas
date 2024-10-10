/*    */ package com.tacz.guns.client.model;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.LightTexture;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ 
/*    */ public class FunctionalBedrockPart
/*    */   extends BedrockPart
/*    */ {
/*    */   @Nullable
/*    */   public Function<BedrockPart, IFunctionalRenderer> functionalRenderer;
/*    */   
/*    */   public FunctionalBedrockPart(@Nullable Function<BedrockPart, IFunctionalRenderer> functionalRenderer, @Nonnull String name) {
/* 20 */     super(name);
/* 21 */     this.functionalRenderer = functionalRenderer;
/*    */   }
/*    */   
/*    */   public FunctionalBedrockPart(@Nullable Function<BedrockPart, IFunctionalRenderer> functionalRenderer, @Nonnull BedrockPart part) {
/* 25 */     super(part.name);
/* 26 */     this.cubes.addAll(part.cubes);
/* 27 */     this.children.addAll(part.children);
/* 28 */     this.x = part.x;
/* 29 */     this.y = part.y;
/* 30 */     this.z = part.z;
/* 31 */     this.xRot = part.xRot;
/* 32 */     this.yRot = part.yRot;
/* 33 */     this.zRot = part.zRot;
/* 34 */     this.offsetX = part.offsetX;
/* 35 */     this.offsetY = part.offsetY;
/* 36 */     this.offsetZ = part.offsetZ;
/* 37 */     this.visible = part.visible;
/* 38 */     this.mirror = part.mirror;
/* 39 */     setInitRotationAngle(part.getInitRotX(), part.getInitRotY(), part.getInitRotZ());
/* 40 */     this.xScale = part.xScale;
/* 41 */     this.yScale = part.yScale;
/* 42 */     this.zScale = part.zScale;
/* 43 */     this.functionalRenderer = functionalRenderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(PoseStack poseStack, ItemDisplayContext transformType, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
/* 48 */     int cubePackedLight = light;
/* 49 */     if (this.illuminated)
/*    */     {
/* 51 */       cubePackedLight = LightTexture.m_109885_(15, 15);
/*    */     }
/*    */     
/* 54 */     poseStack.m_85836_();
/* 55 */     translateAndRotateAndScale(poseStack);
/*    */     
/* 57 */     if (this.functionalRenderer != null) {
/* 58 */       IFunctionalRenderer renderer = this.functionalRenderer.apply(this);
/* 59 */       if (renderer != null) {
/* 60 */         renderer.render(poseStack, consumer, transformType, cubePackedLight, overlay);
/*    */       }
/* 62 */       else if (this.visible) {
/* 63 */         compile(poseStack.m_85850_(), consumer, cubePackedLight, overlay, red, green, blue, alpha);
/* 64 */         for (ObjectListIterator<BedrockPart> objectListIterator = this.children.iterator(); objectListIterator.hasNext(); ) { BedrockPart part = objectListIterator.next();
/* 65 */           part.render(poseStack, transformType, consumer, cubePackedLight, overlay, red, green, blue, alpha); }
/*    */ 
/*    */       
/*    */       }
/*    */     
/* 70 */     } else if (this.visible) {
/* 71 */       compile(poseStack.m_85850_(), consumer, cubePackedLight, overlay, red, green, blue, alpha);
/* 72 */       for (ObjectListIterator<BedrockPart> objectListIterator = this.children.iterator(); objectListIterator.hasNext(); ) { BedrockPart part = objectListIterator.next();
/* 73 */         part.render(poseStack, transformType, consumer, cubePackedLight, overlay, red, green, blue, alpha); }
/*    */     
/*    */     } 
/*    */     
/* 77 */     poseStack.m_85849_();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\FunctionalBedrockPart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */