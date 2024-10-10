/*   */ package com.tacz.guns.util;
/*   */ 
/*   */ import java.nio.file.Path;
/*   */ 
/*   */ public final class PathHandler {
/*   */   public static String getPath(Path root, Path file, String suffix) {
/* 7 */     String relative = root.relativize(file).toString();
/* 8 */     String relativeWithoutSuffix = relative.substring(0, relative.length() - suffix.length());
/* 9 */     return relativeWithoutSuffix.replace('\\', '/');
/*   */   }
/*   */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\gun\\util\PathHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */