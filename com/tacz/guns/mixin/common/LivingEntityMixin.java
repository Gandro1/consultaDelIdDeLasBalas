/*     */ package com.tacz.guns.mixin.common;
/*     */ import com.tacz.guns.api.entity.KnockBackModifier;
/*     */ import com.tacz.guns.api.entity.ReloadState;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityAim;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityAmmoCheck;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityBolt;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityCrawl;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityDrawGun;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityFireSelect;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityMelee;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityReload;
/*     */ import com.tacz.guns.entity.shooter.LivingEntityShoot;
/*     */ import com.tacz.guns.entity.shooter.LivingEntitySpeedModifier;
/*     */ import com.tacz.guns.entity.shooter.ShooterDataHolder;
/*     */ import com.tacz.guns.entity.sync.ModSyncedEntityData;
/*     */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ 
/*     */ @Mixin({LivingEntity.class})
/*     */ public abstract class LivingEntityMixin extends Entity implements IGunOperator, KnockBackModifier {
/*     */   @Unique
/*  28 */   private final LivingEntity tacz$shooter = (LivingEntity)this; @Unique
/*  29 */   private final ShooterDataHolder tacz$data = new ShooterDataHolder(); @Unique
/*  30 */   private final LivingEntityDrawGun tacz$draw = new LivingEntityDrawGun(this.tacz$shooter, this.tacz$data); @Unique
/*  31 */   private final LivingEntityAim tacz$aim = new LivingEntityAim(this.tacz$shooter, this.tacz$data); @Unique
/*  32 */   private final LivingEntityCrawl tacz$crawl = new LivingEntityCrawl(this.tacz$shooter, this.tacz$data); @Unique
/*  33 */   private final LivingEntityAmmoCheck tacz$ammoCheck = new LivingEntityAmmoCheck(this.tacz$shooter); @Unique
/*  34 */   private final LivingEntityFireSelect tacz$fireSelect = new LivingEntityFireSelect(this.tacz$shooter, this.tacz$data); @Unique
/*  35 */   private final LivingEntityMelee tacz$melee = new LivingEntityMelee(this.tacz$shooter, this.tacz$data, this.tacz$draw); @Unique
/*  36 */   private final LivingEntityShoot tacz$shoot = new LivingEntityShoot(this.tacz$shooter, this.tacz$data, this.tacz$draw); @Unique
/*  37 */   private final LivingEntityBolt tacz$bolt = new LivingEntityBolt(this.tacz$data, this.tacz$draw, this.tacz$shoot); @Unique
/*  38 */   private final LivingEntityReload tacz$reload = new LivingEntityReload(this.tacz$shooter, this.tacz$data, this.tacz$draw, this.tacz$shoot); @Unique
/*  39 */   private final LivingEntitySpeedModifier tacz$speed = new LivingEntitySpeedModifier(this.tacz$shooter, this.tacz$data);
/*     */ 
/*     */   
/*     */   public LivingEntityMixin(EntityType<?> entityType, Level level) {
/*  43 */     super(entityType, level);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public long getSynShootCoolDown() {
/*  49 */     return ((Long)ModSyncedEntityData.SHOOT_COOL_DOWN_KEY.getValue((Entity)this.tacz$shooter)).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSynMeleeCoolDown() {
/*  54 */     return ((Long)ModSyncedEntityData.MELEE_COOL_DOWN_KEY.getValue((Entity)this.tacz$shooter)).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public long getSynDrawCoolDown() {
/*  60 */     return ((Long)ModSyncedEntityData.DRAW_COOL_DOWN_KEY.getValue((Entity)this.tacz$shooter)).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public long getSynBoltCoolDown() {
/*  66 */     return ((Long)ModSyncedEntityData.BOLT_COOL_DOWN_KEY.getValue((Entity)this.tacz$shooter)).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public ReloadState getSynReloadState() {
/*  72 */     return (ReloadState)ModSyncedEntityData.RELOAD_STATE_KEY.getValue((Entity)this.tacz$shooter);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public float getSynAimingProgress() {
/*  78 */     return ((Float)ModSyncedEntityData.AIMING_PROGRESS_KEY.getValue((Entity)this.tacz$shooter)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public float getSynSprintTime() {
/*  84 */     return ((Float)ModSyncedEntityData.SPRINT_TIME_KEY.getValue((Entity)this.tacz$shooter)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public boolean getSynIsAiming() {
/*  90 */     return ((Boolean)ModSyncedEntityData.IS_AIMING_KEY.getValue((Entity)this.tacz$shooter)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void initialData() {
/*  96 */     this.tacz$data.initialData();
/*     */     
/*  98 */     AttachmentPropertyManager.postChangeEvent(this.tacz$shooter, this.tacz$shooter.m_21205_());
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void draw(Supplier<ItemStack> gunItemSupplier) {
/* 104 */     this.tacz$draw.draw(gunItemSupplier);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void bolt() {
/* 110 */     this.tacz$bolt.bolt();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void reload() {
/* 116 */     this.tacz$reload.reload();
/*     */   }
/*     */ 
/*     */   
/*     */   public void melee() {
/* 121 */     this.tacz$melee.melee();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public ShootResult shoot(Supplier<Float> pitch, Supplier<Float> yaw) {
/* 127 */     return this.tacz$shoot.shoot(pitch, yaw);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public boolean needCheckAmmo() {
/* 133 */     return this.tacz$ammoCheck.needCheckAmmo();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public boolean consumesAmmoOrNot() {
/* 139 */     return this.tacz$ammoCheck.consumesAmmoOrNot();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void aim(boolean isAim) {
/* 145 */     this.tacz$aim.aim(isAim);
/*     */   }
/*     */ 
/*     */   
/*     */   public void crawl(boolean isCrawl) {
/* 150 */     this.tacz$crawl.crawl(isCrawl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCacheProperty(AttachmentCacheProperty cacheProperty) {
/* 155 */     this.tacz$data.cacheProperty = cacheProperty;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AttachmentCacheProperty getCacheProperty() {
/* 160 */     return this.tacz$data.cacheProperty;
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void fireSelect() {
/* 166 */     this.tacz$fireSelect.fireSelect();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void zoom() {
/* 172 */     this.tacz$aim.zoom();
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"tick"}, at = {@At("RETURN")})
/*     */   private void onTickServerSide(CallbackInfo ci) {
/* 178 */     if (!m_9236_().m_5776_()) {
/*     */       
/* 180 */       ReloadState reloadState = this.tacz$reload.tickReloadState();
/* 181 */       this.tacz$aim.tickAimingProgress();
/* 182 */       this.tacz$aim.tickSprint();
/* 183 */       this.tacz$crawl.tickCrawling();
/* 184 */       this.tacz$bolt.tickBolt();
/* 185 */       this.tacz$melee.scheduleTickMelee();
/* 186 */       this.tacz$speed.updateSpeedModifier();
/*     */       
/* 188 */       ModSyncedEntityData.SHOOT_COOL_DOWN_KEY.setValue((Entity)this.tacz$shooter, Long.valueOf(this.tacz$shoot.getShootCoolDown()));
/* 189 */       ModSyncedEntityData.MELEE_COOL_DOWN_KEY.setValue((Entity)this.tacz$shooter, Long.valueOf(this.tacz$melee.getMeleeCoolDown()));
/* 190 */       ModSyncedEntityData.DRAW_COOL_DOWN_KEY.setValue((Entity)this.tacz$shooter, Long.valueOf(this.tacz$draw.getDrawCoolDown()));
/* 191 */       ModSyncedEntityData.BOLT_COOL_DOWN_KEY.setValue((Entity)this.tacz$shooter, Long.valueOf(this.tacz$data.boltCoolDown));
/* 192 */       ModSyncedEntityData.RELOAD_STATE_KEY.setValue((Entity)this.tacz$shooter, reloadState);
/* 193 */       ModSyncedEntityData.AIMING_PROGRESS_KEY.setValue((Entity)this.tacz$shooter, Float.valueOf(this.tacz$data.aimingProgress));
/* 194 */       ModSyncedEntityData.IS_AIMING_KEY.setValue((Entity)this.tacz$shooter, Boolean.valueOf(this.tacz$data.isAiming));
/* 195 */       ModSyncedEntityData.SPRINT_TIME_KEY.setValue((Entity)this.tacz$shooter, Float.valueOf(this.tacz$data.sprintTimeS));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void resetKnockBackStrength() {
/* 202 */     this.tacz$data.knockbackStrength = -1.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public double getKnockBackStrength() {
/* 208 */     return this.tacz$data.knockbackStrength;
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void setKnockBackStrength(double strength) {
/* 214 */     this.tacz$data.knockbackStrength = strength;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\common\LivingEntityMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */