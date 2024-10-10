/*    */ package com.tacz.guns.network.message;
/*    */ 
/*    */ import com.tacz.guns.client.sound.SoundPlayManager;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class ServerMessageSound
/*    */ {
/*    */   private final int entityId;
/*    */   private final ResourceLocation gunId;
/*    */   private final String soundName;
/*    */   private final float volume;
/*    */   private final float pitch;
/*    */   private final int distance;
/*    */   
/*    */   public ServerMessageSound(int entityId, ResourceLocation gunId, String soundName, float volume, float pitch, int distance) {
/* 19 */     this.entityId = entityId;
/* 20 */     this.gunId = gunId;
/* 21 */     this.soundName = soundName;
/* 22 */     this.volume = volume;
/* 23 */     this.pitch = pitch;
/* 24 */     this.distance = distance;
/*    */   }
/*    */   
/*    */   public static void encode(ServerMessageSound message, FriendlyByteBuf buf) {
/* 28 */     buf.m_130130_(message.entityId);
/* 29 */     buf.m_130085_(message.gunId);
/* 30 */     buf.m_130070_(message.soundName);
/* 31 */     buf.writeFloat(message.volume);
/* 32 */     buf.writeFloat(message.pitch);
/* 33 */     buf.writeInt(message.distance);
/*    */   }
/*    */   
/*    */   public static ServerMessageSound decode(FriendlyByteBuf buf) {
/* 37 */     int entityId = buf.m_130242_();
/* 38 */     ResourceLocation soundId = buf.m_130281_();
/* 39 */     String soundName = buf.m_130277_();
/* 40 */     float volume = buf.readFloat();
/* 41 */     float pitch = buf.readFloat();
/* 42 */     int distance = buf.readInt();
/* 43 */     return new ServerMessageSound(entityId, soundId, soundName, volume, pitch, distance);
/*    */   }
/*    */   
/*    */   public static void handle(ServerMessageSound message, Supplier<NetworkEvent.Context> contextSupplier) {
/* 47 */     NetworkEvent.Context context = contextSupplier.get();
/* 48 */     if (context.getDirection().getReceptionSide().isClient()) {
/* 49 */       context.enqueueWork(() -> SoundPlayManager.playMessageSound(message));
/*    */     }
/* 51 */     context.setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public int getEntityId() {
/* 55 */     return this.entityId;
/*    */   }
/*    */   
/*    */   public ResourceLocation getGunId() {
/* 59 */     return this.gunId;
/*    */   }
/*    */   
/*    */   public String getSoundName() {
/* 63 */     return this.soundName;
/*    */   }
/*    */   
/*    */   public float getVolume() {
/* 67 */     return this.volume;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 71 */     return this.pitch;
/*    */   }
/*    */   
/*    */   public int getDistance() {
/* 75 */     return this.distance;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\message\ServerMessageSound.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */