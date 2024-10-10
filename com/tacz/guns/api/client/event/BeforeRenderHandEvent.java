/*    */ package com.tacz.guns.api.client.event;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeforeRenderHandEvent
/*    */   extends Event
/*    */ {
/*    */   private final PoseStack poseStack;
/*    */   
/*    */   public BeforeRenderHandEvent(PoseStack poseStack) {
/* 14 */     this.poseStack = poseStack;
/*    */   }
/*    */   
/*    */   public PoseStack getPoseStack() {
/* 18 */     return this.poseStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\event\BeforeRenderHandEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */