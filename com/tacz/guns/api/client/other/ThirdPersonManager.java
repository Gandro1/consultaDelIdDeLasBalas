/*    */ package com.tacz.guns.api.client.other;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ThirdPersonManager
/*    */ {
/* 14 */   private static final Map<String, IThirdPersonAnimation> CACHE = Maps.newHashMap();
/*    */   
/* 16 */   private static final IThirdPersonAnimation DEFAULT = new IThirdPersonAnimation()
/*    */     {
/*    */       public void animateGunHold(LivingEntity entity, ModelPart rightArm, ModelPart leftArm, ModelPart body, ModelPart head) {
/* 19 */         rightArm.f_104204_ = -0.3F + head.f_104204_;
/* 20 */         leftArm.f_104204_ = 0.8F + head.f_104204_;
/* 21 */         rightArm.f_104203_ = -1.4F + head.f_104203_;
/* 22 */         leftArm.f_104203_ = -1.4F + head.f_104203_;
/*    */       }
/*    */ 
/*    */       
/*    */       public void animateGunAim(LivingEntity entity, ModelPart rightArm, ModelPart leftArm, ModelPart body, ModelPart head, float aimProgress) {
/* 27 */         float lerp1 = Mth.m_14179_(aimProgress, 0.3F, 0.35F);
/* 28 */         float lerp2 = Mth.m_14179_(aimProgress, 1.4F, 1.6F);
/* 29 */         rightArm.f_104204_ = -lerp1 + head.f_104204_;
/* 30 */         leftArm.f_104204_ = 0.8F + head.f_104204_;
/* 31 */         rightArm.f_104203_ = -lerp2 + head.f_104203_;
/* 32 */         leftArm.f_104203_ = -lerp2 + head.f_104203_;
/*    */       }
/*    */     };
/*    */   private static final String RESERVED_DEFAULT_NAME = "default";
/*    */   public static void registerDefault() {
/* 37 */     CACHE.put("default", DEFAULT);
/*    */   }
/*    */   
/*    */   public static void register(String name, IThirdPersonAnimation animation) {
/* 41 */     if (name.equals("default")) {
/*    */       return;
/*    */     }
/* 44 */     CACHE.put(name, animation);
/*    */   }
/*    */   
/*    */   public static IThirdPersonAnimation getAnimation(String name) {
/* 48 */     return CACHE.getOrDefault(name, DEFAULT);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\other\ThirdPersonManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */