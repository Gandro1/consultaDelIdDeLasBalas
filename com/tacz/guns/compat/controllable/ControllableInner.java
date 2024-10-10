/*     */ package com.tacz.guns.compat.controllable;
/*     */ import com.mrcrayfish.controllable.client.binding.BindingRegistry;
/*     */ import com.mrcrayfish.controllable.client.binding.ButtonBinding;
/*     */ import com.mrcrayfish.controllable.client.binding.IBindingContext;
/*     */ import com.mrcrayfish.controllable.client.input.Controller;
/*     */ import com.mrcrayfish.controllable.event.ControllerEvents;
/*     */ import com.mrcrayfish.controllable.event.Value;
/*     */ import com.mrcrayfish.framework.api.event.IFrameworkEvent;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.gun.FireMode;
/*     */ import com.tacz.guns.client.input.AimKey;
/*     */ import com.tacz.guns.client.input.CrawlKey;
/*     */ import com.tacz.guns.client.input.FireSelectKey;
/*     */ import com.tacz.guns.client.input.ShootKey;
/*     */ import com.tacz.guns.client.input.ZoomKey;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.client.resource.pojo.display.gun.ControllableData;
/*     */ import java.util.EnumMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class ControllableInner {
/*  26 */   public static final IBindingContext GUN_KEY_CONFLICT = new GunKeyConflict();
/*  27 */   public static final ButtonBinding AIM = new ButtonBinding(11, "key.tacz.aim.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  28 */   public static final ButtonBinding SHOOT = new ButtonBinding(12, "key.tacz.shoot.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  29 */   public static final ButtonBinding RELOAD = new ButtonBinding(1, "key.tacz.reload.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  30 */   public static final ButtonBinding MELEE = new ButtonBinding(2, "key.tacz.melee.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  31 */   public static final ButtonBinding ZOOM = new ButtonBinding(2, "key.tacz.zoom.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  32 */   public static final ButtonBinding CRAWL = new ButtonBinding(7, "key.tacz.crawl.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  33 */   public static final ButtonBinding FIRE_SELECT = new ButtonBinding(15, "key.tacz.fire_select.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  34 */   public static final ButtonBinding INTERACT = new ButtonBinding(-1, "key.tacz.interact.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*  35 */   public static final ButtonBinding INSPECT = new ButtonBinding(-1, "key.tacz.inspect.desc", "key.category.tacz", GUN_KEY_CONFLICT);
/*     */   
/*     */   public static void init() {
/*  38 */     BindingRegistry.getInstance().register(AIM);
/*  39 */     BindingRegistry.getInstance().register(SHOOT);
/*  40 */     BindingRegistry.getInstance().register(RELOAD);
/*  41 */     BindingRegistry.getInstance().register(MELEE);
/*  42 */     BindingRegistry.getInstance().register(CRAWL);
/*  43 */     BindingRegistry.getInstance().register(ZOOM);
/*  44 */     BindingRegistry.getInstance().register(FIRE_SELECT);
/*  45 */     BindingRegistry.getInstance().register(INTERACT);
/*  46 */     BindingRegistry.getInstance().register(INSPECT);
/*     */     
/*  48 */     ControllerEvents.INPUT.register((IFrameworkEvent)ControllableInner::onButtonInput);
/*  49 */     TickEvents.END_CLIENT.register((IFrameworkEvent)ControllableInner::onClientTickEnd);
/*     */   }
/*     */   
/*     */   public static boolean onButtonInput(Controller controller, Value<Integer> newButton, int originalButton, boolean isPress) {
/*  53 */     if (!GUN_KEY_CONFLICT.isActive()) {
/*  54 */       return false;
/*     */     }
/*  56 */     if (AIM.getButton() == ((Integer)newButton.get()).intValue() && AimKey.onAimControllerPress(isPress)) {
/*  57 */       return true;
/*     */     }
/*  59 */     if (SHOOT.getButton() == ((Integer)newButton.get()).intValue() && ShootKey.semiShootController(isPress)) {
/*  60 */       doRumble(controller);
/*  61 */       return true;
/*     */     } 
/*  63 */     if (RELOAD.getButton() == ((Integer)newButton.get()).intValue() && ReloadKey.onReloadControllerPress(isPress)) {
/*  64 */       return true;
/*     */     }
/*  66 */     if (MELEE.getButton() == ((Integer)newButton.get()).intValue() && MeleeKey.onMeleeControllerPress(isPress)) {
/*  67 */       return true;
/*     */     }
/*  69 */     if (CRAWL.getButton() == ((Integer)newButton.get()).intValue() && CrawlKey.onCrawlControllerPress(isPress)) {
/*  70 */       return true;
/*     */     }
/*  72 */     if (ZOOM.getButton() == ((Integer)newButton.get()).intValue() && ZoomKey.onZoomControllerPress(isPress)) {
/*  73 */       return true;
/*     */     }
/*  75 */     if (FIRE_SELECT.getButton() == ((Integer)newButton.get()).intValue() && FireSelectKey.onFireSelectControllerPress(isPress)) {
/*  76 */       return true;
/*     */     }
/*  78 */     if (INTERACT.getButton() == ((Integer)newButton.get()).intValue() && InteractKey.onInteractControllerPress(isPress)) {
/*  79 */       return true;
/*     */     }
/*  81 */     return (INSPECT.getButton() == ((Integer)newButton.get()).intValue() && InspectKey.onInspectControllerPress(isPress));
/*     */   }
/*     */   
/*     */   public static void onClientTickEnd() {
/*  85 */     if (!GUN_KEY_CONFLICT.isActive()) {
/*     */       return;
/*     */     }
/*  88 */     Controller controller = Controllable.getController();
/*  89 */     if (controller == null) {
/*     */       return;
/*     */     }
/*  92 */     if (controller.isButtonPressed(SHOOT.getButton()) && ShootKey.autoShootController()) {
/*  93 */       doRumble(controller);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void doRumble(Controller controller) {
/*  98 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*  99 */     if (player == null) {
/*     */       return;
/*     */     }
/* 102 */     ItemStack mainHandItem = player.m_21205_();
/* 103 */     IGun iGun = IGun.getIGunOrNull(mainHandItem);
/* 104 */     if (iGun == null) {
/*     */       return;
/*     */     }
/* 107 */     ResourceLocation gunId = iGun.getGunId(mainHandItem);
/* 108 */     FireMode fireMode = iGun.getFireMode(mainHandItem);
/* 109 */     TimelessAPI.getClientGunIndex(gunId).ifPresent(index -> {
/*     */           EnumMap<FireMode, ControllableData> data = index.getControllableData();
/*     */           if (data.containsKey(fireMode)) {
/*     */             ControllableData controllableData = data.get(fireMode);
/*     */             controller.rumble(controllableData.getLowFrequency(), controllableData.getHighFrequency(), controllableData.getTimeInMs());
/*     */           } else if (fireMode == FireMode.AUTO) {
/*     */             controller.rumble(0.15F, 0.25F, 80);
/*     */           } else {
/*     */             controller.rumble(0.25F, 0.5F, 100);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static class GunKeyConflict
/*     */     implements IBindingContext
/*     */   {
/*     */     public boolean isActive() {
/* 127 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 128 */       return (!KeyConflictContext.GUI.isActive() && player != null && IGun.mainhandHoldGun((LivingEntity)player));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean conflicts(IBindingContext other) {
/* 133 */       return (this == other);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\controllable\ControllableInner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */