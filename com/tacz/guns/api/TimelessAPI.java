/*    */ package com.tacz.guns.api;
/*    */ 
/*    */ import com.tacz.guns.api.client.other.IThirdPersonAnimation;
/*    */ import com.tacz.guns.api.client.other.ThirdPersonManager;
/*    */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*    */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*    */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*    */ import com.tacz.guns.resource.CommonAssetManager;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.index.CommonAmmoIndex;
/*    */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public final class TimelessAPI
/*    */ {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Optional<ClientGunIndex> getClientGunIndex(ResourceLocation gunId) {
/* 26 */     return ClientGunPackLoader.getGunIndex(gunId);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Optional<ClientAttachmentIndex> getClientAttachmentIndex(ResourceLocation attachmentId) {
/* 31 */     return ClientGunPackLoader.getAttachmentIndex(attachmentId);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Optional<ClientAmmoIndex> getClientAmmoIndex(ResourceLocation ammoId) {
/* 36 */     return ClientGunPackLoader.getAmmoIndex(ammoId);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Set<Map.Entry<ResourceLocation, ClientGunIndex>> getAllClientGunIndex() {
/* 41 */     return ClientGunPackLoader.getAllGuns();
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Set<Map.Entry<ResourceLocation, ClientAmmoIndex>> getAllClientAmmoIndex() {
/* 46 */     return ClientGunPackLoader.getAllAmmo();
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static Set<Map.Entry<ResourceLocation, ClientAttachmentIndex>> getAllClientAttachmentIndex() {
/* 51 */     return ClientGunPackLoader.getAllAttachments();
/*    */   }
/*    */   
/*    */   public static Optional<CommonGunIndex> getCommonGunIndex(ResourceLocation gunId) {
/* 55 */     return CommonGunPackLoader.getGunIndex(gunId);
/*    */   }
/*    */   
/*    */   public static Optional<CommonAttachmentIndex> getCommonAttachmentIndex(ResourceLocation attachmentId) {
/* 59 */     return CommonGunPackLoader.getAttachmentIndex(attachmentId);
/*    */   }
/*    */   
/*    */   public static Optional<CommonAmmoIndex> getCommonAmmoIndex(ResourceLocation ammoId) {
/* 63 */     return CommonGunPackLoader.getAmmoIndex(ammoId);
/*    */   }
/*    */   
/*    */   public static Optional<GunSmithTableRecipe> getRecipe(ResourceLocation recipeId) {
/* 67 */     return CommonAssetManager.INSTANCE.getRecipe(recipeId);
/*    */   }
/*    */   
/*    */   public static Set<Map.Entry<ResourceLocation, CommonGunIndex>> getAllCommonGunIndex() {
/* 71 */     return CommonGunPackLoader.getAllGuns();
/*    */   }
/*    */   
/*    */   public static Set<Map.Entry<ResourceLocation, CommonAmmoIndex>> getAllCommonAmmoIndex() {
/* 75 */     return CommonGunPackLoader.getAllAmmo();
/*    */   }
/*    */   
/*    */   public static Set<Map.Entry<ResourceLocation, CommonAttachmentIndex>> getAllCommonAttachmentIndex() {
/* 79 */     return CommonGunPackLoader.getAllAttachments();
/*    */   }
/*    */   
/*    */   public static Map<ResourceLocation, GunSmithTableRecipe> getAllRecipes() {
/* 83 */     return CommonAssetManager.INSTANCE.getAllRecipes();
/*    */   }
/*    */   
/*    */   public static void registerThirdPersonAnimation(String name, IThirdPersonAnimation animation) {
/* 87 */     ThirdPersonManager.register(name, animation);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\TimelessAPI.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */