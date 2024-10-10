/*    */ package com.tacz.guns.command.sub;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ public class DebugCommand
/*    */ {
/*    */   public static boolean DEBUG = false;
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 19 */     LiteralArgumentBuilder<CommandSourceStack> debugCommand = Commands.m_82127_("debug");
/* 20 */     RequiredArgumentBuilder<CommandSourceStack, Boolean> enable = Commands.m_82129_("enable", (ArgumentType)BoolArgumentType.bool());
/* 21 */     debugCommand.then(enable.executes(DebugCommand::setValue));
/* 22 */     return debugCommand;
/*    */   }
/*    */   private static final String DEBUG_NAME = "debug"; private static final String ENABLE = "enable";
/*    */   private static int setValue(CommandContext<CommandSourceStack> context) {
/* 26 */     DEBUG = BoolArgumentType.getBool(context, "enable");
/* 27 */     Entity entity = ((CommandSourceStack)context.getSource()).m_81373_(); if (entity instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)entity;
/* 28 */       if (DEBUG) {
/* 29 */         serverPlayer.m_213846_((Component)Component.m_237113_("TacZ Debug Mode is Turn On"));
/*    */       } else {
/* 31 */         serverPlayer.m_213846_((Component)Component.m_237113_("TacZ Debug Mode is Turn Off"));
/*    */       }  }
/*    */     
/* 34 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\DebugCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */