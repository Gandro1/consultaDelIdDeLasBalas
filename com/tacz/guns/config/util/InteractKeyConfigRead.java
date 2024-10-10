/*    */ package com.tacz.guns.config.util;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.tacz.guns.config.sync.SyncConfig;
/*    */ import java.util.EnumMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ public class InteractKeyConfigRead
/*    */ {
/* 21 */   private static final EnumMap<Type, List<ResourceLocation>> WHITELIST = new EnumMap<>(Type.class);
/* 22 */   private static final EnumMap<Type, List<ResourceLocation>> BLACKLIST = new EnumMap<>(Type.class);
/* 23 */   private static final TagKey<Block> WHITELIST_BLOCKS = BlockTags.create(new ResourceLocation("tacz", "interact_key/whitelist"));
/* 24 */   private static final TagKey<Block> BLACKLIST_BLOCKS = BlockTags.create(new ResourceLocation("tacz", "interact_key/blacklist"));
/* 25 */   private static final TagKey<EntityType<?>> WHITELIST_ENTITIES = TagKey.m_203882_(Registries.f_256939_, new ResourceLocation("tacz", "interact_key/whitelist"));
/* 26 */   private static final TagKey<EntityType<?>> BLACKLIST_ENTITIES = TagKey.m_203882_(Registries.f_256939_, new ResourceLocation("tacz", "interact_key/blacklist"));
/*    */   
/*    */   public static void init() {
/* 29 */     WHITELIST.clear();
/* 30 */     BLACKLIST.clear();
/* 31 */     handleConfigData((List<String>)SyncConfig.INTERACT_KEY_WHITELIST_BLOCKS.get(), WHITELIST, Type.BLOCK);
/* 32 */     handleConfigData((List<String>)SyncConfig.INTERACT_KEY_WHITELIST_ENTITIES.get(), WHITELIST, Type.ENTITY);
/* 33 */     handleConfigData((List<String>)SyncConfig.INTERACT_KEY_BLACKLIST_BLOCKS.get(), BLACKLIST, Type.BLOCK);
/* 34 */     handleConfigData((List<String>)SyncConfig.INTERACT_KEY_BLACKLIST_ENTITIES.get(), BLACKLIST, Type.ENTITY);
/*    */   }
/*    */   
/*    */   public static boolean canInteractBlock(BlockState block) {
/* 38 */     ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block.m_60734_());
/* 39 */     if (blockId == null) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     if (BLACKLIST.containsKey(Type.BLOCK) && ((List)BLACKLIST.get(Type.BLOCK)).contains(blockId)) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (block.m_204336_(BLACKLIST_BLOCKS)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (WHITELIST.containsKey(Type.BLOCK) && ((List)WHITELIST.get(Type.BLOCK)).contains(blockId)) {
/* 51 */       return true;
/*    */     }
/* 53 */     return block.m_204336_(WHITELIST_BLOCKS);
/*    */   }
/*    */   
/*    */   public static boolean canInteractEntity(Entity entity) {
/* 57 */     ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_());
/* 58 */     if (entityId == null) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     if (BLACKLIST.containsKey(Type.ENTITY) && ((List)BLACKLIST.get(Type.ENTITY)).contains(entityId)) {
/* 63 */       return false;
/*    */     }
/* 65 */     if (entity.m_6095_().m_204039_(BLACKLIST_ENTITIES)) {
/* 66 */       return false;
/*    */     }
/*    */     
/* 69 */     if (WHITELIST.containsKey(Type.ENTITY) && ((List)WHITELIST.get(Type.ENTITY)).contains(entityId)) {
/* 70 */       return true;
/*    */     }
/* 72 */     return entity.m_6095_().m_204039_(WHITELIST_ENTITIES);
/*    */   }
/*    */   
/*    */   private static void handleConfigData(List<String> configData, EnumMap<Type, List<ResourceLocation>> storeList, Type type) {
/* 76 */     configData.forEach(data -> {
/*    */           if (data.isEmpty()) {
/*    */             return;
/*    */           }
/*    */           if (StringUtils.isBlank(data))
/*    */             return; 
/*    */           ResourceLocation id = new ResourceLocation(data);
/*    */           ((List<ResourceLocation>)storeList.computeIfAbsent(type, ())).add(id);
/*    */         });
/*    */   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 89 */     BLOCK, ENTITY;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\confi\\util\InteractKeyConfigRead.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */