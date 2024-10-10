/*     */ package com.tacz.guns.mixin.client;
/*     */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerAim;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerCrawl;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerDataHolder;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerDraw;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerInspect;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerMelee;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerReload;
/*     */ import com.tacz.guns.client.gameplay.LocalPlayerShoot;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ 
/*     */ @Mixin({LocalPlayer.class})
/*     */ public abstract class LocalPlayerMixin implements IClientPlayerGunOperator {
/*     */   @Unique
/*  19 */   private final LocalPlayer tac$player = (LocalPlayer)this; @Unique
/*  20 */   private final LocalPlayerDataHolder tac$data = new LocalPlayerDataHolder(this.tac$player); @Unique
/*  21 */   private final LocalPlayerAim tac$aim = new LocalPlayerAim(this.tac$data, this.tac$player); @Unique
/*  22 */   private final LocalPlayerCrawl tac$crawl = new LocalPlayerCrawl(this.tac$player); @Unique
/*  23 */   private final LocalPlayerBolt tac$bolt = new LocalPlayerBolt(this.tac$data, this.tac$player); @Unique
/*  24 */   private final LocalPlayerDraw tac$draw = new LocalPlayerDraw(this.tac$data, this.tac$player); @Unique
/*  25 */   private final LocalPlayerFireSelect tac$fireSelect = new LocalPlayerFireSelect(this.tac$data, this.tac$player); @Unique
/*  26 */   private final LocalPlayerMelee tac$melee = new LocalPlayerMelee(this.tac$data, this.tac$player); @Unique
/*  27 */   private final LocalPlayerInspect tac$inspect = new LocalPlayerInspect(this.tac$data, this.tac$player); @Unique
/*  28 */   private final LocalPlayerReload tac$reload = new LocalPlayerReload(this.tac$data, this.tac$player); @Unique
/*  29 */   private final LocalPlayerShoot tac$shoot = new LocalPlayerShoot(this.tac$data, this.tac$player);
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public ShootResult shoot() {
/*  34 */     return this.tac$shoot.shoot();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void draw(ItemStack lastItem) {
/*  40 */     this.tac$draw.draw(lastItem);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void bolt() {
/*  46 */     this.tac$bolt.bolt();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void reload() {
/*  52 */     this.tac$reload.reload();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public void inspect() {
/*  58 */     this.tac$inspect.inspect();
/*     */   }
/*     */ 
/*     */   
/*     */   public void fireSelect() {
/*  63 */     this.tac$fireSelect.fireSelect();
/*     */   }
/*     */ 
/*     */   
/*     */   public void melee() {
/*  68 */     this.tac$melee.melee();
/*     */   }
/*     */ 
/*     */   
/*     */   public void aim(boolean isAim) {
/*  73 */     this.tac$aim.aim(isAim);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrawl() {
/*  78 */     return this.tac$crawl.isCrawling();
/*     */   }
/*     */ 
/*     */   
/*     */   public void crawl(boolean isCrawl) {
/*  83 */     this.tac$crawl.crawl(isCrawl);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public float getClientAimingProgress(float partialTicks) {
/*  89 */     return this.tac$aim.getClientAimingProgress(partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*     */   public long getClientShootCoolDown() {
/*  95 */     return this.tac$shoot.getClientShootCoolDown();
/*     */   }
/*     */   
/*     */   @Inject(method = {"tick"}, at = {@At("HEAD")})
/*     */   public void onTickClientSide(CallbackInfo ci) {
/* 100 */     LocalPlayer player = (LocalPlayer)this;
/* 101 */     if (player.m_9236_().m_5776_()) {
/* 102 */       this.tac$aim.tickAimingProgress();
/* 103 */       this.tac$crawl.tickCrawl();
/* 104 */       this.tac$data.tickStateLock();
/* 105 */       this.tac$bolt.tickAutoBolt();
/*     */     } 
/*     */   }
/*     */   
/*     */   @WrapOperation(method = {"aiStep"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;setSprinting(Z)V")})
/*     */   public void swapSprintStatus(LocalPlayer player, boolean sprinting, Operation<Void> original) {
/* 111 */     original.call(new Object[] { player, Boolean.valueOf(this.tac$aim.cancelSprint(player, sprinting)) });
/*     */   }
/*     */   
/*     */   @Inject(method = {"respawn"}, at = {@At("RETURN")})
/*     */   public void onRespawn(CallbackInfo ci) {
/* 116 */     this.tac$data.reset();
/* 117 */     draw(ItemStack.f_41583_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAim() {
/* 122 */     return this.tac$aim.isAim();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\LocalPlayerMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */