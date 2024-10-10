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
/*     */ public class EffectiveRangeJsonProperty
/*     */   extends JsonProperty<Modifier>
/*     */ {
/*     */   public EffectiveRangeJsonProperty(Modifier value) {
/*  97 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/* 102 */     Modifier value = (Modifier)getValue();
/* 103 */     if (value != null) {
/* 104 */       double eval = AttachmentPropertyManager.eval(value, 25.0D);
/* 105 */       if (eval > 25.0D) {
/* 106 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.effective_range.increase").m_130940_(ChatFormatting.GREEN));
/* 107 */       } else if (eval < 25.0D) {
/* 108 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.effective_range.decrease").m_130940_(ChatFormatting.RED));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\EffectiveRangeModifier$EffectiveRangeJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */