/*    */ package com.tacz.guns.client.resource.texture;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.NativeImage;
/*    */ import com.mojang.blaze3d.platform.TextureUtil;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.client.renderer.texture.AbstractTexture;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ public class ZipPackTexture
/*    */   extends AbstractTexture {
/*    */   private final ResourceLocation registerId;
/*    */   private final Path zipFilePath;
/*    */   
/*    */   public ZipPackTexture(ResourceLocation registerId, String zipFilePath) {
/* 23 */     this.registerId = registerId;
/* 24 */     this.zipFilePath = Paths.get(zipFilePath, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6704_(@Nonnull ResourceManager manager) {
/* 29 */     if (!RenderSystem.isOnRenderThreadOrInit()) {
/* 30 */       RenderSystem.recordRenderCall(this::doLoad);
/*    */     } else {
/* 32 */       doLoad();
/*    */     } 
/*    */   }
/*    */   private void doLoad() {
/*    */     
/* 37 */     try { ZipFile zipFile = new ZipFile(this.zipFilePath.toFile()); 
/* 38 */       try { ZipEntry entry = zipFile.getEntry(String.format("%s/textures/%s.png", new Object[] { this.registerId.m_135827_(), this.registerId.m_135815_() }));
/* 39 */         if (entry == null)
/*    */         
/*    */         { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 51 */           zipFile.close(); return; }  try { InputStream stream = zipFile.getInputStream(entry); try { NativeImage imageIn = NativeImage.m_85058_(stream); int width = imageIn.m_84982_(); int height = imageIn.m_85084_(); TextureUtil.prepareImage(m_117963_(), 0, width, height); imageIn.m_85013_(0, 0, 0, 0, 0, width, height, false, false, false, true); if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e) { e.printStackTrace(); }  zipFile.close(); } catch (Throwable throwable) { try { zipFile.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 52 */     { e.printStackTrace(); }
/*    */   
/*    */   }
/*    */   
/*    */   public ResourceLocation getRegisterId() {
/* 57 */     return this.registerId;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\texture\ZipPackTexture.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */