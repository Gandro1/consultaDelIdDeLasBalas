/*    */ package com.tacz.guns.client.model;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockCubePerFace;
/*    */ import com.tacz.guns.client.model.bedrock.BedrockPart;
/*    */ import com.tacz.guns.client.resource.pojo.model.FaceUVsItem;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ 
/*    */ public class SlotModel
/*    */   extends EntityModel<Entity> {
/*    */   private final BedrockPart bone;
/*    */   
/*    */   public SlotModel(boolean illuminated) {
/* 17 */     this.bone = new BedrockPart("slot");
/* 18 */     this.bone.setPos(8.0F, 24.0F, -10.0F);
/* 19 */     this.bone.cubes.add(new BedrockCubePerFace(-16.0F, -16.0F, 9.5F, 16.0F, 16.0F, 0.0F, 0.0F, 16.0F, 16.0F, FaceUVsItem.singleSouthFace()));
/* 20 */     this.bone.illuminated = illuminated;
/*    */   }
/*    */   
/*    */   public SlotModel() {
/* 24 */     this(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */   
/*    */   public void m_7695_(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 33 */     this.bone.render(poseStack, ItemDisplayContext.GUI, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\SlotModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */