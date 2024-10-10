/*    */ package com.tacz.guns.entity.sync;
/*    */ 
/*    */ import com.tacz.guns.api.entity.ReloadState;
/*    */ import com.tacz.guns.entity.sync.core.Serializers;
/*    */ import com.tacz.guns.entity.sync.core.SyncedClassKey;
/*    */ import com.tacz.guns.entity.sync.core.SyncedDataKey;
/*    */ import com.tacz.guns.entity.sync.core.SyncedEntityData;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ 
/*    */ public class ModSyncedEntityData {
/* 14 */   public static final SyncedDataKey<LivingEntity, Long> SHOOT_COOL_DOWN_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.LONG)
/* 15 */     .id(new ResourceLocation("tacz", "shoot_cool_down"))
/* 16 */     .defaultValueSupplier(() -> Long.valueOf(-1L))
/* 17 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 18 */     .build();
/*    */   
/* 20 */   public static final SyncedDataKey<LivingEntity, Long> MELEE_COOL_DOWN_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.LONG)
/* 21 */     .id(new ResourceLocation("tacz", "melee_cool_down"))
/* 22 */     .defaultValueSupplier(() -> Long.valueOf(-1L))
/* 23 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 24 */     .build();
/*    */   
/* 26 */   public static final SyncedDataKey<LivingEntity, ReloadState> RELOAD_STATE_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, ModSerializers.RELOAD_STATE)
/* 27 */     .id(new ResourceLocation("tacz", "reload_state"))
/* 28 */     .defaultValueSupplier(ReloadState::new)
/* 29 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 30 */     .build();
/*    */   
/* 32 */   public static final SyncedDataKey<LivingEntity, Float> AIMING_PROGRESS_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.FLOAT)
/* 33 */     .id(new ResourceLocation("tacz", "aiming_progress"))
/* 34 */     .defaultValueSupplier(() -> Float.valueOf(0.0F))
/* 35 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 36 */     .build();
/*    */   
/* 38 */   public static final SyncedDataKey<LivingEntity, Long> DRAW_COOL_DOWN_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.LONG)
/* 39 */     .id(new ResourceLocation("tacz", "draw_cool_down"))
/* 40 */     .defaultValueSupplier(() -> Long.valueOf(-1L))
/* 41 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 42 */     .build();
/*    */   
/* 44 */   public static final SyncedDataKey<LivingEntity, Boolean> IS_AIMING_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.BOOLEAN)
/* 45 */     .id(new ResourceLocation("tacz", "is_aiming"))
/* 46 */     .defaultValueSupplier(() -> Boolean.valueOf(false))
/* 47 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 48 */     .build();
/*    */   
/* 50 */   public static final SyncedDataKey<LivingEntity, Float> SPRINT_TIME_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.FLOAT)
/* 51 */     .id(new ResourceLocation("tacz", "sprint_time"))
/* 52 */     .defaultValueSupplier(() -> Float.valueOf(0.0F))
/* 53 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 54 */     .build();
/*    */   
/* 56 */   public static final SyncedDataKey<LivingEntity, Long> BOLT_COOL_DOWN_KEY = SyncedDataKey.builder(SyncedClassKey.LIVING_ENTITY, Serializers.LONG)
/* 57 */     .id(new ResourceLocation("tacz", "bolt_cool_down"))
/* 58 */     .defaultValueSupplier(() -> Long.valueOf(-1L))
/* 59 */     .syncMode(SyncedDataKey.SyncMode.ALL)
/* 60 */     .build();
/*    */   
/*    */   public static void init() {
/* 63 */     registerEntityData((SyncedDataKey)SHOOT_COOL_DOWN_KEY);
/* 64 */     registerEntityData((SyncedDataKey)MELEE_COOL_DOWN_KEY);
/* 65 */     registerEntityData((SyncedDataKey)RELOAD_STATE_KEY);
/* 66 */     registerEntityData((SyncedDataKey)AIMING_PROGRESS_KEY);
/* 67 */     registerEntityData((SyncedDataKey)DRAW_COOL_DOWN_KEY);
/* 68 */     registerEntityData((SyncedDataKey)IS_AIMING_KEY);
/* 69 */     registerEntityData((SyncedDataKey)SPRINT_TIME_KEY);
/* 70 */     registerEntityData((SyncedDataKey)BOLT_COOL_DOWN_KEY);
/*    */   }
/*    */   
/*    */   private static void registerEntityData(SyncedDataKey<? extends Entity, ?> dataKey) {
/* 74 */     SyncedEntityData.instance().registerDataKey(dataKey);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\ModSyncedEntityData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */