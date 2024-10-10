/*    */ package com.tacz.guns.resource.pojo.data.recipe;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.item.attachment.AttachmentType;
/*    */ import java.util.EnumMap;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class GunResult {
/*    */   @SerializedName("ammo_count")
/* 11 */   private int ammoCount = 0;
/*    */ 
/*    */   
/*    */   @SerializedName("attachments")
/* 15 */   private EnumMap<AttachmentType, ResourceLocation> attachments = Maps.newEnumMap(AttachmentType.class);
/*    */   
/*    */   public int getAmmoCount() {
/* 18 */     return this.ammoCount;
/*    */   }
/*    */   
/*    */   public EnumMap<AttachmentType, ResourceLocation> getAttachments() {
/* 22 */     return this.attachments;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\recipe\GunResult.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */