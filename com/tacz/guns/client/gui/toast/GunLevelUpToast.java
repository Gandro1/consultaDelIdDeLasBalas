/*    */ package com.tacz.guns.client.gui.toast;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.toasts.Toast;
/*    */ import net.minecraft.client.gui.components.toasts.ToastComponent;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class GunLevelUpToast
/*    */   implements Toast {
/*    */   private final Component title;
/*    */   private final Component subTitle;
/*    */   private final ItemStack icon;
/*    */   
/*    */   public GunLevelUpToast(ItemStack icon, Component titleComponent, @Nullable Component subtitle) {
/* 21 */     this.icon = icon;
/* 22 */     this.title = titleComponent;
/* 23 */     this.subTitle = subtitle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Toast.Visibility m_7172_(@NotNull GuiGraphics gui, ToastComponent toastComponent, long timeSinceLastVisible) {
/* 61 */     return (timeSinceLastVisible >= 5000L) ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\gui\toast\GunLevelUpToast.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */