/*    */ package com.tacz.guns.command.sub;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.commands.arguments.selector.EntitySelector;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class AttachmentLockCommand {
/*    */   private static final String ATTACHMENT_LOCK_NAME = "attachment_lock";
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 23 */     LiteralArgumentBuilder<CommandSourceStack> attachmentLock = Commands.m_82127_("attachment_lock");
/* 24 */     RequiredArgumentBuilder<CommandSourceStack, EntitySelector> entities = Commands.m_82129_("target", (ArgumentType)EntityArgument.m_91460_());
/* 25 */     RequiredArgumentBuilder<CommandSourceStack, Boolean> locked = Commands.m_82129_("AttachmentLock", (ArgumentType)BoolArgumentType.bool());
/* 26 */     attachmentLock.then(entities.then(locked.executes(AttachmentLockCommand::setAttachmentLock)));
/* 27 */     return attachmentLock;
/*    */   }
/*    */   private static final String ENTITY = "target"; private static final String GUN_ATTACHMENT_LOCK = "AttachmentLock";
/*    */   private static int setAttachmentLock(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 31 */     Collection<? extends Entity> entities = EntityArgument.m_91461_(context, "target");
/* 32 */     int cnt = 0;
/* 33 */     boolean locked = BoolArgumentType.getBool(context, "AttachmentLock");
/* 34 */     for (Entity entity : entities) {
/* 35 */       if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/* 36 */         ItemStack stack = living.m_21205_();
/* 37 */         Item item = stack.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 38 */           iGun.setAttachmentLock(stack, locked);
/* 39 */           cnt++; }
/*    */          }
/*    */     
/*    */     } 
/* 43 */     return cnt;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\AttachmentLockCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */