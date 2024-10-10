/*     */ package com.tacz.guns.network;
/*     */ import com.tacz.guns.network.message.ClientMessageCraft;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerMelee;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerReloadGun;
/*     */ import com.tacz.guns.network.message.ClientMessagePlayerShoot;
/*     */ import com.tacz.guns.network.message.ClientMessageRefitGun;
/*     */ import com.tacz.guns.network.message.ServerMessageLevelUp;
/*     */ import com.tacz.guns.network.message.ServerMessageSwapItem;
/*     */ import com.tacz.guns.network.message.ServerMessageUpdateEntityData;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunFire;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunFireSelect;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunHurt;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunMelee;
/*     */ import com.tacz.guns.network.message.event.ServerMessageGunShoot;
/*     */ import com.tacz.guns.network.message.handshake.Acknowledge;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraftforge.network.NetworkDirection;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import net.minecraftforge.network.simple.SimpleChannel;
/*     */ 
/*     */ public class NetworkHandler {
/*     */   private static final String VERSION = "1.0.3";
/*     */   
/*     */   static {
/*  31 */     HANDSHAKE_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation("tacz", "handshake"), () -> "1.0.3", it -> it.equals("1.0.3"), it -> it.equals("1.0.3"));
/*     */     
/*  33 */     CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation("tacz", "network"), () -> "1.0.3", it -> it.equals("1.0.3"), it -> it.equals("1.0.3"));
/*     */   }
/*     */   public static final SimpleChannel HANDSHAKE_CHANNEL; public static final SimpleChannel CHANNEL;
/*  36 */   private static final AtomicInteger ID_COUNT = new AtomicInteger(1);
/*  37 */   private static final AtomicInteger HANDSHAKE_ID_COUNT = new AtomicInteger(1);
/*     */   
/*     */   public static void init() {
/*  40 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerShoot.class, ClientMessagePlayerShoot::encode, ClientMessagePlayerShoot::decode, ClientMessagePlayerShoot::handle, 
/*  41 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  42 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerReloadGun.class, ClientMessagePlayerReloadGun::encode, ClientMessagePlayerReloadGun::decode, ClientMessagePlayerReloadGun::handle, 
/*  43 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  44 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerFireSelect.class, ClientMessagePlayerFireSelect::encode, ClientMessagePlayerFireSelect::decode, ClientMessagePlayerFireSelect::handle, 
/*  45 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  46 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerAim.class, ClientMessagePlayerAim::encode, ClientMessagePlayerAim::decode, ClientMessagePlayerAim::handle, 
/*  47 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  48 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerCrawl.class, ClientMessagePlayerCrawl::encode, ClientMessagePlayerCrawl::decode, ClientMessagePlayerCrawl::handle, 
/*  49 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  50 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerDrawGun.class, ClientMessagePlayerDrawGun::encode, ClientMessagePlayerDrawGun::decode, ClientMessagePlayerDrawGun::handle, 
/*  51 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  52 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageSound.class, ServerMessageSound::encode, ServerMessageSound::decode, ServerMessageSound::handle, 
/*  53 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  54 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessageCraft.class, ClientMessageCraft::encode, ClientMessageCraft::decode, ClientMessageCraft::handle, 
/*  55 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  56 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageCraft.class, ServerMessageCraft::encode, ServerMessageCraft::decode, ServerMessageCraft::handle, 
/*  57 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  58 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerZoom.class, ClientMessagePlayerZoom::encode, ClientMessagePlayerZoom::decode, ClientMessagePlayerZoom::handle, 
/*  59 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  60 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessageRefitGun.class, ClientMessageRefitGun::encode, ClientMessageRefitGun::decode, ClientMessageRefitGun::handle, 
/*  61 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  62 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageRefreshRefitScreen.class, ServerMessageRefreshRefitScreen::encode, ServerMessageRefreshRefitScreen::decode, ServerMessageRefreshRefitScreen::handle, 
/*  63 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  64 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessageUnloadAttachment.class, ClientMessageUnloadAttachment::encode, ClientMessageUnloadAttachment::decode, ClientMessageUnloadAttachment::handle, 
/*  65 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  66 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageSwapItem.class, ServerMessageSwapItem::encode, ServerMessageSwapItem::decode, ServerMessageSwapItem::handle, 
/*  67 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  68 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerBoltGun.class, ClientMessagePlayerBoltGun::encode, ClientMessagePlayerBoltGun::decode, ClientMessagePlayerBoltGun::handle, 
/*  69 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*  70 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageLevelUp.class, ServerMessageLevelUp::encode, ServerMessageLevelUp::decode, ServerMessageLevelUp::handle, 
/*  71 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  72 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunHurt.class, ServerMessageGunHurt::encode, ServerMessageGunHurt::decode, ServerMessageGunHurt::handle, 
/*  73 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  74 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunKill.class, ServerMessageGunKill::encode, ServerMessageGunKill::decode, ServerMessageGunKill::handle, 
/*  75 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  76 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageUpdateEntityData.class, ServerMessageUpdateEntityData::encode, ServerMessageUpdateEntityData::decode, ServerMessageUpdateEntityData::handle, 
/*  77 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  78 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageSyncGunPack.class, ServerMessageSyncGunPack::encode, ServerMessageSyncGunPack::decode, ServerMessageSyncGunPack::handle, 
/*  79 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  80 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ClientMessagePlayerMelee.class, ClientMessagePlayerMelee::encode, ClientMessagePlayerMelee::decode, ClientMessagePlayerMelee::handle, 
/*  81 */         Optional.of(NetworkDirection.PLAY_TO_SERVER));
/*     */     
/*  83 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunDraw.class, ServerMessageGunDraw::encode, ServerMessageGunDraw::decode, ServerMessageGunDraw::handle, 
/*  84 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  85 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunFire.class, ServerMessageGunFire::encode, ServerMessageGunFire::decode, ServerMessageGunFire::handle, 
/*  86 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  87 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunFireSelect.class, ServerMessageGunFireSelect::encode, ServerMessageGunFireSelect::decode, ServerMessageGunFireSelect::handle, 
/*  88 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  89 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunMelee.class, ServerMessageGunMelee::encode, ServerMessageGunMelee::decode, ServerMessageGunMelee::handle, 
/*  90 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  91 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunReload.class, ServerMessageGunReload::encode, ServerMessageGunReload::decode, ServerMessageGunReload::handle, 
/*  92 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*  93 */     CHANNEL.registerMessage(ID_COUNT.getAndIncrement(), ServerMessageGunShoot.class, ServerMessageGunShoot::encode, ServerMessageGunShoot::decode, ServerMessageGunShoot::handle, 
/*  94 */         Optional.of(NetworkDirection.PLAY_TO_CLIENT));
/*     */     
/*  96 */     registerAcknowledge();
/*  97 */     registerHandshakeMessage(ServerMessageSyncedEntityDataMapping.class, null);
/*     */   }
/*     */   
/*     */   public static void registerAcknowledge() {
/* 101 */     Acknowledge acknowledge = new Acknowledge();
/*     */ 
/*     */     
/* 104 */     Objects.requireNonNull(acknowledge);
/* 105 */     Objects.requireNonNull(acknowledge); HANDSHAKE_CHANNEL.messageBuilder(Acknowledge.class, HANDSHAKE_ID_COUNT.getAndIncrement()).loginIndex(LoginIndexHolder::getLoginIndex, LoginIndexHolder::setLoginIndex).decoder(acknowledge::decode).encoder(acknowledge::encode)
/* 106 */       .consumerNetworkThread(HandshakeHandler.indexFirst((handler, msg, s) -> acknowledge.handle(msg, s)))
/* 107 */       .add();
/*     */   }
/*     */   
/*     */   public static <T extends LoginIndexHolder & IMessage<T>> void registerHandshakeMessage(Class<T> messageClass, @Nullable Function<Boolean, List<Pair<String, T>>> messages) {
/*     */     try {
/* 112 */       Constructor<T> constructor = messageClass.getDeclaredConstructor(new Class[0]);
/* 113 */       LoginIndexHolder loginIndexHolder = (LoginIndexHolder)constructor.newInstance(new Object[0]);
/*     */ 
/*     */       
/* 116 */       Objects.requireNonNull((IMessage)loginIndexHolder);
/* 117 */       Objects.requireNonNull((IMessage)loginIndexHolder);
/* 118 */       Objects.requireNonNull((IMessage)loginIndexHolder); SimpleChannel.MessageBuilder<T> builder = HANDSHAKE_CHANNEL.messageBuilder(messageClass, HANDSHAKE_ID_COUNT.getAndIncrement()).loginIndex(rec$ -> Integer.valueOf(((LoginIndexHolder)rec$).getLoginIndex()), (rec$, x$0) -> ((LoginIndexHolder)rec$).setLoginIndex(x$0)).encoder((x$0, x$1) -> rec$.encode(x$0, x$1)).decoder((IMessage)loginIndexHolder::decode).consumerNetworkThread((x$0, x$1) -> rec$.handle(x$0, x$1));
/* 119 */       if (messages != null) {
/* 120 */         builder.buildLoginPacketList(messages);
/*     */       } else {
/* 122 */         builder.markAsLoginPacket();
/*     */       } 
/* 124 */       builder.add();
/* 125 */     } catch (NoSuchMethodException e) {
/* 126 */       throw new IllegalArgumentException(String.format("The message %s is missing an empty parameter constructor", new Object[] { messageClass.getName() }), e);
/* 127 */     } catch (IllegalAccessException e) {
/* 128 */       throw new IllegalArgumentException(String.format("Unable to access the constructor of %s. Make sure the constructor is public.", new Object[] { messageClass.getName() }), e);
/* 129 */     } catch (Exception e) {
/* 130 */       GunMod.LOGGER.error("Fail to register handshake message {}", messageClass.getName());
/* 131 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendToClientPlayer(Object message, Player player) {
/* 136 */     CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendToTrackingEntityAndSelf(Entity centerEntity, Object message) {
/* 143 */     CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> centerEntity), message);
/*     */   }
/*     */   
/*     */   public static void sendToAllPlayers(Object message) {
/* 147 */     CHANNEL.send(PacketDistributor.ALL.noArg(), message);
/*     */   }
/*     */   
/*     */   public static void sendToTrackingEntity(Object message, Entity centerEntity) {
/* 151 */     CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> centerEntity), message);
/*     */   }
/*     */   
/*     */   public static void sendToDimension(Object message, Entity centerEntity) {
/* 155 */     ResourceKey<Level> dimension = centerEntity.m_9236_().m_46472_();
/* 156 */     CHANNEL.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\NetworkHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */