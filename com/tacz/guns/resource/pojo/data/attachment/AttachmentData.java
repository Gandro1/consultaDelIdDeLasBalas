/*    */ package com.tacz.guns.resource.pojo.data.attachment;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class AttachmentData
/*    */ {
/*    */   @Expose(serialize = false, deserialize = false)
/* 14 */   private Map<String, JsonProperty<?>> modifier = Maps.newHashMap();
/*    */   @SerializedName("weight")
/* 16 */   private float weight = 0.0F;
/*    */   
/*    */   @SerializedName("extended_mag_level")
/* 19 */   private int extendedMagLevel = 0;
/*    */   @SerializedName("melee")
/*    */   @Nullable
/* 22 */   private MeleeData meleeData = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public float getWeight() {
/* 27 */     return this.weight;
/*    */   }
/*    */   
/*    */   public int getExtendedMagLevel() {
/* 31 */     return this.extendedMagLevel;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public MeleeData getMeleeData() {
/* 36 */     return this.meleeData;
/*    */   }
/*    */   
/*    */   public void addModifier(String id, JsonProperty<?> jsonProperty) {
/* 40 */     this.modifier.put(id, jsonProperty);
/*    */   }
/*    */   
/*    */   public Map<String, JsonProperty<?>> getModifier() {
/* 44 */     return this.modifier;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\pojo\data\attachment\AttachmentData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */