/*    */ package com.tacz.guns.client.tooltip;
/*    */ 
/*    */ import com.tacz.guns.inventory.tooltip.AmmoBoxTooltip;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.FormattedText;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.joml.Matrix4f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientAmmoBoxTooltip
/*    */   implements ClientTooltipComponent
/*    */ {
/*    */   private final ItemStack ammo;
/*    */   private final Component count;
/*    */   private final Component ammoName;
/*    */   
/*    */   public ClientAmmoBoxTooltip(AmmoBoxTooltip tooltip) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokespecial <init> : ()V
/*    */     //   4: aload_0
/*    */     //   5: aload_1
/*    */     //   6: invokevirtual getAmmo : ()Lnet/minecraft/world/item/ItemStack;
/*    */     //   9: putfield ammo : Lnet/minecraft/world/item/ItemStack;
/*    */     //   12: aload_1
/*    */     //   13: invokevirtual getAmmoBox : ()Lnet/minecraft/world/item/ItemStack;
/*    */     //   16: astore_2
/*    */     //   17: aload_2
/*    */     //   18: invokevirtual m_41720_ : ()Lnet/minecraft/world/item/Item;
/*    */     //   21: astore #4
/*    */     //   23: aload #4
/*    */     //   25: instanceof com/tacz/guns/api/item/IAmmoBox
/*    */     //   28: ifeq -> 59
/*    */     //   31: aload #4
/*    */     //   33: checkcast com/tacz/guns/api/item/IAmmoBox
/*    */     //   36: astore_3
/*    */     //   37: aload_3
/*    */     //   38: aload_2
/*    */     //   39: invokeinterface isCreative : (Lnet/minecraft/world/item/ItemStack;)Z
/*    */     //   44: ifeq -> 59
/*    */     //   47: aload_0
/*    */     //   48: ldc '∞'
/*    */     //   50: invokestatic m_237113_ : (Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;
/*    */     //   53: putfield count : Lnet/minecraft/network/chat/Component;
/*    */     //   56: goto -> 82
/*    */     //   59: aload_0
/*    */     //   60: ldc 'tooltip.tacz.ammo_box.count'
/*    */     //   62: iconst_1
/*    */     //   63: anewarray java/lang/Object
/*    */     //   66: dup
/*    */     //   67: iconst_0
/*    */     //   68: aload_1
/*    */     //   69: invokevirtual getCount : ()I
/*    */     //   72: invokestatic valueOf : (I)Ljava/lang/Integer;
/*    */     //   75: aastore
/*    */     //   76: invokestatic m_237110_ : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*    */     //   79: putfield count : Lnet/minecraft/network/chat/Component;
/*    */     //   82: aload_0
/*    */     //   83: aload_0
/*    */     //   84: getfield ammo : Lnet/minecraft/world/item/ItemStack;
/*    */     //   87: invokevirtual m_41786_ : ()Lnet/minecraft/network/chat/Component;
/*    */     //   90: putfield ammoName : Lnet/minecraft/network/chat/Component;
/*    */     //   93: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #18	-> 0
/*    */     //   #19	-> 4
/*    */     //   #20	-> 12
/*    */     //   #21	-> 17
/*    */     //   #22	-> 47
/*    */     //   #24	-> 59
/*    */     //   #26	-> 82
/*    */     //   #27	-> 93
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   37	22	3	box	Lcom/tacz/guns/api/item/IAmmoBox;
/*    */     //   0	94	0	this	Lcom/tacz/guns/client/tooltip/ClientAmmoBoxTooltip;
/*    */     //   0	94	1	tooltip	Lcom/tacz/guns/inventory/tooltip/AmmoBoxTooltip;
/*    */     //   17	77	2	ammoBox	Lnet/minecraft/world/item/ItemStack;
/*    */   }
/*    */   
/*    */   public int m_142103_() {
/* 31 */     return 28;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142069_(Font font) {
/* 36 */     return Math.max(font.m_92852_((FormattedText)this.ammoName), font.m_92852_((FormattedText)this.count)) + 22;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_142440_(Font font, int pX, int pY, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
/* 41 */     font.m_272077_(this.ammoName, (pX + 20), (pY + 4), 16755200, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/* 42 */     font.m_272077_(this.count, (pX + 20), (pY + 15), 6710886, false, matrix4f, (MultiBufferSource)bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_183452_(Font pFont, int pX, int pY, GuiGraphics pGuiGraphics) {
/* 47 */     pGuiGraphics.m_280480_(this.ammo, pX, pY + 5);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\tooltip\ClientAmmoBoxTooltip.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */