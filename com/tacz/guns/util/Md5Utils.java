/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public final class Md5Utils {
/*    */   private static final int STREAM_BUFFER_LENGTH = 1024;
/*    */   private static final MessageDigest DIGEST;
/*    */   
/*    */   static {
/*    */     try {
/* 14 */       DIGEST = MessageDigest.getInstance("MD5");
/* 15 */     } catch (NoSuchAlgorithmException e) {
/* 16 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String md5Hex(InputStream inputStream) throws IOException {
/* 21 */     return toHexString(md5(inputStream));
/*    */   }
/*    */   
/*    */   public static String md5Hex(byte[] data) {
/* 25 */     return toHexString(DIGEST.digest(data));
/*    */   }
/*    */   
/*    */   public static byte[] md5(InputStream inputStream) throws IOException {
/* 29 */     byte[] buffer = new byte[1024];
/* 30 */     int read = inputStream.read(buffer, 0, 1024);
/* 31 */     while (read > -1) {
/* 32 */       DIGEST.update(buffer, 0, read);
/* 33 */       read = inputStream.read(buffer, 0, 1024);
/*    */     } 
/* 35 */     return DIGEST.digest();
/*    */   }
/*    */   
/*    */   public static byte[] md5(byte[] data) {
/* 39 */     return DIGEST.digest(data);
/*    */   }
/*    */   
/*    */   public static String toHexString(byte[] bytes) {
/* 43 */     StringBuilder hexString = new StringBuilder();
/* 44 */     for (byte b : bytes) {
/* 45 */       String hex = Integer.toHexString(0xFF & b);
/* 46 */       if (hex.length() == 1) {
/* 47 */         hexString.append('0');
/*    */       }
/* 49 */       hexString.append(hex);
/*    */     } 
/* 51 */     return hexString.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\Md5Utils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */