package com.tacz.guns.api.client.animation.interpolator;

import com.tacz.guns.api.client.animation.AnimationChannelContent;

public interface Interpolator extends Cloneable {
  void compile(AnimationChannelContent paramAnimationChannelContent);
  
  float[] interpolate(int paramInt1, int paramInt2, float paramFloat);
  
  Interpolator clone();
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\interpolator\Interpolator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */