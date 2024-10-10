/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.client.resource.pojo.display.ammo.AmmoParticle;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class GunAmmo {
/*    */   @Nullable
/*    */   @SerializedName("tracer_color")
/*  9 */   private String tracerColor = null;
/*    */   
/*    */   @Nullable
/*    */   @SerializedName("particle")
/* 13 */   private AmmoParticle particle = null;
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getTracerColor() {
/* 19 */     return this.tracerColor;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AmmoParticle getParticle() {
/* 24 */     return this.particle;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\GunAmmo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */