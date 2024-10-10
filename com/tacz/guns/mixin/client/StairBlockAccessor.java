package com.tacz.guns.mixin.client;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({StairBlock.class})
public interface StairBlockAccessor {
  @Invoker(remap = false)
  Block invokeGetModelBlock();
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\StairBlockAccessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */