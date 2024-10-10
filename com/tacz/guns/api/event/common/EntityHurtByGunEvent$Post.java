/*     */ package com.tacz.guns.api.event.common;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.ApiStatus.Internal;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Post
/*     */   extends EntityHurtByGunEvent
/*     */ {
/*     */   @Internal
/*     */   public Post(Entity bullet, @Nullable Entity hurtEntity, @Nullable LivingEntity attacker, ResourceLocation gunId, float amount, @Nullable Pair<DamageSource, DamageSource> sources, boolean isHeadShot, float headshotMultiplier, LogicalSide logicalSide) {
/* 100 */     super(bullet, hurtEntity, attacker, gunId, amount, sources, isHeadShot, headshotMultiplier, logicalSide);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\event\common\EntityHurtByGunEvent$Post.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */