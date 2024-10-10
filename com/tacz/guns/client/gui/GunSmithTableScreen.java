/*     */ package com.tacz.guns.client.gui;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.platform.Lighting;
/*     */ import com.mojang.blaze3d.platform.Window;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.math.Axis;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IAmmo;
/*     */ import com.tacz.guns.api.item.IAttachment;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.client.gui.components.smith.ResultButton;
/*     */ import com.tacz.guns.client.gui.components.smith.TypeButton;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*     */ import com.tacz.guns.crafting.GunSmithTableIngredient;
/*     */ import com.tacz.guns.crafting.GunSmithTableRecipe;
/*     */ import com.tacz.guns.init.ModCreativeTabs;
/*     */ import com.tacz.guns.inventory.GunSmithTableMenu;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ClientMessageCraft;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.Util;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.ImageButton;
/*     */ import net.minecraft.client.gui.components.Renderable;
/*     */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*     */ import net.minecraft.client.gui.screens.ConfirmLinkScreen;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*     */ import net.minecraft.core.registries.BuiltInRegistries;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.FormattedText;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.FormattedCharSequence;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.item.CreativeModeTab;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraftforge.registries.RegistryObject;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class GunSmithTableScreen extends AbstractContainerScreen<GunSmithTableMenu> {
/*  60 */   private static final ResourceLocation TEXTURE = new ResourceLocation("tacz", "textures/gui/gun_smith_table.png");
/*  61 */   private static final ResourceLocation SIDE = new ResourceLocation("tacz", "textures/gui/gun_smith_table_side.png");
/*     */   
/*  63 */   private final List<String> recipeKeys = Lists.newArrayList();
/*  64 */   private final Map<String, List<ResourceLocation>> recipes = Maps.newHashMap();
/*     */   
/*     */   private int typePage;
/*     */   private String selectedType;
/*     */   private List<ResourceLocation> selectedRecipeList;
/*     */   private int indexPage;
/*     */   @Nullable
/*     */   private GunSmithTableRecipe selectedRecipe;
/*     */   @Nullable
/*     */   private Int2IntArrayMap playerIngredientCount;
/*  74 */   private int scale = 70;
/*     */   
/*     */   public GunSmithTableScreen(GunSmithTableMenu menu, Inventory inventory, Component title) {
/*  77 */     super((AbstractContainerMenu)menu, inventory, title);
/*  78 */     this.f_97726_ = 344;
/*  79 */     this.f_97727_ = 186;
/*  80 */     classifyRecipes();
/*     */     
/*  82 */     this.typePage = 0;
/*  83 */     this.selectedType = "ammo";
/*  84 */     this.selectedRecipeList = this.recipes.get(this.selectedType);
/*     */     
/*  86 */     this.indexPage = 0;
/*  87 */     this.selectedRecipe = getSelectedRecipe(this.selectedRecipeList.get(0));
/*  88 */     getPlayerIngredientCount(this.selectedRecipe);
/*     */   }
/*     */   
/*     */   public static void drawModCenteredString(GuiGraphics gui, Font font, Component component, int pX, int pY, int color) {
/*  92 */     FormattedCharSequence text = component.m_7532_();
/*  93 */     gui.m_280649_(font, text, pX - font.m_92724_(text) / 2, pY, color, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void classifyRecipes() {
/*  99 */     putRecipeType(ModCreativeTabs.AMMO_TAB);
/*     */     
/* 101 */     putRecipeType(ModCreativeTabs.ATTACHMENT_EXTENDED_MAG_TAB);
/* 102 */     putRecipeType(ModCreativeTabs.ATTACHMENT_SCOPE_TAB);
/* 103 */     putRecipeType(ModCreativeTabs.ATTACHMENT_MUZZLE_TAB);
/* 104 */     putRecipeType(ModCreativeTabs.ATTACHMENT_STOCK_TAB);
/* 105 */     putRecipeType(ModCreativeTabs.ATTACHMENT_GRIP_TAB);
/*     */     
/* 107 */     putRecipeType(ModCreativeTabs.GUN_PISTOL_TAB);
/* 108 */     putRecipeType(ModCreativeTabs.GUN_SNIPER_TAB);
/* 109 */     putRecipeType(ModCreativeTabs.GUN_RIFLE_TAB);
/* 110 */     putRecipeType(ModCreativeTabs.GUN_SHOTGUN_TAB);
/* 111 */     putRecipeType(ModCreativeTabs.GUN_SMG_TAB);
/* 112 */     putRecipeType(ModCreativeTabs.GUN_RPG_TAB);
/* 113 */     putRecipeType(ModCreativeTabs.GUN_MG_TAB);
/*     */     
/* 115 */     TimelessAPI.getAllRecipes().forEach((id, recipe) -> {
/*     */           String groupName = recipe.getResult().getGroup();
/*     */           if (this.recipeKeys.contains(groupName)) {
/*     */             ((List<ResourceLocation>)this.recipes.computeIfAbsent(groupName, ())).add(id);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void putRecipeType(RegistryObject<CreativeModeTab> tab) {
/* 124 */     String name = tab.getId().m_135815_();
/* 125 */     this.recipeKeys.add(name);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private GunSmithTableRecipe getSelectedRecipe(ResourceLocation recipeId) {
/* 130 */     return (GunSmithTableRecipe)TimelessAPI.getAllRecipes().get(recipeId);
/*     */   }
/*     */   
/*     */   private void getPlayerIngredientCount(GunSmithTableRecipe recipe) {
/* 134 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 135 */     if (player == null) {
/*     */       return;
/*     */     }
/* 138 */     List<GunSmithTableIngredient> ingredients = recipe.getInputs();
/* 139 */     int size = ingredients.size();
/* 140 */     this.playerIngredientCount = new Int2IntArrayMap(size);
/* 141 */     for (int i = 0; i < size; i++) {
/* 142 */       GunSmithTableIngredient ingredient = ingredients.get(i);
/* 143 */       Inventory inventory = player.m_150109_();
/* 144 */       int count = 0;
/* 145 */       for (ItemStack stack : inventory.f_35974_) {
/* 146 */         if (!stack.m_41619_() && ingredient.getIngredient().test(stack)) {
/* 147 */           count += stack.m_41613_();
/*     */         }
/*     */       } 
/* 150 */       this.playerIngredientCount.put(i, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateIngredientCount() {
/* 155 */     if (this.selectedRecipe != null) {
/* 156 */       getPlayerIngredientCount(this.selectedRecipe);
/*     */     }
/* 158 */     m_7856_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7856_() {
/* 163 */     super.m_7856_();
/* 164 */     m_169413_();
/* 165 */     addTypePageButtons();
/* 166 */     addTypeButtons();
/* 167 */     addIndexPageButtons();
/* 168 */     addIndexButtons();
/* 169 */     addScaleButtons();
/* 170 */     addCraftButton();
/* 171 */     addUrlButton();
/*     */   }
/*     */   
/*     */   private void addCraftButton() {
/* 175 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 289, this.f_97736_ + 162, 48, 18, 138, 164, 18, TEXTURE, b -> {
/*     */             if (this.selectedRecipe != null && this.playerIngredientCount != null) {
/*     */               List<GunSmithTableIngredient> inputs = this.selectedRecipe.getInputs();
/*     */               int size = inputs.size();
/*     */               for (int i = 0; i < size; i++) {
/*     */                 if (i >= this.playerIngredientCount.size()) {
/*     */                   return;
/*     */                 }
/*     */                 int hasCount = this.playerIngredientCount.get(i);
/*     */                 int needCount = ((GunSmithTableIngredient)inputs.get(i)).getCount();
/*     */                 if (hasCount < needCount) {
/*     */                   return;
/*     */                 }
/*     */               } 
/*     */               NetworkHandler.CHANNEL.sendToServer(new ClientMessageCraft(this.selectedRecipe.m_6423_(), ((GunSmithTableMenu)this.f_97732_).f_38840_));
/*     */             } 
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addUrlButton() {
/* 197 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 112, this.f_97736_ + 164, 18, 18, 149, 211, 18, TEXTURE, b -> {
/*     */             if (this.selectedRecipe != null) {
/*     */               ResourceLocation id;
/*     */               ItemStack output = this.selectedRecipe.getOutput();
/*     */               Item item = output.m_41720_();
/*     */               if (item instanceof IGun) {
/*     */                 IGun iGun = (IGun)item;
/*     */                 id = iGun.getGunId(output);
/*     */               } else if (item instanceof IAttachment) {
/*     */                 IAttachment iAttachment = (IAttachment)item;
/*     */                 id = iAttachment.getAttachmentId(output);
/*     */               } else if (item instanceof IAmmo) {
/*     */                 IAmmo iAmmo = (IAmmo)item;
/*     */                 id = iAmmo.getAmmoId(output);
/*     */               } else {
/*     */                 return;
/*     */               } 
/*     */               PackInfo packInfo = ClientAssetManager.INSTANCE.getPackInfo(id);
/*     */               if (packInfo == null) {
/*     */                 return;
/*     */               }
/*     */               String url = packInfo.getUrl();
/*     */               if (StringUtils.isNotBlank(url) && this.f_96541_ != null) {
/*     */                 this.f_96541_.m_91152_((Screen)new ConfirmLinkScreen((), url, false));
/*     */               }
/*     */             } 
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addIndexButtons() {
/* 230 */     if (this.selectedRecipeList == null || this.selectedRecipeList.isEmpty()) {
/*     */       return;
/*     */     }
/* 233 */     for (int i = 0; i < 6; i++) {
/* 234 */       int finalIndex = i + this.indexPage * 6;
/* 235 */       if (finalIndex >= this.selectedRecipeList.size()) {
/*     */         break;
/*     */       }
/* 238 */       int yOffset = this.f_97736_ + 66 + 17 * i;
/* 239 */       TimelessAPI.getRecipe(this.selectedRecipeList.get(finalIndex)).ifPresent(recipe -> {
/*     */             ResultButton button = (ResultButton)m_142416_((GuiEventListener)new ResultButton(this.f_97735_ + 144, yOffset, recipe.getOutput(), ()));
/*     */             if (this.selectedRecipe != null && recipe.m_6423_().equals(this.selectedRecipe.m_6423_())) {
/*     */               button.setSelected(true);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTypeButtons() {
/* 253 */     for (int i = 0; i < 7; i++) {
/* 254 */       int typeIndex = this.typePage * 7 + i;
/* 255 */       if (typeIndex >= this.recipes.size()) {
/*     */         return;
/*     */       }
/* 258 */       String type = this.recipeKeys.get(typeIndex);
/* 259 */       int xOffset = this.f_97735_ + 157 + 24 * i;
/* 260 */       List<ResourceLocation> recipeIdGroups = this.recipes.get(type);
/* 261 */       if (!recipeIdGroups.isEmpty()) {
/*     */ 
/*     */         
/* 264 */         ItemStack icon = ItemStack.f_41583_;
/* 265 */         ResourceLocation tabId = new ResourceLocation("tacz", type);
/* 266 */         CreativeModeTab modTab = (CreativeModeTab)BuiltInRegistries.f_279662_.m_7745_(tabId);
/* 267 */         if (modTab != null) {
/* 268 */           icon = modTab.m_40787_();
/*     */         }
/* 270 */         TypeButton typeButton = new TypeButton(xOffset, this.f_97736_ + 2, icon, b -> {
/*     */               this.selectedType = type;
/*     */               this.selectedRecipeList = this.recipes.get(type);
/*     */               this.indexPage = 0;
/*     */               this.selectedRecipe = getSelectedRecipe(this.selectedRecipeList.get(0));
/*     */               getPlayerIngredientCount(this.selectedRecipe);
/*     */               m_7856_();
/*     */             });
/* 278 */         if (this.selectedType.equals(type)) {
/* 279 */           typeButton.setSelected(true);
/*     */         }
/* 281 */         m_142416_((GuiEventListener)typeButton);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void addIndexPageButtons() {
/* 286 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 143, this.f_97736_ + 56, 96, 6, 40, 166, 6, TEXTURE, b -> {
/*     */             if (this.indexPage > 0) {
/*     */               this.indexPage--;
/*     */               m_7856_();
/*     */             } 
/*     */           }));
/* 292 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 143, this.f_97736_ + 171, 96, 6, 40, 186, 6, TEXTURE, b -> {
/*     */             if (this.selectedRecipeList != null && !this.selectedRecipeList.isEmpty()) {
/*     */               int maxIndexPage = (this.selectedRecipeList.size() - 1) / 6;
/*     */               if (this.indexPage < maxIndexPage) {
/*     */                 this.indexPage++;
/*     */                 m_7856_();
/*     */               } 
/*     */             } 
/*     */           }));
/*     */   }
/*     */   
/*     */   private void addTypePageButtons() {
/* 304 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 136, this.f_97736_ + 4, 18, 20, 0, 162, 20, TEXTURE, b -> {
/*     */             if (this.typePage > 0) {
/*     */               this.typePage--;
/*     */               m_7856_();
/*     */             } 
/*     */           }));
/* 310 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 327, this.f_97736_ + 4, 18, 20, 20, 162, 20, TEXTURE, b -> {
/*     */             int maxIndexPage = (this.recipes.size() - 1) / 7;
/*     */             if (this.typePage < maxIndexPage) {
/*     */               this.typePage++;
/*     */               m_7856_();
/*     */             } 
/*     */           }));
/*     */   }
/*     */   
/*     */   private void addScaleButtons() {
/* 320 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 5, this.f_97736_ + 5, 10, 10, 188, 173, 10, TEXTURE, b -> this.scale = Math.min(this.scale + 20, 200)));
/*     */ 
/*     */     
/* 323 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 17, this.f_97736_ + 5, 10, 10, 200, 173, 10, TEXTURE, b -> this.scale = Math.max(this.scale - 20, 10)));
/*     */ 
/*     */     
/* 326 */     m_142416_((GuiEventListener)new ImageButton(this.f_97735_ + 29, this.f_97736_ + 5, 10, 10, 212, 173, 10, TEXTURE, b -> this.scale = 70));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_88315_(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
/* 333 */     super.m_88315_(graphics, mouseX, mouseY, partialTick);
/* 334 */     drawModCenteredString(graphics, this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.preview"), this.f_97735_ + 108, this.f_97736_ + 5, 5592405);
/* 335 */     graphics.m_280614_(this.f_96547_, (Component)Component.m_237115_(String.format("tacz.type.%s.name", new Object[] { this.selectedType })), this.f_97735_ + 150, this.f_97736_ + 32, 5592405, false);
/* 336 */     graphics.m_280614_(this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.ingredient"), this.f_97735_ + 254, this.f_97736_ + 50, 5592405, false);
/* 337 */     drawModCenteredString(graphics, this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.craft"), this.f_97735_ + 312, this.f_97736_ + 167, 16777215);
/* 338 */     if (this.selectedRecipe != null) {
/* 339 */       renderLeftModel(this.selectedRecipe);
/* 340 */       renderPackInfo(graphics, this.selectedRecipe);
/* 341 */       graphics.m_280614_(this.f_96547_, (Component)Component.m_237110_("gui.tacz.gun_smith_table.count", new Object[] { Integer.valueOf(this.selectedRecipe.getResult().getResult().m_41613_()) }), this.f_97735_ + 254, this.f_97736_ + 140, 5592405, false);
/*     */     } 
/* 343 */     if (this.selectedRecipeList != null && !this.selectedRecipeList.isEmpty()) {
/* 344 */       renderIngredient(graphics);
/*     */     }
/*     */     
/* 347 */     this.f_169369_.stream().filter(w -> w instanceof ResultButton)
/* 348 */       .forEach(w -> ((ResultButton)w).renderTooltips(()));
/*     */   }
/*     */   private void renderPackInfo(GuiGraphics gui, GunSmithTableRecipe recipe) {
/*     */     ResourceLocation id;
/* 352 */     ItemStack output = recipe.getOutput();
/* 353 */     Item item = output.m_41720_();
/*     */     
/* 355 */     if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 356 */       id = iGun.getGunId(output); }
/* 357 */     else if (item instanceof IAttachment) { IAttachment iAttachment = (IAttachment)item;
/* 358 */       id = iAttachment.getAttachmentId(output); }
/* 359 */     else if (item instanceof IAmmo) { IAmmo iAmmo = (IAmmo)item;
/* 360 */       id = iAmmo.getAmmoId(output); }
/*     */     else
/*     */     { return; }
/*     */ 
/*     */     
/* 365 */     PackInfo packInfo = ClientAssetManager.INSTANCE.getPackInfo(id);
/* 366 */     PoseStack poseStack = gui.m_280168_();
/* 367 */     if (packInfo != null) {
/* 368 */       poseStack.m_85836_();
/* 369 */       poseStack.m_85841_(0.75F, 0.75F, 1.0F);
/* 370 */       MutableComponent mutableComponent1 = Component.m_237115_(packInfo.getName());
/* 371 */       gui.m_280614_(this.f_96547_, (Component)mutableComponent1, (int)((this.f_97735_ + 6) / 0.75F), (int)((this.f_97736_ + 122) / 0.75F), ChatFormatting.DARK_GRAY.m_126665_().intValue(), false);
/* 372 */       poseStack.m_85849_();
/*     */       
/* 374 */       poseStack.m_85836_();
/* 375 */       poseStack.m_85841_(0.5F, 0.5F, 1.0F);
/*     */       
/* 377 */       int offsetX = (this.f_97735_ + 6) * 2;
/* 378 */       int offsetY = (this.f_97736_ + 123) * 2;
/* 379 */       int nameWidth = this.f_96547_.m_92852_((FormattedText)mutableComponent1);
/* 380 */       MutableComponent mutableComponent2 = Component.m_237113_("v" + packInfo.getVersion()).m_130940_(ChatFormatting.UNDERLINE);
/* 381 */       gui.m_280614_(this.f_96547_, (Component)mutableComponent2, (int)(offsetX + nameWidth * 0.75F / 0.5F + 5.0F), offsetY, ChatFormatting.DARK_GRAY.m_126665_().intValue(), false);
/* 382 */       offsetY += 14;
/*     */       
/* 384 */       String descKey = packInfo.getDescription();
/* 385 */       if (StringUtils.isNoneBlank(new CharSequence[] { descKey })) {
/* 386 */         MutableComponent mutableComponent = Component.m_237115_(descKey);
/* 387 */         List<FormattedCharSequence> split = this.f_96547_.m_92923_((FormattedText)mutableComponent, 245);
/* 388 */         for (FormattedCharSequence charSequence : split) {
/* 389 */           gui.m_280649_(this.f_96547_, charSequence, offsetX, offsetY, ChatFormatting.DARK_GRAY.m_126665_().intValue(), false);
/* 390 */           Objects.requireNonNull(this.f_96547_); offsetY += 9;
/*     */         } 
/* 392 */         offsetY += 3;
/*     */       } 
/*     */       
/* 395 */       gui.m_280614_(this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.license")
/* 396 */           .m_7220_((Component)Component.m_237113_(packInfo.getLicense()).m_130940_(ChatFormatting.DARK_GRAY)), offsetX, offsetY, ChatFormatting.DARK_GRAY
/* 397 */           .m_126665_().intValue(), false);
/* 398 */       offsetY += 12;
/*     */       
/* 400 */       List<String> authors = packInfo.getAuthors();
/* 401 */       if (!authors.isEmpty()) {
/* 402 */         gui.m_280614_(this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.authors")
/* 403 */             .m_7220_((Component)Component.m_237113_(StringUtils.join(authors, ", ")).m_130940_(ChatFormatting.DARK_GRAY)), offsetX, offsetY, ChatFormatting.DARK_GRAY
/* 404 */             .m_126665_().intValue(), false);
/* 405 */         offsetY += 12;
/*     */       } 
/*     */       
/* 408 */       gui.m_280614_(this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.date")
/* 409 */           .m_7220_((Component)Component.m_237113_(packInfo.getDate()).m_130940_(ChatFormatting.DARK_GRAY)), offsetX, offsetY, ChatFormatting.DARK_GRAY
/* 410 */           .m_126665_().intValue(), false);
/*     */       
/* 412 */       poseStack.m_85849_();
/*     */     } else {
/* 414 */       ResourceLocation recipeId = recipe.m_6423_();
/* 415 */       gui.m_280614_(this.f_96547_, (Component)Component.m_237115_("gui.tacz.gun_smith_table.error").m_130940_(ChatFormatting.DARK_RED), this.f_97735_ + 6, this.f_97736_ + 122, 11468800, false);
/* 416 */       gui.m_280614_(this.f_96547_, (Component)Component.m_237110_("gui.tacz.gun_smith_table.error.id", new Object[] { recipeId.toString() }).m_130940_(ChatFormatting.DARK_RED), this.f_97735_ + 6, this.f_97736_ + 134, 16777215, false);
/* 417 */       PackInfo errorPackInfo = ClientAssetManager.INSTANCE.getPackInfo(recipeId);
/* 418 */       if (errorPackInfo != null) {
/* 419 */         gui.m_280614_(this.f_96547_, (Component)Component.m_237115_(errorPackInfo.getName()).m_130940_(ChatFormatting.DARK_RED), this.f_97735_ + 6, this.f_97736_ + 146, 11468800, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderIngredient(GuiGraphics gui) {
/* 425 */     if (this.selectedRecipe == null) {
/*     */       return;
/*     */     }
/* 428 */     List<GunSmithTableIngredient> inputs = this.selectedRecipe.getInputs();
/* 429 */     for (int i = 0; i < 6; i++) {
/* 430 */       for (int j = 0; j < 2; j++) {
/* 431 */         int index = i * 2 + j;
/* 432 */         if (index >= inputs.size()) {
/*     */           return;
/*     */         }
/* 435 */         int offsetX = this.f_97735_ + 254 + 45 * j;
/* 436 */         int offsetY = this.f_97736_ + 62 + 17 * i;
/*     */         
/* 438 */         GunSmithTableIngredient smithTableIngredient = inputs.get(index);
/* 439 */         Ingredient ingredient = smithTableIngredient.getIngredient();
/*     */         
/* 441 */         ItemStack[] items = ingredient.m_43908_();
/* 442 */         int itemIndex = (int)(System.currentTimeMillis() / 1000L) % items.length;
/* 443 */         ItemStack item = items[itemIndex];
/*     */         
/* 445 */         gui.m_280203_(item, offsetX, offsetY);
/*     */         
/* 447 */         PoseStack poseStack = gui.m_280168_();
/* 448 */         poseStack.m_85836_();
/*     */         
/* 450 */         poseStack.m_252880_(0.0F, 0.0F, 200.0F);
/* 451 */         poseStack.m_85841_(0.5F, 0.5F, 1.0F);
/* 452 */         int count = smithTableIngredient.getCount();
/* 453 */         int hasCount = 0;
/* 454 */         if (this.playerIngredientCount != null && index < this.playerIngredientCount.size()) {
/* 455 */           hasCount = this.playerIngredientCount.get(index);
/*     */         }
/* 457 */         int color = (count <= hasCount) ? 16777215 : 16711680;
/* 458 */         gui.m_280056_(this.f_96547_, String.format("%d/%d", new Object[] { Integer.valueOf(count), Integer.valueOf(hasCount) }), (offsetX + 17) * 2, (offsetY + 10) * 2, color, false);
/*     */         
/* 460 */         poseStack.m_85849_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderLeftModel(GunSmithTableRecipe recipe) {
/* 468 */     RenderDistance.markGuiRenderTimestamp();
/*     */     
/* 470 */     float rotationPeriod = 8.0F;
/* 471 */     int xPos = this.f_97735_ + 60;
/* 472 */     int yPos = this.f_97736_ + 50;
/* 473 */     int startX = this.f_97735_ + 3;
/* 474 */     int startY = this.f_97736_ + 16;
/* 475 */     int width = 128;
/* 476 */     int height = 99;
/* 477 */     float rotPitch = 15.0F;
/*     */     
/* 479 */     Window window = Minecraft.m_91087_().m_91268_();
/* 480 */     double windowGuiScale = window.m_85449_();
/* 481 */     int scissorX = (int)(startX * windowGuiScale);
/* 482 */     int scissorY = (int)(window.m_85442_() - (startY + height) * windowGuiScale);
/* 483 */     int scissorW = (int)(width * windowGuiScale);
/* 484 */     int scissorH = (int)(height * windowGuiScale);
/* 485 */     RenderSystem.enableScissor(scissorX, scissorY, scissorW, scissorH);
/*     */     
/* 487 */     (Minecraft.m_91087_()).f_90987_.m_118506_(TextureAtlas.f_118259_).m_117960_(false, false);
/* 488 */     RenderSystem.setShaderTexture(0, TextureAtlas.f_118259_);
/* 489 */     RenderSystem.enableBlend();
/* 490 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 491 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 492 */     PoseStack posestack = RenderSystem.getModelViewStack();
/* 493 */     posestack.m_85836_();
/* 494 */     posestack.m_252880_(xPos, yPos, 200.0F);
/* 495 */     posestack.m_85837_(8.0D, 8.0D, 0.0D);
/* 496 */     posestack.m_85841_(1.0F, -1.0F, 1.0F);
/* 497 */     posestack.m_85841_(this.scale, this.scale, this.scale);
/* 498 */     float rot = (float)(System.currentTimeMillis() % (int)(rotationPeriod * 1000.0F)) * 360.0F / rotationPeriod * 1000.0F;
/* 499 */     posestack.m_252781_(Axis.f_252529_.m_252977_(rotPitch));
/* 500 */     posestack.m_252781_(Axis.f_252436_.m_252977_(rot));
/* 501 */     RenderSystem.applyModelViewMatrix();
/* 502 */     PoseStack tmpPose = new PoseStack();
/* 503 */     MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
/* 504 */     Lighting.m_84930_();
/*     */     
/* 506 */     Minecraft.m_91087_().m_91291_().m_269128_(recipe.getOutput(), ItemDisplayContext.FIXED, 15728880, OverlayTexture.f_118083_, tmpPose, (MultiBufferSource)bufferSource, null, 0);
/*     */     
/* 508 */     bufferSource.m_109911_();
/* 509 */     RenderSystem.enableDepthTest();
/* 510 */     Lighting.m_84931_();
/* 511 */     posestack.m_85849_();
/* 512 */     RenderSystem.applyModelViewMatrix();
/*     */     
/* 514 */     RenderSystem.disableScissor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_280003_(@NotNull GuiGraphics gui, int mouseX, int mouseY) {}
/*     */ 
/*     */   
/*     */   protected void m_7286_(@NotNull GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
/* 523 */     m_280273_(gui);
/* 524 */     gui.m_280218_(SIDE, this.f_97735_, this.f_97736_, 0, 0, 134, 187);
/* 525 */     gui.m_280218_(TEXTURE, this.f_97735_ + 136, this.f_97736_ + 27, 0, 0, 208, 160);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7043_() {
/* 530 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\GunSmithTableScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */