/*     */ package com.tacz.guns.util;
/*     */ 
/*     */ import com.tacz.guns.config.util.HeadShotAABBConfigRead;
/*     */ import com.tacz.guns.entity.EntityKineticBullet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityUtil {
/*     */   static {
/*  19 */     PROJECTILE_TARGETS = (input -> (input != null && input.m_6087_() && !input.m_5833_()));
/*     */   } private static final Predicate<Entity> PROJECTILE_TARGETS;
/*     */   @Nullable
/*     */   public static EntityKineticBullet.EntityResult findEntityOnPath(Projectile bulletEntity, Vec3 startVec, Vec3 endVec) {
/*  23 */     Vec3 hitVec = null;
/*  24 */     Entity hitEntity = null;
/*  25 */     boolean headshot = false;
/*     */     
/*  27 */     List<Entity> entities = bulletEntity.m_9236_().m_6249_((Entity)bulletEntity, bulletEntity.m_20191_().m_82369_(bulletEntity.m_20184_()).m_82400_(1.0D), PROJECTILE_TARGETS);
/*  28 */     double closestDistance = Double.MAX_VALUE;
/*  29 */     Entity owner = bulletEntity.m_19749_();
/*  30 */     for (Entity entity : entities) {
/*     */       
/*  32 */       if (!entity.equals(owner)) {
/*     */         
/*  34 */         if (owner != null && entity.equals(owner.m_20202_())) {
/*     */           continue;
/*     */         }
/*  37 */         EntityKineticBullet.EntityResult result = getHitResult(bulletEntity, entity, startVec, endVec);
/*  38 */         if (result == null) {
/*     */           continue;
/*     */         }
/*  41 */         Vec3 hitPos = result.getHitPos();
/*  42 */         double distanceToHit = startVec.m_82554_(hitPos);
/*  43 */         if (entity.m_6084_() && 
/*  44 */           distanceToHit < closestDistance) {
/*  45 */           hitVec = hitPos;
/*  46 */           hitEntity = entity;
/*  47 */           closestDistance = distanceToHit;
/*  48 */           headshot = result.isHeadshot();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     return (hitEntity != null) ? new EntityKineticBullet.EntityResult(hitEntity, hitVec, headshot) : null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static List<EntityKineticBullet.EntityResult> findEntitiesOnPath(Projectile bulletEntity, Vec3 startVec, Vec3 endVec) {
/*  58 */     List<EntityKineticBullet.EntityResult> hitEntities = new ArrayList<>();
/*  59 */     List<Entity> entities = bulletEntity.m_9236_().m_6249_((Entity)bulletEntity, bulletEntity.m_20191_().m_82369_(bulletEntity.m_20184_()).m_82400_(1.0D), PROJECTILE_TARGETS);
/*  60 */     Entity owner = bulletEntity.m_19749_();
/*  61 */     for (Entity entity : entities) {
/*  62 */       if (entity.equals(owner) || (
/*  63 */         owner != null && entity.equals(owner.m_20202_()))) {
/*     */         continue;
/*     */       }
/*  66 */       EntityKineticBullet.EntityResult result = getHitResult(bulletEntity, entity, startVec, endVec);
/*  67 */       if (result == null) {
/*     */         continue;
/*     */       }
/*  70 */       if (entity.m_6084_()) {
/*  71 */         hitEntities.add(result);
/*     */       }
/*     */     } 
/*     */     
/*  75 */     return hitEntities;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected static EntityKineticBullet.EntityResult getHitResult(Projectile bulletEntity, Entity entity, Vec3 startVec, Vec3 endVec) {
/*  80 */     AABB boundingBox = HitboxHelper.getFixedBoundingBox(entity, bulletEntity.m_19749_());
/*     */     
/*  82 */     Vec3 hitPos = boundingBox.m_82371_(startVec, endVec).orElse(null);
/*     */     
/*  84 */     if (hitPos == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     Vec3 hitBoxPos = hitPos.m_82546_(entity.m_20182_());
/*  88 */     ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_());
/*     */     
/*  90 */     if (entityId != null) {
/*  91 */       AABB aabb = HeadShotAABBConfigRead.getAABB(entityId);
/*  92 */       if (aabb != null) {
/*  93 */         return new EntityKineticBullet.EntityResult(entity, hitPos, aabb.m_82390_(hitBoxPos));
/*     */       }
/*     */     } 
/*     */     
/*  97 */     boolean headshot = false;
/*  98 */     float eyeHeight = entity.m_20192_();
/*  99 */     if (eyeHeight - 0.25D < hitBoxPos.f_82480_ && hitBoxPos.f_82480_ < eyeHeight + 0.25D) {
/* 100 */       headshot = true;
/*     */     }
/* 102 */     return new EntityKineticBullet.EntityResult(entity, hitPos, headshot);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\EntityUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */