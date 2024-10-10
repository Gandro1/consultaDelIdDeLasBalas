/*     */ package com.tacz.guns.client.model.functional;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.model.BedrockAmmoModel;
/*     */ import com.tacz.guns.client.model.BedrockGunModel;
/*     */ import com.tacz.guns.client.model.IFunctionalRenderer;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.ShellEjection;
/*     */ import com.tacz.guns.compat.oculus.OculusCompat;
/*     */ import java.util.concurrent.ConcurrentLinkedDeque;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemDisplayContext;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix3fc;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class ShellRender implements IFunctionalRenderer {
/*  25 */   private static final ConcurrentLinkedDeque<Data> SHELL_QUEUE = new ConcurrentLinkedDeque<>();
/*     */   
/*     */   public static boolean isSelf = false;
/*     */   private final BedrockGunModel bedrockGunModel;
/*     */   
/*     */   public ShellRender(BedrockGunModel bedrockGunModel) {
/*  31 */     this.bedrockGunModel = bedrockGunModel;
/*     */   }
/*     */   
/*     */   public static void addShell(Vector3f randomVelocity) {
/*  35 */     double xRandom = Math.random() * randomVelocity.x();
/*  36 */     double yRandom = Math.random() * randomVelocity.y();
/*  37 */     double zRandom = Math.random() * randomVelocity.z();
/*  38 */     Vector3f vector3f = new Vector3f((float)xRandom, (float)yRandom, (float)zRandom);
/*  39 */     SHELL_QUEUE.offerLast(new Data(System.currentTimeMillis(), vector3f));
/*     */   }
/*     */   
/*     */   public static void renderShell(ResourceLocation gunId, PoseStack poseStack, BedrockGunModel gunModel) {
/*  43 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*     */           ShellEjection shellEjection = index.getShellEjection();
/*     */           if (shellEjection == null) {
/*     */             SHELL_QUEUE.clear();
/*     */             return;
/*     */           } 
/*     */           TimelessAPI.getClientAmmoIndex(index.getGunData().getAmmoId()).ifPresent(());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderSingleShell(ItemDisplayContext transformType1, int light, int overlay, Data data, Vector3f initialVelocity, Vector3f acceleration, Vector3f angularVelocity, BedrockAmmoModel model, ResourceLocation location) {
/*  85 */     if (data.normal == null && data.pose == null) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     PoseStack poseStack2 = new PoseStack();
/*  90 */     poseStack2.m_85850_().m_252943_().mul((Matrix3fc)data.normal);
/*  91 */     poseStack2.m_85850_().m_252922_().mul((Matrix4fc)data.pose);
/*     */ 
/*     */     
/*  94 */     long remindTime = System.currentTimeMillis() - data.timeStamp;
/*  95 */     double time = remindTime / 1000.0D;
/*  96 */     Vector3f randomOffset = data.randomOffset;
/*     */ 
/*     */     
/*  99 */     double x = (initialVelocity.x() + randomOffset.x()) * time + 0.5D * acceleration.x() * time * time;
/* 100 */     double y = (initialVelocity.y() + randomOffset.y()) * time + 0.5D * acceleration.y() * time * time;
/* 101 */     double z = (initialVelocity.z() + randomOffset.z()) * time + 0.5D * acceleration.z() * time * time;
/* 102 */     poseStack2.m_85837_(-x, -y, z);
/*     */ 
/*     */     
/* 105 */     double xw = time * angularVelocity.x();
/* 106 */     double yw = time * angularVelocity.y();
/* 107 */     double zw = time * angularVelocity.z();
/* 108 */     poseStack2.m_252781_(Axis.f_252495_.m_252977_((float)xw));
/* 109 */     poseStack2.m_252781_(Axis.f_252392_.m_252977_((float)yw));
/* 110 */     poseStack2.m_252781_(Axis.f_252403_.m_252977_((float)zw));
/* 111 */     poseStack2.m_85837_(0.0D, -1.5D, 0.0D);
/*     */     
/* 113 */     model.render(poseStack2, transformType1, RenderType.m_110452_(location), light, overlay);
/*     */   }
/*     */   
/*     */   private static void checkShellQueue(long lifeTime) {
/* 117 */     if (!SHELL_QUEUE.isEmpty()) {
/* 118 */       Data data = SHELL_QUEUE.peekFirst();
/* 119 */       if (System.currentTimeMillis() - data.timeStamp > lifeTime) {
/* 120 */         SHELL_QUEUE.pollFirst();
/* 121 */         checkShellQueue(lifeTime);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(PoseStack poseStack, VertexConsumer vertexBuffer, ItemDisplayContext transformType, int light, int overlay) {
/* 128 */     if (OculusCompat.isRenderShadow()) {
/*     */       return;
/*     */     }
/* 131 */     if (!isSelf) {
/*     */       return;
/*     */     }
/* 134 */     ItemStack currentGunItem = this.bedrockGunModel.getCurrentGunItem();
/* 135 */     IGun iGun = IGun.getIGunOrNull(currentGunItem);
/* 136 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 139 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 140 */     renderShell(gunId, poseStack, this.bedrockGunModel);
/*     */   }
/*     */   
/*     */   public static class Data
/*     */   {
/*     */     public final long timeStamp;
/*     */     public final Vector3f randomOffset;
/* 147 */     public Matrix3f normal = null;
/* 148 */     public Matrix4f pose = null;
/*     */     
/*     */     public Data(long timeStamp, Vector3f randomOffset) {
/* 151 */       this.timeStamp = timeStamp;
/* 152 */       this.randomOffset = randomOffset;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\functional\ShellRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */