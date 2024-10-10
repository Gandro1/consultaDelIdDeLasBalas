/*    */ package com.tacz.guns.network.message.handshake;
/*    */ 
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.network.IMessage;
/*    */ import com.tacz.guns.network.LoginIndexHolder;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public class Acknowledge
/*    */   extends LoginIndexHolder implements IMessage<Acknowledge> {
/* 14 */   public static final Marker ACKNOWLEDGE = MarkerManager.getMarker("HANDSHAKE_ACKNOWLEDGE");
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(Acknowledge message, FriendlyByteBuf buffer) {}
/*    */ 
/*    */   
/*    */   public Acknowledge decode(FriendlyByteBuf buf) {
/* 22 */     return new Acknowledge();
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(Acknowledge message, Supplier<NetworkEvent.Context> c) {
/* 27 */     GunMod.LOGGER.debug(ACKNOWLEDGE, "Received acknowledgement from client");
/* 28 */     ((NetworkEvent.Context)c.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\handshake\Acknowledge.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */