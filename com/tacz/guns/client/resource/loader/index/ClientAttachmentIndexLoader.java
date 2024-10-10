/*    */ package com.tacz.guns.client.resource.loader.index;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*    */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*    */ import com.tacz.guns.resource.pojo.AttachmentIndexPOJO;
/*    */ import java.util.Map;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public final class ClientAttachmentIndexLoader {
/* 14 */   private static final Marker MARKER = MarkerManager.getMarker("ClientAttachmentIndexLoader");
/*    */   
/*    */   public static void loadAttachmentIndex() {
/* 17 */     TimelessAPI.getAllCommonAttachmentIndex().forEach(index -> {
/*    */           ResourceLocation id = (ResourceLocation)index.getKey();
/*    */           AttachmentIndexPOJO pojo = ((CommonAttachmentIndex)index.getValue()).getPojo();
/*    */           try {
/*    */             ClientGunPackLoader.ATTACHMENT_INDEX.put(id, ClientAttachmentIndex.getInstance(id, pojo));
/* 22 */           } catch (IllegalArgumentException exception) {
/*    */             GunMod.LOGGER.warn(MARKER, "{} index file read fail!", id);
/*    */             exception.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\index\ClientAttachmentIndexLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */