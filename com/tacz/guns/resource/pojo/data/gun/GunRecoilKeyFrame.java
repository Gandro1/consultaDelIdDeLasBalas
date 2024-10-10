/*    */ package com.tacz.guns.resource.pojo.data.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GunRecoilKeyFrame implements Comparable<GunRecoilKeyFrame> {
/*    */   @SerializedName("time")
/*    */   private float time;
/*    */   @SerializedName("value")
/* 10 */   private float[] value = new float[] { 0.0F, 0.0F };
/*    */ 
/*    */   
/*    */   public float getTime() {
/* 14 */     return this.time;
/*    */   }
/*    */   
/*    */   public void setTime(float time) {
/* 18 */     this.time = time;
/*    */   }
/*    */   
/*    */   public float[] getValue() {
/* 22 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(float[] value) {
/* 26 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull GunRecoilKeyFrame o) {
/* 31 */     return Double.compare(this.time, o.time);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\gun\GunRecoilKeyFrame.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */