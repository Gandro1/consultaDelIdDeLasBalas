/*    */ package com.tacz.guns.client.resource.pojo.display.gun;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class TextShow {
/*    */   @SerializedName("scale")
/*  7 */   private float scale = 1.0F;
/*    */   
/*    */   @SerializedName("align")
/* 10 */   private Align align = Align.CENTER;
/*    */   
/*    */   @SerializedName("shadow")
/*    */   private boolean shadow = false;
/*    */   
/*    */   @SerializedName("color")
/* 16 */   private String colorText = "#FFFFFF";
/*    */   
/*    */   @SerializedName("light")
/* 19 */   private int textLight = 15;
/*    */   
/*    */   @SerializedName("text")
/* 22 */   private String textKey = "";
/*    */ 
/*    */   
/* 25 */   private volatile int colorInt = 16777215;
/*    */   
/*    */   public float getScale() {
/* 28 */     return this.scale;
/*    */   }
/*    */   
/*    */   public Align getAlign() {
/* 32 */     return this.align;
/*    */   }
/*    */   
/*    */   public boolean isShadow() {
/* 36 */     return this.shadow;
/*    */   }
/*    */   
/*    */   public String getTextKey() {
/* 40 */     return this.textKey;
/*    */   }
/*    */   
/*    */   public String getColorText() {
/* 44 */     return this.colorText;
/*    */   }
/*    */   
/*    */   public int getTextLight() {
/* 48 */     return this.textLight;
/*    */   }
/*    */   
/*    */   public int getColorInt() {
/* 52 */     return this.colorInt;
/*    */   }
/*    */   
/*    */   public void setColorInt(int colorInt) {
/* 56 */     this.colorInt = colorInt;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\display\gun\TextShow.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */