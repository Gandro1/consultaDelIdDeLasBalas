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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeadShotJsonProperty
/*     */   extends JsonProperty<Modifier>
/*     */ {
/*     */   public HeadShotJsonProperty(Modifier value) {
/* 107 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/* 112 */     Modifier value = (Modifier)getValue();
/* 113 */     if (value != null) {
/* 114 */       double eval = AttachmentPropertyManager.eval(value, 2.0D);
/* 115 */       if (eval > 2.0D) {
/* 116 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.head_shot.increase").m_130940_(ChatFormatting.GREEN));
/* 117 */       } else if (eval < 2.0D) {
/* 118 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.head_shot.decrease").m_130940_(ChatFormatting.RED));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\HeadShotModifier$HeadShotJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */