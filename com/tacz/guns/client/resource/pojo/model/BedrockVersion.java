/*    */ package com.tacz.guns.client.resource.pojo.model;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BedrockVersion
/*    */ {
/*  7 */   LEGACY("1.10.0"),
/*    */ 
/*    */ 
/*    */   
/* 11 */   NEW("1.12.0");
/*    */   
/*    */   private final String version;
/*    */   
/*    */   BedrockVersion(String version) {
/* 16 */     this.version = version;
/*    */   }
/*    */   
/*    */   public String getVersion() {
/* 20 */     return this.version;
/*    */   }
/*    */   
/*    */   public static boolean isNewVersion(BedrockModelPOJO bedrockModel) {
/* 24 */     String[] checkVersion = bedrockModel.getFormatVersion().split("\\.", 3);
/* 25 */     String[] newVersion = NEW.getVersion().split("\\.", 3);
/* 26 */     if (checkVersion.length == 3 && newVersion.length == 3) {
/* 27 */       return (Integer.parseInt(checkVersion[1]) >= Integer.parseInt(newVersion[1]));
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean isLegacyVersion(BedrockModelPOJO bedrockModel) {
/* 33 */     return bedrockModel.getFormatVersion().equals(LEGACY.getVersion());
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\resource\pojo\model\BedrockVersion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */