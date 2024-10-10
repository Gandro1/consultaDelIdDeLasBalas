/*    */ package com.tacz.guns.command.sub;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.tacz.guns.GunMod;
/*    */ import com.tacz.guns.client.resource.ClientReloadManager;
/*    */ import com.tacz.guns.config.common.OtherConfig;
/*    */ import com.tacz.guns.resource.DedicatedServerReloadManager;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import org.apache.commons.lang3.time.StopWatch;
/*    */ 
/*    */ public class ReloadCommand
/*    */ {
/*    */   private static final String RELOAD_NAME = "reload";
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> get() {
/* 24 */     LiteralArgumentBuilder<CommandSourceStack> reload = Commands.m_82127_("reload");
/* 25 */     reload.executes(ReloadCommand::reloadAllPack);
/* 26 */     return reload;
/*    */   }
/*    */   
/*    */   private static int reloadAllPack(CommandContext<CommandSourceStack> context) {
/* 30 */     StopWatch watch = StopWatch.createStarted();
/*    */     
/* 32 */     DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientReloadManager::reloadAllPack);
/* 33 */     DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> ());
/*    */     
/* 35 */     watch.stop();
/* 36 */     double time = watch.getTime(TimeUnit.MICROSECONDS) / 1000.0D;
/* 37 */     Entity entity = ((CommandSourceStack)context.getSource()).m_81373_(); if (entity instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)entity;
/* 38 */       serverPlayer.m_213846_((Component)Component.m_237110_("commands.tacz.reload.success", new Object[] { Double.valueOf(time) }));
/* 39 */       if (((Boolean)OtherConfig.DEFAULT_PACK_DEBUG.get()).booleanValue()) {
/* 40 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_off"));
/* 41 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_command.off"));
/*    */       } else {
/* 43 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_on"));
/* 44 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.overwrite_command.on"));
/* 45 */         serverPlayer.m_213846_((Component)Component.m_237115_("commands.tacz.reload.backup"));
/*    */       }  }
/*    */     
/* 48 */     GunMod.LOGGER.info("Model loading time: {} ms", Double.valueOf(time));
/* 49 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\command\sub\ReloadCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */