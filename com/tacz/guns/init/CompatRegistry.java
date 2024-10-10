/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.client.gui.compat.ClothConfigScreen;
/*    */ import com.tacz.guns.compat.carryon.BlackList;
/*    */ import com.tacz.guns.compat.cloth.MenuIntegration;
/*    */ import com.tacz.guns.compat.oculus.OculusCompat;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*    */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class CompatRegistry {
/*    */   public static final String CLOTH_CONFIG = "cloth_config";
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEnqueue(InterModEnqueueEvent event) {
/* 23 */     event.enqueueWork(() -> checkModLoad("cloth_config", ()));
/* 24 */     event.enqueueWork(() -> {
/*    */           if (FMLEnvironment.dist == Dist.CLIENT) {
/*    */             ClothConfigScreen.registerNoClothConfigPage();
/*    */           }
/*    */         });
/* 29 */     event.enqueueWork(() -> checkModLoad("oculus", OculusCompat::initCompat));
/* 30 */     event.enqueueWork(() -> checkModLoad("carryon", BlackList::addBlackList));
/*    */   }
/*    */   public static final String OCULUS = "oculus"; public static final String CARRY_ON_ID = "carryon";
/*    */   public static void checkModLoad(String modId, Runnable runnable) {
/* 34 */     if (ModList.get().isLoaded(modId))
/* 35 */       runnable.run(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\CompatRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */