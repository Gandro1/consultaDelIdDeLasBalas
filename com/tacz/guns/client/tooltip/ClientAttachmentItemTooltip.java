/*     */ package com.tacz.guns.client.tooltip;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*     */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*     */ import com.tacz.guns.api.item.builder.GunItemBuilder;
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.client.resource.ClientAssetManager;
/*     */ import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
/*     */ import com.tacz.guns.client.resource.pojo.PackInfo;
/*     */ import com.tacz.guns.inventory.tooltip.AttachmentItemTooltip;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.AttachmentData;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.resources.language.I18n;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.FormattedText;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public class ClientAttachmentItemTooltip implements ClientTooltipComponent {
/*  36 */   private static final Cache<ResourceLocation, List<ItemStack>> CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.SECONDS).build();
/*     */   private final ResourceLocation attachmentId;
/*  38 */   private final List<Component> components = Lists.newArrayList();
/*  39 */   private final MutableComponent tips = Component.m_237115_("tooltip.tacz.attachment.yaw.shift");
/*  40 */   private final MutableComponent support = Component.m_237115_("tooltip.tacz.attachment.yaw.support"); @Nullable
/*     */   private MutableComponent packInfo;
/*  42 */   private List<ItemStack> showGuns = Lists.newArrayList();
/*     */   
/*     */   public ClientAttachmentItemTooltip(AttachmentItemTooltip tooltip) {
/*  45 */     this.attachmentId = tooltip.getAttachmentId();
/*  46 */     addText(tooltip.getType());
/*  47 */     getShowGuns();
/*  48 */     addPackInfo();
/*     */   }
/*     */   
/*     */   private void addPackInfo() {
/*  52 */     PackInfo packInfoObject = ClientAssetManager.INSTANCE.getPackInfo(this.attachmentId);
/*  53 */     if (packInfoObject != null) {
/*  54 */       this.packInfo = Component.m_237115_(packInfoObject.getName()).m_130940_(ChatFormatting.BLUE).m_130940_(ChatFormatting.ITALIC);
/*     */     }
/*     */   }
/*     */   
/*     */   private static List<ItemStack> getAllAllowGuns(List<ItemStack> output, ResourceLocation attachmentId) {
/*  59 */     ItemStack attachment = AttachmentItemBuilder.create().setId(attachmentId).build();
/*  60 */     TimelessAPI.getAllCommonGunIndex().forEach(entry -> {
/*     */           IGun iGun; ResourceLocation gunId = (ResourceLocation)entry.getKey(); ItemStack gun = GunItemBuilder.create().setId(gunId).build(); Item patt3074$temp = gun.m_41720_();
/*     */           if (patt3074$temp instanceof IGun) {
/*     */             iGun = (IGun)patt3074$temp;
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */           if (iGun.allowAttachment(gun, attachment))
/*     */             output.add(gun); 
/*     */         });
/*  70 */     return output;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142103_() {
/*  75 */     if (!Screen.m_96638_()) {
/*  76 */       return this.components.size() * 10 + 28;
/*     */     }
/*  78 */     return (this.showGuns.size() - 1) / 16 * 18 + 50 + this.components.size() * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142069_(Font font) {
/*  83 */     int[] width = { 0 };
/*  84 */     if (this.packInfo != null) {
/*  85 */       width[0] = Math.max(width[0], font.m_92852_((FormattedText)this.packInfo) + 4);
/*     */     }
/*  87 */     this.components.forEach(c -> width[0] = Math.max(width[0], font.m_92852_((FormattedText)c)));
/*  88 */     if (!Screen.m_96638_()) {
/*  89 */       return Math.max(width[0], font.m_92852_((FormattedText)this.tips) + 4);
/*     */     }
/*  91 */     width[0] = Math.max(width[0], font.m_92852_((FormattedText)this.support) + 4);
/*     */     
/*  93 */     if (this.showGuns.size() > 15) {
/*  94 */       return Math.max(width[0], 260);
/*     */     }
/*  96 */     return Math.max(width[0], this.showGuns.size() * 16 + 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142440_(Font font, int pX, int pY, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
/* 101 */     int yOffset = pY;
/* 102 */     for (Component component : this.components) {
/* 103 */       font.m_272077_(component, pX, yOffset, 16755200, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 104 */       yOffset += 10;
/*     */     } 
/* 106 */     if (!Screen.m_96638_()) {
/* 107 */       font.m_272077_((Component)this.tips, pX, (pY + 5 + this.components.size() * 10), 10395294, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 108 */       yOffset += 10;
/*     */     } else {
/* 110 */       yOffset += (this.showGuns.size() - 1) / 16 * 18 + 32;
/*     */     } 
/*     */     
/* 113 */     if (this.packInfo != null) {
/* 114 */       font.m_272077_((Component)this.packInfo, pX, (yOffset + 8), 16777215, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_183452_(Font font, int mouseX, int mouseY, GuiGraphics gui) {
/* 120 */     if (!Screen.m_96638_()) {
/*     */       return;
/*     */     }
/* 123 */     int minY = this.components.size() * 10 + 3;
/* 124 */     int maxX = m_142069_(font);
/* 125 */     gui.m_280509_(mouseX, mouseY + minY, mouseX + maxX, mouseY + minY + 11, -1895780097);
/* 126 */     gui.m_280430_(font, (Component)this.support, mouseX + 2, mouseY + minY + 2, 14938877);
/*     */     
/* 128 */     for (int i = 0; i < this.showGuns.size(); i++) {
/* 129 */       ItemStack stack = this.showGuns.get(i);
/* 130 */       int x = i % 16 * 16 + 2;
/* 131 */       int y = i / 16 * 18 + minY + 15;
/* 132 */       gui.m_280480_(stack, mouseX + x, mouseY + y);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getShowGuns() {
/*     */     try {
/* 138 */       this.showGuns = (List<ItemStack>)CACHE.get(this.attachmentId, () -> getAllAllowGuns(Lists.newArrayList(), this.attachmentId));
/* 139 */     } catch (ExecutionException e) {
/* 140 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addText(AttachmentType type) {
/* 145 */     TimelessAPI.getClientAttachmentIndex(this.attachmentId).ifPresent(index -> {
/*     */           AttachmentData data = index.getData();
/*     */           String tooltipKey = index.getTooltipKey();
/*     */           if (tooltipKey != null) {
/*     */             String text = I18n.m_118938_(tooltipKey, new Object[0]);
/*     */             String[] split = text.split("\n");
/*     */             Arrays.<String>stream(split).forEach(());
/*     */           } 
/*     */           if (type == AttachmentType.SCOPE) {
/*     */             float[] zoom = index.getZoom();
/*     */             if (zoom != null) {
/*     */               String[] zoomText = new String[zoom.length];
/*     */               for (int i = 0; i < zoom.length; i++)
/*     */                 zoomText[i] = "x" + zoom[i]; 
/*     */               String zoomJoinText = StringUtils.join((Object[])zoomText, ", ");
/*     */               this.components.add(Component.m_237110_("tooltip.tacz.attachment.zoom", new Object[] { zoomJoinText }).m_130940_(ChatFormatting.GOLD));
/*     */             } 
/*     */           } 
/*     */           if (type == AttachmentType.EXTENDED_MAG) {
/*     */             int magLevel = data.getExtendedMagLevel();
/*     */             if (magLevel == 1) {
/*     */               this.components.add(Component.m_237115_("tooltip.tacz.attachment.extended_mag_level_1").m_130940_(ChatFormatting.GRAY));
/*     */             } else if (magLevel == 2) {
/*     */               this.components.add(Component.m_237115_("tooltip.tacz.attachment.extended_mag_level_2").m_130940_(ChatFormatting.BLUE));
/*     */             } else if (magLevel == 3) {
/*     */               this.components.add(Component.m_237115_("tooltip.tacz.attachment.extended_mag_level_3").m_130940_(ChatFormatting.LIGHT_PURPLE));
/*     */             } 
/*     */           } 
/*     */           data.getModifier().forEach(());
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\tooltip\ClientAttachmentItemTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */