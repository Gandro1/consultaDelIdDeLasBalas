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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RpmJsonProperty
/*    */   extends JsonProperty<Modifier>
/*    */ {
/*    */   public RpmJsonProperty(Modifier value) {
/* 83 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 88 */     Modifier value = (Modifier)getValue();
/* 89 */     if (value != null) {
/* 90 */       double eval = AttachmentPropertyManager.eval(value, 300.0D);
/* 91 */       int rpm = (int)Math.round(eval);
/* 92 */       if (rpm > 300) {
/* 93 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.rpm.increase").m_130940_(ChatFormatting.GREEN));
/* 94 */       } else if (rpm < 300) {
/* 95 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.rpm.decrease").m_130940_(ChatFormatting.RED));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\RpmModifier$RpmJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */