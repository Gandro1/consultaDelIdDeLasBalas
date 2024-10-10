/*    */ package com.tacz.guns.resource;
/*    */ 
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.tacz.guns.resource.network.CommonGunPackNetwork;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ 
/*    */ public class DedicatedServerReloadManager
/*    */ {
/*    */   public static void loadGunPack() {
/* 10 */     VersionChecker.clearCache();
/* 11 */     CommonGunPackLoader.init();
/* 12 */     CommonGunPackLoader.reloadAsset();
/* 13 */     CommonGunPackLoader.reloadIndex();
/* 14 */     CommonGunPackLoader.reloadRecipes();
/*    */   }
/*    */   
/*    */   public static void reloadFromCommand(CommandContext<CommandSourceStack> context) {
/* 18 */     loadGunPack();
/* 19 */     CommonGunPackNetwork.syncClient(((CommandSourceStack)context.getSource()).m_81372_().m_7654_());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\DedicatedServerReloadManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */