/*     */ package com.tacz.guns.util.block;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.tacz.guns.config.common.AmmoConfig;
/*     */ import com.tacz.guns.util.HitboxHelper;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.enchantment.ProtectionEnchantment;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.ExplosionDamageCalculator;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.gameevent.GameEvent;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ 
/*     */ public class ProjectileExplosion extends Explosion {
/*  31 */   private static final ExplosionDamageCalculator DEFAULT_CONTEXT = new ExplosionDamageCalculator();
/*     */   private final Level level;
/*     */   private final double x;
/*     */   private final double y;
/*     */   private final double z;
/*     */   private final float power;
/*     */   private final float radius;
/*     */   private final boolean knockback;
/*     */   private final Entity owner;
/*     */   private final Entity exploder;
/*     */   private final ExplosionDamageCalculator damageCalculator;
/*     */   
/*     */   public ProjectileExplosion(Level level, Entity owner, Entity exploder, @Nullable DamageSource source, @Nullable ExplosionDamageCalculator damageCalculator, double x, double y, double z, float power, float radius, boolean knockback, Explosion.BlockInteraction mode) {
/*  44 */     super(level, exploder, source, damageCalculator, x, y, z, radius, ((Boolean)AmmoConfig.EXPLOSIVE_AMMO_FIRE.get()).booleanValue(), mode);
/*  45 */     this.level = level;
/*  46 */     this.x = x;
/*  47 */     this.y = y;
/*  48 */     this.z = z;
/*  49 */     this.power = power;
/*  50 */     this.radius = radius;
/*  51 */     this.owner = owner;
/*  52 */     this.exploder = exploder;
/*  53 */     this.damageCalculator = (damageCalculator == null) ? DEFAULT_CONTEXT : damageCalculator;
/*  54 */     this.knockback = knockback;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_46061_() {
/*  59 */     this.level.m_142346_(this.exploder, GameEvent.f_157812_, BlockPos.m_274561_(this.x, this.y, this.z));
/*  60 */     Set<BlockPos> set = Sets.newHashSet();
/*  61 */     int i = 16;
/*     */     
/*  63 */     for (int x = 0; x < i; x++) {
/*  64 */       for (int y = 0; y < i; y++) {
/*  65 */         for (int z = 0; z < i; z++) {
/*  66 */           if (x == 0 || x == i - 1 || y == 0 || y == i - 1 || z == 0 || z == i - 1) {
/*  67 */             double d0 = (x / (i - 1) * 2.0F - 1.0F);
/*  68 */             double d1 = (y / (i - 1) * 2.0F - 1.0F);
/*  69 */             double d2 = (z / (i - 1) * 2.0F - 1.0F);
/*  70 */             double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*  71 */             d0 /= d3;
/*  72 */             d1 /= d3;
/*  73 */             d2 /= d3;
/*  74 */             float f = this.radius * (0.7F + this.level.f_46441_.m_188501_() * 0.6F);
/*  75 */             double blockX = this.x;
/*  76 */             double blockY = this.y;
/*  77 */             double blockZ = this.z;
/*     */             
/*  79 */             for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
/*  80 */               BlockPos pos = BlockPos.m_274561_(blockX, blockY, blockZ);
/*  81 */               BlockState blockState = this.level.m_8055_(pos);
/*  82 */               FluidState fluidState = this.level.m_6425_(pos);
/*  83 */               if (!this.level.m_46739_(pos)) {
/*     */                 break;
/*     */               }
/*     */               
/*  87 */               Optional<Float> optional = this.damageCalculator.m_6617_(this, (BlockGetter)this.level, pos, blockState, fluidState);
/*  88 */               if (optional.isPresent()) {
/*  89 */                 f -= (((Float)optional.get()).floatValue() + f1) * f1;
/*     */               }
/*     */               
/*  92 */               if (f > 0.0F && this.damageCalculator.m_6714_(this, (BlockGetter)this.level, pos, blockState, f)) {
/*  93 */                 set.add(pos);
/*     */               }
/*     */               
/*  96 */               blockX += d0 * f1;
/*  97 */               blockY += d1 * f1;
/*  98 */               blockZ += d2 * f1;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     m_46081_().addAll(set);
/* 106 */     float radius = this.radius;
/* 107 */     int minX = Mth.m_14107_(this.x - radius - 1.0D);
/* 108 */     int maxX = Mth.m_14107_(this.x + radius + 1.0D);
/* 109 */     int minY = Mth.m_14107_(this.y - radius - 1.0D);
/* 110 */     int maxY = Mth.m_14107_(this.y + radius + 1.0D);
/* 111 */     int minZ = Mth.m_14107_(this.z - radius - 1.0D);
/* 112 */     int maxZ = Mth.m_14107_(this.z + radius + 1.0D);
/* 113 */     radius *= 2.0F;
/* 114 */     List<Entity> entities = this.level.m_45933_(this.exploder, new AABB(minX, minY, minZ, maxX, maxY, maxZ));
/* 115 */     ForgeEventFactory.onExplosionDetonate(this.level, this, entities, radius);
/* 116 */     Vec3 explosionPos = new Vec3(this.x, this.y, this.z);
/*     */     
/* 118 */     for (Entity entity : entities) {
/* 119 */       double strength, deltaX, deltaY, deltaZ; if (entity.m_6128_()) {
/*     */         continue;
/*     */       }
/*     */       
/* 123 */       AABB boundingBox = HitboxHelper.getFixedBoundingBox(entity, this.owner);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       double minDistance = radius;
/*     */       
/* 131 */       Vec3[] d = new Vec3[15];
/*     */       
/* 133 */       if (!(entity instanceof LivingEntity)) {
/* 134 */         strength = Math.sqrt(entity.m_20238_(explosionPos)) * 2.0D / radius;
/* 135 */         deltaX = entity.m_20185_() - this.x;
/* 136 */         deltaY = ((entity instanceof net.minecraft.world.entity.item.PrimedTnt) ? entity.m_20186_() : entity.m_20188_()) - this.y;
/* 137 */         deltaZ = entity.m_20189_() - this.z;
/*     */       } else {
/* 139 */         deltaX = (boundingBox.f_82291_ + boundingBox.f_82288_) / 2.0D;
/* 140 */         deltaY = (boundingBox.f_82292_ + boundingBox.f_82289_) / 2.0D;
/* 141 */         deltaZ = (boundingBox.f_82293_ + boundingBox.f_82290_) / 2.0D;
/* 142 */         d[0] = new Vec3(boundingBox.f_82288_, boundingBox.f_82289_, boundingBox.f_82290_);
/* 143 */         d[1] = new Vec3(boundingBox.f_82288_, boundingBox.f_82289_, boundingBox.f_82293_);
/* 144 */         d[2] = new Vec3(boundingBox.f_82288_, boundingBox.f_82292_, boundingBox.f_82290_);
/* 145 */         d[3] = new Vec3(boundingBox.f_82291_, boundingBox.f_82289_, boundingBox.f_82290_);
/* 146 */         d[4] = new Vec3(boundingBox.f_82288_, boundingBox.f_82292_, boundingBox.f_82293_);
/* 147 */         d[5] = new Vec3(boundingBox.f_82291_, boundingBox.f_82289_, boundingBox.f_82293_);
/* 148 */         d[6] = new Vec3(boundingBox.f_82291_, boundingBox.f_82292_, boundingBox.f_82290_);
/* 149 */         d[7] = new Vec3(boundingBox.f_82291_, boundingBox.f_82292_, boundingBox.f_82293_);
/* 150 */         d[8] = new Vec3(boundingBox.f_82288_, deltaY, deltaZ);
/* 151 */         d[9] = new Vec3(boundingBox.f_82291_, deltaY, deltaZ);
/* 152 */         d[10] = new Vec3(deltaX, boundingBox.f_82289_, deltaZ);
/* 153 */         d[11] = new Vec3(deltaX, boundingBox.f_82292_, deltaZ);
/* 154 */         d[12] = new Vec3(deltaX, deltaY, boundingBox.f_82290_);
/* 155 */         d[13] = new Vec3(deltaX, deltaY, boundingBox.f_82293_);
/* 156 */         d[14] = new Vec3(deltaX, deltaY, deltaZ);
/* 157 */         for (int s = 0; s < 15; s++) {
/* 158 */           BlockHitResult result = BlockRayTrace.rayTraceBlocks(this.level, new ClipContext(explosionPos, d[s], ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 159 */           minDistance = (result.m_6662_() != HitResult.Type.BLOCK) ? Math.min(minDistance, explosionPos.m_82554_(d[s])) : minDistance;
/*     */         } 
/* 161 */         strength = minDistance * 2.0D / radius;
/* 162 */         deltaX -= this.x;
/* 163 */         deltaY -= this.y;
/* 164 */         deltaZ -= this.z;
/*     */       } 
/*     */       
/* 167 */       if (strength > 1.0D) {
/*     */         continue;
/*     */       }
/*     */       
/* 171 */       double distanceToExplosion = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
/*     */       
/* 173 */       if (distanceToExplosion != 0.0D) {
/* 174 */         deltaX /= distanceToExplosion;
/* 175 */         deltaY /= distanceToExplosion;
/* 176 */         deltaZ /= distanceToExplosion;
/*     */       } 
/*     */       
/* 179 */       double damage = 1.0D - strength;
/* 180 */       entity.m_6469_(m_46077_(), (float)damage * this.power);
/*     */       
/* 182 */       if (entity instanceof LivingEntity) {
/* 183 */         damage = (float)ProtectionEnchantment.m_45135_((LivingEntity)entity, damage);
/*     */       }
/*     */       
/* 186 */       float multiplier = this.power * radius / 500.0F;
/*     */       
/* 188 */       if (((Boolean)AmmoConfig.EXPLOSIVE_AMMO_KNOCK_BACK.get()).booleanValue() && this.knockback) {
/* 189 */         entity.m_20256_(entity.m_20184_().m_82520_(deltaX * damage * multiplier, deltaY * damage * multiplier, deltaZ * damage * multiplier));
/* 190 */         if (entity instanceof Player) { Player player = (Player)entity;
/* 191 */           if (!player.m_5833_() && (!player.m_7500_() || !(player.m_150110_()).f_35935_))
/* 192 */             m_46078_().put(player, new Vec3(deltaX * damage * multiplier, deltaY * damage * multiplier, deltaZ * damage * multiplier));  }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\block\ProjectileExplosion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */