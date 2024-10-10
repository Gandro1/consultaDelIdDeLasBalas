/*    */ package com.tacz.guns.client.init;
/*    */ 
/*    */ import com.tacz.guns.block.entity.GunSmithTableBlockEntity;
/*    */ import com.tacz.guns.block.entity.StatueBlockEntity;
/*    */ import com.tacz.guns.block.entity.TargetBlockEntity;
/*    */ import com.tacz.guns.entity.EntityKineticBullet;
/*    */ import com.tacz.guns.entity.TargetMinecart;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderers;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.EntityRenderersEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModEntitiesRender
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers evt) {
/* 24 */     EntityRenderers.m_174036_(EntityKineticBullet.TYPE, com.tacz.guns.client.renderer.entity.EntityBulletRenderer::new);
/* 25 */     EntityRenderers.m_174036_(TargetMinecart.TYPE, com.tacz.guns.client.renderer.entity.TargetMinecartRenderer::new);
/* 26 */     BlockEntityRenderers.m_173590_(GunSmithTableBlockEntity.TYPE, com.tacz.guns.client.renderer.block.GunSmithTableRenderer::new);
/* 27 */     BlockEntityRenderers.m_173590_(TargetBlockEntity.TYPE, com.tacz.guns.client.renderer.block.TargetRenderer::new);
/* 28 */     BlockEntityRenderers.m_173590_(StatueBlockEntity.TYPE, com.tacz.guns.client.renderer.block.StatueRenderer::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\init\ModEntitiesRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */