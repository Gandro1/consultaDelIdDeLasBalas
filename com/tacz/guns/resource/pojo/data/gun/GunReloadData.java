/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class GunReloadData {
/*    */   @SerializedName("type")
/*  6 */   private FeedType type = FeedType.MAGAZINE;
/*    */   
/*    */   @SerializedName("feed")
/*  9 */   private GunReloadTime feed = new GunReloadTime();
/*    */   
/*    */   @SerializedName("cooldown")
/* 12 */   private GunReloadTime cooldown = new GunReloadTime();
/*    */ 
/*    */   
/*    */   public FeedType getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   
/*    */   public GunReloadTime getFeed() {
/* 20 */     return this.feed;
/*    */   }
/*    */   
/*    */   public GunReloadTime getCooldown() {
/* 24 */     return this.cooldown;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunReloadData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */