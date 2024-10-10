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
/*    */ public class ClientMessageRefitGun
/*    */ {
/*    */   private final int attachmentSlotIndex;
/*    */   
/*    */   public ClientMessageRefitGun(int attachmentSlotIndex, int gunSlotIndex, AttachmentType attachmentType) {
/* 21 */     this.attachmentSlotIndex = attachmentSlotIndex;
/* 22 */     this.gunSlotIndex = gunSlotIndex;
/* 23 */     this.attachmentType = attachmentType;
/*    */   }
/*    */   private final int gunSlotIndex; private final AttachmentType attachmentType;
/*    */   public static void encode(ClientMessageRefitGun message, FriendlyByteBuf buf) {
/* 27 */     buf.writeInt(message.attachmentSlotIndex);
/* 28 */     buf.writeInt(message.gunSlotIndex);
/* 29 */     buf.m_130068_((Enum)message.attachmentType);
/*    */   }
/*    */   
/*    */   public static ClientMessageRefitGun decode(FriendlyByteBuf buf) {
/* 33 */     return new ClientMessageRefitGun(buf.readInt(), buf.readInt(), (AttachmentType)buf.m_130066_(AttachmentType.class));
/*    */   }
/*    */   
/*    */   public static void handle(ClientMessageRefitGun message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 37 */     NetworkEvent.Context context = contextSupplier.get();
/* 38 */     if (context.getDirection().getReceptionSide().isServer()) {
/* 39 */       context.enqueueWork(() -> {
/*    */             ServerPlayer player = context.getSender();
/*    */             
/*    */             if (player == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             Inventory inventory = player.m_150109_();
/*    */             
/*    */             ItemStack attachmentItem = inventory.m_8020_(message.attachmentSlotIndex);
/*    */             
/*    */             ItemStack gunItem = inventory.m_8020_(message.gunSlotIndex);
/*    */             IGun iGun = IGun.getIGunOrNull(gunItem);
/*    */             if (iGun != null && iGun.allowAttachment(gunItem, attachmentItem)) {
/*    */               ItemStack oldAttachmentItem = iGun.getAttachment(gunItem, message.attachmentType);
/*    */               iGun.installAttachment(gunItem, attachmentItem);
/*    */               AttachmentPropertyManager.postChangeEvent((LivingEntity)player, gunItem);
/*    */               inventory.m_6836_(message.attachmentSlotIndex, oldAttachmentItem);
/*    */               if (message.attachmentType == AttachmentType.EXTENDED_MAG) {
/*    */                 iGun.dropAllAmmo((Player)player, gunItem);
/*    */               }
/*    */               player.f_36095_.m_38946_();
/*    */               NetworkHandler.sendToClientPlayer(new ServerMessageRefreshRefitScreen(), (Player)player);
/*    */             } 
/*    */           });
/*    */     }
/* 65 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ClientMessageRefitGun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */