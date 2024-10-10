/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import com.tacz.guns.command.RootCommand;
/*    */ import net.minecraftforge.event.RegisterCommandsEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public final class CommandRegistry {
/*    */   @SubscribeEvent
/*    */   public static void onServerStaring(RegisterCommandsEvent event) {
/* 12 */     RootCommand.register(event.getDispatcher());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\CommandRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */