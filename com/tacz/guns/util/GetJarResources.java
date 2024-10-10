/*     */ package com.tacz.guns.util;
/*     */ import com.tacz.guns.GunMod;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.FileVisitResult;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.SimpleFileVisitor;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.BasicFileAttributes;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.nio.file.attribute.FileTime;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.time.Instant;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public final class GetJarResources {
/*  34 */   private static final Instant BACKUP_TIME = Instant.parse("2024-02-26T12:28:08.000Z");
/*  35 */   private static final Path BACKUP_PATH = Paths.get("config", new String[] { "tacz", "backup" });
/*  36 */   private static final SimpleDateFormat BACKUP_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MAX_BACKUP_COUNT = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyModFile(String srcPath, Path root, String path) {
/*  50 */     URL url = GunMod.class.getResource(srcPath);
/*     */     try {
/*  52 */       if (url != null) {
/*  53 */         FileUtils.copyURLToFile(url, root.resolve(path).toFile());
/*     */       }
/*  55 */     } catch (IOException e) {
/*  56 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyModDirectory(Class<?> resourceClass, String srcPath, Path root, String path) {
/*  68 */     URL url = resourceClass.getResource(srcPath);
/*     */     try {
/*  70 */       if (url != null) {
/*  71 */         copyFolder(url.toURI(), root.resolve(path));
/*     */       }
/*  73 */     } catch (IOException e) {
/*  74 */       e.printStackTrace();
/*  75 */     } catch (URISyntaxException e) {
/*  76 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyModDirectory(String srcPath, Path root, String path) {
/*  88 */     copyModDirectory(GunMod.class, srcPath, root, path);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static InputStream readModFile(String filePath) {
/*  93 */     URL url = GunMod.class.getResource(filePath);
/*     */     try {
/*  95 */       if (url != null) {
/*  96 */         return url.openStream();
/*     */       }
/*  98 */     } catch (IOException e) {
/*  99 */       e.printStackTrace();
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   private static void copyFolder(URI sourceURI, Path targetPath) throws IOException {
/* 105 */     if (Files.isDirectory(targetPath, new java.nio.file.LinkOption[0])) {
/*     */       
/* 107 */       backupFiles(targetPath);
/*     */       
/* 109 */       deleteFiles(targetPath);
/*     */     } 
/*     */     
/* 112 */     Stream<Path> stream = Files.walk(Paths.get(sourceURI), 2147483647, new java.nio.file.FileVisitOption[0]); 
/* 113 */     try { stream.forEach(source -> {
/*     */             Path target = targetPath.resolve(sourceURI.relativize(source.toUri()).toString());
/*     */ 
/*     */             
/*     */             try {
/*     */               if (Files.isDirectory(source, new java.nio.file.LinkOption[0])) {
/*     */                 Files.createDirectories(target, (FileAttribute<?>[])new FileAttribute[0]);
/*     */               } else {
/*     */                 Files.copy(source, target, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */               } 
/* 123 */             } catch (IOException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */           });
/*     */       
/* 128 */       if (stream != null) stream.close();  }
/*     */     catch (Throwable throwable) { if (stream != null)
/*     */         try { stream.close(); }
/*     */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */           throw throwable; }
/* 133 */      } private static void backupFiles(Path targetPath) throws IOException { String dirName = targetPath.getFileName().toString();
/* 134 */     Path backupPath = BACKUP_PATH.resolve(dirName);
/* 135 */     if (!Files.isDirectory(backupPath, new java.nio.file.LinkOption[0])) {
/* 136 */       Files.createDirectories(backupPath, (FileAttribute<?>[])new FileAttribute[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 141 */     Set<String> cacheMd5 = checkOldBackups(backupPath);
/*     */ 
/*     */     
/* 144 */     File tempFile = File.createTempFile(dirName, ".tmp");
/* 145 */     FileTime fileTime = FileTime.from(BACKUP_TIME);
/*     */ 
/*     */     
/* 148 */     ZipOutputStream zs = new ZipOutputStream(new FileOutputStream(tempFile)); 
/* 149 */     try { Stream<Path> fileWalks = Files.walk(targetPath, new java.nio.file.FileVisitOption[0]); 
/* 150 */       try { fileWalks.filter(x$0 -> Files.isRegularFile(x$0, new java.nio.file.LinkOption[0])).forEach(path -> {
/*     */               String entryPath = targetPath.relativize(path).toString();
/*     */               
/*     */               ZipEntry zipEntry = new ZipEntry(entryPath);
/*     */               zipEntry.setLastModifiedTime(fileTime);
/*     */               try {
/*     */                 zs.putNextEntry(zipEntry);
/*     */                 Files.copy(path, zs);
/*     */                 zs.closeEntry();
/* 159 */               } catch (IOException e) {
/*     */                 GunMod.LOGGER.info("Error in zip file: {}", e.getMessage());
/*     */               } 
/*     */             });
/* 163 */         if (fileWalks != null) fileWalks.close();  } catch (Throwable throwable) { if (fileWalks != null) try { fileWalks.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  zs.close(); } catch (Throwable throwable) { try { zs.close(); }
/*     */       catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */        throw throwable; }
/* 166 */      FileInputStream inputStream = new FileInputStream(tempFile); 
/* 167 */     try { String md5Hex = Md5Utils.md5Hex(inputStream);
/*     */       
/* 169 */       if (cacheMd5.contains(md5Hex)) {
/*     */         
/* 171 */         tempFile.deleteOnExit();
/*     */       } else {
/*     */         
/* 174 */         String dataName = BACKUP_DATE_FORMAT.format(new Date()).toLowerCase(Locale.ENGLISH);
/* 175 */         Path backupZipFilePath = backupPath.resolve(String.format("backup-%s-%s.zip", new Object[] { dataName, md5Hex }));
/* 176 */         FileUtils.copyFile(tempFile, backupZipFilePath.toFile());
/*     */       } 
/* 178 */       inputStream.close(); }
/*     */     catch (Throwable throwable) { try { inputStream.close(); }
/*     */       catch (Throwable throwable1)
/*     */       { throwable.addSuppressed(throwable1); }
/*     */        throw throwable; }
/* 183 */      } private static Set<String> checkOldBackups(Path backupPath) { Set<String> allMd5Hex = Sets.newHashSet();
/* 184 */     if (!Files.isDirectory(backupPath, new java.nio.file.LinkOption[0])) {
/* 185 */       return allMd5Hex;
/*     */     }
/*     */     try {
/* 188 */       List<File> delFiles = Lists.newArrayList(FileUtils.listFiles(backupPath.toFile(), TrueFileFilter.TRUE, null));
/* 189 */       delFiles.sort(LastModifiedFileComparator.LASTMODIFIED_REVERSE);
/* 190 */       int count = 1;
/* 191 */       for (File file : delFiles) {
/* 192 */         if (count >= 10) {
/*     */           
/* 194 */           GunMod.LOGGER.info("Deleting old backup gun pack {}", file.getName());
/* 195 */           FileUtils.deleteQuietly(file);
/*     */         } else {
/*     */           
/* 198 */           FileInputStream inputStream = new FileInputStream(file); 
/* 199 */           try { allMd5Hex.add(Md5Utils.md5Hex(inputStream));
/* 200 */             inputStream.close(); } catch (Throwable throwable) { try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/*     */         
/* 202 */         }  count++;
/*     */       } 
/* 204 */     } catch (Exception exception) {
/* 205 */       GunMod.LOGGER.error("Error while checking old backup gun pack : {}", exception.getMessage());
/*     */     } 
/* 207 */     return allMd5Hex; }
/*     */ 
/*     */   
/*     */   private static void deleteFiles(Path targetPath) throws IOException {
/* 211 */     Files.walkFileTree(targetPath, new SimpleFileVisitor<Path>()
/*     */         {
/*     */           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
/*     */           {
/* 215 */             Files.delete(file);
/* 216 */             return FileVisitResult.CONTINUE;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
/* 222 */             Files.delete(dir);
/* 223 */             return FileVisitResult.CONTINUE;
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\GetJarResources.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */