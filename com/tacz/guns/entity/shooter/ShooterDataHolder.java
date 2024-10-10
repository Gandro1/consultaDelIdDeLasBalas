/*     */ package com.tacz.guns.entity.shooter;
/*     */ 
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShooterDataHolder
/*     */ {
/*  16 */   public long shootTimestamp = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   public long meleeTimestamp = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public int meleePrepTickCount = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public long drawTimestamp = -1L;
/*     */ 
/*     */ 
/*     */   
/*  37 */   public long boltTimestamp = -1L;
/*  38 */   public long boltCoolDown = -1L;
/*     */ 
/*     */ 
/*     */   
/*  42 */   public float aimingProgress = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public long aimingTimestamp = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAiming = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public long reloadTimestamp = -1L;
/*     */ 
/*     */   
/*     */   @Nonnull
/*  61 */   public ReloadState.StateType reloadStateType = ReloadState.StateType.NOT_RELOADING;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*  66 */   public Supplier<ItemStack> currentGunItem = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public float currentPutAwayTimeS = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*  76 */   public float sprintTimeS = 0.0F;
/*  77 */   public long sprintTimestamp = -1L;
/*     */ 
/*     */ 
/*     */   
/*  81 */   public double knockbackStrength = -1.0D;
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int shootCount = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCrawling = false;
/*     */ 
/*     */   
/*     */   @Nullable
/*  93 */   public AttachmentCacheProperty cacheProperty = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialData() {
/*  98 */     this.shootTimestamp = -1L;
/*  99 */     this.meleeTimestamp = -1L;
/* 100 */     this.meleePrepTickCount = -1;
/* 101 */     this.isAiming = false;
/* 102 */     this.aimingProgress = 0.0F;
/* 103 */     this.reloadTimestamp = -1L;
/* 104 */     this.reloadStateType = ReloadState.StateType.NOT_RELOADING;
/* 105 */     this.sprintTimestamp = -1L;
/* 106 */     this.sprintTimeS = 0.0F;
/* 107 */     this.boltTimestamp = -1L;
/* 108 */     this.boltCoolDown = -1L;
/* 109 */     this.shootCount = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\ShooterDataHolder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */