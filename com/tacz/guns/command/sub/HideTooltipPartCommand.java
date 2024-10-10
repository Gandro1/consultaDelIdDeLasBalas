/*    */ package com.tacz.guns.command.sub;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.tacz.guns.item.GunTooltipPart;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.commands.arguments.selector.EntitySelector;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class HideTooltipPartCommand
/*    */ {
/*    */   private static final String HIDE_TOOLTIP_PART_NAME = "hide_tooltip_part";
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 24 */     LiteralArgumentBuilder<CommandSourceStack> base = Commands.m_82127_("hide_tooltip_part");
/* 25 */     RequiredArgumentBuilder<CommandSourceStack, EntitySelector> entities = Commands.m_82129_("target", (ArgumentType)EntityArgument.m_91460_());
/* 26 */     RequiredArgumentBuilder<CommandSourceStack, Integer> part = Commands.m_82129_("mask", (ArgumentType)IntegerArgumentType.integer(0));
/* 27 */     base.then(entities.then(part.executes(HideTooltipPartCommand::setHide)));
/* 28 */     return base;
/*    */   }
/*    */   private static final String ENTITY = "target"; private static final String MASK = "mask";
/*    */   private static int setHide(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 32 */     Collection<? extends Entity> entities = EntityArgument.m_91461_(context, "target");
/* 33 */     int cnt = 0;
/* 34 */     int mask = IntegerArgumentType.getInteger(context, "mask");
/* 35 */     for (Entity entity : entities) {
/* 36 */       if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity;
/* 37 */         ItemStack stack = living.m_21205_();
/* 38 */         if (stack.m_41720_() instanceof com.tacz.guns.api.item.IGun) {
/* 39 */           GunTooltipPart.setHideFlags(stack, mask);
/* 40 */           cnt++;
/*    */         }  }
/*    */     
/*    */     } 
/* 44 */     return cnt;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\HideTooltipPartCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */