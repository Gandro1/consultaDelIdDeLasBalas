/*    */ package com.tacz.guns.command.sub;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
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
/*    */ public class DummyAmmoCommand {
/*    */   private static final String DUMMY_NAME = "dummy";
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 23 */     LiteralArgumentBuilder<CommandSourceStack> dummy = Commands.m_82127_("dummy");
/* 24 */     RequiredArgumentBuilder<CommandSourceStack, EntitySelector> entities = Commands.m_82129_("target", (ArgumentType)EntityArgument.m_91460_());
/* 25 */     RequiredArgumentBuilder<CommandSourceStack, Integer> amount = Commands.m_82129_("dummyAmount", (ArgumentType)IntegerArgumentType.integer(0));
/* 26 */     dummy.then(entities.then(amount.executes(DummyAmmoCommand::setDummy)));
/* 27 */     return dummy;
/*    */   }
/*    */   private static final String ENTITY = "target"; private static final String AMOUNT = "dummyAmount";
/*    */   private static int setDummy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 31 */     Collection<? extends Entity> entities = EntityArgument.m_91461_(context, "target");
/* 32 */     int cnt = 0;
/* 33 */     int amount = IntegerArgumentType.getInteger(context, "dummyAmount");
/* 34 */     for (Entity entity : entities) {
/* 35 */       if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/* 36 */         ItemStack stack = living.m_21205_();
/* 37 */         Item item = stack.m_41720_(); if (item instanceof IGun) { IGun iGun = (IGun)item;
/* 38 */           iGun.setDummyAmmoAmount(stack, amount);
/* 39 */           cnt++; }
/*    */          }
/*    */     
/*    */     } 
/* 43 */     return cnt;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\DummyAmmoCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */