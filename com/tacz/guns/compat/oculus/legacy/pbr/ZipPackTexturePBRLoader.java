/*    */ package com.tacz.guns.compat.oculus.legacy.pbr;
/*    */ 
/*    */ import com.tacz.guns.client.resource.texture.ZipPackTexture;
/*    */ import net.coderbot.iris.texture.pbr.loader.PBRTextureLoader;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.AbstractTexture;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ public class ZipPackTexturePBRLoader
/*    */   implements PBRTextureLoader<ZipPackTexture> {
/*    */   public void load(ZipPackTexture zipPackTexture, ResourceManager resourceManager, PBRTextureLoader.PBRTextureConsumer pbrTextureConsumer) {
/* 14 */     ResourceLocation id = zipPackTexture.getRegisterId();
/* 15 */     ResourceLocation pbrNormalId = new ResourceLocation(id.m_135827_(), id.m_135815_() + id.m_135815_());
/* 16 */     ResourceLocation pbrSpecularId = new ResourceLocation(id.m_135827_(), id.m_135815_() + id.m_135815_());
/* 17 */     TextureManager textureManager = Minecraft.m_91087_().m_91097_();
/* 18 */     if (textureManager.f_118468_.containsKey(pbrNormalId)) {
/* 19 */       pbrTextureConsumer.acceptNormalTexture(textureManager.m_118506_(pbrNormalId));
/*    */     }
/* 21 */     if (textureManager.f_118468_.containsKey(pbrSpecularId))
/* 22 */       pbrTextureConsumer.acceptSpecularTexture(textureManager.m_118506_(pbrSpecularId)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\oculus\legacy\pbr\ZipPackTexturePBRLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */