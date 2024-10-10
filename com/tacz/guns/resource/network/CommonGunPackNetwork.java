/*     */ package com.tacz.guns.resource.network;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.tacz.guns.client.resource.ClientGunPackLoader;
/*     */ import com.tacz.guns.network.NetworkHandler;
/*     */ import com.tacz.guns.network.message.ServerMessageSyncGunPack;
/*     */ import com.tacz.guns.resource.loader.asset.AllowAttachmentTagsLoader;
/*     */ import com.tacz.guns.resource.loader.asset.AttachmentDataLoader;
/*     */ import com.tacz.guns.resource.loader.asset.AttachmentTagsLoader;
/*     */ import com.tacz.guns.resource.loader.asset.GunDataLoader;
/*     */ import com.tacz.guns.resource.loader.asset.RecipeLoader;
/*     */ import com.tacz.guns.resource.loader.index.CommonAmmoIndexLoader;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class CommonGunPackNetwork {
/*  25 */   private static final Pattern SHA1 = Pattern.compile("^[a-fA-F0-9]{40}$");
/*  26 */   private static final EnumMap<DataType, Map<ResourceLocation, String>> NETWORK_CACHE = new EnumMap<>(DataType.class);
/*     */   
/*     */   public static void clear() {
/*  29 */     NETWORK_CACHE.clear();
/*     */   }
/*     */   
/*     */   public static void addData(DataType type, ResourceLocation id, String json) {
/*  33 */     ((Map<ResourceLocation, String>)NETWORK_CACHE.computeIfAbsent(type, k -> Maps.newHashMap())).put(id, json);
/*     */   }
/*     */   
/*     */   public static void syncClient(MinecraftServer server) {
/*  37 */     server.m_6846_().m_11314_().forEach(player -> NetworkHandler.sendToClientPlayer(new ServerMessageSyncGunPack(NETWORK_CACHE), (Player)player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void syncClientExceptSelf(MinecraftServer server, @Nullable Player self) {
/*  42 */     server.m_6846_().m_11314_().forEach(player -> {
/*     */           if (!player.equals(self)) {
/*     */             ServerMessageSyncGunPack message = new ServerMessageSyncGunPack(NETWORK_CACHE);
/*     */             NetworkHandler.sendToClientPlayer(message, (Player)player);
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   public static void syncClient(ServerPlayer player) {
/*  51 */     NetworkHandler.sendToClientPlayer(new ServerMessageSyncGunPack(NETWORK_CACHE), (Player)player);
/*     */   }
/*     */   
/*     */   public static void toNetwork(EnumMap<DataType, Map<ResourceLocation, String>> cache, FriendlyByteBuf buf) {
/*  55 */     buf.m_130130_(cache.size());
/*  56 */     cache.forEach((type, caches) -> {
/*     */           buf.m_130068_(type);
/*     */           buf.m_130130_(caches.size());
/*     */           caches.forEach(());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumMap<DataType, Map<ResourceLocation, String>> fromNetworkCache(FriendlyByteBuf buf) {
/*  67 */     EnumMap<DataType, Map<ResourceLocation, String>> cache = Maps.newEnumMap(DataType.class);
/*  68 */     int typeSize = buf.m_130242_();
/*  69 */     for (int i = 0; i < typeSize; i++) {
/*  70 */       DataType type = (DataType)buf.m_130066_(DataType.class);
/*  71 */       int size = buf.m_130242_();
/*  72 */       for (int j = 0; j < size; j++) {
/*  73 */         ResourceLocation id = buf.m_130281_();
/*  74 */         String json = buf.m_130277_();
/*  75 */         ((Map<ResourceLocation, String>)cache.computeIfAbsent(type, k -> Maps.newHashMap())).put(id, json);
/*     */       } 
/*     */     } 
/*  78 */     return cache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void loadFromCache(EnumMap<DataType, Map<ResourceLocation, String>> allCache) {
/*  92 */     allCache.forEach((type, cache) -> cache.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     ClientGunPackLoader.reloadIndex();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\network\CommonGunPackNetwork.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */