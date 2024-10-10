/*     */ package com.tacz.guns.compat.controllable;
/*     */ 
/*     */ import com.mrcrayfish.controllable.client.binding.IBindingContext;
/*     */ import com.tacz.guns.api.item.IGun;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraftforge.client.settings.KeyConflictContext;
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
/*     */ public class GunKeyConflict
/*     */   implements IBindingContext
/*     */ {
/*     */   public boolean isActive() {
/* 127 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 128 */     return (!KeyConflictContext.GUI.isActive() && player != null && IGun.mainhandHoldGun((LivingEntity)player));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean conflicts(IBindingContext other) {
/* 133 */     return (this == other);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\controllable\ControllableInner$GunKeyConflict.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */