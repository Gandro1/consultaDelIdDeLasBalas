package com.tacz.guns.network;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public interface IMessage<T> {
  void encode(T paramT, FriendlyByteBuf paramFriendlyByteBuf);
  
  T decode(FriendlyByteBuf paramFriendlyByteBuf);
  
  void handle(T paramT, Supplier<NetworkEvent.Context> paramSupplier);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\network\IMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */