package com.tacz.guns.api.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;

public interface ITargetEntity {
  void onProjectileHit(Entity paramEntity, EntityHitResult paramEntityHitResult, DamageSource paramDamageSource, float paramFloat);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\entity\ITargetEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */