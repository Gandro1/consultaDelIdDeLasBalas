/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import it.unimi.dsi.fastutil.Pair;
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
/*     */ public class RecoilJsonProperty
/*     */   extends JsonProperty<Pair<Modifier, Modifier>>
/*     */ {
/*     */   public RecoilJsonProperty(Pair<Modifier, Modifier> value) {
/* 131 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/* 136 */     Pair<Modifier, Modifier> modified = (Pair<Modifier, Modifier>)getValue();
/* 137 */     float pitch = 1.0F;
/* 138 */     float yaw = 1.0F;
/*     */     
/* 140 */     if (modified != null) {
/* 141 */       pitch = (float)AttachmentPropertyManager.eval((Modifier)modified.left(), 1.0D);
/* 142 */       yaw = (float)AttachmentPropertyManager.eval((Modifier)modified.right(), 1.0D);
/*     */     } 
/*     */     
/* 145 */     if (pitch > 1.0F) {
/* 146 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.pitch.increase").m_130940_(ChatFormatting.RED));
/* 147 */     } else if (pitch < 1.0F) {
/* 148 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.pitch.decrease").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/* 150 */     if (yaw > 1.0F) {
/* 151 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.yaw.increase").m_130940_(ChatFormatting.RED));
/* 152 */     } else if (yaw < 1.0F) {
/* 153 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.yaw.decrease").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\RecoilModifier$RecoilJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */