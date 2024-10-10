/*    */ package com.tacz.guns.compat.carryon;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.fml.InterModComms;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class BlackList
/*    */ {
/*    */   public static void addBlackList() {
/* 11 */     ForgeRegistries.BLOCKS.getKeys().stream().filter(id -> id.m_135827_().equals("tacz"))
/* 12 */       .forEach(id -> {
/*    */           Objects.requireNonNull(id);
/*    */           InterModComms.sendTo("carryon", "blacklistBlock", id::toString);
/*    */         });
/*    */   }
/*    */   
/*    */   private static final String CARRY_ON_ID = "carryon";
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\carryon\BlackList.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */