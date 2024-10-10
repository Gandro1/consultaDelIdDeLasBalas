/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.client.gui.GunSmithTableScreen;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageCraft {
/*    */   private final int menuId;
/*    */   
/*    */   public ServerMessageCraft(int menuId) {
/* 17 */     this.menuId = menuId;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageCraft message, FriendlyByteBuf buf) {
/* 21 */     buf.m_130130_(message.menuId);
/*    */   }
/*    */   
/*    */   public static ServerMessageCraft decode(FriendlyByteBuf buf) {
/* 25 */     return new ServerMessageCraft(buf.m_130242_());
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageCraft message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 29 */     NetworkEvent.Context context = contextSupplier.get();
/* 30 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 31 */       context.enqueueWork(() -> updateScreen(message.menuId));
/*    */     }
/* 33 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private static void updateScreen(int containerId) {
/* 38 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 39 */     if (player != null && player.f_36096_.f_38840_ == containerId) { Screen screen = (Minecraft.m_91087_()).f_91080_; if (screen instanceof GunSmithTableScreen) { GunSmithTableScreen gunSmithTableScreen = (GunSmithTableScreen)screen;
/* 40 */         gunSmithTableScreen.updateIngredientCount(); }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageCraft.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */