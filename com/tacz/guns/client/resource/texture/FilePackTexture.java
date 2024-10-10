/*    */ package com.tacz.guns.client.resource.texture;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.NativeImage;
/*    */ import com.mojang.blaze3d.platform.TextureUtil;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import net.minecraft.client.renderer.texture.AbstractTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ public class FilePackTexture
/*    */   extends AbstractTexture
/*    */ {
/*    */   private final ResourceLocation registerId;
/*    */   private final Path filePath;
/*    */   
/*    */   public FilePackTexture(ResourceLocation registerId, Path filePath) {
/* 22 */     this.registerId = registerId;
/* 23 */     this.filePath = filePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6704_(ResourceManager manager) {
/* 28 */     if (!RenderSystem.isOnRenderThreadOrInit()) {
/* 29 */       RenderSystem.recordRenderCall(this::doLoad);
/*    */     } else {
/* 31 */       doLoad();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void doLoad() {
/* 36 */     File textureFile = this.filePath.toFile();
/* 37 */     if (textureFile.isFile()) {
/* 38 */       try { InputStream stream = Files.newInputStream(textureFile.toPath(), new java.nio.file.OpenOption[0]); 
/* 39 */         try { NativeImage imageIn = NativeImage.m_85058_(stream);
/* 40 */           int width = imageIn.m_84982_();
/* 41 */           int height = imageIn.m_85084_();
/* 42 */           TextureUtil.prepareImage(m_117963_(), 0, width, height);
/* 43 */           imageIn.m_85013_(0, 0, 0, 0, 0, width, height, false, false, false, true);
/* 44 */           if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 45 */       { e.printStackTrace(); }
/*    */     
/*    */     }
/*    */   }
/*    */   
/*    */   public ResourceLocation getRegisterId() {
/* 51 */     return this.registerId;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\texture\FilePackTexture.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */