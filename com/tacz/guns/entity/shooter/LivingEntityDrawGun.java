/*    */ package com.tacz.guns.entity.shooter;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.event.common.GunDrawEvent;
/*    */ import com.tacz.guns.api.item.IGun;
/*    */ import com.tacz.guns.network.NetworkHandler;
/*    */ import com.tacz.guns.network.message.event.ServerMessageGunDraw;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.LogicalSide;
/*    */ 
/*    */ public class LivingEntityDrawGun {
/*    */   private final LivingEntity shooter;
/*    */   
/*    */   public LivingEntityDrawGun(LivingEntity shooter, ShooterDataHolder data) {
/* 24 */     this.shooter = shooter;
/* 25 */     this.data = data;
/*    */   }
/*    */   private final ShooterDataHolder data;
/*    */   
/*    */   public void draw(Supplier<ItemStack> gunItemSupplier) {
/* 30 */     this.data.initialData();
/*    */     
/* 32 */     if (this.data.drawTimestamp == -1L) {
/* 33 */       this.data.drawTimestamp = System.currentTimeMillis();
/*    */     }
/* 35 */     long drawTime = System.currentTimeMillis() - this.data.drawTimestamp;
/* 36 */     if (drawTime >= 0L)
/*    */     {
/* 38 */       if ((float)drawTime < this.data.currentPutAwayTimeS * 1000.0F) {
/*    */         
/* 40 */         this.data.drawTimestamp = System.currentTimeMillis() + drawTime;
/*    */       } else {
/*    */         
/* 43 */         this.data.drawTimestamp = System.currentTimeMillis() + (long)(this.data.currentPutAwayTimeS * 1000.0F);
/*    */       } 
/*    */     }
/* 46 */     ItemStack lastItem = (this.data.currentGunItem == null) ? ItemStack.f_41583_ : this.data.currentGunItem.get();
/* 47 */     MinecraftForge.EVENT_BUS.post((Event)new GunDrawEvent(this.shooter, lastItem, gunItemSupplier.get(), LogicalSide.SERVER));
/* 48 */     NetworkHandler.sendToTrackingEntity(new ServerMessageGunDraw(this.shooter.m_19879_(), lastItem, gunItemSupplier.get()), (Entity)this.shooter);
/* 49 */     this.data.currentGunItem = gunItemSupplier;
/*    */     
/* 51 */     AttachmentPropertyManager.postChangeEvent(this.shooter, gunItemSupplier.get());
/* 52 */     updatePutAwayTime();
/*    */   }
/*    */   public long getDrawCoolDown() {
/*    */     IGun iGun;
/* 56 */     if (this.data.currentGunItem == null) {
/* 57 */       return 0L;
/*    */     }
/* 59 */     ItemStack currentGunItem = this.data.currentGunItem.get();
/* 60 */     Item item = currentGunItem.m_41720_(); if (item instanceof IGun) { iGun = (IGun)item; }
/* 61 */     else { return 0L; }
/*    */     
/* 63 */     ResourceLocation gunId = iGun.getGunId(currentGunItem);
/* 64 */     Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(gunId);
/* 65 */     return ((Long)gunIndex.<Long>map(index -> {
/*    */           long coolDown = (long)(index.getGunData().getDrawTime() * 1000.0F) - System.currentTimeMillis() - this.data.drawTimestamp;
/*    */ 
/*    */           
/*    */           coolDown -= 5L;
/*    */ 
/*    */           
/*    */           return (coolDown < 0L) ? Long.valueOf(0L) : Long.valueOf(coolDown);
/* 73 */         }).orElse(Long.valueOf(-1L))).longValue();
/*    */   }
/*    */   
/*    */   private void updatePutAwayTime() {
/* 77 */     ItemStack gunItem = (this.data.currentGunItem == null) ? ItemStack.f_41583_ : this.data.currentGunItem.get();
/* 78 */     IGun iGun = IGun.getIGunOrNull(gunItem);
/* 79 */     if (iGun != null) {
/* 80 */       Optional<CommonGunIndex> gunIndex = TimelessAPI.getCommonGunIndex(iGun.getGunId(gunItem));
/* 81 */       this.data.currentPutAwayTimeS = ((Float)gunIndex.<Float>map(index -> Float.valueOf(index.getGunData().getPutAwayTime())).orElse(Float.valueOf(0.0F))).floatValue();
/*    */     } else {
/* 83 */       this.data.currentPutAwayTimeS = 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\shooter\LivingEntityDrawGun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */