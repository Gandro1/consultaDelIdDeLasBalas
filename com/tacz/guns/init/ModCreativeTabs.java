/*     */ package com.tacz.guns.init;
/*     */ import com.tacz.guns.api.DefaultAssets;
/*     */ import com.tacz.guns.api.item.GunTabType;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.builder.AmmoItemBuilder;
/*     */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*     */ import com.tacz.guns.api.item.builder.GunItemBuilder;
/*     */ import com.tacz.guns.api.item.gun.AbstractGunItem;
/*     */ import com.tacz.guns.item.AmmoBoxItem;
/*     */ import com.tacz.guns.item.AmmoItem;
/*     */ import com.tacz.guns.item.AttachmentItem;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.CreativeModeTab;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.RegistryObject;
/*     */ 
/*     */ public class ModCreativeTabs {
/*  23 */   public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.f_279569_, "tacz");
/*     */   
/*  25 */   public static RegistryObject<CreativeModeTab> OTHER_TAB = TABS.register("other", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("itemGroup.tab.tacz.other")).m_257737_(()).m_257501_(()).m_257652_());
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
/*  36 */   public static RegistryObject<CreativeModeTab> AMMO_TAB = TABS.register("ammo", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("itemGroup.tab.tacz.ammo")).withTabsBefore(new ResourceLocation[] { OTHER_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static RegistryObject<CreativeModeTab> ATTACHMENT_SCOPE_TAB = TABS.register("scope", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.scope.name")).withTabsBefore(new ResourceLocation[] { AMMO_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static RegistryObject<CreativeModeTab> ATTACHMENT_MUZZLE_TAB = TABS.register("muzzle", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.muzzle.name")).withTabsBefore(new ResourceLocation[] { ATTACHMENT_SCOPE_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static RegistryObject<CreativeModeTab> ATTACHMENT_STOCK_TAB = TABS.register("stock", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.stock.name")).withTabsBefore(new ResourceLocation[] { ATTACHMENT_MUZZLE_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public static RegistryObject<CreativeModeTab> ATTACHMENT_GRIP_TAB = TABS.register("grip", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.grip.name")).withTabsBefore(new ResourceLocation[] { ATTACHMENT_STOCK_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static RegistryObject<CreativeModeTab> ATTACHMENT_EXTENDED_MAG_TAB = TABS.register("extended_mag", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.extended_mag.name")).withTabsBefore(new ResourceLocation[] { ATTACHMENT_GRIP_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static RegistryObject<CreativeModeTab> GUN_PISTOL_TAB = TABS.register("pistol", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.pistol.name")).withTabsBefore(new ResourceLocation[] { ATTACHMENT_EXTENDED_MAG_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static RegistryObject<CreativeModeTab> GUN_SNIPER_TAB = TABS.register("sniper", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.sniper.name")).withTabsBefore(new ResourceLocation[] { GUN_PISTOL_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static RegistryObject<CreativeModeTab> GUN_RIFLE_TAB = TABS.register("rifle", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.rifle.name")).withTabsBefore(new ResourceLocation[] { GUN_SNIPER_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static RegistryObject<CreativeModeTab> GUN_SHOTGUN_TAB = TABS.register("shotgun", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.shotgun.name")).withTabsBefore(new ResourceLocation[] { GUN_RIFLE_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static RegistryObject<CreativeModeTab> GUN_SMG_TAB = TABS.register("smg", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.smg.name")).withTabsBefore(new ResourceLocation[] { GUN_SHOTGUN_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static RegistryObject<CreativeModeTab> GUN_RPG_TAB = TABS.register("rpg", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.rpg.name")).withTabsBefore(new ResourceLocation[] { GUN_SMG_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static RegistryObject<CreativeModeTab> GUN_MG_TAB = TABS.register("mg", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_("tacz.type.mg.name")).withTabsBefore(new ResourceLocation[] { GUN_RPG_TAB.getId() }).m_257737_(()).m_257501_(()).m_257652_());
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModCreativeTabs.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */