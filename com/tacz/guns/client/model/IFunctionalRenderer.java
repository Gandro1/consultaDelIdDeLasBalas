package com.tacz.guns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.item.ItemDisplayContext;

public interface IFunctionalRenderer {
  void render(PoseStack paramPoseStack, VertexConsumer paramVertexConsumer, ItemDisplayContext paramItemDisplayContext, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\model\IFunctionalRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */