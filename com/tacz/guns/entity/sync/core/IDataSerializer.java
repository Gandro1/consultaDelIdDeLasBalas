package com.tacz.guns.entity.sync.core;

import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

public interface IDataSerializer<T> {
  void write(FriendlyByteBuf paramFriendlyByteBuf, T paramT);
  
  T read(FriendlyByteBuf paramFriendlyByteBuf);
  
  Tag write(T paramT);
  
  T read(Tag paramTag);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\IDataSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */