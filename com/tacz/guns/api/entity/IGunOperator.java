/*    */ package com.tacz.guns.api.entity;
/*    */ 
/*    */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IGunOperator
/*    */ {
/*    */   static IGunOperator fromLivingEntity(LivingEntity entity) {
/* 15 */     return (IGunOperator)entity;
/*    */   }
/*    */   
/*    */   long getSynShootCoolDown();
/*    */   
/*    */   long getSynMeleeCoolDown();
/*    */   
/*    */   long getSynDrawCoolDown();
/*    */   
/*    */   long getSynBoltCoolDown();
/*    */   
/*    */   ReloadState getSynReloadState();
/*    */   
/*    */   float getSynAimingProgress();
/*    */   
/*    */   boolean getSynIsAiming();
/*    */   
/*    */   float getSynSprintTime();
/*    */   
/*    */   void initialData();
/*    */   
/*    */   void draw(Supplier<ItemStack> paramSupplier);
/*    */   
/*    */   void bolt();
/*    */   
/*    */   void reload();
/*    */   
/*    */   void fireSelect();
/*    */   
/*    */   void zoom();
/*    */   
/*    */   void melee();
/*    */   
/*    */   ShootResult shoot(Supplier<Float> paramSupplier1, Supplier<Float> paramSupplier2);
/*    */   
/*    */   boolean needCheckAmmo();
/*    */   
/*    */   boolean consumesAmmoOrNot();
/*    */   
/*    */   void aim(boolean paramBoolean);
/*    */   
/*    */   void crawl(boolean paramBoolean);
/*    */   
/*    */   void updateCacheProperty(AttachmentCacheProperty paramAttachmentCacheProperty);
/*    */   
/*    */   @Nullable
/*    */   AttachmentCacheProperty getCacheProperty();
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\entity\IGunOperator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */