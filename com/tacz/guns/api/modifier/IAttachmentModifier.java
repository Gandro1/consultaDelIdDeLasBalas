/*    */ package com.tacz.guns.api.modifier;
/*    */ 
/*    */ import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IAttachmentModifier<T, K>
/*    */ {
/*    */   String getId();
/*    */   
/*    */   default String getOptionalFields() {
/* 33 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   JsonProperty<T> readJson(String paramString);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   CacheValue<K> initCache(ItemStack paramItemStack, GunData paramGunData);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void eval(List<T> paramList, CacheValue<K> paramCacheValue);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   default List<DiagramsData> getPropertyDiagramsData(ItemStack gunItem, GunData gunData, AttachmentCacheProperty cacheProperty) {
/* 66 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   default int getDiagramsDataSize() {
/* 74 */     return 0;
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static final class DiagramsData extends Record {
/*    */     private final double defaultPercent;
/*    */     private final double modifierPercent;
/*    */     private final Number modifier;
/*    */     private final String titleKey;
/*    */     private final String positivelyString;
/*    */     private final String negativeString;
/*    */     private final String defaultString;
/*    */     private final boolean positivelyBetter;
/*    */     
/*    */     public boolean positivelyBetter() {
/* 89 */       return this.positivelyBetter; } public String defaultString() { return this.defaultString; } public String negativeString() { return this.negativeString; } public String positivelyString() { return this.positivelyString; } public String titleKey() { return this.titleKey; } public Number modifier() { return this.modifier; } public double modifierPercent() { return this.modifierPercent; } public double defaultPercent() { return this.defaultPercent; } public DiagramsData(double defaultPercent, double modifierPercent, Number modifier, String titleKey, String positivelyString, String negativeString, String defaultString, boolean positivelyBetter) {
/* 90 */       this.defaultPercent = defaultPercent; this.modifierPercent = modifierPercent; this.modifier = modifier; this.titleKey = titleKey; this.positivelyString = positivelyString; this.negativeString = negativeString; this.defaultString = defaultString; this.positivelyBetter = positivelyBetter;
/*    */     }
/*    */     
/*    */     public final boolean equals(Object o) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #89	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;
/*    */       //   0	8	1	o	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public final int hashCode() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #89	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;
/*    */     }
/*    */     
/*    */     public final String toString() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #89	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/tacz/guns/api/modifier/IAttachmentModifier$DiagramsData;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\modifier\IAttachmentModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */