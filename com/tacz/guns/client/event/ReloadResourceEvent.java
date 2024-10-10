/*    */ package com.tacz.guns.client.event;
/*    */ import com.tacz.guns.client.resource.ClientReloadManager;
/*    */ import com.tacz.guns.client.resource.InternalAssetLoader;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.TextureStitchEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ReloadResourceEvent {
/* 13 */   public static final ResourceLocation BLOCK_ATLAS_TEXTURE = new ResourceLocation("textures/atlas/blocks.png");
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onTextureStitchEventPost(TextureStitchEvent.Post event) {
/* 17 */     if (BLOCK_ATLAS_TEXTURE.equals(event.getAtlas().m_118330_())) {
/*    */       
/* 19 */       InternalAssetLoader.onResourceReload();
/* 20 */       ClientReloadManager.reloadAllPack();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\event\ReloadResourceEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */