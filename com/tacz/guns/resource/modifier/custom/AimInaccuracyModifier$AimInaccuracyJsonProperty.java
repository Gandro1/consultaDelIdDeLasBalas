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
/*     */ public class AimInaccuracyJsonProperty
/*     */   extends JsonProperty<Map<InaccuracyType, Modifier>>
/*     */ {
/*     */   public AimInaccuracyJsonProperty(Map<InaccuracyType, Modifier> value) {
/* 138 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/* 143 */     Map<InaccuracyType, Modifier> value = (Map<InaccuracyType, Modifier>)getValue();
/* 144 */     float inaccuracyAddend = 0.0F;
/* 145 */     if (value != null && value.containsKey(InaccuracyType.AIM)) {
/*     */       
/* 147 */       double eval = AttachmentPropertyManager.eval(value.get(InaccuracyType.AIM), 0.15000000596046448D);
/* 148 */       inaccuracyAddend = (float)(eval - 0.15000000596046448D);
/*     */     } 
/*     */     
/* 151 */     if (inaccuracyAddend > 0.0F) {
/* 152 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.aim_inaccuracy.decrease").m_130940_(ChatFormatting.RED));
/* 153 */     } else if (inaccuracyAddend < 0.0F) {
/* 154 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.aim_inaccuracy.increase").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AimInaccuracyModifier$AimInaccuracyJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */