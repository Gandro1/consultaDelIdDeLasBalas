/*    */ package com.tacz.guns.api.client.event;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderLevelBobEvent
/*    */   extends Event
/*    */ {
/*    */   public boolean isCancelable() {
/* 15 */     return true;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class BobHurt extends RenderLevelBobEvent {}
/*    */   
/*    */   @Cancelable
/*    */   public static class BobView extends RenderLevelBobEvent {}
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\event\RenderLevelBobEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */