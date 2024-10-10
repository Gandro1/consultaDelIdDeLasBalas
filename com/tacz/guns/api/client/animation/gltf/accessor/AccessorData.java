package com.tacz.guns.api.client.animation.gltf.accessor;

import java.nio.ByteBuffer;

public interface AccessorData {
  Class<?> getComponentType();
  
  int getNumElements();
  
  int getNumComponentsPerElement();
  
  int getTotalNumComponents();
  
  ByteBuffer createByteBuffer();
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */