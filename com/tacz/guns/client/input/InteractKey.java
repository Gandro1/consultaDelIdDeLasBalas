/*     */ package com.tacz.guns.client.input;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.InputConstants;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.config.util.InteractKeyConfigRead;
/*     */ import com.tacz.guns.util.InputExtraCheck;
/*     */ import net.minecraft.client.KeyMapping;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.client.settings.IKeyConflictContext;
/*     */ import net.minecraftforge.client.settings.KeyConflictContext;
/*     */ import net.minecraftforge.client.settings.KeyModifier;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ @EventBusSubscriber({Dist.CLIENT})
/*     */ public class InteractKey {
/*  29 */   public static final KeyMapping INTERACT_KEY = new KeyMapping("key.tacz.interact.desc", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, 79, "key.category.tacz");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onInteractKeyPress(InputEvent.Key event) {
/*  38 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && INTERACT_KEY.m_90832_(event.getKey(), event.getScanCode())) {
/*  39 */       doInteractLogic();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onInteractMousePress(InputEvent.MouseButton.Post event) {
/*  45 */     if (InputExtraCheck.isInGame() && event.getAction() == 1 && INTERACT_KEY.m_90830_(event.getButton())) {
/*  46 */       doInteractLogic();
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean onInteractControllerPress(boolean isPress) {
/*  51 */     if (InputExtraCheck.isInGame() && isPress) {
/*  52 */       Minecraft mc = Minecraft.m_91087_();
/*  53 */       LocalPlayer player = mc.f_91074_;
/*  54 */       if (player == null || player.m_5833_()) {
/*  55 */         return false;
/*     */       }
/*  57 */       if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/*  58 */         return false;
/*     */       }
/*  60 */       HitResult hitResult = mc.f_91077_;
/*  61 */       if (hitResult == null) {
/*  62 */         return false;
/*     */       }
/*  64 */       if (hitResult instanceof BlockHitResult) { BlockHitResult blockHitResult = (BlockHitResult)hitResult;
/*  65 */         interactBlock(blockHitResult, player, mc);
/*  66 */         return true; }
/*     */       
/*  68 */       if (hitResult instanceof EntityHitResult) { EntityHitResult entityHitResult = (EntityHitResult)hitResult;
/*  69 */         interactEntity(entityHitResult, mc);
/*  70 */         return true; }
/*     */     
/*     */     } 
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   private static void doInteractLogic() {
/*  77 */     Minecraft mc = Minecraft.m_91087_();
/*  78 */     LocalPlayer player = mc.f_91074_;
/*  79 */     if (player == null || player.m_5833_()) {
/*     */       return;
/*     */     }
/*  82 */     if (!IGun.mainhandHoldGun((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*  85 */     HitResult hitResult = mc.f_91077_;
/*  86 */     if (hitResult == null) {
/*     */       return;
/*     */     }
/*  89 */     if (hitResult instanceof BlockHitResult) { BlockHitResult blockHitResult = (BlockHitResult)hitResult;
/*  90 */       interactBlock(blockHitResult, player, mc);
/*     */       return; }
/*     */     
/*  93 */     if (hitResult instanceof EntityHitResult) { EntityHitResult entityHitResult = (EntityHitResult)hitResult;
/*  94 */       interactEntity(entityHitResult, mc); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void interactBlock(BlockHitResult blockHitResult, LocalPlayer player, Minecraft mc) {
/*  99 */     BlockPos blockPos = blockHitResult.m_82425_();
/* 100 */     BlockState block = player.m_9236_().m_8055_(blockPos);
/* 101 */     if (InteractKeyConfigRead.canInteractBlock(block)) {
/* 102 */       mc.m_91277_();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void interactEntity(EntityHitResult entityHitResult, Minecraft mc) {
/* 107 */     Entity entity = entityHitResult.m_82443_();
/* 108 */     if (InteractKeyConfigRead.canInteractEntity(entity))
/* 109 */       mc.m_91277_(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\input\InteractKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */