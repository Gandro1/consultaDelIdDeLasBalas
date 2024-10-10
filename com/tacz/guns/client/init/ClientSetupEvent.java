/*    */ package com.tacz.guns.client.init;
/*    */ import com.tacz.guns.api.client.other.ThirdPersonManager;
/*    */ import com.tacz.guns.client.gui.overlay.GunHudOverlay;
/*    */ import com.tacz.guns.client.gui.overlay.InteractKeyTextOverlay;
/*    */ import com.tacz.guns.client.gui.overlay.KillAmountOverlay;
/*    */ import com.tacz.guns.client.input.AimKey;
/*    */ import com.tacz.guns.client.input.ConfigKey;
/*    */ import com.tacz.guns.client.input.CrawlKey;
/*    */ import com.tacz.guns.client.input.FireSelectKey;
/*    */ import com.tacz.guns.client.input.RefitKey;
/*    */ import com.tacz.guns.client.input.ShootKey;
/*    */ import com.tacz.guns.client.input.ZoomKey;
/*    */ import com.tacz.guns.compat.controllable.ControllableCompat;
/*    */ import com.tacz.guns.compat.playeranimator.PlayerAnimatorCompat;
/*    */ import com.tacz.guns.compat.shouldersurfing.ShoulderSurfingCompat;
/*    */ import com.tacz.guns.init.ModItems;
/*    */ import com.tacz.guns.inventory.tooltip.AmmoBoxTooltip;
/*    */ import com.tacz.guns.inventory.tooltip.AttachmentItemTooltip;
/*    */ import com.tacz.guns.inventory.tooltip.GunTooltip;
/*    */ import com.tacz.guns.item.AmmoBoxItem;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.item.ItemProperties;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
/*    */ import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
/*    */ import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
/*    */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*    */ import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT}, modid = "tacz")
/*    */ public class ClientSetupEvent {
/*    */   @SubscribeEvent
/*    */   public static void onClientSetup(RegisterKeyMappingsEvent event) {
/* 38 */     event.register(InspectKey.INSPECT_KEY);
/* 39 */     event.register(ReloadKey.RELOAD_KEY);
/* 40 */     event.register(ShootKey.SHOOT_KEY);
/* 41 */     event.register(InteractKey.INTERACT_KEY);
/* 42 */     event.register(FireSelectKey.FIRE_SELECT_KEY);
/* 43 */     event.register(AimKey.AIM_KEY);
/* 44 */     event.register(CrawlKey.CRAWL_KEY);
/* 45 */     event.register(RefitKey.REFIT_KEY);
/* 46 */     event.register(ZoomKey.ZOOM_KEY);
/* 47 */     event.register(MeleeKey.MELEE_KEY);
/* 48 */     event.register(ConfigKey.OPEN_CONFIG_KEY);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onClientSetup(RegisterClientTooltipComponentFactoriesEvent event) {
/* 54 */     event.register(GunTooltip.class, com.tacz.guns.client.tooltip.ClientGunTooltip::new);
/* 55 */     event.register(AmmoBoxTooltip.class, com.tacz.guns.client.tooltip.ClientAmmoBoxTooltip::new);
/* 56 */     event.register(AttachmentItemTooltip.class, com.tacz.guns.client.tooltip.ClientAttachmentItemTooltip::new);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
/* 62 */     event.registerAboveAll("tac_gun_hud_overlay", (IGuiOverlay)new GunHudOverlay());
/* 63 */     event.registerAboveAll("tac_kill_amount_overlay", (IGuiOverlay)new KillAmountOverlay());
/* 64 */     event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "tac_interact_key_overlay", (IGuiOverlay)new InteractKeyTextOverlay());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onClientSetup(FMLClientSetupEvent event) {
/* 71 */     event.enqueueWork(ThirdPersonManager::registerDefault);
/*    */ 
/*    */     
/* 74 */     event.enqueueWork(() -> Minecraft.m_91087_().getItemColors().m_92689_(AmmoBoxItem::getColor, new ItemLike[] { (ItemLike)ModItems.AMMO_BOX.get() }));
/*    */ 
/*    */ 
/*    */     
/* 78 */     event.enqueueWork(() -> ItemProperties.register((Item)ModItems.AMMO_BOX.get(), AmmoBoxItem.PROPERTY_NAME, AmmoBoxItem::getStatue));
/*    */ 
/*    */     
/* 81 */     event.enqueueWork(ClientGunPackDownloadManager::init);
/*    */ 
/*    */     
/* 84 */     event.enqueueWork(PlayerAnimatorCompat::init);
/*    */ 
/*    */     
/* 87 */     event.enqueueWork(ShoulderSurfingCompat::init);
/*    */ 
/*    */     
/* 90 */     event.enqueueWork(ControllableCompat::init);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\init\ClientSetupEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */