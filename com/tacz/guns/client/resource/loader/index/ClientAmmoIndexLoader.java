/*    */ package com.tacz.guns.client.resource.loader.index;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*    */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*    */ import com.tacz.guns.resource.pojo.AmmoIndexPOJO;
/*    */ import java.util.Map;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public final class ClientAmmoIndexLoader {
/* 14 */   private static final Marker MARKER = MarkerManager.getMarker("ClientGunIndexLoader");
/*    */   
/*    */   public static void loadAmmoIndex() {
/* 17 */     TimelessAPI.getAllCommonAmmoIndex().forEach(index -> {
/*    */           ResourceLocation id = (ResourceLocation)index.getKey();
/*    */           AmmoIndexPOJO pojo = ((CommonAmmoIndex)index.getValue()).getPojo();
/*    */           try {
/*    */             ClientGunPackLoader.AMMO_INDEX.put(id, ClientAmmoIndex.getInstance(pojo));
/* 22 */           } catch (IllegalArgumentException exception) {
/*    */             GunMod.LOGGER.warn(MARKER, "{} index file read fail!", id);
/*    */             exception.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\loader\index\ClientAmmoIndexLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */