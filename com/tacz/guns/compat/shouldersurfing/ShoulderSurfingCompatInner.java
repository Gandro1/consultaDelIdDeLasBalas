/*   */ package com.tacz.guns.compat.shouldersurfing;
/*   */ 
/*   */ import com.github.exopandora.shouldersurfing.api.model.Perspective;
/*   */ import com.github.exopandora.shouldersurfing.client.InputHandler;
/*   */ 
/*   */ public class ShoulderSurfingCompatInner {
/*   */   public static boolean showCrosshair() {
/* 8 */     Perspective current = Perspective.current();
/* 9 */     return (current == Perspective.SHOULDER_SURFING && !InputHandler.FREE_LOOK.m_90857_());
/*   */   }
/*   */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\shouldersurfing\ShoulderSurfingCompatInner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */