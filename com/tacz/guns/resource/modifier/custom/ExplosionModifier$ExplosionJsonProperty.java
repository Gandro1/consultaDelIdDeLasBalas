/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
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
/*    */ 
/*    */ public class ExplosionJsonProperty
/*    */   extends JsonProperty<ExplosionModifier.ExplosionModifierValue>
/*    */ {
/*    */   public ExplosionJsonProperty(ExplosionModifier.ExplosionModifierValue value) {
/* 82 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 87 */     ExplosionModifier.ExplosionModifierValue modifierValue = (ExplosionModifier.ExplosionModifierValue)getValue();
/* 88 */     if (modifierValue != null && modifierValue.explode)
/* 89 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.explosion").m_130940_(ChatFormatting.GOLD)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\ExplosionModifier$ExplosionJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */