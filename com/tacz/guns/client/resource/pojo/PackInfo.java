/*    */ package com.tacz.guns.client.resource.pojo;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PackInfo {
/*    */   @SerializedName("version")
/*  9 */   private String version = "1.0.0";
/*    */   
/*    */   @SerializedName("name")
/* 12 */   private String name = "custom.tacz.error.no_name";
/*    */   
/*    */   @SerializedName("desc")
/* 15 */   private String description = "";
/*    */   
/*    */   @SerializedName("license")
/* 18 */   private String license = "All Rights Reserved";
/*    */ 
/*    */   
/*    */   @SerializedName("authors")
/* 22 */   private List<String> authors = Lists.newArrayList();
/*    */   @SerializedName("date")
/* 24 */   private String date = "1919-08-10";
/*    */   
/*    */   @SerializedName("url")
/*    */   private String url;
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 31 */     return this.version;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 39 */     return this.description;
/*    */   }
/*    */   
/*    */   public String getLicense() {
/* 43 */     return this.license;
/*    */   }
/*    */   
/*    */   public List<String> getAuthors() {
/* 47 */     return this.authors;
/*    */   }
/*    */   
/*    */   public String getDate() {
/* 51 */     return this.date;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 55 */     return this.url;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\PackInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */