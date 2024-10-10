/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.client.gui.GunRefitScreen;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageRefreshRefitScreen {
/*    */   public static void encode(ServerMessageRefreshRefitScreen message, FriendlyByteBuf buf) {}
/*    */   
/*    */   public static ServerMessageRefreshRefitScreen decode(FriendlyByteBuf buf) {
/* 19 */     return new ServerMessageRefreshRefitScreen();
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageRefreshRefitScreen message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 23 */     NetworkEvent.Context context = contextSupplier.get();
/* 24 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 25 */       context.enqueueWork(ServerMessageRefreshRefitScreen::updateScreen);
/*    */     }
/* 27 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void updateScreen() {
/* 32 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 33 */     if (player != null) { Screen screen = (Minecraft.m_91087_()).f_91080_; if (screen instanceof GunRefitScreen) { GunRefitScreen gunRefitScreen = (GunRefitScreen)screen;
/* 34 */         gunRefitScreen.m_7856_();
/*    */         
/* 36 */         AttachmentPropertyManager.postChangeEvent((LivingEntity)player, player.m_21205_()); }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageRefreshRefitScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */