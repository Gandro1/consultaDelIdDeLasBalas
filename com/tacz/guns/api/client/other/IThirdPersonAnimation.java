package com.tacz.guns.api.client.other;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public interface IThirdPersonAnimation {
  void animateGunHold(LivingEntity paramLivingEntity, ModelPart paramModelPart1, ModelPart paramModelPart2, ModelPart paramModelPart3, ModelPart paramModelPart4);
  
  void animateGunAim(LivingEntity paramLivingEntity, ModelPart paramModelPart1, ModelPart paramModelPart2, ModelPart paramModelPart3, ModelPart paramModelPart4, float paramFloat);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\other\IThirdPersonAnimation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */