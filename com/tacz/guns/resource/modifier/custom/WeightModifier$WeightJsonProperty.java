/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightJsonProperty
/*     */   extends JsonProperty<Modifier>
/*     */ {
/*     */   public WeightJsonProperty(Modifier value) {
/*  86 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/*  91 */     Modifier value = (Modifier)getValue();
/*  92 */     float adsAddendTime = 0.0F;
/*  93 */     if (value != null) {
/*     */       
/*  95 */       double eval = AttachmentPropertyManager.eval(value, 0.2D);
/*  96 */       adsAddendTime = (float)(eval - 0.2D);
/*     */     } 
/*     */     
/*  99 */     if (adsAddendTime > 0.0F) {
/* 100 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.weight.increase").m_130940_(ChatFormatting.RED));
/* 101 */     } else if (adsAddendTime < 0.0F) {
/* 102 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.weight.decrease").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\WeightModifier$WeightJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */