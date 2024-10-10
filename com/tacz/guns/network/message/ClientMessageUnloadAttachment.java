/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ClientMessageUnloadAttachment {
/*    */   private final int gunSlotIndex;
/*    */   
/*    */   public ClientMessageUnloadAttachment(int gunSlotIndex, AttachmentType attachmentType) {
/* 20 */     this.gunSlotIndex = gunSlotIndex;
/* 21 */     this.attachmentType = attachmentType;
/*    */   }
/*    */   private final AttachmentType attachmentType;
/*    */   public static void encode(ClientMessageUnloadAttachment message, FriendlyByteBuf buf) {
/* 25 */     buf.writeInt(message.gunSlotIndex);
/* 26 */     buf.m_130068_((Enum)message.attachmentType);
/*    */   }
/*    */   
/*    */   public static ClientMessageUnloadAttachment decode(FriendlyByteBuf buf) {
/* 30 */     return new ClientMessageUnloadAttachment(buf.readInt(), (AttachmentType)buf.m_130066_(AttachmentType.class));
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessageUnloadAttachment message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 34 */     NetworkEvent.Context context = contextSupplier.get();
/* 35 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 36 */       context.enqueueWork(() -> {
/*    */             ServerPlayer player = context.getSender();
/*    */             
/*    */             if (player == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             Inventory inventory = player.m_150109_();
/*    */             ItemStack gunItem = inventory.m_8020_(message.gunSlotIndex);
/*    */             IGun iGun = IGun.getIGunOrNull(gunItem);
/*    */             if (iGun != null) {
/*    */               ItemStack attachmentItem = iGun.getAttachment(gunItem, message.attachmentType);
/*    */               if (!attachmentItem.m_41619_() && inventory.m_36054_(attachmentItem)) {
/*    */                 iGun.unloadAttachment(gunItem, message.attachmentType);
/*    */                 AttachmentPropertyManager.postChangeEvent((LivingEntity)player, gunItem);
/*    */                 if (message.attachmentType == AttachmentType.EXTENDED_MAG) {
/*    */                   iGun.dropAllAmmo((Player)player, gunItem);
/*    */                 }
/*    */                 player.f_36095_.m_38946_();
/*    */                 NetworkHandler.sendToClientPlayer(new ServerMessageRefreshRefitScreen(), (Player)player);
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 60 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessageUnloadAttachment.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */