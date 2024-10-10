/*    */ package com.tacz.guns.client.renderer.other;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*    */ import com.tacz.guns.client.resource.pojo.display.gun.LayerGunShow;
/*    */ import com.tacz.guns.util.math.MathUtil;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.joml.Quaternionf;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class HumanoidOffhandRender {
/*    */   public static void renderGun(LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
/* 23 */     renderOffhandGun(entity, matrixStack, buffer, packedLight);
/* 24 */     renderHotbarGun(entity, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   private static void renderOffhandGun(LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
/* 28 */     ItemStack itemStack = entity.m_21206_();
/* 29 */     if (itemStack.m_41619_()) {
/*    */       return;
/*    */     }
/* 32 */     IGun iGun = IGun.getIGunOrNull(itemStack);
/* 33 */     if (iGun == null) {
/*    */       return;
/*    */     }
/* 36 */     ResourceLocation gunId = iGun.getGunId(itemStack);
/* 37 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*    */           LayerGunShow offhandShow = index.getOffhandShow();
/*    */           renderGunItem(entity, matrixStack, buffer, packedLight, itemStack, offhandShow);
/*    */         });
/*    */   }
/*    */   private static void renderHotbarGun(LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
/*    */     Player player;
/* 44 */     if (entity instanceof Player) { player = (Player)entity; }
/*    */     else
/*    */     { return; }
/* 47 */      Inventory inventory = player.m_150109_();
/* 48 */     for (int i = 0; i < 9; i++) {
/* 49 */       if (i != inventory.f_35977_) {
/*    */ 
/*    */         
/* 52 */         ItemStack stack = inventory.m_8020_(i);
/* 53 */         renderHotbarGun(entity, matrixStack, buffer, packedLight, stack, i);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   private static void renderHotbarGun(LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int packedLight, ItemStack itemStack, int inventoryIndex) {
/* 58 */     if (itemStack.m_41619_()) {
/*    */       return;
/*    */     }
/* 61 */     IGun iGun = IGun.getIGunOrNull(itemStack);
/* 62 */     if (iGun == null) {
/*    */       return;
/*    */     }
/* 65 */     ResourceLocation gunId = iGun.getGunId(itemStack);
/* 66 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*    */           Int2ObjectArrayMap<LayerGunShow> hotbarShow = index.getHotbarShow();
/*    */           if (hotbarShow == null || hotbarShow.isEmpty()) {
/*    */             return;
/*    */           }
/*    */           if (!hotbarShow.containsKey(inventoryIndex)) {
/*    */             return;
/*    */           }
/*    */           LayerGunShow gunShow = (LayerGunShow)hotbarShow.get(inventoryIndex);
/*    */           renderGunItem(entity, matrixStack, buffer, packedLight, itemStack, gunShow);
/*    */         });
/*    */   }
/*    */   
/*    */   private static void renderGunItem(LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int packedLight, ItemStack itemStack, LayerGunShow offhandShow) {
/* 80 */     ItemRenderer renderer = Minecraft.m_91087_().m_91291_();
/* 81 */     Vector3f pos = offhandShow.getPos();
/* 82 */     Vector3f rotate = offhandShow.getRotate();
/* 83 */     Vector3f scale = offhandShow.getScale();
/* 84 */     matrixStack.m_85836_();
/* 85 */     matrixStack.m_85837_((-pos.x() / 16.0F), 1.5D - (pos.y() / 16.0F), (pos.z() / 16.0F));
/* 86 */     matrixStack.m_85841_(-scale.x(), -scale.y(), scale.z());
/* 87 */     Quaternionf rotation = new Quaternionf();
/* 88 */     MathUtil.toQuaternion((float)Math.toRadians(rotate.x), (float)Math.toRadians(rotate.y), (float)Math.toRadians(rotate.z), rotation);
/* 89 */     matrixStack.m_252781_(rotation);
/* 90 */     renderer.m_269128_(itemStack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.f_118083_, matrixStack, buffer, entity.m_9236_(), entity.m_19879_());
/* 91 */     matrixStack.m_85849_();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\renderer\other\HumanoidOffhandRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */