/*    */ package com.tacz.guns.api.modifier;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParameterizedCache<T>
/*    */ {
/*    */   private final T defaultValue;
/*    */   private final List<String> scripts;
/*    */   private final double addend;
/*    */   private final double percent;
/*    */   private final double multiplier;
/*    */   
/*    */   public ParameterizedCache(List<Modifier> modifiers, T defaultValue) {
/* 22 */     double addend = 0.0D;
/* 23 */     double percent = 1.0D;
/* 24 */     double multiplier = 1.0D;
/*    */     
/* 26 */     ImmutableList.Builder<String> builder = new ImmutableList.Builder();
/* 27 */     for (Modifier mod : modifiers) {
/* 28 */       addend += mod.getAddend();
/* 29 */       percent += mod.getPercent();
/* 30 */       multiplier *= Math.max(mod.getMultiplier(), 0.0D);
/* 31 */       if (StringUtils.isNotEmpty(mod.getFunction())) {
/* 32 */         builder.add(mod.getFunction());
/*    */       }
/*    */     } 
/*    */     
/* 36 */     this.addend = addend;
/* 37 */     this.percent = percent;
/* 38 */     this.multiplier = multiplier;
/* 39 */     this.scripts = (List<String>)builder.build();
/* 40 */     this.defaultValue = defaultValue;
/*    */   }
/*    */   
/*    */   public T getDefaultValue() {
/* 44 */     return this.defaultValue;
/*    */   }
/*    */   
/*    */   public double eval(double input) {
/* 48 */     double percent = Math.max(this.percent, 0.0D);
/* 49 */     double value = (input + this.addend) * percent * this.multiplier;
/* 50 */     for (String function : this.scripts) {
/* 51 */       if (StringUtils.isEmpty(function)) {
/*    */         continue;
/*    */       }
/* 54 */       value = AttachmentPropertyManager.functionEval(value, input, function);
/*    */     } 
/* 56 */     return value;
/*    */   }
/*    */   
/*    */   public double eval(double input, double extraAddend, double extraPercent, double extraMultiplier) {
/* 60 */     double percent = Math.max(this.percent + extraPercent, 0.0D);
/* 61 */     extraMultiplier = Math.max(extraMultiplier, 0.0D);
/* 62 */     double value = (input + this.addend + extraAddend) * percent * this.multiplier * extraMultiplier;
/* 63 */     for (String function : this.scripts) {
/* 64 */       if (StringUtils.isEmpty(function)) {
/*    */         continue;
/*    */       }
/* 67 */       value = AttachmentPropertyManager.functionEval(value, input, function);
/*    */     } 
/* 69 */     return value;
/*    */   }
/*    */   
/*    */   public static <T> ParameterizedCache<T> of(T defaultValue) {
/* 73 */     return new ParameterizedCache<>(List.of(), defaultValue);
/*    */   }
/*    */   
/*    */   public static <T> ParameterizedCache<T> of(List<Modifier> modifiers, T defaultValue) {
/* 77 */     return new ParameterizedCache<>(modifiers, defaultValue);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\modifier\ParameterizedCache.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */