/*    */ package com.tacz.guns.command;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.tacz.guns.command.sub.DummyAmmoCommand;
/*    */ import com.tacz.guns.command.sub.HideTooltipPartCommand;
/*    */ import com.tacz.guns.command.sub.OverwriteCommand;
/*    */ import com.tacz.guns.command.sub.ReloadCommand;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ 
/*    */ public class RootCommand {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 14 */     LiteralArgumentBuilder<CommandSourceStack> root = (LiteralArgumentBuilder<CommandSourceStack>)Commands.m_82127_("tacz").requires(source -> source.m_6761_(2));
/* 15 */     root.then((ArgumentBuilder)AttachmentLockCommand.get());
/* 16 */     root.then((ArgumentBuilder)DebugCommand.get());
/* 17 */     root.then((ArgumentBuilder)DummyAmmoCommand.get());
/* 18 */     root.then((ArgumentBuilder)OverwriteCommand.get());
/* 19 */     root.then((ArgumentBuilder)ReloadCommand.get());
/* 20 */     root.then((ArgumentBuilder)HideTooltipPartCommand.get());
/* 21 */     dispatcher.register(root);
/*    */   }
/*    */   
/*    */   private static final String ROOT_NAME = "tacz";
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\RootCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */