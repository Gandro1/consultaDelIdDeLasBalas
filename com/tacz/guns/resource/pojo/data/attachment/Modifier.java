/*    */ package com.tacz.guns.resource.pojo.data.attachment;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class Modifier {
/*    */   @SerializedName("addend")
/*  8 */   private double addend = 0.0D;
/*    */   
/*    */   @SerializedName("percent")
/* 11 */   private double percent = 0.0D;
/*    */   
/*    */   @SerializedName("multiplier")
/* 14 */   private double multiplier = 1.0D;
/*    */   @Nullable
/*    */   @SerializedName("function")
/* 17 */   private String function = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public double getAddend() {
/* 22 */     return this.addend;
/*    */   }
/*    */   
/*    */   public double getPercent() {
/* 26 */     return this.percent;
/*    */   }
/*    */   
/*    */   public double getMultiplier() {
/* 30 */     return this.multiplier;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getFunction() {
/* 35 */     return this.function;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public void setAddend(double addend) {
/* 40 */     this.addend = addend;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public void setPercent(double percent) {
/* 45 */     this.percent = percent;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\attachment\Modifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */