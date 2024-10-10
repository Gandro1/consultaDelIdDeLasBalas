/*    */ package com.tacz.guns.client.event;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.tacz.guns.config.client.RenderConfig;
/*    */ import com.tacz.guns.config.util.HeadShotAABBConfigRead;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.LevelRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ @EventBusSubscriber({Dist.CLIENT})
/*    */ public class RenderHeadShotAABB {
/*    */   @SubscribeEvent
/*    */   public static void onRenderEntity(RenderLivingEvent.Post<?, ?> event) {
/* 22 */     boolean canRender = Minecraft.m_91087_().m_91290_().m_114377_();
/* 23 */     if (!canRender) {
/*    */       return;
/*    */     }
/* 26 */     if (!((Boolean)RenderConfig.HEAD_SHOT_DEBUG_HITBOX.get()).booleanValue()) {
/*    */       return;
/*    */     }
/* 29 */     LivingEntity entity = event.getEntity();
/* 30 */     ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_());
/* 31 */     if (entityId == null) {
/*    */       return;
/*    */     }
/* 34 */     AABB aabb = HeadShotAABBConfigRead.getAABB(entityId);
/* 35 */     if (aabb == null) {
/* 36 */       float width = entity.m_20205_();
/* 37 */       float eyeHeight = entity.m_20192_();
/*    */       
/* 39 */       aabb = (new AABB((-width / 2.0F), eyeHeight - 0.25D, (-width / 2.0F), (width / 2.0F), eyeHeight + 0.25D, (width / 2.0F))).m_82400_(0.01D);
/*    */     } 
/* 41 */     VertexConsumer buffer = event.getMultiBufferSource().m_6299_(RenderType.m_110504_());
/* 42 */     LevelRenderer.m_109646_(event.getPoseStack(), buffer, aabb, 1.0F, 1.0F, 0.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\RenderHeadShotAABB.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */