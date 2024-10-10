/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class Ignite
/*    */ {
/*    */   @SerializedName("entity")
/*    */   private boolean igniteEntity = false;
/*    */   @SerializedName("block")
/*    */   private boolean igniteBlock = false;
/*    */   
/*    */   public Ignite(boolean igniteEntity, boolean igniteBlock) {
/* 13 */     this.igniteEntity = igniteEntity;
/* 14 */     this.igniteBlock = igniteBlock;
/*    */   }
/*    */   
/*    */   public Ignite(boolean ignite) {
/* 18 */     this(ignite, ignite);
/*    */   }
/*    */   
/*    */   public boolean isIgniteEntity() {
/* 22 */     return this.igniteEntity;
/*    */   }
/*    */   
/*    */   public boolean isIgniteBlock() {
/* 26 */     return this.igniteBlock;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\Ignite.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */