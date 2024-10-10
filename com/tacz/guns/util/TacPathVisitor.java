/*    */ package com.tacz.guns.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.FileVisitResult;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.SimpleFileVisitor;
/*    */ import java.nio.file.attribute.BasicFileAttributes;
/*    */ import java.util.function.BiConsumer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class TacPathVisitor extends SimpleFileVisitor<Path> {
/*    */   private final File root;
/*    */   private final String namespace;
/*    */   private final String suffix;
/*    */   private final BiConsumer<ResourceLocation, Path> consumer;
/*    */   
/*    */   public TacPathVisitor(File root, String namespace, String suffix, BiConsumer<ResourceLocation, Path> consumer) {
/* 19 */     this.root = root;
/* 20 */     this.namespace = namespace;
/* 21 */     this.suffix = suffix;
/* 22 */     this.consumer = consumer;
/*    */   }
/*    */ 
/*    */   
/*    */   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
/* 27 */     if (file.toFile().getName().endsWith(this.suffix)) {
/* 28 */       String path = PathHandler.getPath(this.root.toPath(), file, this.suffix);
/* 29 */       ResourceLocation id = new ResourceLocation(this.namespace, path);
/* 30 */       this.consumer.accept(id, file);
/*    */     } 
/* 32 */     return FileVisitResult.CONTINUE;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\TacPathVisitor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */