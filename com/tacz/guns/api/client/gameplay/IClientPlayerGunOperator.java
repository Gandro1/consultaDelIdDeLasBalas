/*    */ package com.tacz.guns.api.client.gameplay;
/*    */ 
/*    */ import com.tacz.guns.api.entity.ShootResult;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public interface IClientPlayerGunOperator
/*    */ {
/*    */   static IClientPlayerGunOperator fromLocalPlayer(LocalPlayer player) {
/* 19 */     return (IClientPlayerGunOperator)player;
/*    */   }
/*    */   
/*    */   ShootResult shoot();
/*    */   
/*    */   void draw(ItemStack paramItemStack);
/*    */   
/*    */   void bolt();
/*    */   
/*    */   void reload();
/*    */   
/*    */   void inspect();
/*    */   
/*    */   void fireSelect();
/*    */   
/*    */   void aim(boolean paramBoolean);
/*    */   
/*    */   void crawl(boolean paramBoolean);
/*    */   
/*    */   void melee();
/*    */   
/*    */   boolean isAim();
/*    */   
/*    */   boolean isCrawl();
/*    */   
/*    */   float getClientAimingProgress(float paramFloat);
/*    */   
/*    */   long getClientShootCoolDown();
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\gameplay\IClientPlayerGunOperator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */