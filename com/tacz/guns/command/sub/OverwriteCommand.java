/*    */ package com.tacz.guns.command.sub;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.tacz.guns.config.common.OtherConfig;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ 
/*    */ public class OverwriteCommand {
/*    */   private static final String OVERWRITE_NAME = "overwrite";
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 19 */     LiteralArgumentBuilder<CommandSourceStack> reload = Commands.m_82127_("overwrite");
/* 20 */     RequiredArgumentBuilder<CommandSourceStack, Boolean> enable = Commands.m_82129_("enable", (ArgumentType)BoolArgumentType.bool());
/* 21 */     reload.then(enable.executes(OverwriteCommand::setOverwrite));
/* 22 */     return reload;
/*    */   }
/*    */   private static final String ENABLE = "enable";
/*    */   private static int setOverwrite(CommandContext<CommandSourceStack> context) {
/* 26 */     boolean enable = BoolArgumentType.getBool(context, "enable");
/* 27 */     OtherConfig.DEFAULT_PACK_DEBUG.set(Boolean.valueOf(!enable));
/* 28 */     Entity entity = ((CommandSourceStack)context.getSource()).m_81373_(); if (entity instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)entity;
/* 29 */       if (((Boolean)OtherConfig.DEFAULT_PACK_DEBUG.get()).booleanValue()) {
/* 30 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_off"));
/*    */       } else {
/* 32 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_on"));
/*    */       }  }
/*    */     
/* 35 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\OverwriteCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */