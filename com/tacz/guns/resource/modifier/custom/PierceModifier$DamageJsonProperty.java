/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageJsonProperty
/*    */   extends JsonProperty<Modifier>
/*    */ {
/*    */   public DamageJsonProperty(Modifier value) {
/* 75 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 80 */     Modifier pierce = (Modifier)getValue();
/* 81 */     if (pierce != null) {
/* 82 */       long eval = Math.round(AttachmentPropertyManager.eval(pierce, 5.0D));
/* 83 */       eval = Math.max(eval, 1L);
/* 84 */       if (eval > 5L) {
/* 85 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.pierce.increase").m_130940_(ChatFormatting.GREEN));
/* 86 */       } else if (eval < 5L) {
/* 87 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.pierce.decrease").m_130940_(ChatFormatting.RED));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\PierceModifier$DamageJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */