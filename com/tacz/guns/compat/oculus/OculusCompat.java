/*    */ package com.tacz.guns.compat.oculus;
/*    */ 
/*    */ import com.tacz.guns.compat.oculus.legacy.OculusCompatLegacy;
/*    */ import com.tacz.guns.compat.oculus.newly.OculusCompatNewly;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.irisshaders.iris.api.v0.IrisApi;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraftforge.fml.ModContainer;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
/*    */ 
/*    */ public final class OculusCompat
/*    */ {
/* 15 */   private static final DefaultArtifactVersion VERSION = new DefaultArtifactVersion("1.7.0");
/*    */   private static Function<MultiBufferSource.BufferSource, Boolean> END_BATCH_FUNCTION;
/*    */   private static Supplier<Boolean> IS_RENDER_SHADOW_SUPPER;
/*    */   
/*    */   public static void initCompat() {
/* 20 */     ModList.get().getModContainerById("oculus").ifPresent(mod -> {
/*    */           if (mod.getModInfo().getVersion().compareTo(VERSION) >= 0) {
/*    */             END_BATCH_FUNCTION = OculusCompatNewly::endBatch;
/*    */             IS_RENDER_SHADOW_SUPPER = OculusCompatNewly::isRenderShadow;
/*    */             OculusCompatNewly.registerPBRLoader();
/*    */           } else {
/*    */             END_BATCH_FUNCTION = OculusCompatLegacy::endBatch;
/*    */             IS_RENDER_SHADOW_SUPPER = OculusCompatLegacy::isRenderShadow;
/*    */             OculusCompatLegacy.registerPBRLoader();
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   public static boolean isRenderShadow() {
/* 34 */     if (ModList.get().isLoaded("oculus")) {
/* 35 */       return ((Boolean)IS_RENDER_SHADOW_SUPPER.get()).booleanValue();
/*    */     }
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean isUsingRenderPack() {
/* 41 */     if (ModList.get().isLoaded("oculus")) {
/* 42 */       return IrisApi.getInstance().isShaderPackInUse();
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean endBatch(MultiBufferSource.BufferSource bufferSource) {
/* 48 */     if (ModList.get().isLoaded("oculus")) {
/* 49 */       return ((Boolean)END_BATCH_FUNCTION.apply(bufferSource)).booleanValue();
/*    */     }
/* 51 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\oculus\OculusCompat.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */