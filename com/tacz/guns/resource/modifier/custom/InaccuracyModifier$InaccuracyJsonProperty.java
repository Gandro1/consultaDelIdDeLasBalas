/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import com.tacz.guns.resource.pojo.data.gun.InaccuracyType;
/*     */ import java.util.Map;
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
/*     */ public class InaccuracyJsonProperty
/*     */   extends JsonProperty<Map<InaccuracyType, Modifier>>
/*     */ {
/*     */   public InaccuracyJsonProperty(Map<InaccuracyType, Modifier> value) {
/* 147 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/* 152 */     Map<InaccuracyType, Modifier> value = (Map<InaccuracyType, Modifier>)getValue();
/* 153 */     float inaccuracyAddend = 0.0F;
/* 154 */     if (value != null && value.containsKey(InaccuracyType.STAND)) {
/*     */       
/* 156 */       double eval = AttachmentPropertyManager.eval(value.get(InaccuracyType.STAND), 5.0D);
/* 157 */       inaccuracyAddend = (float)(eval - 5.0D);
/*     */     } 
/*     */     
/* 160 */     if (inaccuracyAddend > 0.0F) {
/* 161 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.inaccuracy.decrease").m_130940_(ChatFormatting.RED));
/* 162 */     } else if (inaccuracyAddend < 0.0F) {
/* 163 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.inaccuracy.increase").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\InaccuracyModifier$InaccuracyJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */