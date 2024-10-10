/*    */ package com.tacz.guns.compat.oculus.legacy.pbr;
/*    */ 
/*    */ import com.tacz.guns.client.resource.texture.FilePackTexture;
/*    */ import com.tacz.guns.client.resource.texture.ZipPackTexture;
/*    */ import net.coderbot.iris.texture.pbr.loader.PBRTextureLoaderRegistry;
/*    */ 
/*    */ public class PBRRegister {
/*    */   public static void registerPBRLoader() {
/*  9 */     PBRTextureLoaderRegistry.INSTANCE.register(FilePackTexture.class, new FilePackTexturePBRLoader());
/* 10 */     PBRTextureLoaderRegistry.INSTANCE.register(ZipPackTexture.class, new ZipPackTexturePBRLoader());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\oculus\legacy\pbr\PBRRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */