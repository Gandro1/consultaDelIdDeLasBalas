/*    */ package com.tacz.guns.api.resource;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ResourceManager
/*    */ {
/* 16 */   public static final List<ExtraEntry> EXTRA_ENTRIES = Lists.newArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerExtraGunPack(Class<?> modMainClass, String extraFolderPath) {
/* 26 */     EXTRA_ENTRIES.add(new ExtraEntry(modMainClass, extraFolderPath, Paths.get(extraFolderPath, new String[0]).getFileName().toString()));
/*    */   } public static final class ExtraEntry extends Record {
/*    */     private final Class<?> modMainClass; private final String srcPath; private final String extraDirName; public final String toString() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #32	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;
/*    */     } public final int hashCode() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #32	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;
/* 32 */     } public ExtraEntry(Class<?> modMainClass, String srcPath, String extraDirName) { this.modMainClass = modMainClass; this.srcPath = srcPath; this.extraDirName = extraDirName; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #32	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/tacz/guns/api/resource/ResourceManager$ExtraEntry;
/* 32 */       //   0	8	1	o	Ljava/lang/Object; } public Class<?> modMainClass() { return this.modMainClass; } public String srcPath() { return this.srcPath; } public String extraDirName() { return this.extraDirName; }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\resource\ResourceManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */