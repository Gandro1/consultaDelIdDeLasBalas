/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.minecraft.core.Direction;
/*    */ 
/*    */ public class FaceUVsItem {
/*    */   @SerializedName("down")
/*    */   private FaceItem down;
/*    */   @SerializedName("east")
/*    */   private FaceItem east;
/*    */   @SerializedName("north")
/*    */   private FaceItem north;
/*    */   @SerializedName("south")
/*    */   private FaceItem south;
/*    */   @SerializedName("up")
/*    */   private FaceItem up;
/*    */   @SerializedName("west")
/*    */   private FaceItem west;
/*    */   
/*    */   public static FaceUVsItem singleSouthFace() {
/* 21 */     FaceUVsItem faces = new FaceUVsItem();
/* 22 */     faces.north = FaceItem.EMPTY;
/* 23 */     faces.east = FaceItem.EMPTY;
/* 24 */     faces.west = FaceItem.EMPTY;
/* 25 */     faces.south = FaceItem.single16X();
/* 26 */     faces.up = FaceItem.EMPTY;
/* 27 */     faces.down = FaceItem.EMPTY;
/* 28 */     return faces;
/*    */   }
/*    */   
/*    */   public FaceItem getFace(Direction direction) {
/* 32 */     switch (direction) {
/*    */       case EAST:
/* 34 */         return (this.west == null) ? FaceItem.EMPTY : this.west;
/*    */       case WEST:
/* 36 */         return (this.east == null) ? FaceItem.EMPTY : this.east;
/*    */       case NORTH:
/* 38 */         return (this.north == null) ? FaceItem.EMPTY : this.north;
/*    */       case SOUTH:
/* 40 */         return (this.south == null) ? FaceItem.EMPTY : this.south;
/*    */       case UP:
/* 42 */         return (this.down == null) ? FaceItem.EMPTY : this.down;
/*    */     } 
/*    */     
/* 45 */     return (this.up == null) ? FaceItem.EMPTY : this.up;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\FaceUVsItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */