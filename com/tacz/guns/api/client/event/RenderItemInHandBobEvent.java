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
/*    */ public class RenderItemInHandBobEvent
/*    */   extends Event
/*    */ {
/*    */   public boolean isCancelable() {
/* 15 */     return true;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class BobHurt extends RenderItemInHandBobEvent {}
/*    */   
/*    */   @Cancelable
/*    */   public static class BobView extends RenderItemInHandBobEvent {}
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\event\RenderItemInHandBobEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */